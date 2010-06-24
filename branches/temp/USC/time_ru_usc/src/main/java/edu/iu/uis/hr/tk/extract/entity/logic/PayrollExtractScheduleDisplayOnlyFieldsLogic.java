package edu.iu.uis.hr.tk.extract.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.PersistentDatabaseEntityDisplayOnlyFieldsLogic;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;

public class PayrollExtractScheduleDisplayOnlyFieldsLogic extends PersistentDatabaseEntityDisplayOnlyFieldsLogic {

    private static final String PENDING_STATUS = "P";

    public boolean execute(Entity entity, String fieldName) {
        if (!(entity instanceof PayrollExtractSchedule)) {
            throw new IllegalArgumentException("PayrollExtractScheduleDisplayOnlyFieldsLogic's execute(Entity entity, String fieldName) method requires a non-null Entity of type PayrollExtractSchedule");
        }
        if (((PayrollExtractSchedule) entity).isDisplayOnly() || FieldNames.ACTUAL_BEGIN_TIME.equals(fieldName)) {
            return true;
        } else if (Entity.SELECTED_FIELD.equals(fieldName) && !((PayrollExtractSchedule) entity).getStatus().equals(PENDING_STATUS)) {
            return true;
        }
        return super.execute(entity, fieldName);
    }
}
