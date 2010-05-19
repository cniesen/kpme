package edu.iu.uis.hr.tk.rule.entity;

import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;
import edu.iu.uis.hr.entity.HierarchicalRuleSearchCriteria;

public class HolidayIncentiveRuleSearchCriteria extends AbstractEffectiveDatedSearchCriteria implements HierarchicalRuleSearchCriteria {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(HolidayIncentiveRule.class);
    }

    private String location;
    private String salaryPlan;

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }
    
    public void clear() {
        setLocation(edu.iu.uis.hr.Utility.getDefaultStringValue());
        setSalaryPlan(edu.iu.uis.hr.Utility.getDefaultStringValue());
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location != null) {
            this.location = location;
        }
    }

    public String getSalaryPlan() {
        return salaryPlan;
    }

    public void setSalaryPlan(String salaryPlan) {
        if (salaryPlan != null) {
            this.salaryPlan = salaryPlan.toUpperCase();
        }
    }

}