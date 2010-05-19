package edu.iu.uis.hr.tk.rule.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.service.Service;
import edu.iu.uis.hr.tk.rule.entity.BreakRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRule;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRule;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.GracePeriodRule;
import edu.iu.uis.hr.tk.rule.entity.HolidayIncentiveRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.HourlyHoursRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRule;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.SystemLunchRule;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRule;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.WeeklyOvertimeRule;

public interface RuleService extends Service {
    // System Rules
    public TypedPersistentMaintainedEntityList lookupMaintainableGracePeriodRules();

    public GracePeriodRule getGracePeriodRule(Date transactionRecordEffectiveDate);

    public void saveGracePeriodRules(TypedPersistentMaintainedEntityList rules);

    public TypedPersistentMaintainedEntityList lookupMaintainableSystemLunchRules();

    public void saveSystemLunchRules(TypedPersistentMaintainedEntityList rules);

    public SystemLunchRule getCachedEffectiveSystemLunchRule(Date date);
    public SystemLunchRule getEffectiveSystemLunchRule(Date date);

    public TypedPersistentMaintainedEntityList lookupMaintainableWeeklyOvertimeRules();

    public void saveWeeklyOvertimeRules(TypedPersistentMaintainedEntityList rules);

    // Salary Plan Rules
    public TypedPersistentMaintainedEntityList lookupMaintainableDailyOvertimeRules(DailyOvertimeRuleSearchCriteria searchCriteria);

    public void saveDailyOvertimeRules(TypedPersistentMaintainedEntityList rules);

    public TypedPersistentMaintainedEntityList lookupMaintainableHolidayIncentiveRules(HolidayIncentiveRuleSearchCriteria searchCriteria);

    public void saveHolidayIncentiveRules(TypedPersistentMaintainedEntityList rules);

    // Grade Rules
    public TypedPersistentMaintainedEntityList lookupMaintainableShiftDifferentialRules(ShiftDifferentialRuleSearchCriteria searchCriteria);

    public void saveShiftDifferentialRules(TypedPersistentMaintainedEntityList rules);

    public ShiftDifferentialRule getShiftDifferentialRule(String location, String salaryAdminPlan, String grade, Date transactionRecordEffectiveDate); // Proof of Concept

    public List getShiftDifferentialRules(PayCalendar payCalendar);

    public List getShiftDifferentialRules(String earnCode, Date transactionRecordEffectiveDate);

    public void delete(ShiftDifferentialRule rule);

    // Work Area Rules
    public TypedPersistentMaintainedEntityList lookupMaintainableTimeCollectionRules(TimeCollectionRuleSearchCriteria timeCollectionRuleSearchCriteria);

    public TimeCollectionRule getTimeCollectionRule(String department, BigDecimal workArea, Date transactionRecordEffectiveDate);

    public void saveTimeCollectionRules(TypedPersistentMaintainedEntityList rules);

    // Job Rules
    public TypedPersistentMaintainedEntityList lookupMaintainableBreakRules(BreakRuleSearchCriteria searchCriteria);

    public void saveBreakRules(TypedPersistentMaintainedEntityList rules);

    public TypedPersistentMaintainedEntityList lookupMaintainableClockLocationRules(ClockLocationRuleSearchCriteria searchCriteria);

    public void saveClockLocationRules(TypedPersistentMaintainedEntityList rules);

    public TypedPersistentMaintainedEntityList lookupMaintainableDepartmentLunchRules(DepartmentLunchRuleSearchCriteria searchCriteria);

    public void saveDepartmentLunchRules(TypedPersistentMaintainedEntityList rules);

    public DepartmentLunchRule getActiveDepartmentLunchRule(String department, BigDecimal workArea, String universityId, BigDecimal employeeRecord, Date transactionRecordEffectiveDate);

    public TypedPersistentMaintainedEntityList lookupMaintainableHourlyHoursRules(HourlyHoursRuleSearchCriteria searchCriteria);

    public void saveHourlyHoursRules(TypedPersistentMaintainedEntityList rules);

    public DailyOvertimeRule getDailyOvertimeRule(String department, BigDecimal workArea, BigDecimal task, Date transactionRecordEffectiveDate);

    // remove rules
    public void removeRule(Class className);

    // add rules
    public void addTimeCollectionRuleManually(TimeCollectionRule timeCollectionRule);

    public void addDepartmentLunchRuleManually(DepartmentLunchRule departmentLunchRule);

    public void addSystemLunchRuleManually(SystemLunchRule systemLunchRule);

    public void addGracePeriodRuleManually(GracePeriodRule gracePeriodRule);

    public void addWeeklyOvertimeRuleManually(WeeklyOvertimeRule weeklyOvertimeRule);


//    public static final Map TIME_COLLECTION_RULE_MAP = new HashMap();
//    public static final Map SHIFT_DIFFERENTIAL_RULES_BY_PAYCALENDAR_MAP = new HashMap();
//    public static final Map SHIFT_DIFFERENTIAL_RULES_BY_EARNCODE_DATE_MAP = new HashMap();
//    public static final Map SYSTEM_LUNCH_RULE_MAP = new HashMap();
//    public static final Map DAILY_OVERTIME_RULE_MAP = new HashMap();
}