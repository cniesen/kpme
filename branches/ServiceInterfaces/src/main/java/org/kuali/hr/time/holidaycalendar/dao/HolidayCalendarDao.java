package org.kuali.hr.time.holidaycalendar.dao;

import java.util.Date;
import java.util.List;

import org.kuali.hr.time.exceptions.TkException;
import org.kuali.hr.time.holidaycalendar.HolidayCalendar;
import org.kuali.hr.time.holidaycalendar.HolidayCalendarDateEntry;

/**
 * 
 * @author crivera
 * 
 */
public interface HolidayCalendarDao {

	/**
	 * 
	 * @param holidayCalendarGroup
	 * @return
	 * @throws TkException
	 */
	public HolidayCalendar getHolidayCalendarByGroup(String holidayCalendarGroup)
			throws TkException;

	public List<HolidayCalendarDateEntry> getHolidayCalendarDateEntriesForPayPeriod(Long holidayCalendarId, 
				Date startDate, Date endDate);
}
