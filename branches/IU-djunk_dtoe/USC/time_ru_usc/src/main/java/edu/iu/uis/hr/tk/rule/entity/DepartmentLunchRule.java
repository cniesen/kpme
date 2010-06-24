package edu.iu.uis.hr.tk.rule.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.tk.rule.entity.logic.EmployeeHasAssignmentInWorkAreaLogic;
import edu.iu.uis.hr.tk.rule.entity.logic.EmployeeHasEmployeeRecordLogic;
import edu.iu.uis.hr.tk.rule.entity.logic.WorkAreaInDeparmentLogic;

public class DepartmentLunchRule extends AbstractDepartmentLunchRule implements JobRule {
    private static final Logger LOG = Logger.getLogger(DepartmentLunchRule.class);

    
    public DepartmentLunchRule() {
        super();
    }

    public DepartmentLunchRule(String department, BigDecimal workArea, String universityId, BigDecimal employeeRecord, Date effectiveDate) {
        this();
        setDepartment(department);
        setWorkArea(workArea);
        setUniversityId(universityId);
        setEmployeeRecord(employeeRecord);
        setEffectiveDate(effectiveDate);
    }

    
    public void setDepartment(String department) {
        super.setDepartment(edu.iu.uis.hr.entity.logic.Utility.trimUpperCaseStringValue(department));
    }
    protected List getOperationalLogics(SaveEvent saveEvent) {
        List operationalLogics = edu.iu.uis.hr.Utility.getDefaultListValue();
        operationalLogics.add(WorkAreaInDeparmentLogic.class);
        operationalLogics.add(EmployeeHasAssignmentInWorkAreaLogic.class);
        operationalLogics.add(EmployeeHasEmployeeRecordLogic.class);
        return operationalLogics;
    }
    public Stack getHierarchicalFields() {
        return (Stack) HIERARCHICAL_FIELDS.clone();
    }

    public Class getModeAuthorization() {
        return DepartmentRuleModeAuthorization.class;
    }

}