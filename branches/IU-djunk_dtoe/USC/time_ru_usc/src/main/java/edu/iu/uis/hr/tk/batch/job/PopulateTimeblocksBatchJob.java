package edu.iu.uis.hr.tk.batch.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.TKBatchJobPopulator;
import edu.iu.uis.hr.tk.batch.TKBatchRunnable;
import edu.iu.uis.hr.tk.batch.job.runnables.PopulateTimeblocksBatchJobRunnable;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.util.TkConstants;

public class PopulateTimeblocksBatchJob extends TKBatchJobPopulator {

	private static final long serialVersionUID = -1425964164769489737L;
	private static final Logger LOG = Logger
			.getLogger(PopulateTimeblocksBatchJob.class);
	public static final int DAYS_TO_PAY_PERIOD_TO_INITIATE = 14;

	@Override
	public String getName() {
		return "PopulateTimeblocksBatchJob";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKBatchRunnable> getTKBatchRunnables() {
		if (Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
			// do nothing if we're in PRD
			return null;
		}
		LOG.info("Executing PopulateTimeblocksBatchJob");
		PayCalendar payCalendar = TKServiceLocator.getPayCalendarService()
				.getPayCalendar(new Date());
		List<DocumentHeader> documentHeaders = TKServiceLocator
				.getTimesheetService().geDocumentHeaders(
						payCalendar.getPayEndDate());

		List<TKBatchRunnable> tkbatchRunnables = new ArrayList<TKBatchRunnable>();
		for (DocumentHeader documentHeader : documentHeaders) {
			tkbatchRunnables.add(new PopulateTimeblocksBatchJobRunnable(
					documentHeader.getDocumentId()));
		}

		return tkbatchRunnables;
	}

	@Override
	public String getType() {
		return TkConstants.BatchJobTypes.Document;
	}

	// public PopulateTimeblocksBatchJob() {
	// setName("PopulateTimeblocksBatchJob");
	// }
	//
	// @SuppressWarnings("unchecked")
	// @Override
	// protected BatchJobDescription setupJobs() {
	// if (Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
	// // do nothing if we're in PRD
	// return null;
	// }
	// LOG.info("Executing PopulateTimeblocksBatchJob");
	// PayCalendar payCalendar =
	// TKServiceLocator.getPayCalendarService().getPayCalendar(new Date());
	// List documentHeaders =
	// TKServiceLocator.getTimesheetService().geDocumentHeaders(payCalendar.getPayEndDate());
	// sendKickOffEmail(documentHeaders.size());
	// return new BatchJobDescription(getName(), new
	// java.sql.Timestamp(System.currentTimeMillis()), null, documentHeaders);
	// }
	//
	// public static class PopulateTimeblocksBatchRunnable extends BatchRunnable
	// {
	// private static final long serialVersionUID = -6869509996689988316L;
	//
	// @SuppressWarnings("unchecked")
	// @Override
	// protected void doWork() {
	// String documentId = (String) getObject();
	// TimesheetDocument td = (TimesheetDocument)
	// TKServiceLocator.getTimesheetService().getTimesheetDocument(documentId);
	//
	// for (Iterator it = td.getJobs().iterator(); it.hasNext();) {
	// Job job = (Job) it.next();
	// //if (!job.isHourly() && job.getAssignment(0).getWorkArea() != null) {
	// populateTimeblocks(td.getDocumentId(), td.getHours().getPayCalendar(),
	// job);
	// return; // no need to process more than one BW1 job
	// //}
	// }
	// }
	//
	// @Override
	// protected String getDataId() {
	// return (String) getObject();
	// }
	//		
	// public void populateTimeblocks(String docId, PayCalendar payCalendar,
	// Job job) {
	// if (!Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
	// String earnCode = "RGH";
	// for (int i = 0; i < 13; i++) {
	//
	// if (i != 0 && i != 6 && i != 7) {
	// Date dateInPayPeriod = (Date) payCalendar
	// .getDatesInPayPeriod().get(i);
	// TimedDate ci = new TimedDate(new TimelessDate(
	// dateInPayPeriod).toString(), "8", "0", "AM");
	// TimedDate co = new TimedDate(new TimelessDate(
	// dateInPayPeriod).toString(), "4", "0", "PM");
	//
	// TimeBlock timeBlock = new TimeBlock();
	// timeBlock.setDocumentId(docId);
	// timeBlock.setUserUniversityId(job.getUniversityId());
	// timeBlock.setEmployeeRecord(job.getEmployeeRecord());
	// timeBlock.setWorkArea(job.getAssignment(0)
	// .getWorkArea());
	// timeBlock.setTask(job.getAssignment(0).getTask());
	// timeBlock.setEarnCode(earnCode);
	// timeBlock.setBeginTime(ci.getTimestamp());
	// timeBlock.setEndTime(co.getTimestamp());
	// timeBlock.setTimestamp(co.getTimestamp());
	// timeBlock.setBeginTsTz("EST");
	// timeBlock.setEndTsTz("EST");
	// timeBlock.setTimestampTz("EST");
	//
	// Long diff = Timestamp.getMillisecondDifference(co
	// .getTimestamp(), ci.getTimestamp());
	// BigDecimal hours = new BigDecimal((diff / 3600000) % 24);
	// timeBlock.setHours(hours);
	//
	// TKServiceLocator.getTimesheetService()
	// .addTimeBlockManually(timeBlock);
	// }
	// }
	// }
	// }
	//		
	// }
	//
	//
	//
	// @Override
	// protected BatchRunnable getBatchRunnable() {
	// return new PopulateTimeblocksBatchRunnable();
	// }
	//
	// @Override
	// protected String getType() {
	// return TkConstants.BatchJobTypes.Document;
	// }
}
