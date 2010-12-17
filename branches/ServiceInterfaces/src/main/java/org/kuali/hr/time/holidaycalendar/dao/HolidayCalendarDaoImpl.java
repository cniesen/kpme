package org.kuali.hr.time.holidaycalendar.dao;

import java.util.Date;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.time.exceptions.TkException;
import org.kuali.hr.time.holidaycalendar.HolidayCalendar;
import org.kuali.hr.time.holidaycalendar.HolidayCalendarDateEntry;
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;

/**
 * 
 * @author crivera
 * 
 */
public class HolidayCalendarDaoImpl extends PersistenceBrokerDaoSupport
		implements HolidayCalendarDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.kuali.hr.time.holidaycalendar.dao.HolidayCalendarDao#
	 * getHolidayCalendarByGroup(java.lang.String)
	 */
	@Override

	public HolidayCalendar getHolidayCalendarByGroup(String holidayCalendarGroup)
			throws TkException {
		Criteria root = new Criteria();
		root.addEqualTo("holidayCalendarGroup", holidayCalendarGroup);
		return (HolidayCalendar) this.getPersistenceBrokerTemplate().getObjectByQuery(QueryFactory.newQuery(HolidayCalendar.class, root));
	}
	
	@SuppressWarnings("unchecked")
	public List<HolidayCalendarDateEntry> getHolidayCalendarDateEntriesForPayPeriod(Long holidayCalendarId, Date startDate, Date endDate){
		Criteria root = new Criteria();
		root.addEqualTo("holidayCalendarId", holidayCalendarId);
		root.addBetween("holidayDate", startDate, endDate);
		return (List<HolidayCalendarDateEntry>)this.getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(HolidayCalendarDateEntry.class, root));
	}

}
