package edu.iu.uis.hr.tk.rule.entity;

import java.util.Stack;

import edu.iu.uis.hr.entity.HierarchicalRule;

public interface SalaryPlanRule extends LocationRule, HierarchicalRule {
    public static final Stack HIERARCHICAL_FIELDS = Utility.getSalaryPlanRuleHierarchicalFields();

    public String getSalaryPlan();

    public void setSalaryPlan(String salaryPlan);
}