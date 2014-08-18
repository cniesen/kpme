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
package org.kuali.kpme.core.departmentaffiliation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.kpme.core.api.departmentaffiliation.DepartmentAffiliation;
import org.kuali.kpme.core.api.departmentaffiliation.DepartmentAffiliationContract;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "HR_DEPT_AFFL_T")
public class DepartmentAffiliationBo extends HrBusinessObject implements DepartmentAffiliationContract {

    private static final String DEPT_AFFL_TYPE = "deptAfflType";

    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(DEPT_AFFL_TYPE).build();

    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "HR_DEPT_AFFL_S")
    @GeneratedValue(generator = "HR_DEPT_AFFL_S")
    @Id
    @Column(name = "HR_DEPT_AFFL_ID", length = 60)
    private String hrDeptAfflId;

    @Column(name = "DEPT_AFFL_TYP", nullable = false, length = 60)
    private String deptAfflType;

    @Column(name = "PRIMARY_INDICATOR", nullable = false, length = 1)
    @Convert(converter = BooleanYNConverter.class)
    private boolean primaryIndicator;

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(DEPT_AFFL_TYPE, this.getDeptAfflType()).build();
    }

    /*
	 * convert bo to immutable
	 *
	 * Can be used with ModelObjectUtils:
	 *
	 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfDepartmentAffiliationBo, DepartmentAffiliationBo.toImmutable);
	 */
    public static final ModelObjectUtils.Transformer<DepartmentAffiliationBo, DepartmentAffiliation> toImmutable = new ModelObjectUtils.Transformer<DepartmentAffiliationBo, DepartmentAffiliation>() {

        public DepartmentAffiliation transform(DepartmentAffiliationBo input) {
            return DepartmentAffiliationBo.to(input);
        }

        ;
    };

    /*
	 * convert immutable to bo
	 * 
	 * Can be used with ModelObjectUtils:
	 * 
	 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfDepartmentAffiliation, DepartmentAffiliationBo.toBo);
	 */
    public static final ModelObjectUtils.Transformer<DepartmentAffiliation, DepartmentAffiliationBo> toBo = new ModelObjectUtils.Transformer<DepartmentAffiliation, DepartmentAffiliationBo>() {

        public DepartmentAffiliationBo transform(DepartmentAffiliation input) {
            return DepartmentAffiliationBo.from(input);
        }

        ;
    };

    @Override
    public String getId() {
        return this.getHrDeptAfflId();
    }

    @Override
    public void setId(String id) {
        setHrDeptAfflId(id);
    }

    @Override
    protected String getUniqueKey() {
        return getDeptAfflType();
    }

    /**
	 * @return the hrDeptAfflId
	 */
    public String getHrDeptAfflId() {
        return hrDeptAfflId;
    }

    /**
	 * @param hrDeptAfflId the hrDeptAfflId to set
	 */
    public void setHrDeptAfflId(String hrDeptAfflId) {
        this.hrDeptAfflId = hrDeptAfflId;
    }

    /**
	 * @return the deptAfflType
	 */
    public String getDeptAfflType() {
        return deptAfflType;
    }

    /**
	 * @param deptAfflType the deptAfflType to set
	 */
    public void setDeptAfflType(String deptAfflType) {
        this.deptAfflType = deptAfflType;
    }

    /**
	 * @return the primaryIndicator
	 */
    public boolean isPrimaryIndicator() {
        return primaryIndicator;
    }

    /**
	 * @param primaryIndicator the primaryIndicator to set
	 */
    public void setPrimaryIndicator(boolean primaryIndicator) {
        this.primaryIndicator = primaryIndicator;
    }

    public static DepartmentAffiliationBo from(DepartmentAffiliation im) {
        if (im == null) {
            return null;
        }
        DepartmentAffiliationBo da = new DepartmentAffiliationBo();
        da.setHrDeptAfflId(im.getHrDeptAfflId());
        da.setDeptAfflType(im.getDeptAfflType());
        da.setPrimaryIndicator(im.isPrimaryIndicator());
        // finally copy over the common fields into da from im 
        copyCommonFields(da, im);
        return da;
    }

    public static DepartmentAffiliation to(DepartmentAffiliationBo bo) {
        if (bo == null) {
            return null;
        }
        return DepartmentAffiliation.Builder.create(bo).build();
    }
}
