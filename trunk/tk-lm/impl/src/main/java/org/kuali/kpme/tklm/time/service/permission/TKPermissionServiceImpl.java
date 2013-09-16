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
package org.kuali.kpme.tklm.time.service.permission;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.KPMENamespace;
import org.kuali.kpme.core.assignment.Assignment;
import org.kuali.kpme.core.assignment.AssignmentDescriptionKey;
import org.kuali.kpme.core.block.CalendarBlockPermissions;
import org.kuali.kpme.core.department.Department;
import org.kuali.kpme.core.earncode.security.EarnCodeSecurity;
import org.kuali.kpme.core.job.Job;
import org.kuali.kpme.core.paytype.PayType;
import org.kuali.kpme.core.role.KPMERole;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.service.permission.HrPermissionServiceBase;
import org.kuali.kpme.core.util.HrContext;
import org.kuali.kpme.core.workarea.WorkArea;
import org.kuali.kpme.tklm.time.rules.timecollection.TimeCollectionRule;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;
import org.kuali.kpme.tklm.time.timeblock.TimeBlock;
import org.kuali.kpme.tklm.time.timesheet.service.TimesheetService;
import org.kuali.kpme.tklm.time.util.TkContext;
import org.kuali.kpme.tklm.time.workflow.TimesheetDocumentHeader;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kim.api.permission.PermissionService;

public class TKPermissionServiceImpl extends HrPermissionServiceBase implements TKPermissionService {
	
	private PermissionService permissionService;
	private TimesheetService timesheetService;

	@Override
	public boolean isAuthorized(String principalId, String permissionName, DateTime asOfDate) {
		Map<String, String> qualification = new HashMap<String, String>();
		
		return isAuthorized(principalId, permissionName, qualification, asOfDate);
	}

	@Override
	public boolean isAuthorized(String principalId, String permissionName, Map<String, String> qualification, DateTime asOfDate) {
		return getPermissionService().isAuthorized(principalId, KPMENamespace.KPME_TK.getNamespaceCode(), permissionName, qualification);
	}
    
    @Override
	public boolean isAuthorizedByTemplate(String principalId, String namespaceCode, String permissionTemplateName, Map<String, String> permissionDetails, DateTime asOfDate) {
		Map<String, String> qualification = new HashMap<String, String>();
		
		return isAuthorizedByTemplate(principalId, namespaceCode, permissionTemplateName, permissionDetails, qualification, asOfDate);
	}
	
    @Override
	public boolean isAuthorizedByTemplate(String principalId, String namespaceCode, String permissionTemplateName, Map<String, String> permissionDetails, Map<String, String> qualification, DateTime asOfDate) {
		return getPermissionService().isAuthorizedByTemplate(principalId, namespaceCode, permissionTemplateName, permissionDetails, qualification);
	}

    private boolean updateCanEditTimeblockPerm(String principalId, CalendarBlockPermissions perms, boolean canEdit) {
        perms.putCanEdit(principalId, canEdit);
        HrServiceLocator.getHRPermissionService().updateTimeBlockPermissions(perms);
        return canEdit;
    }

    private boolean updateCanEditAllFieldsTimeblockPerm(String principalId, CalendarBlockPermissions perms, boolean canEditAll) {
        perms.putCanEditAllFields(principalId, canEditAll);
        HrServiceLocator.getHRPermissionService().updateTimeBlockPermissions(perms);
        return canEditAll;
    }

    private boolean updateCanDeleteTimeblockPerm(String principalId, CalendarBlockPermissions perms, boolean canDelete) {
        perms.putCanDelete(principalId, canDelete);
        HrServiceLocator.getHRPermissionService().updateTimeBlockPermissions(perms);
        return canDelete;
    }

    private boolean updateCanEditOvtPerm(String principalId, CalendarBlockPermissions perms, boolean canEditOvt) {
        perms.putCanEditOvertimeEarnCode(principalId, canEditOvt);
        HrServiceLocator.getHRPermissionService().updateTimeBlockPermissions(perms);
        return canEditOvt;
    }

