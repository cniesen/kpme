package edu.iu.uis.hr.tk;

import org.apache.log4j.Logger;

public class TkPseudocacheRefresher {
	private static final Logger LOG = Logger.getLogger(TkPseudocacheRefresher.class);
	
	public static boolean isJobRefresherStarted = false;
	public static boolean isRuleServiceRefresherStarted = false;
	public static boolean isWeeklyOvertimeRuleRefresherStarted = false;
	
	private static long jobCacheRefreshInterval = 1000 * 60 * 15;
	private static long ruleServiceCacheRefreshInterval = 1000 * 60 * 60 * 24;
	private static long weeklyOvertimeRuleCacheRefreshInterval = 1000 * 60 * 60 * 24;
    
//	public static synchronized void refreshJobCache() {
//		Timer t = new Timer();
//		t.scheduleAtFixedRate(new TimerTask() { 
//			public void run() {
//				LOG.info("Started clearing Job Cache in tk...");
//				JobService.JOB_MAP.clear();
//				JobService.JOBS_BY_DATE_WORKAREA_MAP.clear();
//				JobService.JOBS_BY_DATE_WORKAREA_TASKID_MAP.clear();
//				JobService.JOBS_WITH_ASSIGNMENTS_MAP.clear();
//				JobService.TPMEL_JOBS_MAP.clear();
//				LOG.info("Finished clearing Job Cache in tk...");
//			}
//		}, 1000, getJobCacheRefreshInterval());
//	}
	
//	public static synchronized void refreshRuleServiceCache() {
//		Timer t = new Timer();
//		t.scheduleAtFixedRate(new TimerTask() { 
//			public void run() {
//				LOG.info("Started clearing RuleService Cache in tk...");
//				RuleService.SHIFT_DIFFERENTIAL_RULES_BY_EARNCODE_DATE_MAP.clear();
//				RuleService.SHIFT_DIFFERENTIAL_RULES_BY_PAYCALENDAR_MAP.clear();
//				RuleService.TIME_COLLECTION_RULE_MAP.clear();
//				RuleService.SYSTEM_LUNCH_RULE_MAP.clear();
//				LOG.info("Finished clearing RuleService Cache in tk...");
//			}
//		}, 1000, getRuleServiceCacheRefreshInterval());
//	}

//	public static synchronized void refreshWeeklyOvertimeRuleCache() {
//		Timer t = new Timer();
//		t.scheduleAtFixedRate(new TimerTask() { 
//			public void run() {
//				LOG.info("Started clearing WeeklyOvertimeRule Cache in tk...");
//				TimesheetService.WEEKLY_OVERTIME_RULE_MAP.clear();
//				LOG.info("Finished clearing WeeklyOvertimeRule Cache in tk...");
//			}
//		}, 1000, getWeeklyOvertimeRuleCacheRefreshInterval());
//	}

	public static long getJobCacheRefreshInterval() {
		return jobCacheRefreshInterval;
	}

	public static void setJobCacheRefreshInterval(long jobCacheRefreshInterval) {
		TkPseudocacheRefresher.jobCacheRefreshInterval = jobCacheRefreshInterval;
	}

	public static long getRuleServiceCacheRefreshInterval() {
		return ruleServiceCacheRefreshInterval;
	}

	public static void setRuleServiceCacheRefreshInterval(long ruleServiceCacheRefreshInterval) {
		TkPseudocacheRefresher.ruleServiceCacheRefreshInterval = ruleServiceCacheRefreshInterval;
	}

	public static long getWeeklyOvertimeRuleCacheRefreshInterval() {
		return weeklyOvertimeRuleCacheRefreshInterval;
	}

	public static void setWeeklyOvertimeRuleCacheRefreshInterval(
			long weeklyOvertimeRuleCacheRefreshInterval) {
		TkPseudocacheRefresher.weeklyOvertimeRuleCacheRefreshInterval = weeklyOvertimeRuleCacheRefreshInterval;
	}
}
