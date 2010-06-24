package edu.iu.uis.hr.tk.timesheet.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.entity.Document;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.service.DocumentService;
import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.entity.PayrollExtractReportSearchCriteria;
import edu.iu.uis.hr.tk.entity.WorkAreaStatusInquirySearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.ActualTimeSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLogSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshot;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLock;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLockSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetailToDistribute;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockHistory;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockHistorySearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public interface TimesheetService extends DocumentService {

	public static final String ACTION_HISTORY_DELETED = "Delete";
	public static final String ACTION_HISTORY_ADDED = "Add";
	public static final String ACTION_HISTORY_CLOCKED = "Clock";
	public static final String ACTION_HISTORY_DISTRIBUTED = "Distribute";
	public static final String ACTION_HISTORY_RETRO = "Retro";
	public static final String ACTION_HISTORY_MANUALLY_REMOVED = "Remove";
	public static final String DOC_HANDLER_PARAMS = "?&command=displayDocSearchView&method=open";

	
    public String approve(Document document);

    public String approve(Document document, String networkId);

    public void finalize(Document document);

    public TimesheetDocument getTimesheetDocument(String documentId);

    public TimesheetDocument getTimesheetDocument(String universityId, Date payEndDate);

    public TimesheetDocument openUserTimesheet();

    public TimesheetDocument openUserTimesheet(Date payEndDate);

    public List geDocumentHeaders(Date payEndDate);

    public List getDocumentHeaderSnapshots(Date payEndDate);

    public DocumentHeader getDocumentHeader(String universityId, Date payEndDate);
    
    public DocumentHeader getDocumentHeader(String documentId);

    public DocumentHeaderSnapshot getDocumentHeaderSnapshot(String documentId);

    public List getDocumentHeadersAutoApproved(Date payEndDate);

    public TypedPersistentMaintainedEntityList lookupDocumentHeaders(DocumentHeaderSearchCriteria searchCritera);

    public boolean isFinalApproved(String networkId, String documentId);

    public ClockLog getClockLog(String universityId);

    public ClockLog getClockLog(String universityId, Timestamp timestamp);

    public List lookupClockLogs(WorkAreaStatusInquirySearchCriteria searchCriteria);

    public TypedPersistentMaintainedEntityList lookupClockLogsByEmployee(ClockLogSearchCriteria searchCriteria);

    public void saveClockLogByEmployee(ClockLog clockLogs);

    public List getDropdownClockAssignments(TimesheetDocument timesheetDocument);

    public DocumentLock lock(String documentId);

    public boolean isDocumentLockedByUser(String documentId, String universityId);

    public void clockIn(TimesheetDocument timesheetDocument);

    public void clockOut(TimesheetDocument timesheetDocument);

    public void lunchIn(TimesheetDocument timesheetDocument);

    public void lunchOut(TimesheetDocument timesheetDocument);

    public void breakIn(TimesheetDocument timesheetDocument);

    public void breakOut(TimesheetDocument timesheetDocument);

    public void unlockDocument(TimesheetDocument timesheetDocument);

    public void createHourDetails(TypedPersistentMaintainedEntityList dates, TimesheetDocument timesheetDocument, HourDetail hourDetail);
  //  public void deleteClockAction(TimesheetDocument timesheetDocument);

  //  public void delete(TimesheetDocument timesheetDocument, List timeBlocksToDelete);

    public TypedPersistentMaintainedEntityList lookupLockedDocuments(DocumentLockSearchCriteria searchCriteria);

    public TypedPersistentMaintainedEntityList lookupTimeBlockHistory(TimeBlockHistorySearchCriteria searchCriteria);

    public void unlockMutipleDocuments(String documentIdList);

    public boolean isEditedByAdministrator(TimesheetDocument timesheetDocument);

    public void finishPreparingTimesheetDocument(TimesheetDocument timesheetDocument);

    public void takeSnapshot(Date payEndDate);

    public TypedPersistentMaintainedEntityList lookupPayrollExtractReports(PayrollExtractReportSearchCriteria searchCriteria);

    public List getDocumentHeadersEnroute(Date payEndDate);

    public List getDocumentHeadersRoutedToCanceled(Date payEndDate);

    public void getExtractReportInfo(List documentHeaders, Collection col);
//
//    public static final Map WEEKLY_OVERTIME_RULE_MAP = new HashMap();
//
//    public static final Map DAILY_OVERTIME_RULE_MAP = new HashMap();

    public boolean isHourDistributionAllowed(TimesheetDocument timesheetDocument);

    public TimesheetDocument distributeHours(TimesheetDocument timesheetDocument);

    public boolean hasJurisdictionOverAssignment(TimesheetDocument timesheetDocument,  String assignment);

    public String getEmployeeName(String universityId);

    public boolean isAuthorized(HourDetail hourDetail);

    public boolean isAuthorized(User user, HourDetail hourDetail);

    public Integer getOffsetFromLocation(String location);

    public String getLocationFromTimesheet(TimesheetDocument timesheetDocument);

    public String determineLocation(Clock clock, String universityId);

    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(Date payEndDate, Timestamp startDate, Timestamp endDate);

    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(Date payEndDate, Timestamp startDate, Timestamp endDate, String documentId);
    
    public List getTimeBlockHistoryModifiedDocuments(Date payEndDate, Timestamp startDate, Timestamp endDate);

    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(String documentId);
    
    public TypedPersistentMaintainedEntityList lookupActualTime(ActualTimeSearchCriteria searchCriteria);

    public TypedPersistentMaintainedEntityList lookupTimeBlocks(TimeBlockSearchCriteria searchCriteria);

    public void removeTimeBlockManually(TimeBlock timeBlock);

    public void removeTimeBlockManually(TimeBlockHistory timeBlock);

    public void removeClockLogManually(String universityId);

    public void addTimeBlockManually(TimeBlock timeBlock);

    public void addTimeBlockManually(TimeBlockHistory timeBlock);
    
    public HoursDetailToDistribute convertToHourDetailToDistribute(TimeBlock timeBlock);
    
    public boolean hasHours(String docId);
    
    public void saveTimeBlockAsyncronously(String documentId, String userId, Timestamp clockInTimestamp, String beginTsTz);
    
    public String populateServerLocationTimeOffset();
    
    public List getTimeBlocks(String documentId);
    
    public TypedPersistentMaintainedEntityList getCachedWeeklyOvertimeRule();
    
    public TimeBlock getTimeBlock(DocumentHeader documentHeader, HourDetail hourDetail);
}