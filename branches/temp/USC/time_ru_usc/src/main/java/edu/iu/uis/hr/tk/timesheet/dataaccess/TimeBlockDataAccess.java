package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;

public interface TimeBlockDataAccess extends DataAccessOjb {
    public TimeBlock getTimeBlock(String documentId, BigDecimal employeeRecord, BigDecimal workArea, BigDecimal task, String earnCode, Timestamp beginTime, Timestamp endTime);

    public List getTimeBlocks(String documentId);

    public List getTimeBlocks(String documentId, Date date);
    
    public List getTimeBlocksOrderByAscending(String documentId);
    
    public void removeTimeBlockManually(TimeBlock timeBlock);
    
    public void addTimeBlockManually(TimeBlock timeBlock);
}

