package edu.iu.uis.hr.tk.timesheet.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.kew.dto.EmplIdDTO;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.ksb.service.KSBServiceLocator;

import edu.iu.hrms.hredoc.infrastructure.HredocServiceLocator;
import edu.iu.uis.hr.ApplicationException;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.DocumentMetaData;
import edu.iu.uis.hr.ExceptionAdapter;
import edu.iu.uis.hr.InitiateKey;
import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.Optionable;
import edu.iu.uis.hr.Time;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.client.UserSession;
import edu.iu.uis.hr.directory.entity.User;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.entity.Document;
import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.Translate;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.entity.Holiday;
import edu.iu.uis.hr.job.funding.entity.EarnCode;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.job.service.HolidayService;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.service.DocumentServiceImpl;
import edu.iu.uis.hr.service.TranslateService;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.TimeZoneUtility;
import edu.iu.uis.hr.tk.directory.entity.UserImpl;
import edu.iu.uis.hr.tk.employee.entity.LeaveBalance;
import edu.iu.uis.hr.tk.employee.service.LeaveAccrualService;
import edu.iu.uis.hr.tk.entity.PayrollExtractReportSearchCriteria;
import edu.iu.uis.hr.tk.entity.Preference;
import edu.iu.uis.hr.tk.entity.WorkAreaStatusInquiry;
import edu.iu.uis.hr.tk.entity.WorkAreaStatusInquirySearchCriteria;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.log.LogPerformanceMethod;
import edu.iu.uis.hr.tk.rule.dataaccess.DailyOvertimeRuleDataAccess;
import edu.iu.uis.hr.tk.rule.dataaccess.WeeklyOvertimeRuleDataAccess;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRule;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRule;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRule;
import edu.iu.uis.hr.tk.rule.entity.SystemLunchRule;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRule;
import edu.iu.uis.hr.tk.rule.entity.WeeklyOvertimeRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;
import edu.iu.uis.hr.tk.rule.service.SystemLunchRuleNotFoundException;
import edu.iu.uis.hr.tk.service.UserPreferenceService;
import edu.iu.uis.hr.tk.timesheet.TimesheetInitiateKey;
import edu.iu.uis.hr.tk.timesheet.dataaccess.ClockLogDataAccess;
import edu.iu.uis.hr.tk.timesheet.dataaccess.DocumentHeaderDataAccess;
import edu.iu.uis.hr.tk.timesheet.dataaccess.DocumentHeaderSnapshotDataAccess;
import edu.iu.uis.hr.tk.timesheet.dataaccess.DocumentLockDataAccess;
import edu.iu.uis.hr.tk.timesheet.dataaccess.TimeBlockDataAccess;
import edu.iu.uis.hr.tk.timesheet.dataaccess.TimeBlockHistoryDataAccess;
import edu.iu.uis.hr.tk.timesheet.entity.ActualTime;
import edu.iu.uis.hr.tk.timesheet.entity.ActualTimeSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.CheckedPayCalendarDate;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLogSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DepartmentShiftPayHours;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshot;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLock;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLockSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetailDistribution;
import edu.iu.uis.hr.tk.timesheet.entity.Hours;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetailToDistribute;
import edu.iu.uis.hr.tk.timesheet.entity.HoursSummary;
import edu.iu.uis.hr.tk.timesheet.entity.ShiftHourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.ShiftHours;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockHistory;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockHistorySearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.entity.logic.HourDistributionValidation;
import edu.iu.uis.hr.tk.timesheet.entity.logic.MessageKeyConstants;

public class TimesheetServiceImpl extends DocumentServiceImpl implements TimesheetService {

    private static final Logger LOG = Logger.getLogger(TimesheetServiceImpl.class);
    private static final Logger LOG_PERFORM = Logger.getLogger(LogPerformanceMethod.class);
    private static final String DOCUMENT_HEADER_NOT_FOUND = "You do not have a timesheet for this pay period.  Please contact your supervisor or payroll processor to have a timesheet created.";
    private static final String ACTIVE_ASSIGNMENT_NOT_FOUND = "You do not have an active assignment for this pay period.  Please contact your supervisor or payroll processor to have a timesheet created.";
    private static final String WORKFLOW_TK_SUPER_USER = "timekeeping-system";
    private static final String DAILY_OVERTIME_EXCEPTION = "Daily Overtime Exception:";
    private static final String PREMIUM_EARN_CODE = "PRM";
    private static final String POO_EARN_CODE = "POO";
    private static final String PRW_EARN_CODE = "PRW";
    private static final String HOLIDAY_EARN_CODE = "HOL";
    private static final String EMPLOYEE_TYPE = "  Type: ";
    private static final String HOURLY_TYPE = "H";
    private static final String EXCEPTION_HOURLY_TYPE = "E";
    private static final String UNIVERSITY_ID = "  UnivId: ";
    private static final String WORK_AREA = "  WorkArea: ";

    private DocumentHeaderDataAccess documentHeaderDataAccess;
    private DocumentHeaderSnapshotDataAccess documentHeaderSnapshotDataAccess;
    private ClockLogDataAccess clockLogDataAccess;
    private TimeBlockDataAccess timeBlockDataAccess;
    private TimeBlockHistoryDataAccess timeBlockHistoryDataAccess;
    private DocumentLockDataAccess documentLockDataAccess;
    private EarningService earningService;
    private JobService jobService;
    private AssignmentService assignmentService;
    private PayCalendarService payCalendarService;
    private RuleService ruleService;
    private WeeklyOvertimeRuleDataAccess weeklyOvertimeRuleDataAccess;
    private DailyOvertimeRuleDataAccess dailyOvertimeRuleDataAccess;
    private LeaveAccrualService leaveAccrualService;
    private TranslateService translateService;
    private HolidayService holidayService;
    private UserPreferenceService userPreferenceService;
    private DirectoryService directoryService;
    private PositionService positionService;

    private static final List SHIFT_INDEX_LIST = new ArrayList();
    static {
        for (int i = 0; i <= 14; i++) {
            SHIFT_INDEX_LIST.add(new Integer(i));
        }
    }

    private static final List LEAVE_PLAN_TYPES = new ArrayList();
    static {
        LEAVE_PLAN_TYPES.add("5V");
        LEAVE_PLAN_TYPES.add("51");
        LEAVE_PLAN_TYPES.add("50");
        LEAVE_PLAN_TYPES.add("5X");
        LEAVE_PLAN_TYPES.add("5Y");
        LEAVE_PLAN_TYPES.add("5E"); //dbb add for PAU
        LEAVE_PLAN_TYPES.add("5W"); //dbb add for PAO        

    }

    private static final Map locationTimezoneRawOffsetMap = new HashMap();
    static {
        locationTimezoneRawOffsetMap.put("BL", new Integer(TimeZone.getTimeZone("America/Indianapolis").getOffset(new Date().getTime())));
        locationTimezoneRawOffsetMap.put("EA", new Integer(TimeZone.getTimeZone("America/Indianapolis").getOffset(new Date().getTime())));
        locationTimezoneRawOffsetMap.put("FW", new Integer(TimeZone.getTimeZone("America/Indianapolis").getOffset(new Date().getTime())));
        locationTimezoneRawOffsetMap.put("IN", new Integer(TimeZone.getTimeZone("America/Indianapolis").getOffset(new Date().getTime())));
        locationTimezoneRawOffsetMap.put("KO", new Integer(TimeZone.getTimeZone("America/Indianapolis").getOffset(new Date().getTime())));
        locationTimezoneRawOffsetMap.put("NW", new Integer(TimeZone.getTimeZone("America/Chicago").getOffset(new Date().getTime())));
        locationTimezoneRawOffsetMap.put("SB", new Integer(TimeZone.getTimeZone("America/Indianapolis").getOffset(new Date().getTime())));
        locationTimezoneRawOffsetMap.put("SE", new Integer(TimeZone.getTimeZone("America/Indianapolis").getOffset(new Date().getTime())));
    }

    private static final int serverLocationTimezoneRawOffset = TimeZone.getTimeZone("America/Indianapolis").getOffset(new Date().getTime());


    public TimesheetDocument getTimesheetDocument(String documentId) {
        LOG.debug("Begin getTimesheetDocument(String documentId) " + documentId);
        DocumentHeader documentHeader = getDocumentHeaderDataAccess().getDocumentHeader(documentId);
        if (!Utility.hasValue(documentHeader)) {
            throw new RuntimeException("No timesheet document found for documentId: " + documentId);
        }
        PayCalendar payCalendar = getPayCalendarService().getPayCalendar(documentHeader.getPayEndDate());
        TimesheetDocument timesheetDocument = new TimesheetDocument(documentHeader, new Clock(getClockLog(documentHeader.getUniversityId())), new Hours(documentHeader, getPayCalendarService().getPayCalendar(documentHeader.getPayEndDate())), getJobService().getJobsWithAssignments(documentHeader.getUniversityId(), documentHeader.getPayEndDate()), getLeaveSummary(documentHeader.getUniversityId(), payCalendar.getPayBeginDate()));
        releaseExpiredDocumentLocks(timesheetDocument);
        LOG.debug("End getTimesheetDocument(String documentId) " + documentId);
        return timesheetDocument;
    }

    public TimesheetDocument getTimesheetDocument(String universityId, Date payEndDate) {
        LOG.debug("Begin getTimesheetDocument(String universityId, Date payEndDate) " + universityId + " " + payEndDate.toString());
        PayCalendar payCalendar = getPayCalendarService().getPayCalendar(payEndDate);
        DocumentHeader documentHeader = getDocumentHeaderDataAccess().getDocumentHeader(universityId, payCalendar.getPayEndDate());
        if (!Utility.hasValue(documentHeader)) {
            throw new RuntimeException("No timesheet document found for pay period ending in " + payCalendar.getPayEndDate());
        }
        TimesheetDocument timesheetDocument = new TimesheetDocument(documentHeader, new Clock(getClockLog(documentHeader.getUniversityId())), new Hours(documentHeader, payCalendar), getJobService().getJobsWithAssignments(documentHeader.getUniversityId(), documentHeader.getPayEndDate()), getLeaveSummary(documentHeader.getUniversityId(), payCalendar.getPayBeginDate()));

        releaseExpiredDocumentLocks(timesheetDocument);
        LOG.debug("End getTimesheetDocument(String universityId, Date payEndDate) " + universityId + " " + payEndDate.toString());
        return timesheetDocument;
    }

    public TimesheetDocument openUserTimesheet() {
        PayCalendar payCalendar = getPayCalendarService().getPayCalendar(dateBasedOnLocation());
        DocumentHeader documentHeader = getDocumentHeaderDataAccess().getDocumentHeader(getWebUserService().getUser().getUniversityId(), payCalendar.getPayEndDate());
        //DocumentHeader documentHeader = getDocumentHeaderDataAccess().getDocumentHeader("0000143050", payCalendar.getPayEndDate());


        if (!Utility.hasValue(documentHeader)) {
            if (getAssignmentService().hasAssignment(getWebUserService().getUser().getUniversityId())) {
                TimesheetInitiateKey initiateKey = new TimesheetInitiateKey();
                initiateKey.setUniversityId(getWebUserService().getUser().getUniversityId());

                initiateKey.setPayEndDate(payCalendar.getPayEndDate());
                this.initiate(initiateKey);
                documentHeader = getDocumentHeaderDataAccess().getDocumentHeader(getWebUserService().getUser().getUniversityId(), payCalendar.getPayEndDate());
                if (!Utility.hasValue(documentHeader)) {
                    throw new DocumentHeaderNotFoundException(DOCUMENT_HEADER_NOT_FOUND, LOG);
                }
            } else {
                throw new ActiveAssignmentNotFoundException(ACTIVE_ASSIGNMENT_NOT_FOUND, LOG);
            }
        }
        TimesheetDocument timesheetDocument = new TimesheetDocument(documentHeader, new Clock(getClockLog(documentHeader.getUniversityId())), new Hours(documentHeader, payCalendar), getJobService().getJobsWithAssignments(documentHeader.getUniversityId(), documentHeader.getPayEndDate()), getLeaveSummary(documentHeader.getUniversityId(), payCalendar.getPayBeginDate()));
        releaseExpiredDocumentLocks(timesheetDocument);
        return timesheetDocument;
    }

