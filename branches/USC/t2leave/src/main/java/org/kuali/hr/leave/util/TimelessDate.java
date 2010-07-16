package org.kuali.hr.leave.util;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.emory.mathcs.backport.java.util.Arrays;


public class TimelessDate implements Comparable, Serializable {

	private static final long serialVersionUID = 8045718700537473578L;
	public static final String DATE_FORMAT = new StringBuffer("MM").append("/").append("dd").append("/").append("yyyy").toString();
	public static final String DATE_FORMAT_START_WITH_YEAR = new StringBuffer("yyyy").append("-").append("MM").append("-").append("dd").toString();
	private static final String DAY_OF_WEEK_FORMAT = new StringBuffer("EEEE").toString();
	private static final String LONG_DATE_FORMAT = new StringBuffer("EEEE").append(",").append(" ").append("MMMM").append(" ").append("dd").append(",").append(" ").append("yyyy").toString();
	private static final int[] cumulDaysToMonth = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };

	private GregorianCalendar calendar;

	public TimelessDate() {
		calendar = new GregorianCalendar();
	}

	public TimelessDate(String dateString) {
		this();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			dateFormat.setLenient(false);
			getCalendar().setTime(dateFormat.parse(dateString));
		} catch (ParseException pe) {
			// throw new ExceptionAdapter(pe, LOG);
		}
	}

	public TimelessDate(Date date) {
		this();
		try {
			getCalendar().setTime(date);
		} catch (NullPointerException npe) {
			// throw new ExceptionAdapter(npe, LOG);
		}
	}

	public TimelessDate(java.sql.Date date) {
		this();
		try {
			getCalendar().setTime(date);
		} catch (NullPointerException npe) {
			// throw new ExceptionAdapter(npe, LOG);
		}
	}

	public TimelessDate(int month, int day, int year) {
		this();
		try {
			getCalendar().set(Calendar.MONTH, month);
			getCalendar().set(Calendar.DATE, day);
			getCalendar().set(Calendar.YEAR, year);
			getCalendar().set(Calendar.HOUR_OF_DAY, 0);
			getCalendar().set(Calendar.MINUTE, 0);
			getCalendar().set(Calendar.SECOND, 0);
			getCalendar().set(Calendar.MILLISECOND, 0);

		} catch (NullPointerException npe) {
		}
	}

	public int getMonth() {
		return calendar.get(GregorianCalendar.MONTH) + 1;
	}

	public int getDay() {
		return calendar.get(GregorianCalendar.DAY_OF_MONTH);
	}

	public int getYear() {
		return calendar.get(GregorianCalendar.YEAR);
	}

	public TimelessDate addMonths(int numberOfMonthsToAdd) {
		getCalendar().add(GregorianCalendar.MONTH, numberOfMonthsToAdd);
		return this;
	}

	public TimelessDate addDays(int numberOfDaysToAdd) {
		getCalendar().add(GregorianCalendar.DAY_OF_YEAR, numberOfDaysToAdd);
		return this;
	}

	public TimelessDate addYears(int numberOfYearsToAdd) {
		getCalendar().add(GregorianCalendar.DAY_OF_YEAR, 365 * numberOfYearsToAdd);
		return this;
	}

	public Date getDate() {
		return getCalendar().getTime();
	}

	public String getDateStartWithYear() {
		return new SimpleDateFormat(DATE_FORMAT_START_WITH_YEAR).format(getDate());
	}

	public boolean equals(Object object) {
		if ((object instanceof TimelessDate) && (this.compareTo(object) == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return toString().hashCode();
	}

	public int compareTo(Object object) {
		if (object instanceof TimelessDate) {
			TimelessDate compareDate = (TimelessDate) object;
			if (getYear() > compareDate.getYear()) {
				return 1;
			} else if (getYear() < compareDate.getYear()) {
				return -1;
			} else {
				if (getMonth() > compareDate.getMonth()) {
					return 1;
				} else if (getMonth() < compareDate.getMonth()) {
					return -1;
				} else {
					if (getDay() > compareDate.getDay()) {
						return 1;
					} else if (getDay() < compareDate.getDay()) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		} else {
			throw new ClassCastException("Object presented for comparison was null or not a EptoTimelessDate");
		}
	}

	public String toString() {
		StringBuffer toString = new StringBuffer(10);
		if (getMonth() < 10) {
			toString.append("0");
		}
		toString.append(getMonth()).append("/");
		if (getDay() < 10) {
			toString.append("0");
		}
		toString.append(getDay()).append("/").append(getYear());
		return toString.toString();
	}

	public String toString(String delimiter) {
		StringBuffer toString = new StringBuffer(10);
		if (getMonth() < 10) {
			toString.append("0");
		}
		toString.append(getMonth()).append(delimiter);
		if (getDay() < 10) {
			toString.append("0");
		}
		toString.append(getDay()).append(delimiter).append(getYear());
		return toString.toString();
	}

	public int dayDifference(Date fromDate, Date toDate) {
		Calendar fromCalendarDate = Calendar.getInstance();
		Calendar toCalendarDate = Calendar.getInstance();

		fromCalendarDate.setTime(fromDate);
		toCalendarDate.setTime(toDate);

		return daysSinceEpoch(toCalendarDate) - daysSinceEpoch(fromCalendarDate);
	}

	private int daysSinceEpoch(Calendar day) {
		int year = day.get(Calendar.YEAR);
		int month = day.get(Calendar.MONTH);
		int daysThisYear = cumulDaysToMonth[month] + day.get(Calendar.DAY_OF_MONTH) - 1;
		if ((month > 1) && isLeapYear(year)) {
			daysThisYear++;
		}

		return daysToYear(year) + daysThisYear;
	}

	private boolean isLeapYear(int year) {
		return (year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0));
	}

	private int daysToYear(int year) {
		return (365 * year) + numLeapsToYear(year);
	}

	private int numLeapsToYear(int year) {
		int num4y = (year - 1) / 4;
		int num100y = (year - 1) / 100;
		int num400y = (year - 1) / 400;
		return num4y - num100y + num400y;
	}

	public void setCalendar(GregorianCalendar calendar) {
		if (calendar != null) {
			this.calendar = calendar;
		}
	}

	public GregorianCalendar getCalendar() {
		return calendar;
	}

	public boolean before(TimelessDate compareDate) {
		if (this.compareTo(compareDate) < 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean after(TimelessDate compareDate) {
		if (this.compareTo(compareDate) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String getDayOfWeek() {
		return new SimpleDateFormat(DAY_OF_WEEK_FORMAT).format(getDate());
	}

	public String getLongFormat() {
		return new SimpleDateFormat(LONG_DATE_FORMAT).format(getDate());
	}

	/**
	 *
	 * @param date
	 *            "MM/dd/yyyy"
	 * @return
	 * @throws Exception
	 */
	public static java.sql.Date getSqlDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			return new java.sql.Date(sdf.parse(date).getTime());
		} catch (Exception e) {
			throw new RuntimeException("Timeless Date Exception", e);
		}
	}

	/**
	 *
	 * @param java.util.date
	 * @return
	 * @throws Exception
	 */
	public static java.sql.Date getSqlDate(java.util.Date date) {
		try {
			return new java.sql.Date(date.getTime());
		} catch (Exception e) {
			throw new RuntimeException("Timeless Date Exception", e);
		}
	}

	/**
	 *
	 * @param date
	 *            "MM/dd/yyyy"
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date getUtilDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			return new java.util.Date(sdf.parse(date).getTime());
		} catch (Exception e) {
			throw new RuntimeException("Timeless Date Exception", e);
		}
	}

	/**
	 *
	 * @param java.sql.date
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date getUtilDate(java.sql.Date date) {
		try {
			return new java.util.Date(date.getTime());
		} catch (Exception e) {
			throw new RuntimeException("Timeless Date Exception", e);
		}
	}

	public static boolean isWeekendDate(Calendar date) {
		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
		if ((dayOfWeek == Calendar.SATURDAY) || (dayOfWeek == Calendar.SUNDAY)) {
			return true;
		}
		return false;
	}


	public static Calendar getFirstWeekdayDateForCurrentMonth() {
		Calendar firstDate = GregorianCalendar.getInstance();
		firstDate.set(Calendar.DAY_OF_MONTH, 1);
		while(!TimelessDate.isWeekendDate(firstDate)) {
			firstDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		return firstDate;
	}

	public static Calendar getFirstWeekdayDateForPreviousMonth() {
		Calendar firstDate = getFirstWeekdayDateForCurrentMonth();
		firstDate.add(Calendar.MONTH, -1);
		firstDate.set(Calendar.DAY_OF_MONTH, 1);
		while(TimelessDate.isWeekendDate(firstDate)) {
			firstDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		return firstDate;
	}

	public static Integer getMonthNumByName(String monthName) {
		DateFormatSymbols symbols = new DateFormatSymbols();
		String[] monthNames = symbols.getMonths();

		return Arrays.binarySearch(monthNames, monthName);
	}

	public static String getMonthNameByNum(Integer monthNum) {
		DateFormatSymbols symbols = new DateFormatSymbols();
		String[] monthNames = symbols.getMonths();

		return monthNames[monthNum];
	}

}
