package org.kuali.hr.time.accrual.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.accrual.AccrualCategory;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.paytype.PayType;
import org.kuali.hr.time.salgroup.SalGroup;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class AccrualCategoryServiceRule {
 

	public boolean validateAccrualCategory(AccrualCategory accrualCategory) {
		boolean valid = false;

		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(AccrualCategory.class,
						accrualCategory.getLaAccrualCategoryId()) != null) {
			valid = true;
		}
		return valid;
	}

	public boolean validateAccrualCategoryObject(AccrualCategory accrualCategory)
			throws IllegalArgumentException {
		boolean result = false;
		if (accrualCategory != null) {
			if (accrualCategory.getLaAccrualCategoryId() != null) {
				if (!this.validateAccrualCategory(accrualCategory)) {
					throw new IllegalArgumentException(
							"Invalid accrualCategory ID :"
									+ accrualCategory.getLaAccrualCategoryId());
				}
			}
			result = true;
		}
		return result;
	}
}
