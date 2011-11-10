package org.kuali.hr.time.calendar.service;

import org.kuali.hr.time.calendar.CalendarEntries;

import java.util.Date;
import java.util.List;

public interface CalendarEntriesService {

    /**
     * Method to directly access the CalendarEntries object by ID.
     *
     * @param hrPyCalendarEntriesId The ID to retrieve.
     * @return a CalendarEntries object.
     */
	public CalendarEntries getCalendarEntries(Long hrCalendarEntriesId);

    /**
     * Method to obtain the current CalendarEntries object based on the
     * indicated calendar and asOfDate.
     * @param hrPyCalendarId The calendar to reference.
     * @param asOfDate The date reference point.
     * @return the current CalendarEntries effective by the asOfDate.
     */
	public CalendarEntries getCurrentCalendarEntriesByCalendarId(Long hrCalendarId, Date asOfDate);
    public CalendarEntries getCalendarEntriesByIdAndPeriodEndDate(Long hrCalendarId, Date endPeriodDate);

    public CalendarEntries getPreviousCalendarEntriesByCalendarId(Long hrCalendarId, CalendarEntries pce);
    public CalendarEntries getNextCalendarEntriesByCalendarId(Long hrCalendarId, CalendarEntries pce);

    /**
     * Provides a list of CalendarEntries that are in the indicated window
     * of time from the as of date.
     * @param thresholdDays ± days from the asOfDate to form the window of time.
     * @param asOfDate The central date to query from.
     * @return A list of CalendarEntries.
     */
	public List<CalendarEntries> getCurrentCalendarEntryNeedsScheduled(int thresholdDays, Date asOfDate);
}
