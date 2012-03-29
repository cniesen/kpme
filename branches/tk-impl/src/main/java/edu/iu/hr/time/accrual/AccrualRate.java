package edu.iu.hr.time.accrual;

import java.math.BigDecimal;

public class AccrualRate {
	private String planType;
	private String benefitPlan;
	private BigDecimal hoursEarned;
	
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getBenefitPlan() {
		return benefitPlan;
	}
	public void setBenefitPlan(String benefitPlan) {
		this.benefitPlan = benefitPlan;
	}
	public BigDecimal getHoursEarned() {
		return hoursEarned;
	}
	public void setHoursEarned(BigDecimal hoursEarned) {
		this.hoursEarned = hoursEarned;
	}


}
