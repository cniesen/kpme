package edu.iu.uis.hr.tk.rule.entity;

import java.util.Stack;

import edu.iu.uis.hr.entity.FieldNames;

public class Utility {
    private static final Stack SALARY_PLAN_RULE_HIERARCHICAL_FIELDS = new Stack();
    static {
        SALARY_PLAN_RULE_HIERARCHICAL_FIELDS.push(FieldNames.LOCATION);
        SALARY_PLAN_RULE_HIERARCHICAL_FIELDS.push(FieldNames.SALARY_PLAN);
    }
    private static final Stack GRADE_RULE_HIERARCHICAL_FIELDS = getSalaryPlanRuleHierarchicalFields();
    static {
        GRADE_RULE_HIERARCHICAL_FIELDS.push(FieldNames.GRADE);
    }
    private static final Stack WORK_AREA_RULE_HIERARCHICAL_FIELDS = new Stack();
    static {
        WORK_AREA_RULE_HIERARCHICAL_FIELDS.push(FieldNames.DEPARTMENT);
        WORK_AREA_RULE_HIERARCHICAL_FIELDS.push(FieldNames.WORK_AREA);
    }
    private static final Stack JOB_RULE_HIERARCHICAL_FIELDS = getWorkAreaRuleHierarchicalFields();
    static {
        JOB_RULE_HIERARCHICAL_FIELDS.push(FieldNames.UNIVERSITY_ID);
        JOB_RULE_HIERARCHICAL_FIELDS.push(FieldNames.EMPLOYEE_RECORD);
    }

    protected static Stack getSalaryPlanRuleHierarchicalFields() {
        return (Stack)SALARY_PLAN_RULE_HIERARCHICAL_FIELDS.clone();
    }

    protected static Stack getGradeRuleHierarchicalFields() {
        return (Stack)GRADE_RULE_HIERARCHICAL_FIELDS.clone();
    }

    protected static Stack getWorkAreaRuleHierarchicalFields() {
        return (Stack)WORK_AREA_RULE_HIERARCHICAL_FIELDS.clone();
    }

    protected static Stack getJobRuleHierarchicalFields() {
        return (Stack)JOB_RULE_HIERARCHICAL_FIELDS.clone();
    }
}