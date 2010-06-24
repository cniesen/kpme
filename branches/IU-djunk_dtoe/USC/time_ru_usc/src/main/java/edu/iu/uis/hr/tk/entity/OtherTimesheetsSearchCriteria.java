package edu.iu.uis.hr.tk.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractSearchCriteria;
import edu.iu.uis.hr.tk.client.OtherTimesheetsSearchCriteriaModeAuthorization;

public class OtherTimesheetsSearchCriteria extends AbstractSearchCriteria {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        // TODO: is anything needed here?
    }
    
    private static final Map CUSTOM_PROPERTY_DESCRIPTORS = new HashMap();
    static {
        CUSTOM_PROPERTY_DESCRIPTORS.put("payPeriod", new NonDatabaseStringPropertyDescriptor("payPeriod",false, false,25));
    }
    
    private String payPeriod;
    
    public Class getModeAuthorization() {
        return OtherTimesheetsSearchCriteriaModeAuthorization.class;
    }
    
    public void clear() {
        setPayPeriod(edu.iu.uis.hr.Utility.getDefaultStringValue());
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
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