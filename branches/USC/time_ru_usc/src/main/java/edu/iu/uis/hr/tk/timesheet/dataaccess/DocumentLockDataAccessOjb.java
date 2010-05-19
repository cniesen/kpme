package edu.iu.uis.hr.tk.timesheet.dataaccess;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLock;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLockSearchCriteria;

public class DocumentLockDataAccessOjb extends AbstractDataAccessOjb implements DocumentLockDataAccess {
    private static final Logger LOG = Logger.getLogger(DocumentLockDataAccessOjb.class);
    
    public DocumentLock getDocumentLock(String documentId) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.DOCUMENT_ID, documentId);
       
        return (DocumentLock)getObjectByQuery(QueryFactory.newQuery(DocumentLock.class, criteria));
    }
    
    public TypedPersistentMaintainedEntityList lookupLockedDocuments(DocumentLockSearchCriteria searchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.DOCUMENT_ID, searchCriteria.getDocumentId());
        return getAllMaintainableValuesListByQuery(QueryFactory.newQuery(DocumentLock.class, lookupCriteria));
    }
    
}
