package edu.iu.uis.hr.tk.entity.logic;

import java.util.HashMap;
import java.util.Map;

import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.EntityFilterControlledValueFieldsLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.DepartmentFilterAuthorization;
import edu.iu.uis.hr.tk.LocationFilterAuthorization;
import edu.iu.uis.hr.tk.WorkAreaFilterAuthorization;

public class EntityFilterControlledValueFieldsLogicImpl extends AbstractLogic implements EntityFilterControlledValueFieldsLogic {
    private static final Map FIELD_NAME_FILTER_AUTHORIZATION_CLASSES_MAP = new HashMap();
    static {
        FIELD_NAME_FILTER_AUTHORIZATION_CLASSES_MAP.put(FieldNames.WORK_AREA, WorkAreaFilterAuthorization.class);
        FIELD_NAME_FILTER_AUTHORIZATION_CLASSES_MAP.put(FieldNames.DEPARTMENT, DepartmentFilterAuthorization.class);
        FIELD_NAME_FILTER_AUTHORIZATION_CLASSES_MAP.put(FieldNames.LOCATION, LocationFilterAuthorization.class);
    }
    
    public Class execute(String fieldName) {
        Class filterAuthorizationClass = (Class)FIELD_NAME_FILTER_AUTHORIZATION_CLASSES_MAP.get(fieldName);
        if (!Utility.hasValue(filterAuthorizationClass)) {
            filterAuthorizationClass = null;
        }
        return filterAuthorizationClass;
    }
}