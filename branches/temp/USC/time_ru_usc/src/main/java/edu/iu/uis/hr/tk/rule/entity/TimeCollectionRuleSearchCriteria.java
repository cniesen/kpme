package edu.iu.uis.hr.tk.rule.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;
import edu.iu.uis.hr.entity.HierarchicalRuleSearchCriteria;

public class TimeCollectionRuleSearchCriteria extends AbstractEffectiveDatedSearchCriteria implements HierarchicalRuleSearchCriteria {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(TimeCollectionRule.class);
    }

    private String department;
    private BigDecimal workArea;

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setDepartment(edu.iu.uis.hr.Utility.getDefaultStringValue());
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

    public BigDecimal getWorkArea() {
        return workArea;
    }

    public void setWorkArea(BigDecimal workArea) {
        if (workArea != null) {
            this.workArea = workArea;
        }
    }

}