package org.kuali.hr.lm.leaveplan;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import org.kuali.hr.location.Location;
import org.kuali.hr.time.salgroup.SalGroup;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class LeavePlan extends PersistableBusinessObjectBase {

	private Long lmLeavePlanId;
	private String leavePlan;
	private String description;
	private Date effectiveDate;
	private String salaryGroup;
	private String location;
	private boolean unitOfTime;
	private boolean active;
	private Timestamp timeStamp; 
	private Location locationObj;
	private SalGroup salGroupObj;
	
	
    public Location getLocationObj() {
		return locationObj;
	}


	public void setLocationObj(Location locationObj) {
		this.locationObj = locationObj;
	}


	public SalGroup getSalGroupObj() {
		return salGroupObj;
	}


	public void setSalGroupObj(SalGroup salGroupObj) {
		this.salGroupObj = salGroupObj;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	
	
	public Long getLmLeavePlanId() {
		return lmLeavePlanId;
	}


	public void setLmLeavePlanId(Long lmLeavePlanId) {
		this.lmLeavePlanId = lmLeavePlanId;
	}


	public String getLeavePlan() {
		return leavePlan;
	}


	public void setLeavePlan(String leavePlan) {
		this.leavePlan = leavePlan;
	}


	public String getDescription() {
		return description;
	}


	public void setDescoription(String description) {
		this.description = description;
	}


	public Date getEffectiveDate() {
		return effectiveDate;
	}


	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}


	public String getSalaryGroup() {
		return salaryGroup;
	}


	public void setSalaryGroup(String salaryGroup) {
		this.salaryGroup = salaryGroup;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public boolean isUnitOfTime() {
		return unitOfTime;
	}


	public void setUnitOfTime(boolean unitOfTime) {
		this.unitOfTime = unitOfTime;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Timestamp getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}


	@Override
	protected LinkedHashMap toStringMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
