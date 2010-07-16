package org.kuali.hr.leave.calendar;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.kuali.hr.leave.util.T2Constants;

public class Month {

	private List<Day> days = new ArrayList<Day>();
	private int month;
	private Integer nextMonth;
	private Integer previousMonth;
	private int nextYear;
	private int previousYear;
	private int year;
	private int daysBeforeFirst;
	private int daysAfterLast;
	private String documentId;
	private String documentStatus;
	private boolean approvalRequested = false;
	private boolean yearEndTransferComplete = false;
	

	public boolean isYearEndTransferComplete() {
		return yearEndTransferComplete;
	}

	public void setYearEndTransferComplete(boolean yearEndTransferComplete) {
		this.yearEndTransferComplete = yearEndTransferComplete;
	}

	public int getNextYear() {
		return nextYear;
	}

	public void setNextYear(int nextYear) {
		this.nextYear = nextYear;
	}

	public int getPreviousYear() {
		return previousYear;
	}

	public void setPreviousYear(int previousYear) {
		this.previousYear = previousYear;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Integer getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	public Integer getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public String getName() {
		return T2Constants.MONTHS.get(this.getMonth());
	}

	public Integer getNextMonth() {
		return nextMonth;
	}

	public void setNextMonth(Integer nextMonth) {
		this.nextMonth = nextMonth;
	}

	public Integer getPreviousMonth() {
		return previousMonth;
	}

	public void setPreviousMonth(Integer previousMonth) {
		this.previousMonth = previousMonth;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public int getDaysBeforeFirst() {
		return daysBeforeFirst;
	}

	public void setDaysBeforeFirst(int daysBeforeFirst) {
		this.daysBeforeFirst = daysBeforeFirst;
	}

	public int getDaysAfterLast() {
		return daysAfterLast;
	}

	public void setDaysAfterLast(int daysAfterLast) {
		this.daysAfterLast = daysAfterLast;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}
	
	public Date getFirstDateOfMonth(){
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.YEAR, year);
		
		return new Date(cal.getTimeInMillis());
	}

	public boolean isApprovalRequested() {
		return approvalRequested;
	}

	public void setApprovalRequested(boolean approvalRequested) {
		this.approvalRequested = approvalRequested;
	}

}
