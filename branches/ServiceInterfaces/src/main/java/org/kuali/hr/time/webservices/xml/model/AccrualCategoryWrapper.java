package org.kuali.hr.time.webservices.xml.model;

import java.util.List;

import org.kuali.hr.time.accrual.AccrualCategory;

public class AccrualCategoryWrapper {
	List<AccrualCategory> accrualCategories;

	public AccrualCategoryWrapper(List<AccrualCategory> accrualCategories) {

		this.accrualCategories = accrualCategories;
	}

	public AccrualCategoryWrapper() {

	}

	public List<AccrualCategory> getAccrualCategories() {
		return accrualCategories;
	}

	public void setAccrualCategories(List<AccrualCategory> accrualCategories) {
		this.accrualCategories = accrualCategories;
	}

}
