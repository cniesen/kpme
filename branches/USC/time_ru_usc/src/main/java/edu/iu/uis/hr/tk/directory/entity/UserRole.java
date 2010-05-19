package edu.iu.uis.hr.tk.directory.entity;

import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

public abstract class UserRole extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {
    private String roleName;

    public UserRole() {
        super();
        setRoleName(edu.iu.uis.hr.Utility.getDefaultStringValue());
        setParentModeInherited(false);
    }

    public UserRole(String roleName) {
        super();
        setRoleName(roleName);
        setParentModeInherited(false);
    }

    public abstract Object getRoleData();

    public String getNestedRoleName() {
        String roleDataString = edu.iu.uis.hr.Utility.getDefaultStringValue();
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(getRoleData())) {
            roleDataString = getRoleData().toString();
        }
        return edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(getRoleName(), roleDataString);
    }

    public boolean inheritParentMode() {
        return false;
    }

    public boolean equals(Object o) {
        return (o instanceof UserRole) && ((UserRole) o).getRoleName().equals(getRoleName()) && ((UserRole) o).getRoleData().equals(getRoleData());
    }

    public int hashCode() {
        return new StringBuffer(getRoleName()).append(getRoleData().toString()).hashCode();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        if (roleName != null) {
            this.roleName = roleName;
        }
    }
}