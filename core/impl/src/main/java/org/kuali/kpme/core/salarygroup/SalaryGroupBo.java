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
package org.kuali.kpme.core.salarygroup;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.core.api.salarygroup.SalaryGroup;
import org.kuali.kpme.core.api.salarygroup.SalaryGroupContract;
import org.kuali.kpme.core.bo.HrKeyedSetBusinessObject;
import org.kuali.kpme.core.institution.InstitutionBo;
import org.kuali.kpme.core.leaveplan.LeavePlanBo;
import org.kuali.kpme.core.location.LocationBo;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "HR_SAL_GROUP_T")
public class SalaryGroupBo extends HrKeyedSetBusinessObject<SalaryGroupBo, SalaryGroupKeyBo> implements SalaryGroupContract {

    private static final String HR_SAL_GROUP = "hrSalGroup";

    private static final long serialVersionUID = 8169672203236887348L;

    public static final String CACHE_NAME = HrConstants.CacheNamespace.NAMESPACE_PREFIX + "SalaryGroup";

    //KPME-2273/1965 Primary Business Keys List.	  
    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(HR_SAL_GROUP).build();

    @PortableSequenceGenerator(name = "HR_SAL_GROUP_S")
    @GeneratedValue(generator = "HR_SAL_GROUP_S")
    @Id
    @Column(name = "HR_SAL_GROUP_ID", length = 60)
    private String hrSalGroupId;

    @Column(name = "HR_SAL_GROUP", nullable = false, length = 10)
    private String hrSalGroup;

    @Column(name = "DESCR", nullable = false, length = 30)
    private String descr;

    @Transient
    private boolean history;

    // fields added to position management  
    @Column(name = "INSTITUTION", length = 15)
    private String institution;

    @Column(name = "LOCATION", length = 15)
    private String location;

    @Column(name = "PRCT_TM", length = 10)
    private BigDecimal percentTime;

    @Column(name = "BNFT_ELIG", nullable = false, length = 1)
    private String benefitsEligible;

    @Column(name = "LV_ELIG", nullable = false, length = 1)
    private String leaveEligible;

    @Column(name = "LV_PLN", length = 15)
    private String leavePlan;

    //	relationship objects  
    @Transient
    private transient InstitutionBo institutionObj;

    @Transient
    private transient LocationBo locationObj;

    @Transient
    private transient LeavePlanBo leavePlanObj;

    public InstitutionBo getInstitutionObj() {
        return institutionObj;
    }

    public void setInstitutionObj(InstitutionBo institutionObj) {
        this.institutionObj = institutionObj;
    }

    public LocationBo getLocationObj() {
        return locationObj;
    }

    public void setLocationObj(LocationBo locationObj) {
        this.locationObj = locationObj;
    }

    public LeavePlanBo getLeavePlanObj() {
        return leavePlanObj;
    }

    public void setLeavePlanObj(LeavePlanBo leavePlanObj) {
        this.leavePlanObj = leavePlanObj;
    }

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(HR_SAL_GROUP, this.getHrSalGroup()).build();
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    public String getHrSalGroupId() {
        return hrSalGroupId;
    }

    public void setHrSalGroupId(String hrSalGroupId) {
        this.hrSalGroupId = hrSalGroupId;
    }

    public String getHrSalGroup() {
        return hrSalGroup;
    }

    public void setHrSalGroup(String hrSalGroup) {
        this.hrSalGroup = hrSalGroup;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String getUniqueKey() {
        return getHrSalGroup() + "_" + getInstitution() + "_" + getLocation();
    }

    @Override
    public String getId() {
        return getHrSalGroupId();
    }

    @Override
    public void setId(String id) {
        setHrSalGroupId(id);
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public BigDecimal getPercentTime() {
        return percentTime;
    }

    public void setPercentTime(BigDecimal percentTime) {
        this.percentTime = percentTime;
    }

    public String getBenefitsEligible() {
        return benefitsEligible;
    }

    public void setBenefitsEligible(String benefitsEligible) {
        this.benefitsEligible = benefitsEligible;
    }

    public String getLeaveEligible() {
        return leaveEligible;
    }

    public void setLeaveEligible(String leaveEligible) {
        this.leaveEligible = leaveEligible;
    }

    public String getLeavePlan() {
        return leavePlan;
    }

    public void setLeavePlan(String leavePlan) {
        this.leavePlan = leavePlan;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static SalaryGroupBo from(SalaryGroup im) {
        if (im == null) {
            return null;
        }
        SalaryGroupBo sg = new SalaryGroupBo();
        sg.setHrSalGroupId(im.getHrSalGroupId());
        sg.setHrSalGroup(im.getHrSalGroup());
        sg.setDescr(im.getDescr());
        /*sg.setInstitution(im.getInstitution());
        sg.setLocation(im.getLocation());*/
        sg.setPercentTime(im.getPercentTime());
        sg.setBenefitsEligible(im.getBenefitsEligible());
        sg.setLeaveEligible(im.getLeaveEligible());
        sg.setLeavePlan(im.getLeavePlan());
        Set<SalaryGroupKeyBo> effectiveKeyBoSet = ModelObjectUtils.transformSet(im.getEffectiveKeySet(), SalaryGroupKeyBo.toBo);
        // set prg as the owner for each of the derived effective key objects in the set  
        SalaryGroupKeyBo.setOwnerOfDerivedCollection(sg, effectiveKeyBoSet);
        // set the key list, constructed from the key set  
        if (effectiveKeyBoSet != null) {
            sg.setEffectiveKeyList(new ArrayList<SalaryGroupKeyBo>(effectiveKeyBoSet));
        }
        //        sg.setEffectiveDate(im.getEffectiveLocalDate() == null ? null : im.getEffectiveLocalDate().toDate());  
        //        sg.setActive(im.isActive());  
        //        if (im.getCreateTime() != null) {  
        //            sg.setTimestamp(new Timestamp(im.getCreateTime().getMillis()));  
        //        }  
        //        sg.setUserPrincipalId(im.getUserPrincipalId());  
        //        sg.setVersionNumber(im.getVersionNumber());  
        //        sg.setObjectId(im.getObjectId());  
        copyCommonFields(sg, im);
        return sg;
    }

    public static SalaryGroup to(SalaryGroupBo bo) {
        if (bo == null) {
            return null;
        }
        return SalaryGroup.Builder.create(bo).build();
    }
}
