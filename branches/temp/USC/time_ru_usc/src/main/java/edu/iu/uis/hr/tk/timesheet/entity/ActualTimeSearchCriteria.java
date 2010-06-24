package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;
import edu.iu.uis.hr.tk.department.entity.logic.ActualTimeSearchCriteriaRequiredFieldsLogic;

public class ActualTimeSearchCriteria extends AbstractEffectiveDatedSearchCriteria {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(ActualTime.class);
    }
    
    private String documentId;
    private String universityId;

    @Override
    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setDocumentId(Utility.getDefaultStringValue());

    }

    /**
     * @return the documentId
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * @param the documentId to set
     */
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    /**
     * @return the universityId
     */
    public String getUniversityId() {
        return universityId;
    }

    /**
     * @param the universityId to set
     */
    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public boolean isEffectiveDated(){
        return false;
    }
    
    public Class getRequiredFieldsLogic() {
        return ActualTimeSearchCriteriaRequiredFieldsLogic.class;
    }
}
