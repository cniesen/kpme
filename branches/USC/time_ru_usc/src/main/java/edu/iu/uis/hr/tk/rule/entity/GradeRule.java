package edu.iu.uis.hr.tk.rule.entity;

import java.util.Stack;

public interface GradeRule extends SalaryPlanRule {
    public static final Stack HIERARCHICAL_FIELDS = Utility.getGradeRuleHierarchicalFields();

    public String getGrade();

    public void setGrade(String grade);
}