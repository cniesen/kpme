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
import java.util.LinkedHashMap;
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
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.kuali.kpme.core.api.kfs.coa.businessobject.SubObjectCodeContract;
import org.kuali.kpme.core.kfs.coa.businessobject.Account;
import org.kuali.kpme.core.kfs.coa.businessobject.Chart;
import org.kuali.kpme.core.kfs.coa.businessobject.ObjectCode;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

/**
 * 
 */
@Entity
@Table(name = "CA_SUB_OBJECT_CD_T")
@IdClass(SubObjectCode.SubObjectCodeId.class)
public class SubObjectCode extends PersistableBusinessObjectBase implements SubObjectCodeContract {

    private static final long serialVersionUID = -5292158248714650271L;

    /*   static {
        PersistenceStructureServiceImpl.referenceConversionMap.put(SubObjectCode.class, SubObjectCodeCurrent.class);
    }*/
    /**
     * Default no-arg constructor.
     */
    public SubObjectCode() {
    }

    /**
     * Constructs an active SubObjCd.java with the given primary key.
     * 
     * @param universityFiscalYear
     * @param chartOfAccountsCode
     * @param accountNumber
     * @param financialObjectCode
     * @param financialSubObjectCode
     */
    public SubObjectCode(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber, String financialObjectCode, String financialSubObjectCode) {
        this.universityFiscalYear = universityFiscalYear;
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.accountNumber = accountNumber;
        this.financialObjectCode = financialObjectCode;
        this.financialSubObjectCode = financialSubObjectCode;
        this.active = true;
    }

    @Id
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;

    @Id
    @Column(name = "ACCOUNT_NBR")
    private String accountNumber;

    @Id
    @Column(name = "FIN_OBJECT_CD")
    private String financialObjectCode;

    @Id
    @Column(name = "FIN_SUB_OBJ_CD")
    private String financialSubObjectCode;

    @Column(name = "FIN_SUB_OBJ_CD_NM")
    private String financialSubObjectCodeName;

    @Column(name = "FIN_SUBOBJ_SHRT_NM")
    private String financialSubObjectCdshortNm;

    @Column(name = "FIN_SUBOBJ_ACTV_CD")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    @Id
    @Column(name = "UNIV_FISCAL_YR")
    private Integer universityFiscalYear;

    @ManyToOne(targetEntity = Chart.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chartOfAccounts;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    /*
FIXME: JPA_CONVERSION
For compound primary keys, make sure the join columns are in the correct order.
*/
    @JoinColumns({ @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false), @JoinColumn(name = "ACCOUNT_NBR", referencedColumnName = "ACCOUNT_NBR", insertable = false, updatable = false) })
    private Account account;

    @ManyToOne(targetEntity = ObjectCode.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    /*
FIXME: JPA_CONVERSION
For compound primary keys, make sure the join columns are in the correct order.
*/
    @JoinColumns({ @JoinColumn(name = "UNIV_FISCAL_YR", referencedColumnName = "UNIV_FISCAL_YR", insertable = false, updatable = false), @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false), @JoinColumn(name = "FIN_OBJECT_CD", referencedColumnName = "FIN_OBJECT_CD", insertable = false, updatable = false) })
    private ObjectCode financialObject;

    public String getFinancialSubObjectCode() {
        return financialSubObjectCode;
    }

    public void setFinancialSubObjectCode(String financialSubObjectCode) {
        this.financialSubObjectCode = financialSubObjectCode;
    }

    /**
     * Gets the financialObjectCode attribute.
     * 
     * @return Returns the financialObjectCode
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    /**
     * Sets the financialObjectCode attribute.
     * 
     * @param financialObjectCode The financialObjectCode to set.
     */
    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }

    public ObjectCode getFinancialObject() {
        return financialObject;
    }

    /**
     * @deprecated
     */
    public void setFinancialObject(ObjectCode financialObject) {
        this.financialObject = financialObject;
    }

    /**
     * Gets the financialSubObjectCodeName attribute.
     * 
     * @return Returns the financialSubObjectCodeName
     */
    public String getFinancialSubObjectCodeName() {
        return financialSubObjectCodeName;
    }

    /**
     * Sets the financialSubObjectCodeName attribute.
     * 
     * @param financialSubObjectCodeName The financialSubObjectCodeName to set.
     */
    public void setFinancialSubObjectCodeName(String financialSubObjectCodeName) {
        this.financialSubObjectCodeName = financialSubObjectCodeName;
    }

    /**
     * Gets the financialSubObjectCdshortNm attribute.
     * 
     * @return Returns the financialSubObjectCdshortNm
     */
    public String getFinancialSubObjectCdshortNm() {
        return financialSubObjectCdshortNm;
    }

    /**
     * Sets the financialSubObjectCdshortNm attribute.
     * 
     * @param financialSubObjectCdshortNm The financialSubObjectCdshortNm to set.
     */
    public void setFinancialSubObjectCdshortNm(String financialSubObjectCdshortNm) {
        this.financialSubObjectCdshortNm = financialSubObjectCdshortNm;
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
     * Gets the account attribute.
     * 
     * @return Returns the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account attribute.
     * 
     * @param account The account to set.
     * @deprecated
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /**
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("universityFiscalYear", this.universityFiscalYear);
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("financialObjectCode", this.financialObjectCode);
        m.put("financialSubObjectCode", this.financialSubObjectCode);
        return m;
    }

    public static final class SubObjectCodeId implements Serializable, Comparable<SubObjectCodeId> {

        private Integer universityFiscalYear;

        private String chartOfAccountsCode;

        private String accountNumber;

        private String financialObjectCode;

        private String financialSubObjectCode;

        public Integer getUniversityFiscalYear() {
            return this.universityFiscalYear;
        }

        public void setUniversityFiscalYear(Integer universityFiscalYear) {
            this.universityFiscalYear = universityFiscalYear;
        }

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

        public String getFinancialObjectCode() {
            return this.financialObjectCode;
        }

        public void setFinancialObjectCode(String financialObjectCode) {
            this.financialObjectCode = financialObjectCode;
        }

        public String getFinancialSubObjectCode() {
            return this.financialSubObjectCode;
        }

        public void setFinancialSubObjectCode(String financialSubObjectCode) {
            this.financialSubObjectCode = financialSubObjectCode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("universityFiscalYear", this.universityFiscalYear).append("chartOfAccountsCode", this.chartOfAccountsCode).append("accountNumber", this.accountNumber).append("financialObjectCode", this.financialObjectCode).append("financialSubObjectCode", this.financialSubObjectCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final SubObjectCodeId rhs = (SubObjectCodeId) other;
            return new EqualsBuilder().append(this.universityFiscalYear, rhs.universityFiscalYear).append(this.chartOfAccountsCode, rhs.chartOfAccountsCode).append(this.accountNumber, rhs.accountNumber).append(this.financialObjectCode, rhs.financialObjectCode).append(this.financialSubObjectCode, rhs.financialSubObjectCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.universityFiscalYear).append(this.chartOfAccountsCode).append(this.accountNumber).append(this.financialObjectCode).append(this.financialSubObjectCode).toHashCode();
        }

        @Override
        public int compareTo(SubObjectCodeId other) {
            return new CompareToBuilder().append(this.universityFiscalYear, other.universityFiscalYear).append(this.chartOfAccountsCode, other.chartOfAccountsCode).append(this.accountNumber, other.accountNumber).append(this.financialObjectCode, other.financialObjectCode).append(this.financialSubObjectCode, other.financialSubObjectCode).toComparison();
        }
    }
}
