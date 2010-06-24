package edu.iu.uis.hr.tk.department.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.EntityRequiredFieldsLogic;

public class ActualTimeSearchCriteriaRequiredFieldsLogic extends EntityRequiredFieldsLogic {

    public boolean execute(Entity entity, String fieldName) {
        return fieldName.equals(FieldNames.DOCUMENT_ID);
    }

}
