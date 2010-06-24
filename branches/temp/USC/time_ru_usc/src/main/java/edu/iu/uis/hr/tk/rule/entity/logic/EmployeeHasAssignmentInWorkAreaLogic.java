package edu.iu.uis.hr.tk.rule.entity.logic;

import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.rule.entity.JobRule;
import edu.iu.uis.hr.tk.rule.entity.WorkAreaRule;

public class EmployeeHasAssignmentInWorkAreaLogic extends AbstractLogic implements OperationalLogic {

//    private AssignmentService assignmentService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof JobRule)) {
            throw new IllegalArgumentException("EmployeeHasAssignmentInWorkAreaLogic's execute(Entity entity) method requires a non-null Entity of type JobRule");
        }
        if ( !edu.iu.uis.hr.entity.logic.Utility.isAllValue(((JobRule) entity).getUniversityId()) ) {
            List assignments = getAssignmentService().getAssignments(((JobRule) entity).getUniversityId());
            boolean validWorkArea = false;
            for (Iterator assignment = assignments.iterator(); assignment.hasNext(); ) {
               if ( ((Assignment)assignment.next()).getWorkArea().equals(((WorkAreaRule) entity).getWorkArea()) ) {
                   validWorkArea = true;
                   break;
               }
            }
            if (!validWorkArea) {
              entity.getEntityErrors().add(new String[] { FieldNames.UNIVERSITY_ID }, getMessage(MessageKeyConstants.ERROR_EMPLOYEE_HAS_NO_ASSIGNMENTS_IN_WORKAREA));
            }
        }
    }

    
    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }
}
