package edu.iu.uis.hr.tk.rule.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.tk.rule.entity.logic.HoursDistributionAllowedLogic;
import edu.iu.uis.hr.tk.rule.entity.logic.WorkAreaInDeparmentLogic;

public class TimeCollectionRule extends AbstractTimeCollectionRule implements WorkAreaRule {
    private static final Logger LOG = Logger.getLogger(TimeCollectionRule.class);
    public static final long HOUR_DISTRIBUTION_ALLOWED_RANGE = 24; //after this amount of hours, hour distribution won't be allowed.


    public TimeCollectionRule() {
        super();
    }

    public TimeCollectionRule(String department, BigDecimal workArea, Date effectiveDate) {
        this();
        setDepartment(department);
        setWorkArea(workArea);
        setEffectiveDate(effectiveDate);
    }

    public void setDepartment(String department) {
        super.setDepartment(edu.iu.uis.hr.entity.logic.Utility.trimUpperCaseStringValue(department));
    }

    protected List getOperationalLogics(SaveEvent saveEvent) {
        List operationalLogics = edu.iu.uis.hr.Utility.getDefaultListValue();
        operationalLogics.add(HoursDistributionAllowedLogic.class);
        operationalLogics.add(WorkAreaInDeparmentLogic.class);
        return operationalLogics;
    }

    public Stack getHierarchicalFields() {
        return (Stack)HIERARCHICAL_FIELDS.clone();
    }

    public Class getModeAuthorization() {
        return DepartmentRuleModeAuthorization.class;
    }

}