package org.kuali.hr.job.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.paytype.PayType;
import org.kuali.hr.time.salgroup.SalGroup;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class JobServiceRule {
	public boolean validateDept(Job job) {
		boolean valid = false;
		// TODO: We may need a full DAO that handles bo lookups at some point,
		// but we can use the provided one:
		Criteria crit = new Criteria();
		crit.addEqualTo("dept", job.getDept());
		Query query = QueryFactory.newQuery(Department.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
		}
		return valid;
	}

	public boolean validatePayType(Job job) {
		boolean valid = false;
		Criteria crit = new Criteria();
		crit.addEqualTo("payType", job.getHrPayType());
		Query query = QueryFactory.newQuery(PayType.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
		}
		return valid;
	}

	public boolean validateSalGroup(Job job) {
		boolean valid = false;
		Criteria crit = new Criteria();
		crit.addEqualTo("tkSalGroup", job.getTkSalGroup());
		Query query = QueryFactory.newQuery(SalGroup.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
		}
		return valid;
	}

	public boolean validateJob(Job job) {
		boolean valid = false;

		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(Job.class, job.getHrJobId()) != null) {
			valid = true;
		}
		return valid;
	}

	public boolean validateJobObject(Job job) throws IllegalArgumentException {
		boolean result = false;
		if (job != null) {
			if (!this.validateDept(job)) {
				throw new IllegalArgumentException("Invalid Dept :"
						+ job.getDept());
			}
			if (!this.validatePayType(job)) {
				throw new IllegalArgumentException("Invalid PayType :"
						+ job.getHrPayType());
			}
			if (!this.validateSalGroup(job)) {
				throw new IllegalArgumentException("Invalid SalGroup :"
						+ job.getTkSalGroup());
			}
			if (job.getHrJobId() != null) {
				if (!this.validateJob(job)) {
					throw new IllegalArgumentException("Invalid Job Id:"
							+ job.getHrJobId());
				}

			}
			result = true;
		}
		return result;
	}
}
