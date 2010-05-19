package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.DocumentActionFormMode;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.service.DocumentService;
import edu.iu.uis.hr.tk.directory.service.WebUserService;


/**
 * <p>Title: TimesheetDocumentFormMode</p>
 * <p>Description: Determine whether the document can be editted, saved, approved or not.
 * </p>
 * <p>
 * <ul>
 * <li>Supervisors: Can approve timesheets if they have a pending action request at the supervisor level.</li>
 * <li>Payroll Processors: Can approve timesheets if they have a pending action request at the payroll processor level.</li>
 * <li>System Admins: Have no approval powers.</li>
 * <li>Interface Managers: handle these approval powers through the use of the workflow "superuser" options. 
 * These users could take approve or cancel actions through that interface instead of through the normal action 
 * list interface. This is controlled by them having membership in a "superuser" workgroup for that document. 
 * in workflow.</li>
 * </ul>
 */


public class TimesheetDocumentFormMode extends DocumentActionFormMode{

    private static final String[] APPROVAL_ROUTE_NODE_NAMES = {new String("PayrollProcessor"), new String("Supervisor")};
    
    public TimesheetDocumentFormMode() {
        super();
    }

    public TimesheetDocumentFormMode(boolean editable, ModeApplicable modeApplicable) {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
        if (isEditable()) {
            
            //user is doing Hour Distribution
            if (((TimesheetDocumentForm)getModeApplicable()).getTimesheetDocument().getClock().isUserDoingHourDistribution()){
                return new StringBuffer(TimesheetDocumentAction.HOUR_DISTRIBUTION_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(TimesheetDocumentAction.HOUR_DISTRIBUTION_CANCEL_DISPATCH_PARAM_VALUE).toString();
            }
            
            if (DocumentConstants.SAVED.equals(((TimesheetDocumentForm)getModeApplicable()).getTimesheetDocument().getDocumentHeader().getDocumentStatus())) {
                return new StringBuffer(StrutsDispatchParamConstants.SAVE_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(TimesheetDocumentAction.UNLOCK_DOCUMENT_DISPATCH_PARAM_VALUE).toString();
            } else {
                String save = new StringBuffer(StrutsDispatchParamConstants.SAVE_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(TimesheetDocumentAction.UNLOCK_DOCUMENT_DISPATCH_PARAM_VALUE).toString(); 
                String approval = new StringBuffer(StrutsDispatchParamConstants.SAVE_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(StrutsDispatchParamConstants.APPROVE_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(TimesheetDocumentAction.UNLOCK_DOCUMENT_DISPATCH_PARAM_VALUE).toString(); 
                WebUserService webUserService = (WebUserService) Context.getService(WebUserService.class);
                /*
                 * TODO need a way to interface mgrs to approve - rpiercy
                User user = (User)webUserService.getUser();
                if (user.isInterfaceManager()) {
                    return approval;
                }
                */
                DocumentService documentService = (DocumentService) Context.getService(DocumentService.class);
                for(int i = 0; i < APPROVAL_ROUTE_NODE_NAMES.length; i++) {
                    if (documentService.userHasApprovalRequestAtNode(((TimesheetDocumentForm)getModeApplicable()).getTimesheetDocument().getDocumentHeader().getDocumentId(), webUserService.getUser().getNetworkId(), APPROVAL_ROUTE_NODE_NAMES[i])) {
                        return approval;
                    }
                }
                return save;
            }
        }
        return super.getEventNames();
    }
}
