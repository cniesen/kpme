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

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.kuali.kpme.pm.api.position.PositionDuty;
import org.kuali.kpme.pm.api.position.PositionDutyContract;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "PM_PSTN_DUTY_T")
public class PositionDutyBo extends PositionDerived implements PositionDutyContract {

    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "PM_PSTN_DUTY_S")
    @GeneratedValue(generator = "PM_PSTN_DUTY_S")
    @Id
    @Column(name = "PM_DUTY_ID", length = 60)
    private String pmDutyId;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "DESCR", nullable = false, length = 300)
    private String description;

    @Column(name = "PRCTG", length = 10)
    private BigDecimal percentage;

    public String getPmDutyId() {
        return pmDutyId;
    }

    public void setPmDutyId(String pmDutyId) {
        this.pmDutyId = pmDutyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        PositionDutyBo rhs = (PositionDutyBo) obj;
        return new EqualsBuilder().append(pmDutyId, rhs.getPmDutyId()).append(name, rhs.getName()).append(description, rhs.getDescription()).append(percentage, rhs.getPercentage()).append(hrPositionId, rhs.getHrPositionId()).isEquals();
    }

    @Override
    public String getId() {
        return this.getPmDutyId();
    }

    @Override
    public void setId(String id) {
        this.setPmDutyId(id);
    }

    public static PositionDutyBo from(PositionDuty im) {
        if (im == null) {
            return null;
        }
        PositionDutyBo positionDutyBo = new PositionDutyBo();
        positionDutyBo.setDescription(im.getDescription());
        positionDutyBo.setHrPositionId(im.getHrPositionId());
        positionDutyBo.setName(im.getName());
        positionDutyBo.setObjectId(im.getObjectId());
        positionDutyBo.setPercentage(im.getPercentage());
        positionDutyBo.setPmDutyId(im.getPmDutyId());
        positionDutyBo.setVersionNumber(im.getVersionNumber());
        return positionDutyBo;
    }

    public static PositionDuty to(PositionDutyBo bo) {
        if (bo == null) {
            return null;
        }
        return PositionDuty.Builder.create(bo).build();
    }

    public static final ModelObjectUtils.Transformer<PositionDutyBo, PositionDuty> toImmutable = new ModelObjectUtils.Transformer<PositionDutyBo, PositionDuty>() {

        public PositionDuty transform(PositionDutyBo input) {
            return PositionDutyBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<PositionDuty, PositionDutyBo> toBo = new ModelObjectUtils.Transformer<PositionDuty, PositionDutyBo>() {

        public PositionDutyBo transform(PositionDuty input) {
            return PositionDutyBo.from(input);
        }

        ;
    };
}
