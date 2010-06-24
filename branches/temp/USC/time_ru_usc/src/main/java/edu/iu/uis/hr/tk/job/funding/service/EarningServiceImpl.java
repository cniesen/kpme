package edu.iu.uis.hr.tk.job.funding.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.ApplicationException;
import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.EarnCode;
import edu.iu.uis.hr.job.funding.entity.EarnProgramCodeMapping;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;

public class EarningServiceImpl extends edu.iu.uis.hr.job.funding.service.EarningServiceImpl implements EarningService {

	private static final Logger LOG = Logger.getLogger(EarningServiceImpl.class);
	private PayCalendarService payCalendarService;
	
	public List getEarnCodesListFromEarnProgram (String earnProgram) {
		Date date = new Date();
		return getEarnCodesListFromEarnProgram (earnProgram, new Date(date.getYear(), date.getMonth(), date.getDate()));
	}
		
	public List getEarnCodesListFromEarnProgram (String earnProgram, Date effectiveDate) {
		return getEarnCodesPerProgram(earnProgram, effectiveDate);
	}

    public BigDecimal getCallbackMinimumHours(String earnCode, Assignment assignment, Date effectiveDate) {
        if (isCallbackEarnCode(earnCode, effectiveDate)) {
            if ("IA".equals(assignment.getChartOfAccounts()) || "IN".equals(assignment.getChartOfAccounts())) {
                return new BigDecimal(3).setScale(2,BigDecimal.ROUND_HALF_UP);
            }
            return new BigDecimal(2).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
        return new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public List getDropdownEarnCodes(Date effectiveDate) {
		List optionsList = new ArrayList();
    	List allEarnCodes = new ArrayList();
    		allEarnCodes.addAll(getEarnCodesListFromEarnProgram(HOURS_DETAIL_COLLECT_BY_HOURS_EARN_PROGRAM, effectiveDate));
    		allEarnCodes.addAll(getEarnCodesListFromEarnProgram(HOURS_DETAIL_COLLECT_BY_BEGIN_AND_END_TIMES_EARN_PROGRAM, effectiveDate));
       		allEarnCodes.addAll(getEarnCodesListFromEarnProgram(TIMESHEET_WEEKLY_OVERTIME_HOURS_EARN_PROGRAM, effectiveDate));
      	
    		
    	Iterator allEarnCodesItr = allEarnCodes.iterator();
    	while(allEarnCodesItr.hasNext()) {
    		EarnCode earnCode = getEarnCode((String)allEarnCodesItr.next(), effectiveDate);
    		optionsList.add( new Option(earnCode.getEarnCode(), earnCode.getLabel()) );
        }
	    return optionsList;
	}

    public List getDropdownWeeklyOvertimeEarnCodes(Date effectiveDate) {
        List optionsList = new ArrayList();
        
        Iterator overtimeEarnCodeListItr = getEarnCodesListFromEarnProgram(TIMESHEET_WEEKLY_OVERTIME_HOURS_EARN_PROGRAM, effectiveDate).iterator();   
        while (overtimeEarnCodeListItr.hasNext()) {
            	EarnCode earnCode = getEarnCode((String)overtimeEarnCodeListItr.next(), effectiveDate);
            	optionsList.add( new Option(earnCode.getEarnCode(), earnCode.getLabel()) );
        }
        return optionsList;
    }

    public EarnCode getEarnCode(String earnCodeString, Date effectiveDate) {
		return super.getEarnCode(earnCodeString, effectiveDate);	
    }

    public List getEarnCodesByEarnProgram(String earnProgram, Date effectiveDate) {
		if (OVERTIME_RULE_ATTENDANCE_HOURS_EARN_PROGRAM.equals(earnProgram)){
			 return getEarnCodesListFromEarnProgram(earnProgram, effectiveDate);
		}
		if (OVERTIME_RULE_BIWEEKLY_REGULAR_HOURS_EARN_PROGRAM.equals(earnProgram)){
			 return getEarnCodesListFromEarnProgram(earnProgram, effectiveDate);
		}
		if (OVERTIME_RULE_HOURLY_REGULAR_HOURS_EARN_PROGRAM.equals(earnProgram)){
			 return getEarnCodesListFromEarnProgram(earnProgram, effectiveDate);
		} else {
			 throw new ApplicationException("You must define a method to call for the earn program passed to getEarnCodesByEarnProgram(String earnProgram)", LOG);
		}
	}
    
    public List getEarnCodesPerProgram(String earnProgram, Date effectiveDate) {
        List earnCodeList = new ArrayList();
        List earnCodes = new ArrayList();
        Collection earnProgramCodeMappings = getEarnProgram(earnProgram, effectiveDate).getEarnProgramCodeMappings();
        if (Utility.hasValue(earnProgramCodeMappings)) {
            Iterator earnProgramCodeMappingsItr = earnProgramCodeMappings.iterator();
            while (earnProgramCodeMappingsItr.hasNext()) {
            	earnCodes.add( ((EarnProgramCodeMapping) earnProgramCodeMappingsItr.next()).getEarnCode() );
            }
            earnCodeList.addAll(earnCodes);
        }
        return earnCodeList;
    }
  
    public List getRegularEarnCodes() {
      return getEarnCodeDataAccess().getRegularHoursEarnCodes();
    }

	public boolean isHoursDetailCollectByHoursEarnCode(String earnCode, Date effectiveDate) {
        return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(HOURS_DETAIL_COLLECT_BY_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }
	
	public boolean isCalculatedOtherEarningsEarnCode(String earnCode, Date effectiveDate) {
        return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_CALCULATED_OTHER_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isCallbackEarnCode(String earnCode, Date effectiveDate) {
		return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_CALL_BACK_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isCompensationEarningsEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_COMPENSATION_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isExcessAttendanceEarningsEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_EXCESS_ATTENDANCE_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isExpectedHoursEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(EXPECTED_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isGeneralAttendanceEarningsEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_GENERAL_ATTENDANCE_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isHolidayEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_HOLIDAY_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }
	
	public boolean isUnallocatedHoursEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(APPROVALS_UNALLOCATED_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

    public boolean isAdditionalPaperworkEarnCode(String earnCode, Date effectiveDate) {
         return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(ADDITIONAL_PAPERWORK_EARN_PROGRAM, effectiveDate).contains(earnCode);
   }

	public boolean isPtoEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_PTO_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isActualWorkHoursEarnCode(String earnCode, Date effectiveDate) {
		if (Utility.hasValue(earnCode)) {
			return 	getEarnCodesListFromEarnProgram(OVERTIME_RULE_HOURLY_REGULAR_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode) ||
					getEarnCodesListFromEarnProgram(OVERTIME_RULE_BIWEEKLY_REGULAR_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode) ||
					getEarnCodesListFromEarnProgram(TIMESHEET_WEEKLY_OVERTIME_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode) ||
					getEarnCodesListFromEarnProgram(TIMESHEET_DAILY_OVERTIME_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
		}
		return false;
	}
	
	public boolean isRegularAttendanceEarningsEarnCode(String earnCode) {
		if (Utility.hasValue(earnCode)) {
			Iterator earnCodesItr = getRegularEarnCodes().iterator();
			while (earnCodesItr.hasNext()) {
				if (earnCode.equals(((EarnCode)earnCodesItr.next()).getEarnCode())) {
					return true;
				}
			}
		}
		return false;
    }
	
	@CacheResult
    public Set<String> getEarnCodeListBySalaryPlan(String deptId, String location, String salaryPlan, String role) {
    	Set<String> earnCodeSet = new TreeSet<String>();
        
		String query = "select erncd from tk_dept_erncd_t where deptid = '@' and location = '@' and sal_admin_plan = '" + salaryPlan + "' and  active = 1 and " + role + "= 1";
        SqlRowSet rs = TKServiceLocator.getTkJdbcTemplate().queryForRowSet(query);
		while (rs.next()) {
			earnCodeSet.add(rs.getString("erncd"));
		}
        
        query = "select erncd, " + role + " role from tk_dept_erncd_t where sal_admin_plan = '" + salaryPlan  + "' and  ( (deptid = '@' and location = '" + location + "') or (deptid = '"+ deptId + "' and location = '" + location +"') ) and  active = 1 order by deptid";
        rs = TKServiceLocator.getTkJdbcTemplate().queryForRowSet(query);
		while (rs.next()) {
			if (rs.getInt("role") == 0) {
				earnCodeSet.remove(rs.getString("erncd"));
			} else {
				earnCodeSet.add(rs.getString("erncd"));
			}
		}

		return  earnCodeSet;
		
    }	

    
    
	public boolean isShiftDifferentialRuleEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(SHIFT_DIFFERENTIAL_RULE_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isShiftEligibleEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(SHIFT_ELIGIBLE_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isValidPtoForManualShiftEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_PTO_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public PayCalendarService getPayCalendarService() {
		return payCalendarService;
	}

	public void setPayCalendarService(PayCalendarService payCalendarService) {
		this.payCalendarService = payCalendarService;
	}
}

/*package edu.iu.uis.hr.tk.job.funding.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.ApplicationException;
import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.dataaccess.EarnCodeDataAccess;
import edu.iu.uis.hr.job.funding.entity.EarnCode;
import edu.iu.uis.hr.job.funding.entity.EarnProgramCodeMapping;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.tk.entity.logic.PseudocacheRefresher;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;

public class EarningServiceImpl extends edu.iu.uis.hr.job.funding.service.EarningServiceImpl implements EarningService {

	private static final Logger LOG = Logger.getLogger(EarningServiceImpl.class);
	private PayCalendarService payCalendarService;
	
	
	public EarningServiceImpl() {
		if (!PseudocacheRefresher.isRefresherStarted) {
			LOG.info("Starting psuedo-cache Refresher.");
			PseudocacheRefresher.refresh(this);
			PseudocacheRefresher.isRefresherStarted = true;
			LOG.info("Refresher has been started.");
		}
	}
	
	public List getEarnCodesListFromEarnProgram (String earnProgram) {
		return getEarnCodesListFromEarnProgram (earnProgram, new Date());
	}
		
	public List getEarnCodesListFromEarnProgram (String earnProgram, Date effectiveDate) {
    	if (effectiveDate == null){
    		effectiveDate = new Date();
    	}
    	effectiveDate = getPayCalendarService().getNextPayEndDate(effectiveDate);
    	
    	if (!EARN_PROGRAM_MAP.containsKey(effectiveDate)) {
    		EARN_PROGRAM_MAP.put(effectiveDate, new HashMap());
    	}
		if (!((Map)EARN_PROGRAM_MAP.get(effectiveDate)).containsKey(earnProgram)) {
			((Map)EARN_PROGRAM_MAP.get(effectiveDate)).put(earnProgram, getEarnCodesPerProgram(earnProgram, effectiveDate));
		}
		return (List)((Map)EARN_PROGRAM_MAP.get(effectiveDate)).get(earnProgram);
	}

    public BigDecimal getCallbackMinimumHours(String earnCode, Assignment assignment, Date effectiveDate) {
        if (isCallbackEarnCode(earnCode, effectiveDate)) {
            if ("IA".equals(assignment.getChartOfAccounts()) || "IN".equals(assignment.getChartOfAccounts())) {
                return new BigDecimal(3).setScale(2);
            }
            return new BigDecimal(2).setScale(2);
        }
        return new BigDecimal(0).setScale(2);
    }

    public List getDropdownEarnCodes(Date effectiveDate) {
		List optionsList = new ArrayList();
    	List allEarnCodes = new ArrayList();
    		allEarnCodes.addAll(getEarnCodesListFromEarnProgram(HOURS_DETAIL_COLLECT_BY_HOURS_EARN_PROGRAM, effectiveDate));
    		allEarnCodes.addAll(getEarnCodesListFromEarnProgram(HOURS_DETAIL_COLLECT_BY_BEGIN_AND_END_TIMES_EARN_PROGRAM, effectiveDate));
    	Iterator allEarnCodesItr = allEarnCodes.iterator();
    	while(allEarnCodesItr.hasNext()) {
    		EarnCode earnCode = getEarnCode((String)allEarnCodesItr.next(), effectiveDate);
    		optionsList.add( new Option(earnCode.getValue(), earnCode.getLabel()) );
        }
	    return optionsList;
	}

    public List getDropdownWeeklyOvertimeEarnCodes(Date effectiveDate) {
        List optionsList = new ArrayList();
        
        Iterator overtimeEarnCodeListItr = getEarnCodesListFromEarnProgram(TIMESHEET_WEEKLY_OVERTIME_HOURS_EARN_PROGRAM, effectiveDate).iterator();   
        while (overtimeEarnCodeListItr.hasNext()) {
            	EarnCode earnCode = getEarnCode((String)overtimeEarnCodeListItr.next(), effectiveDate);
            	optionsList.add( new Option(earnCode.getValue(), earnCode.getLabel()));
        }
        return optionsList;
    }

    public EarnCode getEarnCode(String earnCodeString, Date effectiveDate) {
    	if (effectiveDate == null){
    		effectiveDate = new Date();
    	}
    	if (!EARN_CODE_MAP.containsKey(effectiveDate)) {
    		EARN_CODE_MAP.put(effectiveDate, new HashMap());
    	}
		if (!((Map)EARN_CODE_MAP.get(effectiveDate)).containsKey(earnCodeString)) {
			((Map)EARN_CODE_MAP.get(effectiveDate)).put(earnCodeString, getEarnCodeDataAccess().getEarnCode(earnCodeString, effectiveDate));
		}
		return (EarnCode)((Map)EARN_CODE_MAP.get(effectiveDate)).get(earnCodeString);	
    }

    public EarnCodeDataAccess getEarnCodeDataAccess() {
    	return super.getEarnCodeDataAccess();
    }

    public List getEarnCodesByEarnProgram(String earnProgram, Date effectiveDate) {
		if (OVERTIME_RULE_ATTENDANCE_HOURS_EARN_PROGRAM.equals(earnProgram)){
			 return getEarnCodesListFromEarnProgram(earnProgram, effectiveDate);
		}
		if (OVERTIME_RULE_BIWEEKLY_REGULAR_HOURS_EARN_PROGRAM.equals(earnProgram)){
			 return getEarnCodesListFromEarnProgram(earnProgram, effectiveDate);
		}
		if (OVERTIME_RULE_HOURLY_REGULAR_HOURS_EARN_PROGRAM.equals(earnProgram)){
			 return getEarnCodesListFromEarnProgram(earnProgram, effectiveDate);
		} else {
			 throw new ApplicationException("You must define a method to call for the earn program passed to getEarnCodesByEarnProgram(String earnProgram)", LOG);
		}
	}
    
    public List getEarnCodesPerProgram(String earnProgram, Date effectiveDate) {
        List earnCodeList = new ArrayList();
        List earnCodes = new ArrayList();
        Collection earnProgramCodeMappings = getEarnProgram(earnProgram, effectiveDate).getEarnProgramCodeMappings();
        if (Utility.hasValue(earnProgramCodeMappings)) {
            Iterator earnProgramCodeMappingsItr = earnProgramCodeMappings.iterator();
            while (earnProgramCodeMappingsItr.hasNext()) {
            	earnCodes.add( ((EarnProgramCodeMapping) earnProgramCodeMappingsItr.next()).getEarnCode() );
            }
            earnCodeList.addAll(earnCodes);
        }
        return earnCodeList;
    }
  
    public List getRegularEarnCodes() {
        if (REGULAR_EARN_CODES_LIST.isEmpty()) {
            REGULAR_EARN_CODES_LIST.addAll(getEarnCodeDataAccess().getRegularHoursEarnCodes());
        }
        return REGULAR_EARN_CODES_LIST;
    }

	public boolean isHoursDetailCollectByHoursEarnCode(String earnCode, Date effectiveDate) {
        return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(HOURS_DETAIL_COLLECT_BY_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }
	
	public boolean isCalculatedOtherEarningsEarnCode(String earnCode, Date effectiveDate) {
        return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_CALCULATED_OTHER_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isCallbackEarnCode(String earnCode, Date effectiveDate) {
		return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_CALL_BACK_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isCompensationEarningsEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_COMPENSATION_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isExcessAttendanceEarningsEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_EXCESS_ATTENDANCE_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isExpectedHoursEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(EXPECTED_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isGeneralAttendanceEarningsEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_GENERAL_ATTENDANCE_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isHolidayEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_HOLIDAY_HOURS_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }
	
	public boolean isPtoEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_PTO_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isRegularAttendanceEarningsEarnCode(String earnCode) {
		if (Utility.hasValue(earnCode)) {
			Iterator earnCodesItr = getRegularEarnCodes().iterator();
			while (earnCodesItr.hasNext()) {
				if (earnCode.equals(((EarnCode)earnCodesItr.next()).getEarnCode())) {
					return true;
				}
			}
		}
		return false;
    }

	public boolean isShiftDifferentialRuleEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(SHIFT_DIFFERENTIAL_RULE_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isShiftEligibleEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(SHIFT_ELIGIBLE_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public boolean isValidPtoForManualShiftEarnCode(String earnCode, Date effectiveDate) {
		 return Utility.hasValue(earnCode) && getEarnCodesListFromEarnProgram(TIMESHEET_PTO_EARN_PROGRAM, effectiveDate).contains(earnCode);
    }

	public PayCalendarService getPayCalendarService() {
		return payCalendarService;
	}

	public void setPayCalendarService(PayCalendarService payCalendarService) {
		this.payCalendarService = payCalendarService;
	}
}*/