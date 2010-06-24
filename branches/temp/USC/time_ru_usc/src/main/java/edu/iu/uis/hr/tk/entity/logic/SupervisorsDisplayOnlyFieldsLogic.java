package edu.iu.uis.hr.tk.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.NonDatabaseEntityDisplayOnlyFieldsLogic;
import edu.iu.uis.hr.tk.entity.SupervisorNotification;

public class SupervisorsDisplayOnlyFieldsLogic extends NonDatabaseEntityDisplayOnlyFieldsLogic {


    public boolean execute(Entity entity, String fieldName) {
        if (!(entity instanceof SupervisorNotification)) {
            throw new IllegalArgumentException("SupervisorsDisplayOnlyFieldsLogic's execute(Entity entity, String fieldName) method requires a non-null Entity of type SupervisorNotification");
        }
        if ("supervisors".equals(fieldName)) {
            return false;
        } else if (((SupervisorNotification) entity).get(fieldName).isDisplayOnly()) {
            return true;
        }
        return super.execute(entity, fieldName);
    }

}
