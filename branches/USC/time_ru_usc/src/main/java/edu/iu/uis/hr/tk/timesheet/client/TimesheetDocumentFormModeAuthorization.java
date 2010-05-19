package edu.iu.uis.hr.tk.timesheet.client;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.kew.dto.EmplIdDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.WorkflowDocument;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ExceptionAdapter;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.DocumentActionFormModeAuthorization;
import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.service.DocumentService;
import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.log.LogPerformanceMethod;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLock;
import edu.iu.uis.hr.tk.timesheet.entity.logic.MessageKeyConstants;
import edu.iu.uis.hr.tk.timesheet.service.ActiveAssignmentNotFoundException;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

/**
 * <p>Title: TimesheetDocumentFormModeAuthorization</p>
 * <p>Description: Determine whether the document is in edit or view mode.  Also check if the document
 * should be editable according to the user's role.
 * </p>
 * <p>
 * <ul>
 * <li>Supervisors, Reviewers and Payroll Processors should able to open and view any timesheet for an
 * employee that has an assignment in a work area they are assigned to. They should only be able to 
 * edit shifts on the timesheet that are associated with the work areas they have these roles for.</li>
 * <li>Payroll Managers should have view access to any timesheets that have assignments associated with 
 * their department.</li>
 * <li>System Admins should have view (and edit access if possible) for any timesheets that have assignments 
 * associated with the campus they have the System Admin role for.</li>
 * <li>Interface managers should have view and edit access to all timesheets.</li>
 * </ul>
 * </p>
 */

public class TimesheetDocumentFormModeAuthorization extends DocumentActionFormModeAuthorization {
    private static final Logger LOG = Logger.getLogger(TimesheetDocumentFormModeAuthorization.class);
    private static final String SUPERVISOR_NODE_NAME = "Supervisor";
    private static final String PAYROLL_PROCESSOR_NODE_NAME = "PayrollProcessor";

