package org.kuali.hr.time.clocklog.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.hr.time.domain.base.ClockLog;
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;

public class ClockLogDaoSpringOjbImpl extends PersistenceBrokerDaoSupport implements ClockLogDao {

    private static final Logger LOG = Logger.getLogger(ClockLogDaoSpringOjbImpl.class);
    
    public void saveOrUpdate(ClockLog clockLog) {
	this.getPersistenceBrokerTemplate().store(clockLog);
    }
    
    public void saveOrUpdate(List<ClockLog> clockLogList) {
	if (clockLogList != null) {
	    for (ClockLog clockLog : clockLogList) {
		this.getPersistenceBrokerTemplate().store(clockLog);
	    }
	}
    }

}
