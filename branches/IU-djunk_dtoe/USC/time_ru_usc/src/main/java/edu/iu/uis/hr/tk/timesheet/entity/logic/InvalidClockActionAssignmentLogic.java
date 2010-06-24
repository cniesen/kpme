package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public class InvalidClockActionAssignmentLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(InvalidWorkingAssignmentLogic.class);    
    private static final String INVALID_CLOCK_ACTION_ASSIGNMENT = "invalid.clock.action.assignment";
    
//    private WebUserService webUserService;    
//    private AssignmentService assignmentService;
    
    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof Clock)) {
            throw new IllegalArgumentException("InvalidClockActionAssignmentLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
        }
        Clock clock = ((Clock)entity);
        //Map assignmentKeys = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(clock.getClockAssignment());        
        Assignment clockAssignment =  getAssignmentService().getAssignment(clock.getClockAssignment(), clock.getClockTime().getDate());
        if (!Utility.hasValue(clockAssignment)|| (Utility.hasValue(clockAssignment.getEndDate()) && clockAssignment.getEndDate().before(clock.getClockLog().getClockTime().getDate()))) {            
         //   if (getWebUserService().getUser().getUniversityId().equals(((TimesheetDocument)entity).getDocumentHeader().getUniversityId())) {                    
        		 Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(clock.getAssignment());
                 StringBuffer noteText = new StringBuffer();   
                 String emplRcd = assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD) != null ? assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD).toString() : "unknown";
                 String workArea = assignmentKeyMap.get(FieldNames.WORK_AREA) != null ? assignmentKeyMap.get(FieldNames.WORK_AREA).toString() : "unknown";
                 String task = assignmentKeyMap.get(FieldNames.TASK) != null ? assignmentKeyMap.get(FieldNames.TASK).toString() : "unknown";
                 
                 noteText.append(Context.getMessage(INVALID_CLOCK_ACTION_ASSIGNMENT, 
                 				new String[] { 
                		 		emplRcd,
                		 		workArea,
                		 		task
                 }));
                 entity.getEntityErrors().add(new String[] {"timesheetDocument"},noteText.toString());
           // }
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
