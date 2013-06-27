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
package org.kuali.hr.time.clocklog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.clocklog.ClockLog;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.missedpunch.MissedPunchDocument;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUser;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.workflow.TimesheetDocumentHeader;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

@SuppressWarnings("deprecation")
public class ClockLogLookupableHelper extends KualiLookupableHelperServiceImpl {

	private static final long serialVersionUID = -469827905426221716L;

	@Override
    @SuppressWarnings("rawtypes")
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> customActionUrls = super.getCustomActionUrls(businessObject, pkNames);

		ClockLog clockLog = (ClockLog)businessObject;
		String tkClockLogId = clockLog.getTkClockLogId();
		
		MissedPunchDocument missedPunchDocument = TkServiceLocator.getMissedPunchService().getMissedPunchByClockLogId(clockLog.getTkClockLogId());
		if (missedPunchDocument != null) {
			clockLog.setMissedPunchDocumentId(missedPunchDocument.getDocumentNumber());
		}
		
		List<HtmlData> overrideUrls = new ArrayList<HtmlData>();
		for (HtmlData actionUrl : customActionUrls) {
			if(!StringUtils.equals(actionUrl.getMethodToCall(), "copy")){
				overrideUrls.add(actionUrl);
			}
		}
		
		if (TKUser.isSystemAdmin() || TKUser.isGlobalViewOnly()) {
	        Properties params = new Properties();
	        params.put(KRADConstants.BUSINESS_OBJECT_CLASS_ATTRIBUTE, getBusinessObjectClass().getName());
	        params.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.MAINTENANCE_NEW_METHOD_TO_CALL);
	        params.put("tkClockLogId", tkClockLogId);
			HtmlData.AnchorHtmlData viewUrl = new HtmlData.AnchorHtmlData(UrlFactory.parameterizeUrl(KRADConstants.INQUIRY_ACTION, params), "view");
	        viewUrl.setDisplayText("view");
			viewUrl.setTarget(HtmlData.AnchorHtmlData.TARGET_BLANK);
			overrideUrls.add(viewUrl);
		}
		
		return overrideUrls;
	}

	@Override
	public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
		List<ClockLog> results = new ArrayList<ClockLog>();

        List<? extends BusinessObject> searchResults = super.getSearchResults(fieldValues);

        for (BusinessObject searchResult : searchResults) {
            ClockLog clockLog = (ClockLog) searchResult;
            results.add(clockLog);
        }
		
        results = filterByPrincipalId(results, GlobalVariables.getUserSession().getPrincipalId());

		return results;
	}
	
    private List<ClockLog> filterByPrincipalId(List<ClockLog> clockLogs, String principalId) {
        List<ClockLog> results = new ArrayList<ClockLog>();

        for (ClockLog clockLog : clockLogs) {
            if (clockLog.getDocumentId() == null) {
                TimesheetDocumentHeader tsdh = TkServiceLocator.getTimesheetDocumentHeaderService().getDocumentHeaderForDate(clockLog.getPrincipalId(), clockLog.getClockTimestamp());
                if (tsdh != null) {
                    clockLog.setDocumentId(tsdh.getDocumentId());
                }
            }

            Job jobObj = TkServiceLocator.getJobService().getJob(clockLog.getUserPrincipalId(), clockLog.getJobNumber(), TKUtils.getCurrentDate(), false);
            String department = jobObj != null ? jobObj.getDept() : null;

            Department departmentObj = jobObj != null ? TkServiceLocator.getDepartmentService().getDepartment(department, jobObj.getEffectiveDate()) : null;
            String location = departmentObj != null ? departmentObj.getLocation() : null;

            List<TkRole> tkRoles = TkServiceLocator.getTkRoleService().getRoles(TKContext.getPrincipalId(), TKUtils.getCurrentDate());
            for (TkRole tkRole : tkRoles) {
	            if (StringUtils.equals(tkRole.getRoleName(), TkConstants.ROLE_TK_SYS_ADMIN) 
						|| (StringUtils.equals(tkRole.getRoleName(), TkConstants.ROLE_TK_APPROVER) && clockLog.getWorkArea().equals(tkRole.getWorkArea()))
						|| (StringUtils.equals(tkRole.getRoleName(), TkConstants.ROLE_TK_DEPT_ADMIN) && StringUtils.equals(department, tkRole.getDepartment()))) {
	            	results.add(clockLog);
					break;
				} else if (StringUtils.equals(tkRole.getRoleName(), TkConstants.ROLE_TK_LOCATION_ADMIN)) {
					List<Department> locationDepartments = TkServiceLocator.getDepartmentService().getDepartmentByLocation(location);
					for (Department locationDepartment : locationDepartments) {
						if (StringUtils.equals(department, locationDepartment.getDept())) {
							results.add(clockLog);
							break;
						}
					}
				}
            }
        }

        return results;
    }
    
}