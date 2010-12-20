package org.kuali.hr.time.roles;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;

import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class TkRole extends PersistableBusinessObjectBase{
	
	private Long tkRolesId;
	private String principalId;
	private String roleName;
	private String userPrincipalId;
	private Long tkAssignmentId;
	private Long workArea;
	private String department;
	private Date effectiveDate;
	private Timestamp timestamp;
	private boolean active;
	
	private Person principal;
	private WorkArea workAreaObj;
	private Assignment assignmentObj;
	
	
	
	
	public WorkArea getWorkAreaObj() {
		return workAreaObj;
	}
	public void setWorkAreaObj(WorkArea workAreaObj) {
		this.workAreaObj = workAreaObj;
	}
	public Assignment getAssignmentObj() {
		return assignmentObj;
	}
	public void setAssignmentObj(Assignment assignmentObj) {
		this.assignmentObj = assignmentObj;
	}
	public Person getPrincipal() {
		return principal;
	}
	public void setPrincipal(Person principal) {
		this.principal = principal;
	}
	public Long getTkRolesId() {
		return tkRolesId;
	}
	public void setTkRolesId(Long tkRolesId) {
		this.tkRolesId = tkRolesId;
	}
	public String getPrincipalId() {
		return principalId;
	}
	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserPrincipalId() {
		return userPrincipalId;
	}
	public void setUserPrincipalId(String userPrincipalId) {
		this.userPrincipalId = userPrincipalId;
	}
	public Long getWorkArea() {
		return workArea;
	}
	public void setWorkArea(Long workArea) {
		this.workArea = workArea;
	}
	
	public Long getTkAssignmentId() {
		return tkAssignmentId;
	}

	public void setTkAssignmentId(Long tkAssignmentId) {
		this.tkAssignmentId = tkAssignmentId;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	protected LinkedHashMap toStringMapper() {
		// TODO Auto-generated method stub
		return null;
	}
}
