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
package org.kuali.kpme.pm.classification;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.bo.HrKeyedSetBusinessObject;
import org.kuali.kpme.core.groupkey.HrGroupKeyBo;
import org.kuali.kpme.pm.api.classification.Classification;
import org.kuali.kpme.pm.api.classification.ClassificationContract;
import org.kuali.kpme.pm.classification.duty.ClassificationDutyBo;
import org.kuali.kpme.pm.classification.flag.ClassificationFlagBo;
import org.kuali.kpme.pm.classification.qual.ClassificationQualificationBo;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "PM_PSTN_CL_T")
public class ClassificationBo extends HrKeyedSetBusinessObject<ClassificationBo, ClassificationGroupKeyBo> implements ClassificationContract {

    private static final long serialVersionUID = 3012823278753071180L;

    private static final String CLASSIFICATION_TITLE = "classificationTitle";

    private static final String POSITION_CLASS = "positionClass";

    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(POSITION_CLASS).add(CLASSIFICATION_TITLE).build();

    @PortableSequenceGenerator(name = "PM_PSTN_CL_S")
    @GeneratedValue(generator = "PM_PSTN_CL_S")
    @Id
    @Column(name = "PM_PSTN_CL_ID", length = 60)
    private String pmPositionClassId;

    @Column(name = "PSTN_CL", nullable = false, length = 50)
    private String positionClass;

    @Column(name = "CL_TTL", nullable = false, length = 100)
    private String classificationTitle;

    // salary group fields  
    @Column(name = "SAL_GROUP", nullable = false, length = 50)
    private String salaryGroup;

    @Column(name = "PAY_GRADE", length = 20)
    private String payGrade;

    @Column(name = "PRCT_TM", nullable = false, length = 10)
    private BigDecimal percentTime;

    @Column(name = "BNFT_ELIG", nullable = false, length = 1)
    private String benefitsEligible;

    @Column(name = "LV_ELIG", nullable = false, length = 1)
    private String leaveEligible;

    @Column(name = "LV_PLN", length = 50)
    private String leavePlan;

    @Column(name = "PSTN_RPT_GRP", length = 50)
    private String positionReportGroup;

    @Column(name = "PSTN_TYP", nullable = false, length = 50)
    private String positionType;

    @Column(name = "POOL_ELIG", nullable = false, length = 1)
    private String poolEligible;

    @Column(name = "TNR_ELIG", nullable = false, length = 1)
    private String tenureEligible;

    @Column(name = "EXTRNL_RFRNC", length = 100)
    private String externalReference;

    @OneToMany(mappedBy = "owner")
    @JoinColumn(name = "PM_PSTN_CL_ID", referencedColumnName = "PM_PSTN_CL_ID", insertable = false, updatable = false)
    private List<ClassificationQualificationBo> qualificationList = new LinkedList<ClassificationQualificationBo>();

    @OneToMany(mappedBy = "owner")
    @JoinColumn(name = "PM_PSTN_CL_ID", referencedColumnName = "PM_PSTN_CL_ID", insertable = false, updatable = false)
    private List<ClassificationDutyBo> dutyList = new LinkedList<ClassificationDutyBo>();

    @OneToMany(mappedBy = "owner")
    @JoinColumn(name = "PM_PSTN_CL_ID", referencedColumnName = "PM_PSTN_CL_ID", insertable = false, updatable = false)
    private List<ClassificationFlagBo> flagList = new LinkedList<ClassificationFlagBo>();

