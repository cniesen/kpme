package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class InvalidHoursLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(InvalidHoursLogic.class);
    private static final String INVALID_HOURS_USER = "One or more of your recorded Hour Details may be affected because the assignment associated with the hours was no longer valid.  Please contact your supervisor about this problem so that your time can be recorded properly.";
    private static final String INVALID_HOURS_APPROVER = "One or more recorded Hour Details from this employee's timesheet may be affected because the assignment associated with the hours was no longer valid. Please update this employee's timesheet as necessary.";
    private static final String INVALID_TIME_BLOCKS = "timesheet.time.block.hours.invalid";
//    private AssignmentService assignmentService;
//    private WebUserService webUserService;
//    private TimesheetService timesheetService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("InvalidHoursLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
        }

        TypedPersistentMaintainedEntityList hourDetails = ((TimesheetDocument)entity).getHours().getHoursDetails();
        if (Utility.hasValue(hourDetails)) {
            Iterator hourDetailsItr = hourDetails.iterator();
            while (hourDetailsItr.hasNext()) {
                HoursDetail hoursDetail = (HoursDetail)hourDetailsItr.next();

                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
                    HourDetail hourDetail = (HourDetail)iter.next();
                    if (Utility.hasValue(hourDetail.getAssignment())) { //handling invalid assignments
                        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
                        Timestamp timeblockDate = new Timestamp();
                        timeblockDate.setDate(hoursDetail.getDate());
                        Assignment workingAssignment = ((TimesheetDocument)entity).getAssignment((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), (BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), (BigDecimal)assignmentKeyMap.get(FieldNames.TASK), timeblockDate);
                        if (!Utility.hasValue(workingAssignment) || (Utility.hasValue(workingAssignment.getEndDate()) && workingAssignment.getEndDate().before(hoursDetail.getDate()))) {
                            StringBuffer noteText = new StringBuffer();

                          noteText.append(Context.getMessage(INVALID_TIME_BLOCKS, 
                          				new String[] { 
                        		  assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD).toString(), 
                        		  assignmentKeyMap.get(FieldNames.WORK_AREA).toString(), 
                        		  assignmentKeyMap.get(FieldNames.TASK).toString()}));

                            entity.getEntityWarnings().add(new String[] { Assignment.ASSIGNMENT }, noteText.toString());
                            hourDetail.getEntityWarnings().add(new String[] { Assignment.ASSIGNMENT }, noteText.toString());
                            hoursDetail.setTabStatus("true");
                        }
                    }
                }
            }
        }
    }

    public WebUserService getWebUserService() {
        return (WebUserService) Context.getService(WebUserService.class);
	}
	
	public TimesheetService getTimesheetService() {
	        return (TimesheetService) Context.getService(TimesheetService.class);
	}
	
	public AssignmentService getAssignmentService() {
	        return (AssignmentService) Context.getService(AssignmentService.class);
	}

}
