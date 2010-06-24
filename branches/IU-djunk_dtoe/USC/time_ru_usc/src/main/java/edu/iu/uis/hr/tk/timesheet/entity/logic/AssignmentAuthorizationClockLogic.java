package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;
import java.util.Map;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;


public class AssignmentAuthorizationClockLogic extends AbstractAssignmentAuthorizationLogic{

    public void execute(Entity entity) {
        if (!(entity instanceof Clock)) {
            throw new IllegalArgumentException("AssignmentAuthorizationClockLogic's execute(Entity entity) method requires a non-null Entity of type Clock");
        }
        
        Clock clock = ((Clock)entity);
        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(clock.getAssignment());
        

        User user = (User)getWebUserService().getUser();

        boolean isEmployeeAction = clock.getClockLog().getUniversityId().equals(user.getUniversityId());
        
        if(isEmployeeAction) {
            return;
        } else if (!hasAuthorization(user, assignmentKeyMap, clock)) {
            entity.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.ERROR_INVALID_CLOCK_ASSIGNMENT));
        }
    }
    
    private boolean hasAuthorization(User user, Map assignmentKeyMap, Clock clock) {
        WorkArea workArea = getAssignmentService().getCurrentWorkArea(new BigDecimal(assignmentKeyMap.get(FieldNames.WORK_AREA).toString()), clock.getClockTime().getDate());
        Job job = getJobService().getJob(assignmentKeyMap.get(FieldNames.UNIVERSITY_ID).toString(), new BigDecimal(assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD).toString()), clock.getClockTime().getDate());
        return user.hasJurisdictionOver(workArea, job);
    }
    
}