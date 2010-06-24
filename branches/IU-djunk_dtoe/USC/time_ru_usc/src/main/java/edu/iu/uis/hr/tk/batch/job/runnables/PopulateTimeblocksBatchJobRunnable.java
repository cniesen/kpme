package edu.iu.uis.hr.tk.batch.job.runnables;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class PopulateTimeblocksBatchJobRunnable extends AbstractTKBatchRunnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8623708546366836424L;
	private String documentId;
	
	public PopulateTimeblocksBatchJobRunnable(String documentId) {
		this.documentId = documentId;
	}

	public void run() {
		
		TimesheetDocument td = (TimesheetDocument) TKServiceLocator.getTimesheetService().getTimesheetDocument(documentId);

		for (Iterator it = td.getJobs().iterator(); it.hasNext();) {
			Job job = (Job) it.next();
			//if (!job.isHourly() && job.getAssignment(0).getWorkArea() != null) {
				populateTimeblocks(td.getDocumentId(), td.getHours().getPayCalendar(), job);
				return; // no need to process more than one BW1 job
			//}
		}
	}
	
	public String getDataId() {
		return documentId;
	}

	
	public void populateTimeblocks(String docId, PayCalendar payCalendar,
			Job job) {
			if (!Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
				String earnCode = "RGH";
				for (int i = 0; i < 13; i++) {

					if (i != 0 && i != 6 && i != 7) {
						Date dateInPayPeriod = (Date) payCalendar
								.getDatesInPayPeriod().get(i);
						TimedDate ci = new TimedDate(new TimelessDate(
								dateInPayPeriod).toString(), "8", "0", "AM");
						TimedDate co = new TimedDate(new TimelessDate(
								dateInPayPeriod).toString(), "4", "0", "PM");

						TimeBlock timeBlock = new TimeBlock();
						timeBlock.setDocumentId(docId);
						timeBlock.setUserUniversityId(job.getUniversityId());
						timeBlock.setEmployeeRecord(job.getEmployeeRecord());
						timeBlock.setWorkArea(job.getAssignment(0)
								.getWorkArea());
						timeBlock.setTask(job.getAssignment(0).getTask());
						timeBlock.setEarnCode(earnCode);
						timeBlock.setBeginTime(ci.getTimestamp());
						timeBlock.setEndTime(co.getTimestamp());
						timeBlock.setTimestamp(co.getTimestamp());
						timeBlock.setBeginTsTz("EST");
						timeBlock.setEndTsTz("EST");
						timeBlock.setTimestampTz("EST");

						Long diff = Timestamp.getMillisecondDifference(co
								.getTimestamp(), ci.getTimestamp());
						BigDecimal hours = new BigDecimal((diff / 3600000) % 24);
						timeBlock.setHours(hours);

						TKServiceLocator.getTimesheetService()
								.addTimeBlockManually(timeBlock);
					}
				}
			}
		}

}
