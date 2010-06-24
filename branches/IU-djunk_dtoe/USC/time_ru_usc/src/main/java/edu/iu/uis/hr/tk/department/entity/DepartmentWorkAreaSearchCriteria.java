package edu.iu.uis.hr.tk.department.entity;

import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractSetControlMappedSearchCriteria;
import edu.iu.uis.hr.entity.SetidedSearchCriteria;
import edu.iu.uis.hr.entity.logic.EntityControlledValueFieldsLogic;
import edu.iu.uis.hr.position.entity.Department;
import edu.iu.uis.hr.tk.department.entity.logic.DepartmentWorkAreaSearchCriteriaRequiredFieldsLogic;

public class DepartmentWorkAreaSearchCriteria extends AbstractSetControlMappedSearchCriteria implements SetidedSearchCriteria {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(Department.class);
    }

    private String department;
    private boolean active;

    public Class getSetidedPersitentDatabaseEntityClass() {
        return Department.class;
    }

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setDepartment(edu.iu.uis.hr.Utility.getDefaultStringValue());
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null) {
            this.department = department.toUpperCase();
        }
    }

    public Class getRequiredFieldsLogic() {
        return DepartmentWorkAreaSearchCriteriaRequiredFieldsLogic.class;
    }

    public Class getControlledValueFieldsLogic() {
        return EntityControlledValueFieldsLogic.class;
    }

    public boolean isEffectiveDated() {
        // TODO : only display departmentid
        return false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
