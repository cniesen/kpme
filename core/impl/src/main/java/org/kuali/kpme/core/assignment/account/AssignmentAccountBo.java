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
package org.kuali.kpme.core.assignment.account;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.StringUtils;
import org.kuali.kpme.core.api.assignment.account.AssignmentAccount;
import org.kuali.kpme.core.api.assignment.account.AssignmentAccountContract;
import org.kuali.kpme.core.api.workarea.WorkArea;
import org.kuali.kpme.core.assignment.AssignmentBo;
import org.kuali.kpme.core.earncode.EarnCodeBo;
import org.kuali.kpme.core.kfs.coa.businessobject.*;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

@Entity
@Table(name = "TK_ASSIGN_ACCT_T")
public class AssignmentAccountBo extends PersistableBusinessObjectBase implements AssignmentAccountContract {

    private static final long serialVersionUID = 2414818440020234952L;

    public static final ModelObjectUtils.Transformer<AssignmentAccount, AssignmentAccountBo> toAssignmentAccountBo = new ModelObjectUtils.Transformer<AssignmentAccount, AssignmentAccountBo>() {

        public AssignmentAccountBo transform(AssignmentAccount input) {
            return AssignmentAccountBo.from(input);
        }

        ;
    };

    @PortableSequenceGenerator(name = "TK_ASSIGN_ACCT_S")
    @GeneratedValue(generator = "TK_ASSIGN_ACCT_S")
    @Id
    @Column(name = "TK_ASSIGN_ACCT_ID", length = 60)
    private String tkAssignAcctId;

    @Column(name = "FIN_COA_CD", length = 2)
    private String finCoaCd;

    @Column(name = "ACCOUNT_NBR", length = 7)
    private String accountNbr;

    @Column(name = "SUB_ACCT_NBR", length = 5)
    private String subAcctNbr;

    @Column(name = "FIN_OBJECT_CD", length = 4)
    private String finObjectCd;

    @Column(name = "FIN_SUB_OBJ_CD", length = 3)
    private String finSubObjCd;

    @Column(name = "PROJECT_CD", length = 10)
    private String projectCd;

    @Column(name = "ORG_REF_ID", length = 8)
    private String orgRefId;

    @Column(name = "PERCENT")
    private BigDecimal percent;

    @Column(name = "EARN_CODE", nullable = false, length = 15)
    private String earnCode;

    @Column(name = "TK_ASSIGNMENT_ID", nullable = false, length = 60)
    private String tkAssignmentId;

    @Column(name = "USER_PRINCIPAL_ID", nullable = false, length = 40)
    private String userPrincipalId;

    @Column(name = "ACTIVE", length = 1)
    @Convert(converter = BooleanYNConverter.class)
    private boolean active = true;

    @Transient
    private AssignmentBo assignmentObj;

    @Transient
    private Account accountObj;

    @Transient
    private SubAccount subAccountObj;

    @Transient
    private ObjectCode objectCodeObj;

    @Transient
    private SubObjectCode subObjectCodeObj;

    @Transient
    private ProjectCode projectCodeObj;

    @Transient
    private EarnCodeBo earnCodeObj;

    // TODO returning an empty map for the time-being, until the BK is finalized  
    /*@Override
	public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
		return new ImmutableMap.Builder<String, Object>()
				.build();
	}*/
    public AssignmentBo getAssignmentObj() {
        return assignmentObj;
    }

    public void setAssignmentObj(AssignmentBo assignmentObj) {
        this.assignmentObj = assignmentObj;
    }

    public String getFinCoaCd() {
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("accountNumber", this.accountNbr);
        fields.put("active", "true");
        Account account = (Account) KRADServiceLocatorWeb.getLegacyDataAdapter().findByPrimaryKey(Account.class, fields);
        if (account != null && !account.isClosed()) {
            this.setFinCoaCd(account.getChartOfAccountsCode());
        } else {
            this.setFinCoaCd(null);
        }
        return finCoaCd;
    }

    public void setFinCoaCd(String finCoaCd) {
        this.finCoaCd = finCoaCd;
    }

    public String getAccountNbr() {
        return accountNbr;
    }

    public void setAccountNbr(String accountNbr) {
        this.accountNbr = accountNbr;
    }

    public String getSubAcctNbr() {
        return subAcctNbr;
    }

