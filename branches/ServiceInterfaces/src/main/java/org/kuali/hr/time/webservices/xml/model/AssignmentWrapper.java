package org.kuali.hr.time.webservices.xml.model;

import java.util.List;

import org.kuali.hr.time.assignment.Assignment;

public class AssignmentWrapper {
	List<Assignment> assignments;

	public AssignmentWrapper() {

	}

	public AssignmentWrapper(List<Assignment> assignments) {

		this.assignments = assignments;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

}
