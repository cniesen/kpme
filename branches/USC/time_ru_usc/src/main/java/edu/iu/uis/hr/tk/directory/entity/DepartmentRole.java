package edu.iu.uis.hr.tk.directory.entity;

import java.util.HashMap;
import java.util.Map;

import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.position.entity.Department;

public class DepartmentRole extends UserRole {
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        PROPERTY_DESCRIPTORS.put(edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD, edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_NON_DATABASE_STRING_PROPERTY_DESCRIPTOR);
        PROPERTY_DESCRIPTORS.put(FieldNames.DEPARTMENT, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.DEPARTMENT, Department.class));
        PROPERTY_DESCRIPTORS.put(FieldNames.DESCRIPTION, new NonDatabaseStringPropertyDescriptor(FieldNames.DESCRIPTION, true, 30));
    }
    private String department;
    private String description;

    public DepartmentRole() {
    }

    public DepartmentRole(String roleName, String department, String description) {
        super(roleName);
        setDepartment(department);
        setDescription(description);
    }

    public Class getModeAuthorization() {
        return DepartmentRoleModeAuthorization.class;
    }

    public String getDropdownName(String fieldName) {
        if (edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD.equals(fieldName)) {
            return edu.iu.uis.hr.tk.directory.Utility.DEPARTMENT_ROLE_NAME_DROPDOWN;
        }
        return super.getDropdownName(fieldName);
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public Object getRoleData() {
        return getDepartment();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null) {
            this.department = department.toUpperCase().trim();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    public String getLookupFieldsToReturn(String fieldName) {
        return new StringBuffer(FieldNames.DEPARTMENT).append(edu.iu.uis.hr.Utility.COMMA).append(FieldNames.DESCRIPTION).toString();
    }

}