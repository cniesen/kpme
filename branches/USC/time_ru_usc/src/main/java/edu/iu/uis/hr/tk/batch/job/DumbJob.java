package edu.iu.uis.hr.tk.batch.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.tk.batch.TKBatchJobPopulator;
import edu.iu.uis.hr.tk.batch.TKBatchRunnable;
import edu.iu.uis.hr.tk.batch.job.runnables.DumbJobRunnable;
import edu.iu.uis.hr.tk.util.TkConstants;


//Does nothing. Used by test cases
public class DumbJob extends TKBatchJobPopulator {
	
	private static final Logger LOG = Logger.getLogger(DumbJob.class);

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TKBatchRunnable> getTKBatchRunnables() {
		List<TKBatchRunnable> returnList = new ArrayList();
		for (int i = 0; i < 20; i++){
			returnList.add(new DumbJobRunnable());
		}
		return returnList;
	}
	
	
	@Override
	public String getType() {
		return TkConstants.BatchJobTypes.ClockLog;
	}

	@Override
	public String getName() {
		return "DumbJob";
	}
		
	
	
	
//	public EndPayPeriodTimeSheetBatchJob() {
//		setName("EndPayPeriodTimeSheetBatchJob");
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	protected BatchJobDescription setupJobs() {
//		LOG.info("Executing EndPayPeriodTimeSheetBatchJob ");
//		PayCalendar payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).getDate());
//		unlockDocumentsForPayPeriod(payCalendar);
//		List openClockLogs = TKServiceLocator.getClockLogDataAccess().getOpenClockLogs();
//		sendKickOffEmail(openClockLogs.size());
//		LOG.info("Processing EndPayPeriodTimeSheetBatch Job with " + openClockLogs.size() + " open clock logs");
//		return new BatchJobDescription(getName(), new java.sql.Timestamp(System.currentTimeMillis()), null, openClockLogs);
//	}
//

//
////	public static class EndPayPeriodTimeSheetBatchRunnable extends BatchRunnable {
////		private static final long serialVersionUID = -23392624306596763L;
////
////		
////		@Override
////		protected String getDataId() {
////			return ((ClockLog) getObject()).getUniversityId();
////		}
////
////
////		@Override
////		protected void doWork() {
////			
////			ClockLog openClockLog = (ClockLog) getObject();
////			Calendar cal = new GregorianCalendar();
////			TimelessDate today = new TimelessDate(cal.getTime());
////			PayCalendar payCalendar = new PayCalendar();
////			payCalendar = TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new TimelessDate(new java.util.Date()).getDate());
////			TimelessDate  endPayPeriod = new TimelessDate(payCalendar.getPayEndDate());
////			
////			boolean clockedInTimeExceeded = false;
////			Timestamp currentTime = new TimedDate(new Date()).getTimestamp();
////			if (Clock.ON_THE_CLOCK_CODES.contains(openClockLog.getAction())){
////				if  (Timestamp.getMillisecondDifference(currentTime, openClockLog.getClockTime()).longValue() / 3600000 > Clock.MAX_CLOCK_IN_HOURS) {
////					clockedInTimeExceeded = true;
////				}
////			}
////			
////			payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).getDate());
////			TimesheetDocument timesheetDocument = TKServiceLocator.getTimesheetService().getTimesheetDocument(openClockLog.getUniversityId(), payCalendar.getPayEndDate());
////			Timestamp clockTime = new Timestamp();
////			clockTime.setDate(endPayPeriod.getDate());
////			clockTime.setHour(ELEVEN);
////			clockTime.setMinute(FIFTYNINE);
////			clockTime.setSecond(ZERO);
////			clockTime.setAmPm(PM);
////			
////			timesheetDocument.getClock().setClockTime(clockTime);
////			if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.CLOCK_IN)) {
////				TKServiceLocator.getTimesheetService().clockOut(timesheetDocument);
////				LOG.info("Employee " + openClockLog.getUniversityId() + " (Clock-out)");
////			} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_IN)) {
////				TKServiceLocator.getTimesheetService().breakOut(timesheetDocument);
////				LOG.info("Employee " + openClockLog.getUniversityId() + " (Break-out)");
////			} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.LUNCH_IN)) {
////				TKServiceLocator.getTimesheetService().lunchOut(timesheetDocument);
////				LOG.info("Employee " + openClockLog.getUniversityId() + " (Lunch-out)");
////			} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_OUT)) {
////				TKServiceLocator.getTimesheetService().breakIn(timesheetDocument);
////				TKServiceLocator.getTimesheetService().clockOut(timesheetDocument);
////				LOG.info("Employee " + openClockLog.getUniversityId() + " (Break-in and clock-out)");
////			}
////
////			// checks whether the assignment is active
////
////			clockTime.setHour(TWELVE);
////			clockTime.setMinute(ZERO);
////			clockTime.setSecond(ZERO);
////			clockTime.setAmPm(AM);
////			clockTime.setDate(today.getDate());
////
////			if (!clockedInTimeExceeded && edu.iu.uis.hr.entity.logic.Utility.hasValue(timesheetDocument.getAssignment(openClockLog.getEmployeeRecord(), openClockLog.getWorkArea(), openClockLog.getTask(), clockTime))) {
////				timesheetDocument.getClock().setClockTime(clockTime);
////				if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.CLOCK_IN)) {
////					TKServiceLocator.getTimesheetService().clockIn(timesheetDocument);
////					LOG.info("Employee " + openClockLog.getUniversityId() + " (Clock-in)");
////				} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_IN)) {
////					TKServiceLocator.getTimesheetService().breakIn(timesheetDocument);
////					LOG.info("Employee " + openClockLog.getUniversityId() + " (Break-in)");
////				} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.LUNCH_IN)) {
////					TKServiceLocator.getTimesheetService().lunchIn(timesheetDocument);
////					LOG.info("Employee " + openClockLog.getUniversityId() + " (Lunch-in)");
////				} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_OUT)) {
////					TKServiceLocator.getTimesheetService().clockIn(timesheetDocument);
////					TKServiceLocator.getTimesheetService().breakOut(timesheetDocument);
////					LOG.info("Employee " + openClockLog.getUniversityId() + " (Clock-in and break-out)");
////				}
////			}
////		}
////	}
//
//	@Override
//	protected BatchRunnable getBatchRunnable() {
//		return new EndPayPeriodTimeSheetBatchRunnable();
//	}
//
//	@Override
//	protected String getType() {
//		return TkConstants.BatchJobTypes.ClockLog;
//	}
}
