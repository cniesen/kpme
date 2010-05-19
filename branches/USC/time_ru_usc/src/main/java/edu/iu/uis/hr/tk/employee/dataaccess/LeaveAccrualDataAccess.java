package edu.iu.uis.hr.tk.employee.dataaccess;

import java.util.Date;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.tk.employee.entity.LeaveAccrual;

public interface LeaveAccrualDataAccess extends DataAccessOjb {

	public LeaveAccrual getLeaveAccrual(String universityId, String planType,  Date accrualProcessDate);

}