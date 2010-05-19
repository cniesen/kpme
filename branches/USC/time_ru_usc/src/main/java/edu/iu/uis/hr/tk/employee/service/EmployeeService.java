package edu.iu.uis.hr.tk.employee.service;

import java.util.List;

import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.service.Service;
import edu.iu.uis.hr.tk.employee.entity.Employee;

public interface EmployeeService extends Service {

    public Employee getEmployee(String universityId);

    public List getActiveEmployees(PayCalendar payCalendar);

}