package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockHistorySearchCriteria;

public interface TimeBlockHistoryDataAccess extends DataAccessOjb {
    public TypedPersistentMaintainedEntityList lookupTimeBlockHistory(TimeBlockHistorySearchCriteria searchCriteria);
    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(Date payEndDate, Timestamp startDate, Timestamp endDate);
    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(Date payEndDate, Timestamp startDate, Timestamp endDate, String documentId);    
    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(String documentId);   
    public List getTimeBlockHistoryModifiedDocuments(Date payEndDate, Timestamp startDate, Timestamp endDate);
   }
