package edu.iu.uis.hr.tk.timesheet.client;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.springframework.dao.DataAccessException;

import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.client.BatchProgram;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.job.funding.service.AssignmentService;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.tk.timesheet.dataaccess.ClockLogDataAccess;
import edu.iu.uis.hr.tk.timesheet.dataaccess.DocumentLockDataAccess;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLock;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class EndPayPeriodTimesheetBatchProgram implements BatchProgram {
    private static final Logger LOG = Logger.getLogger(EndPayPeriodTimesheetBatchProgram.class);

    private AssignmentService assignmentService;
    private ClockLogDataAccess clockLogDataAccess;
    private PayCalendarService payCalendarService;
    private TimesheetService timesheetService;
    private DocumentLockDataAccess documentLockDataAccess;

    private static final BigDecimal ELEVEN = new BigDecimal(11);
    private static final BigDecimal TWELVE = new BigDecimal(12);
    private static final BigDecimal FIFTYNINE = new BigDecimal(59);
    private static final BigDecimal ZERO = new BigDecimal(0);
    private static final String PM = "PM";
    private static final String AM = "AM";
    private static final String DOCUMENT_ID_OBJECT_FIELD = "documentId";
    private static final String PAY_END_DT_OBJECT_FIELD = "payEndDate";
    private static final String DOCUMENT_ID_DATABASE_FIELD = "DOCUMENT_ID";

    private List batchProgramReport = new java.util.ArrayList();

    public void execute() {
        initializeTkOJB();
        Calendar cal = new GregorianCalendar();
        PayCalendar payCalendar = getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).getDate());
        TimelessDate today = new TimelessDate(cal.getTime());
        TimelessDate yesterday = new TimelessDate(cal.getTime()).addDays(-1);
        
        unlockDocumentsForPayPeriod(payCalendar);

        List openClockLogs = getClockLogDataAccess().getOpenClockLogs();
        for (Iterator iter = openClockLogs.iterator(); iter.hasNext();) {
            ClockLog openClockLog = (ClockLog)iter.next();
            try {
                TimesheetDocument timesheetDocument = getTimesheetService().getTimesheetDocument(openClockLog.getUniversityId(), payCalendar.getPayEndDate());
                Timestamp clockTime = new Timestamp();
                clockTime.setHour(ELEVEN);
                clockTime.setMinute(FIFTYNINE);
                clockTime.setSecond(ZERO);
                clockTime.setAmPm(PM);
                clockTime.setDate(yesterday.getDate());
                timesheetDocument.getClock().setClockTime(clockTime);
                if (openClockLog.getAction().equals(edu.iu.uis.hr.tk.timesheet.entity.Clock.CLOCK_IN)) {
                    try {
                        getTimesheetService().clockOut(timesheetDocument);
                        batchProgramReport.add("Employee " + openClockLog.getUniversityId() + " (Clock-out)");
                    } catch (Exception e) {
                        batchProgramReport.add("Error trying to clock-out employee: " + openClockLog.getUniversityId() + " Exception: " + e);
                        continue;
                    }
                } else if (openClockLog.getAction().equals(edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_IN)) {
                    try {
                        getTimesheetService().breakOut(timesheetDocument);
                        batchProgramReport.add("Employee " + openClockLog.getUniversityId() + " (Break-out)");
                    } catch (Exception e) {
                        batchProgramReport.add("Error trying to break-out employee: " + openClockLog.getUniversityId() + " Exception: " + e);
                        continue;
                    }
                } else if (openClockLog.getAction().equals(edu.iu.uis.hr.tk.timesheet.entity.Clock.LUNCH_IN)) {
                    try {
                        getTimesheetService().lunchOut(timesheetDocument);
                        batchProgramReport.add("Employee " + openClockLog.getUniversityId() + " (Lunch-out)");
                    } catch (Exception e) {
                        batchProgramReport.add("Error trying to lunch-out employee: " + openClockLog.getUniversityId() + " Exception: " + e);
                        continue;
                    }
                } else if (openClockLog.getAction().equals(edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_OUT)) {
                    try {
                        getTimesheetService().breakIn(timesheetDocument);
                        getTimesheetService().clockOut(timesheetDocument);
                        batchProgramReport.add("Employee " + openClockLog.getUniversityId() + " (Break-in and clock-out)");
                    } catch (Exception e) {
                        batchProgramReport.add("Error trying to break-in and clock-out employee: " + openClockLog.getUniversityId() + " Exception: " + e);
                        continue;
                    }
                }
                //checks whether the assignment is active
                clockTime.setHour(TWELVE);
                clockTime.setMinute(ZERO);
                clockTime.setSecond(ZERO);
                clockTime.setAmPm(AM);
                clockTime.setDate(today.getDate());
                
                if (edu.iu.uis.hr.entity.logic.Utility.hasValue(timesheetDocument.getAssignment(openClockLog.getEmployeeRecord(), openClockLog.getWorkArea(), openClockLog.getTask(), clockTime))) {
                
                    timesheetDocument.getClock().setClockTime(clockTime);
                    if (openClockLog.getAction().equals(edu.iu.uis.hr.tk.timesheet.entity.Clock.CLOCK_IN)) {
                        try {
                            getTimesheetService().clockIn(timesheetDocument);
                            batchProgramReport.add("Employee " + openClockLog.getUniversityId() + " (Clock-in)");
                        } catch (Exception e) {
                            batchProgramReport.add("Error trying to clock-in employee: " + openClockLog.getUniversityId() + " Exception: " + e);
                            continue;
                        }
                    } else if (openClockLog.getAction().equals(edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_IN)) {
                        try {
                            getTimesheetService().breakIn(timesheetDocument);
                            batchProgramReport.add("Employee " + openClockLog.getUniversityId() + " (Break-in)");
                        } catch (Exception e) {
                            batchProgramReport.add("Error trying to break-in employee: " + openClockLog.getUniversityId() + " Exception: " + e);
                            continue;
                        }
                    } else if (openClockLog.getAction().equals(edu.iu.uis.hr.tk.timesheet.entity.Clock.LUNCH_IN)) {
                        try {
                            getTimesheetService().lunchIn(timesheetDocument);
                            batchProgramReport.add("Employee " + openClockLog.getUniversityId() + " (Lunch-in)");
                        } catch (Exception e) {
                            batchProgramReport.add("Error trying to lunch-in employee: " + openClockLog.getUniversityId() + " Exception: " + e);
                            continue;
                        }
                    } else if (openClockLog.getAction().equals(edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_OUT)) {
                        try {
                            getTimesheetService().clockIn(timesheetDocument);
                            getTimesheetService().breakOut(timesheetDocument);
                            batchProgramReport.add("Employee " + openClockLog.getUniversityId() + " (Clock-in and break-out)");
                        } catch (Exception e) {
                            batchProgramReport.add("Error trying to clock-in and break-out employee: " + openClockLog.getUniversityId() + " Exception: " + e);
                            continue;
                        }
                    }
                }
            } catch (Exception e) {
                batchProgramReport.add("Error creating timesheet for employee: " + openClockLog.getUniversityId() + " Exception: " + e);
            }
        }
        displayBatchProgramReport();
    }

    public void displayBatchProgramReport() {
        // TODO : Talk to Damon to check whether this report is necessary 
        java.util.Collections.sort(batchProgramReport);
        for (Iterator iter = batchProgramReport.iterator(); iter.hasNext();) {
            String element = (String)iter.next();
            LOG.info(element);
        }
    }

    /**
     * Works around a bug(?) in OJB 1.1 (HEAD) where it can't handle concurrent initialization of the OJB persistence broker pool.
     */
    private void initializeTkOJB() {
    	PersistenceBroker broker = PersistenceBrokerFactory.createPersistenceBroker("tkDataSource", null, null);
    	broker.close();
    }
    
    private TimesheetService getTimesheetService() {
        return timesheetService;
    }

    public void setTimesheetService(TimesheetService timesheetService) {
        if (timesheetService != null) {
            this.timesheetService = timesheetService;
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

    public ClockLogDataAccess getClockLogDataAccess() {
        return clockLogDataAccess;
    }

    public void setClockLogDataAccess(ClockLogDataAccess clockLogDataAccess) {
        if (clockLogDataAccess != null) {
            this.clockLogDataAccess = clockLogDataAccess;
        }
    }

    public AssignmentService getAssignmentService() {
        return assignmentService;
    }

    public void setAssignmentService(AssignmentService assignmentService) {
        if (assignmentService != null) {
            this.assignmentService = assignmentService;
        }
    }
    
    public DocumentLockDataAccess getDocumentLockDataAccess() {
        return documentLockDataAccess;
    }
    
    public void setDocumentLockDataAccess(DocumentLockDataAccess documentLockDataAccess) {
        if (documentLockDataAccess != null) {
            this.documentLockDataAccess = documentLockDataAccess;
        }
    }
    
    private void unlockDocumentsForPayPeriod(PayCalendar payCalendar) {
        // set up sub query - need all locked docs in pay period
        ReportQueryByCriteria subQuery;

        // want all in doc header table where pay end is equal to paycalendar.payend
        Criteria subCriteria = new Criteria();
        subCriteria.addEqualTo(PAY_END_DT_OBJECT_FIELD, new java.sql.Timestamp(payCalendar.getPayEndDate().getTime()));
        subQuery = QueryFactory.newReportQuery(DocumentHeader.class, subCriteria);
        subQuery.setAttributes(new String[] {DOCUMENT_ID_DATABASE_FIELD});
        
        // TODO an exists query would be better here? rpiercy
        // grab all in doc lock table that match the subquery above
        // select * from tk_document_lock_t where document_id in 
        // (select document_id from tk_document_header_t where pay_end_dt = [paycalendar.payend()]
        Criteria criteria = new Criteria();
        criteria.addIn(DOCUMENT_ID_OBJECT_FIELD, subQuery);
        Query query = QueryFactory.newQuery(DocumentLock.class, criteria);
        try {
            documentLockDataAccess.deleteByQuery(query);
        } catch (DataAccessException e) {
            batchProgramReport.add("Could not delete the following locked documents.");
            Iterator resultsIter = documentLockDataAccess.getIteratorByQuery(query);
            while(resultsIter.hasNext()) {
                DocumentLock documentLock = (DocumentLock) resultsIter.next();
                batchProgramReport.add("Document ID: " + documentLock.getDocumentId());
            }
        }
    }
}
