package org.kuali.hr.time.clocklog.service;

import org.kuali.hr.time.clocklog.dao.ClockLogDao;
import org.kuali.hr.time.domain.base.ClockLog;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class ClockLogServiceImpl implements ClockLogService {

    private ClockLogDao clockLogDao;
    
    public ClockLogServiceImpl() {
    }
    
    @Override
    public void saveClockAction(ClockLog clockLog) {

    }

    public void setClockLogDao(ClockLogDao clockLogDao) {
        this.clockLogDao = clockLogDao;
    }

}
