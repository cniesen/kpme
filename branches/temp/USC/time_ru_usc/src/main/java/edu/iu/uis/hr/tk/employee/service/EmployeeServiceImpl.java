package edu.iu.uis.hr.tk.employee.service;

import java.util.List;

import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.tk.employee.dataaccess.EmployeeDataAccess;
import edu.iu.uis.hr.tk.employee.entity.Employee;

public class EmployeeServiceImpl extends AbstractService implements EmployeeService {

    private EmployeeDataAccess employeeDataAccess;

    public Employee getEmployee(String universityId) {
        return getEmployeeDataAccess().getEmployee(universityId);
    }

    public List getActiveEmployees(PayCalendar payCalendar) {
        return getEmployeeDataAccess().getActiveEmployees(payCalendar);
    }

    public EmployeeDataAccess getEmployeeDataAccess() {
        return employeeDataAccess;
    }

    public void setEmployeeDataAccess(EmployeeDataAccess employeeDataAccess) {
        if (employeeDataAccess != null) {
            this.employeeDataAccess = employeeDataAccess;
        }
    }
}