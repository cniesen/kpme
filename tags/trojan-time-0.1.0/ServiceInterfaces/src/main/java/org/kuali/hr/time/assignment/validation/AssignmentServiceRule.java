package org.kuali.hr.time.assignment.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.task.Task;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class AssignmentServiceRule {

	public AssignmentServiceRule() {
	}

	public boolean validateWorkArea(Assignment assignment) {
		boolean valid = false;
		Criteria crit = new Criteria();
		crit.addEqualTo("workArea", assignment.getWorkArea());
		Query query = QueryFactory.newQuery(WorkArea.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
		}
		return valid;

	}

	public boolean validateTask(Assignment assignment) {
		boolean valid = false;

		Criteria crit = new Criteria();
		crit.addEqualTo("task", assignment.getTask());
		Query query = QueryFactory.newQuery(Task.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
		}
		return valid;
	}

	public boolean validateJob(Assignment assignment) {
		boolean valid = false;
		Criteria crit = new Criteria();
		crit.addEqualTo("jobNumber", assignment.getJobNumber());
		Query query = QueryFactory.newQuery(Job.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
		}
		return valid;
	}

	public boolean validateAssignment(Assignment assignment) {
		boolean valid = false;
		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(Assignment.class,
						assignment.getTkAssignmentId()) != null) {
			valid = true;
		}
		return valid;
	}

	// TODO fix this class
	public boolean validateEarnCode(Assignment assignment) {
		boolean valid = false;
		// LOG.debug("Validating EarnCode: " + assignment.getEarnCode()());
		// EarnCode earnCode = KNSServiceLocator.getBusinessObjectService()
		// .findBySinglePrimaryKey(EarnCode.class, assignment.getEarnCodeId());
		// if (earnCode != null) {
		// valid = true;
		// LOG.debug("found earnCode.");
		// } else {
		// this.putFieldError("earnCodeId", "error.existence", "earnCodeId '"
		// + assignment.getEarnCodeId()+ "'");
		// }
		return valid;
	}

	public boolean validateAssignmentObject(Assignment assignment)
			throws IllegalArgumentException {
		boolean result = false;
		if (assignment != null) {

			if (!this.validateWorkArea(assignment)) {
				throw new IllegalArgumentException("Invalid WorkArea :"
						+ assignment.getWorkArea());
			}
			if (!this.validateJob(assignment)) {
				throw new IllegalArgumentException("Invalid Job :"
						+ assignment.getJob());
			}
			if (!this.validateTask(assignment)) {
				throw new IllegalArgumentException("Invalid Task :"
						+ assignment.getTask());
			}
			if (assignment.getTkAssignmentId() != null) {
				if (!this.validateAssignment(assignment)) {
					throw new IllegalArgumentException("Invalid Assignment Id:"
							+ assignment.getTkAssignmentId());
				}
			}
			result = true;
		}
		return result;
	}

}
