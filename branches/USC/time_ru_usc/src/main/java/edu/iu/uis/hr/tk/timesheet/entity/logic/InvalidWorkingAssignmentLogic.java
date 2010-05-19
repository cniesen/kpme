package edu.iu.uis.hr.tk.timesheet.entity.logic;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class InvalidWorkingAssignmentLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(InvalidWorkingAssignmentLogic.class);    
    private static final String INVALID_WORKING_ASSIGNMENT_USER = "The assignment that you are clocked into is no longer valid.  Please contact your supervisor about this problem so that your time can be recorded properly.";
    private static final String INVALID_WORKING_ASSIGNMENT_APPROVER = "The assignment that the employee was clocked into was no longer valid. Please update this employee's timesheet as necessary.";
    private static final String INVALID_WORKING_ASSIGNMENT = "working.assignment.invalid";
    
//    private WebUserService webUserService;    
//    private TimesheetService timesheetService;
    
    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("InvalidWorkingAssignmentLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
        }
        Clock clock = ((TimesheetDocument)entity).getClock();
        
        if (clock.isOnTheClock()) {
            Assignment workingAssignment = ((TimesheetDocument)entity).getAssignment(clock.getClockLog().getEmployeeRecord(), clock.getClockLog().getWorkArea(), clock.getClockLog().getTask(), clock.getClockLog().getClockTime());
            if (!Utility.hasValue(workingAssignment)|| (Utility.hasValue(workingAssignment.getEndDate()) 
           		&& (new TimelessDate(workingAssignment.getEndDate()).compareTo(new TimelessDate(clock.getClockLog().getClockTime().getDate())) < 0))) {            
                if (getWebUserService().getUser().getUniversityId().equals(((TimesheetDocument)entity).getDocumentHeader().getUniversityId())) {                    
                	 entity.getEntityWarnings().add(new String[] {"timesheetDocument"},INVALID_WORKING_ASSIGNMENT_USER);                	 
                } else {
                  	 entity.getEntityWarnings().add(new String[] {"timesheetDocument"},INVALID_WORKING_ASSIGNMENT_APPROVER);
            	}
                StringBuffer noteText = new StringBuffer();
           
                noteText.append(Context.getMessage(INVALID_WORKING_ASSIGNMENT, 
                				new String[] { 
                				clock.getClockLog().getEmployeeRecord().toString(), 
                				clock.getClockLog().getWorkArea().toString(), 
                				clock.getClockLog().getTask().toString(), 
                				clock.getClockLog().getClockTime().toString()}));
                entity.getEntityWarnings().add(new String[] {"timesheetDocument"},noteText.toString());
            }
        }
    }


    public WebUserService getWebUserService() {
        return (WebUserService) Context.getService(WebUserService.class);
	}
	
	public TimesheetService getTimesheetService() {
	        return (TimesheetService) Context.getService(TimesheetService.class);
	}

}
