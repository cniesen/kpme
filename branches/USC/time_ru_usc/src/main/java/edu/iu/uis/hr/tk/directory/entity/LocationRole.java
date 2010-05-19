package edu.iu.uis.hr.tk.directory.entity;

import java.util.HashMap;
import java.util.Map;

import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.position.entity.Location;

public class LocationRole extends UserRole {
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        PROPERTY_DESCRIPTORS.put(edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD, edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_NON_DATABASE_STRING_PROPERTY_DESCRIPTOR);
        PROPERTY_DESCRIPTORS.put(FieldNames.LOCATION, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.LOCATION, Location.class));
    }
    private String location;

    public LocationRole() {
        super();
    }

    public LocationRole(String roleName, String location) {
        super(roleName);
        setLocation(location);
    }

    public Class getModeAuthorization() {
        return LocationRoleModeAuthorization.class;
    }

    public String getDropdownName(String fieldName) {
        if (edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD.equals(fieldName)) {
            return edu.iu.uis.hr.tk.directory.Utility.LOCATION_ROLE_NAME_DROPDOWN;
        }
        return super.getDropdownName(fieldName);
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public Object getRoleData() {
        return location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location != null) {
            this.location = location;
        }
    }
}