package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.TimeZoneUtility;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class SetTimeZoneLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(SetTimeZoneLogic.class);
    private static final String INVALID_ASSIGNMENT_FOR_TIMEZONE = "invalid.assignment.time.zone";    
//    private AssignmentService assignmentService;

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
            java.util.Date payPeriodBeginDate = null;
            while (hourDetailsItr.hasNext()) {
                HoursDetail hoursDetail = (HoursDetail)hourDetailsItr.next();
                if(payPeriodBeginDate == null){
                	payPeriodBeginDate = hoursDetail.getDate();
                }	
                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
                	//Unless the hour is the 1:00 - 2:00 am that occurs once a year when the time changes. If so - 
                	//force user to set???
                    HourDetail hourDetail = (HourDetail)iter.next();
                    Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
                    java.util.Date assignmentDate = hoursDetail.getDate();
                    Assignment assignment = getAssignmentService().getAssignment(hourDetail.getAssignment(), assignmentDate);
                    if(!Utility.hasValue(assignment)){
                    	//In case the assignment began midway through the pay period
                        assignmentDate = ((TimesheetDocument)entity).getDocumentHeader().getPayEndDate();
                    	assignment = getAssignmentService().getAssignment(hourDetail.getAssignment(), assignmentDate);
                    }
                    if(!Utility.hasValue(assignment)){
                       	//In case the assignment ended midway through the pay period
                    	assignmentDate = payPeriodBeginDate;
                        assignment = getAssignmentService().getAssignment(hourDetail.getAssignment(), assignmentDate);                   	                 	
                    }
                    if(Utility.hasValue(assignment))
                    {
	                	Job job = (getAssignmentService().getAssignment((hourDetail).getAssignment(), assignmentDate)).getJob();
	                    TimedDate tDate = new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getBeginTime());
	                 	String beginTsTz = TimeZoneUtility.getTimeZoneThreeLetterCode(job.getLocation(),tDate.getTimestamp().getDate().getTime());
	                 	hourDetail.setBeginTsTz(beginTsTz);
	                   
	                 	tDate = new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getEndTime());
	                 	String endTsTz = TimeZoneUtility.getTimeZoneThreeLetterCode(job.getLocation(),tDate.getTimestamp().getDate().getTime());
	                 	hourDetail.setEndTsTz(endTsTz);                    
                    }else{//This should be caught on the 
                    	TimesheetDocument timesheet = ((TimesheetDocument)entity);
                        StringBuffer noteText = new StringBuffer();             
                        noteText.append(Context.getMessage(INVALID_ASSIGNMENT_FOR_TIMEZONE, 
                        				new String[] { 
                        		 assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD).toString(), 
                       		  assignmentKeyMap.get(FieldNames.WORK_AREA).toString(), 
                       		  assignmentKeyMap.get(FieldNames.TASK).toString()}));
                        entity.getEntityErrors().add(new String[] {"timesheetDocument"},noteText.toString());           
                    }
                 }
            }
        }
    }
    
    public AssignmentService getAssignmentService() {
    	return (AssignmentService)Context.getService(AssignmentService.class);
    }

}
