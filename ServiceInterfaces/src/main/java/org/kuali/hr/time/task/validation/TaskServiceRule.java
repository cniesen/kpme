package org.kuali.hr.time.task.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.time.task.Task;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class TaskServiceRule {
	public TaskServiceRule() {
	}

	public boolean validateWorkArea(Task task) {
		boolean valid = false;
		Criteria crit = new Criteria();
		crit.addEqualTo("workArea", task.getWorkArea());
		Query query = QueryFactory.newQuery(WorkArea.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
		}
		return valid;

	}

	public boolean validateTask(Task task) {
		boolean valid = false;
		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(Task.class, task.getTkTaskId()) != null) {
			valid = true;
		}
		return valid;
	}

	public boolean validateTkWorkAreaId(Task task) {
		boolean valid = false;
		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(WorkArea.class, task.getTkWorkAreaId()) != null) {
			valid = true;
		}
		return valid;
	}

	public boolean validateTaskObject(Task task)
			throws IllegalArgumentException {
		boolean result = false;
		if (task != null) {
			if (!this.validateWorkArea(task)) {
				throw new IllegalArgumentException("Invalid WorkArea :"
						+ task.getWorkArea());
			}

			if (!this.validateTkWorkAreaId(task)) {
				throw new IllegalArgumentException("Invalid WorkArea :"
						+ task.getWorkArea());
			}
			result = true;
		}

		return result;
	}

}
