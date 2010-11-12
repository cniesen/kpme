package org.kuali.hr.time.webservices.xml.validation;

import org.kuali.hr.time.assignment.Assignment;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.hr.time.assignment.AssignmentAccount;

public class AssignmentAccountRule {
	public boolean validateAssignment(AssignmentAccount assignmentAccount) {
		boolean valid = false;

		Assignment assignment = KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(Assignment.class,
						assignmentAccount.getTkAssignmentId());
		if (assignment != null) {
			valid = true;
		}
		return valid;
	}

	public boolean validateAssignmentAccountObject(
			AssignmentAccount assignmentAccount)
			throws IllegalArgumentException {
		boolean result = false;
		if (assignmentAccount != null) {
			if (!this.validateAssignment(assignmentAccount)) {
				throw new IllegalArgumentException("Invalid Assignment :"
						+ assignmentAccount.getTkAssignmentId());
			}
			result = true;
		}
		return result;
	}
}
