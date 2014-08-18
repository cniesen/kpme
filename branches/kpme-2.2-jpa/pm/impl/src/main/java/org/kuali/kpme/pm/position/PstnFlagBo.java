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
package org.kuali.kpme.pm.position;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.kuali.kpme.core.api.data.jpa.converters.StringListConverter;
import org.kuali.kpme.core.groupkey.HrGroupKeyBo;
import org.kuali.kpme.pm.api.position.PstnFlag;
import org.kuali.kpme.pm.api.position.PstnFlagContract;
import org.kuali.kpme.pm.api.positiontype.PositionType;
import org.kuali.kpme.pm.positiontype.PositionTypeBo;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "PM_PSTN_FLAG_T")
public class PstnFlagBo extends PositionDerived implements PstnFlagContract {

    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "PM_PSTN_FLAG_S")
    @GeneratedValue(generator = "PM_PSTN_FLAG_S")
    @Id
    @Column(name = "PM_FLAG_ID", length = 60)
    private String pmFlagId;

    @Column(name = "CATEGORY", nullable = false, length = 50)
    private String category;

    //private String names; 
    @Column(name = "NAMES", nullable = false, length = 100)
    @Convert(converter = StringListConverter.class)
    private List<String> names;

    // KPME-2360/2958 
    public String getPmFlagId() {
        return pmFlagId;
    }

    public void setPmFlagId(String pmFlagId) {
        this.pmFlagId = pmFlagId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        PstnFlagBo rhs = (PstnFlagBo) obj;
        return new EqualsBuilder().append(this.getPmFlagId(), rhs.getPmFlagId()).append(this.getCategory(), rhs.getCategory()).append(this.getNames(), rhs.getNames()).append(hrPositionId, rhs.getHrPositionId()).isEquals();
    }

    public static PstnFlag to(PstnFlagBo bo) {
        if (bo == null) {
            return null;
        }
        return PstnFlag.Builder.create(bo).build();
    }

    public static PstnFlagBo from(PstnFlag im) {
        if (im == null) {
            return null;
        }
        PstnFlagBo pstnFlagBo = new PstnFlagBo();
        pstnFlagBo.setCategory(im.getCategory());
        pstnFlagBo.setHrPositionId(im.getHrPositionId());
        pstnFlagBo.setNames(im.getNames());
        pstnFlagBo.setObjectId(im.getObjectId());
        pstnFlagBo.setPmFlagId(im.getPmFlagId());
        //		pstnFlagBo.setOwner(PositionBo.from(im.getOwner())); 
        pstnFlagBo.setVersionNumber(im.getVersionNumber());
        return pstnFlagBo;
    }

    public static final ModelObjectUtils.Transformer<PstnFlagBo, PstnFlag> toImmutable = new ModelObjectUtils.Transformer<PstnFlagBo, PstnFlag>() {

        public PstnFlag transform(PstnFlagBo input) {
            return PstnFlagBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<PstnFlag, PstnFlagBo> toBo = new ModelObjectUtils.Transformer<PstnFlag, PstnFlagBo>() {

        public PstnFlagBo transform(PstnFlag input) {
            return PstnFlagBo.from(input);
        }

        ;
    };

    @Override
    public String getId() {
        return this.getPmFlagId();
    }

    @Override
    public void setId(String id) {
        this.setPmFlagId(id);
    }
}
