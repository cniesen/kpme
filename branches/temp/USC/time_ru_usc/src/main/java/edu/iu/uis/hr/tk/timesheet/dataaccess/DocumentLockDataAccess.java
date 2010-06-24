package edu.iu.uis.hr.tk.timesheet.dataaccess;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLock;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLockSearchCriteria;

public interface DocumentLockDataAccess extends DataAccessOjb {

    public DocumentLock getDocumentLock(String documentId);
    
    public TypedPersistentMaintainedEntityList lookupLockedDocuments(DocumentLockSearchCriteria searchCriteria);    
}
