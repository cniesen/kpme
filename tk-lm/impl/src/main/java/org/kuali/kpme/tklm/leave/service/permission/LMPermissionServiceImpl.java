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
package org.kuali.kpme.tklm.leave.service.permission;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.KPMENamespace;
import org.kuali.kpme.core.block.CalendarBlockPermissions;
import org.kuali.kpme.core.calendar.entry.CalendarEntry;
import org.kuali.kpme.core.department.Department;
import org.kuali.kpme.core.job.Job;
import org.kuali.kpme.core.role.KPMERole;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.service.permission.HrPermissionServiceBase;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.kpme.core.util.HrContext;
import org.kuali.kpme.tklm.common.LMConstants;
import org.kuali.kpme.tklm.leave.block.LeaveBlock;
import org.kuali.kpme.tklm.leave.calendar.service.LeaveCalendarService;
import org.kuali.kpme.tklm.leave.request.service.LeaveRequestDocumentService;
import org.kuali.kpme.tklm.leave.service.LmServiceLocator;
import org.kuali.kpme.tklm.leave.timeoff.SystemScheduledTimeOff;
import org.kuali.kpme.tklm.leave.workflow.LeaveRequestDocument;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;
import org.kuali.kpme.tklm.time.workflow.TimesheetDocumentHeader;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.api.action.ValidActions;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.util.KRADConstants;

public class LMPermissionServiceImpl extends HrPermissionServiceBase implements LMPermissionService {
	
	private PermissionService permissionService;
	private LeaveCalendarService leaveCalendarService;
	private LeaveRequestDocumentService leaveRequestDocumentService;
	
	@Override
	public boolean isAuthorized(String principalId, String permissionName, DateTime asOfDate) {
		Map<String, String> qualification = new HashMap<String, String>();
		
		return getPermissionService().isAuthorized(principalId, KPMENamespace.KPME_LM.getNamespaceCode(), permissionName, qualification);
	}
	
	@Override
	public boolean isAuthorized(String principalId, String permissionName, Map<String, String> qualification, DateTime asOfDate) {
		return getPermissionService().isAuthorized(principalId, KPMENamespace.KPME_LM.getNamespaceCode(), permissionName, qualification);
	}
    
    @Override
    public boolean canViewLeaveRequest(String principalId, String documentId) {
    	return canSuperUserAdministerLeaveRequest(principalId, documentId) 
    			|| isAuthorizedByTemplate(principalId, KRADConstants.KNS_NAMESPACE, KimConstants.PermissionTemplateNames.OPEN_DOCUMENT, documentId);
    }
    
    @Override
    public boolean canEditLeaveRequest(String principalId, String documentId) {
    	return canSuperUserAdministerLeaveRequest(principalId, documentId) 
    			|| isAuthorizedByTemplate(principalId, KRADConstants.KNS_NAMESPACE, KimConstants.PermissionTemplateNames.EDIT_DOCUMENT, documentId);
    }
    
    @Override
    public boolean canSubmitLeaveRequest(String principalId, String documentId) {
        return canSuperUserAdministerLeaveRequest(principalId, documentId) 
        		|| isAuthorizedByTemplate(principalId, KRADConstants.KUALI_RICE_WORKFLOW_NAMESPACE, KimConstants.PermissionTemplateNames.ROUTE_DOCUMENT, documentId);
    }
    
    @Override
    public boolean canApproveLeaveRequest(String principalId, String documentId) {
    	boolean canApproveLeaveRequest = false;
    	
    	ValidActions validActions = KewApiServiceLocator.getWorkflowDocumentActionsService().determineValidActions(documentId, principalId);
    	
    	if (validActions.getValidActions() != null) {
    		canApproveLeaveRequest = validActions.getValidActions().contains(ActionType.APPROVE);
    	}
    	
    	return canApproveLeaveRequest;
    }
    
    @Override
    public boolean canSuperUserAdministerLeaveRequest(String principalId, String documentId) {
        return isAuthorizedByTemplate(principalId, KRADConstants.KUALI_RICE_WORKFLOW_NAMESPACE, "Administer Routing for Document", documentId);
    }
    
