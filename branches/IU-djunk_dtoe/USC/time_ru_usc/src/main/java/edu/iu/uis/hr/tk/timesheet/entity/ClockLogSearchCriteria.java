package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ClockLogSearchCriteriaRequiredFieldsLogic;


public class ClockLogSearchCriteria extends AbstractEffectiveDatedSearchCriteria  {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(ClockLog.class);
    }

    private String universityId;

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setUniversityId(edu.iu.uis.hr.Utility.getDefaultStringValue());
    }
    
    public boolean isEffectiveDated(){
        return false;
    }    

    @Override
	public Class getRequiredFieldsLogic() {
		return ClockLogSearchCriteriaRequiredFieldsLogic.class;
	}

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        if (universityId != null) {
            this.universityId = universityId;
        }
    }

}