package edu.iu.uis.hr.tk.job.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;

public class Job extends AbstractJob implements PersistentMaintainedEntity {
    private static final Logger LOG = Logger.getLogger(Job.class);

    public static final String HOURLY_EMPLOYEE_TYPE = "H";
    public static final String EXCEPTION_HOURLY_EMPLOYEE_TYPE = "E";
    public static final String EMPLOYEE_RECORD_TEXT ="Employee Record ";
    public static final BigDecimal MAX_EMPL_RCD = new BigDecimal(99);
    public static final String PRIMARY_JOB_INDICATOR = "P";
    private static final String DISPLAY_RECORD_NAME = "displayRecord";
    
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        PROPERTY_DESCRIPTORS.put(DISPLAY_RECORD_NAME, new NonDatabaseStringPropertyDescriptor(DISPLAY_RECORD_NAME, true, 50));
    }
    
    private String displayRecord;
    
    public Job() {
        super();
    }

    public Job(String universityId, BigDecimal employeeRecord, Date transactionRecordEffectiveDate) {
        this();
        setUniversityId(universityId);
        setEmployeeRecord(employeeRecord);
        setEffectiveDate(transactionRecordEffectiveDate);
    }

    public Collection getAssignments() {
        if (!(super.getAssignments() instanceof TypedPersistentMaintainedEntityList)) {
            setAssignments(new TypedPersistentMaintainedEntityList(Assignment.class, this, super.getAssignments()));
        }
        return super.getAssignments();
    }

    public void setAssignments(Collection assignments) {
        if (assignments instanceof TypedPersistentMaintainedEntityList) {
            super.setAssignments(assignments);
        }
        super.setAssignments(new TypedPersistentMaintainedEntityList(Assignment.class, this, assignments));
    }

    public Assignment getAssignment(int index) {
        return (Assignment)((TypedPersistentMaintainedEntityList)getAssignments()).get(index);
    }

    public void setAssignment(int index, Assignment assignment) {
        ((TypedPersistentMaintainedEntityList)getAssignments()).add(index, assignment);
    }

    public Assignment getAssignment(BigDecimal workArea, BigDecimal task) {
        Iterator assignmentsItr = getAssignments().iterator();
        while (assignmentsItr.hasNext()) {
            Assignment assignment = (Assignment)assignmentsItr.next();
            if (assignment.getWorkArea().equals(workArea) && ((Utility.hasValue(assignment.getTask()) && assignment.getTask().equals(task)) || (!Utility.hasValue(assignment.getTask()) && (!Utility.hasValue(assignment.getTask()) || task.equals(Assignment.DEFAULT_TASK))))) {
                return assignment;
            }
        }
        return null;
    }

    public boolean isHourly() {
        return HOURLY_EMPLOYEE_TYPE.equals(getEmployeeType());
    }

	public String getDisplayRecord() {
		String employeeRecord = "";
		try {
			employeeRecord = getEmployeeRecord().toString();
		} catch (RuntimeException e) { }
		return EMPLOYEE_RECORD_TEXT + employeeRecord;
	}

	public void setDisplayRecord(String displayRecord) {
		this.displayRecord = displayRecord;
	}
	
    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }
    
    public boolean isDisplayOnly() {
    	return true;
    }
}
