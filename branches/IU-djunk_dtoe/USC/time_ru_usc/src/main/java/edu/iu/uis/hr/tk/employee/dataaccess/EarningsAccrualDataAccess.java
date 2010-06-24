package edu.iu.uis.hr.tk.employee.dataaccess;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;

public interface EarningsAccrualDataAccess extends DataAccessOjb {

		public List getEarningsAccrualPlans(String erncd, Date accrualProcessDate);
	
}
