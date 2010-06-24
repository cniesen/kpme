package edu.iu.uis.hr.tk.department.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.department.entity.logic.EmployeeTypeOvertimePreferenceWorkAreaLogic;
import edu.iu.uis.hr.tk.department.entity.logic.MustHaveOneMemberInDepartmentWorkAreaRolesLogic;
import edu.iu.uis.hr.tk.department.entity.logic.NoActiveAssignmentForTerminatedWorkAreaLogic;
import edu.iu.uis.hr.tk.department.entity.logic.ValidRoleUserNetworkId;
import edu.iu.uis.hr.tk.directory.entity.RoleUser;
import edu.iu.uis.hr.tk.directory.entity.WorkAreaRoleUser;

public class WorkArea extends edu.iu.uis.hr.job.funding.entity.WorkArea {

    private TypedPersistentMaintainedEntityList roleUsers;

    public WorkArea() {
        setRoleUsers(new TypedPersistentMaintainedEntityList(WorkAreaRoleUser.class, this));
    }

    protected List getOperationalLogics(SaveEvent saveEvent) {
        List operationalLogics = edu.iu.uis.hr.Utility.getDefaultListValue();
        operationalLogics.add(ValidRoleUserNetworkId.class);
        operationalLogics.add(MustHaveOneMemberInDepartmentWorkAreaRolesLogic.class);
        operationalLogics.add(NoActiveAssignmentForTerminatedWorkAreaLogic.class);
        operationalLogics.add(EmployeeTypeOvertimePreferenceWorkAreaLogic.class);        
        return operationalLogics;
    }
    
//    public Class getModeAuthorization() {
//        return WorkAreaModeAuthorization.class;
//    }

    public TypedPersistentMaintainedEntityList getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(TypedPersistentMaintainedEntityList roleUsers) {
        if (roleUsers != null) {
            this.roleUsers = roleUsers;
        }
    }

    public RoleUser getRoleUser(int index) {
        return (RoleUser) ((TypedPersistentMaintainedEntityList) getRoleUsers()).get(index);
    }

    public void setRoleUser(int index, RoleUser roleUser) {
        ((TypedPersistentMaintainedEntityList) getRoleUsers()).add(index, roleUser);
    }

    public List getNetworkIds(String roleName) {
        List networkIds = new ArrayList();
        Iterator allRoleUsers = getRoleUsers().iterator();
        while (allRoleUsers.hasNext()) {
            RoleUser roleUser = (RoleUser) allRoleUsers.next();
            if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(roleUser.getRoleName(), getWorkArea().toString()))) {
                networkIds.add(roleUser.getNetworkId());
            }
        }
        return networkIds;
    }

}