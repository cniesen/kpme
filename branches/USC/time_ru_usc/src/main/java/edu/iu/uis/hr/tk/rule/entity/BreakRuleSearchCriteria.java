package edu.iu.uis.hr.tk.rule.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;
import edu.iu.uis.hr.entity.HierarchicalRuleSearchCriteria;

//TODO Add a boolean active field to enable searching by current status
public class BreakRuleSearchCriteria extends AbstractEffectiveDatedSearchCriteria implements HierarchicalRuleSearchCriteria {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(BreakRule.class);
    }

    private String department;
    private BigDecimal workArea;
    private String universityId;
    private BigDecimal employeeRecord;
    private boolean active;
    

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setUniversityId(edu.iu.uis.hr.Utility.getDefaultStringValue());
        setDepartment(edu.iu.uis.hr.Utility.getDefaultStringValue());
        setEmployeeRecord(null);
        setWorkArea(null);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null) {
            this.department = department.toUpperCase();
        }
    }

    public BigDecimal getEmployeeRecord() {
        return employeeRecord;
    }

    public void setEmployeeRecord(BigDecimal employeeRecord) {
        if (employeeRecord != null) {
            this.employeeRecord = employeeRecord;
        }
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        if (universityId != null) {
            this.universityId = universityId.toUpperCase();
        }
    }

    public BigDecimal getWorkArea() {
        return workArea;
    }

    public void setWorkArea(BigDecimal workArea) {
        if (workArea != null) {
            this.workArea = workArea;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}