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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.kuali.kpme.core.api.kfs.coa.businessobject.OrganizationContract;
import org.kuali.kpme.core.kfs.coa.businessobject.Chart;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

/**
 * 
 */
@Entity
@Table(name = "CA_ORG_T")
@IdClass(Organization.OrganizationId.class)
public class Organization extends PersistableBusinessObjectBase implements OrganizationContract {

    private static final Logger LOG = Logger.getLogger(Organization.class);

    private static final long serialVersionUID = 121873645110037203L;

    /**
     * Default no-arg constructor.
     */
    @Id
    @Column(name = "ORG_CD")
    private String organizationCode;

    @Column(name = "ORG_NM")
    private String organizationName;

    @ManyToOne(targetEntity = Chart.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "FIN_COA_CD", referencedColumnName = "FIN_COA_CD", insertable = false, updatable = false)
    private Chart chartOfAccounts;

    @Column(name = "ORG_ACTIVE_CD")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    // fields for mixed anonymous keys 
    @Id
    @Column(name = "FIN_COA_CD")
    private String chartOfAccountsCode;

    /**
     * Gets the organizationCode attribute.
     * 
     * @return Returns the organizationCode
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     * Sets the organizationCode attribute.
     * 
     * @param organizationCode The organizationCode to set.
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    /**
     * Gets the organizationName attribute.
     * 
     * @return Returns the organizationName
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * Sets the organizationName attribute.
     * 
     * @param organizationName The organizationName to set.
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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
     * Gets the chartOfAccountsCode attribute.
     * 
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * Sets the chartOfAccountsCode attribute value.
     * 
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
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("organizationCode", this.organizationCode);
        return m;
    }

    /**
   

    public String getOrganizationReviewHierarchy() {

        Properties params = new Properties();
        params.put("methodToCall", "start");
        params.put("docFormKey", "");
        params.put("lookupableImplServiceName", "RuleBaseValuesLookupableImplService");
        params.put("fin_coa_cd", this.chartOfAccountsCode);
        params.put("org_cd", this.organizationCode);
        params.put("conversionFields", "");
        params.put("returnLocation", "");
        params.put("active_ind", "true");
        params.put("ruleTemplateName", "KualiOrgReviewTemplate");

        return UrlFactory.parameterizeUrl(SpringContext.getBean(KualiConfigurationService.class).getPropertyString(KFSConstants.WORKFLOW_URL_KEY) + "/Lookup.do", params);
    }

    /**
     * Implementing equals so Org will behave reasonably in a hashed datastructure.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        boolean equal = false;
        LOG.debug("Org equals");
        if (obj != null) {
            if (this == obj)
                return true;
            if (this.getClass().isAssignableFrom(obj.getClass())) {
                Organization other = (Organization) obj;
                LOG.debug("this: " + this);
                LOG.debug("other: " + other);
                if (StringUtils.equals(this.getChartOfAccountsCode(), other.getChartOfAccountsCode())) {
                    if (StringUtils.equals(this.getOrganizationCode(), other.getOrganizationCode())) {
                        equal = true;
                    }
                }
            }
        }
        return equal;
    }

    /**
     * @return Returns the code and description in format: xx - xxxxxxxxxxxxxxxx
     */
    public String getCodeAndDescription() {
        String theString = getOrganizationCode() + "-" + getOrganizationName();
        return theString;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        String hashString = getChartOfAccountsCode() + "|" + getOrganizationCode();
        return hashString.hashCode();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static final class OrganizationId implements Serializable, Comparable<OrganizationId> {

        private String chartOfAccountsCode;

        private String organizationCode;

        public String getChartOfAccountsCode() {
            return this.chartOfAccountsCode;
        }

        public void setChartOfAccountsCode(String chartOfAccountsCode) {
            this.chartOfAccountsCode = chartOfAccountsCode;
        }

        public String getOrganizationCode() {
            return this.organizationCode;
        }

        public void setOrganizationCode(String organizationCode) {
            this.organizationCode = organizationCode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("chartOfAccountsCode", this.chartOfAccountsCode).append("organizationCode", this.organizationCode).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final OrganizationId rhs = (OrganizationId) other;
            return new EqualsBuilder().append(this.chartOfAccountsCode, rhs.chartOfAccountsCode).append(this.organizationCode, rhs.organizationCode).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.chartOfAccountsCode).append(this.organizationCode).toHashCode();
        }

        @Override
        public int compareTo(OrganizationId other) {
            return new CompareToBuilder().append(this.chartOfAccountsCode, other.chartOfAccountsCode).append(this.organizationCode, other.organizationCode).toComparison();
        }
    }
}
