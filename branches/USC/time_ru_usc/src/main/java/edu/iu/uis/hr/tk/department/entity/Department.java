package edu.iu.uis.hr.tk.department.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.department.entity.logic.MustHaveOneMemberInDepartmentWorkAreaRolesLogic;
import edu.iu.uis.hr.tk.department.entity.logic.ValidRoleUserNetworkId;
import edu.iu.uis.hr.tk.directory.entity.DepartmentRoleUser;
import edu.iu.uis.hr.tk.directory.entity.KioskAdminRoleUser;
import edu.iu.uis.hr.tk.directory.entity.RoleUser;
import edu.iu.uis.hr.tk.directory.entity.ViewOnlyRoleUser;

public class Department extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {
    private static final String PARENT_PROPERTY_NAME = "parent";
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(PARENT_PROPERTY_NAME);
    }
    private String department;
    private TypedPersistentMaintainedEntityList roleUsers;
    private TypedPersistentMaintainedEntityList viewOnlyRoleUsers;
    private TypedPersistentMaintainedEntityList kioskManagers;
    private TypedPersistentMaintainedEntityList workAreas;
    private TypedPersistentMaintainedEntityList kiosks;

    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        NonDatabasePropertyDescriptor departmentPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.DEPARTMENT, edu.iu.uis.hr.position.entity.Department.class);
        departmentPropertyDescriptor.setDisplayOnly(true);
        PROPERTY_DESCRIPTORS.put(departmentPropertyDescriptor.getName(), departmentPropertyDescriptor);
    }

    public Department() {
        setRoleUsers(new TypedPersistentMaintainedEntityList(DepartmentRoleUser.class, this));
        // TODO - moved to dept role
        setViewOnlyRoleUsers(new TypedPersistentMaintainedEntityList(ViewOnlyRoleUser.class, this));
        setKioskManagers(new TypedPersistentMaintainedEntityList(KioskAdminRoleUser.class, this));
        setWorkAreas(new TypedPersistentMaintainedEntityList(WorkArea.class));
        setKiosks(new TypedPersistentMaintainedEntityList(Kiosk.class));
    }

    protected List getOperationalLogics(SaveEvent saveEvent) {
        List operationalLogics = edu.iu.uis.hr.Utility.getDefaultListValue();
        operationalLogics.add(ValidRoleUserNetworkId.class);
        operationalLogics.add(MustHaveOneMemberInDepartmentWorkAreaRolesLogic.class);
        return operationalLogics;
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public Class getModeAuthorization() {
        return DepartmentModeAuthorization.class;
    }

    public List getNetworkIds(String roleName) {
        List networkIds = new ArrayList();
        Iterator allRoleUsers = getRoleUsers().iterator();
        while (allRoleUsers.hasNext()) {
            RoleUser roleUser = (RoleUser) allRoleUsers.next();
            if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(roleUser.getRoleName(), getDepartment()))) {
                networkIds.add(roleUser.getNetworkId());
            }
        }
        return networkIds;
    }

    public List getViewOnlyNetworkIds(String roleName) {
        List networkIds = new ArrayList();
        Iterator allViewOnly = getViewOnlyRoleUsers().iterator();
        while (allViewOnly.hasNext()) {
            RoleUser roleUser = (RoleUser) allViewOnly.next();
            if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(roleUser.getRoleName(), getDepartment()))) {
                networkIds.add(roleUser.getNetworkId());
            }
        }
        return networkIds;    
    }
        
    public List getKioskManagerNetworkIds(String roleName) {
        List networkIds = new ArrayList();
        Iterator allKioskManagers = getKioskManagers().iterator();
        while (allKioskManagers.hasNext()) {
            RoleUser roleUser = (RoleUser) allKioskManagers.next();
            if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(roleUser.getRoleName(), getDepartment()))) {
                networkIds.add(roleUser.getNetworkId());
            }
        }
        return networkIds;
    }

    public List getKioskNames(String roleName) {
        List kioskNames = new ArrayList();
        Iterator kiosks = getKiosks().iterator();
        while (kiosks.hasNext()) {
            Kiosk kiosk = (Kiosk) kiosks.next();
            // TODO : not clear yet ?
            if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(edu.iu.uis.hr.tk.directory.Utility.KIOSK_ROLE, getDepartment()))) {
                if (kiosk.isChecked()) {
                    kioskNames.add(kiosk.getKioskName());
                }
            } else {
                kioskNames.add(kiosk.getKioskName());

            }
        }
        return kioskNames;
    }

    public TypedPersistentMaintainedEntityList getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(TypedPersistentMaintainedEntityList roleUsers) {
        if (roleUsers != null) {
            this.roleUsers = roleUsers;
        }
    }
    
    public TypedPersistentMaintainedEntityList getViewOnlyRoleUsers() {
        return viewOnlyRoleUsers;
    }

    public void setViewOnlyRoleUsers(TypedPersistentMaintainedEntityList viewOnlyRoleUsers) {
        if (viewOnlyRoleUsers != null) {
            this.viewOnlyRoleUsers = viewOnlyRoleUsers;    
        }
    } 

    public TypedPersistentMaintainedEntityList getWorkAreas() {
        return workAreas;
    }

    public void setWorkAreas(TypedPersistentMaintainedEntityList workAreas) {
        if (workAreas != null) {
            this.workAreas = workAreas;
        }
    }

    public WorkArea getWorkArea(int index) {
        return (WorkArea) ((TypedPersistentMaintainedEntityList) getWorkAreas()).get(index);
    }

    public void setWorkArea(int index, WorkArea workArea) {
        ((TypedPersistentMaintainedEntityList) getWorkAreas()).set(index, workArea);
    }

    public RoleUser getRoleUser(int index) {
        return (RoleUser) ((TypedPersistentMaintainedEntityList) getRoleUsers()).get(index);
    }

    public void setRoleUser(int index, RoleUser roleUser) {
        ((TypedPersistentMaintainedEntityList) getRoleUsers()).add(index, roleUser);
    }

    public RoleUser getViewOnlyRoleUser(int index) {
        return (RoleUser) ((TypedPersistentMaintainedEntityList) getViewOnlyRoleUsers()).get(index);
    }

    public void setViewOnlyRoleUser(int index, RoleUser roleUser) {   	
        ((TypedPersistentMaintainedEntityList) getViewOnlyRoleUsers()).add(index, roleUser);
    }    
    
    public TypedPersistentMaintainedEntityList getKiosks() {
        return kiosks;
    }

    public void setKiosks(TypedPersistentMaintainedEntityList kiosks) {
        if (kiosks != null) {
            this.kiosks = kiosks;
        }
    }

    public Kiosk getKiosk(int index) {
        return (Kiosk) ((TypedPersistentMaintainedEntityList) getKiosks()).get(index);
    }

    public void setKiosk(int index, Kiosk kiosk) {
        ((TypedPersistentMaintainedEntityList) getKiosks()).set(index, kiosk);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null) {
            this.department = department;
        }
    }

    public List getRoleUsersNetworkIds() {
        List networkIds = new ArrayList();
        Iterator i = getRoleUsers().iterator();
        while (i.hasNext()) {
            networkIds.add(((RoleUser) i.next()).getNetworkId());
        }
        return networkIds;
    }

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public TypedPersistentMaintainedEntityList getKioskManagers() {
        return kioskManagers;
    }

    public void setKioskManagers(TypedPersistentMaintainedEntityList kioskManagers) {
        if (kioskManagers != null) {
            this.kioskManagers = kioskManagers;
        }
    }

    public RoleUser getKioskManager(int index) {
        return (RoleUser) ((TypedPersistentMaintainedEntityList) getKioskManagers()).get(index);
    }

    public void setKioskManager(int index, RoleUser roleUser) {
        ((TypedPersistentMaintainedEntityList) getKioskManagers()).add(index, roleUser);
    }

}
