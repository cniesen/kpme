package edu.iu.uis.hr.tk.directory.entity;

/*******************************************************************************
 * DO NOT MODIFY THIS FILE - MODIFY THE MODEL AND REGENERATE
 * Generated on: Fri Dec 28 10:16:43 EST 2007
 * Generated by: HR/bdo version 1.52
/******************************************************************************/

import java.math.BigDecimal;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;

public abstract class AbstractRoleAudit extends AbstractPersistentDatabaseEntity {
	private BigDecimal roleAuditId;
	private String userUniversityId;
	private String roleUniversityId;
	private String department;
	private Timestamp timestamp;
	private String role;
	private String operation;
	private String location;

	public AbstractRoleAudit() {
		super();
	}

	public BigDecimal getRoleAuditId() {
		return roleAuditId;
	}

	public void setRoleAuditId(BigDecimal roleAuditId) {
		this.roleAuditId = roleAuditId;
	}

	public String getUserUniversityId() {
		return userUniversityId;
	}

	public void setUserUniversityId(String userUniversityId) {
		this.userUniversityId = userUniversityId;
	}

	public String getRoleUniversityId() {
		return roleUniversityId;
	}

	public void setRoleUniversityId(String roleUniversityId) {
		this.roleUniversityId = roleUniversityId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}