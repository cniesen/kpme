package edu.iu.uis.hr.tk.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.NonDatabaseEntityDisplayOnlyFieldsLogic;
import edu.iu.uis.hr.tk.entity.UserPreferences;

public class UserPreferencesDisplayOnlyFieldsLogic extends NonDatabaseEntityDisplayOnlyFieldsLogic {

    public boolean execute(Entity entity, String fieldName) {
        if (!(entity instanceof UserPreferences)) {
            throw new IllegalArgumentException("UserPreferencesDisplayOnlyFieldsLogic's execute(Entity entity, String fieldName) method requires a non-null Entity of type UserPreferences");
        }
        if ("preferences".equals(fieldName)) {
            return false;
        } else if (((UserPreferences) entity).get(fieldName).isDisplayOnly()) {
            return true;
        }
        return super.execute(entity, fieldName);
    }

}
