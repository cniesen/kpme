package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.directory.entity.User;
import edu.iu.uis.hr.entity.AuditedPersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.EffectivePersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;

public class TimeBlockDataAccessOjb extends AbstractDataAccessOjb implements TimeBlockDataAccess {
    private static final Logger LOG = Logger.getLogger(TimeBlockDataAccessOjb.class);
    private static final String C = "C";

    public TimeBlock getTimeBlock(String documentId, BigDecimal employeeRecord, BigDecimal workArea, BigDecimal task, String earnCode, Timestamp beginTime, Timestamp endTime) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.DOCUMENT_ID, documentId);
        criteria.addEqualTo(FieldNames.EMPLOYEE_RECORD, employeeRecord);
        criteria.addEqualTo(FieldNames.WORK_AREA, workArea);
        criteria.addEqualTo(FieldNames.TASK, task);
        criteria.addEqualTo(FieldNames.EARN_CODE, earnCode);
        criteria.addEqualTo(FieldNames.BEGIN_TIME, beginTime);
        criteria.addEqualTo(FieldNames.END_TIME, endTime);

        return (TimeBlock) getObjectByQuery(QueryFactory.newQuery(TimeBlock.class, criteria));
    }

    public List getTimeBlocks(String documentId) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.DOCUMENT_ID, documentId);

        return getValidValuesListByQuery(QueryFactory.newQuery(TimeBlock.class, criteria));
    }

    public List getTimeBlocks(String documentId, Date date) {
        // this is for get previous day or next day timeblock for shift diff
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.DOCUMENT_ID, documentId);
        Timestamp beginTime=new Timestamp();
        beginTime.setDate(date);
        // these constant should be defined somewhere
        beginTime.setAmPm("AM");
        beginTime.setHour(new BigDecimal(12));
        beginTime.setMinute(new BigDecimal(0));
        beginTime.setSecond(new BigDecimal(0));
        Timestamp endTime=new Timestamp();
        endTime.setDate(date);
        endTime.setAmPm("PM");
        endTime.setHour(new BigDecimal(11));
        endTime.setMinute(new BigDecimal(59));
        endTime.setSecond(new BigDecimal(59));
        criteria.addGreaterOrEqualThan(FieldNames.BEGIN_TIME, beginTime);
        criteria.addLessOrEqualThan(FieldNames.END_TIME, endTime);
        return getValidValuesListByQuery(QueryFactory.newQuery(TimeBlock.class, criteria));
    }
    
    public void setDerivedValues(PersistentMaintainedEntity persistentMaintainedEntity, User user) {
        if (EffectivePersitentDatabaseMaintainedEntity.class.isAssignableFrom(persistentMaintainedEntity.getClass())) {
            super.deriveEffectiveSequence((EffectivePersitentDatabaseMaintainedEntity)persistentMaintainedEntity);
        }
        if (AuditedPersitentDatabaseMaintainedEntity.class.isAssignableFrom(persistentMaintainedEntity.getClass())) {
            ((AuditedPersitentDatabaseMaintainedEntity)persistentMaintainedEntity).setUserUniversityId(user.getUniversityId());
            Timestamp timestamp = new TimedDate().getTimestamp();
            TimeBlock timeBlock = ((TimeBlock)persistentMaintainedEntity);
            timeBlock.setTimestampTz(timeBlock.getBeginTsTz());
            if (Utility.hasValue(timeBlock.getBeginTsTz())) {
                if (timeBlock.getBeginTsTz().substring(0,1).equals(C)){
                	if (timestamp.getHour().intValue() == 1){
                		timestamp.setHour(new BigDecimal(12));
                	} else {
                		timestamp.setHour(new BigDecimal(timestamp.getHour().intValue() - 1));
                	}
                }
            }
            ((AuditedPersitentDatabaseMaintainedEntity)persistentMaintainedEntity).setTimestamp(timestamp);
        }
    }
    
    /**
     * Method to get ascending-order sorted time blocks depending on the field name specified as parameter
     */
    public List getTimeBlocksOrderByAscending(String documentId) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.DOCUMENT_ID, documentId);
        criteria.addOrderByAscending("begin_ts");
        QueryByCriteria query = QueryFactory.newQuery(TimeBlock.class, criteria);
        query.addOrderByAscending("begin_ts");
        return getValidValuesListByQuery(query);
    }
    
    
    //Removes timeblock from database 
    public void removeTimeBlockManually(TimeBlock timeBlock){
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.DOCUMENT_ID, timeBlock.getDocumentId());
        criteria.addEqualTo(FieldNames.EMPLOYEE_RECORD, timeBlock.getEmployeeRecord());
        criteria.addEqualTo(FieldNames.WORK_AREA, timeBlock.getWorkArea());
        criteria.addEqualTo(FieldNames.BEGIN_TIME, timeBlock.getBeginTime());
        criteria.addEqualTo(FieldNames.END_TIME, timeBlock.getEndTime());
        criteria.addEqualTo(FieldNames.TIMESTAMP, timeBlock.getTimestamp());
        deleteByQuery(QueryFactory.newQuery(TimeBlock.class, criteria));
    }
    
    public void addTimeBlockManually(TimeBlock timeBlock){ //used for unit test
    	store(timeBlock);    	
    }
    
}
