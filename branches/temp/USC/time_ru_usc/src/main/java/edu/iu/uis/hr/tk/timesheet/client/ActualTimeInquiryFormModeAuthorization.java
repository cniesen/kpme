package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.SearchActionFormModeAuthorization;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.timesheet.entity.ActualTimeSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class ActualTimeInquiryFormModeAuthorization extends SearchActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
	        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof ActualTimeInquiryForm)) {
	            throw new IllegalArgumentException("ActualTimeInquiryFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable ActualTimeInquiryForm object.");
	        }
	        
	        modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
	        String documentId = ((ActualTimeSearchCriteria)((ActualTimeInquiryForm)modeApplicable).getSearchCriteria()).getDocumentId();
	        
	        if (!Utility.hasValue(documentId)){
	        	return;
	        }
	    	edu.iu.uis.hr.tk.directory.entity.User user = (edu.iu.uis.hr.tk.directory.entity.User)getWebUserService().getUser();
	        String userUniversityId = user.getUniversityId();
	       TimesheetDocument timesheetDocument = getTimesheetService().getTimesheetDocument(documentId);
	       String documentUniversityId = timesheetDocument.getDocumentHeader().getUniversityId();
	       boolean userIsEmployee = userUniversityId.equals(documentUniversityId);
	       boolean userIsPayrollProcessor = user.hasJurisdictionOver(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE, timesheetDocument.getJobs());
	       boolean userIsSupervisor = userIsPayrollProcessor || user.hasJurisdictionOver(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE, timesheetDocument.getJobs());
	       boolean userIsReviewer =  userIsSupervisor || user.hasJurisdictionOver(edu.iu.uis.hr.tk.directory.Utility.REVIEWER_ROLE, timesheetDocument.getJobs());
	       boolean userIsViewOnly = user.hasJurisdictionOver(edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE,timesheetDocument.getJobs());
	       boolean userIsInterfaceManager = user.isInterfaceManager();
	       if ( !(userIsInterfaceManager || userIsEmployee ||userIsPayrollProcessor || userIsSupervisor || userIsReviewer || userIsReviewer)) {
	       	   throw new IllegalArgumentException("You are not authorized to view this document.  Please submit the Incident Report Form if you think you should be able to access this document.");
	       } 
    }

    public TimesheetService getTimesheetService() {
        return (TimesheetService) Context.getService(TimesheetService.class);
    }

}