    private boolean isAuthorizedByTemplate(String principalId, String namespaceCode, String permissionTemplateName, String documentId) {
    	boolean isAuthorizedByTemplate = false;

/*    	LeaveRequestDocument lrd = LmServiceLocator.getLeaveRequestDocumentService().getLeaveRequestDocument(documentId);
    	
    	if (lrd != null) {
    		String documentTypeName = LeaveRequestDocument.LEAVE_REQUEST_DOCUMENT_TYPE;
        	DocumentStatus documentStatus = DocumentStatus.fromCode(lrd.getActionCode());
        	
        	isAuthorizedByTemplate = isAuthorizedByTemplate(principalId, namespaceCode, permissionTemplateName, documentTypeName, documentId, documentStatus);
    	}*/
    	
    	return isAuthorizedByTemplate;
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

    private boolean updateCanEditLeavePerm(String principalId, CalendarBlockPermissions perms, boolean canEditAll) {
        perms.putCanEdit(principalId, canEditAll);
        HrServiceLocator.getHRPermissionService().updateLeaveBlockPermissions(perms);
        return canEditAll;
    }

    @Override
    public boolean canEditLeaveBlock(String principalId, LeaveBlock leaveBlock) {
        if (principalId != null) {
            CalendarBlockPermissions perms = HrServiceLocator.getHRPermissionService().getLeaveBlockPermissions(leaveBlock.getLmLeaveBlockId());
            Boolean canEdit = perms.isPrincipalCanEdit(principalId);

            if (canEdit != null) {
                return canEdit;
            }
        	String documentId = leaveBlock.getDocumentId();
        	if (StringUtils.isBlank(documentId)) {
        		TimesheetDocumentHeader timesheetDocumentHeader = TkServiceLocator.getTimesheetDocumentHeaderService().getDocumentHeaderForDate(principalId, leaveBlock.getLeaveLocalDate().toDateTimeAtStartOfDay());
        		if (timesheetDocumentHeader != null) {
        			documentId = timesheetDocumentHeader.getDocumentId();
        		}
        	}
        	if (StringUtils.isNotBlank(documentId)) {
        		DocumentStatus documentStatus = KewApiServiceLocator.getWorkflowDocumentService().getDocumentStatus(documentId);
        		if (DocumentStatus.CANCELED.equals(documentStatus) || DocumentStatus.DISAPPROVED.equals(documentStatus)) {
                    return updateCanEditLeavePerm(principalId, perms, false);
        		}
        	}
	 	 	 	
            String blockType = leaveBlock.getLeaveBlockType();
            String requestStatus = leaveBlock.getRequestStatus();
            if (StringUtils.equals(HrConstants.REQUEST_STATUS.DISAPPROVED, requestStatus)) {
                return updateCanEditLeavePerm(principalId, perms, false);
            }
            if (StringUtils.equals(HrConstants.REQUEST_STATUS.APPROVED, requestStatus)) {
            	List<LeaveRequestDocument> docList= LmServiceLocator.getLeaveRequestDocumentService().getLeaveRequestDocumentsByLeaveBlockId(leaveBlock.getLmLeaveBlockId());
            	if(CollectionUtils.isEmpty(docList)) {
            		// not a leave request. if this is a leave request, do further checking on it
                    return updateCanEditLeavePerm(principalId, perms, false);
            	}            	
            }
            if (StringUtils.isBlank(blockType)
                    || StringUtils.equals(LMConstants.LEAVE_BLOCK_TYPE.LEAVE_CALENDAR, blockType)
                    || StringUtils.equals(LMConstants.LEAVE_BLOCK_TYPE.TIME_CALENDAR, blockType)) {

//            	if (!TkContext.isDepartmentAdmin()
//                        || HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.APPROVER.getRoleName(), leaveBlock.getWorkArea(), new DateTime())) {
            	if(this.userHasRolesToEditLeaveBlock(principalId, leaveBlock)) {
                    return updateCanEditLeavePerm(principalId, perms, true);
            	}
            } else if (LMConstants.LEAVE_BLOCK_TYPE.LEAVE_PAYOUT.equals(blockType)
                    || LMConstants.LEAVE_BLOCK_TYPE.DONATION_MAINT.equals(blockType)
                    || LMConstants.LEAVE_BLOCK_TYPE.BALANCE_TRANSFER.equals(blockType)
                    || LMConstants.LEAVE_BLOCK_TYPE.LEAVE_ADJUSTMENT_MAINT.equals(blockType)) {
                if (HrContext.isSystemAdmin()) {
                    return updateCanEditLeavePerm(principalId, perms, true);
                }
            }
            // kpme-1689
            if(StringUtils.equals(LMConstants.LEAVE_BLOCK_TYPE.ACCRUAL_SERVICE, blockType)
            		&& StringUtils.isNotEmpty(leaveBlock.getScheduleTimeOffId())
            		&& leaveBlock.getLeaveAmount().compareTo(BigDecimal.ZERO) == -1) {
            	if(HrContext.isSystemAdmin()) {
                    return updateCanEditLeavePerm(principalId, perms, true);
            	}
            	SystemScheduledTimeOff ssto = LmServiceLocator.getSysSchTimeOffService().getSystemScheduledTimeOff(leaveBlock.getScheduleTimeOffId());
            	if(ssto != null && !StringUtils.equals(LMConstants.UNUSED_TIME.NO_UNUSED, ssto.getUnusedTime())) {
                    return updateCanEditLeavePerm(principalId, perms, true);
            	}
            }
        }

        return false;
    }
    
    @Override
    public boolean userHasRolesToEditLeaveBlock(String principalId, LeaveBlock aLeaveBlock) {
    	// Location and sys admins along with approver,reviewer, payroll processors should have access to edit calendar
    	// department admins and view only should not have access to edit timesheets. view only roles are location view only and global view only
    	// location and sys admin roles should have priority unless it is their own calendar.
      
	    // use if blocks to check the roles in priority order and returns true so we don't need to check all possible roles for performance purpose 
    	// system admin
	    if(HrServiceLocator.getKPMEGroupService().isMemberOfSystemAdministratorGroup(principalId, DateTime.now()))
	    	return true;
	   // LeaveSysAdmin
	    if(HrServiceLocator.getKPMERoleService().principalHasRole(principalId, KPMENamespace.KPME_LM.getNamespaceCode(), KPMERole.LEAVE_SYSTEM_ADMINISTRATOR.getRoleName(), new DateTime()))
	    	return true;
	    
	    // use job to find the department, then use the location from Department to get the location roles 
	    Job aJob = HrServiceLocator.getJobService().getJob(aLeaveBlock.getPrincipalId(), aLeaveBlock.getJobNumber(), aLeaveBlock.getLeaveLocalDate());
	    if(aJob != null) {
	    	Department aDept = HrServiceLocator.getDepartmentService().getDepartment(aJob.getDept(), aJob.getEffectiveLocalDate());
	    	if(aDept != null) {
	    		// LeaveLocationAdmin
			    if(HrServiceLocator.getKPMERoleService().principalHasRoleInLocation(principalId, KPMENamespace.KPME_LM.getNamespaceCode(), KPMERole.LEAVE_LOCATION_ADMINISTRATOR.getRoleName(), aDept.getLocation(), new DateTime()))
			    	return true;
	    	}
	    }	    
    	Long aWorkArea = aLeaveBlock.getWorkArea();
	    // Reviewer
	    if(HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.REVIEWER.getRoleName(), aWorkArea, new DateTime()))
	    	return true;
	    // Approver
	    if(HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.APPROVER.getRoleName(), aWorkArea, new DateTime()))
	    	return true;
	    // Approver Delegate
	    if(HrServiceLocator.getKPMERoleService().principalHasRoleInWorkArea(principalId, KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.APPROVER_DELEGATE.getRoleName(), aWorkArea, new DateTime()))
	    	return true;
	    
