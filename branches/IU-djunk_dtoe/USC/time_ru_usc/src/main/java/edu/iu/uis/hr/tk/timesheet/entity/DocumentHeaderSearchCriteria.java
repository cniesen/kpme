package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;

public class DocumentHeaderSearchCriteria extends AbstractEffectiveDatedSearchCriteria {

    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(DocumentHeader.class);
    }
	
    private String documentId;
    private String universityId;
    private Date payEndDate;

	protected Set getPersistentDatabaseEntityClasses() {
		return PERSISTENT_DATABASE_ENTITY_CLASSES;
	}

	public void clear() {
		// TODO Auto-generated method stub
	}
	
    public boolean isEffectiveDated(){
        return false;
    }
    
    
	public Date getPayEndDate() {
		return payEndDate;
	}

	public void setPayEndDate(Date payEndDate) {
		this.payEndDate = payEndDate;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        if (documentId != null) {
            this.documentId = documentId.trim();
        }
    }

}
