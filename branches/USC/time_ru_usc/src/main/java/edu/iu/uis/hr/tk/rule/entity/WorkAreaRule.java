package edu.iu.uis.hr.tk.rule.entity;

import java.math.BigDecimal;
import java.util.Stack;

import edu.iu.uis.hr.entity.HierarchicalRule;

public interface WorkAreaRule extends DepartmentRule, HierarchicalRule {
    public static final Stack HIERARCHICAL_FIELDS = Utility.getWorkAreaRuleHierarchicalFields();

    public BigDecimal getWorkArea();

    public void setWorkArea(BigDecimal workArea);
}