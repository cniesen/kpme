package org.kuali.hr.time;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class TimeCollectionRule extends PersistableBusinessObjectBase {
	
	private static final long serialVersionUID = 1L;
	
	private long timeCollectionRuleId;
	private String deptId;
	private Long workArea;
	private Date effDate;
	private String clockUserFl;
	private String hrsDistributionF;
	private String userPrincipalId;
	private Timestamp timeStamp;
	
	private Department department;	
	private WorkArea workAreaObj;
		
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public WorkArea getWorkAreaObj() {
		return workAreaObj;
	}

	public void setWorkAreaObj(WorkArea workAreaObj) {
		this.workAreaObj = workAreaObj;
	}

	public String getHrsDistributionF() {
		return hrsDistributionF;
	}

	public void setHrsDistributionF(String hrsDistributionF) {
		this.hrsDistributionF = hrsDistributionF;
	}

	public long getTimeCollectionRuleId() {
		return timeCollectionRuleId;
	}

	public void setTimeCollectionRuleId(long timeCollectionRuleId) {
		this.timeCollectionRuleId = timeCollectionRuleId;
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

	public void setWorkArea(Long workArea) {
		this.workArea = workArea;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public String getClockUserFl() {
		return clockUserFl;
	}

	public void setClockUserFl(String clockUserFl) {
		this.clockUserFl = clockUserFl;
	}

	public String getUserPrincipalId() {
		return userPrincipalId;
	}

	public void setUserPrincipalId(String userPrincipalId) {
		this.userPrincipalId = userPrincipalId;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	

	@Override
	protected LinkedHashMap<String,Object> toStringMapper() {
		LinkedHashMap<String, Object> toStringMap = new LinkedHashMap<String,Object>();

		toStringMap.put("timeCollectionRuleId", timeCollectionRuleId);
		toStringMap.put("department", department);
		toStringMap.put("deptId", deptId);
		toStringMap.put("workAreaObj", workAreaObj);
		toStringMap.put("workArea", workArea);
		toStringMap.put("effDate", effDate);
		toStringMap.put("clockUserFl", clockUserFl);
		toStringMap.put("hrsDistributionF", hrsDistributionF);
		toStringMap.put("userPrincipalId", userPrincipalId);
		toStringMap.put("timeStamp", timeStamp);		

		return toStringMap;
	}


}
