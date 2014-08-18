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
package org.kuali.kpme.pm.pstnqlfrtype;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.pm.api.pstnqlfrtype.PstnQlfrType;
import org.kuali.kpme.pm.api.pstnqlfrtype.PstnQlfrTypeContract;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "PM_PSTN_QLFR_TYP_T")
public class PstnQlfrTypeBo extends HrBusinessObject implements PstnQlfrTypeContract {

    public static final ModelObjectUtils.Transformer<PstnQlfrTypeBo, PstnQlfrType> toImmutable = new ModelObjectUtils.Transformer<PstnQlfrTypeBo, PstnQlfrType>() {

        public PstnQlfrType transform(PstnQlfrTypeBo input) {
            return PstnQlfrTypeBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<PstnQlfrType, PstnQlfrTypeBo> toBo = new ModelObjectUtils.Transformer<PstnQlfrType, PstnQlfrTypeBo>() {

        public PstnQlfrTypeBo transform(PstnQlfrType input) {
            return PstnQlfrTypeBo.from(input);
        }

        ;
    };

    private static final String TYPE_VALUE = "typeValue";

    private static final String TYPE = "type";

    private static final String CODE = "code";

    private static final long serialVersionUID = 1L;

    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(CODE).add(TYPE).add(TYPE_VALUE).build();

    @PortableSequenceGenerator(name = "PM_PSTN_QLFR_TYP_S")
    @GeneratedValue(generator = "PM_PSTN_QLFR_TYP_S")
    @Id
    @Column(name = "PM_PSTN_QLFR_TYP_ID", length = 60)
    private String pmPstnQlfrTypeId;

    @Column(name = "CODE", nullable = false, length = 50)
    private String code;

    @Column(name = "TYPE", nullable = false, length = 50)
    private String type;

    @Column(name = "DESCR", length = 100)
    private String descr;

    @Column(name = "TYPE_VL", nullable = false, length = 50)
    private String typeValue;

    // private PriorityQueue<String> selectValues; 
    @Column(name = "SELECT_VALUES", length = 100)
    private String selectValues;

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(CODE, this.getCode()).put(TYPE, this.getType()).put(TYPE_VALUE, this.getTypeValue()).build();
    }

    @Override
    public String getId() {
        return this.getPmPstnQlfrTypeId();
    }

    @Override
    public void setId(String id) {
        this.setPmPstnQlfrTypeId(id);
    }

    @Override
    protected String getUniqueKey() {
        return this.getCode() + "_" + this.getType();
    }

    public String getPmPstnQlfrTypeId() {
        return pmPstnQlfrTypeId;
    }

    public void setPmPstnQlfrTypeId(String pmPstnQlfrTypeId) {
        this.pmPstnQlfrTypeId = pmPstnQlfrTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSelectValues() {
        return selectValues;
    }

    public void setSelectValues(String selectValues) {
        this.selectValues = selectValues;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public static PstnQlfrTypeBo from(PstnQlfrType im) {
        if (im == null) {
            return null;
        }
        PstnQlfrTypeBo pstnQlfrTypeBo = new PstnQlfrTypeBo();
        pstnQlfrTypeBo.setCode(im.getCode());
        pstnQlfrTypeBo.setDescr(im.getDescr());
        pstnQlfrTypeBo.setId(im.getId());
        pstnQlfrTypeBo.setPmPstnQlfrTypeId(im.getPmPstnQlfrTypeId());
        pstnQlfrTypeBo.setSelectValues(im.getSelectValues());
        pstnQlfrTypeBo.setType(im.getType());
        pstnQlfrTypeBo.setTypeValue(im.getTypeValue());
        copyCommonFields(pstnQlfrTypeBo, im);
        return pstnQlfrTypeBo;
    }

    public static PstnQlfrType to(PstnQlfrTypeBo bo) {
        if (bo == null) {
            return null;
        }
        return PstnQlfrType.Builder.create(bo).build();
    }
}
