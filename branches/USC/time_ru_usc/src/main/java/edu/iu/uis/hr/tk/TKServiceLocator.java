package edu.iu.uis.hr.tk;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.backportconcurrent.ScheduledExecutorFactoryBean;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.job.service.HolidayService;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.authorization.TimeAuthorizationService;
import edu.iu.uis.hr.tk.batch.web.service.BatchJobService;
import edu.iu.uis.hr.tk.config.TKConfigurationService;
import edu.iu.uis.hr.tk.department.service.DepartmentService;
import edu.iu.uis.hr.tk.directory.service.RolesService;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.employee.service.EmployeeService;
import edu.iu.uis.hr.tk.extract.service.PayrollExtractService;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.report.dataaccess.BatchMessageDataAccess;
import edu.iu.uis.hr.tk.report.service.BatchMessageService;
import edu.iu.uis.hr.tk.rule.service.ClockLocationService;
import edu.iu.uis.hr.tk.rule.service.RuleService;
import edu.iu.uis.hr.tk.service.TranslateService;
import edu.iu.uis.hr.tk.service.UserPreferenceService;
import edu.iu.uis.hr.tk.timesheet.dataaccess.ClockLogDataAccess;
import edu.iu.uis.hr.tk.timesheet.dataaccess.DocumentLockDataAccess;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class TKServiceLocator {

	public static TranslateService getTranslateService() {
		return (TranslateService) Context.getService(TranslateService.class);
	}

	public static TimesheetService getTimesheetService() {
		return (TimesheetService) Context.getApplicationContext().getBean("timesheetService");
	}

	public static WebUserService getWebUserService() {
		return (WebUserService) Context.getApplicationContext().getBean("webUserService");
	}

	public static DirectoryService getDirectoryService() {
		return (DirectoryService) Context.getService(DirectoryService.class);
	}

	public static AbstractService getAbstractService() {
		return (AbstractService) Context.getService(AbstractService.class);
	}

	public static AssignmentService getAssignmentService() {
		return (AssignmentService) Context.getService(AssignmentService.class);
	}

	public static PayCalendarService getPayCalendarService() {
		return (PayCalendarService) Context.getService(PayCalendarService.class);
	}

	public static EmailService getEmailService() {
		return (EmailService) Context.getService(EmailService.class);
	}

	public static RuleService getRuleService() {
		return (RuleService) Context.getApplicationContext().getBean("ruleService");
	}

	public static ScheduledExecutorFactoryBean getKSBServiceBean() {
		return (ScheduledExecutorFactoryBean) Context.getApplicationContext().getBean("ksbScheduler");
	}

	//	public static BatchRunnerService getBatchRunnerService() {
	//		return (BatchRunnerService) Context.getApplicationContext().getBean("batchRunnerService");
	//	}

	public static BatchJobService getBatchJobService() {
		return (BatchJobService) Context.getApplicationContext().getBean("batchJobService");
	}

	public static DocumentLockDataAccess getDocumentLockDataAccess() {
		return (DocumentLockDataAccess) Context.getApplicationContext().getBean("documentLockDataAccess");
	}

	public static ClockLogDataAccess getClockLogDataAccess() {
		return (ClockLogDataAccess) Context.getApplicationContext().getBean("clockLogDataAccess");
	}

	public static EmployeeService getEmployeeService() {
		return (EmployeeService) Context.getApplicationContext().getBean("employeeService");
	}

	public static UserPreferenceService getUserPreferenceService() {
		return (UserPreferenceService) Context.getApplicationContext().getBean("userPreferenceService");
	}

	public static RolesService getRolesService() {
		return (RolesService) Context.getApplicationContext().getBean("rolesService");
	}

	public static JobService getJobService() {
		return (JobService) Context.getApplicationContext().getBean("tkJobService");
	}

	public static JdbcTemplate getTkJdbcTemplate() {
		return (JdbcTemplate) Context.getApplicationContext().getBean("tkJdbcTemplate");
	}

	public static JdbcTemplate getEnJdbcTemplate() {
		return (JdbcTemplate) Context.getApplicationContext().getBean("enJdbcTemplate");
	}

	public static JdbcTemplate getNontransactionDSJdbcTemplate() {
		return (JdbcTemplate) Context.getApplicationContext().getBean("tkNonTransJdbcTemplate");
	}

	public static TKConfigurationService getConfigService() {
		return (TKConfigurationService) Context.getApplicationContext().getBean("configurationService");
	}

	public static EarningService getEarningService() {
		return (EarningService) Context.getApplicationContext().getBean("earningService");
	}

	public static TimeAuthorizationService getTimeAuthorizationService() {
		return (TimeAuthorizationService) Context.getApplicationContext().getBean("timeAuthorizationService");
	}

	public static DataSource getDataSource(String name) {
		return (DataSource) Context.getApplicationContext().getBean(name);
	}

	public static DepartmentService getDepartmentService() {
		return (DepartmentService) Context.getApplicationContext().getBean("departmentService");
	}

	public static HolidayService getHolidayService() {
		return (HolidayService) Context.getApplicationContext().getBean("holidayService");
	}
	
	public static edu.iu.uis.hr.job.service.JobService getHrJobService() {
		return (edu.iu.uis.hr.job.service.JobService) Context.getApplicationContext().getBean("jobService");
	}
	
	public static PayrollExtractService getPayrollExtractService() {
		return (PayrollExtractService)Context.getApplicationContext().getBean("payrollExtractService");
	}
	
	public static ClockLocationService getClockLocationService() {
		return (ClockLocationService)Context.getApplicationContext().getBean("clockLocationService");
	}
	
	public static BatchMessageDataAccess getBatchMessageDataAccess() {
		return (BatchMessageDataAccess) Context.getApplicationContext().getBean("batchMessageDataAccess");
	}
	
	public static BatchMessageService getBatchMessageService() {
		return (BatchMessageService) Context.getApplicationContext().getBean("batchMessageService");
	}
	
}
