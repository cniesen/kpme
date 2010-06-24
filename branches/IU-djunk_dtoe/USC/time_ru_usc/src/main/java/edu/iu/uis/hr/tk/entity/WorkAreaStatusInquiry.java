package edu.iu.uis.hr.tk.entity;

import java.util.HashMap;
import java.util.Map;

import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractNonDatabaseEntity;
import edu.iu.uis.hr.entity.TransientNonDatabaseEntity;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;

public class WorkAreaStatusInquiry extends AbstractNonDatabaseEntity implements TransientNonDatabaseEntity {
	
	private static final String NAME_PROPERTY_DESCRIPTOR_FIELD = "name";
	private static final int NAME_PROPERTY_DESCRIPTOR_LENGTH = 50;
	
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        NonDatabaseStringPropertyDescriptor namePropertyDescriptor = new NonDatabaseStringPropertyDescriptor(NAME_PROPERTY_DESCRIPTOR_FIELD, NAME_PROPERTY_DESCRIPTOR_LENGTH);
        namePropertyDescriptor.setDisplayOnly(true);
        PROPERTY_DESCRIPTORS.put(NAME_PROPERTY_DESCRIPTOR_FIELD, namePropertyDescriptor);
    }

    private String name;
    private ClockLog clockLog;
    
    public WorkAreaStatusInquiry() {
        super();
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public ClockLog getClockLog() {
        return clockLog;
    }

    public void setClockLog(ClockLog clockLog) {
        if (clockLog != null) {
            this.clockLog = clockLog;
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
}
