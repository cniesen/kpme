package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;

public class DocumentHeaderSnapshotSearchCriteria extends AbstractEffectiveDatedSearchCriteria {

    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(DocumentHeaderSnapshot.class);
    }
	
    // need to put in our search fields here, with getters/setters
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
}
