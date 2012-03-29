package edu.iu.hr.time.accrual;
import java.util.ArrayList;
import java.util.List;

import org.kuali.hr.time.accrual.web.TimeOffAccrualActionForm;


public class IUTimeOffAccrualActionForm extends TimeOffAccrualActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AccrualRate> accrualRates = new ArrayList<AccrualRate>();

	public List<AccrualRate> getAccrualRates() {
		return accrualRates;
	}

	public void setAccrualRates(List<AccrualRate> accrualRates) {
		this.accrualRates = accrualRates;
	}


}
