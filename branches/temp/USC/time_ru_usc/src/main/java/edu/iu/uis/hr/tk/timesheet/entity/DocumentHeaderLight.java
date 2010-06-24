package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

//TODO: Check if we can accomplish the same results by setting the auto-retrieve to false on the OJB configuration file.

public class DocumentHeaderLight extends DocumentHeader implements PersistentMaintainedEntity {
	private static final Logger LOG = Logger.getLogger(DocumentHeaderLight.class);
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    
	public DocumentHeaderLight() {
		super();
        setDocumentId(Utility.getDefaultStringValue());
	}
    
    public DocumentHeaderLight(String documentId) {
        this();
        setDocumentId(documentId);    
    }

    public DocumentHeaderLight(String documentId, String universityId, Date payEndDate) {
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

    public boolean equals(Object o) {
    	DocumentHeaderLight compObj = (DocumentHeaderLight)o;
    	return getDocumentId().equals(compObj.getDocumentId());   
    }
    
    public int hashCode() {
    	return getDocumentId().hashCode();
    }

}

