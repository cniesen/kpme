package org.kuali.hr.leave.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.kuali.hr.leave.ledger.Ledger;

public class Day {
	private List<Ledger> ledgerItems;
	private int dayInMonth;
	private int dayInWeek;
	private Month month;
	
	
	
	public Day(){
		this.setLedgerItems(new ArrayList<Ledger>());
	}


	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}


	public int getDayInMonth() {
		return dayInMonth;
	}

	public void setDayInMonth(int dayInMonth) {
		this.dayInMonth = dayInMonth;
	}


	public int getDayInWeek() {
		return dayInWeek;
	}

	public void setDayInWeek(int dayInWeek) {
		this.dayInWeek = dayInWeek;
	}

	public int getTodayDayInMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public List<Ledger> getLedgerItems() {
		return ledgerItems;
	}

	public void setLedgerItems(List<Ledger> ledgerItems) {
		this.ledgerItems = ledgerItems;
	}

	public Date getDate() {
		return this.getCalendar().getTime();
	}
	
	public Calendar getCalendar() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(this.month.getYear(), this.month.getMonth(), this.getDayInMonth());
		return calendar;
	}
	
	public boolean isWeekDay() {
		return getCalendar().get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && getCalendar().get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;
	}


	@Override
	public String toString() {
		return this.getCalendar().getTime().toString();
	}

}
