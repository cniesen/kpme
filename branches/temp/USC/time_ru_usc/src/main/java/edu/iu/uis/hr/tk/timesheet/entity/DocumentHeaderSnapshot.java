package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

public class DocumentHeaderSnapshot extends AbstractDocumentHeaderSnapshot implements PersistentMaintainedEntity {
	private static final Logger LOG = Logger.getLogger(DocumentHeaderSnapshot.class);
    private static final String DOCUMENT_STATUS_FIELD_NAME = "documentStatus";
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(DOCUMENT_STATUS_FIELD_NAME);
    }
    
	public DocumentHeaderSnapshot() {
		super();
        setDocumentId(Utility.getDefaultStringValue());
	}
    
    public DocumentHeaderSnapshot(String documentId) {
        this();
        setDocumentId(documentId);    
    }

    public DocumentHeaderSnapshot(String documentId, String universityId, Date payEndDate) {
        this(documentId);
        setUniversityId(universityId);
        setPayEndDate(payEndDate);        
    }
    
//    public Class getModeAuthorization() {
//        return DocumentHeaderModeAuthorization.class;
//    }
    
    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

//    protected List getOperationalLogics(SaveEvent event) {
//        List operationalLogics = new ArrayList();
//        operationalLogics.add(HasValidAssignmentLogic.class);
//        return operationalLogics;
//    }
    public String getDateAsMillis() {
    	if (edu.iu.uis.hr.entity.logic.Utility.hasValue(getPayEndDate())) {
    		return (new Long(getPayEndDate().getTime())).toString();
    	} else {
    		return "0";
    	}
    }
}

