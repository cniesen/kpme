package edu.iu.uis.hr.tk.department.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.EntityRequiredFieldsLogic;

public class DepartmentWorkAreaSearchCriteriaRequiredFieldsLogic extends EntityRequiredFieldsLogic {

    public boolean execute(Entity entity, String fieldName) {
        return fieldName.equals(FieldNames.DEPARTMENT);
    }

}