    // list of position flags, need to add flag maint section to Position maint doc  
    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(POSITION_CLASS, this.getPositionClass()).put(CLASSIFICATION_TITLE, this.getClassificationTitle()).build();
    }

    @Override
    public String getId() {
        return this.getPmPositionClassId();
    }

    @Override
    public void setId(String id) {
        this.setPmPositionClassId(id);
    }

    @Override
    protected String getUniqueKey() {
        return this.getPositionClass();
    }

    public String getPositionClass() {
        return positionClass;
    }

    public void setPositionClass(String positionClass) {
        this.positionClass = positionClass;
    }

    public String getClassificationTitle() {
        return classificationTitle;
    }

    public void setClassificationTitle(String classificationTitle) {
        this.classificationTitle = classificationTitle;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getSalaryGroup() {
        return salaryGroup;
    }

    public void setSalaryGroup(String salaryGroup) {
        this.salaryGroup = salaryGroup;
    }

    public String getPayGrade() {
        return payGrade;
    }

    public void setPayGrade(String payGrade) {
        this.payGrade = payGrade;
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

    public String getPositionReportGroup() {
        return positionReportGroup;
    }

    public void setPositionReportGroup(String positionReportGroup) {
        this.positionReportGroup = positionReportGroup;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getPoolEligible() {
        return poolEligible;
    }

    public void setPoolEligible(String poolEligible) {
        this.poolEligible = poolEligible;
    }

    public String getTenureEligible() {
        return tenureEligible;
    }

    public void setTenureEligible(String tenureEligible) {
        this.tenureEligible = tenureEligible;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public List<ClassificationQualificationBo> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<ClassificationQualificationBo> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public String getPmPositionClassId() {
        return pmPositionClassId;
    }

    public void setPmPositionClassId(String pmPositionClassId) {
        this.pmPositionClassId = pmPositionClassId;
    }

    public List<ClassificationDutyBo> getDutyList() {
        return dutyList;
    }

    public void setDutyList(List<ClassificationDutyBo> dutyList) {
        this.dutyList = dutyList;
    }

    public List<ClassificationFlagBo> getFlagList() {
        return flagList;
    }

    public void setFlagList(List<ClassificationFlagBo> flagList) {
        this.flagList = flagList;
    }

    public static ClassificationBo from(Classification im) {
        if (im == null) {
            return null;
        }
        ClassificationBo classificationBo = new ClassificationBo();
        classificationBo.setPmPositionClassId(im.getPmPositionClassId());
        classificationBo.setVersionNumber(im.getVersionNumber());
        classificationBo.setObjectId(im.getObjectId());
        classificationBo.setPoolEligible(im.getPoolEligible());
        classificationBo.setPositionType(im.getPositionType());
        classificationBo.setPositionReportGroup(im.getPositionReportGroup());
        classificationBo.setLeaveEligible(im.getLeaveEligible());
        classificationBo.setBenefitsEligible(im.getBenefitsEligible());
        classificationBo.setClassificationTitle(im.getClassificationTitle());
        classificationBo.setPositionClass(im.getPositionClass());
        classificationBo.setPercentTime(im.getPercentTime());
        classificationBo.setSalaryGroup(im.getSalaryGroup());
        classificationBo.setTenureEligible(im.getTenureEligible());
        classificationBo.setExternalReference(im.getExternalReference());
        List<ClassificationQualificationBo> qualificationList = ModelObjectUtils.transform(im.getQualificationList(), ClassificationQualificationBo.toBo);
        ClassificationQualificationBo.setOwnerOfDerivedCollection(classificationBo, qualificationList);
        classificationBo.setQualificationList(qualificationList);
        List<ClassificationFlagBo> flagList = ModelObjectUtils.transform(im.getFlagList(), ClassificationFlagBo.toBo);
        ClassificationFlagBo.setOwnerOfDerivedCollection(classificationBo, flagList);
        classificationBo.setFlagList(flagList);
        List<ClassificationDutyBo> dutyList = ModelObjectUtils.transform(im.getDutyList(), ClassificationDutyBo.toBo);
        ClassificationDutyBo.setOwnerOfDerivedCollection(classificationBo, dutyList);
        classificationBo.setDutyList(dutyList);
        classificationBo.setLeavePlan(im.getLeavePlan());
        classificationBo.setPayGrade(im.getPayGrade());
        classificationBo.setId(im.getId());
        classificationBo.setEffectiveLocalDate(im.getEffectiveLocalDate());
        Set<ClassificationGroupKeyBo> effectiveKeyBoSet = ModelObjectUtils.transformSet(im.getEffectiveKeySet(), ClassificationGroupKeyBo.toBo);
        ClassificationGroupKeyBo.setOwnerOfDerivedCollection(classificationBo, effectiveKeyBoSet);
        if (effectiveKeyBoSet != null) {
            classificationBo.setEffectiveKeyList(new ArrayList<ClassificationGroupKeyBo>(effectiveKeyBoSet));
        }
        copyCommonFields(classificationBo, im);
        return classificationBo;
    }

    public static Classification to(ClassificationBo bo) {
        if (bo == null) {
            return null;
        }
        return Classification.Builder.create(bo).build();
    }

    public static final ModelObjectUtils.Transformer<ClassificationBo, Classification> toImmutable = new ModelObjectUtils.Transformer<ClassificationBo, Classification>() {

        public Classification transform(ClassificationBo input) {
            return ClassificationBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<Classification, ClassificationBo> toBo = new ModelObjectUtils.Transformer<Classification, ClassificationBo>() {

        public ClassificationBo transform(Classification input) {
            return ClassificationBo.from(input);
        }

        ;
    };
}
