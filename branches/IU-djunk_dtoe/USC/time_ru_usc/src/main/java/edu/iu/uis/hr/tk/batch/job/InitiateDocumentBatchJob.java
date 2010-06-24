package edu.iu.uis.hr.tk.batch.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.TKBatchJobPopulator;
import edu.iu.uis.hr.tk.batch.TKBatchRunnable;
import edu.iu.uis.hr.tk.batch.job.runnables.InitiateBatchJobRunnable;
import edu.iu.uis.hr.tk.employee.entity.Employee;
import edu.iu.uis.hr.tk.util.TkConstants;

public class InitiateDocumentBatchJob extends TKBatchJobPopulator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3608482403386555891L;
	private static final Logger LOG = Logger.getLogger(InitiateDocumentBatchJob.class);
	public static final int DAYS_TO_PAY_PERIOD_TO_INITIATE = 14;
//	private static final String MAIL_SUBJECT = "TIME: Initiate Document Batch Job";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TKBatchRunnable> getTKBatchRunnables() {
		LOG.info("Executing InitiateDocumentBatchJob");
		PayCalendar payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).addDays(DAYS_TO_PAY_PERIOD_TO_INITIATE).getDate());
		List<Employee> activeEmployees = (List<Employee>)TKServiceLocator.getEmployeeService().getActiveEmployees(payCalendar);
		
		if (!Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())){
			activeEmployees = activeEmployees.subList(1400, 1500); //initializing a subset for testing environments	
		}
		
		LOG.info("Processing InitiateDocumentBatchJob " + activeEmployees.size() + " documents");
		try {
			sendKickOffEmail(activeEmployees.size());
		}catch (Exception e){
			LOG.debug("Error sending email from " + getName() + ": " + e);
		}		
		
		List<TKBatchRunnable> tkbatchRunnables = new ArrayList<TKBatchRunnable>();
		for (Employee employee : activeEmployees) {
			tkbatchRunnables.add(new InitiateBatchJobRunnable(employee));
		}
		
		return tkbatchRunnables;
	}
	
	@Override
	public String getName() {
		return "InitiateDocumentBatchJob";
	}
	
	@Override
	public String getType() {
		return TkConstants.BatchJobTypes.Employee;
	}

	
	
//	public InitiateDocumentBatchJob() {
//		setName("InitiateDocumentBatchJob");
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	protected BatchJobDescription setupJobs() {
//		LOG.info("Executing InitiateDocumentBatchJob");
//		PayCalendar payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).addDays(DAYS_TO_PAY_PERIOD_TO_INITIATE).getDate());
//		List activeEmployees = getEmployeeService().getActiveEmployees(payCalendar);
//		
//		//activeEmployees = activeEmployees.subList(0, 100);
//		LOG.info("Processing InitiateDocumentBatchJob " + activeEmployees.size() + " documents");
//		sendKickOffEmail(activeEmployees.size());
//		return new BatchJobDescription(getName(), new Timestamp(System.currentTimeMillis()), null, activeEmployees);
//	}
//
//	public static class InitiateDocumentBatchRunnable extends BatchRunnable {
//		private static final long serialVersionUID = -6869509996689988316L;
//
//		@Override
//		protected void doWork() {
//			Employee employee = (Employee) getObject();
//			LOG.info("initiating document for employee " + employee.getUniversityId());
//			PayCalendar payCalendar = new PayCalendar();
//
//			// initiates documents for current pay period instead of next
//			if (StringUtils.equals(ConfigContext.getCurrentContextConfig().getProperty("batch.current.payPeriod"), "true")) {
//				payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).getDate());
//			} else {
//				payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).addDays(DAYS_TO_PAY_PERIOD_TO_INITIATE).getDate());
//			}
//			TimesheetInitiateKey initiateKey = new TimesheetInitiateKey();
//			initiateKey.setUniversityId(employee.getUniversityId());
//			initiateKey.setPayEndDate(payCalendar.getPayEndDate());
//			TimesheetDocument td = (TimesheetDocument) TKServiceLocator.getTimesheetService().initiate(initiateKey);
//			LOG.info("created docId: " + td.getDocumentId() + " for employee " + employee.getUniversityId());
//		}
//
//		@Override
//		protected String getDataId() {
//			return ((Employee) getObject()).getUniversityId();
//		}
//	}
//
//	@Override
//	protected String getType() {
//		return TkConstants.BatchJobTypes.Employee;
//	}
//	
//	@Override
//	protected BatchRunnable getBatchRunnable() {
//		return new InitiateDocumentBatchRunnable();
//	}

}
