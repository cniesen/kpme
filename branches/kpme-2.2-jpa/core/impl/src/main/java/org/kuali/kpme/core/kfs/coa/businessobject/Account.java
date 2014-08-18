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
package org.kuali.kpme.core.kfs.coa.businessobject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateUtils;
import org.kuali.kpme.core.api.kfs.coa.businessobject.AccountContract;
import org.kuali.kpme.core.kfs.coa.businessobject.Chart;
import org.kuali.kpme.core.kfs.coa.businessobject.Organization;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.InverseBooleanYNConverter;

/**
 * 
 */
@Entity
@Table(name = "CA_ACCOUNT_T")
@IdClass(Account.AccountId.class)
public class Account extends PersistableBusinessObjectBase implements AccountContract {

    protected static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Account.class);

    @Id
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;

    @Id
    @Column(name = "ACCOUNT_NBR")
    private String accountNumber;

    @Column(name = "ACCOUNT_NM")
    private String accountName;

    @Column(name = "ACCT_CREATE_DT")
    @Temporal(TemporalType.DATE)
    private Date accountCreateDate;

    @Column(name = "ACCT_EFFECT_DT")
    @Temporal(TemporalType.DATE)
    private Date accountEffectiveDate;

    @Column(name = "ACCT_EXPIRATION_DT")
    @Temporal(TemporalType.DATE)
    private Date accountExpirationDate;

    @Column(name = "ACCT_CLOSED_IND")
    @Convert(converter = InverseBooleanYNConverter.class)
    private boolean active;

    @Column(name = "ORG_CD")
    private String organizationCode;

    @ManyToOne(targetEntity = Chart.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chartOfAccounts;

    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    /*
FIXME: JPA_CONVERSION
For compound primary keys, make sure the join columns are in the correct order.
*/
    @JoinColumns({ @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false), @JoinColumn(name = "ORG_CD", referencedColumnName = "ORG_CD", insertable = false, updatable = false) })
    private Organization organization;

    @Transient
    private List subAccounts;

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    /**
     * Default no-arg constructor.
     */
    public Account() {
        active = true;
    }

    /**
     * Gets the accountNumber attribute.
     * 
     * @return Returns the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the accountNumber attribute.
     * 
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the accountName attribute.
     * 
     * @return Returns the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the accountName attribute.
     * 
     * @param accountName The accountName to set.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Gets the accountCreateDate attribute.
     * 
     * @return Returns the accountCreateDate
     */
    public Date getAccountCreateDate() {
        return accountCreateDate;
    }

    /**
     * Sets the accountCreateDate attribute.
     * 
     * @param accountCreateDate The accountCreateDate to set.
     */
    public void setAccountCreateDate(Date accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }

    /**
     * Gets the accountEffectiveDate attribute.
     * 
     * @return Returns the accountEffectiveDate
     */
    public Date getAccountEffectiveDate() {
        return accountEffectiveDate;
    }

    /**
     * Sets the accountEffectiveDate attribute.
     * 
     * @param accountEffectiveDate The accountEffectiveDate to set.
     */
    public void setAccountEffectiveDate(Date accountEffectiveDate) {
        this.accountEffectiveDate = accountEffectiveDate;
    }

    /**
     * Gets the accountExpirationDate attribute.
     * 
     * @return Returns the accountExpirationDate
     */
    public Date getAccountExpirationDate() {
        return accountExpirationDate;
    }

    /**
     * Sets the accountExpirationDate attribute.
     * 
     * @param accountExpirationDate The accountExpirationDate to set.
     */
    public void setAccountExpirationDate(Date accountExpirationDate) {
        this.accountExpirationDate = accountExpirationDate;
    }

    /**
     * This method determines whether the account is expired or not. Note that if Expiration Date is the same date as testDate, then
     * this will return false. It will only return true if the account expiration date is one day earlier than testDate or earlier.
     * Note that this logic ignores all time components when doing the comparison. It only does the before/after comparison based on
     * date values, not time-values.
     * 
     * @param testDate - Calendar instance with the date to test the Account's Expiration Date against. This is most commonly set to
     *        today's date.
     * @return true or false based on the logic outlined above
     */
    public boolean isExpired(Calendar testDate) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("entering isExpired(" + testDate + ")");
        }
        // dont even bother trying to test if the accountExpirationDate is null 
        if (this.accountExpirationDate == null) {
            return false;
        }
        // remove any time-components from the testDate 
        testDate = DateUtils.truncate(testDate, Calendar.DAY_OF_MONTH);
        // get a calendar reference to the Account Expiration 
        // date, and remove any time components 
        Calendar acctDate = Calendar.getInstance();
        acctDate.setTime(this.accountExpirationDate);
        acctDate = DateUtils.truncate(acctDate, Calendar.DAY_OF_MONTH);
        // if the Account Expiration Date is before the testDate 
        if (acctDate.before(testDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method determines whether the account is expired or not. Note that if Expiration Date is the same date as testDate, then
     * this will return false. It will only return true if the account expiration date is one day earlier than testDate or earlier.
     * Note that this logic ignores all time components when doing the comparison. It only does the before/after comparison based on
     * date values, not time-values.
     * 
     * @param testDate - Date instance with the date to test the Account's Expiration Date against. This is most commonly
     *        set to today's date.
     * @return true or false based on the logic outlined above
     */
    public boolean isExpired(Date testDate) {
        // dont even bother trying to test if the accountExpirationDate is null 
        if (this.accountExpirationDate == null) {
            return false;
        }
        Calendar acctDate = Calendar.getInstance();
        acctDate.setTime(testDate);
        return isExpired(acctDate);
    }

    /**
     * Gets the active attribute.
     * 
     * @return Returns the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active attribute.
     * 
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns whether this account is not active or not
     * 
     * @return the opposite of isActive()
     */
    public boolean isClosed() {
        return !active;
    }

    /**
     * Sets the closed attribute.
     * 
     * @param closed The closed to set.
     */
    public void setClosed(boolean closed) {
        this.active = !closed;
    }

    /**
     * Gets the chartOfAccounts attribute.
     * 
     * @return Returns the chartOfAccounts
     */
    public Chart getChartOfAccounts() {
        return chartOfAccounts;
    }

    /**
     * Sets the chartOfAccounts attribute.
     * 
     * @param chartOfAccounts The chartOfAccounts to set.
     * @deprecated
     */
    public void setChartOfAccounts(Chart chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    /**
     * Gets the organization attribute.
     * 
     * @return Returns the organization
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Sets the organization attribute.
     * 
     * @param organization The organization to set.
     * @deprecated
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * @return Returns the subAccounts.
     */
    public List getSubAccounts() {
        return subAccounts;
    }

    /**
     * @param subAccounts The subAccounts to set.
     */
    public void setSubAccounts(List subAccounts) {
        this.subAccounts = subAccounts;
    }

    /**
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("chartCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        return m;
    }

    /**
     * Implementing equals since I need contains to behave reasonably in a hashed datastructure.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        boolean equal = false;
        if (obj != null) {
            if (this.getClass().equals(obj.getClass())) {
                Account other = (Account) obj;
                if (StringUtils.equals(this.getChartOfAccountsCode(), other.getChartOfAccountsCode())) {
                    if (StringUtils.equals(this.getAccountNumber(), other.getAccountNumber())) {
                        equal = true;
                    }
                }
            }
        }
        return equal;
    }

    /**
     * Calcluates hashCode based on current values of chartOfAccountsCode and accountNumber fields. Somewhat dangerous, since both
     * of those fields are mutable, but I don't expect people to be editing those values directly for Accounts stored in hashed
     * datastructures.
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        String hashString = getChartOfAccountsCode() + "|" + getAccountNumber();
        return hashString.hashCode();
    }

    /**
     * Convenience method to make the primitive account fields from this Account easier to compare to the account fields from
     * another Account or an AccountingLine
     * 
     * @return String representing the account associated with this Accounting
     */
    public String getAccountKey() {
        String key = getChartOfAccountsCode() + ":" + getAccountNumber();
        return key;
    }

    public static final class AccountId implements Serializable, Comparable<AccountId> {

        private String chartOfAccountsCode;

        private String accountNumber;

        public String getChartOfAccountsCode() {
            return this.chartOfAccountsCode;
        }

        public void setChartOfAccountsCode(String chartOfAccountsCode) {
            this.chartOfAccountsCode = chartOfAccountsCode;
        }

        public String getAccountNumber() {
            return this.accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("chartOfAccountsCode", this.chartOfAccountsCode).append("accountNumber", this.accountNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final AccountId rhs = (AccountId) other;
            return new EqualsBuilder().append(this.chartOfAccountsCode, rhs.chartOfAccountsCode).append(this.accountNumber, rhs.accountNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.chartOfAccountsCode).append(this.accountNumber).toHashCode();
        }

        @Override
        public int compareTo(AccountId other) {
            return new CompareToBuilder().append(this.chartOfAccountsCode, other.chartOfAccountsCode).append(this.accountNumber, other.accountNumber).toComparison();
        }
    }
}
