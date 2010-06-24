package edu.iu.uis.hr.tk.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.client.NonDatabaseTimestampPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;

public class WorkAreaStatusInquirySearchCriteria extends AbstractSearchCriteria {
    private static final String END_TIMESTAMP = "endTimestamp";
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(ClockLog.class);
    }
    private static final Map CUSTOM_PROPERTY_DESCRIPTORS = new HashMap();
    static {
        CUSTOM_PROPERTY_DESCRIPTORS.put(END_TIMESTAMP, new NonDatabaseTimestampPropertyDescriptor(END_TIMESTAMP,false, false));
    }
    private BigDecimal workArea;
    private Timestamp endTimestamp;

    public WorkAreaStatusInquirySearchCriteria() {
        setEndTimestamp((new TimedDate()).getTimestamp());
    }
    
    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        if (endTimestamp != null) {
            this.endTimestamp = endTimestamp;
        }
    }

    public BigDecimal getWorkArea() {
        return workArea;
    }

    public void setWorkArea(BigDecimal workArea) {
            this.workArea = workArea;
    }

    protected Map getPropertyDescriptorMap() {
        Map propertyDescriptorMap = super.getPropertyDescriptorMap();
        propertyDescriptorMap.putAll(CUSTOM_PROPERTY_DESCRIPTORS);
        return propertyDescriptorMap;
    }

    public void clear() {
        setWorkArea(null);
        setEndTimestamp((new TimedDate()).getTimestamp());
    }

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }
}