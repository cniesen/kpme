package org.kuali.hr.time.assignment.validation;

import org.kuali.hr.time.assignment.Assignment;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.hr.time.assignment.AssignmentAccount;

public class AssignmentAccountServiceRule {
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

	public boolean validateAssignmentAccount(AssignmentAccount assignmentAccount) {
		boolean valid = false;
		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(AssignmentAccount.class,
						assignmentAccount.getTkAssignAcctId()) != null) {
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
			if (assignmentAccount.getTkAssignAcctId() != null) {
				if (!this.validateAssignmentAccount(assignmentAccount)) {
					throw new IllegalArgumentException(
							"Invalid AssignmentAccount ID :"
									+ assignmentAccount.getTkAssignAcctId());
				}
			}
			result = true;
		}
		return result;
	}
}
