package edu.iu.uis.hr.tk.directory.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.client.NonDatabaseBooleanPropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.person.entity.Person;
import edu.iu.uis.hr.tk.directory.entity.logic.RoleMustExistLogic;
import edu.iu.uis.hr.tk.directory.entity.logic.RoleMustHaveOneMemberLogic;

public class UserRoles extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {
    private static final Logger LOG = Logger.getLogger(UserRoles.class);
    private static final String INTERFACE_MANAGER_FIELD = "interfaceManager";
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    private static int MAX_MAINTAINABLE_LIST_SIZE = 500;
    static {
        NonDatabasePropertyDescriptor universityIdPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.UNIVERSITY_ID, Person.class);
        universityIdPropertyDescriptor.setDisplayOnly(true);
        PROPERTY_DESCRIPTORS.put(universityIdPropertyDescriptor.getName(), universityIdPropertyDescriptor);
        PROPERTY_DESCRIPTORS.put(User.NETWORK_ID_FIELD, new NonDatabaseStringPropertyDescriptor(User.NETWORK_ID_FIELD, true, 20));
        PROPERTY_DESCRIPTORS.put(INTERFACE_MANAGER_FIELD, new NonDatabaseBooleanPropertyDescriptor(INTERFACE_MANAGER_FIELD));
        PROPERTY_DESCRIPTORS.put(User.NETWORK_ID_FIELD, User.NETWORK_ID_NON_DATABASE_STRING_PROPERTY_DESCRIPTOR);
        PROPERTY_DESCRIPTORS.put(FieldNames.NAME, User.NAME_NON_DATABASE_STRING_PROPERTY_DESCRIPTOR);
    }

    private String universityId;
    private String networkId;
    private String name;
    private boolean interfaceManager;
    private TypedPersistentMaintainedEntityList locationRoles;
    private TypedPersistentMaintainedEntityList departmentRoles;
    private TypedPersistentMaintainedEntityList viewOnlyRoles;    
    private TypedPersistentMaintainedEntityList workAreaRoles;

    public UserRoles() {
        super();
        setLocationRoles(new TypedPersistentMaintainedEntityList(LocationRole.class));
        setDepartmentRoles(new TypedPersistentMaintainedEntityList(DepartmentRole.class));
        setViewOnlyRoles(new TypedPersistentMaintainedEntityList(ViewOnlyRole.class));
        setWorkAreaRoles(new TypedPersistentMaintainedEntityList(WorkAreaRole.class));
        setParentModeInherited(false);
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public Class getModeAuthorization() {
        return UserRolesModeAuthorization.class;
    }
    
    protected List getOperationalLogics(SaveEvent saveEvent) {
        List logics = new ArrayList();
        logics.add(RoleMustHaveOneMemberLogic.class);
        logics.add(RoleMustExistLogic.class);
        return logics;
    }

    @SuppressWarnings("unchecked")
	public List getRoleNames() {
        List dataRelatedRoles = getUserRoles();
        List roleNames = new ArrayList();
        Iterator dataRelatedRoleItr = dataRelatedRoles.iterator();
        while (dataRelatedRoleItr.hasNext()) {
            roleNames.add(((UserRole) dataRelatedRoleItr.next()).getNestedRoleName());
        }
        if (isInterfaceManager()) {
            roleNames.add(edu.iu.uis.hr.tk.directory.Utility.INTERFACE_MANAGER_ROLE);
        }
        return roleNames;
    }

    @SuppressWarnings("unchecked")
	public List getUserRoles() {
        List dataRelatedRoles = new ArrayList(getLocationRoles());
        dataRelatedRoles.addAll(getDepartmentRoles());
        dataRelatedRoles.addAll(getViewOnlyRoles());
        dataRelatedRoles.addAll(getWorkAreaRoles());
        return dataRelatedRoles;
    }
    
    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        if (universityId != null) {
            this.universityId = universityId;
        }
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        if (networkId != null) {
            this.networkId = networkId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public boolean isInterfaceManager() {
        return interfaceManager;
    }

    public void setInterfaceManager(boolean interfaceManager) {
        this.interfaceManager = interfaceManager;
    }

    public TypedPersistentMaintainedEntityList getLocationRoles() {
        return locationRoles;
    }

    public void setLocationRoles(TypedPersistentMaintainedEntityList locationRoles) {
        if (locationRoles != null) {
            this.locationRoles = locationRoles;
        }
    }

    public LocationRole getLocationRole(int index) {
        return (LocationRole) getLocationRoles().get(index);
    }

    public void setLocationRole(int index, LocationRole locationRole) {
        if (locationRole != null) {
            getLocationRoles().add(index, locationRole);
        }
    }

    public TypedPersistentMaintainedEntityList getDepartmentRoles() {
        return departmentRoles;
    }

    public void setDepartmentRoles(TypedPersistentMaintainedEntityList departmentRoles) {
        if (departmentRoles != null) {
            this.departmentRoles = departmentRoles;
        }
    }
    
    public TypedPersistentMaintainedEntityList getViewOnlyRoles() {
        return viewOnlyRoles;
    }

    public void setViewOnlyRoles(TypedPersistentMaintainedEntityList viewOnlyRoles) {
        if (viewOnlyRoles != null) {
            this.viewOnlyRoles = viewOnlyRoles;
        }
    }

    public DepartmentRole getDepartmentRole(int index) {
        return (DepartmentRole) getDepartmentRoles().get(index);
    }

    public void setDepartmentRole(int index, DepartmentRole departmentRole) {
        if (departmentRole != null) {
            getDepartmentRoles().add(index, departmentRole);
        }
    }

    public ViewOnlyRole getViewOnlyRole(int index) {
        return (ViewOnlyRole) getViewOnlyRoles().get(index);
    }

    public void setViewOnlyRole(int index, ViewOnlyRole viewOnlyRole) {
        if (viewOnlyRole != null) {
            getViewOnlyRoles().add(index, viewOnlyRole);
        }
    }    
    
    public TypedPersistentMaintainedEntityList getWorkAreaRoles() {
        workAreaRoles.setTypedPersistentMaintainedEntityListSizeLimit(MAX_MAINTAINABLE_LIST_SIZE);
        return workAreaRoles;
    }

    public void setWorkAreaRoles(TypedPersistentMaintainedEntityList workAreaRoles) {
        if (workAreaRoles != null) {
            this.workAreaRoles = workAreaRoles;
        }
    }

    public WorkAreaRole getWorkAreaRole(int index) {
        return (WorkAreaRole) getWorkAreaRoles().get(index);
    }

    public void setWorkAreaRole(int index, WorkAreaRole workAreaRole) {
        if (workAreaRole != null) {
            getWorkAreaRoles().add(index, workAreaRole);
        }
    }
}