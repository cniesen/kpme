package edu.iu.uis.hr.tk.employee.service;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.tk.employee.dataaccess.EarningsAccrualDataAccess;
import edu.iu.uis.hr.tk.employee.dataaccess.LeaveAccrualDataAccess;
import edu.iu.uis.hr.tk.employee.entity.LeaveAccrual;

public class LeaveAccrualServiceImpl extends AbstractService implements LeaveAccrualService {

	private LeaveAccrualDataAccess leaveAccrualDataAccess;
	private EarningsAccrualDataAccess earningsAccrualDataAccess;

	public LeaveAccrual getLeaveAccrual(String universityId, String planType, Date accrualProcessDate) {
		return getLeaveAccrualDataAccess().getLeaveAccrual(universityId, planType, new Date(accrualProcessDate.getYear(), accrualProcessDate.getMonth(), accrualProcessDate.getDate()));
	}

	public List getEarningsAccrualPlans(String erncd, Date accrualProcessDate) {
		return getEarningsAccrualDataAccess().getEarningsAccrualPlans(erncd, accrualProcessDate);
	}

	public LeaveAccrualDataAccess getLeaveAccrualDataAccess() {
		return leaveAccrualDataAccess;
	}

	public void setLeaveAccrualDataAccess(LeaveAccrualDataAccess leaveAccrualDataAccess) {
		if (leaveAccrualDataAccess != null) {
			this.leaveAccrualDataAccess = leaveAccrualDataAccess;
		}
	}

	public EarningsAccrualDataAccess getEarningsAccrualDataAccess() {
		return earningsAccrualDataAccess;
	}

	public void setEarningsAccrualDataAccess(EarningsAccrualDataAccess earningsAccrualDataAccess) {
		this.earningsAccrualDataAccess = earningsAccrualDataAccess;
	}

}
