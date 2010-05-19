package edu.iu.uis.hr.tk.directory.entity;

import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;

public class RoleAuditSearchCriteria extends AbstractEffectiveDatedSearchCriteria {

    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(RoleAudit.class);
    }

    private String department;
    
    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setDepartment(edu.iu.uis.hr.Utility.getDefaultStringValue());
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null) {
            this.department = department;
        }
    }
}
