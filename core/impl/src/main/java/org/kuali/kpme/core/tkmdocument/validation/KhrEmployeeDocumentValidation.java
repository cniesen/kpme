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
package org.kuali.kpme.core.tkmdocument.validation;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.KPMEConstants;
import org.kuali.kpme.core.api.calendar.Calendar;
import org.kuali.kpme.core.api.calendar.entry.CalendarEntry;
import org.kuali.kpme.core.api.namespace.KPMENamespace;
import org.kuali.kpme.core.api.principal.PrincipalHRAttributes;
import org.kuali.kpme.core.api.workarea.WorkArea;
import org.kuali.kpme.core.assignment.AssignmentBo;
import org.kuali.kpme.core.assignment.account.AssignmentAccountBo;
import org.kuali.kpme.core.authorization.KPMEMaintenanceDocumentViewAuthorizer;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.calendar.CalendarBo;
import org.kuali.kpme.core.job.authorization.JobAuthorizer;
import org.kuali.kpme.core.principal.PrincipalHRAttributesBo;
import org.kuali.kpme.core.role.KPMERole;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.tkmdocument.KhrEmployeeDocument;
import org.kuali.kpme.core.tkmdocument.tkmjob.KhrEmployeeJob;
import org.kuali.kpme.core.util.HrContext;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.maintenance.MaintenanceDocumentAuthorizer;
import org.kuali.rice.krad.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.RouteToCompletionUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;

public class KhrEmployeeDocumentValidation extends MaintenanceDocumentRuleBase {

    @Override
    protected boolean processGlobalSaveDocumentBusinessRules(MaintenanceDocument document) {
        return true;
    }

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean valid = false;
        LOG.debug("entering custom validation for TKMDocument");
        KhrEmployeeDocument tkmDocument = (KhrEmployeeDocument) this.getNewDataObject();

