package org.kuali.hr.time.timeentry.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeCaptureRow {

	public TimeCaptureRow(java.util.Date startDate) {
		for (int i = 0; i < 14; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.DAY_OF_MONTH, i);
			dates[i] = new TimeCaptureDate(cal.getTime());
		}
	}

	private TimeCaptureDate[] dates = new TimeCaptureDate[14];

	private String approver;

	private Long assignment;

	private String timeType;

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApprover() {
		return approver;
	}

	public void setDates(TimeCaptureDate[] dates) {
		this.dates = dates;
	}

	public TimeCaptureDate[] getDates() {
		return dates;
	}

	public void setAssignment(Long assignment) {
		this.assignment = assignment;
	}

	public Long getAssignment() {
		return assignment;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getTimeType() {
		return timeType;
	}

	public class TimeCaptureDate {

		private final SimpleDateFormat DF = new SimpleDateFormat("MM/dd/yyyy");

		public TimeCaptureDate(java.util.Date date) {
			this.date = date;
		}

		private java.util.Date date;

		private String hours1;
		private String hours2;

		public String getFormattedDate() {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String nameOfDay = cal.getDisplayName(Calendar.DAY_OF_WEEK,
					Calendar.LONG, Locale.ENGLISH);
			return nameOfDay + "<br/>" + DF.format(date);
		}

		public void setDate(java.util.Date date) {
			this.date = date;
		}

		public java.util.Date getDate() {
			return date;
		}

		public void setHours1(String hours1) {
			this.hours1 = hours1;
		}

		public String getHours1() {
			return hours1;
		}

		public void setHours2(String hours2) {
			this.hours2 = hours2;
		}

		public String getHours2() {
			return hours2;
		}

	}
}
