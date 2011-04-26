package org.kuali.hr.time.assignment.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.assignment.AssignmentDescriptionKey;
import org.kuali.hr.time.timesheet.TimesheetDocument;

public interface AssignmentService {
	/**
	 * Fetches a list of Assignments for a given principal Id as of a particular date
	 * @param principalId
	 * @param asOfDate
	 * @return
	 */
    public List<Assignment> getAssignments(String principalId, Date asOfDate);
    /**
     * Reverse lookup of an assignment based on the assignment key and the document
     * @param timesheetDocument
     * @param assignmentKey
     * @return
     */
    public Assignment getAssignment(TimesheetDocument timesheetDocument, String assignmentKey);
    /**
     * Reverse lookup of an assignment based on the assignment id
     * @param tkAssignmentId
     * @return
     */
    public Assignment getAssignment(String tkAssignmentId);
    /**
     * Get Assignment Description key based off of description
     * @param assignmentDesc
     * @return
     */
    public AssignmentDescriptionKey getAssignmentDescriptionKey(String assignmentDesc);
    /**
     * Get all assignment descriptions for a document
     * @param td
     * @param clockOnlyAssignments
     * @return
     */
    public Map<String,String> getAssignmentDescriptions(TimesheetDocument td, boolean clockOnlyAssignments);
    /**
     * Get all assigment descriptions for an assignment
     * @param assignment
     * @return
     */
	public Map<String,String> getAssignmentDescriptions(Assignment assignment);
	/**
	 * Get all active assignments for a work area
	 * @param workArea
	 * @param asOfDate
	 * @return
	 */
	public List<Assignment> getActiveAssignmentsForWorkArea(String workArea, Date asOfDate);
	
	public List<Assignment> getActiveAssignments(Date asOfDate);
}
