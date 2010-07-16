package org.kuali.hr.leave.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.util.JSTLConstants;

public class T2Constants extends JSTLConstants {

	private static final long serialVersionUID = 7263043079349629634L;

	/* CHANGING STATIC CALLS TO REFERENCE THESE NUMBERS SO
	 * THAT THIS CAN BE ABSTRACTED TO APPLICATION LOGIC LATER */
	public static final Integer LAST_YEAR = 2009;
	public static final Integer CURRENT_YEAR = 2010;


	public static final Integer APPLICATION_BEGINNING_YEAR = 2009;
	public static final Calendar APPLICATION_BEGINNING_CALENDAR = new GregorianCalendar(); //used in the balance transfer service
	static {
		APPLICATION_BEGINNING_CALENDAR.set(Calendar.YEAR, T2Constants.APPLICATION_BEGINNING_YEAR);
		APPLICATION_BEGINNING_CALENDAR.set(Calendar.MONTH, 0);
		APPLICATION_BEGINNING_CALENDAR.set(Calendar.DAY_OF_MONTH, 1);
		java.util.Date date = APPLICATION_BEGINNING_CALENDAR.getTime(); //this forces the calendar to recalculate the new values for the date (no really, it does)
	}
	public static final Integer WORK_HOUR = 8;
	public static final Integer HOLIDAY_HOUR = 8;
	public static final Integer ANNUAL_HOURS_BEFORE_FIVE_YEARS = 240;
	public static final Integer ANNUAL_HOURS_AFTER_FIVE_YEARS = 288;
	public static final Integer ANNUAL_HONORARY_HOURS = 480;
	public static final Integer MILITARY_PTO_ANNUAL_HOURS = 80;
	public static final Integer MILITARY_TRAINING_DUTY_ANNUAL_HOURS = 120;
	public static final Integer INJURY_WITHIN_SEVEN_DAYS_HOURS = 40;

	public static final String DEFAULT_FORM_NAME = "CalendarForm";

	public static Map<Integer, String> MONTHS = new HashMap<Integer, String>();
	static {
		MONTHS.put(0, "January");
		MONTHS.put(1, "February");
		MONTHS.put(2, "March");
		MONTHS.put(3, "April");
		MONTHS.put(4, "May");
		MONTHS.put(5, "June");
		MONTHS.put(6, "July");
		MONTHS.put(7, "August");
		MONTHS.put(8, "September");
		MONTHS.put(9, "October");
		MONTHS.put(10, "November");
		MONTHS.put(11, "December");
	}

