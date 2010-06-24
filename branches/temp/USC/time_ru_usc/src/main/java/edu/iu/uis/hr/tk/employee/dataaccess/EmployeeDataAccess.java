package edu.iu.uis.hr.tk.employee.dataaccess;

import java.util.List;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.employee.entity.Employee;

public interface EmployeeDataAccess extends DataAccessOjb {

    public Employee getEmployee(String universityId);

    public List getActiveEmployees(PayCalendar payCalendar);

}
