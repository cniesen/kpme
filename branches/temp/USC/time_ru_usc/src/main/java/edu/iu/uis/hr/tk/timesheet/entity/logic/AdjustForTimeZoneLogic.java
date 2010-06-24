package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.TimeZoneUtility;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public class AdjustForTimeZoneLogic extends AbstractLogic implements OperationalLogic {

	private static final Logger LOG = Logger.getLogger(AdjustForTimeZoneLogic.class);
	private static final String INVALID_ASSIGNMENT_TIME_ZONE_ADJUSTMENT = "invalid.assignment.time.zone.adjustment";

	//    private WebUserService webUserService;
	//    private AssignmentService assignmentService;

	public boolean isMatch() {
		return true;
	}

	public void execute(Entity entity) {
		if (!(entity instanceof Clock)) {
			throw new IllegalArgumentException("RoundClockTimeLogic's execute(Entity entity) method requires a non-null Entity of type Clock");
		}

		if (!Utility.hasValue(((Clock) entity).getClockTime())
				|| (Utility.hasValue(((Clock) entity).getClockLog()) && getWebUserService().getUser().getUniversityId().equals(((Clock) entity).getClockLog().getUniversityId()))) {
			((Clock) entity).setClockTime(new TimedDate().getTimestamp());
		}
		//TIME ZONE ADJUSTMENT
		//May need to address lookup of job if the time zone puts them in a different day 
		//If they clock in around midnight, and they are in a different time zone, 
		//and if it happens that an assingment begins/ends on the date, 
		//May not retrieve the valid assignment until after the time zone conversion,
		//Yet need the assignment to get the time zone from the job. 
		Assignment assignment = this.getAssignmentService().getAssignment(((Clock) entity).getAssignment(), (((Clock) entity).getClockTime().getDate()));
		// https://uisapp2.iu.edu/jira-prd/browse/TK-1081 - fix NPEs in this object
		// instead of checking if the assignment is valid or the employee is on the clock, do them as separate checks
		if (Utility.hasValue(assignment) || Clock.ON_THE_CLOCK_CODES.contains(((Clock) entity).getClockLog().getAction())) {
				//Allow them to clock out/break out/lunch out even if assignment is invalidated
				String location = null;
				if (Utility.hasValue(assignment)) {
					location = assignment.getJob().getLocation();
				} else {
					location = ((Clock) entity).getClockLog().getTimeZone();
				}
				//          If manually entered, still need to set the zone but the time should reflect what the supervisor has entered.
				//          Also may need to allow supervisor to set the zone if they are setting it to the ambiguous 1:30
				if (getWebUserService().getUser().getUniversityId().equals(((Clock) entity).getClockLog().getUniversityId())) {
					entity = TimeZoneUtility.adjustClock((Clock) entity, new java.util.Date().getTime(), location);
				} else {
					entity = TimeZoneUtility.setZone((Clock) entity, location);
				}
		} else {
			//
			Clock clock = ((Clock) entity);
			Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(clock.getAssignment());
			// make sure objects are valid
			if (Utility.hasValue(assignmentKeyMap.containsKey(FieldNames.EMPLOYEE_RECORD)) && Utility.hasValue(assignmentKeyMap.containsKey(FieldNames.WORK_AREA)) && Utility.hasValue(assignmentKeyMap.containsKey(FieldNames.TASK))) {
				StringBuffer noteText = new StringBuffer();
				noteText.append(Context.getMessage(INVALID_ASSIGNMENT_TIME_ZONE_ADJUSTMENT, 
						new Object[] { assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD),
						assignmentKeyMap.get(FieldNames.WORK_AREA), 
						assignmentKeyMap.get(FieldNames.TASK) }));
				entity.getEntityErrors().add(new String[] { "timesheetDocument" }, noteText.toString());
			}
		}
	}

	public WebUserService getWebUserService() {
		return (WebUserService) Context.getService(WebUserService.class);
	}

	//    public void setWebUserService(WebUserService webUserService) {
	//        if (webUserService != null) {
	//            this.webUserService = webUserService;
	//        }
	//    }

	public AssignmentService getAssignmentService() {
		return (AssignmentService) Context.getService(AssignmentService.class);
	}

	//    public void setAssignmentService(AssignmentService assignmentService) {
	//        if (assignmentService != null) {
	//            this.assignmentService = assignmentService;
	//        }
	//    }

}