	    // no eligible roles found
	    return false;
	 
    }

    private boolean updateCanDeleteLeaveblockPerm(String principalId, CalendarBlockPermissions perms, boolean canDelete) {
        perms.putCanDelete(principalId, canDelete);
        HrServiceLocator.getHRPermissionService().updateLeaveBlockPermissions(perms);
        return canDelete;
    }

    @Override
    public boolean canDeleteLeaveBlock(String principalId, LeaveBlock leaveBlock) {
        if (principalId != null) {
            CalendarBlockPermissions perms = HrServiceLocator.getHRPermissionService().getLeaveBlockPermissions(leaveBlock.getLmLeaveBlockId());
            Boolean canDelete = perms.isPrincipalCanDelete(principalId);

            if (canDelete != null) {
                return canDelete;
            }
        	String documentId = leaveBlock.getDocumentId();
        	if (StringUtils.isBlank(documentId)) {
        		TimesheetDocumentHeader timesheetDocumentHeader = TkServiceLocator.getTimesheetDocumentHeaderService().getDocumentHeaderForDate(principalId, leaveBlock.getLeaveLocalDate().toDateTimeAtStartOfDay());
        		if (timesheetDocumentHeader != null) {
        			documentId = timesheetDocumentHeader.getDocumentId();
        		}
        	}
        	if (StringUtils.isNotBlank(documentId)) {
        		DocumentStatus documentStatus = KewApiServiceLocator.getWorkflowDocumentService().getDocumentStatus(documentId);
        		if (DocumentStatus.CANCELED.equals(documentStatus) || DocumentStatus.DISAPPROVED.equals(documentStatus)) {
                    return updateCanDeleteLeaveblockPerm(principalId, perms, false);
        		}
        	}
        }
    	
    	if(StringUtils.equals(HrConstants.REQUEST_STATUS.DISAPPROVED, leaveBlock.getRequestStatus()))  {
            return false;
        }
    	if(canBankOrTransferSSTOUsage(leaveBlock)) {
            return true;
    	}
        if (StringUtils.equals(HrConstants.REQUEST_STATUS.APPROVED, leaveBlock.getRequestStatus())) {
        	List<LeaveRequestDocument> docList= LmServiceLocator.getLeaveRequestDocumentService().getLeaveRequestDocumentsByLeaveBlockId(leaveBlock.getLmLeaveBlockId());
        	if(CollectionUtils.isEmpty(docList)) {
        		return false;	// not a leave request
        	}
        }
       
        return canEditLeaveBlock(principalId, leaveBlock);
    }
    
    @Override
	public boolean canBankOrTransferSSTOUsage(LeaveBlock lb) {
		// if it's an accrual generated ssto usage leave block which can be banked or transferred, and on a current leave calendar,
	    // it can be deleted so the accrualed amount can be banked
	    return canBankSSTOUsage(lb) || canTransferSSTOUsage(lb);
	}
    
    @Override
	public boolean canBankSSTOUsage(LeaveBlock lb) {
 	   if(lb.getAccrualGenerated() 
			   && StringUtils.isNotEmpty(lb.getScheduleTimeOffId()) 
			   && lb.getLeaveAmount().compareTo(BigDecimal.ZERO) < 0) {
		   SystemScheduledTimeOff ssto = LmServiceLocator.getSysSchTimeOffService().getSystemScheduledTimeOff(lb.getScheduleTimeOffId());
		   if(ssto != null && StringUtils.equals(ssto.getUnusedTime(), LMConstants.UNUSED_TIME.BANK)) {
			   String viewPrincipal = HrContext.getTargetPrincipalId();
			   CalendarEntry ce = HrServiceLocator.getCalendarEntryService()
						.getCurrentCalendarDatesForLeaveCalendar(viewPrincipal, new LocalDate().toDateTimeAtStartOfDay());
			   if(ce != null) {
				   if(!lb.getLeaveDate().before(ce.getBeginPeriodDate()) && !lb.getLeaveDate().after(ce.getEndPeriodDate())) {
					   return true;
				   }
			   }
			  
		   }
	   }
	   return false;
	}
    
    @Override
	public boolean canTransferSSTOUsage(LeaveBlock lb) {
	   if(lb.getAccrualGenerated() 
			   && StringUtils.isNotEmpty(lb.getScheduleTimeOffId()) 
			   && lb.getLeaveAmount().compareTo(BigDecimal.ZERO) < 0) {
		   SystemScheduledTimeOff ssto = LmServiceLocator.getSysSchTimeOffService().getSystemScheduledTimeOff(lb.getScheduleTimeOffId());
		   if(ssto != null && LMConstants.UNUSED_TIME.TRANSFER.equals(ssto.getUnusedTime())) {
			   String viewPrincipal = HrContext.getTargetPrincipalId();
			   CalendarEntry ce = HrServiceLocator.getCalendarEntryService()
						.getCurrentCalendarDatesForLeaveCalendar(viewPrincipal, new LocalDate().toDateTimeAtStartOfDay());
			   if(ce != null) {
				   if(!lb.getLeaveDate().before(ce.getBeginPeriodDate()) && !lb.getLeaveDate().after(ce.getEndPeriodDate())) {
					   return true;
				   }
			   }
			  
		   }
	   }
	   return false;
	}
	
	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public LeaveCalendarService getLeaveCalendarService() {
		return leaveCalendarService;
	}

	public void setLeaveCalendarService(LeaveCalendarService leaveCalendarService) {
		this.leaveCalendarService = leaveCalendarService;
	}
	
	public LeaveRequestDocumentService getLeaveRequestDocumentService() {
		return leaveRequestDocumentService;
	}

	public void setLeaveRequestDocumentService(LeaveRequestDocumentService leaveRequestDocumentService) {
		this.leaveRequestDocumentService = leaveRequestDocumentService;
	}
	
}
