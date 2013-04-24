/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.hr.time.timeblock.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.hr.core.KPMENamespace;
import org.kuali.hr.core.lookup.KPMELookupableHelper;
import org.kuali.hr.core.permission.KPMEPermissionTemplate;
import org.kuali.hr.core.role.KPMERoleMemberAttribute;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.timeblock.TimeBlock;
import org.kuali.hr.time.timeblock.TimeHourDetail;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

@SuppressWarnings("deprecation")
public class TimeBlockLookupableHelperServiceImpl extends KPMELookupableHelper {

	private static final long serialVersionUID = 4941254351346463875L;

	private static final String DOCUMENT_STATUS = "timesheetDocumentHeader.documentStatus";
	private static final String BEGIN_DATE = "beginDate";

	@Override
	public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
		List<TimeBlock> results = new ArrayList<TimeBlock>();

		String documentStatus = StringUtils.EMPTY;
		String beginDate = StringUtils.EMPTY;

		if (fieldValues.containsKey(DOCUMENT_STATUS)) {
			documentStatus = fieldValues.get(DOCUMENT_STATUS);
			fieldValues.remove(DOCUMENT_STATUS);
		}

		if (fieldValues.containsKey(BEGIN_DATE)) {
			beginDate = fieldValues.get(BEGIN_DATE);
			fieldValues.remove(BEGIN_DATE);
		}

		List<? extends BusinessObject> searchResults = super.getSearchResults(fieldValues);
		
		for (BusinessObject searchResult : searchResults) {
			TimeBlock timeBlock = (TimeBlock) searchResult;
			results.add(timeBlock);
		}

		results = filterByDocumentStatus(results, documentStatus);
		results = filterByBeginDate(results, beginDate);
		results = filterByPrincipalId(results, GlobalVariables.getUserSession().getPrincipalId());
		results = addDetails(results);
		
		Collections.sort(results, new Comparator<TimeBlock>() {
			@Override
			public int compare(TimeBlock timeBlock1, TimeBlock timeBlock2) {
				return timeBlock1.getTkTimeBlockId().compareTo(timeBlock2.getTkTimeBlockId());
			}
		});

		return results;
	}

	private List<TimeBlock> filterByDocumentStatus(List<TimeBlock> timeBlocks, String documentStatus) {
		List<TimeBlock> results = new ArrayList<TimeBlock>();

		if (StringUtils.isNotEmpty(documentStatus)) {
			for (TimeBlock timeBlock : timeBlocks) {
				if (timeBlock.getTimesheetDocumentHeader() != null) {
					if (timeBlock.getTimesheetDocumentHeader().getDocumentStatus() != null) {
						if (timeBlock.getTimesheetDocumentHeader().getDocumentStatus().equals(documentStatus)) {
							results.add(timeBlock);
						}
					}
				}
			}
		} else {
			results.addAll(timeBlocks);
		}

		return results;
	}

	private List<TimeBlock> filterByBeginDate(List<TimeBlock> timeBlocks, String beginDate) {
		List<TimeBlock> results = new ArrayList<TimeBlock>();

		if (StringUtils.isNotEmpty(beginDate)) {
			for (TimeBlock timeBlock : timeBlocks) {
				if (timeBlock.getBeginDate() != null) {
					if (TKUtils.isDateEqualOrBetween(timeBlock, timeBlock.getBeginDateTime(), beginDate)) {
						results.add(timeBlock);
					}
				}
			}
		} else {
			results.addAll(timeBlocks);
		}

		return results;
	}
	
	private List<TimeBlock> filterByPrincipalId(List<TimeBlock> timeBlocks, String principalId) {
		List<TimeBlock> results = new ArrayList<TimeBlock>();
		
		for (TimeBlock timeBlock : timeBlocks) {
			Job jobObj = TkServiceLocator.getJobService().getJob(timeBlock.getPrincipalId(), timeBlock.getJobNumber(), LocalDate.now(), false);
			String department = jobObj != null ? jobObj.getDept() : null;

			Department departmentObj = jobObj != null ? TkServiceLocator.getDepartmentService().getDepartment(department, jobObj.getEffectiveLocalDate()) : null;
			String location = departmentObj != null ? departmentObj.getLocation() : null;

			Map<String, String> roleQualification = new HashMap<String, String>();
			roleQualification.put(KimConstants.AttributeConstants.PRINCIPAL_ID, GlobalVariables.getUserSession().getPrincipalId());
			roleQualification.put(KPMERoleMemberAttribute.DEPARTMENT.getRoleMemberAttributeName(), department);
			roleQualification.put(KPMERoleMemberAttribute.LOCATION.getRoleMemberAttributeName(), location);

			if (!KimApiServiceLocator.getPermissionService().isPermissionDefinedByTemplate(KPMENamespace.KPME_WKFLW.getNamespaceCode(),
					KPMEPermissionTemplate.VIEW_KPME_RECORD.getPermissionTemplateName(), new HashMap<String, String>())
					|| KimApiServiceLocator.getPermissionService().isAuthorizedByTemplate(principalId, KPMENamespace.KPME_WKFLW.getNamespaceCode(),
							KPMEPermissionTemplate.VIEW_KPME_RECORD.getPermissionTemplateName(), new HashMap<String, String>(), roleQualification)) {
				results.add(timeBlock);
			}
		}
		
		return results;
	}

	private List<TimeBlock> addDetails(List<TimeBlock> timeBlocks) {
		List<TimeBlock> results = new ArrayList<TimeBlock>(timeBlocks);

		for (TimeBlock timeBlock : timeBlocks) {
			List<TimeHourDetail> timeHourDetails = timeBlock.getTimeHourDetails();

			for (TimeHourDetail timeHourDetail : timeHourDetails) {
				if (!timeHourDetail.getEarnCode().equalsIgnoreCase(timeBlock.getEarnCode())) {
					TimeBlock newTimeBlock = timeBlock.copy();
					newTimeBlock.setEarnCode(timeHourDetail.getEarnCode());
					newTimeBlock.setHours(timeHourDetail.getHours());
					newTimeBlock.setAmount(timeHourDetail.getAmount());
					results.add(newTimeBlock);
				}
			}
		}

		return results;
	}

}