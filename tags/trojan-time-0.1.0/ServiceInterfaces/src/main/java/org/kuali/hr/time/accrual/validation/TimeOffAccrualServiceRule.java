package org.kuali.hr.time.accrual.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.time.accrual.AccrualCategory;
import org.kuali.hr.time.accrual.TimeOffAccrual;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class TimeOffAccrualServiceRule {

	public boolean validateAccrualCategory(TimeOffAccrual timeOffAccrual) {
		boolean valid = false;
		Criteria crit = new Criteria();
		crit.addEqualTo("accrualCategory", timeOffAccrual.getAccrualCategory());
		Query query = QueryFactory.newQuery(AccrualCategory.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
		}
		return valid;
	}

	public boolean validateTimeOffAccrual(TimeOffAccrual timeOffAccrual) {
		boolean valid = false;

		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(TimeOffAccrual.class,
						timeOffAccrual.getLaAccrualId()) != null) {
			valid = true;
		}
		return valid;
	}

	public boolean validateTimeOffAccrualObject(TimeOffAccrual timeOffAccrual)
			throws IllegalArgumentException {
		boolean result = false;
		if (timeOffAccrual != null) {
			if (!this.validateAccrualCategory(timeOffAccrual)) {
				throw new IllegalArgumentException("Invalid accrualCategory :"
						+ timeOffAccrual.getAccrualCategory());
			}
			if (timeOffAccrual.getLaAccrualId() != null) {
				if (!this.validateTimeOffAccrual(timeOffAccrual)) {
					throw new IllegalArgumentException(
							"Invalid TimeOffCategory Id:"
									+ timeOffAccrual.getLaAccrualId());
				}
			}
			result = true;
		}
		return result;
	}

}
