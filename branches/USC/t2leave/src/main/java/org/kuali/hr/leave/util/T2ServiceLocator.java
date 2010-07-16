package org.kuali.hr.leave.util;

import org.kuali.hr.leave.calendar.services.CalendarService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class T2ServiceLocator implements ApplicationContextAware{

	public static String SPRING_BEANS = "classpath:SpringBeans.xml";
	public static ApplicationContext CONTEXT;

// TODO	public static final String DATASOURCE = "dataSource";
// TODO	public static final String PS_DATASOURCE = "psDataSource";
// TODO	public static final String EPTO_DATASOURCE = "eptoDataSource";
// TODO	public static final String SPRING_TRANSACTION_MANAGER = "transactionManager";
// TODO	public static final String EPTO_JDBC_TEMPLATE = "eptoTemplate";
// TODO	public static final String PS_JDBC_TEMPLATE = "psTemplate";
	public static final String CALENDAR_SERVICE = "calendarService";
// TODO	public static final String LEDGER_SERVICE = "ledgerService";
// TODO	public static final String USER_SERVICE = "userService";
// TODO	public static final String EPTO_CONFIG_SERVICE = "eptoConfigService";
// TODO	public static final String EPTO_BALANCE_TRANSFER_SERVICE = "eptoBalanceTransferService";
// TODO	public static final String EPTO_AUTHORIZATION_SERVICE = "eptoAuthService";
// TODO	public static final String POSITION_HIERARCHY_SERVICE = "positionService";
// TODO	public static final String EPTO_MAINTENANCE_PERSISTENCE_SERVICE_DIRECTORY = "maintenancePersistenceServiceDirectory";
// TODO	public static final String EPTO_WORKFLOW_SERVICE = "eptoWorkflowDocumentService";
// TODO	public static final String SCHED_TIME_OFF_PERSISTENCE_SERVICE = "schedTimeOffPersistenceService";
// TODO	public static final String EMPL_SCHED_TIME_OFF_PERSISTENCE_SERVICE = "emplSchedTimeOffPersistenceService";
// TODO	public static final String LEAVE_PLAN_PERSISTENCE_SERVICE = "leavePlanPersistenceService";
// TODO	public static final String LEAVE_CODE_PERSISTENCE_SERVICE = "leaveCodePersistenceService";
// TODO	public static final String HOLIDAY_SERVICE = "holidayService";
// TODO	public static final String ROLE_PERSISTENCE_SERVICE = "rolePersistenceService";
// TODO	public static final String BALANCE_TRANSFER_PERSISTENCE_SERVICE = "balanceTransferPersistenceService";
// TODO	public static final String ACCRUAL_CATEGORY_PERSISTENCE_SERVICE = "accrualCategoryPersistenceService";
// TODO	public static final String EMPL_OVERRIDE_PERSISTENCE_SERVICE = "emplOverridePersistenceService";
// TODO	public static final String EPTO_CALENDAR_WORKFLOW_SERVICE = "eptoCalendarWorkflowService";
// TODO	public static final String CALENDAR_DISPLAY_SERVICE = "calendarDisplayService";
// TODO	public static final String LEAVE_CODE_SERVICE = "leaveCodeService";
// TODO	public static final String REPORT_PERSISTENCE_SERVICE = "reportPersistenceService";
// TODO	public static final String EPTO_ACCRUAL_SERVICE = "accrualService";
// TODO	public static final String JOB_SERVICE = "jobService"	;
// TODO	public static final String EPTO_MONTLY_ACCRUAL_RATE_SERVICE = "monthlyAccrualRateService";
// TODO	public static final String SCHEDULED_TIME_OFF_SERVICE = "scheduledTimeOffService";
// TODO	public static final String BALANCE_CONTAINER_SERVICE = "balanceContainerService";
// TODO	public static final String EPTO_NOTIFICATION_SERVICE = "eptoNotificationService";
// TODO	public static final String POSITION_HIER_REPORT_SERVICE = "positionHierarchyReportService";

	public static void start() throws Exception {
//		CONTEXT = new ClassPathXmlApplicationContext(SPRING_BEANS);
//		CONTEXT.start();
	}
//
	public static void stop() throws Exception {
//		CONTEXT.stop();
	}

	// TODO -- add back other services when needed.

	public static CalendarService getCalendarService() {
		return (CalendarService)CONTEXT.getBean(CALENDAR_SERVICE);
	}

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
	    CONTEXT = arg0;
	}



}
