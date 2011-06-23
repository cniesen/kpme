package org.kuali.hr.lm.leaveaccrualcategoryrule;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import org.kuali.hr.lm.leaveaccrualcategory.LeaveAccrualCategory;
import org.kuali.hr.lm.leaveplan.LeavePlan;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class LeaveAccrualCategoryRule extends PersistableBusinessObjectBase {

	private Long lmLeaveAccrualCategoryRuleId;
	private String leaveAccrualCategory;
	private String serviceUnitOfTime;
	private Integer ruleStart;
	private Integer ruleEnd;
	private Integer accrualRate;
	private Integer maxBalance;
	private String actionAtMaxBalance;
	private String transferToLeaveAccrualCategory;
	private BigDecimal transferFactor;
	private Integer transferAmount;
	private Integer maxPayoutAmount;
	private String maxPayoutLeaveCode;
	private Integer maxUsage;
	private Integer maxCarryOver;
	private Timestamp timeStamp; 
	
	private LeaveAccrualCategory accrualCategoryObj;
	//private LeaveCode leaveCodeObj;
	
	 
	public Long getLmLeaveAccrualCategoryRuleId() {
		return lmLeaveAccrualCategoryRuleId;
	}

	public void setLmLeaveAccrualCategoryRuleId(Long lmLeaveAccrualCategoryRuleId) {
		this.lmLeaveAccrualCategoryRuleId = lmLeaveAccrualCategoryRuleId;
	}

	public String getLeaveAccrualCategory() {
		return leaveAccrualCategory;
	}

	public void setLeaveAccrualCategory(String leaveAccrualCategory) {
		this.leaveAccrualCategory = leaveAccrualCategory;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getServiceUnitOfTime() {
		return serviceUnitOfTime;
	}

	public void setServiceUnitOfTime(String serviceUnitOfTime) {
		this.serviceUnitOfTime = serviceUnitOfTime;
	}

	public Integer getRuleStart() {
		return ruleStart;
	}

	public void setRuleStart(Integer ruleStart) {
		this.ruleStart = ruleStart;
	}

	public Integer getRuleEnd() {
		return ruleEnd;
	}

	public void setRuleEnd(Integer ruleEnd) {
		this.ruleEnd = ruleEnd;
	}

	public Integer getAccrualRate() {
		return accrualRate;
	}

	public void setAccrualRate(Integer accrualRate) {
		this.accrualRate = accrualRate;
	}

	public Integer getMaxBalance() {
		return maxBalance;
	}

	public void setMaxBalance(Integer maxBalance) {
		this.maxBalance = maxBalance;
	}

	public String getActionAtMaxBalance() {
		return actionAtMaxBalance;
	}

	public void setActionAtMaxBalance(String actionAtMaxBalance) {
		this.actionAtMaxBalance = actionAtMaxBalance;
	}

	public String getTransferToLeaveAccrualCategory() {
		return transferToLeaveAccrualCategory;
	}

	public void setTransferToLeaveAccrualCategory(
			String transferToLeaveAccrualCategory) {
		this.transferToLeaveAccrualCategory = transferToLeaveAccrualCategory;
	}

	public BigDecimal getTransferFactor() {
		return transferFactor;
	}

	public void setTransferFactor(BigDecimal transferFactor) {
		this.transferFactor = transferFactor;
	}

	public Integer getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Integer transferAmount) {
		this.transferAmount = transferAmount;
	}

	public Integer getMaxPayoutAmount() {
		return maxPayoutAmount;
	}

	public void setMaxPayoutAmount(Integer maxPayoutAmount) {
		this.maxPayoutAmount = maxPayoutAmount;
	}

	public String getMaxPayoutLeaveCode() {
		return maxPayoutLeaveCode;
	}

	public void setMaxPayoutLeaveCode(String maxPayoutLeaveCode) {
		this.maxPayoutLeaveCode = maxPayoutLeaveCode;
	}

	public Integer getMaxUsage() {
		return maxUsage;
	}

	public void setMaxUsage(Integer maxUsage) {
		this.maxUsage = maxUsage;
	}

	public Integer getMaxCarryOver() {
		return maxCarryOver;
	}

	public void setMaxCarryOver(Integer maxCarryOver) {
		this.maxCarryOver = maxCarryOver;
	}


	public LeaveAccrualCategory getAccrualCategoryObj() {
		return accrualCategoryObj;
	}

	public void setAccrualCategoryObj(LeaveAccrualCategory accrualCategoryObj) {
		this.accrualCategoryObj = accrualCategoryObj;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