	public static Integer[] monthNumbers = {1,2,3,4,5,6,7,8,9,10,11,12};
	public static String[] monthNames = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};

	public static Map<Integer, String> DAYS = new HashMap<Integer, String>();
	static {
		DAYS.put(1, "Sunday");
		DAYS.put(2, "Monday");
		DAYS.put(3, "Tuesday");
		DAYS.put(4, "Wednesday");
		DAYS.put(5, "Thursday");
		DAYS.put(6, "Friday");
		DAYS.put(7, "Saturday");
	}

	public static String ACCRUAL_CODE_FAUX_SUFFIX = "_ACCRUAL";
	public static String ACCRUAL_CODE_ACTUAL_AVAIL = "_ACTUAL_AVAIL";

	// If there is any new leave code that belongs to either pto or sick bucket, remember to add it to the bucket below.
	// It's mainly for the reports

	public static String PTO_CODE = "PTO";
	public static String PTO_ACCRUAL_CAT_CODE = "PTO";
	public static String SICK_CODE = "SCK";
	public static String SICK_ACCRUAL_CAT_CODE = "SICK";
	public static String HOLIDAY_CODE = "HOL";
	public static String HOLIDAY_FMLA_CODE = "HFL";
	public static String HONORARY_CODE = "HON";
	public static String FMLA_HONORARY_CODE = "HNF";
	public static String PTO_ACCRUAL_FAUX_CODE = "PTO_ACR";
	public static String SICK_ACCRUAL_FAUX_CODE = "SCK_ACR";
	public static String HOLIDAY_ACCRUAL_FAUX_CODE = "HOL_ACR";
	public static String HONORARY_ACCRUAL_FAUX_CODE = "HON_ACR";
	public static String MIL_CODE = "MIL";

	// FMLA
	public static String PTO_FMLA = "PFL";
	public static String SICK_FMLA = "SFL";
	public static String ABE_FMLA = "AFL";
	public static String HON_FMLA = "HNF";
	public static String HOL_FMLA = "HFL";
	public static String FMLA_MIL_CARE_ABS = "AMC";
	public static String FMLA_MIL_CARE_PTO = "PFC";
	public static String FMLA_MIL_CARE_SCK = "SFC";
	public static String FMLA_MIL_CARE_HON = "HNC";

	public static String ABSENT_W_BENEFITS = "AWB";
	public static String FMLA_W_BENEFITS = "FWB";

	public static String CHRISTMAS = "Christmas";
	public static String PTO_ANTICIPATED = "ANP";
	public static String MILITARY_C0DE = "PML";
	public static String FAM_MILITARY_CODE = "PMF";
	public static String PTO_INJURY_CODE = "IJP";
	public static String INJURY_SEVEN_DAYS = "INJ";
	public static String WEATHER_CODE = "WTH";
	public static String COURT_DUTY_CODE = "CTD";
	public static String ABSENT_WITHOUT_PAY = "ABE";
	public static String INJURY_HOLIDAY = "IJH";
	public static String MILITARY_CAREGIVER_HOLIDAY = "HFC";
	public static String INJ_CHRG_HON = "IHN";
	public static String INJ_SICK = "IJS";
	public static String INJ_NO_PAY = "INA";
	public static String INJ_NO_PAY_WITH_BEN = "IND";

	public static String OTHER_ACCRUAL_CODE = "OTHER";

	public static class ReportConstants {
		public static final Map<String, String> DOCUMENT_STATUS = new HashMap<String, String>();
		static {
			DOCUMENT_STATUS.put("ALL","ALL");
			DOCUMENT_STATUS.put("F","Approved");
			DOCUMENT_STATUS.put("R","Routed");
			DOCUMENT_STATUS.put("P", "Pending");
			DOCUMENT_STATUS.put("D", "Disapproved");
		}

	}

	public static class LeaveEarnCodes{
		public static final String LVE = "LVE";
		public static final String LFL = "LFL";
		public static final String LML = "LML";

		public static List<String> leaveEarnCodes = new ArrayList<String>();
		static{
			leaveEarnCodes.add(LVE);
			leaveEarnCodes.add(LFL);
			leaveEarnCodes.add(LML);
		}
	}

	public static List<String> BASIC_TIME_OFFS = new ArrayList<String>();

	static {
		BASIC_TIME_OFFS.add(T2Constants.PTO_CODE);
		BASIC_TIME_OFFS.add(T2Constants.SICK_CODE);
		BASIC_TIME_OFFS.add(T2Constants.HONORARY_CODE);
	}

	public static List<String> TIMEOFFS_AFFECT_HOLIDAY_PTO = new ArrayList<String>();
	static {
		TIMEOFFS_AFFECT_HOLIDAY_PTO.add(T2Constants.HOL_FMLA);
		TIMEOFFS_AFFECT_HOLIDAY_PTO.add(T2Constants.INJURY_HOLIDAY);
		TIMEOFFS_AFFECT_HOLIDAY_PTO.add(T2Constants.MILITARY_CAREGIVER_HOLIDAY);
		TIMEOFFS_AFFECT_HOLIDAY_PTO.add(T2Constants.AdminLeaveCodes.TERM_BALANCE_HOL);
	}

	public static List<String> displayPtoCodes = new ArrayList<String>();
	static {
		displayPtoCodes.add(0, T2Constants.PTO_CODE);
		displayPtoCodes.add(1, T2Constants.SICK_CODE);
		displayPtoCodes.add(2, T2Constants.HOLIDAY_CODE);
		displayPtoCodes.add(3, T2Constants.HONORARY_CODE);
	}

	public static class BatchWebConstants {
		public static String SCHEDULED_TRIGGER = "_scheduledTrigger";
		public static String SCHEDULED_TRIGGER_GROUP = "scheduledTriggerGroup";
		public static String SCHEDULED_JOB_GROUP = "scheduledJobGroup";
		public static String RUN_NOW__JOB_GROUP = "runNowJobGroup";
		public static String RUN_NOW_TRIGGER = "_runNowTrigger";
		public static String RUN_NOW_TRIGGER_GROUP = "runNowTriggerGroup";
	}


	public static String ACTIVE_DOCUMENT_STATUS = "Approved";

	public static class ConfigSettings {
		public static final String SESSION_TIMEOUT = "session.timeout";
	}

	public static class ConfigProperties{
		public static final String WORKFLOW_SUBMISSION_CHECK = "WORKFLOW_SUBMISSION_CHECK";
	}

	public static class ErrorKeys {
		public static final String DATE_RANGE_KEYS = "dateRangeValidation";
		public static final String ACCRUAL_KEYS = "accrualKeys";
	}

	public static class EptoSecurity{

		public static final String EPTO_USER_SAL_ADMIN_PLAN = "PAE";
		//View only can not be a substring of the global view only!!!
		//!!! DO NOT RENAME TO GLOBAL_VIEW_ONLY !!!
		public static final String GLOBAL_VIEWONLY_ROLENAME = "GLOBAL_VIEWONLY";
		//!!! DO NOT RENAME TO GLOBAL_EPTOADMIN !!!
		public static final String GLOBAL_EPTOADMIN_ROLENAME = "GLOBAL_EPTOADMIN";

		public static final String VIEW_ONLY_ROLENAME = "VIEW_ONLY";
		public static final String SYSTEM_ADMINISTRATOR_ROLENAME = "SYSTEM_ADMINISTRATOR";
		public static final String EPTO_ADMINISTRATOR_ROLENAME = "EPTO_ADMINISTRATOR";
		public static final String SUPERVISOR_ROLENAME = "SUPERVISOR";
		public static final String SUPERVISOR_FOR_EMPLOYEE_ROLENAME = "EMPLOYEE_SUP";
		public static final String DELEGATE_ROLENAME = "DELEGATE";
		public static final String USER_ROLENAME = "USER";
		public static final String CALENDAR_EDITOR = "CALENDAR_EDITOR";

		// if there is any new role, add to the role(s) to the list below so that the new role(s) will show up in the drop down menu in the role maintenance screen
		public static List<String> EPTO_ROLES = new ArrayList<String>();
		static {
			EPTO_ROLES.add(GLOBAL_VIEWONLY_ROLENAME);
			EPTO_ROLES.add(VIEW_ONLY_ROLENAME);
			EPTO_ROLES.add(SYSTEM_ADMINISTRATOR_ROLENAME);
			EPTO_ROLES.add(EPTO_ADMINISTRATOR_ROLENAME);
			EPTO_ROLES.add(GLOBAL_EPTOADMIN_ROLENAME);
			EPTO_ROLES.add(SUPERVISOR_ROLENAME);
			EPTO_ROLES.add(DELEGATE_ROLENAME);
			EPTO_ROLES.add(USER_ROLENAME);
		}

	}

	public static class EptoDisplay{

		public static final Map<String, String> WIDTH = new LinkedHashMap<String, String>();
		static {
			WIDTH.put("TINY", "grid-950.css");
			WIDTH.put("SMALL", "grid-1094.css");
			WIDTH.put("MEDIUM", "grid-1190.css");
			WIDTH.put("LARGE", "grid-1382.css");
			WIDTH.put("JUMBO", "grid-1598.css");
		}
	}

	public static class EptoDocumentStatus {
		public static final Map<String, String> DOCUMENT_STATUS = new HashMap<String, String>();
		static {
			DOCUMENT_STATUS.put("ALL","ALL");
			DOCUMENT_STATUS.put("F","Approved");
			DOCUMENT_STATUS.put("R","Route");
		}
	}

	public static class EptoConfig {
		public static final String MAXIMUM_PTO_BALANCE_TRANSFER = "MAXIMUM_TRANSFER";
		public static final String DEFAULT_PTO_BALANCE_TRANSFER = "DEFAULT_TRANSFER";
		public static final String TEST_EMAIL_NOTIFICATION = "test.email.notification";
		public static final String EMAIL_NOTIFICATION = "email.notification";
		public static final String NOTIFICATIONS_ON = "notifications.on";
		public static final String MAIL_USER = "user_mail";
		public static final String MAIL_PWD = "user_pwd";

	}

	public static class BalanceTransfer {
		public static final String BALANCE_TRANSFER_DESCRIPTION = "Balance Transfer";
		public static final String MAX_CARRYOVER_DEDUCTION_DESCRIPTION = "Max Carryover Deduction";
	}

	public static class FMLALeaveCodes {
		public static final List<String> FMLALeaveCodes = new ArrayList<String>();
		static{
			FMLALeaveCodes.add(T2Constants.ABE_FMLA);
			FMLALeaveCodes.add(T2Constants.HOL_FMLA);
			FMLALeaveCodes.add(T2Constants.PTO_FMLA);
			FMLALeaveCodes.add(T2Constants.SICK_FMLA);
			FMLALeaveCodes.add(T2Constants.FMLA_HONORARY_CODE);
			FMLALeaveCodes.add(T2Constants.FMLA_MIL_CARE_ABS);
			FMLALeaveCodes.add(T2Constants.FMLA_MIL_CARE_HON);
			FMLALeaveCodes.add(T2Constants.FMLA_MIL_CARE_PTO);
			FMLALeaveCodes.add(T2Constants.FMLA_MIL_CARE_SCK);
		}
	}


	public static class FMLALeaveCodesMIL {
		public static final List<String> FMLALeaveCodesMIL = new ArrayList<String>();
		static{
			FMLALeaveCodesMIL.add(T2Constants.FMLA_MIL_CARE_ABS);
			FMLALeaveCodesMIL.add(T2Constants.FMLA_MIL_CARE_HON);
			FMLALeaveCodesMIL.add(T2Constants.FMLA_MIL_CARE_PTO);
			FMLALeaveCodesMIL.add(T2Constants.FMLA_MIL_CARE_SCK);
		}
		public static String getFMLACodes(){
			StringBuilder sb = new StringBuilder();
			for(String fmlaCode : FMLALeaveCodesMIL){
				sb.append("'"+fmlaCode + "',");
			}
			return StringUtils.isNotBlank(sb.toString()) ? StringUtils.removeEnd(sb.toString(),",") : "";
		}
	}

	public static class InjuryLeaveCodes {
		public static final List<String> InjuryLeaveCodes = new ArrayList<String>();
		static{
			InjuryLeaveCodes.add(T2Constants.INJURY_HOLIDAY);
			InjuryLeaveCodes.add(T2Constants.INJURY_SEVEN_DAYS);
			InjuryLeaveCodes.add(T2Constants.PTO_INJURY_CODE);
			InjuryLeaveCodes.add(T2Constants.INJ_CHRG_HON);
			InjuryLeaveCodes.add(T2Constants.INJ_NO_PAY);
			InjuryLeaveCodes.add(T2Constants.INJ_NO_PAY_WITH_BEN);
			InjuryLeaveCodes.add(T2Constants.INJ_SICK);
		}
		public static String getInjuryCodes(){
			StringBuilder sb = new StringBuilder();
			for(String injuryCode : InjuryLeaveCodes){
				sb.append("'"+injuryCode + "',");
			}
			return StringUtils.isNotBlank(sb.toString()) ? StringUtils.removeEnd(sb.toString(),",") : "";
		}
	}

	public static class MilitaryLeaveCodes {
		public static final List<String> MilitaryLeaveCodes = new ArrayList<String>();
		static{
			MilitaryLeaveCodes.add(T2Constants.FAM_MILITARY_CODE);
			MilitaryLeaveCodes.add(T2Constants.MILITARY_C0DE);
			MilitaryLeaveCodes.add(T2Constants.MILITARY_CAREGIVER_HOLIDAY);
			MilitaryLeaveCodes.add(T2Constants.MIL_CODE);
		}
		public static String getMilitaryCodes(){
			StringBuilder sb = new StringBuilder();
			for(String milCode : MilitaryLeaveCodes){
				sb.append("'"+milCode + "',");
			}
			return StringUtils.isNotBlank(sb.toString()) ? StringUtils.removeEnd(sb.toString(),",") : "";
		}
	}

	public static class AdminLeaveCodes {
		public static final String TERM_BALANCE_PTO = "TBP";
		public static final String TERM_BALANCE_SCK = "TBS";
		public static final String TERM_BALANCE_HOL = "TBH";
		public static final String TERM_BALANCE_HON = "TBN";

		public static final List<String> AdminLeaveCodes = new ArrayList<String>();
		static{
			AdminLeaveCodes.add(TERM_BALANCE_HOL);
			AdminLeaveCodes.add(TERM_BALANCE_HON);
			AdminLeaveCodes.add(TERM_BALANCE_PTO);
			AdminLeaveCodes.add(TERM_BALANCE_SCK);
		}
		public static String getAdminCodes(){
			StringBuilder sb = new StringBuilder();
			for(String adminCode : AdminLeaveCodes){
				sb.append("'"+adminCode+"',");
			}
			return StringUtils.isNotBlank(sb.toString()) ? StringUtils.removeEnd(sb.toString(), ","): "";
		}
	}

	public static Set<String> NO_ACCRUAL_STATUSES = new HashSet<String>();
	static {
		NO_ACCRUAL_STATUSES.add("T");
		NO_ACCRUAL_STATUSES.add("R");
		NO_ACCRUAL_STATUSES.add("D");
	}

	public static String JOB_ACTIVE_INDICATOR = "A";

	public static List<String> ACCRUAL_CAT_OPTIONS = new ArrayList<String>();
	static {
		ACCRUAL_CAT_OPTIONS.add("PTO");
		ACCRUAL_CAT_OPTIONS.add("SICK");
		ACCRUAL_CAT_OPTIONS.add("HON");
		ACCRUAL_CAT_OPTIONS.add("OTHER");
		ACCRUAL_CAT_OPTIONS.add("HOL");
	}

	public static List<String> CAMPUS_LIST = new ArrayList<String>();
	static {
		CAMPUS_LIST.add("BL");
		CAMPUS_LIST.add("EA");
		CAMPUS_LIST.add("FW");
		CAMPUS_LIST.add("IN");
		CAMPUS_LIST.add("KO");
		CAMPUS_LIST.add("NW");
		CAMPUS_LIST.add("SB");
		CAMPUS_LIST.add("SE");
	}

	public static Map<String, String> JOB_STATUS_LABEL_MAP = new HashMap<String, String>();
	static {
		JOB_STATUS_LABEL_MAP.put("A", "Active");
		JOB_STATUS_LABEL_MAP.put("D", "Deceased");
		JOB_STATUS_LABEL_MAP.put("L", "Leave");
		JOB_STATUS_LABEL_MAP.put("P", "Partial Leave");
		JOB_STATUS_LABEL_MAP.put("R", "Retired");
		JOB_STATUS_LABEL_MAP.put("S", "Suspended");
		JOB_STATUS_LABEL_MAP.put("T", "Terminated");
		JOB_STATUS_LABEL_MAP.put("W", "W");
	}

	public static String EPTO_FORM = "epto_form";
	public static String REPORT_TABLE_IP = "hierarchy.write.ip";

	public static class FieldNames {
		public static final String FROM_DATE = "fromDate";
		public static final String TO_DATE = "toDate";
		public static final String EFFECTIVE_DATE = "effectiveDate";
		public static final String EXPIRATION_DATE = "expirationDate";
	}

}
