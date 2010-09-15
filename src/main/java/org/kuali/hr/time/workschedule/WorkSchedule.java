package org.kuali.hr.time.workschedule;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.paycalendar.PayCalendarDates;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class WorkSchedule extends PersistableBusinessObjectBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long workScheduleId;
	
	private String workScheduleDesc;
	private Date effectiveDate;
	private String deptId;	
	private Long workArea;	
	private Long principalId;
	private boolean active;
	
	private WorkArea workAreaObj;
	private Department department;
	
	
	private List<WorkScheduleEntry> workScheduleEntries = new  LinkedList<WorkScheduleEntry>();
	 
	

	public List<WorkScheduleEntry> getWorkScheduleEntries() {
		return workScheduleEntries;
	}


	public void setWorkScheduleEntries(List<WorkScheduleEntry> workScheduleEntries) {
		this.workScheduleEntries = workScheduleEntries;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Long getWorkScheduleId() {
		return workScheduleId;
	}


	public void setHrWorkScheduleId(Long workScheduleId) {
		this.workScheduleId = workScheduleId;
	}


	public String getWorkScheduleDesc() {
		return workScheduleDesc;
	}


	public WorkArea getWorkAreaObj() {
		return workAreaObj;
	}


	public void setWorkAreaObj(WorkArea workAreaObj) {
		this.workAreaObj = workAreaObj;
	}


	public void setWorkScheduleId(Long workScheduleId) {
		this.workScheduleId = workScheduleId;
	}


	public void setWorkArea(Long workArea) {
		this.workArea = workArea;
	}


	public void setWorkScheduleDesc(String workScheduleDesc) {
		this.workScheduleDesc = workScheduleDesc;
	}


	public Date getEffectiveDate() {
		return effectiveDate;
	}


	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	public Long getWorkArea() {
		return workArea;
	}


	public Long getPrincipalId() {
		return principalId;
	}


	public void setPrincipalId(Long principalId) {
		this.principalId = principalId;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	

	
	@SuppressWarnings("unchecked")
	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap<String, Object> toStringMap = new LinkedHashMap<String, Object>();
		
		toStringMap.put("workScheduleId", workScheduleId);
		toStringMap.put("workScheduleDesc", workScheduleDesc);
		toStringMap.put("effectiveDate", effectiveDate);
		toStringMap.put("deptId", deptId);
		toStringMap.put("workArea", workArea);
		toStringMap.put("active", active);
		toStringMap.put("workAreaObj", workAreaObj); 	
		 
		return toStringMap;
	}


}
