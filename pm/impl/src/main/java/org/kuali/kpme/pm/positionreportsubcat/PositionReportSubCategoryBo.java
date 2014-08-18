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
package org.kuali.kpme.pm.positionreportsubcat;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.pm.api.positionreportsubcat.PositionReportSubCategory;
import org.kuali.kpme.pm.api.positionreportsubcat.PositionReportSubCategoryContract;
import org.kuali.kpme.pm.positionreportcat.PositionReportCategoryBo;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "PM_PSTN_RPT_SUB_CAT_T")
public class PositionReportSubCategoryBo extends HrBusinessObject implements PositionReportSubCategoryContract {

    static class KeyFields {

        private static final String POSITION_REPORT_SUB_CAT = "positionReportSubCat";
    }

    //KPME-2273/1965 Primary Business Keys List.  
    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(KeyFields.POSITION_REPORT_SUB_CAT).build();

    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "PM_PSTN_RPT_SUB_CAT_S")
    @GeneratedValue(generator = "PM_PSTN_RPT_SUB_CAT_S")
    @Id
    @Column(name = "PM_PSTN_RPT_SUB_CAT_ID", length = 60)
    private String pmPositionReportSubCatId;

    @Column(name = "PSTN_RPT_SUB_CAT", nullable = false, length = 50)
    private String positionReportSubCat;

    @Column(name = "PSTN_RPT_CAT", nullable = false, length = 50)
    private String positionReportCat;

    @Column(name = "PSTN_RPT_TYPE", nullable = false, length = 50)
    private String positionReportType;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Transient
    private PositionReportCategoryBo prcObj;

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(KeyFields.POSITION_REPORT_SUB_CAT, this.getPositionReportSubCat()).build();
    }

    /*
	 * convert bo to immutable
	 *
	 * Can be used with ModelObjectUtils:
	 *
	 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfPositionReportSubCategoryBo, PositionReportSubCategoryBo.toImmutable);
	 */
    public static final ModelObjectUtils.Transformer<PositionReportSubCategoryBo, PositionReportSubCategory> toImmutable = new ModelObjectUtils.Transformer<PositionReportSubCategoryBo, PositionReportSubCategory>() {

        public PositionReportSubCategory transform(PositionReportSubCategoryBo input) {
            return PositionReportSubCategoryBo.to(input);
        }

        ;
    };

    /*
	 * convert immutable to bo
	 * 
	 * Can be used with ModelObjectUtils:
	 * 
	 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfPositionReportSubCategory, PositionReportSubCategoryBo.toBo);
	 */
    public static final ModelObjectUtils.Transformer<PositionReportSubCategory, PositionReportSubCategoryBo> toBo = new ModelObjectUtils.Transformer<PositionReportSubCategory, PositionReportSubCategoryBo>() {

        public PositionReportSubCategoryBo transform(PositionReportSubCategory input) {
            return PositionReportSubCategoryBo.from(input);
        }

        ;
    };

    @Override
    public String getId() {
        return getPmPositionReportSubCatId();
    }

    @Override
    public void setId(String id) {
        this.setPmPositionReportSubCatId(id);
    }

    @Override
    protected String getUniqueKey() {
        return getPositionReportSubCat() + "_" + this.getPositionReportCat() + "_" + this.getPositionReportType();
    }

    public String getPmPositionReportSubCatId() {
        return pmPositionReportSubCatId;
    }

    public void setPmPositionReportSubCatId(String pmPositionReportSubCatId) {
        this.pmPositionReportSubCatId = pmPositionReportSubCatId;
    }

    public String getPositionReportSubCat() {
        return positionReportSubCat;
    }

    public void setPositionReportSubCat(String positionReportSubCat) {
        this.positionReportSubCat = positionReportSubCat;
    }

    public String getPositionReportCat() {
        return positionReportCat;
    }

    public void setPositionReportCat(String positionReportCat) {
        this.positionReportCat = positionReportCat;
    }

    public String getPositionReportType() {
        return positionReportType;
    }

    public void setPositionReportType(String positionReportType) {
        this.positionReportType = positionReportType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PositionReportCategoryBo getPrcObj() {
        return prcObj;
    }

    public void setPrcObj(PositionReportCategoryBo prcObj) {
        this.prcObj = prcObj;
    }

    public static PositionReportSubCategoryBo from(PositionReportSubCategory im) {
        if (im == null) {
            return null;
        }
        PositionReportSubCategoryBo prsc = new PositionReportSubCategoryBo();
        prsc.setPmPositionReportSubCatId(im.getPmPositionReportSubCatId());
        prsc.setPositionReportSubCat(im.getPositionReportSubCat());
        prsc.setPositionReportCat(im.getPositionReportCat());
        prsc.setPositionReportType(im.getPositionReportType());
        prsc.setDescription(im.getDescription());
        prsc.setPrcObj(PositionReportCategoryBo.from(im.getPrcObj()));
        // finally copy over the common fields into prsc from im  
        copyCommonFields(prsc, im);
        return prsc;
    }

    public static PositionReportSubCategory to(PositionReportSubCategoryBo bo) {
        if (bo == null) {
            return null;
        }
        return PositionReportSubCategory.Builder.create(bo).build();
    }
}
