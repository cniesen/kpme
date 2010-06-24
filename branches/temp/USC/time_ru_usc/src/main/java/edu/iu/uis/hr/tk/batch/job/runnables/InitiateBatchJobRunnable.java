package edu.iu.uis.hr.tk.batch.job.runnables;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.core.config.ConfigContext;

import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.employee.entity.Employee;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.TimesheetInitiateKey;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class InitiateBatchJobRunnable extends AbstractTKBatchRunnable {

	private static final long serialVersionUID = 528836894619565437L;
	private static final Logger LOG = Logger.getLogger(InitiateBatchJobRunnable.class);
	public static final int DAYS_TO_PAY_PERIOD_TO_INITIATE = 14;

	private Employee employee;

	public InitiateBatchJobRunnable(Employee employee) {
		this.employee = employee;
	}

	public void run() {
		try {
			Employee employee = this.employee;
			LOG.info("initiating document for employee " + employee.getUniversityId());
			PayCalendar payCalendar = new PayCalendar();

			// initiates documents for current pay period instead of next
			if (StringUtils.equals(ConfigContext.getCurrentContextConfig().getProperty("batch.current.payPeriod"), "true")) {
				payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).getDate());
			} else {
				payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).addDays(DAYS_TO_PAY_PERIOD_TO_INITIATE).getDate());
			}
			TimesheetInitiateKey initiateKey = new TimesheetInitiateKey();
			initiateKey.setUniversityId(employee.getUniversityId());
			initiateKey.setPayEndDate(payCalendar.getPayEndDate());
			TimesheetDocument td = (TimesheetDocument) TKServiceLocator.getTimesheetService().initiate(initiateKey);
			LOG.info("created docId: " + td.getDocumentId() + " for employee " + employee.getUniversityId());
		} catch (Throwable t) {
			handleException(t, BatchMessageSearchCriteria.DOCUMENT_INITIATION_PROCESS_NAME, new Date(), null, this.employee.getUniversityId());
		}

	}

	public String getDataId() {
		return employee.getUniversityId();
	}

	
}