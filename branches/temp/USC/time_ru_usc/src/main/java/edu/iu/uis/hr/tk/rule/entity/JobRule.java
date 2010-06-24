package edu.iu.uis.hr.tk.rule.entity;

import java.math.BigDecimal;
import java.util.Stack;

public interface JobRule extends WorkAreaRule {
    public static final Stack HIERARCHICAL_FIELDS = Utility.getJobRuleHierarchicalFields();

    public String getUniversityId();

    public void setUniversityId(String universityId);

    public BigDecimal getEmployeeRecord();

    public void setEmployeeRecord(BigDecimal employeeRecord);
}