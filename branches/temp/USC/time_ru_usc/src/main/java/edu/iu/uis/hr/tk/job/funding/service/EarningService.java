package edu.iu.uis.hr.tk.job.funding.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.iu.uis.hr.tk.job.funding.entity.Assignment;

public interface EarningService extends edu.iu.uis.hr.job.funding.service.EarningService {
	
    public static final String TIMESHEET_GENERAL_ATTENDANCE_HOURS_EARN_PROGRAM = "T03";
    public static final String TIMESHEET_EXCESS_ATTENDANCE_HOURS_EARN_PROGRAM = "T04";
    public static final String TIMESHEET_CALCULATED_OTHER_HOURS_EARN_PROGRAM = "T05";
    public static final String TIMESHEET_COMPENSATION_HOURS_EARN_PROGRAM = "T06";
    public static final String TIMESHEET_CALL_BACK_HOURS_EARN_PROGRAM = "T07";
    public static final String TIMESHEET_WEEKLY_OVERTIME_HOURS_EARN_PROGRAM = "T08";
    public static final String TIMESHEET_HOLIDAY_HOURS_EARN_PROGRAM = "T09"; 
//    public static final String TIMESHEET_SHIFT_ON_PTO_EARN_PROGRAM = "T12";
    public static final String TIMESHEET_PTO_EARN_PROGRAM = "T13";
    public static final String BIWEEKLY_EMPLOYEE_EARN_PROGRAM = "T14";
    public static final String BIWEEKLY_SUPERVISOR_EARN_PROGRAM = "T15";
    public static final String BIWEEKLY_PAYROLL_PROCESSOR_EARN_PROGRAM = "T16"; 
    public static final String TIMESHEET_DAILY_OVERTIME_HOURS_EARN_PROGRAM = "T17";
    public static final String OVERTIME_RULE_ATTENDANCE_HOURS_EARN_PROGRAM = "T18";
    public static final String OVERTIME_RULE_BIWEEKLY_REGULAR_HOURS_EARN_PROGRAM = "T19";
    public static final String OVERTIME_RULE_HOURLY_REGULAR_HOURS_EARN_PROGRAM = "T20";
    public static final String SHIFT_DIFFERENTIAL_RULE_EARN_PROGRAM = "T21";
//    public static final String APPROVALS_ABSENT_HOURS_EARN_PROGRAM = "T22";
    public static final String APPROVALS_UNALLOCATED_HOURS_EARN_PROGRAM = "T23";
    public static final String HOURLY_EMPLOYEE_EARN_PROGRAM = "T24";
    public static final String HOURLY_SUPERVISOR_EARN_PROGRAM = "T25";
    public static final String HOURLY_PAYROLL_PROCESSOR_EARN_PROGRAM = "T26";
    public static final String HOURS_DETAIL_COLLECT_BY_HOURS_EARN_PROGRAM = "T27";
    public static final String HOURS_DETAIL_COLLECT_BY_BEGIN_AND_END_TIMES_EARN_PROGRAM = "T28";
    public static final String EXPECTED_HOURS_EARN_PROGRAM = "T29";
    public static final String SHIFT_ELIGIBLE_EARN_PROGRAM = "T30";
    public static final String ADDITIONAL_PAPERWORK_EARN_PROGRAM = "T31";
        
    public static final Map EARN_CODE_MAP = new HashMap();
    public static final Map EARN_PROGRAM_MAP = new HashMap();    
//
//    public static final List TIMESHEET_SHIFT_ON_PTO_EARN_PROGRAM_LIST = new ArrayList();
//
//    public static final List TIMESHEET_DAILY_OVERTIME_HOURS_EARN_PROGRAM_LIST = new ArrayList();
//
//    public static final List APPROVALS_ABSENT_HOURS_EARN_PROGRAM_LIST = new ArrayList();
//
//    public static final List APPROVALS_UNALLOCATED_HOURS_EARN_PROGRAM_LIST = new ArrayList();

    public static final List REGULAR_EARN_CODES_LIST = new ArrayList();

    public boolean isPtoEarnCode(String earnCode, Date effectiveDate);
    
    public boolean isRegularAttendanceEarningsEarnCode(String earnCode);
    
    public boolean isActualWorkHoursEarnCode(String earnCode, Date effectiveDate);

    public boolean isGeneralAttendanceEarningsEarnCode(String earnCode, Date effectiveDate);

    public boolean isExcessAttendanceEarningsEarnCode(String earnCode, Date effectiveDate);

    public boolean isCalculatedOtherEarningsEarnCode(String earnCode, Date effectiveDate);
    
    public boolean isHoursDetailCollectByHoursEarnCode(String earnCode, Date effectiveDate);

    public boolean isShiftDifferentialRuleEarnCode(String earnCode, Date effectiveDate);

    public boolean isShiftEligibleEarnCode(String earnCode, Date effectiveDate);

    public boolean isExpectedHoursEarnCode(String earnCode, Date effectiveDate);

    public boolean isValidPtoForManualShiftEarnCode(String earnCode, Date effectiveDate);
    
    public boolean isCompensationEarningsEarnCode(String earnCode, Date effectiveDate);
    
    public boolean isHolidayEarnCode(String earnCode, Date effectiveDate);
    
    public boolean isUnallocatedHoursEarnCode(String earnCode, Date effectiveDate);

    public boolean isAdditionalPaperworkEarnCode(String earnCode, Date effectiveDate);
    
    public List getDropdownWeeklyOvertimeEarnCodes(Date effectiveDate);
    
    public List getDropdownEarnCodes(Date effectiveDate);
    
    public List getEarnCodesByEarnProgram(String earnProgram, Date effectiveDate);
    
	public List getEarnCodesListFromEarnProgram (String earnProgram);
    
    public List getEarnCodesListFromEarnProgram(String earnProgram, Date effectiveDate);
    
    public BigDecimal getCallbackMinimumHours(String earnCode, Assignment assignment, Date effectiveDate);
    
    public Set getEarnCodeListBySalaryPlan(String deptId, String location, String salaryPlan, String role);    
}
