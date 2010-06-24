package edu.iu.uis.hr.tk.rule.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.directory.service.WebUserService;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.tk.rule.dataaccess.BreakRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.ClockLocationRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.DailyOvertimeRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.DepartmentLunchRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.GracePeriodRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.HolidayIncentiveRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.HourlyHoursRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.ShiftDifferentialRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.SystemLunchRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.TimeCollectionRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.WeeklyOvertimeRuleDataAccess;
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

public class RuleServiceImpl extends AbstractService implements RuleService {
    private GracePeriodRuleDataAccess gracePeriodRuleDataAccess;
    private SystemLunchRuleDataAccess systemLunchRuleDataAccess;
    private DailyOvertimeRuleDataAccess dailyOvertimeRuleDataAccess;
    private WeeklyOvertimeRuleDataAccess weeklyOvertimeRuleDataAccess;
    private ShiftDifferentialRuleDataAccess shiftDifferentialRuleDataAccess;
    private TimeCollectionRuleDataAccess timeCollectionRuleDataAccess;
    private BreakRuleDataAccess breakRuleDataAccess;
    private ClockLocationRuleDataAccess clockLocationRuleDataAccess;
    private DepartmentLunchRuleDataAccess departmentLunchRuleDataAccess;
    private HourlyHoursRuleDataAccess hourlyHoursRuleDataAccess;
    private HolidayIncentiveRuleDataAccess holidayIncentiveRuleDataAccess;
    private WebUserService webUserService;

    public TypedPersistentMaintainedEntityList lookupMaintainableGracePeriodRules() {
        return getGracePeriodRuleDataAccess().lookupMaintainableGracePeriodRules(false);
    }

    @SuppressWarnings("deprecation")
	public GracePeriodRule getGracePeriodRule(Date transactionRecordEffectiveDate) {
        return getGracePeriodRuleDataAccess().getGracePeriodRule(new Date(transactionRecordEffectiveDate.getYear(), transactionRecordEffectiveDate.getMonth(), transactionRecordEffectiveDate.getDate()));
    }

