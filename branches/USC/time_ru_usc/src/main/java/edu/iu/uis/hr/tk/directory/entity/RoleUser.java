package edu.iu.uis.hr.tk.directory.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.person.entity.Person;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.WorkArea;

public abstract class RoleUser extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {
    private static final String PARENT_FIELD_NAME = "parent";
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(PARENT_FIELD_NAME);
    }
    private static final List AUTHORIZATION_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        AUTHORIZATION_EXEMPT_PROPERTY_NAMES.add(PARENT_FIELD_NAME);
    }

    private PersistentMaintainedEntity parent;
    private String universityId;
    private String networkId;
    private String name;
    private String roleName;

    public static final String ROLE_NAME_FIELD_NAME = "roleName";
    public static final int MAX_NAME_LENGTH = 50; //max number of characters to display for GDSPerson.getDisplayName()
    
    protected static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        NonDatabasePropertyDescriptor universityIdPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.UNIVERSITY_ID, Person.class);
        universityIdPropertyDescriptor.setDisplayOnly(false);
        PROPERTY_DESCRIPTORS.put(universityIdPropertyDescriptor.getName(), universityIdPropertyDescriptor);
        PROPERTY_DESCRIPTORS.put(User.NETWORK_ID_FIELD, User.NETWORK_ID_NON_DATABASE_STRING_PROPERTY_DESCRIPTOR);
        PROPERTY_DESCRIPTORS.put(FieldNames.NAME, User.NAME_NON_DATABASE_STRING_PROPERTY_DESCRIPTOR);
        PROPERTY_DESCRIPTORS.put(RoleUser.ROLE_NAME_FIELD_NAME, new NonDatabaseStringPropertyDescriptor(RoleUser.ROLE_NAME_FIELD_NAME, false, 50));
    }
    
    public static final String UNIVERSITY_ID_JAVASCRIPT_EVENT_HANDLER = "retrieveNetworkId('/UserRolesAjaxLookup.do', this)";
    private static final Map JAVASCRIPT_EVENT_HANDLERS = new HashMap();
    static {
        JAVASCRIPT_EVENT_HANDLERS.put(FieldNames.UNIVERSITY_ID, UNIVERSITY_ID_JAVASCRIPT_EVENT_HANDLER);
    }
    

    public RoleUser() {
        super();
    }

    public RoleUser(String name, String networkId, String universityId, String roleName) {
        this();
        setName(name);
        setUniversityId(universityId);
        setNetworkId(networkId);
        setRoleName(roleName);
    }

    public Class getModeAuthorization() {
        return RoleUserModeAuthorization.class;
    }

    public abstract String getDropdownName(String fieldName);
    // TODO - derived objects implement this
    /*
    {
        if (edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD.equals(fieldName)) {
            if (getParent() instanceof Department) {
                return edu.iu.uis.hr.tk.directory.Utility.DEPARTMENT_ROLE_NAME_DROPDOWN;
            } else if (getParent() instanceof WorkArea) {
                return edu.iu.uis.hr.tk.directory.Utility.WORK_AREA_ROLE_NAME_DROPDOWN;
            }
        }
        return super.getDropdownName(fieldName);
    }
    */

    public List getAuthorizationExemptPropertyNames() {
        return AUTHORIZATION_EXEMPT_PROPERTY_NAMES;
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public PersistentMaintainedEntity getParent() {
        return parent;
    }

    public void setParent(PersistentMaintainedEntity parent) {
        if (parent != null) {
            this.parent = parent;
        }
    }

    public void setDepartment(Department department) {
        setParent(department);
    }

    public void setWorkArea(WorkArea workArea) {
        setParent(workArea);
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
            this.name = name.substring(0,name.length()>=MAX_NAME_LENGTH?MAX_NAME_LENGTH-1:name.length());
        }
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        if (roleName != null) {
            this.roleName = roleName;
        }
    }

    public String getLookupFieldsToReturn(String fieldName) {
        return new StringBuffer(FieldNames.UNIVERSITY_ID).append(edu.iu.uis.hr.Utility.COMMA).append(FieldNames.NAME).append(edu.iu.uis.hr.Utility.COMMA).append(User.NETWORK_ID_FIELD).toString();
    }

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }
    
    public Map getJavascriptEventHandlers() {
        return JAVASCRIPT_EVENT_HANDLERS;
    }    

    /**
     * need a deep comparison for checking if objects where changes
     */
    
    public boolean equals(Object o) {
        if (!(getName().equals(((RoleUser)o).getName()))) {
            return false;
        } else if (!(getUniversityId().equals(((RoleUser)o).getUniversityId()))) {
            return false;
        } else if (!(getNetworkId().equals(((RoleUser)o).getNetworkId()))) {
            return false;
        } else if (!(getRoleName().equals(((RoleUser)o).getRoleName()))) {
            return false;
        }
        return true;
    }

}