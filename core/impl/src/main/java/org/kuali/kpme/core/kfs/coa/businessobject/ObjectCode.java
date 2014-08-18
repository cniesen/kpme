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

import com.google.common.collect.ImmutableList;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.kuali.kpme.core.api.kfs.coa.businessobject.ObjectCodeContract;
import org.kuali.kpme.core.kfs.coa.businessobject.Chart;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

/**
 *
 */
@Entity
@Table(name = "CA_OBJECT_CODE_T")
@IdClass(ObjectCode.ObjectCodeId.class)
public class ObjectCode extends PersistableBusinessObjectBase implements ObjectCodeContract {

    /*    static {
        PersistenceStructureServiceImpl.referenceConversionMap.put(ObjectCode.class, ObjectCodeCurrent.class);
    }*/
    // business keys. ( also primary key in db ). 
    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add("universityFiscalYear").add("chartOfAccountsCode").add("financialObjectCode").build();

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ObjectCode.class);

    private static final long serialVersionUID = -965833141452795485L;

    @Id
    @Column(name = "UNIV_FISCAL_YR")
    private Integer universityFiscalYear;

    @Id
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;

    @Id
    @Column(name = "FIN_OBJECT_CD")
    private String financialObjectCode;

    @Column(name = "FIN_OBJ_CD_NM")
    private String financialObjectCodeName;

    @Column(name = "FIN_OBJ_CD_SHRT_NM")
    private String financialObjectCodeShortName;

    @Column(name = "FIN_OBJ_ACTIVE_CD")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    @Transient
    private String financialObjectLevelCode;

    @ManyToOne(targetEntity = Chart.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false)
    private transient Chart chartOfAccounts;

    /**
     * This method is only for use by the framework
     */
    public void setUniversityFiscalYear(Integer i) {
        this.universityFiscalYear = i;
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

    /**
     * Gets the financialObjectCodeName attribute.
     *
     * @return Returns the financialObjectCodeName
     */
    public String getFinancialObjectCodeName() {
        return financialObjectCodeName;
    }

    /**
     * Sets the financialObjectCodeName attribute.
     *
     * @param financialObjectCodeName The financialObjectCodeName to set.
     */
    public void setFinancialObjectCodeName(String financialObjectCodeName) {
        this.financialObjectCodeName = financialObjectCodeName;
    }

    /**
     * Gets the financialObjectCodeShortName attribute.
     *
     * @return Returns the financialObjectCodeShortName
     */
    public String getFinancialObjectCodeShortName() {
        return financialObjectCodeShortName;
    }

    /**
     * Sets the financialObjectCodeShortName attribute.
     *
     * @param financialObjectCodeShortName The financialObjectCodeShortName to set.
     */
    public void setFinancialObjectCodeShortName(String financialObjectCodeShortName) {
        this.financialObjectCodeShortName = financialObjectCodeShortName;
    }

    /**
     * Gets the financialObjectActiveCode attribute.
     *
     * @return Returns the financialObjectActiveCode
     */
    public boolean isFinancialObjectActiveCode() {
        return active;
    }

    /**
     * Sets the financialObjectActiveCode attribute.
     *
     */
    public void setFinancialObjectActiveCode(boolean active) {
        this.active = active;
    }

    /**
     * Gets the financialBudgetAggregationCd attribute.
     *
     * @return Returns the financialBudgetAggregationCd
     */
    /*
     * public BudgetAggregationCode getFinancialBudgetAggregation() { return financialBudgetAggregation; }
     */
    /**
     * Sets the financialBudgetAggregationCd attribute.
     *
     * @param financialBudgetAggregationCd The financialBudgetAggregationCd to set.
     * @deprecated
     */
    /*
     * public void setFinancialBudgetAggregation(BudgetAggregationCode financialBudgetAggregationCd) {
     * this.financialBudgetAggregation = financialBudgetAggregationCd; }
     */
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
     */
    public void setChartOfAccountsCode(String string) {
        this.chartOfAccountsCode = string;
    }

    /**
     *
     */
    public String getChartOfAccountsCode() {
        return this.chartOfAccountsCode;
    }

    /**
     *
     */
    public Integer getUniversityFiscalYear() {
        return this.universityFiscalYear;
    }

    /**
     * @return Returns the financialObjectLevelCode.
     */
    public String getFinancialObjectLevelCode() {
        return financialObjectLevelCode;
    }

    /**
     * @param financialObjectLevelCode The financialObjectLevelCode to set.
     */
    public void setFinancialObjectLevelCode(String financialObjectLevelCode) {
        this.financialObjectLevelCode = financialObjectLevelCode;
    }

    /**
     * @see org.kuali.rice.kns.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("financialObjectCode", this.financialObjectCode);
        return m;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean a) {
        this.active = a;
    }

    public void setCode(String code) {
        this.chartOfAccountsCode = code;
    }

    public void setName(String name) {
        this.financialObjectCodeName = name;
    }

    public String getCode() {
        return this.financialObjectCode;
    }

    public String getName() {
        return this.financialObjectCodeName;
    }

    public static final class ObjectCodeId implements Serializable, Comparable<ObjectCodeId> {

        private Integer universityFiscalYear;

        private String chartOfAccountsCode;

        private String financialObjectCode;

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

        public String getFinancialObjectCode() {
            return this.financialObjectCode;
        }

        public void setFinancialObjectCode(String financialObjectCode) {
            this.financialObjectCode = financialObjectCode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("universityFiscalYear", this.universityFiscalYear).append("chartOfAccountsCode", this.chartOfAccountsCode).append("financialObjectCode", this.financialObjectCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ObjectCodeId rhs = (ObjectCodeId) other;
            return new EqualsBuilder().append(this.universityFiscalYear, rhs.universityFiscalYear).append(this.chartOfAccountsCode, rhs.chartOfAccountsCode).append(this.financialObjectCode, rhs.financialObjectCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.universityFiscalYear).append(this.chartOfAccountsCode).append(this.financialObjectCode).toHashCode();
        }

        @Override
        public int compareTo(ObjectCodeId other) {
            return new CompareToBuilder().append(this.universityFiscalYear, other.universityFiscalYear).append(this.chartOfAccountsCode, other.chartOfAccountsCode).append(this.financialObjectCode, other.financialObjectCode).toComparison();
        }
    }
}
