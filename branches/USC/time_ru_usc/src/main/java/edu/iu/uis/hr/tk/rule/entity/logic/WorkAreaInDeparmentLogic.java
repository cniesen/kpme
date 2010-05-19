package edu.iu.uis.hr.tk.rule.entity.logic;

import java.util.Date;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.MessageKeyConstants;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.rule.entity.WorkAreaRule;

public class WorkAreaInDeparmentLogic extends AbstractLogic implements OperationalLogic {

//    private AssignmentService assignmentService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof WorkAreaRule)) {
            throw new IllegalArgumentException("WorkAreaInDeparmentLogic's execute(Entity entity) method requires a non-null Entity of type WorkAreaRule");
        }
        WorkArea workArea = getAssignmentService().getWorkArea(((WorkAreaRule) entity).getWorkArea(), new Date());
        if (!edu.iu.uis.hr.entity.logic.Utility.isAllValue(((WorkAreaRule) entity).getWorkArea()) && (!edu.iu.uis.hr.entity.logic.Utility.hasValue(workArea) || !workArea.getDepartment().equals(((WorkAreaRule) entity).getDepartment()))) {
            entity.getEntityErrors().add(new String[] { FieldNames.WORK_AREA }, getMessage(MessageKeyConstants.ERROR_INVALID_VALUE, new String[] { getLabel(FieldNames.WORK_AREA) }));
        }
    }
    
    private AssignmentService getAssignmentService() {
        return (AssignmentService)Context.getService(AssignmentService.class);
    }

//    public void setAssignmentService(AssignmentService assignmentService) {
//        if (assignmentService != null) {
//            this.assignmentService = assignmentService;
//        }
//    }
}
