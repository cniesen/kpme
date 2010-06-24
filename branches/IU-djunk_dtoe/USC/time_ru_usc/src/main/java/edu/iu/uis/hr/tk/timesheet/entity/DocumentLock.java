package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.entity.AuditedPersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.FieldNames;

public class DocumentLock extends AbstractDocumentLock implements  AuditedPersitentDatabaseMaintainedEntity {
	
    private static final Logger LOG = Logger.getLogger(DocumentLock.class);
    public static final int LOCK_TIMEOUT = 8; //time in hours before timesheet lock expires

    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.TIMESTAMP);
    }
    
    
	public DocumentLock() {
		super();
	}

    public DocumentLock(String documentId) {
        setDocumentId(documentId);
    }
    
    public Class getModeAuthorization() {
        return DocumentLockModeAuthorization.class;
    }

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }
    
}

