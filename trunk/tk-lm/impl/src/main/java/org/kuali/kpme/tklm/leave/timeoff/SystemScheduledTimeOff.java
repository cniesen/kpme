/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.tklm.leave.timeoff;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.accrualcategory.AccrualCategory;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.earncode.EarnCode;
import org.kuali.kpme.core.leaveplan.LeavePlan;
import org.kuali.kpme.core.location.Location;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.kpme.tklm.api.leave.timeoff.SystemScheduledTimeOffContract;
import org.kuali.kpme.tklm.common.TkConstants;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class SystemScheduledTimeOff extends HrBusinessObject implements SystemScheduledTimeOffContract {

	private static final String ACCRUED_DATE = "accruedDate";
	private static final String ACCRUAL_CATEGORY = "accrualCategory";
	private static final String LOCATION = "location";
	private static final String LEAVE_PLAN = "leavePlan";
	private static final String EARN_CODE = "earnCode";

	private static final long serialVersionUID = 6660625335629574993L;

	public static final String CACHE_NAME = TkConstants.CacheNamespace.NAMESPACE_PREFIX + "SystemScheduledTimeOff";
	//KPME-2273/1965 Primary Business Keys List.
	public static final ImmutableList<String> fields = new ImmutableList.Builder<String>()
             .add(EARN_CODE)             
             .add(LEAVE_PLAN)
             .add(LOCATION)
             .add(ACCRUAL_CATEGORY)
             .add(ACCRUED_DATE)
             .build();
	
	
	private String lmSystemScheduledTimeOffId;
	private String leavePlan;
	private String accrualCategory;
	private String earnCode;
	private Date accruedDate;
	private Date scheduledTimeOffDate;
	private String location;
	private String descr;
	private BigDecimal amountofTime;
	private String unusedTime;
	private BigDecimal transferConversionFactor;
	private String transfertoEarnCode;
	private String premiumEarnCode;
	private String premiumHoliday;

	private LeavePlan leavePlanObj;
	private AccrualCategory accrualCategoryObj;
	private EarnCode earnCodeObj;
	private EarnCode transferToEarnCodeObj;
	private EarnCode premiumEarnCodeObj;
	private Location locationObj;
	
	
	@Override
	public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
    	return  new ImmutableMap.Builder<String, Object>()
			.put(EARN_CODE, this.getEarnCode())
			.put(LEAVE_PLAN, this.getLeavePlan())
			.put(LOCATION, this.getLocation())
			.put(ACCRUAL_CATEGORY, this.getAccrualCategory())
			.put(ACCRUED_DATE, this.getAccruedDate())
			.build();
	}
	
	public String getLmSystemScheduledTimeOffId() {
		return lmSystemScheduledTimeOffId;
	}

	public void setLmSystemScheduledTimeOffId(String lmSystemScheduledTimeOffId) {
		this.lmSystemScheduledTimeOffId = lmSystemScheduledTimeOffId;
	}

	public String getLeavePlan() {
		if(StringUtils.isNotEmpty(leavePlan)) {
			return leavePlan;
		}
		if (this.earnCodeObj == null && 
				(!StringUtils.isEmpty(this.earnCode) && getEffectiveDate() != null)) {		
			earnCodeObj =  (EarnCode) HrServiceLocator.getEarnCodeService().getEarnCode(earnCode, getEffectiveLocalDate());
		}
		leavePlan = (earnCodeObj != null) ? earnCodeObj.getLeavePlan() : "";
		return leavePlan;
	}

	public void setLeavePlan(String leavePlan) {
		this.leavePlan = leavePlan;
	}

	public String getAccrualCategory() {
        if(StringUtils.isNotEmpty(accrualCategory)) {
            return accrualCategory;
        }
		if (this.earnCodeObj == null &&
				(!StringUtils.isEmpty(this.earnCode) && getEffectiveDate() != null)) {
			earnCodeObj =  (EarnCode) HrServiceLocator.getEarnCodeService().getEarnCode(earnCode, getEffectiveLocalDate());
		}
		accrualCategory = (earnCodeObj != null) ? earnCodeObj.getAccrualCategory() : "";
		return accrualCategory;
	}

	public void setAccrualCategory(String accrualCategory) {
		this.accrualCategory = accrualCategory;
	}

	public Date getAccruedDate() {
		return accruedDate;
	}

	public void setAccruedDate(Date accruedDate) {
		this.accruedDate = accruedDate;
	}
	
	public LocalDate getAccruedLocalDate() {
		return accruedDate != null ? LocalDate.fromDateFields(accruedDate) : null;
	}
	
	public void setAccruedLocalDate(LocalDate accruedLocalDate) {
		this.accruedDate = accruedLocalDate != null? accruedLocalDate.toDate() : null;
	}

	public Date getScheduledTimeOffDate() {
		return scheduledTimeOffDate;
	}

	public void setScheduledTimeOffDate(Date scheduledTimeOffDate) {
		this.scheduledTimeOffDate = scheduledTimeOffDate;
	}
	
	public LocalDate getScheduledTimeOffLocalDate() {
		return scheduledTimeOffDate != null ? LocalDate.fromDateFields(scheduledTimeOffDate) : null;
	}
	
	public void setScheduledTimeOffLocalDate(LocalDate scheduledTimeOffLocalDate) {
		this.scheduledTimeOffDate = scheduledTimeOffLocalDate != null? scheduledTimeOffLocalDate.toDate() : null;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public BigDecimal getAmountofTime() {
		return amountofTime; // != null ? amountofTime.setScale(scale) : null;
	}

	public void setAmountofTime(BigDecimal amountofTime) {
		this.amountofTime = amountofTime;
	}

	public String getUnusedTime() {
		return unusedTime;
	}

	public void setUnusedTime(String unusedTime) {
		this.unusedTime = unusedTime;
	}

	public BigDecimal getTransferConversionFactor() {
		return transferConversionFactor != null ? transferConversionFactor.setScale(HrConstants.BIG_DECIMAL_SCALE) : null;
	}

	public void setTransferConversionFactor(BigDecimal transferConversionFactor) {
		this.transferConversionFactor = transferConversionFactor;
	}

	public String getPremiumHoliday() {
		return premiumHoliday;
	}

	public void setPremiumHoliday(String premiumHoliday) {
		this.premiumHoliday = premiumHoliday;
	}

	public LeavePlan getLeavePlanObj() {
		return leavePlanObj;
	}

	public void setLeavePlanObj(LeavePlan leavePlanObj) {
		this.leavePlanObj = leavePlanObj;
	}

	public AccrualCategory getAccrualCategoryObj() {
		return accrualCategoryObj;
	}

	public void setAccrualCategoryObj(AccrualCategory accrualCategoryObj) {
		this.accrualCategoryObj = accrualCategoryObj;
	}

	public String getEarnCode() {
		return earnCode;
	}

	public void setEarnCode(String earnCode) {
		this.earnCode = earnCode;
	}

	public String getTransfertoEarnCode() {
		return transfertoEarnCode;
	}

	public void setTransfertoEarnCode(String transfertoEarnCode) {
		this.transfertoEarnCode = transfertoEarnCode;
	}

	public EarnCode getEarnCodeObj() {
		return earnCodeObj;
	}

	public void setEarnCodeObj(EarnCode earnCodeObj) {
		this.earnCodeObj = earnCodeObj;
	}

	public Location getLocationObj() {
		return locationObj;
	}

	public void setLocationObj(Location locationObj) {
		this.locationObj = locationObj;
	}

	@Override
	protected String getUniqueKey() {
		String lmSystemScheduledTimeOffKey = getEarnCode() +"_"+ getLeavePlan() +"_"+ getAccrualCategory();
		return lmSystemScheduledTimeOffKey;
	}

	@Override
	public String getId() {
		return getLmSystemScheduledTimeOffId();
	}

	@Override
	public void setId(String id) {
		setLmSystemScheduledTimeOffId(id);
	}

	public EarnCode getTransferToEarnCodeObj() {
		return transferToEarnCodeObj;
	}

	public void setTransferToEarnCodeObj(EarnCode transferToEarnCodeObj) {
		this.transferToEarnCodeObj = transferToEarnCodeObj;
	}

	public String getPremiumEarnCode() {
		return premiumEarnCode;
	}

	public void setPremiumEarnCode(String premiumEarnCode) {
		this.premiumEarnCode = premiumEarnCode;
	}

	public EarnCode getPremiumEarnCodeObj() {
		return premiumEarnCodeObj;
	}

	public void setPremiumEarnCodeObj(EarnCode premiumEarnCodeObj) {
		this.premiumEarnCodeObj = premiumEarnCodeObj;
	}
	
}
