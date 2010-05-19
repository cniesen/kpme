package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;


public class DocumentLockSearchCriteria extends AbstractEffectiveDatedSearchCriteria  {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(DocumentLock.class);
    }

    private String documentId;

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setDocumentId(edu.iu.uis.hr.Utility.getDefaultStringValue());
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        if (documentId != null) {
            this.documentId = documentId;
        }
    }

    public boolean isEffectiveDated(){
        return false;
    }

}