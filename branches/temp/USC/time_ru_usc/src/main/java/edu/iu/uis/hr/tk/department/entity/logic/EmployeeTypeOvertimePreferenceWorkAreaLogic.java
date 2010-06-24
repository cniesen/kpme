package edu.iu.uis.hr.tk.department.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.department.entity.WorkArea;
import edu.iu.uis.hr.tk.service.TranslateService;

public class EmployeeTypeOvertimePreferenceWorkAreaLogic extends AbstractLogic implements OperationalLogic {
    
    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof WorkArea)) {
            throw new IllegalArgumentException("NoActiveAssignmentForTerminatedWorkAreaLogic's execute(Entity entity) method requires a non-null Entity of type WorkArea");
        }
        if (((WorkArea)entity).getEmployeeType().equals(TranslateService.EMPLOYEE_TYPE_BIWEEKLY) && !Utility.hasValue(((WorkArea)entity).getOvertimePreference())) {
            entity.getEntityErrors().add(new String[] { FieldNames.EMPLOYEE_TYPE, FieldNames.OVERTIME_PREFERENCE }, getMessage(MessageKeyConstants.ERROR_BIWEEKLY_EMPLOYEE_OVERTIME_PREFERENCE));
        } else {
            if (((WorkArea)entity).getEmployeeType().equals(TranslateService.EMPLOYEE_TYPE_HOURLY)) {
                ((WorkArea)entity).setOvertimePreference(edu.iu.uis.hr.Utility.getDefaultStringValue());
            }
        }
    }
}