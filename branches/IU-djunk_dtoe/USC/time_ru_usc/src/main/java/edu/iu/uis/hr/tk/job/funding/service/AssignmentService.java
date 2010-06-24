package edu.iu.uis.hr.tk.job.funding.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;

public interface AssignmentService extends edu.iu.uis.hr.job.funding.service.AssignmentService {
    public static final String PER_HOUR = "/hr";
    public static final String RECORD_NUMBER_LABEL = "Rcd#";
    
    public Map getAssignmentKeyMapFromCommaDelimitedList(String assignmentKeyCommaDelimitedList);
    
    public boolean hasAssignment(String universityId);
    
    public boolean hasSynchronousAssignment(String universityId);

    public boolean hasAsynchronousAssignment(String universityId);
    
    public boolean containsAsynchronousAssignment(List jobs);
    
    public boolean containsSynchronousAssignment(List jobs);
    
    public List getSynchronousAssignments(String universityId);
    
    public List getAsynchronousAssignments(String universityId);
    
    public List getAssignments(String universityId);
    
    public List getAssignments(String universityId, Date date);
    
    public List getDropdownAssignments(String universityId);
    	 
    public List getDropdownAssignments(String universityId, Date date, boolean synchronous);
    
    public List getDropdownSynchronousAssignments(String universityId);
    
    public String getAssignmentDescription(Assignment assignment);

    public Assignment getAssignment(String assignmentString, Date transactionRecordEffectiveDate);
    
    public boolean isSynchronousAssignment(Assignment assignment);
    
    //Hour distribution
    public List getDropdownHourDistributionAssignments(String universityId);
    
    public String appendMatchingAssignEffectiveDate(TimeBlock timeBlock, String universityId, String timeBlockAssignKey);
    
}