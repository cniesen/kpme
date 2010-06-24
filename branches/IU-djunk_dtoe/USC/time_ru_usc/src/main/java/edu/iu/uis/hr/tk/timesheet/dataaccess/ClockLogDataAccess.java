package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.util.List;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.entity.WorkAreaStatusInquirySearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLogSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;

public interface ClockLogDataAccess extends DataAccessOjb {
    public ClockLog getClockLog(String universityId);
    
    @SuppressWarnings("unchecked")
	public List lookupClockLogs(WorkAreaStatusInquirySearchCriteria searchCriteria);

    @SuppressWarnings("unchecked")
    public List getOpenClockLogs();
    
    public TypedPersistentMaintainedEntityList lookupClockLogsPerDate(ClockLogSearchCriteria searchCriteria);

    public TypedPersistentMaintainedEntityList lookupClockLogsPerDateAndAction(String emplId, TimeBlock block);
    
    public ClockLog getClockLog(String universityId, Timestamp clockTimestamp);   
    
    public TypedPersistentMaintainedEntityList getClockLogs(String universityId);
}
