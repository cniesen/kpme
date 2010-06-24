package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.SearchCriteriaRequiredFieldsLogic;

public class ClockLogSearchCriteriaRequiredFieldsLogic extends SearchCriteriaRequiredFieldsLogic {

	@Override
	public boolean execute(Entity entity, String fieldName) {
		return fieldName.equals(FieldNames.UNIVERSITY_ID);
	}

}
