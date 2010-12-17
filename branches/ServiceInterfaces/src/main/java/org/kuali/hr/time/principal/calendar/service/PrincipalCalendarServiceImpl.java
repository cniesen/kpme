package org.kuali.hr.time.principal.calendar.service;

import java.sql.Date;

import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.paycalendar.PayCalendarEntries;
import org.kuali.hr.time.principal.calendar.PrincipalCalendar;
import org.kuali.hr.time.principal.calendar.dao.PrincipalCalendarDao;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;

public class PrincipalCalendarServiceImpl implements PrincipalCalendarService {

	private PrincipalCalendarDao principalCalendarDao;

	public void setPrincipalCalendarDao(
			PrincipalCalendarDao principalCalendarDao) {
		this.principalCalendarDao = principalCalendarDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kuali.hr.time.principal.calendar.service.PrincipalCalendarService
	 * #getPrincipalCalendar(java.lang.String, java.sql.Date)
	 */
	public PrincipalCalendar getPrincipalCalendar(String principalId,
			Date asOfDate) {
		PrincipalCalendar pc = this.principalCalendarDao.getPrincipalCalendar(
				principalId, asOfDate);
		pc.setPayCalendar(TkServiceLocator.getPayCalendarSerivce()
				.getPayCalendarByGroup(pc.getCalendarGroup()));
		return pc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kuali.hr.time.principal.calendar.service.PrincipalCalendarService
	 * #getPayCalendarForCurrentPrincipalAndCurrentDate(java.lang.String,
	 * java.sql.Date)
	 */
	@Override
	public PayCalendarEntries getPayCalendarForCurrentPrincipalAndCurrentDate(
			String principalId, Date date) {
		PrincipalCalendar principalCalendar = this.getPrincipalCalendar(
				principalId, date);
		for (PayCalendarEntries entry : principalCalendar.getPayCalendar()
				.getPayCalendarEntries()) {
			if (entry.includesDate(date))
				return entry;
		}
		// TODO: this is just for dev purpose
		if (true)
			return principalCalendar.getPayCalendar().getPayCalendarEntries().get(0);
		return null;
	}

}
