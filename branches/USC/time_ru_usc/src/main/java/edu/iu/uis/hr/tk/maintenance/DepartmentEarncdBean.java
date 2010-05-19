package edu.iu.uis.hr.tk.maintenance;

public class DepartmentEarncdBean {
	
	private String deptid;
	private String sal_admin_plan;
	private String location;
	private String erncd;
	private Boolean employee;
	private Boolean supervisor;
	private Boolean payrollprocessor;
	private Boolean active;
	
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getSal_admin_plan() {
		return sal_admin_plan;
	}
	public void setSal_admin_plan(String salAdminPlan) {
		sal_admin_plan = salAdminPlan;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getErncd() {
		return erncd;
	}
	public void setErncd(String erncd) {
		this.erncd = erncd;
	}
	public Boolean getEmployee() {
		return employee;
	}
	public void setEmployee(Boolean employee) {
		this.employee = employee;
	}
	public Boolean getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Boolean supervisor) {
		this.supervisor = supervisor;
	}
	public Boolean getPayrollprocessor() {
		return payrollprocessor;
	}
	public void setPayrollprocessor(Boolean payrollprocessor) {
		this.payrollprocessor = payrollprocessor;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}


}