        if (tkmDocument != null) {
            valid = true;
            valid &= this.validateJobLines(tkmDocument);
        }
        return valid;
    }

    protected boolean validateJobLines(KhrEmployeeDocument tkmDocument) {
        boolean valid = true;
        if (CollectionUtils.isNotEmpty(tkmDocument.getJobList())) {
            for(ListIterator<KhrEmployeeJob> jobIterator = tkmDocument.getJobList().listIterator(); jobIterator.hasNext();)  {
                int index = jobIterator.nextIndex();
                KhrEmployeeJob tkmJob = jobIterator.next();
                valid &= isValidEffectiveDate(tkmDocument, tkmJob, index);
                //validate comp rate
                valid &= isValidCompRate(tkmJob, index);
                valid &= isValidEndDate(tkmJob, index);
                valid &= canMaintainJob(tkmJob, index);
                valid &= isValidPayGrade(tkmJob, index);
                valid &= isValidAssignment(tkmJob, index);


            }
        }

        return valid;

    }

    private boolean isValidAssignment(KhrEmployeeJob tkmJob, int index) {
        //validate Assignments
        boolean valid = true;
        if (CollectionUtils.isNotEmpty(tkmJob.getAssignments())) {
            for(ListIterator<AssignmentBo> assignmentIterator = tkmJob.getAssignments().listIterator(); assignmentIterator.hasNext();) {
                int assignmentIndex = assignmentIterator.nextIndex();
                AssignmentBo assignment = assignmentIterator.next();
                valid &= isValidAssignmentEffectiveDate(tkmJob, assignment, assignmentIndex, index);
                valid &= isValidWorkareaDepartment(tkmJob, assignment, assignmentIndex, index);
                valid &= isValidAssignmentWorkArea(assignment, assignmentIndex, index);
                valid &= isValidAssignmentAccounts(assignment, assignmentIndex, index);
            }

        } else {
            this.putFieldError("dataObject.jobList["+ index +"].jobNumber","tkmjob.no.assignment");
            valid = false;
        }
        return valid;
    }

    protected boolean isValidAssignmentAccounts(AssignmentBo assignment, int assignmentIndex, int index) {
        boolean valid = true;
        if (CollectionUtils.isNotEmpty(assignment.getAssignmentAccounts())) {
            BigDecimal totalPercent = new BigDecimal(0);
            for(ListIterator<AssignmentAccountBo> assignmentAccountIterator = assignment.getAssignmentAccounts().listIterator(); assignmentAccountIterator.hasNext();) {
                int assignmentAccountIndex = assignmentAccountIterator.nextIndex();
                AssignmentAccountBo assignmentAccount = assignmentAccountIterator.next();

                totalPercent = totalPercent.add(assignmentAccount.getPercent());
                valid &= isValidAccount(assignmentAccount, index, assignmentIndex, assignmentAccountIndex);
                valid &= isValidSubAccount(assignmentAccount, index, assignmentIndex, assignmentAccountIndex);


            }

            valid &= isValidAccountPercent(totalPercent, index, assignmentIndex);
        } else {
            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].workArea","earncode.regular.pay.required");
            valid = false;
        }
        return valid;
    }

    protected boolean isValidAccountPercent(BigDecimal totalPercent, int index, int assignmentIndex) {
        if (totalPercent.compareTo(new BigDecimal(100)) != 0) {
            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].workArea","error.active.account.percentage");
            return false;
        }
        return true;
    }

    protected boolean isValidSubAccount(AssignmentAccountBo assignmentAccount, int index, int assignmentIndex, int assignmentAccountIndex) {
        //validate sub account
        /*if (StringUtils.isNotEmpty(assignmentAccount.getSubAcctNbr())) {
            if (!ValidationUtils.validateSubAccount(assignmentAccount.getSubAcctNbr(), assignmentAccount.getAccountNbr(), "IS")) {
                String[] params = new String[2];
                params[0] = assignmentAccount.getSubAcctNbr();
                params[1] = assignmentAccount.getAccountNbr();
                this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].assignmentAccounts[" + assignmentAccountIndex + "].subAcctNbr","tkmdocument.assignment.subacct.exists",params);
                return false;
            }
        }*/
        return true;
    }

    private boolean isValidAccount(AssignmentAccountBo assignmentAccount, int index, int assignmentIndex, int assignmentAccountIndex) {
        //validate account
        //todo figure out how to pass chart
        /*if (!ValidationUtils.validateAccount("IS", assignmentAccount.getAccountNbr())) {
            String[] params = new String[1];
            params[0] = assignmentAccount.getFinCoaCd();
            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].assignmentAccounts[" + assignmentAccountIndex + "].accountNbr","exists.account");
            return false;
        }*/
        return true;
    }

    protected boolean isValidAssignmentWorkArea(AssignmentBo assignment, int assignmentIndex, int index) {
        //validate workarea
        WorkArea workArea = HrServiceLocator.getWorkAreaService().getWorkArea(assignment.getWorkArea(),assignment.getEffectiveLocalDate());
        if (workArea == null) {
            String[] params = new String[1];
            params[0] = assignment.getWorkArea().toString();
            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].workArea","tkmdocument.assignment.workarea.not.active",params);
            return false;
        }
        return true;
    }

    protected boolean isValidWorkareaDepartment(KhrEmployeeJob tkmJob, AssignmentBo assignment, int assignmentIndex, int index) {
        //validate workarea department
        List<Long> validWorkAreasforDepartment = HrServiceLocator.getWorkAreaService().getWorkAreasForDepartment(tkmJob.getDept(),tkmJob.getEffectiveLocalDate());
        if(!validWorkAreasforDepartment.contains(assignment.getWorkArea())){
            String[] params = new String[2];
            params[0] = assignment.getWorkArea().toString();
            params[1] = tkmJob.getDept();
            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].workArea","tkmdocument.assignment.workarea.wrong.dept",params);
            return false;
        }
        return true;
    }

    protected boolean isValidAssignmentEffectiveDate(KhrEmployeeJob tkmJob, AssignmentBo assignment, int assignmentIndex, int index) {
        //validate assignment effective date

        //assignment.
        if (assignment.getEffectiveDate().before(tkmJob.getEffectiveDate()) && (assignment.getId() == null) ) {
            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].effectiveDate", "tkmdocument.assignment.effective.before.job");
            return false;
        }
        return true;
    }

    protected boolean isValidPayGrade(KhrEmployeeJob tkmJob, int index) {
        //validate pay grade
        /*String validSalGroup = "XH"; //todo create parameter for valid salary groups
        List<String> validPayGrades  = new ArrayList<String>();

        for (PayGrade payGrade : HrServiceLocator.getPayGradeService().getPayGrades("", "", validSalGroup, "Y", "N")) {
            validPayGrades.add(payGrade.getPayGrade());
        }

        if(!validPayGrades.contains(tkmJob.getPayGrade())){
            String[] params = new String[1];
            params[0] = validSalGroup;
            this.putFieldError("dataObject.jobList["+ index +"].payGrade","tkmjob.invalid.paygrade",params);
            return false;
        }*/
        return true;
    }
    
    // check permission re-using the job doc authorizer
    public boolean canMaintainJob(KhrEmployeeJob tkmJob) {
    	Person user = KimApiServiceLocator.getPersonService().getPerson(HrContext.getTargetPrincipalId());
    	if( !(((KPMEMaintenanceDocumentViewAuthorizer)(KRADServiceLocatorWeb.getDocumentDictionaryService().getDocumentAuthorizer("JobMaintenanceDocumentType"))).canMaintain(tkmJob, user)) ) {
            return false;
        }
        return true;
    }

    protected boolean canMaintainJob(KhrEmployeeJob tkmJob, int index) {
        if(!canMaintainJob(tkmJob)) {
        	String[] params = new String[1];
            params[0] = tkmJob.getDept();
            this.putFieldError("dataObject.jobList[" + index + "].dept", "tkmdocument.job.permissions", params);
        	return false;
        }
        return true;
    }

    protected boolean isValidEndDate(KhrEmployeeJob tkmJob, int index) {
        //validate end date
        if (tkmJob.getEndDate() != null && tkmJob.getEndDate().after(LocalDate.now().plusMonths(18).toDate())) {
            this.putFieldError("dataObject.jobList["+ index +"].endDate","tkmjob.invalid.end.date");
            return false;
        }
        return true;
    }

    protected boolean isValidCompRate(KhrEmployeeJob tkmJob, int index) {
    	BigDecimal miniWage = new BigDecimal(7.25);
    	String miniWageString = ConfigContext.getCurrentContextConfig().getProperty(KPMEConstants.MINIMUM_WAGE);
    	if(StringUtils.isNotBlank(miniWageString)) {    	
    		miniWage = new BigDecimal(miniWageString);
    	}
        if (tkmJob.getCompRate().bigDecimalValue().compareTo(miniWage) < 0) {
        	String[] params = new String[1];
            params[0] = miniWage.toString();
            this.putFieldError("dataObject.jobList["+ index +"].compRate","tkmjob.minimum.wage", params);
            return false;
        }
        return true;
    }

    protected boolean isValidEffectiveDate(KhrEmployeeDocument tkmDocument, KhrEmployeeJob tkmJob, int index) {
        //validate job effective date against document start date
        if (tkmJob.getEffectiveDate().before(tkmDocument.getStartDate())) {
            this.putFieldError("dataObject.jobList["+ index +"].effectiveDate","tkmjob.effective.before.start");
            return false;
        }
        
       //validate job effective date against job end date
        if (tkmJob.getEndDate() != null && tkmJob.getEffectiveDate().after(tkmJob.getEndDate())) {
            this.putFieldError("dataObject.jobList["+ index +"].effectiveDate","tkmjob.effective.before.end");
            return false;
        }
        
        // use the job's effective date to find the calendar entry it's for, then check if the current time is 
        // after the final approval time of the calendar entry. If it is, we should not allow the effective date to be used
        boolean effectiveDateChanged = false;
        if(StringUtils.isNotBlank(tkmJob.getHrJobId())) {
        	HrBusinessObject existingJob = KRADServiceLocator.getBusinessObjectService().findBySinglePrimaryKey(KhrEmployeeJob.class, tkmJob.getId());
        	if(existingJob != null && !existingJob.getEffectiveDate().equals(tkmJob.getEffectiveDate())) {
        		effectiveDateChanged = true;
        	}
        } else {
        	effectiveDateChanged = true;
        }
		if(effectiveDateChanged) {        
	        PrincipalHRAttributes ph = HrServiceLocator.getPrincipalHRAttributeService().getPrincipalCalendar(tkmDocument.getPrincipalId(), LocalDate.now()) ;
	        if(ph != null) {
	        	Calendar aCal = ph.getCalendar();
	        	if(aCal != null) {
	        		CalendarEntry aCalEntry = HrServiceLocator.getCalendarEntryService().getCurrentCalendarEntryByCalendarId(aCal.getHrCalendarId(), tkmJob.getEffectiveLocalDate().toDateTimeAtStartOfDay());
	        		if(aCalEntry != null && aCalEntry.getBatchPayrollApprovalLocalTime() != null) {
	        			if(LocalDate.now().toDateTimeAtStartOfDay().isAfter(aCalEntry.getBatchPayrollApprovalFullDateTime())) {
	        				this.putFieldError("dataObject.jobList["+ index +"].effectiveDate","tkmjob.calendar.entry.approved");
	        				return false;
	        			}
	        		}
	        	}        			
	        }
		}
     
        
        return true;
    }


}