    public void setSubAcctNbr(String subAcctNbr) {
        this.subAcctNbr = subAcctNbr;
    }

    public String getFinObjectCd() {
        return finObjectCd;
    }

    public void setFinObjectCd(String finObjectCd) {
        this.finObjectCd = finObjectCd;
    }

    public String getFinSubObjCd() {
        return finSubObjCd;
    }

    public void setFinSubObjCd(String finSubObjCd) {
        this.finSubObjCd = finSubObjCd;
    }

    public String getProjectCd() {
        return projectCd;
    }

    public void setProjectCd(String projectCd) {
        this.projectCd = projectCd;
    }

    public String getOrgRefId() {
        return orgRefId;
    }

    public void setOrgRefId(String orgRefId) {
        this.orgRefId = orgRefId;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public String getTkAssignAcctId() {
        return tkAssignAcctId;
    }

    public void setTkAssignAcctId(String tkAssignAcctId) {
        this.tkAssignAcctId = tkAssignAcctId;
    }

    public String getTkAssignmentId() {
        return tkAssignmentId;
    }

    public void setTkAssignmentId(String tkAssignmentId) {
        this.tkAssignmentId = tkAssignmentId;
    }

    public String getEarnCode() {
        return earnCode;
    }

    public void setEarnCode(String earnCode) {
        this.earnCode = earnCode;
    }

    public Account getAccountObj() {
        return accountObj;
    }

    public void setAccountObj(Account accountObj) {
        this.accountObj = accountObj;
    }

    public SubAccount getSubAccountObj() {
        return subAccountObj;
    }

    public void setSubAccountObj(SubAccount subAccountObj) {
        this.subAccountObj = subAccountObj;
    }

    public ObjectCode getObjectCodeObj() {
        return objectCodeObj;
    }

    public void setObjectCodeObj(ObjectCode objectCodeObj) {
        this.objectCodeObj = objectCodeObj;
    }

    public SubObjectCode getSubObjectCodeObj() {
        return subObjectCodeObj;
    }

    public void setSubObjectCodeObj(SubObjectCode subObjectCodeObj) {
        this.subObjectCodeObj = subObjectCodeObj;
    }

    public ProjectCode getProjectCodeObj() {
        return projectCodeObj;
    }

    public void setProjectCodeObj(ProjectCode projectCodeObj) {
        this.projectCodeObj = projectCodeObj;
    }

    public EarnCodeBo getEarnCodeObj() {
        return earnCodeObj;
    }

    public void setEarnCodeObj(EarnCodeBo earnCodeObj) {
        this.earnCodeObj = earnCodeObj;
    }

    //@Override  
    public String getUniqueKey() {
        return earnCode + "_" + accountNbr + "_" + subAcctNbr;
    }

    @Override
    public String getId() {
        return tkAssignAcctId;
    }

    public void setId(String id) {
        setTkAssignAcctId(id);
    }

    public String getUserPrincipalId() {
        return userPrincipalId;
    }

    public void setUserPrincipalId(String userPrincipalId) {
        this.userPrincipalId = userPrincipalId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static AssignmentAccountBo from(AssignmentAccount im) {
        if (im == null) {
            return null;
        }
        AssignmentAccountBo acct = new AssignmentAccountBo();
        acct.setTkAssignAcctId(im.getTkAssignAcctId());
        acct.setFinCoaCd(im.getFinCoaCd());
        acct.setAccountNbr(im.getAccountNbr());
        acct.setSubAcctNbr(im.getSubAcctNbr());
        acct.setFinObjectCd(im.getFinObjectCd());
        acct.setFinSubObjCd(im.getFinSubObjCd());
        acct.setProjectCd(im.getProjectCd());
        acct.setOrgRefId(im.getOrgRefId());
        acct.setEarnCode(im.getEarnCode());
        acct.setTkAssignmentId(im.getTkAssignmentId());
        acct.setUserPrincipalId(im.getUserPrincipalId());
        acct.setPercent(im.getPercent());
        acct.setActive(im.isActive());
        acct.setUserPrincipalId(im.getUserPrincipalId());
        acct.setVersionNumber(im.getVersionNumber());
        acct.setObjectId(im.getObjectId());
        return acct;
    }

    public static AssignmentAccount to(AssignmentAccountBo bo) {
        if (bo == null) {
            return null;
        }
        return AssignmentAccount.Builder.create(bo).build();
    }
}
