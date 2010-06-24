package edu.iu.uis.hr.tk.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractSearchCriteria;
import edu.iu.uis.hr.tk.client.NotifySupervisorSearchCriteriaModeAuthorization;

public class NotifySupervisorSearchCriteria extends AbstractSearchCriteria {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        // TODO: is anything needed here?
    }
    
    private static final Map CUSTOM_PROPERTY_DESCRIPTORS = new HashMap();
    static {
        // TODO:  what is the length of this field?
        CUSTOM_PROPERTY_DESCRIPTORS.put("assignment", new NonDatabaseStringPropertyDescriptor("assignment",false, false,50));
    }
    
    private String assignment;
    
    public Class getModeAuthorization() {
        return NotifySupervisorSearchCriteriaModeAuthorization.class;
    }
    
    public void clear() {
        setAssignment(edu.iu.uis.hr.Utility.getDefaultStringValue());
    }
    
    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        if (assignment != null) {
            this.assignment = assignment;
        }
    }

    protected Map getPropertyDescriptorMap() {
        Map propertyDescriptorMap = super.getPropertyDescriptorMap();
        propertyDescriptorMap.putAll(CUSTOM_PROPERTY_DESCRIPTORS);
        return propertyDescriptorMap;
    }
    
    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }
}