    //Determines which timesheet to use. Only useful between 12:00AM and 1:00AM EST right after the pay period ended.
    public Date dateBasedOnLocation() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        if (cal.get(Calendar.HOUR_OF_DAY) < 1) { //logic only executed if it's between 12:00AM and 1:00AM EST
            String universityId = getWebUserService().getUser().getUniversityId();
           	String location = determineLocation(new Clock(getClockLog(universityId)), universityId);
            int offset = (((Integer)locationTimezoneRawOffsetMap.get(location)).intValue() - serverLocationTimezoneRawOffset) / 3600000;
            if (offset != 0) {
                cal.add(Calendar.HOUR, offset);
            }
        }
        return cal.getTime();
    }

    public TimesheetDocument openUserTimesheet(Date payEndDate) {
        return this.getTimesheetDocument(getWebUserService().getUser().getUniversityId(), payEndDate);
    }

    public DocumentLock lock(String documentId) {
        DocumentLock lock = getDocumentLockDataAccess().getDocumentLock(documentId);
        if (Utility.hasValue(lock)) {
            if (!getWebUserService().getUser().getUniversityId().equals(lock.getUserUniversityId())) {
                throw new ConflictingLockException("The document " + documentId + " is already locked by another user.  You cannot edit this document while another user has it locked.", LOG);
            }
        }
        save(new DocumentLock(documentId), getDocumentLockDataAccess());
        return getDocumentLockDataAccess().getDocumentLock(documentId);
    }

    public void unlock(Document document) {
        DocumentLock lock = getDocumentLockDataAccess().getDocumentLock(document.getDocumentId());
        if (!Utility.hasValue(lock) || !getWebUserService().getUser().getUniversityId().equals(lock.getUserUniversityId())) {
            throw new ConflictingLockException("You do not have a lock on this document.  You cannot save this document if you do not have a lock on it.", LOG);
        }
        getDocumentLockDataAccess().delete(new DocumentLock(document.getDocumentId()));
    }

    public void unlockMutipleDocuments(String documentIdList) {
        String[] documentIds = documentIdList.split(",");
        for (int i = 0; i < documentIds.length; i++) {
            getDocumentLockDataAccess().delete(new DocumentLock(documentIds[i]));
        }
    }

    private void releaseExpiredDocumentLocks(TimesheetDocument timesheetDocument) {
        TimedDate currentTime = new TimedDate(new Date());
        DocumentLock lock = timesheetDocument.getDocumentHeader().getDocumentLock();
        if (Utility.hasValue(lock) && Utility.hasValue(lock.getTimestamp())) {
            if (Timestamp.getMillisecondDifference(currentTime.getTimestamp(), lock.getTimestamp()).longValue() / 3600000 > DocumentLock.LOCK_TIMEOUT) {
                unlockMutipleDocuments(timesheetDocument.getDocumentHeader().getDocumentId());
                timesheetDocument.getDocumentHeader().setDocumentLock(null);
            }
        }
    }

    public List geDocumentHeaders(Date payEndDate) {
        return getDocumentHeaderDataAccess().getDocumentHeaders(payEndDate);
    }

    public List getDocumentHeaderSnapshots(Date payEndDate) {
        return getDocumentHeaderSnapshotDataAccess().getDocumentHeaderSnapshots(payEndDate);
    }

    public DocumentHeader getDocumentHeader(String universityId, Date payEndDate) {
        return getDocumentHeaderDataAccess().getDocumentHeader(universityId, payEndDate);
    }

    public DocumentHeaderSnapshot getDocumentHeaderSnapshot(String documentId) {
        return getDocumentHeaderSnapshotDataAccess().getDocumentHeaderSnapshot(documentId);
    }

    public DocumentHeader getDocumentHeader(String documentId) {
    	return getDocumentHeaderDataAccess().getDocumentHeader(documentId);
    }

    public List getDocumentHeadersAutoApproved(Date payEndDate) {
        return getDocumentHeaderDataAccess().getDocumentHeadersAutoApproved(payEndDate);
    }

    @SuppressWarnings("unchecked")
	public Document initiate(InitiateKey initiateKey) {
    	long startTime = System.currentTimeMillis();
        if (!Utility.hasValue(initiateKey) || !(initiateKey instanceof TimesheetInitiateKey)) {
            throw new IllegalArgumentException("TimesheetServiceImpl's initiate(InitiateKey initiateKey) method requires a non-null instance of TimesheetInitiateKey");
        }
        DocumentHeader docHeader = getDocumentHeader(initiateKey.getUniversityId(), ((TimesheetInitiateKey)initiateKey).getPayEndDate());
        if (!Utility.hasValue(docHeader)) {
            try {
                WorkflowDocument workflowDocument = super.initiateWorkflowDocument(initiateKey);
                StringBuffer title = new StringBuffer();
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
                title.append(dateFormatter.format(((TimesheetInitiateKey)initiateKey).getPayEndDate()));
                title.append(EMPLOYEE_TYPE);
                Iterator iter = getJobService().getJobs(((TimesheetInitiateKey)initiateKey).getUniversityId(), ((TimesheetInitiateKey)initiateKey).getPayEndDate()).iterator();
                Set utilSet = new HashSet();
                String utilString = new String();
                while (iter.hasNext()) {
                    utilString = ((Job)iter.next()).getEmployeeType();
                    if (utilString.equals(HOURLY_TYPE) || utilString.equals(EXCEPTION_HOURLY_TYPE)) {
                        utilSet.add(utilString);
                    }
                }
                iter = utilSet.iterator();
                utilString = edu.iu.uis.hr.Utility.getDefaultStringValue();
                while (iter.hasNext()) {
                    utilString += iter.next() + edu.iu.uis.hr.Utility.COMMA + edu.iu.uis.hr.Utility.SPACE;
                }
                if (StringUtils.isNotBlank(utilString)) {
					title.append(utilString.substring(0, utilString.lastIndexOf(edu.iu.uis.hr.Utility.COMMA)));
					title.append(UNIVERSITY_ID + ((TimesheetInitiateKey) initiateKey).getUniversityId());
					title.append(WORK_AREA);
					iter = getJobService().getJobs(((TimesheetInitiateKey) initiateKey).getUniversityId(), ((TimesheetInitiateKey) initiateKey).getPayEndDate()).iterator();
					utilSet.clear();
					while (iter.hasNext()) {
						Iterator assIter = ((Job) iter.next()).getAssignments().iterator();
						while (assIter.hasNext()) {
							utilSet.add(((Assignment) assIter.next()).getWorkArea().toString());
						}
					}
                iter = utilSet.iterator();
                utilString = edu.iu.uis.hr.Utility.getDefaultStringValue();
                while (iter.hasNext()) {
                    utilString += iter.next() + edu.iu.uis.hr.Utility.COMMA + edu.iu.uis.hr.Utility.SPACE;
                }
                    title.append(utilString.substring(0, utilString.lastIndexOf(edu.iu.uis.hr.Utility.COMMA)));
                    workflowDocument.setTitle(title.toString());
                }
                DocumentHeader documentHeader = new DocumentHeader(workflowDocument.getRouteHeaderId().toString(), ((TimesheetInitiateKey)initiateKey).getUniversityId(), ((TimesheetInitiateKey)initiateKey).getPayEndDate());
                documentHeader.setDocumentStatus(workflowDocument.getRouteHeader().getDocRouteStatus());
                getDocumentHeaderDataAccess().store(documentHeader);
                TimesheetDocument timesheetDocument = new TimesheetDocument(documentHeader);
                timesheetDocument.setJobs(getJobService().getJobs(documentHeader.getUniversityId(), documentHeader.getPayEndDate()));
                documentHeader.setDocumentStatus(save(timesheetDocument, ((TimesheetInitiateKey)initiateKey).getUniversityId()));
                getDocumentHeaderDataAccess().store(documentHeader);
                List holidayList = getHolidayService().getHolidays(HolidayService.HOLIDAY_SCHECULE_HRUN, (new TimelessDate(documentHeader.getPayEndDate()).addDays(-13)).getDate(), documentHeader.getPayEndDate());
                try {
                    addHolidayTimeBlocks(timesheetDocument, holidayList);
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
                //LOG_PERFORM.error("Total time spent in TimesheetService init " + (System.currentTimeMillis()-startTime));
                return timesheetDocument;
            } catch (NumberFormatException e) {
                throw new ExceptionAdapter(e, getLogger());
            } catch (WorkflowException e) {
                LOG.error("Initiate document error " + e.getMessage());
                throw new ExceptionAdapter(e, getLogger());
            } catch (Exception e) {
                throw new ExceptionAdapter(e, getLogger());
            }
        } else {
            LOG.debug("Doc Header already exists " + initiateKey.getUniversityId() + " " + ((TimesheetInitiateKey)initiateKey).getPayEndDate());
            return new TimesheetDocument(docHeader);
        }
    }

    protected WorkflowDocument updateWorkflowTitle(Document document, WorkflowDocument workflowDocument) {
        StringBuffer title = new StringBuffer();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        title.append(dateFormatter.format(((TimesheetDocument)document).getDocumentHeader().getPayEndDate()));
        title.append(EMPLOYEE_TYPE);
        Iterator iter = getJobService().getJobs(((TimesheetDocument)document).getDocumentHeader().getUniversityId(), ((TimesheetDocument)document).getDocumentHeader().getPayEndDate()).iterator();
        Set utilSet = new HashSet();
        String utilString = new String();
        while (iter.hasNext()) {
        	Job job = (Job) iter.next();
        	if (job.getEffectiveDate().compareTo(((TimesheetDocument)document).getHours().getPayCalendar().getPayBeginDate()) >= 0){
        	}
            utilString = job.getEmployeeType();
            if (utilString.equals(HOURLY_TYPE) || utilString.equals(EXCEPTION_HOURLY_TYPE)) {
                utilSet.add(utilString); //doing this for all jobs, including the ones with effective date before current pay period.
            }
        }
        iter = utilSet.iterator();
        utilString = edu.iu.uis.hr.Utility.getDefaultStringValue();
        while (iter.hasNext()) {
            utilString += iter.next() + edu.iu.uis.hr.Utility.COMMA + edu.iu.uis.hr.Utility.SPACE;
        }
        title.append(utilString.substring(0, utilString.lastIndexOf(edu.iu.uis.hr.Utility.COMMA)));
        title.append(UNIVERSITY_ID + (((TimesheetDocument)document).getDocumentHeader().getUniversityId()));
        title.append(WORK_AREA);
        iter = getJobService().getJobs(((TimesheetDocument)document).getDocumentHeader().getUniversityId(), ((TimesheetDocument)document).getDocumentHeader().getPayEndDate()).iterator();
        utilSet.clear();
        while (iter.hasNext()) {
            Iterator assIter = ((Job)iter.next()).getAssignments().iterator();
            while (assIter.hasNext()) {
                utilSet.add(((Assignment)assIter.next()).getWorkArea().toString());
            }
        }
        iter = utilSet.iterator();
        utilString = edu.iu.uis.hr.Utility.getDefaultStringValue();
        while (iter.hasNext()) {
            utilString += iter.next() + edu.iu.uis.hr.Utility.COMMA + edu.iu.uis.hr.Utility.SPACE;
        }
        title.append(utilString.substring(0, utilString.lastIndexOf(edu.iu.uis.hr.Utility.COMMA)));
        try {
            workflowDocument.setTitle(title.toString());
            return workflowDocument;

        } catch (WorkflowException e) {
            LOG.error("Initiate document error " + e.getMessage());
            throw new ExceptionAdapter(e, getLogger());
        } catch (Exception e) {
            throw new ExceptionAdapter(e, getLogger());
        }

    }

    private void addHolidayTimeBlocks(TimesheetDocument timesheetDocument, List holidayList) throws Exception {
        try {
            List timeBlocks = new TypedPersistentMaintainedEntityList(TimeBlock.class);
            for (Iterator jobListIterator = timesheetDocument.getJobs().iterator(); jobListIterator.hasNext();) {
                Job job = (Job)jobListIterator.next();
                if (!job.isHourly()) { //holiday pto blocks don't apply to hourly employees
                    List assignmentList = (List)job.getAssignments();
                    //If there are multiple assignments, just grabs the first one and puts holiday hours on it
                    if (assignmentList.size() > 0) {
                        Assignment assignment = (Assignment)assignmentList.get(0);
                        for (Iterator holidayListIterator = holidayList.iterator(); holidayListIterator.hasNext();) {
                            TimeBlock timeBlock = new TimeBlock();
                            timeBlock.setDocumentId(timesheetDocument.getDocumentId());
                            timeBlock.setEmployeeRecord(job.getEmployeeRecord());
                            timeBlock.setWorkArea(assignment.getWorkArea());
                            timeBlock.setTask(assignment.getTask());
                            timeBlock.setEarnCode(HOLIDAY_EARN_CODE);
                            timeBlock.setHours(job.getStandardHours().multiply(new BigDecimal(8)).divide(new BigDecimal(40), BigDecimal.ROUND_DOWN).setScale(2));
                            Timestamp timestamp = new Timestamp();
                            timestamp.setDate(((Date)((Holiday)holidayListIterator.next()).getHolidayDate()));
                            timestamp.setHour(new BigDecimal(12));
                            timestamp.setMinute(new BigDecimal(0));
                            timestamp.setSecond(new BigDecimal(0));
                            timestamp.setAmPm("PM");
                            timeBlock.setBeginTime(timestamp);
                            timeBlock.setEndTime(timestamp);
                            String location = "BL"; //default location
                            try {
                              location=assignment.getJob().getLocation();
                            } catch (Exception e) {
                              LOG.error("Not able to determine Holiday timezone for docId " + timesheetDocument.getDocumentId() + ". Error: " + e.getMessage());
                            }
                            String timeZone = TimeZoneUtility.getTimeZoneThreeLetterCode(location, timestamp.getDate().getTime());
                            timeBlock.setBeginTsTz(timeZone);
                            timeBlock.setEndTsTz(timeZone);
                            timeBlock.setTimestampTz(timeZone);
                            timeBlocks.add(timeBlock);
                        }
                    }
                }
            }
            if (Utility.hasValue(timeBlocks)) {
                save((TypedPersistentMaintainedEntityList)timeBlocks, getTimeBlockDataAccess());
            }
        } catch (Exception e) {
            LOG.error("Holiday TimeBlock creation error for document " + timesheetDocument.getDocumentId() + " " + e.getMessage());
            //don't throw exception, allowing timesheet creation
        }
    }

    public Document open(DocumentMetaData documentMetaData) {
        if (!Utility.hasValue(documentMetaData) || !Utility.hasValue(documentMetaData.getDocumentId())) {
            throw new IllegalArgumentException("TimesheetServiceImpl's open(DocumentMetaData documentMetaData) method requires a non-null instance of DocumentMetaData that has a non-null documentId");
        }
        TimesheetDocument timesheetDocument = getTimesheetDocument(documentMetaData.getDocumentId());
        timesheetDocument.setDocumentMetaData(documentMetaData);
        timesheetDocument.setClock(TimeZoneUtility.adjustClock(timesheetDocument.getClock(), new java.util.Date().getTime(), determineLocation(timesheetDocument.getClock(), timesheetDocument.getDocumentHeader().getUniversityId())));
        return timesheetDocument;
    }

    public String save(Document document) {
        if (!Utility.hasValue(document) || !(document instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("TimesheetServiceImpl's save(Document document) method requires a non-null instance of TimesheetDocument");
        }
        String documentStatus = new String();
        save((TimesheetDocument)document);
        unlock(document);
        ((TimesheetDocument)document).getDocumentHeader().setDocumentLock(null);
        if (Context.isUsingWorkflow()) {
            documentStatus = super.save(document);
            ((TimesheetDocument)document).getDocumentHeader().setDocumentStatus(documentStatus);
            DocumentHeader documentHeader = ((TimesheetDocument)document).getDocumentHeader();
            //preventing writing timeblocks to the database once more when saving the document header:
            TypedPersistentMaintainedEntityList timeBlocks = new TypedPersistentMaintainedEntityList(TimeBlock.class);
            timeBlocks.addAll(documentHeader.getTimeBlocks());
            documentHeader.setTimeBlocks(new ArrayList());
            getDocumentHeaderDataAccess().store(((TimesheetDocument)document).getDocumentHeader());
            documentHeader.setTimeBlocks(timeBlocks);
        }
        return documentStatus;
    }

    public String approve(Document document) {
        if (!Utility.hasValue(document) || !(document instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("TimesheetServiceImpl's approve(Document document) method requires a non-null instance of TimesheetDocument");
        }
        String documentStatus = new String();
        save((TimesheetDocument)document);
        unlock(document);
        ((TimesheetDocument)document).getDocumentHeader().setDocumentLock(null);
        if (Context.isUsingWorkflow()) {
            documentStatus = super.approve(document);
            if (documentStatus != null) {
                ((TimesheetDocument)document).getDocumentHeader().setDocumentStatus(documentStatus);
                getDocumentHeaderDataAccess().store(((TimesheetDocument)document).getDocumentHeader());
            }
        }
        return documentStatus;
    }

    public String approve(Document document, String networkId) {
        if (Context.isUsingWorkflow()) {
            String documentStatus = super.approve(document, networkId);
            ((TimesheetDocument)document).getDocumentHeader().setDocumentStatus(documentStatus);
            DocumentHeader documentHeader = ((TimesheetDocument)document).getDocumentHeader();
            getDocumentHeaderDataAccess().store(documentHeader);
            return documentStatus;
        }
        return null;
    }

    public void finalize(Document document) {
        if (Context.isUsingWorkflow()) {
            DocumentHeader documentHeader = ((TimesheetDocument)document).getDocumentHeader();
            documentHeader.setDocumentStatus(DocumentConstants.FINAL);
            getDocumentHeaderDataAccess().store(documentHeader);
        }
    }

    public void cancel(Document document) {
        unlock(document);
        super.cancel(document);
    }

    public boolean isFinalApproved(String networkId, String documentId) {
        try {
            WorkflowDocument workflowDocument = new WorkflowDocument(new NetworkIdDTO(networkId), new Long(documentId));
            return workflowDocument.getRouteHeader().getDocRouteStatus().equals(DocumentConstants.FINAL);
        } catch (NumberFormatException e) {
            throw new ExceptionAdapter(e, getLogger());
        } catch (WorkflowException e) {
            throw new ExceptionAdapter(e, getLogger());
        }
    }

    public ClockLog getClockLog(String universityId) {
        ClockLog clockLog = getClockLogDataAccess().getClockLog(universityId);
        if (!Utility.hasValue(clockLog)) {
            clockLog = new ClockLog(universityId);
        }
        return clockLog;
    }

    public List lookupClockLogs(WorkAreaStatusInquirySearchCriteria searchCriteria) {
        List clockLogs = getClockLogDataAccess().lookupClockLogs(searchCriteria);

        Iterator clockLogsIterator = clockLogs.iterator();
        List inquiryResults = new ArrayList(clockLogs.size());
        while (clockLogsIterator.hasNext()) {
            ClockLog clockLog = (ClockLog)clockLogsIterator.next();
            WorkAreaStatusInquiry workAreaStatusInquiry = new WorkAreaStatusInquiry();
            workAreaStatusInquiry.setClockLog(clockLog);
            workAreaStatusInquiry.setName((getDirectoryService().getUserByEmployeeId(clockLog.getUniversityId())).getName());
            inquiryResults.add(workAreaStatusInquiry);
        }
        return inquiryResults;
    }

    public TypedPersistentMaintainedEntityList lookupClockLogsByEmployee(ClockLogSearchCriteria searchCriteria) {
        return getClockLogDataAccess().lookupClockLogsPerDate(searchCriteria);
    }


    public void saveClockLogByEmployee(ClockLog clockLog) {
        save(clockLog, getClockLogDataAccess());
    }
    @LogPerformanceMethod
    public void clockIn(TimesheetDocument timesheetDocument) {
        takeClockAction(timesheetDocument.getClock(), Clock.CLOCK_IN);
    }
    @LogPerformanceMethod
    public void clockOut(TimesheetDocument timesheetDocument) {
        saveTimeBlockCreatedByClock(timesheetDocument, Clock.CLOCK_OUT);
    }

    public void lunchIn(TimesheetDocument timesheetDocument) {
        takeClockAction(timesheetDocument.getClock(), Clock.LUNCH_IN);
    }

    public void lunchOut(TimesheetDocument timesheetDocument) {
        saveTimeBlockCreatedByClock(timesheetDocument, Clock.LUNCH_OUT);
    }

    public void breakIn(TimesheetDocument timesheetDocument) {
        takeClockAction(timesheetDocument.getClock(), Clock.BREAK_IN);
    }

    public void breakOut(TimesheetDocument timesheetDocument) {
        takeClockAction(timesheetDocument.getClock(), Clock.BREAK_OUT);
    }

    public void unlockDocument(TimesheetDocument timesheetDocument) {
        unlock(timesheetDocument);
        ((TimesheetDocument)timesheetDocument).getDocumentHeader().setDocumentLock(null);
    }

    /**
     * Precodition:
     * <ul>
     * <li>User has jurisdiction over assignment in hourDetail</li>
     * </ul>
     * Postconditions:
     * <ul>
     * <li>if HourDetail to be copied does not exists in timesheetDocument.hours.hoursDetails, it is added
     * (the add/copy case)</li>
     * <li>new HourDetail created for each checked CheckedPayCalendarDate</li>
     * <li>Each HourDetail associated with the correct HoursDetail (using date)</li>
     * <li>TimesheetDocument remains in edit mode</li>
     * <li>HourDetail items that were added or copied but not saved are placed on the form as well</li>
     * </ul>
     */

    public void createHourDetails(TypedPersistentMaintainedEntityList dates, TimesheetDocument timesheetDocument, HourDetail copyFromHourDetail) {
        // save the copy to the other dates that were checked
        List hoursDetails = timesheetDocument.getHours().getHoursDetails();
        Iterator datesIterator = dates.iterator();
        Iterator hoursDetailIterator = hoursDetails.iterator();
        while (datesIterator.hasNext() && hoursDetailIterator.hasNext()) {
            CheckedPayCalendarDate checkedDate = (CheckedPayCalendarDate)datesIterator.next();
            HoursDetail hoursDetail = (HoursDetail)hoursDetailIterator.next();
            hoursDetail.setTabStatus("true");
            if (checkedDate.isChecked()) {
                Date hoursDetailDate = hoursDetail.getDate();
                if (hoursDetailDate.compareTo(checkedDate.getPayCalendarDate()) == 0) {
                    HourDetail hourDetail = new HourDetail(copyFromHourDetail);
                    hourDetail.setUserUniversityId(getWebUserService().getUser().getUniversityId());
                    hourDetail.setHoursDetail(hoursDetail);
                    hoursDetail.getHourDetails().add(hourDetail);
                    copyFromHourDetail.getHoursDetail().setTabStatus("true");
                }
            }
        }
    }

    //No longer deleting - warning in case assignment data is not refreshed/available
    /*public void deleteClockAction(TimesheetDocument timesheetDocument) {
     Clock clock = timesheetDocument.getClock();
     getClockLogDataAccess().delete(timesheetDocument.getClock().getClockLog());
     timesheetDocument.setClock(new Clock(getClockLog(timesheetDocument.getDocumentHeader().getUniversityId())));
     addAnnotation(timesheetDocument.getDocumentId(), Context.getMessage(CLOCK_ACTION_DELETED, new String[] { clock.getClockLog().getEmployeeRecord().toString(), clock.getClockLog().getWorkArea().toString(), clock.getClockLog().getTask().toString(), clock.getClockLog().getClockTime().toString() }));
     }*/

    @SuppressWarnings("unchecked")
	public List getDropdownClockAssignments(TimesheetDocument timesheetDocument) {
        Clock clock = timesheetDocument.getClock();
        List options = new ArrayList();
        if (clock.isOnTheClock()) {
            Assignment workingAssignment = timesheetDocument.getAssignment(clock.getClockLog().getEmployeeRecord(), clock.getClockLog().getWorkArea(), clock.getClockLog().getTask(), clock.getClockLog().getClockTime());
            if (Utility.hasValue(workingAssignment)) {
                options.add(new Option(clock.getClockAssignment(), getAssignmentService().getAssignmentDescription(workingAssignment)));
            } else {
                options.add(new Option(clock.getClockAssignment(), ""));
            }
        } else {
            options.addAll(getAssignmentService().getDropdownSynchronousAssignments(timesheetDocument.getDocumentHeader().getUniversityId()));
        }
        return options;
    }

    public TypedPersistentMaintainedEntityList lookupLockedDocuments(DocumentLockSearchCriteria searchCriteria) {
        return getDocumentLockDataAccess().lookupLockedDocuments(searchCriteria);
    }

    public TypedPersistentMaintainedEntityList lookupDocumentHeaders(DocumentHeaderSearchCriteria searchCriteria) {
        return getDocumentHeaderDataAccess().lookupDocumentHeaders(searchCriteria);
    }

    public TypedPersistentMaintainedEntityList lookupTimeBlockHistory(TimeBlockHistorySearchCriteria searchCriteria) {
        return getTimeBlockHistoryDataAccess().lookupTimeBlockHistory(searchCriteria);
    }

    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(Date payEndDate, Timestamp startDate, Timestamp endDate) {
        return getTimeBlockHistoryDataAccess().getTimeBlockHistoryModifiedRecords(payEndDate, startDate,  endDate);
    }

    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(Date payEndDate, Timestamp startDate, Timestamp endDate, String documentId) {
        return getTimeBlockHistoryDataAccess().getTimeBlockHistoryModifiedRecords(payEndDate, startDate,  endDate, documentId);
    }

    public List getTimeBlockHistoryModifiedDocuments(Date payEndDate, Timestamp startDate, Timestamp endDate) {
        return getTimeBlockHistoryDataAccess().getTimeBlockHistoryModifiedDocuments(payEndDate, startDate,  endDate);
    }

    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(String documentId) {
        return getTimeBlockHistoryDataAccess().getTimeBlockHistoryModifiedRecords(documentId);
    }


    protected Logger getLogger() {
        return LOG;
    }

    protected String getWorkflowSuperUser() {
        return WORKFLOW_TK_SUPER_USER;
    }

    public String getEmployeeName(String universityId) {
    	User user = getDirectoryService().getUser(getNetworkId(universityId), false);
    	return user.getName() + " (" + user.getNetworkId() + ")";
    }

    public String getNetworkId(String universityId) {
    	return getDirectoryService().getNetworkIdByEmployeeId(universityId);
    }

    public void finishPreparingTimesheetDocument(TimesheetDocument timesheetDocument) {
    	timesheetDocument.setName(getEmployeeName(timesheetDocument.getDocumentHeader().getUniversityId()));
        sortTimeBlocksChronologically(timesheetDocument.getHours()); //use for displaying purposes only, orders timeblocks within each day based on start time
        setDefaultExpandedTabs(timesheetDocument.getHours()); //use for displaying purposes. Determines which time block should shown expanded for current date
        if (Utility.hasValue(timesheetDocument.getHours())) {
            timesheetDocument.setHoursSummary(new HoursSummary(timesheetDocument));
        }
        timesheetDocument.setAssignmentEarnCodes(populateAssignmentEarnCodes(timesheetDocument));
        timesheetDocument.setAssignmentTimeOffsets(populateAssignmentTimeOffsets(timesheetDocument));
        timesheetDocument.setServerLocationTimeOffset(populateServerLocationTimeOffset());
        timesheetDocument.setServerTimeMillisAtLoad(new Long(new Date().getTime()).toString());
        timesheetDocument.setHoursEntryEarnCodes(populateHoursEntryEarnCodes(timesheetDocument));
    }

    @SuppressWarnings("unchecked")
	public boolean hasHours(String docId){
    	List<TimeBlock> timeBlocks = getTimeBlocks(docId);
    	for (TimeBlock timeBlock : timeBlocks) {
    		if(timeBlock.getHours().intValue() > 0) {
    			return true;
    		}
    		if(timeBlock.getShiftHours().intValue() > 0) {
    			return true;
    		}
    		if(timeBlock.getPremiumHours().intValue() > 0) {
    			return true;
    		}
    		if(timeBlock.getOvertimeHours().intValue() > 0) {
    			return true;
    		}
   		}
    	return false;
    }

    private void save(TimesheetDocument timesheetDocument) {
        List timeBlocksToSave = new TypedPersistentMaintainedEntityList(TimeBlock.class);
        List timeBlocksDocumentHeader = new TypedPersistentMaintainedEntityList(TimeBlock.class);
        TypedPersistentMaintainedEntityList timeBlocksToDelete = new TypedPersistentMaintainedEntityList(TimeBlock.class);

        prepareHours(timesheetDocument);
        calculateShiftPayHours(timesheetDocument);
        calculateLunchHours(timesheetDocument);
        resetOvertimeHours(timesheetDocument.getHours());
        calculateDailyOvertime(timesheetDocument);
        calculateOvertime(timesheetDocument);
        timeBlocksToSave = convertHourDetailToTimeBlock(timesheetDocument);

        //handling timeblock history & saving/deleting just affected records
        timeBlocksDocumentHeader.addAll(timeBlocksToSave);
        Set originalTimeBlockSet = new HashSet(timesheetDocument.getDocumentHeader().getTimeBlocks());
        Set timeBlockSetToDelete = new HashSet(timesheetDocument.getDocumentHeader().getTimeBlocks());
        Set timeBlockSetToSave = new HashSet(timeBlocksToSave);

        timeBlockSetToDelete.removeAll(timeBlockSetToSave);

        timeBlocksToDelete.addAll(timeBlockSetToDelete);
        delete(timeBlocksToDelete, getTimeBlockDataAccess());
        archiveTimeBlocks(timeBlocksToDelete, ACTION_HISTORY_DELETED);

        timeBlockSetToSave.removeAll(originalTimeBlockSet);
        timeBlocksToSave.clear();
        timeBlocksToSave.addAll(timeBlockSetToSave);

        prepareToSaveTimesheet(((TimesheetDocument)timesheetDocument), timeBlocksToSave);
        save(((TimesheetDocument)timesheetDocument).getDocumentHeader(), getDocumentHeaderDataAccess());
        archiveTimeBlocks(timeBlocksToSave, ACTION_HISTORY_ADDED);

        ((TimesheetDocument)timesheetDocument).getDocumentHeader().setTimeBlocks(timeBlocksDocumentHeader);

        if (Utility.hasValue(timeBlocksToSave)) {
            //setting Hours once more to synchronize HourDetail with TimeBlock values (specially userEmployeeId which could have changed after prepareToSaveTimesheet())
            timesheetDocument.setHours(new Hours(((TimesheetDocument)timesheetDocument).getDocumentHeader(), ((TimesheetDocument)timesheetDocument).getHours().getPayCalendar()));
        }
        sortTimeBlocksChronologically(timesheetDocument.getHours());

// TODO - put this in a logic object for timesheet document and register it with the approve event.
//        String documentStatus = timesheetDocument.getDocumentHeader().getDocumentStatus();
//        if(StringUtils.equals("R", documentStatus) || StringUtils.equals("F", documentStatus)){
//        	try {
//        		User user = TKServiceLocator.getWebUserService().getUser();
//				WorkflowDocument wd = new WorkflowDocument(new EmplIdDTO(user.getUniversityId()),new Long(timesheetDocument.getDocumentId()));
//				for(ActionRequestDTO ar : wd.getActionRequests()){
//					// send an fyi to those in the route chain who have already taken the action notifying them that the timesheet was modified
//					if(ar.isDone() && ar.isUserRequest()){
//						// do not route to the user that is doing the action
//						if (!(StringUtils.equals(user.getNetworkId(), ar.getUserVO().getNetworkId()))) {
//							// don't send to the user
//							String employeeNetworkId = getDirectoryService().getNetworkIdByEmployeeId(timesheetDocument.getDocumentHeader().getUniversityId());
//							if (!StringUtils.equals(employeeNetworkId, ar.getUserVO().getNetworkId())) {
//								wd.appSpecificRouteDocumentToUser(KEWConstants.ACTION_REQUEST_FYI_REQ, "Timesheet was modified by " + user.getNetworkId() + " for this document after it was approved.", new NetworkIdDTO(ar.getUserVO().getNetworkId()), "", true);
//							}
//						}
//					}
//				}
//        	} catch (WorkflowException e) {
//        		LOG.error("Cannot generate FYI for document "+timesheetDocument.getDocumentId());
//			}
//        }
    }

    private void archiveTimeBlocks(List timeBlocks, String actionHistory) {
        TypedPersistentMaintainedEntityList timeBlockHistoryList = new TypedPersistentMaintainedEntityList(TimeBlockHistory.class);

        for (Iterator iter = timeBlocks.iterator(); iter.hasNext();) {
            TimeBlock timeBlock = (TimeBlock)iter.next();
            TimeBlockHistory timeBlockHistory = new TimeBlockHistory(timeBlock);
            timeBlockHistory.setActionHistory(actionHistory);
            timeBlockHistory.setModifiedByUniversityId(getWebUserService().getUser().getUniversityId());
            /*if (!Utility.hasValue(timeBlockHistory.getUserUniversityId())) { //used by clock actions
             timeBlockHistory.setUserUniversityId(getWebUserService().getUser().getUniversityId());
             }*/
            timeBlockHistoryList.add(timeBlockHistory);
        }
        save(timeBlockHistoryList, getDataAccess(TimeBlockHistory.class));
    }

    private void calculateLunchHours(TimesheetDocument timesheetDocument) {

        SystemLunchRule systemLunchRule = getRuleService().getCachedEffectiveSystemLunchRule(timesheetDocument.getHours().getPayCalendar().getPayBeginDate());
        if (!Utility.hasValue(systemLunchRule)) {
            throw new SystemLunchRuleNotFoundException(SystemLunchRule.SYSTEM_LUNCH_RULE_NOT_FOUND, LOG);
        }
        BigDecimal blockHours = systemLunchRule.getBlockHours();

        try {
            Map maxMinutesMap = new HashMap();
            Iterator hoursDetailsItr = timesheetDocument.getHours().getHoursDetails().iterator();
            while (hoursDetailsItr.hasNext()) {
                HoursDetail hoursDetail = (HoursDetail)hoursDetailsItr.next();
                Iterator hourDetailsItr = hoursDetail.getHourDetails().iterator();
                while (hourDetailsItr.hasNext()) {
                    HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
                    if (earningService.isShiftEligibleEarnCode(hourDetail.getEarnCode(), hoursDetail.getDate())) {
                        if (Utility.hasValue(hourDetail.getHours()) && hourDetail.getHours().compareTo(blockHours) >= 0) {
                            Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());

                            if (!maxMinutesMap.containsKey((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA))) {
                                Job job = getJobByWorkArea(timesheetDocument, (BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA));
                                if (Utility.hasValue(job)) {
                                    DepartmentLunchRule departmentLunchRule = getRuleService().getActiveDepartmentLunchRule(job.getDepartment(), (BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), (String)assignmentKeyMap.get(FieldNames.UNIVERSITY_ID), (BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hoursDetail.getDate());
                                    if (Utility.hasValue(departmentLunchRule)) {
                                        if (!departmentLunchRule.isClockUseRequired()) {
                                            maxMinutesMap.put((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), departmentLunchRule.getMaxMinutes());
                                        }
                                    }
                                }
                            }
                            if (!maxMinutesMap.containsKey((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA))) {
                                maxMinutesMap.put((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), new BigDecimal(0));
                            }

                            if (Utility.hasValue(hourDetail.getHours())) {
                                hourDetail.setHours(hourDetail.getHours().subtract(((BigDecimal)maxMinutesMap.get((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA))).divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP)));
                            }
                            if (Utility.hasValue(hourDetail.getPremiumHours())) {
                                hourDetail.setPremiumHours(hourDetail.getPremiumHours().subtract(((BigDecimal)maxMinutesMap.get((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA))).divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP)));
                                if (hourDetail.getPremiumHours().compareTo(new BigDecimal(0)) < 0) {
                                    hourDetail.setPremiumHours(new BigDecimal(0));
                                }
                            }
                            if (Utility.hasValue(hourDetail.getShiftHours())) {
                                hourDetail.setShiftHours(hourDetail.getShiftHours().subtract(((BigDecimal)maxMinutesMap.get((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA))).divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP)));
                                if (hourDetail.getShiftHours().compareTo(new BigDecimal(0)) < 0) {
                                    hourDetail.setShiftHours(new BigDecimal(0));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error calculating Lunch Hours");

        }
    }

    private Job getJobByWorkArea(TimesheetDocument timesheetDocument, BigDecimal workArea) {
        Iterator jobIterator = timesheetDocument.getJobs().iterator();
        while (jobIterator.hasNext()) {
            Job job = (Job)jobIterator.next();
            Iterator assignmentIterator = job.getAssignments().iterator();
            while (assignmentIterator.hasNext()) {
                if (((Assignment)assignmentIterator.next()).getWorkArea().equals(workArea)) {
                    return job;
                }
            }
        }
        return null;
    }


    //Method used ONLY when there is a timeblock within 12:00AM-3:00AM on the FIRST day of the pay period.
    //Checks if there are any Daily Overtime eligible hours on the previous day (which belongs to the previous pay period)
    //if so, updates the accumulativeHoursPerAssignmentMap and lastEndTimePerAssignmentMap values
    private void calculateDailyOvertimeForPreviousPayPeriodLastDay(TimesheetDocument timesheetDocument, List earnCodeDailyOverTimeList, Map accumulativeHoursPerAssignmentMap, Map lastEndTimePerAssignmentMap) {
    	Date lastDayPreviousPayPeriod = new TimelessDate(timesheetDocument.getHours().getPayCalendar().getPayBeginDate()).addDays(-1).getDate();
    	DocumentHeader previousDocHeader = getDocumentHeaderDataAccess().getDocumentHeaderLight(timesheetDocument.getDocumentHeader().getUniversityId(), lastDayPreviousPayPeriod);

    	if (Utility.hasValue(previousDocHeader)) {

        	  List lastDayHourDetails = new ArrayList();
        	  List lastDayTimeblocks = this.getTimeBlockDataAccess().getTimeBlocks(previousDocHeader.getDocumentId(), lastDayPreviousPayPeriod);

        	  if (Utility.hasValue(lastDayTimeblocks)) {
		        	  //sorting timeblocks chronologically
		        	  for (Iterator lastDayTimeblocksIt = lastDayTimeblocks.iterator(); lastDayTimeblocksIt.hasNext();){
		        		  TimeBlock lastDayTimeblock = (TimeBlock) lastDayTimeblocksIt.next();
		        		  HourDetail lastDayHourDetail = this.convertToHourDetail(lastDayTimeblock);
		        		  lastDayHourDetail.setAssignment(lastDayTimeblock.getAssignment(timesheetDocument.getDocumentHeader().getUniversityId()));
		        		  lastDayHourDetail.setOvertimeEarnCode(lastDayTimeblock.getOvertimeEarnCode());
		        		  if (!Utility.hasValue(lastDayTimeblock.getOvertimeHours())){
		        			  lastDayHourDetail.setOvertimeHours(new BigDecimal(0));
		        		  } else { //deflating CPE hours
		        			  BigDecimal lastDayHourDetailOvertime = lastDayTimeblock.getOvertimeHours();
							  if (Utility.hasValue(lastDayTimeblock.getOvertimeEarnCode()) && getEarningService().isCompensationEarningsEarnCode(lastDayTimeblock.getOvertimeEarnCode(), previousDocHeader.getPayEndDate())) { //inflates overtime by 1.5
									lastDayHourDetailOvertime = (lastDayTimeblock.getOvertimeHours().divide(new BigDecimal(1.5)).setScale(2, BigDecimal.ROUND_HALF_UP));
							   }
							   lastDayHourDetail.setOvertimeHours(lastDayHourDetailOvertime);
		        		  }
		        		  lastDayHourDetails.add(lastDayHourDetail);
		        	  }
		        	  Collections.sort(lastDayHourDetails);


		          	  for (Iterator lastDayHourDetailsIt = lastDayHourDetails.iterator(); lastDayHourDetailsIt.hasNext();){
		          		HourDetail lastDayTimeblock = (HourDetail) lastDayHourDetailsIt.next();

		        		  if (earnCodeDailyOverTimeList.contains(lastDayTimeblock.getEarnCode())) {
				    			BigDecimal accumulativeHours = new BigDecimal(0);
				            	Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(lastDayTimeblock.getAssignment());
				            	WorkArea lastDayWorkArea = assignmentService.getCurrentWorkArea((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), timesheetDocument.getDocumentHeader().getPayEndDate());
				            	DailyOvertimeRule lastDayDailyOvertimeRule = getRuleService().getDailyOvertimeRule(lastDayWorkArea.getDepartment(),(BigDecimal)assignmentKeyMap.get("workArea"), (BigDecimal)assignmentKeyMap.get("task"), timesheetDocument.getDocumentHeader().getPayEndDate());
			                	String assignmentKey = assignmentKeyMap.get(FieldNames.WORK_AREA).toString() + assignmentKeyMap.get(FieldNames.TASK).toString();

				            	if (Utility.hasValue(lastDayDailyOvertimeRule) && Utility.hasValue(lastDayDailyOvertimeRule.getWorkArea())) {

				             			if ( !Utility.hasValue(lastEndTimePerAssignmentMap.get(assignmentKey))) { //sets value from first timeblock
				               				lastEndTimePerAssignmentMap.put(assignmentKey, new TimedDate(previousDocHeader.getPayEndDate(),lastDayTimeblock.getEndTime()));
				               			}
				             			TimedDate lastEndTime = (TimedDate) lastEndTimePerAssignmentMap.get(assignmentKey);

				                		if (!Utility.hasValue(accumulativeHoursPerAssignmentMap.get(assignmentKey))){//first timeblock
				                			accumulativeHoursPerAssignmentMap.put(assignmentKey,lastDayTimeblock.getHours().add(lastDayTimeblock.getOvertimeHours()));
				                		} else {
				                     			if (blocksGapMinute(lastEndTime.getTime(), lastDayTimeblock.getBeginTime()) <= lastDayDailyOvertimeRule.getMaxGap().doubleValue()){
				                     				accumulativeHours = (BigDecimal)accumulativeHoursPerAssignmentMap.get(assignmentKey);
				                       			    accumulativeHoursPerAssignmentMap.put(assignmentKey, accumulativeHours.add(lastDayTimeblock.getHours()).add(lastDayTimeblock.getOvertimeHours()));
				                     			} else {
				                					accumulativeHoursPerAssignmentMap.put(assignmentKey, lastDayTimeblock.getHours());//resetting value
				                     			}
				                 		}

				                		lastEndTimePerAssignmentMap.put(assignmentKey, new TimedDate(previousDocHeader.getPayEndDate(),lastDayTimeblock.getEndTime()));
				            	}
			        	   }
			         }//for
        	  }
	    }
    }


    private void calculateDailyOvertime(TimesheetDocument timesheetDocument) {
    	int currentTimeblock = 0;
    	try {
    	//retrieving earn codes to calculate daily overtime (earn codes are exactly the same as the Weekly overtime but this could change)
    		//get through Spring to leverage cache
    	TypedPersistentMaintainedEntityList overtimeRulesList = ((TimesheetService)Context.getService(TimesheetService.class)).getCachedWeeklyOvertimeRule();
        WeeklyOvertimeRule weeeklyOvertimeRule = (WeeklyOvertimeRule)overtimeRulesList.get(0);
        List earnCodeDailyOverTimeList = getEarningService().getEarnCodesByEarnProgram(weeeklyOvertimeRule.getMaxHoursEarnProgram(), timesheetDocument.getDocumentHeader().getPayEndDate());
        Date payEndDate = timesheetDocument.getDocumentHeader().getPayEndDate();
        Iterator hoursDetailsItr = timesheetDocument.getHours().getHoursDetails().iterator();
        Map accumulativeHoursPerAssignmentMap = new HashMap();
        Map lastEndTimePerAssignmentMap = new HashMap();
        boolean alreadyCheckedLastPayPeriod = false;
        Time lastPayPeriodMinimumGap = new Time("03:01 AM");//Checks previous DOT on previous pay period if there is a timeblock on this pay period's first day starting before this time.

        while (hoursDetailsItr.hasNext()) {
            HoursDetail hoursDetail = (HoursDetail)hoursDetailsItr.next();
            Iterator hourDetailsItr = hoursDetail.getHourDetails().iterator();
            currentTimeblock++;
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsItr.next();

                if (earnCodeDailyOverTimeList.contains(hourDetail.getEarnCode())) {

                	//getting active Daily Overtime Rule for this assignment on this pay period
                	Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
                	WorkArea workArea = assignmentService.getCurrentWorkArea((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), payEndDate);
                	DailyOvertimeRule dailyOvertimeRule = getRuleService().getDailyOvertimeRule(workArea.getDepartment(),(BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), (BigDecimal)assignmentKeyMap.get(FieldNames.TASK), payEndDate);
                	String assignmentKey = assignmentKeyMap.get(FieldNames.WORK_AREA).toString() + assignmentKeyMap.get(FieldNames.TASK).toString();

                	BigDecimal accumulativeHours = new BigDecimal(0);

                	if (Utility.hasValue(dailyOvertimeRule) && Utility.hasValue(dailyOvertimeRule.getWorkArea())) {

		               			//checking if we need to consider hours from previous pay period's last day
		                        if (hoursDetail.getDate().equals(timesheetDocument.getHours().getPayCalendar().getPayBeginDate())){
		                        	if (!alreadyCheckedLastPayPeriod && hourDetail.getBeginTime().compareTo(lastPayPeriodMinimumGap) < 0){
		                        		alreadyCheckedLastPayPeriod=true;
                        			    calculateDailyOvertimeForPreviousPayPeriodLastDay(timesheetDocument, earnCodeDailyOverTimeList, accumulativeHoursPerAssignmentMap, lastEndTimePerAssignmentMap);
		                        	}
		                        }

		               			if ( !Utility.hasValue(lastEndTimePerAssignmentMap.get(assignmentKey))) { //sets value from first timeblock
		               				lastEndTimePerAssignmentMap.put(assignmentKey, new TimedDate(hoursDetail.getDate(),hourDetail.getEndTime()));
		               			}

		               			TimedDate lastEndTime = (TimedDate) lastEndTimePerAssignmentMap.get(assignmentKey);

		                		if  ( !Utility.hasValue(accumulativeHoursPerAssignmentMap.get(assignmentKey))){//first timeblock found
		                			accumulativeHoursPerAssignmentMap.put(assignmentKey,hourDetail.getHours());
		                		} else {
				        				if ((new TimedDate(hoursDetail.getDate(),hourDetail.getBeginTime()).getDate().getTime() - lastEndTime.getDate().getTime()) < 1000 * 60 * 60 * 12  &&
				    						 Math.abs(blocksGapMinute(lastEndTime.getTime(), hourDetail.getBeginTime())) <= dailyOvertimeRule.getMaxGap().doubleValue()) {
				                   			 accumulativeHours = (BigDecimal)accumulativeHoursPerAssignmentMap.get(assignmentKey);
				                			 accumulativeHours = accumulativeHours.add(hourDetail.getHours());
				                			 accumulativeHoursPerAssignmentMap.put(assignmentKey, accumulativeHours);

				        				} else {
				        					accumulativeHoursPerAssignmentMap.put(assignmentKey, hourDetail.getHours());//resetting value
				        				}
		                		}

                   			    accumulativeHours = (BigDecimal)accumulativeHoursPerAssignmentMap.get(assignmentKey);

		        			    if (accumulativeHours.compareTo(dailyOvertimeRule.getShiftHours()) > 0) {
		                			 if ( hourDetail.getHours().compareTo(accumulativeHours.subtract(dailyOvertimeRule.getShiftHours())) < 1){
		                 			    hourDetail.setOvertimeHours(hourDetail.getHours());
		                 			 } else {
		                 			    hourDetail.setOvertimeHours(accumulativeHours.subtract(dailyOvertimeRule.getShiftHours()));
		                 			 }
			                	    hourDetail.setHours(hourDetail.getHours().subtract(hourDetail.getOvertimeHours()));
									if (!Utility.hasValue(hourDetail.getOvertimeEarnCode())) { //updates overtimeEarnCode only if it hasn't been calculated, otherwise it might override changes done by supervisor
										hourDetail.setOvertimeEarnCode(dailyOvertimeRule.getOvertimePreference());
									}
									//Earncode earncode = getEarningService().getEarnCode(hourDetail.getOvertimeEarnCode(), hoursDetail.getDate());
									if (Utility.hasValue(hourDetail.getOvertimeEarnCode()) && getEarningService().isCompensationEarningsEarnCode(hourDetail.getOvertimeEarnCode(), hoursDetail.getDate())) { //inflates overtime by 1.5
									    hourDetail.setOvertimeHours(hourDetail.getOvertimeHours().multiply(new BigDecimal(1.5)).setScale(2, BigDecimal.ROUND_HALF_UP));
									}
		        			   }
        			    lastEndTimePerAssignmentMap.put(assignmentKey, new TimedDate(hoursDetail.getDate(),hourDetail.getEndTime()));
                	}//if rule
                }//if earncode
            }
          }
    	}
    	catch (Exception e) {
    		timesheetDocument.getHours().getHoursDetail(currentTimeblock--).getEntityWarnings().add(new String[] { FieldNames.OVERTIME_HOURS }, DAILY_OVERTIME_EXCEPTION + " " + e);
    	}
    }


    private void calculateOvertime(TimesheetDocument timesheetDocument) {
        if (!Utility.hasValue(timesheetDocument) || !(timesheetDocument instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("TimesheetServiceImpl's computeOvertime(TimesheetDocument timesheetDocument) method requires a non-null instance of TimesheetDocument");
        }
        //resetOvertimeHours(timesheetDocument.getHours());
        //get through spring to leverage cache
        TypedPersistentMaintainedEntityList overtimeRulesList = ((TimesheetService)Context.getService(TimesheetService.class)).getCachedWeeklyOvertimeRule();
        if (!Utility.hasValue(overtimeRulesList)) {
            return; //do nothing, no overtime rule found
        }
        WeeklyOvertimeRule weeeklyOvertimeRule = (WeeklyOvertimeRule)overtimeRulesList.get(0); //get first rule to retrieve earn codes to be used to calculate overtime per week
        List earnCodeMaxHoursList = getEarningService().getEarnCodesByEarnProgram(weeeklyOvertimeRule.getMaxHoursEarnProgram(), timesheetDocument.getDocumentHeader().getPayEndDate());

        for (int week = 1; week <= 2; week++) {
            Map overtimePreferencePerWorkArea = getOvertimePreferencePerWorkArea(getWorkAreasInTimesheet(timesheetDocument), timesheetDocument.getHours().getPayCalendar().getPayBeginDate());

            double overtime = calculateTotalOvertimeHoursPerWeek(week, timesheetDocument.getHours(), earnCodeMaxHoursList) - weeeklyOvertimeRule.getMaxHours().doubleValue();
            if (overtime > 0) {
                for (Iterator overtimeRulesListIt = overtimeRulesList.iterator(); overtimeRulesListIt.hasNext();) {
                    overtime = updateTimeBlockOvertimePerWeek(week, timesheetDocument.getHours(), overtime, (WeeklyOvertimeRule)overtimeRulesListIt.next(), overtimePreferencePerWorkArea, timesheetDocument.getDocumentHeader().getPayEndDate());
                }
            }
            if ((int)overtime > 0) { //error, all overtime should have already been distributed on this week
                timesheetDocument.getHours().getHoursDetail((week - 1) * 7).getEntityErrors().add(new String[] { FieldNames.OVERTIME_HOURS }, Context.getMessage(MessageKeyConstants.ERROR_WEEKLY_OVERTIME_REMAINING, new String[] { String.valueOf(new BigDecimal(overtime).setScale(2,BigDecimal.ROUND_HALF_UP)), String.valueOf(week) }));
            }
        }
        resetOvertimeEarnCodes(timesheetDocument.getHours()); //Removes OT earn codes if they don't have OT Hours
    }

    private void resetOvertimeHours(Hours hours) {
        Iterator hoursDetailsItr = hours.getHoursDetails().iterator();
        while (hoursDetailsItr.hasNext()) {
            HoursDetail hoursDetail = (HoursDetail)hoursDetailsItr.next();
            Iterator hourDetailsItr = hoursDetail.getHourDetails().iterator();
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
                hourDetail.setOvertimeHours(new BigDecimal(0).setScale(0));
            }
        }
    }

    private void resetOvertimeEarnCodes(Hours hours) {
        Iterator hoursDetailsItr = hours.getHoursDetails().iterator();
        while (hoursDetailsItr.hasNext()) {
            HoursDetail hoursDetail = (HoursDetail)hoursDetailsItr.next();
            Iterator hourDetailsItr = hoursDetail.getHourDetails().iterator();
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
                if (Utility.hasValue(hourDetail.getOvertimeHours()) && hourDetail.getOvertimeHours().setScale(2,BigDecimal.ROUND_HALF_UP).equals(new BigDecimal("0.00"))) {
                    hourDetail.setOvertimeEarnCode(edu.iu.uis.hr.Utility.getDefaultStringValue());
                }
            }
        }
    }

    private List getWorkAreasInTimesheet(TimesheetDocument timesheetDocument) {
        Set workAreas = new HashSet();
        List jobs = timesheetDocument.getJobs();
        for (Iterator jobIterator = jobs.iterator(); jobIterator.hasNext();) {
            Collection assignments = ((Job)jobIterator.next()).getAssignments();
            for (Iterator assignmentIterator = assignments.iterator(); assignmentIterator.hasNext();) {
                workAreas.add(((Assignment)assignmentIterator.next()).getWorkArea().toString());
            }
        }
        return new ArrayList(workAreas);
    }

    private Map getOvertimePreferencePerWorkArea(List workAreasInTimesheet, Date payBeginDate) {
        Map overtimePreferencePerWorkArea = new HashMap();
        List workAreaList = getAssignmentService().getWorkAreas(workAreasInTimesheet, payBeginDate);
        for (Iterator workAreaIterator = workAreaList.iterator(); workAreaIterator.hasNext();) {
            WorkArea workArea = (WorkArea)workAreaIterator.next();
            overtimePreferencePerWorkArea.put(workArea.getWorkArea(), workArea.getOvertimePreference());
        }
        return overtimePreferencePerWorkArea;
    }

    private double calculateTotalOvertimeHoursPerWeek(int week, Hours hours, List earnCodeMaxHoursList) {
        double totalWeeklyHours = 0.0;
        for (int i = (week - 1) * PayCalendar.SATURDAY; i < week * PayCalendar.SATURDAY; i++) {
            HoursDetail hoursDetail = hours.getHoursDetail(i);
            for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
                HourDetail hourDetail = (HourDetail)iter.next();
                if (Utility.hasValue(hourDetail.getEarnCode()) && earnCodeMaxHoursList.contains(hourDetail.getEarnCode())) {
                    if (Utility.hasValue(hourDetail.getHours())) {
                        totalWeeklyHours += hourDetail.getHours().doubleValue();
                    }
                }
            }
        }
        return totalWeeklyHours;
    }

    private double updateTimeBlockOvertimePerWeek(int week, Hours hours, double overtime, WeeklyOvertimeRule weeeklyOvertimeRule, Map overtimePreferencePerWorkArea, Date effectiveDate) {
        List convertFromEarnCodeList = getEarningService().getEarnCodesByEarnProgram(weeeklyOvertimeRule.getConvertFromEarnProgram(), effectiveDate);

        //checks earn codes starting from last timeblock for specific week
        for (int i = PayCalendar.SATURDAY * week; i > PayCalendar.SATURDAY * (week - 1); i--) {
            HoursDetail hoursDetail = hours.getHoursDetail(i - 1);
            List hourDetailList = hoursDetail.getHourDetails();
            Collections.sort(hourDetailList, Collections.reverseOrder());

            for (Iterator hourDetailIt = hourDetailList.iterator(); hourDetailIt.hasNext();) {
                HourDetail hourDetail = (HourDetail)hourDetailIt.next();
                if (Utility.hasValue(hourDetail.getHours()) && Utility.hasValue(hourDetail.getEarnCode()) && convertFromEarnCodeList.contains(hourDetail.getEarnCode()) && overtime > 0) {
                    BigDecimal originalHours = hourDetail.getHours();

                    //including existing daily overtime hours to weekly overtime calculation
                    if (Utility.hasValue(hourDetail.getOvertimeHours())) { // timeblock has daily overtime hours
                    	if ("CPE".equals(hourDetail.getOvertimeEarnCode())) { //deflating OT hours
                    		hourDetail.setOvertimeHours(hourDetail.getOvertimeHours().divide(new BigDecimal(1.5)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    	}
                        originalHours = originalHours.add(hourDetail.getOvertimeHours());
                        hourDetail.setHours(hourDetail.getHours().add(hourDetail.getOvertimeHours()));
                    }

                    if (hourDetail.getHours().doubleValue() > overtime) {
                        hourDetail.setOvertimeHours(new BigDecimal(overtime).setScale(2, BigDecimal.ROUND_HALF_UP).add(hourDetail.getOvertimeHours()));
                    } else {
                        hourDetail.setOvertimeHours(hourDetail.getHours().add(hourDetail.getOvertimeHours()));
                    }
                    hourDetail.setHours(hourDetail.getHours().subtract(hourDetail.getOvertimeHours()));

                    if (!Utility.hasValue(hourDetail.getOvertimeEarnCode())) { //updates overtimeEarnCode only if it hasn't been calculated, otherwise it might override changes done by supervisor
                        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
                        if (Utility.hasValue(overtimePreferencePerWorkArea.get((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA)))) {
                        	String overtimeEarnCode = (String)overtimePreferencePerWorkArea.get((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA));
                        	if ("BOT".equals(overtimeEarnCode)) {
                        		hourDetail.setOvertimeEarnCode("CPE");
                        	} else {
                              hourDetail.setOvertimeEarnCode((String)overtimePreferencePerWorkArea.get((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA)));
                        	}
                        } else {
                            hourDetail.setOvertimeEarnCode(weeeklyOvertimeRule.getConvertToEarnCode());
                        }
                    }
                    if (Utility.hasValue(hourDetail.getOvertimeEarnCode()) && getEarningService().isCompensationEarningsEarnCode(hourDetail.getOvertimeEarnCode(), effectiveDate)) { //inflates overtime by 1.5
                        hourDetail.setOvertimeHours(hourDetail.getOvertimeHours().multiply(new BigDecimal(1.5)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    overtime -= originalHours.doubleValue();
                    if (overtime <= 0) { //no need to proceed checking values in loop
                        return overtime;
                    }
                }
            }
        }
        return overtime;
    }

    //Method to sort timeblocks within each day  for displaying purposes
    private void sortTimeBlocksChronologically(Hours hours) {
        if (Utility.hasValue(hours)) { //avoids NPE
            for (int i = 0; i < PayCalendar.SATURDAY * 2; i++) {
                HoursDetail hoursDetail = hours.getHoursDetail(i);
                List hourDetailList = hoursDetail.getHourDetails();
                Collections.sort(hourDetailList);
            }
        }
    }

    //Sets current date's timeblock tab to be shown expanded
    private void setDefaultExpandedTabs(Hours hours) {
        GregorianCalendar today = new GregorianCalendar();
        GregorianCalendar day = new GregorianCalendar();
        today.setTime(new Date());

        if (Utility.hasValue(hours)) {
            Iterator hoursDetailsItr = hours.getHoursDetails().iterator();
            while (hoursDetailsItr.hasNext()) {
                HoursDetail hoursDetail = (HoursDetail)hoursDetailsItr.next();
                day.setTime(hoursDetail.getDate());
                if (today.get(Calendar.DAY_OF_YEAR) == day.get(Calendar.DAY_OF_YEAR)) { //expands current date
                    hoursDetail.setTabStatus("true");
                }
                Iterator hourDetailsItr = hoursDetail.getHourDetails().iterator();
                while (hourDetailsItr.hasNext()) { //expands  tabs with erroneous data
                    HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
                    if (hourDetail.isErroneous() || hourDetail.isWarned()) {
                        hoursDetail.setTabStatus("true");
                    }
                }
            }
        }
    }

    private void prepareHours(TimesheetDocument document) {
        Map dateToHourDetailsMap = document.getHours().getDateToHourDetailsMap();
        Iterator datesItr = dateToHourDetailsMap.keySet().iterator();
        while (datesItr.hasNext()) {
            TimelessDate timelessDate = (TimelessDate)datesItr.next();
            Iterator hourDetailsItr = ((List)dateToHourDetailsMap.get(timelessDate)).iterator();
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
                hourDetail.setHours(getCalculatedHours(document, hourDetail));
                hourDetail.setHoursDetail(new HoursDetail(timelessDate.getDate()));
            }
        }
    }

    private List convertHourDetailToTimeBlock(TimesheetDocument timesheetDocument) {
        List timeBlocks = new TypedPersistentMaintainedEntityList(TimeBlock.class);
        Iterator hoursDetailsItr = timesheetDocument.getHours().getHoursDetails().iterator();
        while (hoursDetailsItr.hasNext()) {
            HoursDetail hoursDetail = (HoursDetail)hoursDetailsItr.next();
            Iterator hourDetailsItr = hoursDetail.getHourDetails().iterator();
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
                timeBlocks.add(getTimeBlock(timesheetDocument.getDocumentHeader(), hourDetail));
            }
        }
        return timeBlocks;
    }

    private void prepareToSaveTimesheet(TimesheetDocument document, List timeBlocks) {
        setDerivedValuesOnUpdatedObjects((List)document.getDocumentHeader().getTimeBlocks(), timeBlocks);
        document.getDocumentHeader().setTimeBlocks(timeBlocks);
    }

    private List getTimeBlocksForDeletion(TimesheetDocument document) {
        List timeBlocks = new TypedPersistentMaintainedEntityList(TimeBlock.class);
        Map dateToHourDetailsMap = document.getHours().getDateToHourDetailsMap();
        Iterator datesItr = dateToHourDetailsMap.keySet().iterator();
        while (datesItr.hasNext()) {
            TimelessDate timelessDate = (TimelessDate)datesItr.next();
            Iterator hourDetailsItr = ((List)dateToHourDetailsMap.get(timelessDate)).iterator();
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
                hourDetail.setHoursDetail(new HoursDetail(timelessDate.getDate()));
                timeBlocks.add(getTimeBlock(document.getDocumentHeader(), hourDetail));
            }
        }
        return edu.iu.uis.hr.Utility.getRemovedObjects((List)document.getDocumentHeader().getTimeBlocks(), timeBlocks);
    }

    public TimeBlock getTimeBlock(DocumentHeader documentHeader, HourDetail hourDetail) {
        TimeBlock timeBlock = new TimeBlock();
        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
        timeBlock.setDocumentId(documentHeader.getDocumentId());
        timeBlock.setEmployeeRecord((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD));
        timeBlock.setWorkArea((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA));
        timeBlock.setTask((BigDecimal)assignmentKeyMap.get(FieldNames.TASK));
        timeBlock.setEarnCode(hourDetail.getEarnCode());
        timeBlock.setBeginTime(new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getBeginTime()).getTimestamp());
        timeBlock.setEndTime(new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getEndTime()).getTimestamp());
        timeBlock.setBeginTsTz(hourDetail.getBeginTsTz());
        timeBlock.setEndTsTz(hourDetail.getEndTsTz());
        timeBlock.setHours(hourDetail.getHours());
        timeBlock.setCreatedByClock(hourDetail.isCreatedByClock());
        timeBlock.setUserUniversityId(hourDetail.getUserUniversityId());
        timeBlock.setTimestamp(hourDetail.getTimestamp());
        timeBlock.setOvertimeHours(hourDetail.getOvertimeHours());
        timeBlock.setOvertimeEarnCode(hourDetail.getOvertimeEarnCode());
        timeBlock.setShiftEarnCode(hourDetail.getShiftEarnCode());
        timeBlock.setShiftHours(hourDetail.getShiftHours());
        timeBlock.setPremiumEarnCode(hourDetail.getPremiumEarnCode());
        timeBlock.setPremiumHours(hourDetail.getPremiumHours());
        timeBlock.setEntityWarnings(hourDetail.getEntityWarnings());
        return timeBlock;
    }

    private void setDerivedValuesOnUpdatedObjects(List compareToList, List comparisonList) {
        Iterator comparisonListItr = comparisonList.iterator();
        while (comparisonListItr.hasNext()) {
            PersistentMaintainedEntity entity = (PersistentMaintainedEntity)comparisonListItr.next();
            if (!compareToList.contains(entity)) {
                getDataAccess(entity.getClass()).setDerivedValues(entity, getWebUserService().getUser());
            }
        }
    }

    private void takeClockAction(Clock clock, String action) {
        Map assignmentKeys = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(clock.getClockAssignment());
        if (!Utility.hasValue(clock.getTimeZone())) {
            //This should only happen if the batch process ends the time block
            //it does not perform the logic to set the time zone that occurs on a clock action.
        	clock = setTimeZoneOnClock(clock);
        }
        //WorkArea workArea = TKServiceLocator.getAssignmentService().getWorkArea((BigDecimal)assignmentKeys.get(FieldNames.WORK_AREA), clock.getClockTime().getDate());

        //ClockLocationRuleSearchCriteria cLocSearchCriteria = new ClockLocationRuleSearchCriteria();
        //cLocSearchCriteria.setDepartment(workArea.getDepartment());
        //cLocSearchCriteria.setWorkArea((BigDecimal)assignmentKeys.get(FieldNames.WORK_AREA));
        //cLocSearchCriteria.setUniversityId((String)assignmentKeys.get(FieldNames.UNIVERSITY_ID));
        //cLocSearchCriteria.setEmployeeRecord((BigDecimal)assignmentKeys.get(FieldNames.EMPLOYEE_RECORD));

        //List<ClockLocationRule> locationsRules = TKServiceLocator.getRuleService().lookupMaintainableClockLocationRules(cLocSearchCriteria);

        ClockLog clockLog = new ClockLog(clock.getClockLog().getUniversityId(), (BigDecimal)assignmentKeys.get(FieldNames.EMPLOYEE_RECORD), (BigDecimal)assignmentKeys.get(FieldNames.WORK_AREA), (BigDecimal)assignmentKeys.get(FieldNames.TASK), clock.getClockTime(), clock.getTimeZone(), action);
        ClockLog lastClockAction = getClockLog(clock.getClockLog().getUniversityId());
        if (!Utility.hasValue(lastClockAction) || !action.equals(lastClockAction.getAction())) {
        	LOG.info("Saving Clock action "+action);
            save(clockLog, getClockLogDataAccess());
        }
        clock.update(getClockLog(clockLog.getUniversityId()));
    }

    private void saveTimeBlockCreatedByClock(TimesheetDocument timesheetDocument, String action) {
    	String networkId = getWebUserService().getUser().getNetworkId();
    	String beginTsTz = timesheetDocument.getClock().getClockLog().getTimeZone();
    	Timestamp clockInTimestamp = timesheetDocument.getClock().getClockLog().getClockTime();
    	String previousAction = timesheetDocument.getClock().getClockLog().getAction();
        takeClockAction(timesheetDocument.getClock(), action);

        if (timesheetDocument.getJobs().size() == 0){
        	//refreshing job list since clock-only view is not passing any job data.
    		timesheetDocument.setJobs(TKServiceLocator.getJobService().getJobs(timesheetDocument.getDocumentHeader().getUniversityId(), ((TimesheetDocument)timesheetDocument).getDocumentHeader().getPayEndDate()));
        }
    	if (timesheetDocument.isRunAsynchronously() &&  !(timesheetDocument.getClock().isUserDoingHourDistribution())) {
    		String val1 = null;
    		String val2 = null;
    		try {
    			TimedDate clockInTime = new TimedDate(clockInTimestamp);
    			TimedDate clockOutTime = new TimedDate(timesheetDocument.getClock().getClockTime());
	    		val1 = "Document Id: {" + timesheetDocument.getDocumentId() + "} Network Id: {" + networkId + "}";
	    		val2 = Clock.getClockActionDescription(previousAction) + " Time Stamp: {" + clockInTime.toString() + "}, Action attempted: " + Clock.getClockActionDescription(action) + " at Time Stamp: " + clockOutTime.toString() + ", beginTsTz: {" + beginTsTz + "}";
    		} catch (Exception e) {
	    		val1 = "Document Id: {" + timesheetDocument.getDocumentId() + "} Network Id: {" + networkId + "}";
	    		val2 = "Could not retrieve clock in/out timestamps - Please check clock log for employee assigned to document id " + timesheetDocument.getDocumentId();
    		}
    		long startTime = System.currentTimeMillis();
    		TimesheetService asyncTimesheetService = (TimesheetService)KSBServiceLocator.getMessageHelper().getServiceAsynchronously(new QName("TK", "timesheetService"), null, null, val1, val2);
   			asyncTimesheetService.saveTimeBlockAsyncronously(timesheetDocument.getDocumentId(), networkId, clockInTimestamp, beginTsTz);
   			LOG.fatal("****ASYNC TIME: "+(System.currentTimeMillis()-startTime));
//    		this.saveTimeBlockUsingTimesheet(timesheetDocument, clockInTimestamp, beginTsTz);
    	} else {
    		this.saveTimeBlockUsingTimesheet(timesheetDocument, clockInTimestamp, beginTsTz);
    	}
    }

    public void saveTimeBlockAsyncronously(String documentId, String networkId, Timestamp clockInTimestamp, String beginTsTz) {
    	TimesheetDocument timesheetDocument = this.getTimesheetDocument(documentId);
    	User user = TKServiceLocator.getDirectoryService().getUser(networkId);
    	edu.iu.uis.hr.tk.directory.entity.User tkUser = new UserImpl(user);
    	TKServiceLocator.getWebUserService().setUserSession(new UserSession(tkUser));
    	this.saveTimeBlockUsingTimesheet(timesheetDocument, clockInTimestamp, beginTsTz );
    }

    private void saveTimeBlockUsingTimesheet(TimesheetDocument timesheetDocument, Timestamp clockInTimestamp, String beginTsTz) {
    	//String beginTsTz = timesheetDocument.getClock().getClockLog().getTimeZone();
    	//Timestamp clockInTimestamp = timesheetDocument.getClock().getClockLog().getClockTime();
    	List timeBlocksForLastPayPeriod = new TypedPersistentMaintainedEntityList(TimeBlock.class);
    	Date startTime = new Date();

        List timeBlocks = new TypedPersistentMaintainedEntityList(TimeBlock.class);
        List timeBlocksDocumentHeader = new TypedPersistentMaintainedEntityList(TimeBlock.class);
        TimesheetDocument lastPeriodTimesheet = null;

        Set originalTimeBlockSet = new HashSet(timesheetDocument.getDocumentHeader().getTimeBlocks());
        Set timeBlockSetToSave = new HashSet();

        Job workingJob = timesheetDocument.getJob(timesheetDocument.getClock().getClockLog().getEmployeeRecord(), timesheetDocument.getClock().getClockLog().getClockTime());
        String earnCode = getEarningService().getPaygroup(workingJob.getCompany(), workingJob.getPaygroup(), new Date()).getRegularHoursEarnCode();

        Timestamp clockOutTimestamp = timesheetDocument.getClock().getClockLog().getClockTime();

        //Preventing creating of multiple timeblocks when somebody forgets to clock out after 24 hours
        if  (Timestamp.getMillisecondDifference(clockOutTimestamp, clockInTimestamp).longValue() / 3600000 > Clock.MAX_CLOCK_IN_HOURS) {
        	clockOutTimestamp = new Timestamp();
        	clockOutTimestamp.setDate(clockInTimestamp.getDate());
        	clockOutTimestamp.setHour(new BigDecimal(11));
        	clockOutTimestamp.setMinute(new BigDecimal(59));
        	clockOutTimestamp.setAmPm("PM");
        	if (!StringUtils.equals(getWebUserService().getUser().getUniversityId(),timesheetDocument.getDocumentHeader().getUniversityId())){
        		try {
	        		WorkflowDocument wd = new WorkflowDocument(new EmplIdDTO(timesheetDocument.getDocumentHeader().getUniversityId()), new Long(timesheetDocument.getDocumentHeader().getDocumentId()));
	        		wd.adHocRouteDocumentToPrincipal(KEWConstants.ACTION_REQUEST_FYI_REQ, Clock.clockedInTimeExceededMessage, HredocServiceLocator.getHreUserGroupService().convertPrincipalNameToId(this.getNetworkId(timesheetDocument.getDocumentHeader().getUniversityId())), "Supervisor", true);
        		}
        		catch (Exception e) {
        			LOG.error("Error sending exceeded clocked-in time for document " + timesheetDocument.getDocumentHeader().getDocumentId());
        		}
        	}
        }

        String endTsTz = timesheetDocument.getClock().getTimeZone();
        if(!Utility.hasValue(endTsTz) || !Utility.hasValue(beginTsTz)){
        //This should only happen if the batch process ends the time block
        //it does not perform the logic to set the time zone that occur on a clock action.
        	timesheetDocument.setClock(setTimeZoneOnClock(timesheetDocument.getClock()));
            beginTsTz = timesheetDocument.getClock().getClockLog().getTimeZone();
            endTsTz = timesheetDocument.getClock().getTimeZone();
        }
        TimelessDate counterDate = new TimelessDate(clockInTimestamp.getDate());
        TimelessDate clockOutDate = new TimelessDate(clockOutTimestamp.getDate());

        Timestamp timeBlockBeginTimestamp = new TimedDate(clockInTimestamp).getTimestamp();
        Timestamp timeBlockEndTimestamp = new TimedDate(clockOutTimestamp).getTimestamp();

        TimelessDate previousPayPeriodBeginDate = new TimelessDate(timesheetDocument.getHours().getPayCalendar().getPayBeginDate()).addDays(-14);
        if (new TimelessDate(timeBlockBeginTimestamp.getDate()).compareTo(previousPayPeriodBeginDate) < 0 || new TimelessDate(timeBlockEndTimestamp.getDate()).compareTo(new TimelessDate(timesheetDocument.getDocumentHeader().getPayEndDate())) > 0) {
            throw new RuntimeException("Error: Trying to add a timeblock in wrong Pay Period. Current pay period's end date:  " + timesheetDocument.getDocumentHeader().getPayEndDate() + ". Timeblock date:  " + timeBlockBeginTimestamp.getDate());
        }

        while (counterDate.compareTo(clockOutDate) <= 0) {
            TimeBlock timeBlock = new TimeBlock(timesheetDocument.getDocumentId(), timesheetDocument.getClock().getClockLog().getEmployeeRecord(), timesheetDocument.getClock().getClockLog().getWorkArea(), timesheetDocument.getClock().getClockLog().getTask(), earnCode, timeBlockBeginTimestamp, timeBlockEndTimestamp, beginTsTz, endTsTz, true);
            if (!new TimelessDate(timeBlockBeginTimestamp.getDate()).equals(new TimelessDate(timeBlockEndTimestamp.getDate()))) {
                timeBlock.setEndTime(new TimedDate(timeBlockBeginTimestamp.getDate(), Clock.END_OF_DAY_TIME).getTimestamp());
                timeBlockBeginTimestamp = new TimedDate(new TimedDate(timeBlockBeginTimestamp).addDays(1).getDate(), Clock.BEGIN_OF_DAY_TIME).getTimestamp();
            }

            timeBlock.setHours(getCalculatedHours(timesheetDocument, timeBlock));
            timeBlock.setUserUniversityId(getWebUserService().getUser().getUniversityId());
            TimedDate timestamp = new TimedDate(new Date());
            timeBlock.setTimestamp(timestamp.getTimestamp());

            if (counterDate.compareTo(new TimelessDate(timesheetDocument.getHours().getPayCalendar().getPayBeginDate())) < 0) { //timeblocks belong to last pay period
                if (!Utility.hasValue(lastPeriodTimesheet)) {
                    try {
                        lastPeriodTimesheet = getTimesheetDocument(timesheetDocument.getDocumentHeader().getUniversityId(), new TimelessDate(timesheetDocument.getHours().getPayCalendar().getPayBeginDate()).addDays(-1).getDate());
                    } catch (Exception e) {
                        throw new RuntimeException("Error: Trying to add a timeblock to a non existing timesheet for previous pay period.");
                    }
                }
                if (Utility.hasValue(lastPeriodTimesheet)) {
                    timeBlock.setDocumentId(lastPeriodTimesheet.getDocumentId());
                    timeBlocksForLastPayPeriod.add(timeBlock);
                }
            } else {
                timeBlocks.add(timeBlock);
                if (timesheetDocument.getClock().isUserDoingHourDistribution()) { //stores timeblocks to distribute hours
                    HourDetail hourDetailToDistribute = convertToHourDetailToDistribute(timeBlock);
                    hourDetailToDistribute.setAssignment(timeBlock.getAssignment(timesheetDocument.getDocumentHeader().getUniversityId()));
                    hourDetailToDistribute.setHoursDetail(new HoursDetail());
                    hourDetailToDistribute.getHoursDetail().setDate(timeBlock.getBeginTime().getDate());
                    timesheetDocument.setTimeBlockToDistribute((HoursDetailToDistribute)hourDetailToDistribute);
                    initializeDistributedHours(timesheetDocument);
                    timesheetDocument.getTimeBlockToDistributeLists().add(timesheetDocument.getTimeBlockToDistribute());
                }
            }
            counterDate.addDays(1);
        }

        //saves timeblocks for last pay period (needs to save these first for daily overtime to consider previous pay period)
        if (Utility.hasValue(timeBlocksForLastPayPeriod)) {
            prepareHours(lastPeriodTimesheet);
            calculateShiftDifferentialPayForClockAction(lastPeriodTimesheet, timeBlocksForLastPayPeriod);
            calculateLunchHours(lastPeriodTimesheet);
            resetOvertimeHours(lastPeriodTimesheet.getHours());
            calculateDailyOvertime(lastPeriodTimesheet);
            calculateOvertime(lastPeriodTimesheet);
            timeBlocksForLastPayPeriod = convertHourDetailToTimeBlock(lastPeriodTimesheet);
            archiveTimeBlocks(timeBlocksForLastPayPeriod, ACTION_HISTORY_RETRO); //order matters, first need to archive
            super.save((TypedPersistentMaintainedEntityList)timeBlocksForLastPayPeriod, getTimeBlockDataAccess());
        }
        prepareHours(timesheetDocument);
        calculateShiftDifferentialPayForClockAction(timesheetDocument, timeBlocks);
        calculateLunchHours(timesheetDocument);
        resetOvertimeHours(timesheetDocument.getHours());
        calculateDailyOvertime(timesheetDocument);
        calculateOvertime(timesheetDocument);

        //handling timeblock history & saving just updated records
        timeBlocks = convertHourDetailToTimeBlock(timesheetDocument);
        timesheetDocument.getDocumentHeader().setTimeBlocks(timeBlocks);
        timeBlockSetToSave.addAll(timeBlocks);
        timeBlockSetToSave.removeAll(originalTimeBlockSet);
        timeBlocks.clear();
        timeBlocks.addAll(timeBlockSetToSave);
        archiveTimeBlocks(timeBlocks, ACTION_HISTORY_CLOCKED);
        super.save((TypedPersistentMaintainedEntityList)timeBlocks, getTimeBlockDataAccess());

        //setting Hours once more to synchronize HourDetail with TimeBlock values (specially userEmployeeId and createdByClock)
        timesheetDocument.setHours(new Hours(((TimesheetDocument)timesheetDocument).getDocumentHeader(), ((TimesheetDocument)timesheetDocument).getHours().getPayCalendar()));
        sortTimeBlocksChronologically(timesheetDocument.getHours());

    	// Auditing SavedByClock Processing Time
        Date endDate = new Date();
        long totalTime = (endDate.getTime() - startTime.getTime()) / 1000;
        LOG.error("Time audit. DocId: " + timesheetDocument.getDocumentId()  + " SavedByClock Processing Time: " + totalTime);

        //Checking if there are new jobs within pay period to update workflow document
        Iterator iter = getJobService().getJobs(((TimesheetDocument)timesheetDocument).getDocumentHeader().getUniversityId(), ((TimesheetDocument)timesheetDocument).getDocumentHeader().getPayEndDate()).iterator();
        boolean needToUpdateTitle = false;
        while (iter.hasNext()) {
        	Job job = (Job) iter.next();
        	if (job.getEffectiveDate().compareTo(((TimesheetDocument)timesheetDocument).getHours().getPayCalendar().getPayBeginDate()) >= 0){
        		needToUpdateTitle = true;
        	}
        }
        startTime = new Date();
        if (needToUpdateTitle){
            try {
	            WorkflowDocument workflowDocument = new WorkflowDocument(new EmplIdDTO(timesheetDocument.getDocumentHeader().getUniversityId()), new Long(timesheetDocument.getDocumentId()));
	            workflowDocument.setApplicationContent(timesheetDocument.getDocumentElement().buildXml());
	            if (!"I".equals(workflowDocument.getRouteHeader().getDocRouteStatus())) {
	            	workflowDocument = updateWorkflowTitle(timesheetDocument, workflowDocument);
	            }

	            if (Utility.hasValue(workflowDocument)){
	               workflowDocument.saveDocument(edu.iu.uis.hr.Utility.getDefaultStringValue());
	            }
	        } catch (WorkflowException e) {
	            LOG.error("Update document title error " + e.getMessage());
	            throw new ExceptionAdapter(e, getLogger());
	        } catch (Exception e) {
	            throw new ExceptionAdapter(e, getLogger());
	        }
        }
    	// Auditing Workflow time
        endDate = new Date();
        totalTime = (endDate.getTime() - startTime.getTime()) / 1000;
        LOG.error("Time audit. DocId: " + timesheetDocument.getDocumentId()  + " Workflow Time: " + totalTime);

    }

    private Clock setTimeZoneOnClock(Clock clock){
        Assignment assignment = assignmentService.getAssignment((clock).getAssignment(), ((clock).getClockTime().getDate()));
        if(Utility.hasValue(assignment) || Clock.ON_THE_CLOCK_CODES.contains((clock).getClockLog().getAction())){
        	String location = null;
        	if(Utility.hasValue(assignment)){
        		location = assignment.getJob().getLocation();
        	}else{
        		location = clock.getClockLog().getTimeZone();
        	}
           	clock = TimeZoneUtility.setZone(clock, location);
        }
        return clock;
    }

    private void initializeDistributedHours(TimesheetDocument timesheet) {
        HourDetail hourDetail = new HourDetailDistribution();
        timesheet.getTimeBlockToDistribute().getDistributedHours().add(hourDetail);
    }

    //If return value is set to true, it will display the Hour Distribution checkbox within the Clock section
    public boolean isHourDistributionAllowed(TimesheetDocument timesheetDocument) {
        TimeCollectionRule timeCollectionRule = new TimeCollectionRule();

        TimedDate today = new TimedDate(new Date());
        if (Timestamp.getMillisecondDifference(today.getTimestamp(), timesheetDocument.getClock().getClockLog().getClockTime()).longValue() / 3600000 > TimeCollectionRule.HOUR_DISTRIBUTION_ALLOWED_RANGE) {
            return false; //No hour distribution for periods longer than 24 hours
        }
        if (isEditedByAdministrator(timesheetDocument)) {
            if (!hasJurisdictionOverAssignment(timesheetDocument, timesheetDocument.getClock().getAssignment())) {
                return false;
            }
        }
        try {
            Job job = getJobByWorkArea(timesheetDocument, timesheetDocument.getClock().getClockLog().getWorkArea());
            if (Utility.hasValue(job)) {
                timeCollectionRule = getRuleService().getTimeCollectionRule(job.getDepartment(), timesheetDocument.getClock().getClockLog().getWorkArea(), new Date());
            }
        } catch (Exception e) {
            timeCollectionRule = null;
        }
        if (!Utility.hasValue(timeCollectionRule)) {
            return false;
        } else {
            return timeCollectionRule.isHoursDistributionAllowed();
        }
    }

    public boolean hasJurisdictionOverAssignment(TimesheetDocument timesheetDocument, String assignment) {
        if (!Utility.hasValue(assignment)) {
            return false;
        }
        edu.iu.uis.hr.tk.directory.entity.User user = (edu.iu.uis.hr.tk.directory.entity.User)getWebUserService().getUser();
        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(assignment);
        //Job tjob = getJobByWorkArea(timesheetDocument, (BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA));
        WorkArea workArea = new WorkArea((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), timesheetDocument.getDocumentHeader().getPayEndDate());
        Job job = getJobService().getJob(assignmentKeyMap.get(FieldNames.UNIVERSITY_ID).toString(), new BigDecimal(assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD).toString()), workArea.getEffectiveDate());
        if ( !Utility.hasValue(job) || !user.hasJurisdictionOver(workArea, job)) {
            return false;
        }
        return true;
    }

    public TimesheetDocument distributeHours(TimesheetDocument timesheet) {
        if (!HourDistributionValidation.execute(timesheet)) {
            return timesheet;
        }
        List timeBlocksToDelete = new TypedPersistentMaintainedEntityList(TimeBlock.class);
        List timeBlocksToSave = new TypedPersistentMaintainedEntityList(TimeBlock.class);
        List hourDetailDistribution = new TypedPersistentMaintainedEntityList(HourDetail.class);
        List timeBlockToDistributeList = timesheet.getTimeBlockToDistributeLists();
        for (Iterator iter = timeBlockToDistributeList.iterator(); iter.hasNext();) {
            HoursDetailToDistribute hoursDetailToDistribute = (HoursDetailToDistribute)iter.next();
            TimeBlock originalTimeblock = getTimeBlock(timesheet.getDocumentHeader(), (HourDetail)hoursDetailToDistribute);
            timeBlocksToDelete.add(originalTimeblock);
            hourDetailDistribution.clear();
            hourDetailDistribution.addAll(hoursDetailToDistribute.getDistributedHours());

            if (Utility.hasValue(hourDetailDistribution)) {
                HourDetail previousHourDetail = new HourDetail(); //stores timeblock's begin time
                previousHourDetail.setBeginTime(getTimeFromTimestamp(originalTimeblock.getBeginTime()));
                previousHourDetail.setBeginTsTz(originalTimeblock.getBeginTsTz());
                for (Iterator hourDetailDistributionIter = hourDetailDistribution.iterator(); hourDetailDistributionIter.hasNext();) {
                    HourDetail hourDetail = (HourDetail)hourDetailDistributionIter.next();
                    hourDetail.setEarnCode(originalTimeblock.getEarnCode());
                    hourDetail.setCreatedByClock(originalTimeblock.isCreatedByClock());
                    hourDetail.setBeginTsTz(originalTimeblock.getBeginTsTz());
                    hourDetail.setEndTsTz(originalTimeblock.getEndTsTz());
                    hourDetail.setBeginTime(previousHourDetail.getBeginTime());
                    if (Utility.hasValue(hourDetail.getHourDistributionPercent()) && hourDetail.getHourDistributionPercent().compareTo(new BigDecimal(0)) != 0) {
                        //hourDetail.setHours(originalTimeblock.getHours().multiply(hourDetail.getHourDistributionPercent().divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP));
                    	//change to allow compilation under java 1.4
                    	hourDetail.setHours(originalTimeblock.getHours().multiply(hourDetail.getHourDistributionPercent()).divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP));
                    }
                    if (hourDetailDistributionIter.hasNext()) {
                        hourDetail.setEndTime(calculateDistributedEndTime(hourDetail.getBeginTime(), hourDetail.getHours()));
                    } else {
                        hourDetail.setEndTime(getTimeFromTimestamp(originalTimeblock.getEndTime()));
                    }
                    hourDetail.setHoursDetail(new HoursDetail());
                    hourDetail.getHoursDetail().setDate(originalTimeblock.getBeginTime().getDate());
                    previousHourDetail.setBeginTime(hourDetail.getEndTime());
                    timeBlocksToSave.add(getTimeBlock(timesheet.getDocumentHeader(), hourDetail));
                }
            }
        }

        if (Utility.hasValue(timeBlocksToSave)) { //deletes original records ONLY if there are new records to save
            delete((TypedPersistentMaintainedEntityList)timeBlocksToDelete, getTimeBlockDataAccess());
            archiveTimeBlocks(timeBlocksToDelete, ACTION_HISTORY_DISTRIBUTED);
            save((TypedPersistentMaintainedEntityList)timeBlocksToSave, getTimeBlockDataAccess());
            archiveTimeBlocks(timeBlocksToSave, ACTION_HISTORY_ADDED);
        }

        timesheet = getTimesheetDocument(timesheet.getDocumentId());
        save(timesheet); //calculates overtime and shift differential for new timeblocks
        timesheet.getClock().setUserDoingHourDistribution(false);
        return timesheet;
    }

    private Time calculateDistributedEndTime(Time beginTime, BigDecimal hours) {
        TimedDate endTime = new TimedDate(new Date(), beginTime);
        endTime.addHours(hours.intValue());
        endTime.addMinutes((hours.subtract(new BigDecimal(hours.intValue()))).multiply(new BigDecimal(60)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
        return endTime.getTime();
    }

    private ClockLogDataAccess getClockLogDataAccess() {
        return clockLogDataAccess;
    }

    public void setClockLogDataAccess(ClockLogDataAccess clockLogDataAccess) {
        if (clockLogDataAccess != null) {
            this.clockLogDataAccess = clockLogDataAccess;
        }
    }

    private DocumentHeaderDataAccess getDocumentHeaderDataAccess() {
        return documentHeaderDataAccess;
    }

    public void setDocumentHeaderDataAccess(DocumentHeaderDataAccess documentHeaderDataAccess) {
        if (documentHeaderDataAccess != null) {
            this.documentHeaderDataAccess = documentHeaderDataAccess;
        }
    }

    private EarningService getEarningService() {
        return earningService;
    }

    public void setEarningService(EarningService earningService) {
        if (earningService != null) {
            this.earningService = earningService;
        }
    }

    private TimeBlockDataAccess getTimeBlockDataAccess() {
        return timeBlockDataAccess;
    }

    public void setTimeBlockDataAccess(TimeBlockDataAccess timeBlockDataAccess) {
        if (timeBlockDataAccess != null) {
            this.timeBlockDataAccess = timeBlockDataAccess;
        }
    }

    private TimeBlockHistoryDataAccess getTimeBlockHistoryDataAccess() {
        return timeBlockHistoryDataAccess;
    }

    public void setTimeBlockHistoryDataAccess(TimeBlockHistoryDataAccess timeBlockHistoryDataAccess) {
        if (timeBlockHistoryDataAccess != null) {
            this.timeBlockHistoryDataAccess = timeBlockHistoryDataAccess;
        }
    }

    private JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        if (jobService != null) {
            this.jobService = jobService;
        }
    }

    private AssignmentService getAssignmentService() {
        return assignmentService;
    }

    public void setAssignmentService(AssignmentService assignmentService) {
        if (assignmentService != null) {
            this.assignmentService = assignmentService;
        }
    }

    private PayCalendarService getPayCalendarService() {
        return payCalendarService;
    }

    public void setPayCalendarService(PayCalendarService payCalendarService) {
        if (payCalendarService != null) {
            this.payCalendarService = payCalendarService;
        }
    }

    private DocumentLockDataAccess getDocumentLockDataAccess() {
        return documentLockDataAccess;
    }

    public void setDocumentLockDataAccess(DocumentLockDataAccess documentLockDataAccess) {
        if (documentLockDataAccess != null) {
            this.documentLockDataAccess = documentLockDataAccess;
        }
    }

    private WeeklyOvertimeRuleDataAccess getWeeklyOvertimeRuleDataAccess() {
        return weeklyOvertimeRuleDataAccess;
    }

    public void setWeeklyOvertimeRuleDataAccess(WeeklyOvertimeRuleDataAccess weeklyOvertimeRuleDataAccess) {
        if (weeklyOvertimeRuleDataAccess != null) {
            this.weeklyOvertimeRuleDataAccess = weeklyOvertimeRuleDataAccess;
        }
    }

	public DailyOvertimeRuleDataAccess getDailyOvertimeRuleDataAccess() {
		return dailyOvertimeRuleDataAccess;
	}

	public void setDailyOvertimeRuleDataAccess(
		DailyOvertimeRuleDataAccess dailyOvertimeRuleDataAccess) {
		this.dailyOvertimeRuleDataAccess = dailyOvertimeRuleDataAccess;
	}


    // TODO: below are shift pay logic.  Code may be moved up to appropriate places
    private void calculateShiftPayHours(TimesheetDocument document) {
        Hours hours = document.getHours();
        TypedPersistentMaintainedEntityList jobs = document.getJobs();
        Map jobShiftDifferentialRulesMap = getJobShiftDifferentialRulesMap(jobs, hours.getPayCalendar());
        List employeeShiftPayHoursList = getEmployeeShiftHoursList(jobShiftDifferentialRulesMap);
        Map assignmentJobMap = new HashMap();
        boolean shiftHourFound = false;
        boolean shiftApplicableBlock = false;
        Iterator hoursDetailsItr = hours.getHoursDetails().iterator();

        while (hoursDetailsItr.hasNext()) {
            HoursDetail hoursDetail = (HoursDetail)hoursDetailsItr.next();
            Iterator hourDetailsItr = hoursDetail.getHourDetails().iterator();
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsItr.next();

                // TODO : try to deal with shift/ovt logic
                // reset hour and shift earn code
                // maybe should move this up, to reset for all detail
                // because some detail's maybe edited to become not shift eligible
                hourDetail.setShiftEarnCode(edu.iu.uis.hr.Utility.getDefaultStringValue());
                hourDetail.setShiftHours(edu.iu.uis.hr.Utility.getDefaultBigDecimalValue());
                if (getEarningService().isShiftEligibleEarnCode(hourDetail.getEarnCode(), document.getDocumentHeader().getPayEndDate())) {
                    // TODO : not sure about earncodes qualified for shift pay
                    // at this point, do we have to check the remaining hour detail if there is shiftpayjob exist
                    // should change the following method name and type to void.
                    // check premium first
                    if (isPremiumApplicableBlock(assignmentJobMap, jobShiftDifferentialRulesMap, hourDetail, PREMIUM_EARN_CODE)) {
                        // TODO : this is assume premium is from 00:00-23:59
                        hourDetail.setPremiumEarnCode(PREMIUM_EARN_CODE);
                        hourDetail.setPremiumHours(Time.getTimeDifferenceInHours(hourDetail.getEndTime(), hourDetail.getBeginTime(), hourDetail.getHoursDetail().getDate()));
                    } else if (isPremiumApplicableBlock(assignmentJobMap, jobShiftDifferentialRulesMap, hourDetail, POO_EARN_CODE)) {
                        // TODO : this is assume premium is from 00:00-23:59
                        hourDetail.setPremiumEarnCode(POO_EARN_CODE);
                        hourDetail.setPremiumHours(Time.getTimeDifferenceInHours(hourDetail.getEndTime(), hourDetail.getBeginTime(), hourDetail.getHoursDetail().getDate()));
                    } else if (isPremiumApplicableBlock(assignmentJobMap, jobShiftDifferentialRulesMap, hourDetail, PRW_EARN_CODE)) {
                        hourDetail.setPremiumEarnCode(PRW_EARN_CODE);
                        hourDetail.setPremiumHours(Time.getTimeDifferenceInHours(hourDetail.getEndTime(), hourDetail.getBeginTime(), hourDetail.getHoursDetail().getDate()));
                    }
                    // check real shift
                    shiftApplicableBlock = isShiftApplicableBlock(employeeShiftPayHoursList, assignmentJobMap, jobShiftDifferentialRulesMap, hourDetail, hours.getPayCalendar()) || shiftHourFound;
                    shiftHourFound = shiftHourFound || shiftApplicableBlock;
                    if (shiftApplicableBlock) {
                        if (hourDetail.getHoursDetail().getDate().compareTo(hours.getPayCalendar().getPayBeginDate()) == 0) {
                            // TODO : need to get the last day of previous pprd
                            // Probably should add time between 0-8am ? but this is rule specified, not sure how to do it yet?
                            DocumentHeader previousDocHeader = getDocumentHeaderDataAccess().getDocumentHeader(document.getDocumentHeader().getUniversityId(), (new TimelessDate(hours.getPayCalendar().getPayBeginDate()).addDays(-1)).getDate());
                            if (Utility.hasValue(previousDocHeader)) {
                                List timeblocks = (List)getTimeBlockDataAccess().getTimeBlocks(previousDocHeader.getDocumentId(), previousDocHeader.getPayEndDate());
                                if (Utility.hasValue(timeblocks)) {
                                    checkToAddBlocksToShift(employeeShiftPayHoursList, assignmentJobMap, jobShiftDifferentialRulesMap, timeblocks, hours.getPayCalendar(), previousDocHeader);
                                }
                            }
                        } else if (hourDetail.getHoursDetail().getDate().compareTo(hours.getPayCalendar().getPayEndDate()) == 0) {
                            // TODO : need to get the first day of next pprd
                            // probably should add time > 3pm.  But this is rule based, not sure how to do it yet.
                            DocumentHeader nextDocHeader = getDocumentHeaderDataAccess().getDocumentHeader(document.getDocumentHeader().getUniversityId(), (new TimelessDate(hours.getPayCalendar().getPayEndDate()).addDays(14)).getDate());
                            if (Utility.hasValue(nextDocHeader)) {
                                List timeblocks = (List)getTimeBlockDataAccess().getTimeBlocks(nextDocHeader.getDocumentId(), (new TimelessDate(hours.getPayCalendar().getPayEndDate()).addDays(1)).getDate());
                                if (Utility.hasValue(timeblocks)) {
                                    checkToAddBlocksToShift(employeeShiftPayHoursList, assignmentJobMap, jobShiftDifferentialRulesMap, timeblocks, hours.getPayCalendar(), nextDocHeader);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (shiftHourFound) {
            //TODO : rename checkMinHours
            checkMinHours(employeeShiftPayHoursList);
            if (Utility.hasValue(employeeShiftPayHoursList)) {
                for (Iterator iter = document.getHours().getHoursDetails().iterator(); iter.hasNext();) {
                    HoursDetail hoursDetail = (HoursDetail)iter.next();
                    for (Iterator iterator = hoursDetail.getHourDetails().iterator(); iterator.hasNext();) {
                        HourDetail hourDetail = (HourDetail)iterator.next();
                        updateShiftHoursAndEarnCode(hourDetail, employeeShiftPayHoursList);
                        // TODO: remove this log later
                        //LOG.info(hourDetail.getHoursDetail().getDate() + " " + hourDetail.getBeginTime() + " " + hourDetail.getEndTime() + " " + hourDetail.getShiftEarnCode() + " " + hourDetail.getShiftHours());
                    }
                }
            }
        }
    }

    private void updateShiftHoursAndEarnCode(HourDetail hourDetail, List employeeShiftPayHoursList) {
        for (Iterator iter = employeeShiftPayHoursList.iterator(); iter.hasNext();) {
            DepartmentShiftPayHours departmentShiftPayHours = (DepartmentShiftPayHours)iter.next();
            for (Iterator iterator = departmentShiftPayHours.getShiftHoursDetails().iterator(); iterator.hasNext();) {
                ShiftHours shiftHours = (ShiftHours)iterator.next();
                for (Iterator iterator1 = shiftHours.getHourDetails().iterator(); iterator1.hasNext();) {
                    ShiftHourDetail shiftHourDetail = (ShiftHourDetail)iterator1.next();
                    if (hourDetail.getHoursDetail().getDate().compareTo(shiftHourDetail.getHoursDetail().getDate()) == 0 && hourDetail.getAssignment().equals(shiftHourDetail.getAssignment()) && hourDetail.getBeginTime().compareTo(shiftHourDetail.getBlockBeginTime(), shiftHourDetail.getHoursDetail().getDate()) == 0 && hourDetail.getEndTime().compareTo(shiftHourDetail.getBlockEndTime(), shiftHourDetail.getHoursDetail().getDate()) == 0) {
                        hourDetail.setShiftHours(hourDetail.getShiftHours().add(shiftHourDetail.getHours()));
                        hourDetail.setShiftEarnCode(shiftHourDetail.getEarnCode());
                    }
                }
            }
        }

    }

    private void addShiftPayHoursToDept(List employeeShiftPayHoursList, HourDetail hourDetail, Job job, ShiftDifferentialRule shiftDifferentialRule, PayCalendar payCalendar) {
        for (Iterator iter = employeeShiftPayHoursList.iterator(); iter.hasNext();) {
            DepartmentShiftPayHours departmentShiftPayHours = (DepartmentShiftPayHours)iter.next();
            if (departmentShiftPayHours.getDepartment().equalsIgnoreCase(job.getDepartment())) {
                for (Iterator iterator = departmentShiftPayHours.getShiftHoursDetails().iterator(); iterator.hasNext();) {
                    ShiftHours shiftHours = (ShiftHours)iterator.next();
                    if (shiftHours.getShiftDifferentialRule().equals(shiftDifferentialRule)) {
                        ShiftHourDetail shiftHourDetail = new ShiftHourDetail();
                        shiftHourDetail.setHoursDetail(hourDetail.getHoursDetail());
                        shiftHourDetail.setBeginTime(hourDetail.getBeginTime());
                        shiftHourDetail.setEndTime(hourDetail.getEndTime());
                        shiftHourDetail.setAssignment(hourDetail.getAssignment());
                        shiftHourDetail.setEarnCode(shiftDifferentialRule.getEarnCode());
                        shiftHourDetail.setAssignment(hourDetail.getAssignment());
                        if (hourDetail.getBeginTime().compareTo(shiftDifferentialRule.getEndTime(), hourDetail.getHoursDetail().getDate()) < 0 && hourDetail.getEndTime().compareTo(shiftDifferentialRule.getBeginTime(), hourDetail.getHoursDetail().getDate()) > 0) {
                            // block have both morning and afternoon shift, such as 7am-4pm
                            shiftHourDetail.setEndTime(shiftDifferentialRule.getEndTime());
                            shiftHourDetail.setHours(Time.getTimeDifferenceInHours(shiftHourDetail.getEndTime(), shiftHourDetail.getBeginTime(), shiftHourDetail.getHoursDetail().getDate()));
                            setShiftHourDayIndex(shiftHourDetail, shiftDifferentialRule, payCalendar);
                            shiftHourDetail.setBlockBeginTime(hourDetail.getBeginTime());
                            shiftHourDetail.setBlockEndTime(hourDetail.getEndTime());
                            shiftHours.getHourDetails().add(shiftHourDetail);

                            ShiftHourDetail shiftHourDetail2 = new ShiftHourDetail();
                            shiftHourDetail2.setHoursDetail(hourDetail.getHoursDetail());
                            shiftHourDetail2.setBeginTime(shiftDifferentialRule.getBeginTime());
                            shiftHourDetail2.setEndTime(hourDetail.getEndTime());
                            shiftHourDetail2.setAssignment(hourDetail.getAssignment());
                            shiftHourDetail2.setEarnCode(shiftDifferentialRule.getEarnCode());
                            shiftHourDetail2.setAssignment(hourDetail.getAssignment());
                            shiftHourDetail2.setHours(Time.getTimeDifferenceInHours(shiftHourDetail2.getEndTime(), shiftHourDetail2.getBeginTime(), shiftHourDetail2.getHoursDetail().getDate()));
                            setShiftHourDayIndex(shiftHourDetail2, shiftDifferentialRule, payCalendar);
                            shiftHourDetail2.setBlockBeginTime(hourDetail.getBeginTime());
                            shiftHourDetail2.setBlockEndTime(hourDetail.getEndTime());
                            shiftHours.getHourDetails().add(shiftHourDetail2);
                        } else {
                            adjustBeginEndTimeIfNecessary(shiftHourDetail, shiftDifferentialRule);
                            setShiftHourDayIndex(shiftHourDetail, shiftDifferentialRule, payCalendar);
                            shiftHourDetail.setBlockBeginTime(hourDetail.getBeginTime());
                            shiftHourDetail.setBlockEndTime(hourDetail.getEndTime());
                            shiftHourDetail.setHours(Time.getTimeDifferenceInHours(shiftHourDetail.getEndTime(), shiftHourDetail.getBeginTime(), shiftHourDetail.getHoursDetail().getDate()));
                            shiftHours.getHourDetails().add(shiftHourDetail);
                        }
                    }
                }
            }
        }
    }

    private void calculateShiftDifferentialPayForClockAction(TimesheetDocument timesheetDocument, List timeBlocks) {
        // rewrite for multiple blocks that may be created by clock actions
        Map jobShiftDifferentialRulesMap = getJobShiftDifferentialRulesMap(timesheetDocument.getJobs(), timesheetDocument.getHours().getPayCalendar());
        List employeeShiftPayHoursList = getEmployeeShiftHoursList(jobShiftDifferentialRulesMap);
        Map assignmentJobMap = new HashMap();
        Hours hours = timesheetDocument.getHours();
        TimeBlock firstBlock = null;
        TimeBlock lastBlock = null;
        List timeBlocksAffected = new ArrayList(); // existing timeblocks' shift may be changed by this clock action

        for (Iterator iter = timeBlocks.iterator(); iter.hasNext();) {
            TimeBlock timeBlock = (TimeBlock)iter.next();
            if (firstBlock == null) {
                firstBlock = timeBlock;
            }
            if (!iter.hasNext()) {
                lastBlock = timeBlock;
            }
            HoursDetail hoursDetail;
            hoursDetail = getHoursDetail(timesheetDocument, getTimelessDate(timeBlock.getBeginTime().getDate()));
            // TODO: to remove "time from timeless date.  Not sure should be done here
            if (hoursDetail == null) {
                hoursDetail = new HoursDetail();
                hoursDetail.setDate(getTimelessDate(timeBlock.getBeginTime().getDate()));
                hours.getHoursDetails().add(hoursDetail);
            }
            HourDetail hourDetail = convertToHourDetail(timeBlock);
            hourDetail.setAssignment(timeBlock.getAssignment(timesheetDocument.getDocumentHeader().getUniversityId()));
            hoursDetail.getHourDetails().add(hourDetail);

            // check to add this block to employeeShiftPayHoursList
            isShiftApplicableBlock(employeeShiftPayHoursList, assignmentJobMap, jobShiftDifferentialRulesMap, hourDetail, timesheetDocument.getHours().getPayCalendar());
            // get existing blocks in the same day if there is any
            List existingTimeblocks = (List)getTimeBlockDataAccess().getTimeBlocks(timesheetDocument.getDocumentId(), timeBlock.getBeginTime().getDate());
            if (Utility.hasValue(existingTimeblocks)) {
                timeBlocksAffected.addAll(existingTimeblocks);
            }
        }

        if (Utility.hasValue(firstBlock) && getTimelessDate(firstBlock.getBeginTime().getDate()).compareTo(timesheetDocument.getHours().getPayCalendar().getPayBeginDate()) > 0) {
            List existingTimeblocks = (List)getTimeBlockDataAccess().getTimeBlocks(timesheetDocument.getDocumentHeader().getDocumentId(), (new TimelessDate(firstBlock.getBeginTime().getDate())).addDays(-1).getDate());
            if (Utility.hasValue(existingTimeblocks)) {
                timeBlocksAffected.addAll(existingTimeblocks);
            }
        }

        if (Utility.hasValue(lastBlock) && getTimelessDate(lastBlock.getBeginTime().getDate()).compareTo(timesheetDocument.getHours().getPayCalendar().getPayEndDate()) < 0) {
            List existingTimeblocks = (List)getTimeBlockDataAccess().getTimeBlocks(timesheetDocument.getDocumentHeader().getDocumentId(), (new TimelessDate(lastBlock.getBeginTime().getDate())).addDays(1).getDate());
            if (Utility.hasValue(existingTimeblocks)) {
                timeBlocksAffected.addAll(existingTimeblocks);
            }
        }
        if (Utility.hasValue(employeeShiftPayHoursList)) {
            // there is shift applicable block that is created by clock action
            List shiftIndexList = new ArrayList();
            for (Iterator iter = ((DepartmentShiftPayHours)employeeShiftPayHoursList.get(0)).getShiftHoursDetails().iterator(); iter.hasNext();) {
                ShiftHours shiftHours = (ShiftHours)iter.next();
                for (Iterator iterator = shiftHours.getHourDetails().iterator(); iterator.hasNext();) {
                    ShiftHourDetail shiftHourDetail = (ShiftHourDetail)iterator.next();
                    if (!shiftIndexList.contains(new Integer(shiftHourDetail.getShiftDayIndex()))) {
                        shiftIndexList.add(new Integer(shiftHourDetail.getShiftDayIndex()));
                    }
                }
            }
            // if (!shiftIndexList.isEmpty()) {
            // how about premium, so comment this if
            // need to check the whole list because something like 7am-4pm block
            calculateShiftPayHours(timesheetDocument);
            updateNewTimeBlocks(timesheetDocument, timeBlocks);
            checkExistingBlocksAffected(timesheetDocument, timeBlocksAffected);
            if (Utility.hasValue(timeBlocksAffected)) {
                // TODO : added to timeBlocks, so it can be updated.  not sure how to do it yet
                timeBlocks.addAll(timeBlocksAffected);
            }

            // }
        }
    }

    private HoursDetail getHoursDetail(TimesheetDocument timesheetDocument, Date date) {
        for (Iterator iter1 = timesheetDocument.getHours().getHoursDetails().iterator(); iter1.hasNext();) {
            HoursDetail hoursDetail = (HoursDetail)iter1.next();
            if (hoursDetail.getDate().compareTo(date) == 0) {
                return hoursDetail;
            }
        }
        return null;
    }

    private void updateNewTimeBlocks(TimesheetDocument timesheetDocument, List timeBlocks) {
        for (Iterator iter = timeBlocks.iterator(); iter.hasNext();) {
            TimeBlock timeBlock = (TimeBlock)iter.next();
            for (Iterator iter1 = timesheetDocument.getHours().getHoursDetails().iterator(); iter1.hasNext();) {
                HoursDetail hoursDetail = (HoursDetail)iter1.next();
                for (Iterator iterator = hoursDetail.getHourDetails().iterator(); iterator.hasNext();) {
                    HourDetail hourDetail = (HourDetail)iterator.next();
                    hourDetail.setHours(getCalculatedHours(timesheetDocument, hourDetail));
                    if (getTimelessDate(timeBlock.getBeginTime().getDate()).compareTo(hourDetail.getHoursDetail().getDate()) == 0 && timeBlock.getAssignment(timesheetDocument.getDocumentHeader().getUniversityId()).equals(hourDetail.getAssignment()) && getTimeFromTimestamp(timeBlock.getBeginTime()).compareTo(hourDetail.getBeginTime(), hourDetail.getHoursDetail().getDate()) == 0 && getTimeFromTimestamp(timeBlock.getEndTime()).compareTo(hourDetail.getEndTime(), hourDetail.getHoursDetail().getDate()) == 0) {
                        timeBlock.setShiftEarnCode(hourDetail.getShiftEarnCode());
                        timeBlock.setShiftHours(hourDetail.getShiftHours());
                        timeBlock.setPremiumEarnCode(hourDetail.getPremiumEarnCode());
                        timeBlock.setPremiumHours(hourDetail.getPremiumHours());
                        timeBlock.setOvertimeEarnCode(hourDetail.getOvertimeEarnCode());
                        timeBlock.setOvertimeHours(hourDetail.getOvertimeHours());
                    }
                }
            }
        }
    }

    private void checkExistingBlocksAffected(TimesheetDocument timesheetDocument, List timeBlocks) {
        List timeBlocksToKeep = new ArrayList();
        for (Iterator iter = timeBlocks.iterator(); iter.hasNext();) {
            TimeBlock timeBlock = (TimeBlock)iter.next();
            timeBlock.setHours(getCalculatedHours(timesheetDocument, timeBlock));
            if (timeBlock.getShiftHours() == null) {
                timeBlock.setShiftHours(edu.iu.uis.hr.Utility.getDefaultBigDecimalValue());
            }
            for (Iterator iter1 = timesheetDocument.getHours().getHoursDetails().iterator(); iter1.hasNext();) {
                HoursDetail hoursDetail = (HoursDetail)iter1.next();
                for (Iterator iterator = hoursDetail.getHourDetails().iterator(); iterator.hasNext();) {
                    HourDetail hourDetail = (HourDetail)iterator.next();
                    hourDetail.setHours(getCalculatedHours(timesheetDocument, hourDetail));
                    if (getTimelessDate(timeBlock.getBeginTime().getDate()).compareTo(hourDetail.getHoursDetail().getDate()) == 0 && timeBlock.getAssignment(timesheetDocument.getDocumentHeader().getUniversityId()).equals(hourDetail.getAssignment()) && getTimeFromTimestamp(timeBlock.getBeginTime()).compareTo(hourDetail.getBeginTime(), hourDetail.getHoursDetail().getDate()) == 0 && getTimeFromTimestamp(timeBlock.getEndTime()).compareTo(hourDetail.getEndTime(), hourDetail.getHoursDetail().getDate()) == 0 && timeBlock.getShiftHours().doubleValue() != hourDetail.getShiftHours().doubleValue()) {
                        timeBlock.setShiftEarnCode(hourDetail.getShiftEarnCode());
                        timeBlock.setShiftHours(hourDetail.getShiftHours());
                        if (!timeBlocksToKeep.contains(timeBlock)) {
                            timeBlocksToKeep.add(timeBlock);
                        }
                    }
                }
            }
        }
        int numberOfTimeBlocks = timeBlocks.size();
        for (int i = 0; i < numberOfTimeBlocks; i++) {
            timeBlocks.remove(0);
        }
        if (Utility.hasValue(timeBlocksToKeep)) {
            timeBlocks.addAll(timeBlocksToKeep);
        }

    }

    private Date getTimelessDate(Date date) {
        return new TimelessDate(new TimelessDate(date).toString()).getDate();
    }

    private HourDetail convertToHourDetail(TimeBlock timeBlock) {
        HourDetail hourDetail = new HourDetail();
        hourDetail.setBeginTime(getTimeFromTimestamp(timeBlock.getBeginTime()));
        hourDetail.setEndTime(getTimeFromTimestamp(timeBlock.getEndTime()));
        hourDetail.setBeginTsTz(timeBlock.getBeginTsTz());
        hourDetail.setEndTsTz(timeBlock.getEndTsTz());
        hourDetail.setCreatedByClock(timeBlock.isCreatedByClock());
        hourDetail.setEarnCode(timeBlock.getEarnCode());
        hourDetail.setHours(timeBlock.getHours());
        return hourDetail;
    }

    public HoursDetailToDistribute convertToHourDetailToDistribute(TimeBlock timeBlock) {
        HourDetail hourDetail = new HoursDetailToDistribute();
        hourDetail.setBeginTime(getTimeFromTimestamp(timeBlock.getBeginTime()));
        hourDetail.setEndTime(getTimeFromTimestamp(timeBlock.getEndTime()));
        hourDetail.setBeginTsTz(timeBlock.getBeginTsTz());
        hourDetail.setEndTsTz(timeBlock.getEndTsTz());
        hourDetail.setCreatedByClock(timeBlock.isCreatedByClock());
        hourDetail.setEarnCode(timeBlock.getEarnCode());
        hourDetail.setHours(timeBlock.getHours());
        return (HoursDetailToDistribute)hourDetail;
    }

    private void checkToAddBlocksToShift(List employeeShiftPayHoursList, Map assignmentJobMap, Map jobShiftDifferentialRulesMap, List timeBlocks, PayCalendar payCalendar, DocumentHeader docHeader) {
        HoursDetail hoursDetail = new HoursDetail();
        hoursDetail.setDate(getTimelessDate(((TimeBlock)timeBlocks.get(0)).getBeginTime().getDate()));
        for (Iterator iter = timeBlocks.iterator(); iter.hasNext();) {
            TimeBlock timeBlock = (TimeBlock)iter.next();
            HourDetail hourDetail = convertToHourDetail(timeBlock);
            hourDetail.setAssignment(timeBlock.getAssignment(docHeader.getUniversityId()));
            hoursDetail.getHourDetails().add(hourDetail);
            isShiftApplicableBlock(employeeShiftPayHoursList, assignmentJobMap, jobShiftDifferentialRulesMap, hourDetail, payCalendar);
        }
    }

    private Time getTimeFromTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        Time time = new Time();
        time.setHour(timestamp.getHour());
        time.setMinute(timestamp.getMinute());
        time.setAmPm(timestamp.getAmPm());
        return time;
    }

    private boolean isShiftApplicableBlock(List employeeShiftPayHoursList, Map assignmentJobMap, Map jobShiftDifferentialRulesMap, HourDetail hourDetail, PayCalendar payCalendar) {
        Job job = (Job)assignmentJobMap.get(hourDetail.getAssignment() + hourDetail.getHoursDetail().getDate());
        if (!Utility.hasValue(job)) {
            Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
            job = getJobService().getJob((String)assignmentKeyMap.get(FieldNames.UNIVERSITY_ID), (BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hourDetail.getHoursDetail().getDate());
            // TODO : if needs to consider job change in the middle of pprd, then the following map key should include hourdetaildate
            assignmentJobMap.put(hourDetail.getAssignment() + hourDetail.getHoursDetail().getDate(), job);
        }
        List shiftDifferentialRules = (List)jobShiftDifferentialRulesMap.get(job);
        if (Utility.hasValue(shiftDifferentialRules)) {
            for (Iterator iter = shiftDifferentialRules.iterator(); iter.hasNext();) {
                ShiftDifferentialRule shiftDifferentialRule = (ShiftDifferentialRule)iter.next();
            //including POO and PRW earn codes (tk-312)
          if (!shiftDifferentialRule.getEarnCode().equals(PREMIUM_EARN_CODE) && !shiftDifferentialRule.getEarnCode().equals(POO_EARN_CODE) &&  !shiftDifferentialRule.getEarnCode().equals(PRW_EARN_CODE) && isShiftDiffRuleApplicable(shiftDifferentialRule, hourDetail)) {
                    addShiftPayHoursToDept(employeeShiftPayHoursList, hourDetail, (Job)assignmentJobMap.get(hourDetail.getAssignment() + hourDetail.getHoursDetail().getDate()), shiftDifferentialRule, payCalendar);
                    return true;
                }
            }
        }
        return false;

    }

    private boolean isPremiumApplicableBlock(Map assignmentJobMap, Map jobShiftDifferentialRulesMap, HourDetail hourDetail, String earnCode) {
        Job job = (Job)assignmentJobMap.get(hourDetail.getAssignment() + hourDetail.getHoursDetail().getDate());
        if (!Utility.hasValue(job)) {
            Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
            job = getJobService().getJob((String)assignmentKeyMap.get(FieldNames.UNIVERSITY_ID), (BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hourDetail.getHoursDetail().getDate());
            assignmentJobMap.put(hourDetail.getAssignment() + hourDetail.getHoursDetail().getDate(), job);
        }
        List shiftDifferentialRules = (List)jobShiftDifferentialRulesMap.get(job);
        if (Utility.hasValue(shiftDifferentialRules)) {
            for (Iterator iter = shiftDifferentialRules.iterator(); iter.hasNext();) {
                ShiftDifferentialRule shiftDifferentialRule = (ShiftDifferentialRule)iter.next();
                if (shiftDifferentialRule.getEarnCode().equals(earnCode) && isShiftDiffRuleApplicable(shiftDifferentialRule, hourDetail)) {
                    return true;
                }
            }
        }
        return false;

    }

    private void checkMinHours(List employeeShiftPayHoursList) {
        // shiftIndexList : for edit timesheet use SHIFT_INDEX_LIST
        //    for block created by clock action only need shiftdayindex for that block in the list
        for (Iterator iter = employeeShiftPayHoursList.iterator(); iter.hasNext();) {
            DepartmentShiftPayHours departmentShiftPayHours = (DepartmentShiftPayHours)iter.next();
            List dayIndexes = new ArrayList();
            for (Iterator iterator = departmentShiftPayHours.getShiftHoursDetails().iterator(); iterator.hasNext();) {
                ShiftHours shiftHours = (ShiftHours)iterator.next();
                int dayIndex = 0;
                double blockTotalHours = 0.0;
                Time lastBlockEndTime = null;
                for (Iterator iterator1 = shiftHours.getHourDetails().iterator(); iterator1.hasNext();) {
                    ShiftHourDetail shiftHourDetail = (ShiftHourDetail)iterator1.next();
                    if (SHIFT_INDEX_LIST.contains(new Integer(shiftHourDetail.getShiftDayIndex()))) {
                        if (dayIndex != shiftHourDetail.getShiftDayIndex()) {
                            // reset for new day
                            dayIndex = shiftHourDetail.getShiftDayIndex();
                            blockTotalHours = 0.0;
                            lastBlockEndTime = null;
                        }
                        if (!dayIndexes.contains(new Integer(shiftHourDetail.getShiftDayIndex()))) {
                            // if index already qualified in the same dept, then this also qualified
                            if (blockTotalHours == 0.0 || shiftHourDetail.getHours().doubleValue() >= shiftHours.getShiftDifferentialRule().getMinHours().doubleValue()) {
                                // total=0 or current block alone qualify for the min hours
                                blockTotalHours = shiftHourDetail.getHours().doubleValue();
                                lastBlockEndTime = shiftHourDetail.getEndTime();
                            } else {
                                // check gap to see if it is OK
                                if (blocksGapMinute(lastBlockEndTime, shiftHourDetail.getBeginTime()) <= shiftHours.getShiftDifferentialRule().getMaxGap().doubleValue()) {
                                    lastBlockEndTime = shiftHourDetail.getEndTime();
                                    blockTotalHours += shiftHourDetail.getHours().doubleValue();
                                } else {
                                    lastBlockEndTime = shiftHourDetail.getEndTime();
                                    blockTotalHours = shiftHourDetail.getHours().doubleValue();
                                }
                            }
                        }
                        if (blockTotalHours >= shiftHours.getShiftDifferentialRule().getMinHours().doubleValue()) {
                            dayIndexes.add(new Integer(shiftHourDetail.getShiftDayIndex()));
                            dayIndex = 0;
                            blockTotalHours = 0.0;
                            lastBlockEndTime = null;
                        }
                    }
                }
            }
            //if (Utility.hasValue(dayIndexes)) {
            //    departmentShiftDayIndexListMap.put(departmentShiftPayHours.getDepartment(), dayIndexes);
            //}
            // TODO : below is a cleanup loop.  need more tests
            for (Iterator iterator = departmentShiftPayHours.getShiftHoursDetails().iterator(); iterator.hasNext();) {
                ShiftHours shiftHours = (ShiftHours)iterator.next();
                List shiftHourDetailRemoveList = new ArrayList();
                for (Iterator iterator1 = shiftHours.getHourDetails().iterator(); iterator1.hasNext();) {
                    ShiftHourDetail shiftHourDetail = (ShiftHourDetail)iterator1.next();
                    if (!dayIndexes.contains(new Integer(shiftHourDetail.getShiftDayIndex()))) {
                        shiftHourDetailRemoveList.add(shiftHourDetail);
                    }
                }
                if (Utility.hasValue(shiftHourDetailRemoveList)) {
                    for (Iterator iterator1 = shiftHourDetailRemoveList.iterator(); iterator1.hasNext();) {
                        ShiftHourDetail shiftHourDetail = (ShiftHourDetail)iterator1.next();
                        shiftHours.getHourDetails().remove(shiftHourDetail);
                    }
                }
            }
        }
    }

    private double blocksGapMinute(Time endTime, Time beginTime) {
        if (endTime.getAmPm().equals("PM") && beginTime.getAmPm().equals("AM")) {
            return (beginTime.get24Hour() * 60.0 + beginTime.getMinute().doubleValue() + (24 * 60.0)) - (endTime.get24Hour() * 60.0 + endTime.getMinute().doubleValue());
        } else {
            return beginTime.get24Hour() * 60.0 + beginTime.getMinute().doubleValue() - (endTime.get24Hour() * 60.0 + endTime.getMinute().doubleValue());
        }
    }

    private void adjustBeginEndTimeIfNecessary(ShiftHourDetail hourDetail, ShiftDifferentialRule shiftDifferentialRule) {
        if (hourDetail.getBeginTime().compareTo(shiftDifferentialRule.getBeginTime(), hourDetail.getHoursDetail().getDate()) < 0 && hourDetail.getBeginTime().compareTo(shiftDifferentialRule.getEndTime(), hourDetail.getHoursDetail().getDate()) >= 0) {
            hourDetail.setBeginTime(shiftDifferentialRule.getBeginTime());
        }
        if (hourDetail.getEndTime().compareTo(shiftDifferentialRule.getBeginTime(), hourDetail.getHoursDetail().getDate()) <= 0 && hourDetail.getEndTime().compareTo(shiftDifferentialRule.getEndTime(), hourDetail.getHoursDetail().getDate()) > 0) {
            hourDetail.setEndTime(shiftDifferentialRule.getEndTime());
        }
    }

    private void setShiftHourDayIndex(ShiftHourDetail hourDetail, ShiftDifferentialRule shiftDifferentialRule, PayCalendar payCalendar) {
        if (hourDetail.getHoursDetail().getDate().compareTo(payCalendar.getPayBeginDate()) < 0) {
            // for last day of prev prrd
            hourDetail.setDayIndex(0);
            if (hourDetail.getBeginTime().compareTo(shiftDifferentialRule.getBeginTime(), hourDetail.getHoursDetail().getDate()) >= 0 && hourDetail.getBeginTime().compareTo(shiftDifferentialRule.getEndTime(), hourDetail.getHoursDetail().getDate()) > 0) {
                hourDetail.setShiftDayIndex(hourDetail.getDayIndex());
            } else {
                hourDetail.setShiftDayIndex(hourDetail.getDayIndex() - 1);
            }

        } else if (hourDetail.getHoursDetail().getDate().compareTo(payCalendar.getPayEndDate()) > 0) {
            // for 1st day of next pprd
            hourDetail.setDayIndex(15);
            if (hourDetail.getBeginTime().compareTo(shiftDifferentialRule.getBeginTime(), hourDetail.getHoursDetail().getDate()) >= 0 && hourDetail.getBeginTime().compareTo(shiftDifferentialRule.getEndTime(), hourDetail.getHoursDetail().getDate()) > 0) {
                hourDetail.setShiftDayIndex(hourDetail.getDayIndex());
            } else {
                hourDetail.setShiftDayIndex(hourDetail.getDayIndex() - 1);
            }
        } else {
            hourDetail.setDayIndex(payCalendar.getDayInPayPeriod(hourDetail.getHoursDetail().getDate()));
            if (hourDetail.getBeginTime().compareTo(shiftDifferentialRule.getBeginTime(), hourDetail.getHoursDetail().getDate()) >= 0 && hourDetail.getBeginTime().compareTo(shiftDifferentialRule.getEndTime(), hourDetail.getHoursDetail().getDate()) > 0) {
                hourDetail.setShiftDayIndex(hourDetail.getDayIndex());
            } else {
                hourDetail.setShiftDayIndex(hourDetail.getDayIndex() - 1);
            }
        }
    }

    private Map getJobShiftDifferentialRulesMap(TypedPersistentMaintainedEntityList jobs, PayCalendar payCalendar) {
        // TODO : probably don't need this if we separate PRM from the other shift code
        Map jobShiftDifferentialRulesMap = new HashMap();
        List shiftDifferentialRules = getRuleService().getShiftDifferentialRules(payCalendar);
        for (Iterator iter = jobs.iterator(); iter.hasNext();) {
            Job job = (Job)iter.next();
            List matchedShiftDifferentialRules = new ArrayList();
            for (Iterator iter1 = shiftDifferentialRules.iterator(); iter1.hasNext();) {
                ShiftDifferentialRule shiftDifferentialRule = (ShiftDifferentialRule)iter1.next();
                if (shiftDifferentialRule.getLocation().equals(Optionable.ALL_VALUE) || shiftDifferentialRule.getLocation().equalsIgnoreCase(job.getLocation())) {
                    if (shiftDifferentialRule.getSalaryPlan().equals(Optionable.ALL_VALUE) || shiftDifferentialRule.getSalaryPlan().equalsIgnoreCase(job.getSalaryPlan())) {
                        if (shiftDifferentialRule.getGrade().equals(Optionable.ALL_VALUE) || shiftDifferentialRule.getGrade().equalsIgnoreCase(job.getGrade())) {
                            matchedShiftDifferentialRules.add(shiftDifferentialRule);
                        }
                    }
                }
            }
            if (Utility.hasValue(matchedShiftDifferentialRules)) {
                // WHen form is posted, the jobs under timesheetdocument is not exactly the same in database unless all the job fields are in the form
                // so froce to retrive it from database
                jobShiftDifferentialRulesMap.put(job, matchedShiftDifferentialRules);
            }
        }
        return jobShiftDifferentialRulesMap;
    }

    private List getEmployeeShiftHoursList(Map jobShiftDifferentialRulesMap) {
        List employeeShiftPayDetails = new ArrayList();
        List uniqueDepartments = new ArrayList();
        List uniqueShiftDifferentialRules = new ArrayList();
        for (Iterator iter = jobShiftDifferentialRulesMap.keySet().iterator(); iter.hasNext();) {
            Job job = (Job)iter.next();
            List shiftDifferentialRules = (List)jobShiftDifferentialRulesMap.get(job);
            if (Utility.hasValue(shiftDifferentialRules)) {
                DepartmentShiftPayHours departmentShiftPayHours = null;
                for (Iterator iterator = employeeShiftPayDetails.iterator(); iterator.hasNext();) {
                    DepartmentShiftPayHours workDepartmentShiftPayHours = (DepartmentShiftPayHours)iterator.next();
                    if (workDepartmentShiftPayHours.getDepartment().equalsIgnoreCase(job.getDepartment())) {
                        departmentShiftPayHours = workDepartmentShiftPayHours;
                        break;
                    }
                }
                if (!Utility.hasValue(departmentShiftPayHours)) {
                    departmentShiftPayHours = new DepartmentShiftPayHours(job.getDepartment());
                }
                for (Iterator iterator = shiftDifferentialRules.iterator(); iterator.hasNext();) {
                    ShiftDifferentialRule shiftDifferentialRule = (ShiftDifferentialRule)iterator.next();
                    ShiftHours shiftHours = null;
                    for (Iterator iterator1 = departmentShiftPayHours.getShiftHoursDetails().iterator(); iterator1.hasNext();) {
                        ShiftHours workShiftHours = (ShiftHours)iterator1.next();
                        if (workShiftHours.getShiftDifferentialRule().equals(shiftDifferentialRule)) {
                            shiftHours = workShiftHours;
                            break;
                        }
                    }
                    if (!Utility.hasValue(shiftHours)) {
                        shiftHours = new ShiftHours(shiftDifferentialRule);
                    }
                    shiftHours.getJobs().add(job);
                    if (!uniqueShiftDifferentialRules.contains(shiftDifferentialRule.getLocation() + edu.iu.uis.hr.Utility.COLON + shiftDifferentialRule.getSalaryPlan() + edu.iu.uis.hr.Utility.COLON + shiftDifferentialRule.getGrade() + edu.iu.uis.hr.Utility.COLON + shiftDifferentialRule.getEarnCode())) {
                        departmentShiftPayHours.getShiftHoursDetails().add(shiftHours);
                        uniqueShiftDifferentialRules.add(shiftDifferentialRule.getLocation() + edu.iu.uis.hr.Utility.COLON + shiftDifferentialRule.getSalaryPlan() + edu.iu.uis.hr.Utility.COLON + shiftDifferentialRule.getGrade() + edu.iu.uis.hr.Utility.COLON + shiftDifferentialRule.getEarnCode());
                    }
                }
                if (Utility.hasValue(departmentShiftPayHours) && !uniqueDepartments.contains(departmentShiftPayHours.getDepartment())) {
                    employeeShiftPayDetails.add(departmentShiftPayHours);
                    uniqueDepartments.add(departmentShiftPayHours.getDepartment());
                }
            }
        }
        return employeeShiftPayDetails;
    }

    private boolean isShiftDiffRuleApplicable(ShiftDifferentialRule shiftDifferentialRule, HourDetail hourDetail) {
        // TODO : make this a static so it can be shared in validptoformanualshift
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hourDetail.getHoursDetail().getDate());
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        boolean dayIsApplicable = false;
        if (weekDay == Calendar.SUNDAY && shiftDifferentialRule.isApplicableToSunday()) {
            dayIsApplicable = true;
        } else if (weekDay == Calendar.MONDAY && shiftDifferentialRule.isApplicableToMonday()) {
            dayIsApplicable = true;
        } else if (weekDay == Calendar.TUESDAY && shiftDifferentialRule.isApplicableToTuesday()) {
            dayIsApplicable = true;
        } else if (weekDay == Calendar.WEDNESDAY && shiftDifferentialRule.isApplicableToWednesday()) {
            dayIsApplicable = true;
        } else if (weekDay == Calendar.THURSDAY && shiftDifferentialRule.isApplicableToThursday()) {
            dayIsApplicable = true;
        } else if (weekDay == Calendar.FRIDAY && shiftDifferentialRule.isApplicableToFriday()) {
            dayIsApplicable = true;
        } else if (weekDay == Calendar.SATURDAY && shiftDifferentialRule.isApplicableToSaturday()) {
            dayIsApplicable = true;
        }
        Time shiftRuleBeginTime = shiftDifferentialRule.getBeginTime();
        Time shiftRuleEndTime = shiftDifferentialRule.getEndTime();
        Time blockBeginTime = hourDetail.getBeginTime();
        Time blockEndTime = hourDetail.getEndTime();

        if (dayIsApplicable) {
            if (shiftRuleBeginTime.compareTo(blockBeginTime, hourDetail.getHoursDetail().getDate()) <= 0 || shiftRuleEndTime.compareTo(blockBeginTime, hourDetail.getHoursDetail().getDate()) > 0 || shiftRuleBeginTime.compareTo(blockEndTime, hourDetail.getHoursDetail().getDate()) < 0 || shiftRuleEndTime.compareTo(blockEndTime, hourDetail.getHoursDetail().getDate()) >= 0) {
                return true;
            }
        }
        return false;
    }

    public RuleService getRuleService() {
        return ruleService;
    }

    public void setRuleService(RuleService ruleService) {
        if (ruleService != null) {
            this.ruleService = ruleService;
        }
    }

    public LeaveAccrualService getLeaveAccrualService() {
        return leaveAccrualService;
    }

    public void setLeaveAccrualService(LeaveAccrualService leaveAccrualService) {
        if (leaveAccrualService != null) {
            this.leaveAccrualService = leaveAccrualService;
        }
    }

    private TypedPersistentMaintainedEntityList getLeaveSummary(String universityId, Date leaveAccrualDate) {
        TypedPersistentMaintainedEntityList result = new TypedPersistentMaintainedEntityList(LeaveBalance.class);
        if (isLeaveBalanceRequired(universityId, leaveAccrualDate)) {
            for (Iterator iter = LEAVE_PLAN_TYPES.iterator(); iter.hasNext();) {
                String planType = (String)iter.next();
                LeaveBalance leaveBalance = new LeaveBalance(getLeaveAccrualService().getLeaveAccrual(universityId, planType, leaveAccrualDate));
                if (Utility.hasValue(leaveBalance.getPlanType())) {
                    // TODO: not really the timesheet's job to get a description for the leave plan...
                    leaveBalance.setPlanDescription(((Translate)getTranslateService().getTranslate(FieldNames.PLAN_TYPE, leaveBalance.getPlanType(), leaveAccrualDate)).getLabel());
                    result.add(leaveBalance);
                }
            }
        }
        return result;
    }

    //  TODO - this check is supposed to eliminate db calls for getting leave accrual when no accruals should exist
    private boolean isLeaveBalanceRequired(String universityId, Date transactionRecordEffectiveDate) {
        TypedPersistentMaintainedEntityList jobs = getJobService().getJobs(universityId, transactionRecordEffectiveDate);
        for (Iterator iter = jobs.iterator(); iter.hasNext();) {
            Job job = (Job)iter.next();
            if (!"H".equalsIgnoreCase(job.getEmployeeType())) {
                return true;
            }
        }
        return false;
    }

    // check whether is edited by administrator
    public boolean isEditedByAdministrator(TimesheetDocument timesheetDocument) {
        return !getWebUserService().getUser().getUniversityId().equals(timesheetDocument.getDocumentHeader().getUniversityId());
    }

    public HolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(HolidayService holidayService) {
        if (holidayService != null) {
            this.holidayService = holidayService;
        }
    }

    public TranslateService getTranslateService() {
        return translateService;
    }

    public void setTranslateService(TranslateService translateService) {
        this.translateService = translateService;
    }

    public String populateHoursEntryEarnCodes(TimesheetDocument timeSheetDocument) {
        //comma delimited list used in javascript for onchange event of earn code field
        String results = "";
        Iterator earnCodeListItr = getEarningService().getEarnCodesListFromEarnProgram(EarningService.HOURS_DETAIL_COLLECT_BY_HOURS_EARN_PROGRAM, timeSheetDocument.getDocumentHeader().getPayEndDate()).iterator();
        while (earnCodeListItr.hasNext()) {
            if (Utility.hasValue(results)) {
                results += ",";
            }
            results += (String)earnCodeListItr.next();
        }
        return results;
    }

    public Integer getOffsetFromLocation(String location) {
    	if (locationTimezoneRawOffsetMap.containsKey(location)) {
    		return (Integer)locationTimezoneRawOffsetMap.get(location);
    	} else {
    		return null;
    	}
    }

    public String getLocationFromTimesheet(TimesheetDocument timesheetDocument) {
        // TODO determine why locationTimezoneRawOffsetMap can't find location (above)
    	return determineLocation(timesheetDocument.getClock(), timesheetDocument.getDocumentHeader().getUniversityId());
    }

    public String determineLocation(Clock clock, String universityId) {
    String location = "BL";
    if (clock.isOnTheClock()) {
        Assignment assignment = getAssignmentService().getAssignment(clock.getAssignment(), clock.getClockTime().getDate());
        if (Utility.hasValue(assignment)) {
            location = assignment.getJob().getLocation();
        }
    } else {
        Preference preference = getUserPreferenceService().getUserPreference(universityId, UserPreferenceService.DEFAULT_LOCATION_PREFERENCE_GROUP);
        if (Utility.hasValue(preference)) {
            // TODO - THIS IS BAD - SHOULDN'T RELY ON THE DESCRIPTION BEING A 2-CHAR LOCATION CODE!!!! WOULD BE BETTER TO HAVE THE PREFERENCE CODE MATCH LOCATION CODE? NOT GOOD BUT BETTER. WOULD REQUIRE US TO UPDATE ALREADY EXISTING PREFS...
            location = preference.getDescription();
        } else {
            List assignmentList = getAssignmentService().getAssignments(universityId);
            Iterator assignmentListItr = assignmentList.iterator();
            BigDecimal lowestSecondaryEmplRcd = Job.MAX_EMPL_RCD;
            while (assignmentListItr.hasNext()) {
                Job tempJob = ((Assignment)assignmentListItr.next()).getJob();
                String jobIndicator = tempJob.getJobIndicator();
                if (jobIndicator.equals(Job.PRIMARY_JOB_INDICATOR)) {
                    location = tempJob.getLocation();
                    break;
                } else {
                    if (tempJob.getEmployeeRecord().compareTo(lowestSecondaryEmplRcd) < 0) {
                        lowestSecondaryEmplRcd = tempJob.getEmployeeRecord();
                        location = tempJob.getLocation();
                    }
                }
            }
        }
    }
    return location;
}
    public String populateAssignmentTimeOffsets(TimesheetDocument timesheetDocument) {
        //pipe delimited list of: assignmentKey1|offset1|assignmentKey2|offset2|etc. used in javascript for onchange event of clock assignment
        List assignmentList = getAssignmentService().getAssignments(timesheetDocument.getDocumentHeader().getUniversityId());
        Iterator assignmentListItr = assignmentList.iterator();
        StringBuffer results = new StringBuffer(edu.iu.uis.hr.Utility.getDefaultStringValue());
        while (assignmentListItr.hasNext()) {
            if (Utility.hasValue(results)) {
                results.append("|");
            }
            Assignment assignment = (Assignment)assignmentListItr.next();
            //results.append(assignment.getAssignmentKeyAsOptionKey(false));
            results.append(assignment.getJob().getUniversityId()).append(",");
            results.append(assignment.getJob().getEmployeeRecord()).append(",");
            results.append(assignment.getWorkArea()).append(",");
            results.append(assignment.getTask()).append("|");
            results.append(((Integer)locationTimezoneRawOffsetMap.get(assignment.getJob().getLocation())).toString());
        }
        return results.toString();
    }

    public String populateServerLocationTimeOffset() {
        return new Integer(serverLocationTimezoneRawOffset).toString();
    }

    public String populateAssignmentEarnCodes(TimesheetDocument timesheetDocument) {
        //generate a pipe delimited list of: assignmentKey1|validEarnCodes1|assignmentKey2|validEarnCodes2|etc. for javascript onchange event of assignment field
        List assignmentList = getAssignmentService().getAssignments(timesheetDocument.getDocumentHeader().getUniversityId(), timesheetDocument.getDocumentHeader().getPayEndDate());
        Iterator assignmentListItr = assignmentList.iterator();
        String results = edu.iu.uis.hr.Utility.getDefaultStringValue();
        while (assignmentListItr.hasNext()) {
        	//If the assignment terms before the pay end date, the earn codes still need to be available.
        	Date earnCodeDate = timesheetDocument.getDocumentHeader().getPayEndDate();
            if (Utility.hasValue(results)) {
                results += "|";
            }
            Assignment assignment = (Assignment)assignmentListItr.next();
            if(Utility.hasValue(assignment.getEndDate())){
            	earnCodeDate = assignment.getEndDate();
            }
            results += assignment.getAssignmentKeyAsOptionKey(false);
            results += "|";

            String earnCodes = edu.iu.uis.hr.Utility.getDefaultStringValue();
            if (!getAssignmentService().isSynchronousAssignment(assignment)) {
                //EarnCode earnCodeObject = getEarningService().getEarnCode(assignment.getEarnCode(), earnCodeDate);
            	EarnCode earnCodeObject = getEarningService().getEarnCode(getEarningService().getPaygroup(assignment.getJob().getCompany(), assignment.getJob().getPaygroup(), new Date()).getRegularHoursEarnCode(), earnCodeDate);
                earnCodes += earnCodeObject.getEarnCode() + "," + earnCodeObject.getLabel();//add regular earnings earn code if asynchronous
            } else {
                if (isEditedByAdministrator(timesheetDocument)) {
                    EarnCode earnCodeObject = getEarningService().getEarnCode(getEarningService().getPaygroup(assignment.getJob().getCompany(), assignment.getJob().getPaygroup(), new Date()).getRegularHoursEarnCode(), earnCodeDate);
                    earnCodes += earnCodeObject.getEarnCode() + "," + earnCodeObject.getLabel();//add regular earnings earn code if user <> employee
                }
            }

            //List<String> earnCodeList = getEarnCodeListByEmployeeType(assignment.getJob().getEmployeeType(), earnCodeDate);
            Set<String> earnCodeList = getEarnCodeListBySalaryPlan(assignment.getJob(), earnCodeDate);

            for (String earnCode : earnCodeList) {
                if (Utility.hasValue(earnCodes)) {
                    earnCodes += ",";
                }
                EarnCode earnCodeObject = getEarningService().getEarnCode(earnCode, earnCodeDate);
                if (earnCodeObject != null) {
                    earnCodes += earnCodeObject.getEarnCode() + "," + earnCodeObject.getLabel();
                } else {
                    earnCodes += earnCode + ",No Label Found";

                }

            }
            results += earnCodes;

//            Iterator earnCodeListItr = earnCodesList.iterator();
//            while (earnCodeListItr.hasNext()) {
//                if (Utility.hasValue(earnCodes)) {
//                    earnCodes += ",";
//                }
//                EarnCode earnCodeObject = getEarningService().getEarnCode((String)earnCodeListItr.next(), earnCodeDate);
//                earnCodes += earnCodeObject.getEarnCode() + "," + earnCodeObject.getLabel();
//            }
        }
        return results;
    }


    public Set getEarnCodeListBySalaryPlan(Job job, Date effectiveDate) {
        User user = getWebUserService().getUser();
        int employee = 0;
        int supervisor = 0;
        int payrollProcessor = 0;
        int interfaceManager = 0;
        String queryRole;
        if (user.hasRole(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE)) {
        	queryRole = "payrollProcessor";
        } else if (user.hasRole(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE)) {
        	queryRole = "supervisor";
        } else {
        	queryRole = "employee";
        }
        return this.getEarningService().getEarnCodeListBySalaryPlan(job.getDepartment(), job.getLocation(), job.getSalaryPlan(), queryRole);
    }

    public List getEarnCodeListByEmployeeType(String employeeType, Date effectiveDate) {
        User user = getWebUserService().getUser();
        if (Job.HOURLY_EMPLOYEE_TYPE.equals(employeeType)) {
            if (user.hasRole(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE)) {
                return getEarningService().getEarnCodesListFromEarnProgram(EarningService.HOURLY_PAYROLL_PROCESSOR_EARN_PROGRAM, effectiveDate);
            } else if (user.hasRole(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE)) {
                return getEarningService().getEarnCodesListFromEarnProgram(EarningService.HOURLY_SUPERVISOR_EARN_PROGRAM, effectiveDate);
            } else {
                return getEarningService().getEarnCodesListFromEarnProgram(EarningService.HOURLY_EMPLOYEE_EARN_PROGRAM, effectiveDate);
            }

        } else {
            if (user.hasRole(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE)) {
                return getEarningService().getEarnCodesListFromEarnProgram(EarningService.BIWEEKLY_PAYROLL_PROCESSOR_EARN_PROGRAM, effectiveDate);
            } else if (user.hasRole(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE)) {
                return getEarningService().getEarnCodesListFromEarnProgram(EarningService.BIWEEKLY_SUPERVISOR_EARN_PROGRAM, effectiveDate);
            } else {
                return getEarningService().getEarnCodesListFromEarnProgram(EarningService.BIWEEKLY_EMPLOYEE_EARN_PROGRAM, effectiveDate);
            }
        }

    }

    public BigDecimal getCalculatedHours(TimesheetDocument timesheetDocument, Object object) {
        String earnCode;
        boolean isHoursEntry;
        boolean isCompensationTime;
        BigDecimal callbackMinimumHours;
        Time beginTime;
        Time endTime;
        Date hourDate;
        Date today = null;
        Date payEndDate = getPayCalendarService().getNextPayEndDate(new Date());
        BigDecimal hours = new BigDecimal(0);

        if (object instanceof TimeBlock) {
            TimeBlock timeblock = (TimeBlock)object;
            try {
                today = timeblock.getBeginTime().getDate();
            } catch (Exception e) {
            }
            if (today == null) {
                today = new Date();
            }
            earnCode = timeblock.getEarnCode();
            isCompensationTime = getEarningService().isCompensationEarningsEarnCode(earnCode, today);
            callbackMinimumHours = getEarningService().getCallbackMinimumHours(earnCode, getAssignmentService().getAssignment(timeblock.getAssignment(timesheetDocument.getDocumentHeader().getUniversityId()), today), payEndDate);
            isHoursEntry = getEarningService().isHoursDetailCollectByHoursEarnCode(earnCode, payEndDate);
            beginTime = new Time(timeblock.getBeginTime());
            beginTime.setZone(timeblock.getBeginTsTz());
            endTime = new Time(timeblock.getEndTime());
            endTime.setZone(timeblock.getEndTsTz());
            hourDate = timeblock.getBeginTime().getDate();

            hours = timeblock.getHours();

        } else if (object instanceof HourDetail) {
            HourDetail hourDetail = (HourDetail)object;
            try {
                today = hourDetail.getHoursDetail().getDate();
            } catch (Exception e) {
            }
            if (today == null) {
                today = new Date();
            }
            earnCode = hourDetail.getEarnCode();
            isCompensationTime = getEarningService().isCompensationEarningsEarnCode(earnCode, today);
            callbackMinimumHours = getEarningService().getCallbackMinimumHours(earnCode, getAssignmentService().getAssignment(hourDetail.getAssignment(), today), payEndDate);
            isHoursEntry = getEarningService().isHoursDetailCollectByHoursEarnCode(earnCode, payEndDate);
            beginTime = hourDetail.getBeginTime();
            beginTime.setZone(hourDetail.getBeginTsTz());
            endTime = hourDetail.getEndTime();
            endTime.setZone(hourDetail.getEndTsTz());
            hourDate = hourDetail.getHoursDetail().getDate();
            hours = hourDetail.getHours();

        } else {
            return new BigDecimal(0);
        }

        if (isHoursEntry) {
            Timestamp timestamp = Timestamp.NOON_TIMESTAMP;
            if (object instanceof TimeBlock) {
                TimeBlock timeblock = (TimeBlock)object;
                timestamp.setDate(today);
                timestamp.setAmPm("PM");
                timeblock.setBeginTime(timestamp);
                timeblock.setEndTime(timestamp);

                return timeblock.getHours().setScale(2,BigDecimal.ROUND_HALF_UP);
            } else {
                HourDetail hourDetail = (HourDetail)object;
                hourDetail.setBeginTime(new Time(timestamp));
                hourDetail.setEndTime(new Time(timestamp));

                return hourDetail.getHours().setScale(2,BigDecimal.ROUND_HALF_UP);
            }
        }

        if (endTime == null || beginTime == null) {
            return new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        hours = Time.getTimeDifferenceInHours(endTime, beginTime, hourDate);
        if (isCompensationTime) {
            hours = hours.multiply(edu.iu.uis.hr.client.Utility.OVERTIME_FACTOR);
        }
        if (callbackMinimumHours.compareTo(new BigDecimal(0)) != 0) {
            if (hours.compareTo(callbackMinimumHours) < 0) {
                hours = callbackMinimumHours;
            }
        }
        return hours.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void takeSnapshot(Date payEndDate) {
        try {
            getDocumentHeaderDataAccess().takeSnapshot(payEndDate);
        } catch (Exception e) {
            throw new ExceptionAdapter(e, getLogger());
        }
    }

    public TypedPersistentMaintainedEntityList lookupPayrollExtractReports(PayrollExtractReportSearchCriteria searchCriteria) {
        return getDocumentHeaderSnapshotDataAccess().lookupPayrollExtractReports(searchCriteria);
    }

    public List getDocumentHeadersEnroute(Date payEndDate) {
        return getDocumentHeaderDataAccess().getDocumentHeadersEnroute(payEndDate);
    }

    public List getDocumentHeadersRoutedToCanceled(Date payEndDate) {
        return getDocumentHeaderDataAccess().getDocumentHeadersRoutedToCanceled(payEndDate);
    }

    public void getExtractReportInfo(List documentHeaders, Collection col) {
    	getDocumentHeaderDataAccess().getExtractReportInfo(documentHeaders,col);
    }

    public TypedPersistentMaintainedEntityList getCachedWeeklyOvertimeRule() {
        return getWeeklyOvertimeRuleDataAccess().getActiveSortedWeeklyOvertimeRules();
    }

    public DocumentHeaderSnapshotDataAccess getDocumentHeaderSnapshotDataAccess() {
        return documentHeaderSnapshotDataAccess;
    }

    public void setDocumentHeaderSnapshotDataAccess(DocumentHeaderSnapshotDataAccess documentHeaderSnapshotDataAccess) {
        this.documentHeaderSnapshotDataAccess = documentHeaderSnapshotDataAccess;
    }

    public boolean isAuthorized(HourDetail hourDetail) {
        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
        Assignment assignment = new Assignment(assignmentKeyMap);

        Job job = getJobService().getJob((String)assignmentKeyMap.get(FieldNames.UNIVERSITY_ID), (BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hourDetail.getHoursDetail().getDate());
        assignment.setJob(job);

        // if sync and reg - attach error
            if (getAssignmentService().isSynchronousAssignment(assignment) && getEarningService().isRegularAttendanceEarningsEarnCode(hourDetail.getEarnCode())) {
                hourDetail.getEntityErrors().add(new String[] { EarnCode.EARN_CODE }, Context.getMessage(MessageKeyConstants.ERROR_INVALID_COPY_ACTION));
            }
        if (hourDetail.isErroneous()) {
            return false;
        }
        return true;
    }

    public boolean isAuthorized(edu.iu.uis.hr.tk.directory.entity.User user, HourDetail hourDetail) {
        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());

        if (Utility.hasValue(assignmentKeyMap)) {
            String workAreaString = assignmentKeyMap.get(FieldNames.WORK_AREA).toString();
            WorkArea workArea = getAssignmentService().getCurrentWorkArea(new BigDecimal(workAreaString), new Date());
            Job job = getJobService().getJob(assignmentKeyMap.get(FieldNames.UNIVERSITY_ID).toString(), new BigDecimal(assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD).toString()), hourDetail.getHoursDetail().getDate());
            if(!user.hasJurisdictionOver(workArea, job)) {
                hourDetail.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, Context.getMessage(MessageKeyConstants.ERROR_INVALID_HOUR_DETAIL_ASSIGNMENT));
            }
        }
        if (hourDetail.isErroneous()) {
            return false;
        }
        return true;
    }

    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    public void setDirectoryService(DirectoryService directoryService) {
        if (directoryService != null) {
            this.directoryService = directoryService;
        }
    }

    public PositionService getPositionService() {
        return positionService;
    }

    public void setPositionService(PositionService positionService) {
        if (positionService != null) {
            this.positionService = positionService;
        }
    }

    public UserPreferenceService getUserPreferenceService() {
        return userPreferenceService;
    }

    public void setUserPreferenceService(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }


    /**
     * Method to get a set of actual times versus rounded times for the Actual time lookup
     */
    public TypedPersistentMaintainedEntityList lookupActualTime(ActualTimeSearchCriteria searchCriteria) {
		DocumentHeader docHeader = getDocumentHeaderDataAccess().getDocumentHeader(searchCriteria.getDocumentId());
		if (!Utility.hasValue(docHeader)) {
			return null; //displays nothing if no documentHeader
		}

		/* Getting the time blocks for the person based on document Id */
		List<TimeBlock> timeBlockList = getTimeBlockDataAccess().getTimeBlocksOrderByAscending(searchCriteria.getDocumentId());
		TypedPersistentMaintainedEntityList comparisonRecords = new TypedPersistentMaintainedEntityList(ActualTime.class);

       for (TimeBlock timeBlock : timeBlockList) {

    	   if (timeBlock.getBeginTime().compareTo(timeBlock.getEndTime()) != 0 && this.getEarningService().isRegularAttendanceEarningsEarnCode(timeBlock.getEarnCode())) { //ignoring timeblocks with 0 hours
				TypedPersistentMaintainedEntityList clockLogList = getClockLogDataAccess().lookupClockLogsPerDateAndAction(docHeader.getUniversityId(), timeBlock);
	            Timestamp actualClockIn = new Timestamp();
	            Timestamp actualClockOut = new Timestamp();
	            String clockInDisplay = new String();
	            String clockOutDisplay = new String();

				for (Iterator clockLogIt = clockLogList.iterator(); clockLogIt.hasNext();) {
					ClockLog clockLog = (ClockLog) clockLogIt.next();
					if (Clock.ON_THE_CLOCK_CODES.contains(clockLog.getAction())){
						if (!Utility.hasValue(actualClockIn) || clockLog.getTimestamp().compareTo(actualClockIn) > 0){ //gets maximum clock in timestamp
							actualClockIn = clockLog.getTimestamp();
						}
					}
					if (Clock.OFF_THE_CLOCK_CODES.contains(clockLog.getAction())){
						if (!Utility.hasValue(actualClockOut) || clockLog.getTimestamp().compareTo(actualClockOut) < 0) { //gets minimum clock out timestamp
							actualClockOut = clockLog.getTimestamp();
						}
					}
				}

				if (Utility.hasValue(actualClockIn)){
					clockInDisplay = new TimedDate(actualClockIn).getTime().toString();
					clockInDisplay = new Time(actualClockIn).toString();
				}
				if (Utility.hasValue(actualClockOut)){
					clockOutDisplay = new TimedDate(actualClockOut).getTime().toString();
				}

	            Job currentJob = getJobService().getJob(docHeader.getUniversityId(), timeBlock.getEmployeeRecord(), timeBlock.getBeginTime().getDate());
	            Assignment assignment = new Assignment();
	            if (Utility.hasValue(currentJob)) {
	              assignment = currentJob.getAssignment(timeBlock.getWorkArea(), timeBlock.getTask());
	            } else {
	                throw new ApplicationException("No listed Job was found for the current timesheet", Logger.getLogger(TimesheetServiceImpl.class));
	            }

	           /*Creating the ActualTime instance and adding it to the records list */
	           comparisonRecords.add(new ActualTime(timeBlock.getBeginTime().getDate(), getAssignmentService().getAssignmentDescription(assignment), clockInDisplay,
	                   clockOutDisplay, new TimedDate(timeBlock.getBeginTime()).getTime(), new TimedDate(timeBlock.getEndTime()).getTime()));
	       }
	   }
       return comparisonRecords;
    }

	public boolean isDocumentLockedByUser(String documentId, String universityId) {
		DocumentLock lock = getDocumentLockDataAccess().getDocumentLock(documentId);
		boolean isLockedByUser = false;
        if (Utility.hasValue(lock)) {
            if (getWebUserService().getUser().getUniversityId().equals(lock.getUserUniversityId())) {
            	isLockedByUser = true;
            }
        }
        return isLockedByUser;
	}

// Timeblock Maintenance Methods
	public TypedPersistentMaintainedEntityList lookupTimeBlocks(TimeBlockSearchCriteria searchCriteria){
		return new TypedPersistentMaintainedEntityList(TimeBlock.class, getTimeBlockDataAccess().getTimeBlocks(searchCriteria.getDocumentId()));
	}

    public void removeTimeBlockManually(TimeBlock timeBlock) {
    	List timeBlockList = new ArrayList();
    	timeBlockList.add(timeBlock);
    	archiveTimeBlocks(timeBlockList,ACTION_HISTORY_MANUALLY_REMOVED);
        getTimeBlockDataAccess().removeTimeBlockManually(timeBlock);
    }

    public void removeClockLogManually(String universityId) {
    	getDataAccess(ClockLog.class).removeRecords(FieldNames.UNIVERSITY_ID, universityId, ClockLog.class);
    }

    public void addTimeBlockManually(TimeBlock timeBlock){
    	getTimeBlockDataAccess().addTimeBlockManually(timeBlock);
    }

	public void addTimeBlockManually(TimeBlockHistory timeBlock) {
		getTimeBlockHistoryDataAccess().store(timeBlock);
	}

	public void removeTimeBlockManually(TimeBlockHistory timeBlock) {
		getTimeBlockHistoryDataAccess().delete(timeBlock);
	}

	public List getTimeBlocks(String documentId){
		return getTimeBlockDataAccess().getTimeBlocks(documentId);
	}

	public ClockLog getClockLog(String universityId, Timestamp timestamp) {
		return getClockLogDataAccess().getClockLog(universityId, timestamp);
	}

}