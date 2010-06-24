package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.EntityRequiredFieldsLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public class ClockRequiredFieldsLogic  extends EntityRequiredFieldsLogic {

    public boolean execute(Entity entity, String fieldName) {
        if (entity.getLogicExemptPropertyNames().contains(fieldName)) {
            return false;
        } else if ((fieldName.equals("clockAssignment")||fieldName.equals("assignment")) && !Utility.hasValue(((Clock)entity).getWorkStatus())) {
            // TODO : hack for now.  If there is no sync job, then assignment="", and should not be required fields
            return false;
        }
        return true;
    }
}