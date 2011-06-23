package org.kuali.hr.lm.leaveaccrualcategory;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import org.kuali.hr.lm.leaveplan.LeavePlan;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class LeaveAccrualCategory extends PersistableBusinessObjectBase {

	private Long lmLeaveAccrualCategoryId;
	private String accrualCategory;
	private String description;
	private Date effectiveDate;
	private String leavePlan;
	private String accrualEarnInterval;
	private String proration;
	private boolean showOnGrid;
	private Timestamp timeStamp; 
	
	private LeavePlan leavePlanObj;
	
	public String getLeavePlan() {
		return leavePlan;
	}

	public void setLeavePlan(String leavePlan) {
		this.leavePlan = leavePlan;
	}

	public Long getLmLeaveAccrualCategoryId() {
		return lmLeaveAccrualCategoryId;
	}

	public void setLmLeaveAccrualCategoryId(Long lmLeaveAccrualCategoryId) {
		this.lmLeaveAccrualCategoryId = lmLeaveAccrualCategoryId;
	}

	public String getAccrualCategory() {
		return accrualCategory;
	}

	public void setAccrualCategory(String accrualCategory) {
		this.accrualCategory = accrualCategory;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getAccrualEarnInterval() {
		return accrualEarnInterval;
	}

	public void setAccrualEarnInterval(String accrualEarnInterval) {
		this.accrualEarnInterval = accrualEarnInterval;
	}

	public String isProration() {
		return proration;
	}

	public void setProration(String proration) {
		this.proration = proration;
	}

	public boolean isShowOnGrid() {
		return showOnGrid;
	}

	public void setShowOnGrid(boolean showOnGrid) {
		this.showOnGrid = showOnGrid;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public LeavePlan getLeavePlanObj() {
		return leavePlanObj;
	}

	public void setLeavePlanObj(LeavePlan leavePlanObj) {
		this.leavePlanObj = leavePlanObj;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