    private boolean updateCanRegEarnCd(CalendarBlockPermissions perms, boolean canEditRegEarn) {
        perms.setCanEditRegEarnCode(canEditRegEarn);
        HrServiceLocator.getHRPermissionService().updateTimeBlockPermissions(perms);
        return canEditRegEarn;
    }

    @Override
    public boolean canEditTimeBlock(String principalId, TimeBlock timeBlock) {
        if (principalId != null) {
            //check cache!
            CalendarBlockPermissions perms = HrServiceLocator.getHRPermissionService().getTimeBlockPermissions(timeBlock.getTkTimeBlockId());
            Boolean canEdit = perms.isPrincipalCanEdit(principalId);

            if (canEdit != null)
                return canEdit;
            
            if(this.userHasRolesToEditTimeBlock(principalId, timeBlock))
            	return updateCanEditTimeblockPerm(principalId, perms, true);
            else
            	return updateCanEditTimeblockPerm(principalId, perms, false);
        }
        return false;
    }
    
    @Override
    public boolean userHasRolesToEditTimeBlock(String principalId, TimeBlock aTimeBlock) {
    	// system admin, TimeSysAdmin and time location admin have full permissions when they are not working on their own timesheet, no need to check earnCodeSecurity in this case
    	if(this.userHasTimeSysLocationAdminRoles(principalId,aTimeBlock) && !StringUtils.equals(TkContext.getTargetPrincipalId(), principalId))
    		return true;
    	
    	// timesheet is cancelled/disapproved, then no edit permissions for roles other than sys/location admins
    	if (StringUtils.isNotBlank(aTimeBlock.getDocumentId())) {
        	DocumentStatus documentStatus = KewApiServiceLocator.getWorkflowDocumentService().getDocumentStatus(aTimeBlock.getDocumentId());
        	if (DocumentStatus.CANCELED.equals(documentStatus) || DocumentStatus.DISAPPROVED.equals(documentStatus)) {
        		return false;
        	}
        }    	
    	
    	Job job = HrServiceLocator.getJobService().getJob(
                 HrContext.getTargetPrincipalId(), aTimeBlock.getJobNumber(),
                 aTimeBlock.getEndDateTime().toLocalDate());
    	// need to use earnCodeSecurity employee/approver/payrollProcessor flags to determine if the user has edit permission to the earn code 
    	List<EarnCodeSecurity> earnCodeSecurityList = HrServiceLocator
                  .getEarnCodeSecurityService().getEarnCodeSecurities(
                          job.getDept(), job.getHrSalGroup(),
                          job.getLocation(), aTimeBlock.getEndDateTime().toLocalDate());
    	// no earn code security found, then no edit permissions for roles other than sys/location admins
    	if(CollectionUtils.isEmpty(earnCodeSecurityList))
    		return false; 
    	
        PayType payType = HrServiceLocator.getPayTypeService().getPayType(
                 job.getHrPayType(), aTimeBlock.getEndDateTime().toLocalDate()); 
        
    	// user working on his/her own timesheet
        if (StringUtils.equals(TkContext.getTargetPrincipalId(), principalId)) {    	
        	boolean employeeSecurityFlag = false;
        	for(EarnCodeSecurity ecs : earnCodeSecurityList ) {
        		if(ecs.isEmployee() && StringUtils.equals(ecs.getEarnCode(), aTimeBlock.getEarnCode())) {
        			employeeSecurityFlag = true;
        			break;
        		}
        	}        	
        	if(employeeSecurityFlag) {	// found earn code security with employee flag
        		// regular earn codes
		    	if (StringUtils.equals(payType.getRegEarnCode(), aTimeBlock.getEarnCode())) {		             
		            boolean moreThanOneClockAssignment = this.userHasMoreThanOneClockAssignment(principalId, aTimeBlock.getBeginDateTime().toLocalDate());
		            //If you are a clock user and you have only one Clock assignment you should not be allowed to change the time block
		            if(!moreThanOneClockAssignment){
		             	TimeCollectionRule tcr = TkServiceLocator.getTimeCollectionRuleService().getTimeCollectionRule(job.getDept(),aTimeBlock.getWorkArea(),job.getHrPayType(), aTimeBlock.getBeginDateTime().toLocalDate());
		                 return  (tcr != null && !tcr.isClockUserFl());
		            } else {
		                return true;
		            }
		        } else {
		        	return true;
		        }
        	}        	
        } else {        // user not working on his/her own timesheet		    
	    	DateTime asOfDate = LocalDate.now().toDateTimeAtStartOfDay();
		    // Reviewer/Approver/Approver Delegate
		    if(HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.REVIEWER.getRoleName(), aTimeBlock.getWorkArea(), asOfDate)
		    	|| HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.APPROVER.getRoleName(), aTimeBlock.getWorkArea(), asOfDate)
		    	|| HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.APPROVER_DELEGATE.getRoleName(), aTimeBlock.getWorkArea(), asOfDate)) {
		    	for(EarnCodeSecurity ecs : earnCodeSecurityList ) {
	        		if(ecs.isApprover() && StringUtils.equals(ecs.getEarnCode(),aTimeBlock.getEarnCode()))
	        			return true;
	        	}
	        }
		    
		    // Payroll Processor / Payroll Processor Delegate
		    if(HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.PAYROLL_PROCESSOR.getRoleName(), aTimeBlock.getWorkArea(), asOfDate)
		    	|| HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.PAYROLL_PROCESSOR_DELEGATE.getRoleName(), aTimeBlock.getWorkArea(), asOfDate)) {
		    	for(EarnCodeSecurity ecs : earnCodeSecurityList ) {
	        		if(ecs.isPayrollProcessor() && StringUtils.equals(ecs.getEarnCode(),aTimeBlock.getEarnCode()))
	        			return true;
	        	}
	        }
        }
        
	    // no eligible roles found
	    return false;
    }
    
    private boolean userHasMoreThanOneClockAssignment(String principalId, LocalDate aLocalDate) {
    	 List<Assignment> assignments = HrServiceLocator.getAssignmentService().getAssignments(principalId, aLocalDate);
         int clockAssignNumber = 0;
         for(Assignment anAssign : assignments) {
        	 Job aJob = HrServiceLocator.getJobService().getJob(HrContext.getTargetPrincipalId(), anAssign.getJobNumber(), aLocalDate);
        	 TimeCollectionRule tcr = TkServiceLocator.getTimeCollectionRuleService().getTimeCollectionRule(anAssign.getDept(), anAssign.getWorkArea(),aJob.getHrPayType(), aLocalDate);
        	 if(tcr != null && tcr.isClockUserFl())
        		 clockAssignNumber ++;
         }
         return clockAssignNumber > 1;
    }

    @Override
	public boolean userHasTimeSysLocationAdminRoles(String principalId,TimeBlock aTimeBlock) {
		DateTime asOfDate = LocalDate.now().toDateTimeAtStartOfDay();
		// system admin or TimeSysAdmin has full permissions when they are not working on their own timesheet, no need to check earnCodeSecurity in this case
		if(HrServiceLocator.getKPMEGroupService().isMemberOfSystemAdministratorGroup(principalId, asOfDate )
				|| HrServiceLocator.getKPMERoleService().principalHasRole(principalId, KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_SYSTEM_ADMINISTRATOR.getRoleName(), asOfDate)) {
			return true;
		}		    
		// use job to find the department, then use the location from Department to get the location roles 
		Job aJob = HrServiceLocator.getJobService().getJob(aTimeBlock.getPrincipalId(), aTimeBlock.getJobNumber(), aTimeBlock.getEndDateTime().toLocalDate());
		if(aJob != null) {
			Department aDept = HrServiceLocator.getDepartmentService().getDepartment(aJob.getDept(), aJob.getEffectiveLocalDate());
			if(aDept != null) {
				// TimeLocationAdmin
			    if(HrServiceLocator.getKPMERoleService().principalHasRoleInLocation(principalId, KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_LOCATION_ADMINISTRATOR.getRoleName(), aDept.getLocation(), asOfDate))
			    	return true;
			}
		}
		return false;
	} 
    
    @Override
    public boolean canEditTimeBlockAllFields(String principalId, TimeBlock timeBlock) {
        if (principalId != null) {
            CalendarBlockPermissions perms = HrServiceLocator.getHRPermissionService().getTimeBlockPermissions(timeBlock.getTkTimeBlockId());
            Boolean canEditAll = perms.isPrincipalCanEditAllFields(principalId);

            if (canEditAll != null) {
                return canEditAll;
            }

            // if the user does not have permission to edit the time block at all, no need to check any further
            // this cover the case when the user only have one clock assignment, so the regular earn code Clocked time block is not editable
            if(!this.canEditTimeBlock(principalId, timeBlock)) {
            	return updateCanEditAllFieldsTimeblockPerm(principalId, perms, false);
            } else {
            	// user is working on his/her own timesheet and has more than one clock assignment,
            	// user should only be able to edit assignment list field if the earn code is a regular one
	          if(principalId.equals(HrContext.getTargetPrincipalId())) {       	
	        	  Job job = HrServiceLocator.getJobService().getJob(
		        		  HrContext.getTargetPrincipalId(), timeBlock.getJobNumber(),
		        		  timeBlock.getEndDateTime().toLocalDate());
		          PayType payType = job.getPayTypeObj();		          
		          TimeCollectionRule tcr = TkServiceLocator.getTimeCollectionRuleService().
		        		  getTimeCollectionRule(job.getDept(), timeBlock.getWorkArea(),job.getHrPayType(),timeBlock.getBeginDateTime().toLocalDate());
		          
	              if (tcr != null && tcr.isClockUserFl() && StringUtils.equals(payType.getRegEarnCode(), timeBlock.getEarnCode())) {
	                  return updateCanEditAllFieldsTimeblockPerm(principalId, perms, false);
	              } else {
	            	  return updateCanEditAllFieldsTimeblockPerm(principalId, perms, true); 
	              }
	        	  
	            } else {
	            	return updateCanEditAllFieldsTimeblockPerm(principalId, perms, true);
	            }
            }
        }

        return false;
    }

    @Override
    public boolean canDeleteTimeBlock(String principalId, TimeBlock timeBlock) {
        if (principalId != null) {
            CalendarBlockPermissions perms = HrServiceLocator.getHRPermissionService().getTimeBlockPermissions(timeBlock.getTkTimeBlockId());
            Boolean canDelete = perms.isPrincipalCanDelete(principalId);

            if (canDelete != null) {
                return canDelete;
            }
       
            // if user cannot edit time block, they cannot delete it either
            if(!this.canEditTimeBlock(principalId, timeBlock)) {
            	return updateCanDeleteTimeblockPerm(principalId, perms, false);
            } else {
            	 Job job = HrServiceLocator.getJobService().getJob(
                         HrContext.getTargetPrincipalId(), timeBlock.getJobNumber(),
                         timeBlock.getEndDateTime().toLocalDate());
                 PayType payType = HrServiceLocator.getPayTypeService().getPayType(
                         job.getHrPayType(), timeBlock.getEndDateTime().toLocalDate());
            	  if (principalId.equals(HrContext.getTargetPrincipalId())) {
            		  // if the user is working on his/her own timesheet and the time block is clock generated, user should not be able to delete the time block
            		  if(timeBlock.getClockLogCreated()) {
      	                return updateCanDeleteTimeblockPerm(principalId, perms, false);
      				  }
            		  //if on a regular earncode and the user is a clock user and this is the users timesheet, do not allow to be deleted
            		  if(StringUtils.equals(payType.getRegEarnCode(), timeBlock.getEarnCode())) {
      	            	TimeCollectionRule tcr = TkServiceLocator.getTimeCollectionRuleService().getTimeCollectionRule(job.getDept(),timeBlock.getWorkArea(),payType.getPayType(),timeBlock.getEndDateTime().toLocalDate());
      	            	
      	            	if (tcr == null || tcr.isClockUserFl()) {
      	            		if (StringUtils.equals(principalId,HrContext.getTargetPrincipalId())) {
      	                        return updateCanDeleteTimeblockPerm(principalId, perms, false);
      		                }  else {
      	                        return updateCanDeleteTimeblockPerm(principalId, perms, true);
      		                }
      	                }
      	              }
            		  return updateCanDeleteTimeblockPerm(principalId, perms, true);
            	  } else {
            		  // user not working on his/her own timesheet and have permission to edit, so user should be able to delete the time block
            		  return updateCanDeleteTimeblockPerm(principalId, perms, true); 
            	  }
            }
        }
        return false;
    }
    
    @Override
    public boolean canEditOvertimeEarnCode(String principalId, TimeBlock timeBlock) {
        //String principalId = GlobalVariables.getUserSession().getPrincipalId();
        if (principalId != null) {
            CalendarBlockPermissions perms = HrServiceLocator.getHRPermissionService().getTimeBlockPermissions(timeBlock.getTkTimeBlockId());
            Boolean canEdit = perms.isPrincipalCanEditOvertimeEarnCode(principalId);
            if (canEdit != null) {
                return canEdit;
            }

            Long workArea = timeBlock.getWorkArea();
            WorkArea workAreaObj = HrServiceLocator.getWorkAreaService().getWorkArea(workArea, timeBlock.getEndDateTime().toLocalDate());
            String department = workAreaObj.getDept();
            Department departmentObj = HrServiceLocator.getDepartmentService().getDepartment(department, LocalDate.now());
            String location = departmentObj != null ? departmentObj.getLocation() : null;

            if (StringUtils.equals(workAreaObj.getOvertimeEditRole(), "Employee")) {
            	if(principalId.equals(timeBlock.getPrincipalId())) {
            		return updateCanEditOvtPerm(principalId, perms, true);
            	} 
            } else if (StringUtils.equals(workAreaObj.getOvertimeEditRole(), KPMERole.APPROVER.getRoleName()) ||
                    StringUtils.equals(workAreaObj.getOvertimeEditRole(), KPMERole.APPROVER_DELEGATE.getRoleName())) {
                boolean toReturn = HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.APPROVER_DELEGATE.getRoleName(), workArea, new DateTime())
                        || HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.APPROVER.getRoleName(), workArea, new DateTime());
                return updateCanEditOvtPerm(principalId, perms, toReturn);
            } else if(StringUtils.equals(workAreaObj.getOvertimeEditRole(), KPMERole.PAYROLL_PROCESSOR.getRoleName()) ||
                StringUtils.equals(workAreaObj.getOvertimeEditRole(), KPMERole.PAYROLL_PROCESSOR_DELEGATE.getRoleName())) {
                boolean toReturn = isPrincipalAnyProcessorInWorkArea(principalId, workArea, timeBlock.getBeginDateTime().toLocalDate());
                return updateCanEditOvtPerm(principalId, perms, toReturn);
            } else {
                boolean toReturn = HrServiceLocator.getKPMERoleService().principalHasRoleInDepartment(principalId, KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_DEPARTMENT_ADMINISTRATOR.getRoleName(), department, new DateTime())
                        || HrServiceLocator.getKPMERoleService().principalHasRoleInDepartment(principalId, KPMENamespace.KPME_LM.getNamespaceCode(), KPMERole.LEAVE_DEPARTMENT_ADMINISTRATOR.getRoleName(), department, new DateTime())
                        || HrServiceLocator.getKPMERoleService().principalHasRoleInLocation(principalId, KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_LOCATION_ADMINISTRATOR.getRoleName(), location, new DateTime())
                        || HrServiceLocator.getKPMERoleService().principalHasRoleInLocation(principalId, KPMENamespace.KPME_LM.getNamespaceCode(), KPMERole.LEAVE_LOCATION_ADMINISTRATOR.getRoleName(), location, new DateTime());
                return updateCanEditOvtPerm(principalId, perms, toReturn);
            }
        }
        return false;
    }
    
    /*
     * @see org.kuali.kpme.tklm.time.permissions.TkPermissionsService#canEditRegEarnCode(org.kuali.kpme.tklm.time.timeblock.TimeBlock)
     * this method is used in calendar.tag
     * it's only used when a user is working on its own timesheet, regular earn code cannot be editable on clock entered time block
     */
    @Override
    public boolean canEditRegEarnCode(TimeBlock tb) {
        CalendarBlockPermissions perms = HrServiceLocator.getHRPermissionService().getTimeBlockPermissions(tb.getTkTimeBlockId());
        Boolean canEdit = perms.getCanEditRegEarnCode();
        if (canEdit != null) {
            return canEdit;
        }
    	AssignmentDescriptionKey adk = new AssignmentDescriptionKey(tb.getJobNumber(), tb.getWorkArea(), tb.getTask());
        Assignment anAssignment = HrServiceLocator.getAssignmentService().getAssignmentForTargetPrincipal(adk, tb.getBeginDateTime().toLocalDate());
        if(anAssignment != null) {
        	// use timesheet's end date to get Time Collection Rule
        	TimesheetDocumentHeader tdh = TkServiceLocator.getTimesheetDocumentHeaderService().getDocumentHeader(tb.getDocumentId());
        	DateTime aDate =  tb.getBeginDateTime();
        	if(tdh != null && tdh.getEndDate() != null) {
        		aDate = tdh.getEndDateTime();
        	}
        	
        	if(anAssignment.getJob() != null) {
	        	TimeCollectionRule tcr = TkServiceLocator.getTimeCollectionRuleService().getTimeCollectionRule(anAssignment.getDept(), anAssignment.getWorkArea(), anAssignment.getJob().getHrPayType(), aDate.toLocalDate());
	        	if (tcr == null || tcr.isClockUserFl()) {
	        		// use assignment to get the payType object, then check if the regEarnCode of the paytyep matches the earn code of the timeblock
	        		// if they do match, then return false
	        		PayType pt = HrServiceLocator.getPayTypeService().getPayType(anAssignment.getJob().getHrPayType(), anAssignment.getJob().getEffectiveLocalDate());
	        		if(pt != null && pt.getRegEarnCode().equals(tb.getEarnCode())) {
	                    updateCanRegEarnCd(perms, false);
	        		}
	        	}
        	}
        }
    	return updateCanRegEarnCd(perms, true);
    }

    private Boolean isPrincipalAnyProcessorInWorkArea(String principalId, Long tbWorkArea, LocalDate asOfDate) {

    	Boolean flag = false;
        Set<Long> workAreas = new HashSet<Long>();
    	workAreas.addAll(HrServiceLocator.getKPMERoleService().getWorkAreasForPrincipalInRole(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.PAYROLL_PROCESSOR.getRoleName(), asOfDate.toDateTimeAtStartOfDay(), true));
        workAreas.addAll(HrServiceLocator.getKPMERoleService().getWorkAreasForPrincipalInRole(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.PAYROLL_PROCESSOR_DELEGATE.getRoleName(), asOfDate.toDateTimeAtStartOfDay(), true));

        for (Long wa : workAreas) {
            WorkArea workArea = HrServiceLocator.getWorkAreaService().getWorkArea(wa, asOfDate);
            if (workArea!= null && tbWorkArea.compareTo(wa)==0) {
                flag = true;
                break;
            }
        }

        return flag;
    }

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public TimesheetService getTimesheetService() {
		return timesheetService;
	}

	public void setTimesheetService(TimesheetService timesheetService) {
		this.timesheetService = timesheetService;
	}
}
