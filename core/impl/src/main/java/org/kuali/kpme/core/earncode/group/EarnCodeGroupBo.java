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
package org.kuali.kpme.core.earncode.group;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.kuali.kpme.core.api.earncode.group.EarnCodeGroup;
import org.kuali.kpme.core.api.earncode.group.EarnCodeGroupContract;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.earncode.group.EarnCodeGroupDefinitionBo;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "HR_EARN_CODE_GROUP_T")
public class EarnCodeGroupBo extends HrBusinessObject implements EarnCodeGroupContract {

    private static final String EARN_CODE_GROUP = "earnCodeGroup";

    //KPME-2273/1965 Primary Business Keys List.  
    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(EARN_CODE_GROUP).build();

    /*
     * convert bo to immutable
     *
 * Can be used with ModelObjectUtils:
 *
 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfEarnCodeGroupBo, EarnCodeGroupBo.toImmutable);
 */
    public static final ModelObjectUtils.Transformer<EarnCodeGroupBo, EarnCodeGroup> toImmutable = new ModelObjectUtils.Transformer<EarnCodeGroupBo, EarnCodeGroup>() {

        public EarnCodeGroup transform(EarnCodeGroupBo input) {
            return EarnCodeGroupBo.to(input);
        }

        ;
    };

    /*
 * convert immutable to bo
 *
 * Can be used with ModelObjectUtils:
 *
 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfEarnCodeGroup, EarnCodeGroupBo.toBo);
 */
    public static final ModelObjectUtils.Transformer<EarnCodeGroup, EarnCodeGroupBo> toBo = new ModelObjectUtils.Transformer<EarnCodeGroup, EarnCodeGroupBo>() {

        public EarnCodeGroupBo transform(EarnCodeGroup input) {
            return EarnCodeGroupBo.from(input);
        }

        ;
    };

    private static final long serialVersionUID = -3034933572755800531L;

    @PortableSequenceGenerator(name = "HR_EARN_CODE_GROUP_S")
    @GeneratedValue(generator = "HR_EARN_CODE_GROUP_S")
    @Id
    @Column(name = "HR_EARN_CODE_GROUP_ID", length = 60)
    private String hrEarnCodeGroupId;

    @Column(name = "EARN_CODE_GROUP", nullable = false, length = 10)
    private String earnCodeGroup;

    @Column(name = "DESCR", nullable = false, length = 30)
    private String descr;

    @Column(name = "SHOW_SUMMARY", nullable = false, length = 1)
    @Convert(converter = BooleanYNConverter.class)
    private Boolean showSummary;

    @OneToMany(targetEntity = EarnCodeGroupDefinitionBo.class, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "HR_EARN_CODE_GROUP_ID", referencedColumnName = "HR_EARN_CODE_GROUP_ID", insertable = false, updatable = false)
    private List<EarnCodeGroupDefinitionBo> earnCodeGroups = new ArrayList<EarnCodeGroupDefinitionBo>();

    @Column(name = "WARNING_TEXT", length = 100)
    private String warningText;

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(EARN_CODE_GROUP, this.getEarnCodeGroup()).build();
    }

    public List<EarnCodeGroupDefinitionBo> getEarnCodeGroups() {
        return earnCodeGroups;
    }

    public void setEarnCodeGroups(List<EarnCodeGroupDefinitionBo> earnCodeGroups) {
        this.earnCodeGroups = earnCodeGroups;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }

    public Boolean getShowSummary() {
        return showSummary;
    }

    public void setShowSummary(Boolean showSummary) {
        this.showSummary = showSummary;
    }

    @Override
    public String getUniqueKey() {
        return earnCodeGroup;
    }

    @Override
    public String getId() {
        return getHrEarnCodeGroupId();
    }

    @Override
    public void setId(String id) {
        setHrEarnCodeGroupId(id);
    }

    public String getWarningText() {
        return warningText;
    }

    public void setWarningText(String warningText) {
        this.warningText = warningText;
    }

    public String getHrEarnCodeGroupId() {
        return hrEarnCodeGroupId;
    }

    public void setHrEarnCodeGroupId(String hrEarnCodeGroupId) {
        this.hrEarnCodeGroupId = hrEarnCodeGroupId;
    }

    public String getEarnCodeGroup() {
        return earnCodeGroup;
    }

    public void setEarnCodeGroup(String earnCodeGroup) {
        this.earnCodeGroup = earnCodeGroup;
    }

    public static EarnCodeGroupBo from(EarnCodeGroup im) {
        if (im == null) {
            return null;
        }
        EarnCodeGroupBo ecg = new EarnCodeGroupBo();
        ecg.setHrEarnCodeGroupId(im.getHrEarnCodeGroupId());
        ecg.setEarnCodeGroup(im.getEarnCodeGroup());
        ecg.setDescr(im.getDescr());
        ecg.setWarningText(im.getWarningText());
        ecg.setEarnCodeGroups(ModelObjectUtils.transform(im.getEarnCodeGroups(), EarnCodeGroupDefinitionBo.toEarnCodeGroupDefinitionBo));
        copyCommonFields(ecg, im);
        return ecg;
    }

    public static EarnCodeGroup to(EarnCodeGroupBo bo) {
        if (bo == null) {
            return null;
        }
        return EarnCodeGroup.Builder.create(bo).build();
    }
}
