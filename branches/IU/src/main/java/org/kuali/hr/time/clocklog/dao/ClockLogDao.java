package org.kuali.hr.time.clocklog.dao;

import java.util.List;

import org.kuali.hr.time.domain.base.ClockLog;

public interface ClockLogDao {

    public void saveOrUpdate(ClockLog clockLog);
    public void saveOrUpdate(List<ClockLog> clockLogList);
}
