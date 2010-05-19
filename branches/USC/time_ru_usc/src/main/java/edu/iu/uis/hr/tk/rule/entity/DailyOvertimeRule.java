package edu.iu.uis.hr.tk.rule.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Stack;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.entity.FieldNames;

public class DailyOvertimeRule extends AbstractDailyOvertimeRule implements WorkAreaRule {
    private static final Logger LOG = Logger.getLogger(DailyOvertimeRule.class);

//    public void setSalaryPlan(String salaryPlan) {
//        super.setSalaryPlan(edu.iu.uis.hr.entity.logic.Utility.trimUpperCaseStringValue(salaryPlan));
//    }

    public DailyOvertimeRule() {
        super();
    }
    
    public DailyOvertimeRule(String department, BigDecimal workArea, BigDecimal task, Date effectiveDate) {
        this();
        setDepartment(department);
        setWorkArea(workArea);
        setTask(task);
        setEffectiveDate(effectiveDate);
    }
    
    public Stack getHierarchicalFields() {
    	Stack stack = (Stack) HIERARCHICAL_FIELDS.clone();
    	stack.push(FieldNames.TASK);
        return stack;
    }

    public void setDepartment(String department) {
        super.setDepartment(edu.iu.uis.hr.entity.logic.Utility.trimUpperCaseStringValue(department));
    }
    
    
    public Class getModeAuthorization() {
        return DepartmentRuleModeAuthorization.class;
    }

}