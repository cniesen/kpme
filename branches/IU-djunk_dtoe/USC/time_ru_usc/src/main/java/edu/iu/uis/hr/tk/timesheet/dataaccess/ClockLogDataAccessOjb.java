package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.directory.entity.User;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.entity.WorkAreaStatusInquirySearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLogSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;

public class ClockLogDataAccessOjb extends AbstractDataAccessOjb implements ClockLogDataAccess {

    private static final Logger LOG = Logger.getLogger(ClockLogDataAccessOjb.class);    

    public void setDerivedValues(PersistentMaintainedEntity persistentMaintainedEntity, User user) {
        if (persistentMaintainedEntity instanceof ClockLog) {
            ((ClockLog)persistentMaintainedEntity).setIpAddress(user.getIpAddress());
        }
        super.setDerivedValues(persistentMaintainedEntity, user);
    }

    
    @SuppressWarnings("unchecked")
	public List lookupClockLogs(WorkAreaStatusInquirySearchCriteria searchCriteria) {
        Criteria lookupClockLogsCriteria = new Criteria();
        getStandardLookupLogic(lookupClockLogsCriteria, FieldNames.WORK_AREA, searchCriteria.getWorkArea());
        
        Timestamp endTimestamp = searchCriteria.getEndTimestamp();
        if (!Utility.hasValue(endTimestamp)) {            
            searchCriteria.setEndTimestamp(new TimedDate().getTimestamp());
        }
 
        Timestamp beginTimestamp = new TimedDate(searchCriteria.getEndTimestamp()).addHours(-24).getTimestamp();     
        lookupClockLogsCriteria.addGreaterOrEqualThan(FieldNames.CLOCK_TIME, beginTimestamp);
        lookupClockLogsCriteria.addLessOrEqualThan(FieldNames.CLOCK_TIME, searchCriteria.getEndTimestamp());       

        return getValidValuesListByQuery(QueryFactory.newQuery(ClockLog.class, lookupClockLogsCriteria));
    }

