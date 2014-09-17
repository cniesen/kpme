/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.kpme.tklm.time.missedpunch;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.assignment.Assignment;
import org.kuali.kpme.core.api.util.KpmeUtils;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.util.HrContext;
import org.kuali.kpme.core.util.TKUtils;
import org.kuali.kpme.tklm.api.common.TkConstants;
import org.kuali.kpme.tklm.time.missedpunch.web.MissedPunchForm;
import org.kuali.kpme.tklm.time.rules.timecollection.TimeCollectionRule;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;
import org.kuali.kpme.tklm.time.timesheet.TimesheetDocument;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.core.api.util.Truth;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.view.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MissedPunchAssignmentKeyValuesFinder extends UifKeyValuesFinderBase {

	private static final long serialVersionUID = -6828936184460318588L;

	@Override
	public List<KeyValue> getKeyValues(ViewModel model) {
		List<KeyValue> labels = new ArrayList<KeyValue>();

		if (model instanceof MissedPunchForm) {
			MissedPunchForm missedPunchForm = (MissedPunchForm) model;
			MissedPunchDocument missedPunchDocument = (MissedPunchDocument) missedPunchForm.getDocument();
			MissedPunchBo mp = missedPunchDocument == null ? missedPunchForm.getMissedPunch() : missedPunchDocument.getMissedPunch();
			LocalDate mpDate = mp.getLocalDate();
			if(mpDate == null) {
                mpDate = mp.getActionLocalDate();
                if (mpDate == null) {
                    mpDate = LocalDate.now();
                }
			}
			String timesheetDocumentId = missedPunchDocument != null ? missedPunchDocument.getMissedPunch().getTimesheetDocumentId() : missedPunchForm.getMissedPunch().getTimesheetDocumentId();
			if (StringUtils.isNotBlank(timesheetDocumentId)) {
				TimesheetDocument timesheetDocument = TkServiceLocator.getTimesheetService().getTimesheetDocument(timesheetDocumentId);

                List<Assignment> dayAssignments = KpmeUtils.getUniqueAssignments(HrServiceLocator.getAssignmentService().getAssignmentHistoryBetweenDays(timesheetDocument.getPrincipalId(), mpDate, mpDate));
                List<Assignment> assignments = new ArrayList<Assignment>();
                if (CollectionUtils.isNotEmpty(dayAssignments)) {
                    for (Assignment assignment : dayAssignments) {

                        TimeCollectionRule tcr = null;
                        if (assignment.getJob() != null) {
                            tcr = TkServiceLocator.getTimeCollectionRuleService().getTimeCollectionRule(assignment.getJob().getDept(), assignment.getWorkArea(), assignment.getJob().getHrPayType(), assignment.getGroupKeyCode(), mpDate);
                        }
                        boolean isSynchronous = tcr == null || tcr.isClockUserFl();
                        if (isSynchronous) {
                            assignments.add(assignment);
                        }
                    }
                }

                if (CollectionUtils.isEmpty(assignments)) {
                    assignments = Collections.emptyList();
                }
				
				if(missedPunchForm.getIpAddress()!=null){
					String ipAddress = TKUtils.getIPAddressFromRequest(missedPunchForm.getIpAddress());

					//Map<String, String> assignmentDescMap = timesheetDocument.getAssignmentDescriptions(true, mpDate);
					String targetPrincipalId = HrContext.getTargetPrincipalId(); 	            
					String principalId = HrContext.getPrincipalId();
					if(targetPrincipalId.equals(principalId)){
						DateTime currentDateTime = new DateTime();
                        //Boolean allowActionFromInvalidLocation = Truth.strToBooleanIgnoreCase(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ALLOW_CLOCKING_EMPLOYEE_FROM_INVALID_LOCATION), false);
                        Boolean limitMPAssignmentsFromInvalidLocation = Truth.strToBooleanIgnoreCase(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.LIMIT_MP_ASSIGN_FROM_INVALIDLOCATION), false);

                        for (Assignment assignment : assignments) {
							//Assignment assignment = timesheetDocument.getAssignment(AssignmentDescriptionKey.get(entry.getKey()), LocalDate.now());

							if(limitMPAssignmentsFromInvalidLocation) {
								boolean isInvalid = TkServiceLocator.getClockLocationRuleService().isInvalidIPClockLocation(assignment.getGroupKeyCode(), assignment.getDept(), assignment.getWorkArea(), assignment.getPrincipalId(), assignment.getJobNumber(), ipAddress, currentDateTime.toLocalDate());
								if(!isInvalid){
									labels.add(new ConcreteKeyValue(assignment.getAssignmentKey(),assignment.getAssignmentDescription()));
								}
							} else {
                                labels.add(new ConcreteKeyValue(assignment.getAssignmentKey(),assignment.getAssignmentDescription()));
                            }
						}
					}else{
						for (Assignment assignment : assignments) {
							labels.add(new ConcreteKeyValue(assignment.getAssignmentKey(),assignment.getAssignmentDescription()));
						}
					}
				}else{
                    if (CollectionUtils.isNotEmpty(assignments)) {
                        for (Assignment assignment : assignments) {
                            labels.add(new ConcreteKeyValue(assignment.getAssignmentKey(), assignment.getAssignmentDescription()));
                        }
                    }
				}
			}
		}
		if(labels.size()>1){
			List<KeyValue> newLables = new ArrayList<KeyValue>();
			newLables.add(new ConcreteKeyValue("", ""));
			newLables.addAll(labels);
			labels = newLables;
		}
        if(labels.size()==0){
            labels.add(new ConcreteKeyValue("", "--- No asssignments for date  ---"));
        }
		return labels;
	}

}
