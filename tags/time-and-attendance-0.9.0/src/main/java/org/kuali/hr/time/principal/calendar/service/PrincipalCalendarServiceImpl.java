package org.kuali.hr.time.principal.calendar.service;

import org.kuali.hr.time.cache.CacheResult;
import org.kuali.hr.time.principal.calendar.PrincipalCalendar;
import org.kuali.hr.time.principal.calendar.dao.PrincipalCalendarDao;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TkConstants;

import java.util.Date;

public class PrincipalCalendarServiceImpl implements PrincipalCalendarService {
	private PrincipalCalendarDao principalCalendarDao;

	public void setPrincipalCalendarDao(PrincipalCalendarDao principalCalendarDao) {
		this.principalCalendarDao = principalCalendarDao;
	}
	
	@CacheResult(secondsRefreshPeriod=TkConstants.DEFAULT_CACHE_TIME)
	public PrincipalCalendar getPrincipalCalendar(String principalId, Date asOfDate){
		PrincipalCalendar pc =  this.principalCalendarDao.getPrincipalCalendar(principalId, asOfDate);
		if(pc != null) {
			pc.setPayCalendar(TkServiceLocator.getPayCalendarSerivce().getPayCalendarByGroup(pc.getPyCalendarGroup()));
		}
		return pc;
	}

}
