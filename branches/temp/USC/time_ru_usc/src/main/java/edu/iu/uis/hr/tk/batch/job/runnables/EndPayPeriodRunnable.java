package edu.iu.uis.hr.tk.batch.job.runnables;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class EndPayPeriodRunnable extends AbstractTKBatchRunnable {

	private static final long serialVersionUID = 3104696208508641734L;
	private static final Logger LOG = Logger.getLogger(EndPayPeriodRunnable.class);

	private static final BigDecimal ELEVEN = new BigDecimal(11);
	private static final BigDecimal TWELVE = new BigDecimal(12);
	private static final BigDecimal FIFTYNINE = new BigDecimal(59);
	private static final BigDecimal ZERO = new BigDecimal(0);
	private static final String PM = "PM";
	private static final String AM = "AM";
	
	private ClockLog clockLog;
	
	public EndPayPeriodRunnable(ClockLog clockLog) {
		this.clockLog = clockLog;
	}
	
	public void run() {
		ClockLog openClockLog = this.clockLog;
		Calendar cal = new GregorianCalendar();
		TimelessDate today = new TimelessDate(cal.getTime());
		PayCalendar payCalendar = new PayCalendar();
		payCalendar = TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new TimelessDate(new java.util.Date()).getDate());
		TimelessDate  endPayPeriod = new TimelessDate(payCalendar.getPayEndDate());
		
		boolean clockedInTimeExceeded = false;
		Timestamp currentTime = new TimedDate(new Date()).getTimestamp();
		if (Clock.ON_THE_CLOCK_CODES.contains(openClockLog.getAction())){
			if  (Timestamp.getMillisecondDifference(currentTime, openClockLog.getClockTime()).longValue() / 3600000 > Clock.MAX_CLOCK_IN_HOURS) {
				clockedInTimeExceeded = true;
			}
		}
		
		payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).getDate());
		TimesheetDocument timesheetDocument = TKServiceLocator.getTimesheetService().getTimesheetDocument(openClockLog.getUniversityId(), payCalendar.getPayEndDate());
		Timestamp clockTime = new Timestamp();
		clockTime.setDate(endPayPeriod.getDate());
		clockTime.setHour(ELEVEN);
		clockTime.setMinute(FIFTYNINE);
		clockTime.setSecond(ZERO);
		clockTime.setAmPm(PM);
		
		timesheetDocument.getClock().setClockTime(clockTime);
		if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.CLOCK_IN)) {
			TKServiceLocator.getTimesheetService().clockOut(timesheetDocument);
			LOG.info("Employee " + openClockLog.getUniversityId() + " (Clock-out)");
		} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_IN)) {
			TKServiceLocator.getTimesheetService().breakOut(timesheetDocument);
			LOG.info("Employee " + openClockLog.getUniversityId() + " (Break-out)");
		} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.LUNCH_IN)) {
			TKServiceLocator.getTimesheetService().lunchOut(timesheetDocument);
			LOG.info("Employee " + openClockLog.getUniversityId() + " (Lunch-out)");
		} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_OUT)) {
			TKServiceLocator.getTimesheetService().breakIn(timesheetDocument);
			TKServiceLocator.getTimesheetService().clockOut(timesheetDocument);
			LOG.info("Employee " + openClockLog.getUniversityId() + " (Break-in and clock-out)");
		}

		// checks whether the assignment is active

		clockTime.setHour(TWELVE);
		clockTime.setMinute(ZERO);
		clockTime.setSecond(ZERO);
		clockTime.setAmPm(AM);
		clockTime.setDate(today.getDate());

		if (!clockedInTimeExceeded && edu.iu.uis.hr.entity.logic.Utility.hasValue(timesheetDocument.getAssignment(openClockLog.getEmployeeRecord(), openClockLog.getWorkArea(), openClockLog.getTask(), clockTime))) {
			timesheetDocument.getClock().setClockTime(clockTime);
			if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.CLOCK_IN)) {
				TKServiceLocator.getTimesheetService().clockIn(timesheetDocument);
				LOG.info("Employee " + openClockLog.getUniversityId() + " (Clock-in)");
			} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_IN)) {
				TKServiceLocator.getTimesheetService().breakIn(timesheetDocument);
				LOG.info("Employee " + openClockLog.getUniversityId() + " (Break-in)");
			} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.LUNCH_IN)) {
				TKServiceLocator.getTimesheetService().lunchIn(timesheetDocument);
				LOG.info("Employee " + openClockLog.getUniversityId() + " (Lunch-in)");
			} else if (StringUtils.equals(openClockLog.getAction(), edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_OUT)) {
				TKServiceLocator.getTimesheetService().clockIn(timesheetDocument);
				TKServiceLocator.getTimesheetService().breakOut(timesheetDocument);
				LOG.info("Employee " + openClockLog.getUniversityId() + " (Clock-in and break-out)");
			}
		}
	}
	
	
	public String getDataId() {
		return this.clockLog.getUniversityId();
	}

	public String getJobMessage() {
		return "";
	}






}
