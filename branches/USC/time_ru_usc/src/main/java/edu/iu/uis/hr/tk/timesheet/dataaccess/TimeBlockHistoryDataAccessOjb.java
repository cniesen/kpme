package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.directory.entity.User;
import edu.iu.uis.hr.entity.AuditedPersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.EffectivePersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.timesheet.entity.DailyNotification;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockHistory;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockHistorySearchCriteria;

public class TimeBlockHistoryDataAccessOjb extends AbstractDataAccessOjb implements TimeBlockHistoryDataAccess {
    private static final Logger LOG = Logger.getLogger(TimeBlockHistoryDataAccessOjb.class);
    private static final String C = "C";
    
    public TypedPersistentMaintainedEntityList lookupTimeBlockHistory(TimeBlockHistorySearchCriteria searchCriteria) {
        Criteria lookupCriteria = new Criteria();
        lookupCriteria.addEqualTo(searchCriteria.getLookupField(), StringUtils.trim(searchCriteria.getDocumentId()));
//        getStandardLookupLogic(lookupCriteria, FieldNames.DOCUMENT_ID, searchCriteria.getDocumentId());
        return getAllMaintainableValuesListByQuery(QueryFactory.newQuery(TimeBlockHistory.class, lookupCriteria));
    }

    //Gets all records in timeblock history table that have been modified by other than the document's owner within a specific date range
    // (mainly used by the daily changes batch notification)
    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(Date payEndDate, Timestamp startDate, Timestamp endDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        criteria.addBetween(FieldNames.TIMESTAMP, startDate, endDate);
        QueryByCriteria query = QueryFactory.newQuery(DailyNotification.class, criteria);
        return new TypedPersistentMaintainedEntityList(query.getSearchClass(),getCollectionByQuery(query));
    }
    
    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(Date payEndDate, Timestamp startDate, Timestamp endDate, String documentId) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        criteria.addBetween(FieldNames.TIMESTAMP, startDate, endDate);
        criteria.addEqualTo(FieldNames.DOCUMENT_ID, documentId);
        QueryByCriteria query = QueryFactory.newQuery(DailyNotification.class, criteria);
        return new TypedPersistentMaintainedEntityList(query.getSearchClass(),getCollectionByQuery(query));
    }
    
    //Gets list of all documents with timeblocks that were modified by other than the owner.
    // (mainly used by the daily changes batch notification)
    public List getTimeBlockHistoryModifiedDocuments(Date payEndDate, Timestamp startDate, Timestamp endDate){
    	List modifiedDocuments = new ArrayList();
//        String query = "SELECT document_Id, emplid FROM tk_daily_notification_v WHERE pay_end_dt=to_date('" + new TimelessDate(payEndDate) +"', 'MM/DD/YYYY') AND timestamp BETWEEN to_date('" + new TimedDate(startDate)  + "', 'MM-DD-YYYY  HH:MI AM') AND to_date('" + new TimedDate(endDate)  + "', 'MM-DD-YYYY  HH:MI AM') GROUP BY document_Id, emplid";
        String query = "SELECT document_Id, emplid FROM tk_daily_notification_v WHERE timestamp BETWEEN to_date('" + new TimedDate(startDate)  + "', 'MM-DD-YYYY  HH:MI AM') AND to_date('" + new TimedDate(endDate)  + "', 'MM-DD-YYYY  HH:MI AM') GROUP BY document_Id, emplid";
        LOG.debug(query);
        SqlRowSet rs = TKServiceLocator.getTkJdbcTemplate().queryForRowSet(query);
		while (rs.next()) {
	        DailyNotification modifiedDocument = new DailyNotification();
	  	 	modifiedDocument.setDocumentId(rs.getString("document_Id"));
	   	 	modifiedDocument.setUniversityId(rs.getString("emplid"));
	   	 	modifiedDocument.setPayEndDate(payEndDate);
	   	 	modifiedDocuments.add(modifiedDocument);
		}
        return modifiedDocuments;
    }


    public TypedPersistentMaintainedEntityList getTimeBlockHistoryModifiedRecords(String documentId) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.DOCUMENT_ID, documentId);
        QueryByCriteria query = QueryFactory.newQuery(DailyNotification.class, criteria);
        return new TypedPersistentMaintainedEntityList(query.getSearchClass(),getCollectionByQuery(query));
    }
    
    public void setDerivedValues(PersistentMaintainedEntity persistentMaintainedEntity, User user) {
        if (EffectivePersitentDatabaseMaintainedEntity.class.isAssignableFrom(persistentMaintainedEntity.getClass())) {
            super.deriveEffectiveSequence((EffectivePersitentDatabaseMaintainedEntity)persistentMaintainedEntity);
        }
        if (AuditedPersitentDatabaseMaintainedEntity.class.isAssignableFrom(persistentMaintainedEntity.getClass())) {
            ((AuditedPersitentDatabaseMaintainedEntity)persistentMaintainedEntity).setUserUniversityId(user.getUniversityId());
            Timestamp timestamp = new TimedDate().getTimestamp();
            TimeBlockHistory timeBlockHist = ((TimeBlockHistory)persistentMaintainedEntity);
            timeBlockHist.setTimestampTz(timeBlockHist.getBeginTsTz());
            
            if (Utility.hasValue(timeBlockHist.getBeginTsTz())) {
                if(timeBlockHist.getBeginTsTz().substring(0,1).equals(C)){
                	if(timestamp.getHour().intValue() == 1){
                		timestamp.setHour(new BigDecimal(12));
                	}else{
                		timestamp.setHour(new BigDecimal(timestamp.getHour().intValue() - 1));
                	}
                }
            }
            ((AuditedPersitentDatabaseMaintainedEntity)persistentMaintainedEntity).setTimestamp(timestamp);
            if (!Utility.hasValue(timeBlockHist.getTimestampOriginal())){
            	timeBlockHist.setTimestampOriginal(timeBlockHist.getTimestamp());
            	timeBlockHist.setTimestampOriginalTz(timeBlockHist.getTimestampTz());
            }
        }
    }    
    
}
