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
package org.kuali.kpme.tklm.leave.donation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.core.accrualcategory.AccrualCategoryBo;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.earncode.EarnCodeBo;
import org.kuali.kpme.tklm.api.common.TkConstants;
import org.kuali.kpme.tklm.api.leave.donation.LeaveDonationContract;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "LM_LEAVE_DONATION_T")
public class LeaveDonation extends HrBusinessObject implements LeaveDonationContract {

    private static final String AMOUNT_RECEIVED = "amountReceived";

    private static final String RECIPIENTS_ACCRUAL_CATEGORY = "recipientsAccrualCategory";

    private static final String RECIPIENTS_PRINCIPAL_ID = "recipientsPrincipalID";

    private static final String AMOUNT_DONATED = "amountDonated";

    private static final String DONATED_ACCRUAL_CATEGORY = "donatedAccrualCategory";

    private static final String DONORS_PRINCIPAL_ID = "donorsPrincipalID";

    public static final String CACHE_NAME = TkConstants.Namespace.NAMESPACE_PREFIX + "LeaveDonation";

    private static final long serialVersionUID = 1L;

    //KPME-2273/1965 Primary Business Keys List.	  
    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(DONORS_PRINCIPAL_ID).add(DONATED_ACCRUAL_CATEGORY).add(AMOUNT_DONATED).add(RECIPIENTS_PRINCIPAL_ID).add(RECIPIENTS_ACCRUAL_CATEGORY).add(AMOUNT_RECEIVED).build();

    @PortableSequenceGenerator(name = "LM_LEAVE_DONATION_S")
    @GeneratedValue(generator = "LM_LEAVE_DONATION_S")
    @Id
    @Column(name = "LM_LEAVE_DONATION_ID", length = 60)
    private String lmLeaveDonationId;

    @Column(name = "DONATED_ACC_CAT", nullable = false, length = 15)
    private String donatedAccrualCategory;

    @Column(name = "RECIPIENTS_ACC_CAT", nullable = false, length = 15)
    private String recipientsAccrualCategory;

    @Column(name = "AMOUNT_DONATED", nullable = false, length = 20)
    private BigDecimal amountDonated = new BigDecimal("0.0");

    @Column(name = "AMOUNT_RECEIVED", nullable = false, length = 20)
    private BigDecimal amountReceived = new BigDecimal("0.0");

    @Column(name = "DONOR", nullable = false, length = 20)
    private String donorsPrincipalID;

    @Column(name = "RECEPIENT", nullable = false, length = 20)
    private String recipientsPrincipalID;

    @Column(name = "DESCR", nullable = false, length = 50)
    private String description;

    @Transient
    private transient AccrualCategoryBo accrualCategoryObj;

    @Transient
    private transient Person personObj;

    @Transient
    private transient EarnCodeBo earnCodeObj;

    @Column(name = "DONATED_ER_CODE", nullable = false, length = 15)
    private String donatedEarnCode;

    @Column(name = "RECIPIENTS_ER_CODE", nullable = false, length = 15)
    private String recipientsEarnCode;

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(DONORS_PRINCIPAL_ID, this.getDonorsPrincipalID()).put(DONATED_ACCRUAL_CATEGORY, this.getDonatedAccrualCategory()).put(AMOUNT_DONATED, this.getAmountDonated()).put(RECIPIENTS_PRINCIPAL_ID, this.getRecipientsPrincipalID()).put(RECIPIENTS_ACCRUAL_CATEGORY, this.getRecipientsAccrualCategory()).put(AMOUNT_RECEIVED, this.getAmountReceived()).build();
    }

    public EarnCodeBo getEarnCodeObj() {
        return earnCodeObj;
    }

    public void setEarnCodeObj(EarnCodeBo earnCodeObj) {
        this.earnCodeObj = earnCodeObj;
    }

    public String getDonatedEarnCode() {
        return donatedEarnCode;
    }

    public void setDonatedEarnCode(String donatedEarnCode) {
        this.donatedEarnCode = donatedEarnCode;
    }

    public String getRecipientsEarnCode() {
        return recipientsEarnCode;
    }

    public void setRecipientsEarnCode(String recipientsEarnCode) {
        this.recipientsEarnCode = recipientsEarnCode;
    }

    public String getLmLeaveDonationId() {
        return lmLeaveDonationId;
    }

    public void setLmLeaveDonationId(String lmLeaveDonationId) {
        this.lmLeaveDonationId = lmLeaveDonationId;
    }

    public String getDonatedAccrualCategory() {
        return donatedAccrualCategory;
    }

    public void setDonatedAccrualCategory(String donatedAccrualCategory) {
        this.donatedAccrualCategory = donatedAccrualCategory;
    }

    public String getRecipientsAccrualCategory() {
        return recipientsAccrualCategory;
    }

    public void setRecipientsAccrualCategory(String recipientsAccrualCategory) {
        this.recipientsAccrualCategory = recipientsAccrualCategory;
    }

    public BigDecimal getAmountDonated() {
        return amountDonated;
    }

    public void setAmountDonated(BigDecimal amountDonated) {
        this.amountDonated = amountDonated;
    }

    public BigDecimal getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(BigDecimal amountReceived) {
        this.amountReceived = amountReceived;
    }

    public String getDonorsPrincipalID() {
        return donorsPrincipalID;
    }

    public void setDonorsPrincipalID(String donorsPrincipalID) {
        this.donorsPrincipalID = donorsPrincipalID;
    }

    public String getRecipientsPrincipalID() {
        return recipientsPrincipalID;
    }

    public void setRecipientsPrincipalID(String recipientsPrincipalID) {
        this.recipientsPrincipalID = recipientsPrincipalID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPersonObj() {
        return personObj;
    }

    public void setPersonObj(Person personObj) {
        this.personObj = personObj;
    }

    public AccrualCategoryBo getAccrualCategoryObj() {
        return accrualCategoryObj;
    }

    public void setAccrualCategoryObj(AccrualCategoryBo accrualCategoryObj) {
        this.accrualCategoryObj = accrualCategoryObj;
    }

    @Override
    protected String getUniqueKey() {
        return getDonorsPrincipalID() + "_" + getRecipientsPrincipalID() + "_" + getDonatedAccrualCategory() + "_" + getRecipientsAccrualCategory();
    }

    @Override
    public String getId() {
        return getLmLeaveDonationId();
    }

    @Override
    public void setId(String id) {
        setLmLeaveDonationId(id);
    }
}