    public void saveGracePeriodRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getGracePeriodRuleDataAccess());
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableSystemLunchRules() {
        return getSystemLunchRuleDataAccess().lookupMaintainableSystemLunchRules(getWebUserService().getUser().hasCorrectionMode());
    }

    public void saveSystemLunchRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getSystemLunchRuleDataAccess());
    }

	@CacheResult
    public SystemLunchRule getCachedEffectiveSystemLunchRule(Date date) {
    	return getSystemLunchRuleDataAccess().getEffectiveSystemLunchRule(date);
    }

    public SystemLunchRule getEffectiveSystemLunchRule(Date date) {
        return getCachedEffectiveSystemLunchRule(date);
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableDailyOvertimeRules(DailyOvertimeRuleSearchCriteria searchCriteria) {
        return getDailyOvertimeRuleDataAccess().lookupMaintainableDailyOvertimeRules(searchCriteria);
    }

    public void saveWeeklyOvertimeRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getWeeklyOvertimeRuleDataAccess());
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableWeeklyOvertimeRules() {
        return getWeeklyOvertimeRuleDataAccess().lookupMaintainableWeeklyOvertimeRules(false);
    }

    public void saveDailyOvertimeRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getDailyOvertimeRuleDataAccess());
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableHolidayIncentiveRules(HolidayIncentiveRuleSearchCriteria searchCriteria){
        return getHolidayIncentiveRuleDataAccess().lookupMaintainableHolidayIncentiveRules(searchCriteria);
    }

    public void saveHolidayIncentiveRules(TypedPersistentMaintainedEntityList rules){
        save(rules, getHolidayIncentiveRuleDataAccess());
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableShiftDifferentialRules(ShiftDifferentialRuleSearchCriteria searchCriteria) {
        return getShiftDifferentialRuleDataAccess().lookupMaintainableShiftDifferentialRules(searchCriteria);
    }

    public void saveShiftDifferentialRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getShiftDifferentialRuleDataAccess());
    }

    public ShiftDifferentialRule getShiftDifferentialRule(String location, String salaryAdminPlan, String grade, Date transactionRecordEffectiveDate) {
        return (ShiftDifferentialRule) getRule(new ShiftDifferentialRule(location, salaryAdminPlan, grade, transactionRecordEffectiveDate), getShiftDifferentialRuleDataAccess());
    }

    @CacheResult
    public List getShiftDifferentialRules(PayCalendar payCalendar) {
        // this is for hourssummary to check & calc shift hours
        return getShiftDifferentialRuleDataAccess().getShiftDifferentialRules(payCalendar);//getCachedShiftDifferentialRules(payCalendar);
    }

    @CacheResult
    public List getShiftDifferentialRules(String earnCode, Date transactionRecordEffectiveDate) {
        // for valid pto for manual shift logic
        return getShiftDifferentialRuleDataAccess().getShiftDifferentialRules(earnCode, transactionRecordEffectiveDate);
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableTimeCollectionRules(TimeCollectionRuleSearchCriteria timeCollectionRuleSearchCriteria) {
        return getTimeCollectionRuleDataAccess().lookupMaintainableTimeCollectionRules(timeCollectionRuleSearchCriteria);
    }

    @CacheResult
    public TimeCollectionRule getTimeCollectionRule(String department, BigDecimal workArea, Date transactionRecordEffectiveDate) {
        return (TimeCollectionRule) getRule(new TimeCollectionRule(department, workArea, transactionRecordEffectiveDate), getTimeCollectionRuleDataAccess());//(TimeCollectionRule) getCachedTimeCollectionRule(department, workArea, transactionRecordEffectiveDate);
    }

    @CacheResult
    public DailyOvertimeRule getDailyOvertimeRule(String department, BigDecimal workArea, BigDecimal task, Date transactionRecordEffectiveDate) {
        return (DailyOvertimeRule) getRule(new DailyOvertimeRule(department, workArea, task, transactionRecordEffectiveDate), getDailyOvertimeRuleDataAccess());//getCachedDailyOvertimeRule(department, workArea, task, transactionRecordEffectiveDate);
    }

    public void saveTimeCollectionRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getTimeCollectionRuleDataAccess());
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableBreakRules(BreakRuleSearchCriteria breakRuleSearchCriteria) {
        return getBreakRuleDataAccess().lookupMaintainableBreakRules(breakRuleSearchCriteria);
    }

    public void saveBreakRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getBreakRuleDataAccess());
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableClockLocationRules(ClockLocationRuleSearchCriteria searchCriteria) {
        return getClockLocationRuleDataAccess().lookupMaintainableClockLocationRules(searchCriteria);
    }

    public void saveClockLocationRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getClockLocationRuleDataAccess());
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableDepartmentLunchRules(DepartmentLunchRuleSearchCriteria searchCriteria) {
        return getDepartmentLunchRuleDataAccess().lookupMaintainableDepartmentLunchRules(searchCriteria);
    }

    public void saveDepartmentLunchRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getDepartmentLunchRuleDataAccess());
    }

    public DepartmentLunchRule getActiveDepartmentLunchRule(String department, BigDecimal workArea, String universityId, BigDecimal employeeRecord, Date transactionRecordEffectiveDate) {
        return (DepartmentLunchRule) getRule(new DepartmentLunchRule(department, workArea, universityId, employeeRecord, transactionRecordEffectiveDate), getDepartmentLunchRuleDataAccess());
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableHourlyHoursRules(HourlyHoursRuleSearchCriteria hourlyHoursRuleSearchCriteria) {
        return getHourlyHoursRuleDataAccess().lookupMaintainableHourlyHoursRules(hourlyHoursRuleSearchCriteria);
    }

    public void saveHourlyHoursRules(TypedPersistentMaintainedEntityList rules) {
        save(rules, getHourlyHoursRuleDataAccess());
    }

    private GracePeriodRuleDataAccess getGracePeriodRuleDataAccess() {
        return gracePeriodRuleDataAccess;
    }

    public void setGracePeriodRuleDataAccess(GracePeriodRuleDataAccess gracePeriodRuleDataAccess) {
        if (gracePeriodRuleDataAccess != null) {
            this.gracePeriodRuleDataAccess = gracePeriodRuleDataAccess;
        }
    }

    private SystemLunchRuleDataAccess getSystemLunchRuleDataAccess() {
        return systemLunchRuleDataAccess;
    }

    public void setSystemLunchRuleDataAccess(SystemLunchRuleDataAccess systemLunchRuleDataAccess) {
        if (systemLunchRuleDataAccess != null) {
            this.systemLunchRuleDataAccess = systemLunchRuleDataAccess;
        }
    }

    private DailyOvertimeRuleDataAccess getDailyOvertimeRuleDataAccess() {
        return dailyOvertimeRuleDataAccess;
    }

    public void setDailyOvertimeRuleDataAccess(DailyOvertimeRuleDataAccess dailyOvertimeRuleDataAccess) {
        if (dailyOvertimeRuleDataAccess != null) {
            this.dailyOvertimeRuleDataAccess = dailyOvertimeRuleDataAccess;
        }
    }

    private ShiftDifferentialRuleDataAccess getShiftDifferentialRuleDataAccess() {
        return shiftDifferentialRuleDataAccess;
    }

    public void setShiftDifferentialRuleDataAccess(ShiftDifferentialRuleDataAccess shiftDifferentialRuleDataAccess) {
        if (shiftDifferentialRuleDataAccess != null) {
            this.shiftDifferentialRuleDataAccess = shiftDifferentialRuleDataAccess;
        }
    }

    private TimeCollectionRuleDataAccess getTimeCollectionRuleDataAccess() {
        return timeCollectionRuleDataAccess;
    }

    public void setTimeCollectionRuleDataAccess(TimeCollectionRuleDataAccess timeCollectionRuleDataAccess) {
        if (timeCollectionRuleDataAccess != null) {
            this.timeCollectionRuleDataAccess = timeCollectionRuleDataAccess;
        }
    }

    private BreakRuleDataAccess getBreakRuleDataAccess() {
        return breakRuleDataAccess;
    }

    public void setBreakRuleDataAccess(BreakRuleDataAccess breakRuleDataAccess) {
        if (breakRuleDataAccess != null) {
            this.breakRuleDataAccess = breakRuleDataAccess;
        }
    }

    private ClockLocationRuleDataAccess getClockLocationRuleDataAccess() {
        return clockLocationRuleDataAccess;
    }

    public void setClockLocationRuleDataAccess(ClockLocationRuleDataAccess clockLocationRuleDataAccess) {
        if (clockLocationRuleDataAccess != null) {
            this.clockLocationRuleDataAccess = clockLocationRuleDataAccess;
        }
    }

    private DepartmentLunchRuleDataAccess getDepartmentLunchRuleDataAccess() {
        return departmentLunchRuleDataAccess;
    }

    public void setDepartmentLunchRuleDataAccess(DepartmentLunchRuleDataAccess departmentLunchRuleDataAccess) {
        if (departmentLunchRuleDataAccess != null) {
            this.departmentLunchRuleDataAccess = departmentLunchRuleDataAccess;
        }
    }

    private HourlyHoursRuleDataAccess getHourlyHoursRuleDataAccess() {
        return hourlyHoursRuleDataAccess;
    }

    public void setHourlyHoursRuleDataAccess(HourlyHoursRuleDataAccess hourlyHoursRuleDataAccess) {
        if (hourlyHoursRuleDataAccess != null) {
            this.hourlyHoursRuleDataAccess = hourlyHoursRuleDataAccess;
        }
    }

    private WebUserService getWebUserService() {
        return webUserService;
    }

    public void setWebUserService(WebUserService webUserService) {
        if (webUserService != null) {
            this.webUserService = webUserService;
        }
    }

    private WeeklyOvertimeRuleDataAccess getWeeklyOvertimeRuleDataAccess() {
        return weeklyOvertimeRuleDataAccess;
    }

    public void setWeeklyOvertimeRuleDataAccess(WeeklyOvertimeRuleDataAccess weeklyOvertimeRuleDataAccess) {
        if (weeklyOvertimeRuleDataAccess != null) {
            this.weeklyOvertimeRuleDataAccess = weeklyOvertimeRuleDataAccess;
        }
    }

    public HolidayIncentiveRuleDataAccess getHolidayIncentiveRuleDataAccess() {
        return holidayIncentiveRuleDataAccess;
    }

    public void setHolidayIncentiveRuleDataAccess(HolidayIncentiveRuleDataAccess holidayIncentiveRuleDataAccess) {
        if (holidayIncentiveRuleDataAccess != null) {
            this.holidayIncentiveRuleDataAccess = holidayIncentiveRuleDataAccess;
        }
    }

	public void delete(ShiftDifferentialRule rule) {
		TypedPersistentMaintainedEntityList list = new TypedPersistentMaintainedEntityList(ShiftDifferentialRule.class);
		list.add(rule);
		delete(list, this.getShiftDifferentialRuleDataAccess());
	}

	@SuppressWarnings("unchecked")
	public void removeRule(Class className) {
		getDataAccess(className).removeAllRecords(className);
	}

	public void addTimeCollectionRuleManually(TimeCollectionRule timeCollectionRule) {
		getTimeCollectionRuleDataAccess().addTimeCollectionRuleManually(timeCollectionRule);
	}

	public void addDepartmentLunchRuleManually(DepartmentLunchRule departmentLunchRule) {
		getDepartmentLunchRuleDataAccess().addDepartmentLunchRuleManually(departmentLunchRule);

	}

	public void addGracePeriodRuleManually(GracePeriodRule gracePeriodRule) {
		getGracePeriodRuleDataAccess().addGracePeriodRuleManually(gracePeriodRule);

	}

	public void addSystemLunchRuleManually(SystemLunchRule systemLunchRule) {
		getSystemLunchRuleDataAccess().addSystemLunchRuleManually(systemLunchRule);
	}

	public void addWeeklyOvertimeRuleManually(WeeklyOvertimeRule weeklyOvertimeRule) {
		getWeeklyOvertimeRuleDataAccess().addWeeklyOvertimeRuleManually(weeklyOvertimeRule);
	}
}