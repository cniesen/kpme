package edu.iu.uis.hr.tk.directory.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.job.funding.entity.WorkArea;

public class WorkAreaRole extends UserRole {
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        PROPERTY_DESCRIPTORS.put(edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD, edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_NON_DATABASE_STRING_PROPERTY_DESCRIPTOR);
        PROPERTY_DESCRIPTORS.put(FieldNames.WORK_AREA, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.WORK_AREA, WorkArea.class));
        PROPERTY_DESCRIPTORS.put(FieldNames.DESCRIPTION, new NonDatabaseStringPropertyDescriptor(FieldNames.DESCRIPTION, true, 30));
        PROPERTY_DESCRIPTORS.put(FieldNames.DEPARTMENT, new NonDatabaseStringPropertyDescriptor(FieldNames.DEPARTMENT, true, 30));
    }
    private BigDecimal workArea;
    private String department;
    private String description;

    public WorkAreaRole() {
        super();
    }

    public WorkAreaRole(String roleName, BigDecimal workArea, String department, String workAreaDescription) {
        super(roleName);
        setWorkArea(workArea);
        setDepartment(department);
        setDescription(workAreaDescription);
    }

    public Class getModeAuthorization() {
        return WorkAreaRoleModeAuthorization.class;
    }

    public String getDropdownName(String fieldName) {
        if (edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD.equals(fieldName)) {
            return edu.iu.uis.hr.tk.directory.Utility.WORK_AREA_ROLE_NAME_DROPDOWN;
        }
        return super.getDropdownName(fieldName);
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public Object getRoleData() {
        return getWorkArea();
    }

    public BigDecimal getWorkArea() {
        return workArea;
    }

    public void setWorkArea(BigDecimal workArea) {
        if (workArea != null) {
            this.workArea = workArea;
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
        return new StringBuffer(FieldNames.WORK_AREA).append(edu.iu.uis.hr.Utility.COMMA).append(FieldNames.DEPARTMENT).append(edu.iu.uis.hr.Utility.COMMA).append(FieldNames.DESCRIPTION).toString();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null) {
            this.department = department;
        }
    }

}