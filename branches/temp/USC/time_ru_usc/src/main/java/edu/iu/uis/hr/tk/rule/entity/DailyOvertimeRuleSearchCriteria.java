package edu.iu.uis.hr.tk.rule.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;
import edu.iu.uis.hr.entity.HierarchicalRuleSearchCriteria;

public class DailyOvertimeRuleSearchCriteria extends AbstractEffectiveDatedSearchCriteria implements HierarchicalRuleSearchCriteria {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(DailyOvertimeRule.class);
    }

    private String department;
    private BigDecimal workArea;
    private BigDecimal task;

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setDepartment(edu.iu.uis.hr.Utility.getDefaultStringValue());
        setWorkArea(null);
        setTask(null);
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
		this.workArea = workArea;
	}

	public BigDecimal getTask() {
		return task;
	}

	public void setTask(BigDecimal task) {
		this.task = task;
	}

}