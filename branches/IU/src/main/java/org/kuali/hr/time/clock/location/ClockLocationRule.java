package org.kuali.hr.time.clock.location;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class ClockLocationRule extends PersistableBusinessObjectBase {

	private static final long serialVersionUID = 1L;

	private Long clockLocationRuleId;
	private String department;
	private BigDecimal workArea;
	private String principalId;
	private BigDecimal jobNumber;
	
	private Date effectiveDate;
	private boolean active;
	private String ipAddress;
	private String userPrincipalId;
	private Timestamp timestamp;
	
	@Override
	protected LinkedHashMap<String,Object> toStringMapper() {
		LinkedHashMap<String, Object> toStringMap = new LinkedHashMap<String,Object>();
		
		toStringMap.put("principalId", principalId);
		
		return toStringMap;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public BigDecimal getWorkArea() {
		return workArea;
	}

	public void setWorkArea(BigDecimal workArea) {
		this.workArea = workArea;
	}

	public String getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserPrincipalId() {
		return userPrincipalId;
	}

	public void setUserPrincipalId(String userPrincipalId) {
		this.userPrincipalId = userPrincipalId;
	}

	public BigDecimal getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(BigDecimal jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Long getClockLocationRuleId() {
	    return clockLocationRuleId;
	}

	public void setClockLocationRuleId(Long clockLocationRuleId) {
	    this.clockLocationRuleId = clockLocationRuleId;
	}
}