    public ClockLog getClockLog(String universityId) {
        Criteria currentRecordCriteria = new Criteria();        
        currentRecordCriteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
        
        Criteria clockTimeJoinCriteria = new Criteria();
        clockTimeJoinCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        // Taking out employee_record from query to ensure to get latest current clock action regardless of employee_record
        //clockTimeJoinCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);

        ReportQueryByCriteria clockTimeSubQuery = QueryFactory.newReportQuery(ClockLog.class, clockTimeJoinCriteria);
        clockTimeSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.CLOCK_TIME).append(")").toString() });         
        
        currentRecordCriteria.addEqualTo(FieldNames.CLOCK_TIME, clockTimeSubQuery);

        Criteria clockTimestampJoinCriteria = new Criteria();
        clockTimestampJoinCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        //clockTimestampJoinCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        clockTimestampJoinCriteria.addEqualToField(FieldNames.CLOCK_TIME, Criteria.PARENT_QUERY_PREFIX + FieldNames.CLOCK_TIME);
        
        ReportQueryByCriteria clockTimestampSubQuery = QueryFactory.newReportQuery(ClockLog.class, clockTimestampJoinCriteria);       
        clockTimestampSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.TIMESTAMP).append(")").toString() });         
        
        currentRecordCriteria.addEqualTo(FieldNames.TIMESTAMP, clockTimestampSubQuery);
        
        return (ClockLog)getObjectByQuery(QueryFactory.newQuery(ClockLog.class, currentRecordCriteria));
    }
    
    //Retrieving all open clock logs from last pay period
    @SuppressWarnings("unchecked")
	public List getOpenClockLogs() {
        List OpenBlockCodes = edu.iu.uis.hr.tk.timesheet.entity.Clock.ON_THE_CLOCK_CODES;
        OpenBlockCodes.add(edu.iu.uis.hr.tk.timesheet.entity.Clock.BREAK_OUT);
        Criteria criteria = new Criteria();
        criteria.addIn(FieldNames.ACTION, OpenBlockCodes);

        Criteria clockTimeJoinCriteria = new Criteria();
        clockTimeJoinCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        ReportQueryByCriteria clockTimeSubQuery = QueryFactory.newReportQuery(ClockLog.class, clockTimeJoinCriteria);
        clockTimeSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.CLOCK_TIME).append(")").toString() });
        
        criteria.addEqualTo(FieldNames.CLOCK_TIME, clockTimeSubQuery);

        Criteria clockTimestampJoinCriteria = new Criteria();
        clockTimestampJoinCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        ReportQueryByCriteria clockTimestampSubQuery = QueryFactory.newReportQuery(ClockLog.class, clockTimestampJoinCriteria);
        clockTimestampSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.TIMESTAMP).append(")").toString() });
        
        criteria.addEqualTo(FieldNames.TIMESTAMP, clockTimestampSubQuery);
        
        PayCalendar payCalendar = new PayCalendar();
        payCalendar = TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new TimelessDate(new java.util.Date()).getDate());
        
        Timestamp startDate = new TimedDate(payCalendar.getPayBeginDate()).getTimestamp();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(payCalendar.getPayEndDate());
        cal.set(Calendar.HOUR, 11);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.AM_PM, Calendar.PM);
        Timestamp endDate = new TimedDate(cal.getTime()).getTimestamp();
        criteria.addBetween(FieldNames.CLOCK_TIME, startDate, endDate);
        return (List)getCollectionByQuery(QueryFactory.newQuery(ClockLog.class, criteria));
    }
    
    
    public TypedPersistentMaintainedEntityList lookupClockLogsPerDate(ClockLogSearchCriteria searchCriteria) {
        Criteria lookupCriteria = new Criteria();
        lookupCriteria.addEqualTo(FieldNames.UNIVERSITY_ID, searchCriteria.getUniversityId());
//        getStandardLookupLogic(lookupCriteria, FieldNames.UNIVERSITY_ID, searchCriteria.getUniversityId());
        Query query = QueryFactory.newQuery(ClockLog.class, lookupCriteria);
        ((QueryByCriteria)query).addOrderByDescending(FieldNames.CLOCK_TIME);
        ((QueryByCriteria)query).setEndAtIndex(100);
        return new TypedPersistentMaintainedEntityList(query.getSearchClass(), getCollectionByQuery(query));
    }
    
    /**
     * Method to pull up ClockLog records from the database to display the actual vs rounded time.
     * The ClockLog records are pulled up based on each time-block for a particular document Id.
     * 
     * @param emplId The university Id of the person trying to view the lookup
     * @param block The time-block for which the actual and rounded times need to be pulled up
     *
     * Example of query generated:
     * SELECT * FROM tk.tk_clock_log_t A0 WHERE 
 	 *  A0.emplid = '0000072833' AND A0.user_emplid = '0000072833' AND A0.work_area_id = '1358'
     *  AND  ( (A0.clock_ts = '2008-04-08 09:48' AND  (A0.clock_action_cd = 'CI' OR A0.clock_action_cd = 'LI'))
     *   OR    (A0.clock_ts = '2008-04-08 10:12' AND  (A0.clock_action_cd = 'CO' OR A0.clock_action_cd = 'LO')) ) 
     */
    public TypedPersistentMaintainedEntityList lookupClockLogsPerDateAndAction(String emplId, TimeBlock block) {
        Criteria masterCriteria = new Criteria();
        Criteria clockInSubCriteria = new Criteria();
        Criteria clockOrLunchInSubCriteria = new Criteria();
        clockInSubCriteria.addEqualTo(FieldNames.CLOCK_TIME, block.getBeginTime());
        
        Criteria clockInFieldNameCrit = new Criteria();
        clockInFieldNameCrit.addEqualTo(FieldNames.ACTION, Clock.CLOCK_IN);
        clockOrLunchInSubCriteria.addAndCriteria(clockInFieldNameCrit);
        Criteria clockInLunchCrit = new Criteria();
        clockInLunchCrit.addEqualTo(FieldNames.ACTION, Clock.LUNCH_IN);
        clockOrLunchInSubCriteria.addOrCriteria(clockInLunchCrit);
        clockInSubCriteria.addAndCriteria(clockOrLunchInSubCriteria);
        
        
        Criteria clockOutSubCriteria = new Criteria();
        Criteria clockOrLunchOutSubCriteria = new Criteria();
        clockOutSubCriteria.addEqualTo(FieldNames.CLOCK_TIME, block.getEndTime());
        
        Criteria clockOutCriteria = new Criteria();
        clockOutCriteria.addEqualTo(FieldNames.ACTION, Clock.CLOCK_OUT);
        clockOrLunchOutSubCriteria.addAndCriteria(clockOutCriteria);
        Criteria clockOutLunchCriteria = new Criteria();
        clockOutLunchCriteria.addEqualTo(FieldNames.ACTION, Clock.LUNCH_OUT);
        clockOrLunchOutSubCriteria.addOrCriteria(clockOutLunchCriteria);
        
        clockOutSubCriteria.addAndCriteria(clockOrLunchOutSubCriteria);
        
        Criteria clockSubCriteria = new Criteria();
        clockSubCriteria.addAndCriteria(clockInSubCriteria);
        clockSubCriteria.addOrCriteria(clockOutSubCriteria);
        
        masterCriteria.addEqualTo(FieldNames.UNIVERSITY_ID, emplId);
        masterCriteria.addEqualTo(FieldNames.USER_UNIVERSITY_ID, emplId);
        masterCriteria.addEqualTo(FieldNames.WORK_AREA, block.getWorkArea().intValue());
        masterCriteria.addAndCriteria(clockSubCriteria);
        
        /* Creating the query object and putting in the criterias*/
        Query query = QueryFactory.newQuery(ClockLog.class, masterCriteria);
        /*Sorting the given records in ascending order of timestamp*/
        ((QueryByCriteria)query).addOrderByAscending(FieldNames.TIMESTAMP);
        return  new TypedPersistentMaintainedEntityList(query.getSearchClass(), getCollectionByQuery(query));
    }


	public ClockLog getClockLog(String universityId, Timestamp clockTimestamp) {
        Criteria criteria = new Criteria();        
        criteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
        criteria.addEqualTo("clockTime", clockTimestamp);
        return (ClockLog)getObjectByQuery(QueryFactory.newQuery(ClockLog.class, criteria));
	}

	public TypedPersistentMaintainedEntityList getClockLogs(String universityId) {
        Criteria criteria = new Criteria();        
        criteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
        Query query = QueryFactory.newQuery(ClockLog.class, criteria);
        return new TypedPersistentMaintainedEntityList(query.getSearchClass(), getCollectionByQuery(query));
	}
	
}
