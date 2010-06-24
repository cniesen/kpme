package edu.iu.uis.hr.tk.util;

public class TkConstants {
	public static class ConfigSettings {
		public static final String LOG_PERFORMANCE = "log.performance";
		public static final String LOG_PERFORM_TIME_IGNORE = "log.performance.time.ignore";
		public static final String LOG_PERFORM_SERVICE = "log.performance.services";
		public static final String LOG_LOG4J_PROPERTIES = "log4j.settings.path";
	}
	public static class BatchWebConstants {
		public static String SCHEDULED_TRIGGER = "_scheduledTrigger";
		public static String SCHEDULED_TRIGGER_GROUP = "scheduledTriggerGroup";
		public static String SCHEDULED_JOB_GROUP = "scheduledJobGroup";
		public static String RUN_NOW__JOB_GROUP = "runNowJobGroup";
		public static String RUN_NOW_TRIGGER = "_runNowTrigger";
		public static String RUN_NOW_TRIGGER_GROUP = "runNowTriggerGroup";
	}
	
	public static class BatchJobTypes{
		public static String Employee = "E";
		public static String ClockLog = "C";
		public static String Document = "D";
		public static String UserPref = "U";
	}
	
	
	public static final String SEND_BATCH_EMAIL = "send.batch.email";
	public static final String ALTERNATE_BATCH_EMAIL = "alternate.batch.email";
	
	// footprints changes
	public static final String FOOTPRINTS_INCIDENT_JSP = "/jsp/FootprintsIncident.jsp";
//	public static final String PROD_FOOTPRINTS_URL = "https://footprints-test.uits.iu.edu/forms/form_processor.php";
//	public static final String TEST_FOOTPRINTS_URL = "https://footprints-test.uits.iu.edu/forms/form_processor.php";
	
}
