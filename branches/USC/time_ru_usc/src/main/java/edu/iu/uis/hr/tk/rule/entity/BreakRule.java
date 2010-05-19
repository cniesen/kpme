package edu.iu.uis.hr.tk.rule.entity;

import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.tk.rule.entity.logic.EmployeeHasAssignmentInWorkAreaLogic;
import edu.iu.uis.hr.tk.rule.entity.logic.EmployeeHasEmployeeRecordLogic;
import edu.iu.uis.hr.tk.rule.entity.logic.WorkAreaInDeparmentLogic;

public class BreakRule extends AbstractBreakRule implements JobRule {
    private static final Logger LOG = Logger.getLogger(BreakRule.class);

    public void setDepartment(String department) {
        super.setDepartment(edu.iu.uis.hr.entity.logic.Utility.trimUpperCaseStringValue(department));
    }

    public Stack getHierarchicalFields() {
        return (Stack) HIERARCHICAL_FIELDS.clone();
    }

    protected List getOperationalLogics(SaveEvent saveEvent) {
        List operationalLogics = edu.iu.uis.hr.Utility.getDefaultListValue();
        operationalLogics.add(WorkAreaInDeparmentLogic.class);
        operationalLogics.add(EmployeeHasAssignmentInWorkAreaLogic.class);
        operationalLogics.add(EmployeeHasEmployeeRecordLogic.class);
        return operationalLogics;
    }

    public Class getModeAuthorization() {
        return DepartmentRuleModeAuthorization.class;
    }

}