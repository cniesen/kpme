package edu.iu.uis.hr.tk.job.funding.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.job.entity.Job;

public class Assignment extends AbstractAssignment implements  PersistentMaintainedEntity  {
    private static final Logger LOG = Logger.getLogger(Assignment.class);
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add("job");
        LOGIC_EXEMPT_PROPERTY_NAMES.add("assignmentDescription");
    }
    
    public static final BigDecimal DEFAULT_TASK = new BigDecimal(0);
    public static final String ASSIGNMENT = "assignment";
    public static final String ASSIGNMENT_DESCRIPTION = "assignmentDescription";
    public static final NonDatabaseStringPropertyDescriptor ASSIGNMENT_PROPERTY_DESCRIPTOR = new NonDatabaseStringPropertyDescriptor(ASSIGNMENT, 100);
    public static final NonDatabaseStringPropertyDescriptor ASSIGNMENT_DESCRIPTION_PROPERTY_DESCRIPTOR = new NonDatabaseStringPropertyDescriptor(ASSIGNMENT_DESCRIPTION, 100);
    
    private String assignmentDescription;
    private Date endDate;
    
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        PROPERTY_DESCRIPTORS.put(ASSIGNMENT_DESCRIPTION, ASSIGNMENT_DESCRIPTION_PROPERTY_DESCRIPTOR);
    }
 
    
    public Assignment() {
        super();
    }
    
    public Assignment(BigDecimal employeeRecord, BigDecimal workArea, BigDecimal task) {
        this();
        setJob(new Job());
        getJob().setEmployeeRecord(employeeRecord);
        setWorkArea(workArea);
        setTask(task);
    }
    
    public Assignment(Map assignmentKeyMap) {
        this();
        setJob(new Job());
        getJob().setUniversityId((String)assignmentKeyMap.get(FieldNames.UNIVERSITY_ID));
        getJob().setEmployeeRecord((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD));
        setWorkArea((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA));
        setTask((BigDecimal)assignmentKeyMap.get(FieldNames.TASK));
    }
    
    public String getAssignmentKeyAsOptionKey(boolean synchronous) {
    	//Added effective date for matching purposes on the timesheet in the case that the effective date is different
    	//and a field that may affect the assignment display has changed, but none of the other fields have changed. 
        if(synchronous){
        	//Don't need the effdt on the clock assignment drop-down, this drop-down has only what is valid on that date.
        	return getJob().getUniversityId() + edu.iu.uis.hr.Utility.COMMA + getJob().getEmployeeRecord() + edu.iu.uis.hr.Utility.COMMA + getWorkArea() + edu.iu.uis.hr.Utility.COMMA + getTask();
        }else{
        	return getJob().getUniversityId() + edu.iu.uis.hr.Utility.COMMA + getJob().getEmployeeRecord() + edu.iu.uis.hr.Utility.COMMA + getWorkArea() + edu.iu.uis.hr.Utility.COMMA + getTask() + edu.iu.uis.hr.Utility.COMMA + this.getJob().getEffectiveDate();
        }
    }

    public void setTask(BigDecimal task) {
        if (!Utility.hasValue(task)) {
            task = DEFAULT_TASK;
        }
        super.setTask(task);
    }
    
    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public List getAuthorizationExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }    


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        if (endDate != null) {
            this.endDate = endDate;
        }
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        if (assignmentDescription != null) {
            this.assignmentDescription = assignmentDescription;
        }
    }
    
    // JIRA::TK-485 - Adding a new method for checking whether the assignment is still active or not
    public boolean isActive(Date date) {
        // If the end date is not null, then check whether the end date is before the holiday, in that
        // case the assignment is not active
        if (endDate!= null) {
            int decider = endDate.compareTo(date);
            if (decider < 0) {
                return false;
            }
            else { 
             return true;
            }
        }
        return true;
    }
    
    public boolean isDisplayOnly() {
    	return true;
    }

}
