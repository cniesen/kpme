package org.kuali.hr.time.webservices.xml.model;

import java.util.List;

import org.kuali.hr.time.assignment.AssignmentAccount;

public class AssignmentAccountWrapper {
	private List<AssignmentAccount> assignmentAccounts;

	public AssignmentAccountWrapper() {
		 
	}

	public AssignmentAccountWrapper(List<AssignmentAccount> assignmentAccounts) {
		this.assignmentAccounts = assignmentAccounts;
	}

	public List<AssignmentAccount> getAssignmentAccounts() {
		return assignmentAccounts;
	}

	public void setAssignmentAccounts(List<AssignmentAccount> assignmentAccounts) {
		this.assignmentAccounts = assignmentAccounts;
	}
	
}
