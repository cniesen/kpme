package org.kuali.hr.time.webservices.xml.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.time.accrual.AccrualCategory;
import org.kuali.hr.time.accrual.TimeOffAccrual;


public class TimeOffAccrualRule {

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

	public boolean validateTimeOffAccrualObject(TimeOffAccrual timeOffAccrual)
			throws IllegalArgumentException {
		boolean result = false;
		if (timeOffAccrual != null) {
			if (!this.validateAccrualCategory(timeOffAccrual)) {
				throw new IllegalArgumentException("Invalid accrualCategory :"
						+ timeOffAccrual.getAccrualCategory());
			}
			result = true;
		}
		return result;
	}

}
