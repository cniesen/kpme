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
package org.kuali.kpme.core.accrualcategory.rule;

import com.google.common.collect.ImmutableMap;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.core.accrualcategory.AccrualCategoryBo;
import org.kuali.kpme.core.api.accrualcategory.rule.AccrualCategoryRule;
import org.kuali.kpme.core.api.accrualcategory.rule.AccrualCategoryRuleContract;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.earncode.EarnCodeBo;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "LM_ACCRUAL_CATEGORY_RULES_T")
public class AccrualCategoryRuleBo extends HrBusinessObject implements AccrualCategoryRuleContract {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "LM_ACCRUAL_CATEGORY_RULES_S")
    @GeneratedValue(generator = "LM_ACCRUAL_CATEGORY_RULES_S")
    @Id
    @Column(name = "LM_ACCRUAL_CATEGORY_RULES_ID", length = 60)
    private String lmAccrualCategoryRuleId;

    @Column(name = "SERVICE_UNIT_OF_TIME", length = 15)
    private String serviceUnitOfTime;

    @Column(name = "START_ACC", nullable = false, length = 5)
    private Long start;

    @Column(name = "END_ACC", nullable = false, length = 5)
    private Long end;

    @Column(name = "ACCRUAL_RATE", nullable = false, length = 12)
    private BigDecimal accrualRate;

    @Column(name = "MAX_BAL", length = 12)
    private BigDecimal maxBalance;

    @Column(name = "MAX_BAL_FLAG", nullable = false, length = 1)
    private String maxBalFlag;

    @Column(name = "MAX_BAL_ACTION_FREQUENCY", length = 5)
    private String maxBalanceActionFrequency;

    @Column(name = "ACTION_AT_MAX_BAL", length = 5)
    private String actionAtMaxBalance;

    @Column(name = "MAX_BAL_TRANS_ACC_CAT", length = 15)
    private String maxBalanceTransferToAccrualCategory;

    @Column(name = "MAX_BAL_TRANS_CONV_FACTOR", length = 12)
    private BigDecimal maxBalanceTransferConversionFactor;

    @Column(name = "MAX_TRANS_AMOUNT", length = 20)
    private Long maxTransferAmount;

    @Column(name = "MAX_PAYOUT_AMOUNT", length = 20)
    private Long maxPayoutAmount;

    @Column(name = "MAX_PAYOUT_EARN_CODE", length = 15)
    private String maxPayoutEarnCode;

    @Column(name = "MAX_USAGE", length = 20)
    private Long maxUsage;

    @Column(name = "MAX_CARRY_OVER", length = 20)
    private Long maxCarryOver;

    @Column(name = "LM_ACCRUAL_CATEGORY_ID", length = 60)
    private String lmAccrualCategoryId;

    @Transient
    private EarnCodeBo earnCodeObj;

    // TODO returning an empty map for the time-being, until the BK is finalized  
    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().build();
    }

    public EarnCodeBo getEarnCodeObj() {
        return earnCodeObj;
    }

    public void setEarnCodeObj(EarnCodeBo earnCodeObj) {
        this.earnCodeObj = earnCodeObj;
    }

    @Transient
    private AccrualCategoryBo accrualCategoryObj;

    public AccrualCategoryBo getAccrualCategoryObj() {
        return accrualCategoryObj;
    }

    public void setAccrualCategoryObj(AccrualCategoryBo accrualCategoryObj) {
        this.accrualCategoryObj = accrualCategoryObj;
    }

    public String getLmAccrualCategoryRuleId() {
        return lmAccrualCategoryRuleId;
    }

    public void setLmAccrualCategoryRuleId(String lmAccrualCategoryRuleId) {
        this.lmAccrualCategoryRuleId = lmAccrualCategoryRuleId;
    }

    public String getServiceUnitOfTime() {
        return serviceUnitOfTime;
    }

    public void setServiceUnitOfTime(String serviceUnitOfTime) {
        this.serviceUnitOfTime = serviceUnitOfTime;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public BigDecimal getAccrualRate() {
        return accrualRate;
    }

    public void setAccrualRate(BigDecimal accrualRate) {
        this.accrualRate = accrualRate;
    }

    public BigDecimal getMaxBalance() {
        return maxBalance;
    }

    public void setMaxBalance(BigDecimal maxBalance) {
        this.maxBalance = maxBalance;
    }

    public String getMaxBalFlag() {
        return maxBalFlag;
    }

    public void setMaxBalFlag(String maxBalFlag) {
        this.maxBalFlag = maxBalFlag;
    }

    public String getMaxBalanceActionFrequency() {
        return maxBalanceActionFrequency;
    }

    public void setMaxBalanceActionFrequency(String maxBalanceActionFrequency) {
        this.maxBalanceActionFrequency = maxBalanceActionFrequency;
    }

    public String getActionAtMaxBalance() {
        return actionAtMaxBalance;
    }

    public void setActionAtMaxBalance(String actionAtMaxBalance) {
        this.actionAtMaxBalance = actionAtMaxBalance;
    }

    public String getMaxBalanceTransferToAccrualCategory() {
        return maxBalanceTransferToAccrualCategory;
    }

    public void setMaxBalanceTransferToAccrualCategory(String maxBalanceTransferToAccrualCategory) {
        this.maxBalanceTransferToAccrualCategory = maxBalanceTransferToAccrualCategory;
    }

    public BigDecimal getMaxBalanceTransferConversionFactor() {
        return maxBalanceTransferConversionFactor;
    }

    public void setMaxBalanceTransferConversionFactor(BigDecimal maxBalanceTransferConversionFactor) {
        this.maxBalanceTransferConversionFactor = maxBalanceTransferConversionFactor;
    }

    public Long getMaxTransferAmount() {
        return maxTransferAmount;
    }

    public void setMaxTransferAmount(Long maxTransferAmount) {
        this.maxTransferAmount = maxTransferAmount;
    }

    public Long getMaxPayoutAmount() {
        return maxPayoutAmount;
    }

    public void setMaxPayoutAmount(Long maxPayoutAmount) {
        this.maxPayoutAmount = maxPayoutAmount;
    }

    public String getMaxPayoutEarnCode() {
        return maxPayoutEarnCode;
    }

    public void setMaxPayoutEarnCode(String maxPayoutEarnCode) {
        this.maxPayoutEarnCode = maxPayoutEarnCode;
    }

    public Long getMaxUsage() {
        return maxUsage;
    }

    public void setMaxUsage(Long maxUsage) {
        this.maxUsage = maxUsage;
    }

    public Long getMaxCarryOver() {
        return maxCarryOver;
    }

    public void setMaxCarryOver(Long maxCarryOver) {
        this.maxCarryOver = maxCarryOver;
    }

    public String getLmAccrualCategoryId() {
        return lmAccrualCategoryId;
    }

    public void setLmAccrualCategoryId(String lmAccrualCategoryId) {
        this.lmAccrualCategoryId = lmAccrualCategoryId;
    }

    @Override
    protected String getUniqueKey() {
        return accrualRate.toString();
    }

    @Override
    public String getId() {
        return getLmAccrualCategoryRuleId();
    }

    @Override
    public void setId(String id) {
        setLmAccrualCategoryRuleId(id);
    }

    public static AccrualCategoryRuleBo from(AccrualCategoryRule im) {
        AccrualCategoryRuleBo ac = new AccrualCategoryRuleBo();
        ac.setLmAccrualCategoryRuleId(im.getLmAccrualCategoryRuleId());
        ac.setServiceUnitOfTime(im.getServiceUnitOfTime());
        ac.setStart(im.getStart());
        ac.setEnd(im.getEnd());
        ac.setAccrualRate(im.getAccrualRate());
        ac.setMaxBalance(im.getMaxBalance());
        ac.setMaxBalFlag(im.getMaxBalFlag());
        ac.setMaxBalanceActionFrequency(im.getMaxBalanceActionFrequency());
        ac.setActionAtMaxBalance(im.getActionAtMaxBalance());
        ac.setMaxBalanceTransferToAccrualCategory(im.getMaxBalanceTransferToAccrualCategory());
        ac.setMaxBalanceTransferConversionFactor(im.getMaxBalanceTransferConversionFactor());
        ac.setMaxTransferAmount(im.getMaxTransferAmount());
        ac.setMaxPayoutAmount(im.getMaxPayoutAmount());
        ac.setMaxPayoutEarnCode(im.getMaxPayoutEarnCode());
        ac.setMaxUsage(im.getMaxUsage());
        ac.setMaxCarryOver(im.getMaxCarryOver());
        ac.setLmAccrualCategoryId(im.getLmAccrualCategoryId());
        ac.setEffectiveDate(im.getEffectiveLocalDate() == null ? null : im.getEffectiveLocalDate().toDate());
        ac.setActive(im.isActive());
        if (im.getCreateTime() != null) {
            ac.setTimestamp(new Timestamp(im.getCreateTime().getMillis()));
        }
        ac.setUserPrincipalId(im.getUserPrincipalId());
        ac.setVersionNumber(im.getVersionNumber());
        ac.setObjectId(im.getObjectId());
        return ac;
    }

    public static AccrualCategoryRule to(AccrualCategoryRuleBo bo) {
        if (bo == null) {
            return null;
        }
        return AccrualCategoryRule.Builder.create(bo).build();
    }

    public static final ModelObjectUtils.Transformer<AccrualCategoryRuleBo, AccrualCategoryRule> toImmutable = new ModelObjectUtils.Transformer<AccrualCategoryRuleBo, AccrualCategoryRule>() {

        public AccrualCategoryRule transform(AccrualCategoryRuleBo input) {
            return AccrualCategoryRuleBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<AccrualCategoryRule, AccrualCategoryRuleBo> toBo = new ModelObjectUtils.Transformer<AccrualCategoryRule, AccrualCategoryRuleBo>() {

        public AccrualCategoryRuleBo transform(AccrualCategoryRule input) {
            return AccrualCategoryRuleBo.from(input);
        }

        ;
    };
}
