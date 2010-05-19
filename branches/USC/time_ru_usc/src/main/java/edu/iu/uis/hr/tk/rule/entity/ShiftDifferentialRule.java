package edu.iu.uis.hr.tk.rule.entity;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.Time;
import edu.iu.uis.hr.tk.rule.entity.logic.ApplicableToDayMustHaveOneSelectedLogic;

public class ShiftDifferentialRule extends AbstractShiftDifferentialRule implements GradeRule {
    private static final Logger LOG = Logger.getLogger(ShiftDifferentialRule.class);
    
    private static final String DEFAULT_TIME= "12:00 AM";

    public ShiftDifferentialRule() {
        super();
        this.setBeginTime(new Time("12:00 AM"));
        this.setEndTime(new Time("12:00 AM"));
    }

    public ShiftDifferentialRule(String location, String salaryPlan, String grade, Date transactionRecordEffectiveDate) {
        this();
        setLocation(location);
        setSalaryPlan(salaryPlan);
        setGrade(grade);
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
        logics.add(ApplicableToDayMustHaveOneSelectedLogic.class);
        return logics;
    }

    public Class getModeAuthorization() {
        return LocationRuleModeAuthorization.class;
    }
	
}