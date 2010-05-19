//TODO
//extend AbstractHolidayIncentiveRule instead of  AbstractShiftDifferentialRule
package edu.iu.uis.hr.tk.rule.entity;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.SaveEvent;

public class HolidayIncentiveRule extends AbstractHolidayIncentiveRule implements SalaryPlanRule {
    private static final Logger LOG = Logger.getLogger(HolidayIncentiveRule.class);

    public HolidayIncentiveRule() {
        super();
    }

    public HolidayIncentiveRule(String location, String salaryPlan, String grade, Date transactionRecordEffectiveDate) {
        this();
        setLocation(location);
        setSalaryPlan(salaryPlan);
        setEffectiveDate(transactionRecordEffectiveDate);
    }

    public void setSalaryPlan(String salaryPlan) {
        super.setSalaryPlan(edu.iu.uis.hr.entity.logic.Utility.trimUpperCaseStringValue(salaryPlan));
    }

    public Stack getHierarchicalFields() {
        return (Stack) HIERARCHICAL_FIELDS.clone();
    }
    
    protected List getOperationalLogics(SaveEvent saveEvent) {
        List logics = edu.iu.uis.hr.Utility.getDefaultListValue();
        return logics;
    }

    public Class getModeAuthorization() {
        return LocationRuleModeAuthorization.class;
    }

}