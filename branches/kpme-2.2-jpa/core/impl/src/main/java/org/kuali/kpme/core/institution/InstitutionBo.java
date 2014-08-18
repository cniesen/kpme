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
package org.kuali.kpme.core.institution;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.kpme.core.api.institution.Institution;
import org.kuali.kpme.core.api.institution.InstitutionContract;
import org.kuali.kpme.core.api.paytype.PayType;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.earncode.EarnCodeBo;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "PM_INSTITUTION_T")
public class InstitutionBo extends HrBusinessObject implements InstitutionContract {

    private static final String INSTITUTION_CODE = "institutionCode";

    /**
	 * 
	 */
    private static final long serialVersionUID = -4414386560856612370L;

    //KPME-2273/1965 Primary Business Keys List.  
    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(INSTITUTION_CODE).build();

    @PortableSequenceGenerator(name = "PM_INSTITUTION_S")
    @GeneratedValue(generator = "PM_INSTITUTION_S")
    @Id
    @Column(name = "PM_INSTITUTION_ID", length = 60)
    private String pmInstitutionId;

    @Column(name = "INSTITUTION_CODE", nullable = false, length = 15)
    private String institutionCode;

    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(INSTITUTION_CODE, this.getInstitutionCode()).build();
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return pmInstitutionId;
    }

    @Override
    public void setId(String id) {
        setPmInstitutionId(id);
    }

    @Override
    protected String getUniqueKey() {
        return institutionCode + "_" + getEffectiveDate() + "_" + getTimestamp();
    }

    public String getPmInstitutionId() {
        return pmInstitutionId;
    }

    public void setPmInstitutionId(String pmInstitutionId) {
        this.pmInstitutionId = pmInstitutionId;
    }

    public static InstitutionBo from(Institution im) {
        if (im == null) {
            return null;
        }
        InstitutionBo inst = new InstitutionBo();
        inst.setPmInstitutionId(im.getPmInstitutionId());
        inst.setInstitutionCode(im.getInstitutionCode());
        inst.setDescription(im.getDescription());
        inst.setEffectiveDate(im.getEffectiveLocalDate() == null ? null : im.getEffectiveLocalDate().toDate());
        inst.setActive(im.isActive());
        if (im.getCreateTime() != null) {
            inst.setTimestamp(new Timestamp(im.getCreateTime().getMillis()));
        }
        inst.setUserPrincipalId(im.getUserPrincipalId());
        inst.setVersionNumber(im.getVersionNumber());
        inst.setObjectId(im.getObjectId());
        return inst;
    }

    public static Institution to(InstitutionBo bo) {
        if (bo == null) {
            return null;
        }
        return Institution.Builder.create(bo).build();
    }
}
