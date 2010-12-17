package org.kuali.hr.time.holidaycalendar.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.kuali.hr.job.Job;
import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.exceptions.TkException;
import org.kuali.hr.time.holidaycalendar.HolidayCalendar;
import org.kuali.hr.time.holidaycalendar.HolidayCalendarDateEntry;
import org.kuali.hr.time.holidaycalendar.dao.HolidayCalendarDao;
import org.kuali.hr.time.paytype.PayType;
import org.kuali.hr.time.paytype.dao.PayTypeDao;
import org.kuali.hr.time.util.TkConstants;



/**
 * 
 * @author crivera
 * 
 */
@WebService(endpointInterface = "org.kuali.hr.time.holidaycalendar.service.HolidayCalendarService")
public class HolidayCalendarServiceImpl implements HolidayCalendarService {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.kuali.hr.time.holidaycalendar.service.HolidayCalendarService#
	 * getHolidaysByPrincipalId(java.lang.String)
	 */
	@Override
	public List<HolidayCalendarDateEntry> getHolidaysByPrincipalId(
			String principalId) throws TkException {
		// get the paytype
		PayTypeDao payTypeDao = SpringContext.getBean(PayTypeDao.class);
		PayType payType = payTypeDao.getPayTypeByPrincipalId(principalId);
		// get the actual calendar
		// TODO: THIS HAPPENED WHEN THE MERGE CAME ... NEED TO FIX !
		// HolidayCalendar calendar = this.getHolidayCalendarByGroup(payType
		// .getHolidayCalendarGroup());
		// return the list of dates
		return null;// calendar.getDateEntries();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.kuali.hr.time.holidaycalendar.service.HolidayCalendarService#
	 * getHolidayCalendarByGroup(java.lang.String)
	 */
	@Override
	public HolidayCalendar getHolidayCalendarByGroup(String holidayCalendarGroup)
			throws TkException {
		HolidayCalendarDao holidayCalendarDao = SpringContext
				.getBean(HolidayCalendarDao.class);
		return holidayCalendarDao
				.getHolidayCalendarByGroup(holidayCalendarGroup);
	}


	@Override
	public List<HolidayCalendarDateEntry> getHolidayCalendarDateEntriesForPayPeriod(
			Long holidayCalendarId, Date startDate, Date endDate) {
		return SpringContext.getBean(HolidayCalendarDao.class).getHolidayCalendarDateEntriesForPayPeriod(holidayCalendarId, startDate, endDate);
	}


	@Override
	public Assignment getAssignmentToApplyHolidays() {
		return null;
	}


	@Override
	public BigDecimal calculateHolidayHours(Job job, BigDecimal holidayHours) {
		BigDecimal fte = job.getStandardHours().divide(new BigDecimal(40.0),TkConstants.BIG_DECIMAL_SCALE);
		return fte.multiply(holidayHours);
	}

}