    private static final Logger LOG_PERFORM = Logger.getLogger(LogPerformanceMethod.class);
    
    
    public void execute(ModeApplicable modeApplicable) {
    	
    	Date startTime = new Date();
    	
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof TimesheetDocumentForm)) {
            throw new IllegalArgumentException("TimesheetDocumentFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable TimesheetDocumentForm object.");
        }
        TimesheetDocumentForm timesheetDocumentForm = ((TimesheetDocumentForm)modeApplicable);
        User user = (User)getWebUserService().getUser();

        //allowing edition whenever hours need to be distributed
        if (timesheetDocumentForm.getTimesheetDocument().getClock().isUserDoingHourDistribution()){
            modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
            return;
        }        

        timesheetDocumentForm.getTimesheetDocument().checkLeaveStatus();
        
        DocumentLock lock = timesheetDocumentForm.getTimesheetDocument().getDocumentHeader().getDocumentLock();
        if (Utility.hasValue(lock) && Utility.hasValue(lock.getUserUniversityId())) { 
            if ( user.getUniversityId().equals(lock.getUserUniversityId())) {
                modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
            } else {
                modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
                timesheetDocumentForm.getTimesheetDocument().getEntityWarnings().add(new String[] { "timesheetDocument" }, Context.getMessage(MessageKeyConstants.WARNING_DOCUMENT_ALREADY_LOCKED));
                timesheetDocumentForm.setEditAllowed(false);
                return;
            }
        } else {
            modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
        }

        try {
            String userUniversityId = user.getUniversityId();
            String documentUniversityId = timesheetDocumentForm.getTimesheetDocument().getDocumentHeader().getUniversityId();
            boolean userIsEmployee = userUniversityId.equals(documentUniversityId);
            
        	if (timesheetDocumentForm.getTimesheetDocument().getJobs().size() == 0){
       		 	throw new ActiveAssignmentNotFoundException("Employee " + documentUniversityId + " has no active TIME assignments for this pay period.", LOG);
        	}
            
            if (userIsEmployee) {
            	//TODO: Revisit this logic, Hourly employees shouldn't get the Edit button (tk-631)
            	 //making sure there are active jobs/assignments
            	//allowing timesheet's owner to edit his/her own timesheet if document status is saved
            	if ( StringUtils.equals(timesheetDocumentForm.getTimesheetDocument().getDocumentHeader().getDocumentStatus(),DocumentConstants.SAVED)){
            		   timesheetDocumentForm.setEditAllowed(true);
                  	   return;
            	   }
            }
            
            WorkflowDocument workflowDocument = new WorkflowDocument(new EmplIdDTO(userUniversityId), new Long(timesheetDocumentForm.getTimesheetDocument().getDocumentHeader().getDocumentId()));
            String[] nodeNames = workflowDocument.getNodeNames();
            
            boolean userIsPayrollProcessor = user.hasJurisdictionOver(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE, timesheetDocumentForm.getTimesheetDocument().getJobs());
            boolean userIsSupervisor = userIsPayrollProcessor || user.hasJurisdictionOver(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE, timesheetDocumentForm.getTimesheetDocument().getJobs());
            boolean userIsReviewer =  userIsSupervisor || user.hasJurisdictionOver(edu.iu.uis.hr.tk.directory.Utility.REVIEWER_ROLE, timesheetDocumentForm.getTimesheetDocument().getJobs());
            boolean userIsViewOnly = user.hasJurisdictionOver(edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE,timesheetDocumentForm.getTimesheetDocument().getJobs());

            if ((workflowDocument.stateIsSaved() || workflowDocument.stateIsInitiated()) && (userIsEmployee || userIsSupervisor)) {
                timesheetDocumentForm.setEditAllowed(true);
            } else if( !(workflowDocument.stateIsSaved() || workflowDocument.stateIsInitiated()) && userIsEmployee) {
                timesheetDocumentForm.setEditAllowed(false);
            } else  if ((isAtSupervisorNode(nodeNames) && userIsSupervisor)) {
                timesheetDocumentForm.setEditAllowed(true);
            } else if (isAtPayrollProcessorNode(nodeNames)) {
                // reviewers should not edit past sup approval node
                if (userIsPayrollProcessor) {
                    timesheetDocumentForm.setEditAllowed(true);
                } else if (userIsReviewer || userIsViewOnly) {
                    timesheetDocumentForm.setEditAllowed(false);
                } else {
                    throw new IllegalArgumentException("You are not authorized to view this document.  Please submit the Incident Report Form if you think you should be able to access this document.");
                }
            } else if (workflowDocument.stateIsException() && user.isInterfaceManager()) {
                timesheetDocumentForm.setEditAllowed(true);
            } else if (!workflowDocument.stateIsFinal() && userIsReviewer) {
                timesheetDocumentForm.setEditAllowed(true);
            } else if (workflowDocument.stateIsFinal() && userIsReviewer) {
                timesheetDocumentForm.setEditAllowed(false);
            } else if (userIsViewOnly){
            	timesheetDocumentForm.setEditAllowed(false);
            } else {
                throw new IllegalArgumentException("You are not authorized to view this document.  Please submit the Incident Report Form if you think you should be able to access this document.");
            }
            
            if ( (userIsReviewer || userIsViewOnly) && ! userIsEmployee || (workflowDocument.isApprovalRequested() && !workflowDocument.stateIsSaved())) {
            	timesheetDocumentForm.setIgnoreClockOnlyStatus(true);
            }

        } catch (NumberFormatException e) {
        	LOG.error("*******NumberFormatException in TimesheetDocumentFormModeAuthorization ******");
            throw new ExceptionAdapter(e, getLogger());
        } catch (WorkflowException e) {
        	LOG.error("*******WorkflowException in TimesheetDocumentFormModeAuthorization ******");
            throw new IllegalArgumentException("WorkflowException in TimesheetDocumentFormModeAuthorization " + e);
            //throw new ExceptionAdapter(e, getLogger());
        }
        
        LOG_PERFORM.error("--- TimesheetDocumentFormModeAuthorization Time: " + (System.currentTimeMillis() - startTime.getTime()) + "ms ----");
    }

    private boolean employeeCanEditTimesheet() {
        // TODO : implement this
        // this doc is already batch approved ?
        return true;
    }

    private Logger getLogger() {
        return LOG;
    }

    // kind of duplication from customactionlist
    private boolean isAtSupervisorNode(String[] nodeNames) {
        return (nodeNames.length > 0 && nodeNames[0].equals(SUPERVISOR_NODE_NAME));

    }

    private boolean isAtPayrollProcessorNode(String[] nodeNames) {
        return (nodeNames.length > 0 && nodeNames[0].equals(PAYROLL_PROCESSOR_NODE_NAME));

    }
    protected DocumentService getDocumentService() {
    	return (DocumentService) Context.getService(TimesheetService.class);
    }
    
    
}
