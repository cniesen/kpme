package edu.iu.uis.hr.tk.employee.service;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.service.Service;
import edu.iu.uis.hr.tk.employee.entity.LeaveAccrual;

public interface LeaveAccrualService extends Service {

	public LeaveAccrual getLeaveAccrual(String universityId, String planType, Date accrualProcessDate);
	
	public List getEarningsAccrualPlans(String erncd, Date accrualProcessDate);
	
}
