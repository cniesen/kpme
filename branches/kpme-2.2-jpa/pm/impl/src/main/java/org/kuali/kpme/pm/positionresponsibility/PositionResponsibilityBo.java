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
package org.kuali.kpme.pm.positionresponsibility;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.kuali.kpme.pm.api.positionresponsibility.PositionResponsibility;
import org.kuali.kpme.pm.api.positionresponsibility.PositionResponsibilityContract;
import org.kuali.kpme.pm.position.PositionBo;
import org.kuali.kpme.pm.position.PositionDerived;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.location.api.campus.Campus;

@Entity
@Table(name = "PM_PSTN_RPS_T")
public class PositionResponsibilityBo extends PositionDerived implements PositionResponsibilityContract {

    private static final long serialVersionUID = -1631206606795253956L;

    /*
	 * convert bo to immutable
	 *
	 * Can be used with ModelObjectUtils:
	 *
	 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfPositionResponsibilityBo, PositionResponsibilityBo.toImmutable);
	 */
    public static final ModelObjectUtils.Transformer<PositionResponsibilityBo, PositionResponsibility> toImmutable = new ModelObjectUtils.Transformer<PositionResponsibilityBo, PositionResponsibility>() {

        public PositionResponsibility transform(PositionResponsibilityBo input) {
            return PositionResponsibilityBo.to(input);
        }

        ;
    };

    /*
	 * convert immutable to bo
	 * 
	 * Can be used with ModelObjectUtils:
	 * 
	 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfPositionResponsibility, PositionResponsibilityBo.toBo);
	 */
    public static final ModelObjectUtils.Transformer<PositionResponsibility, PositionResponsibilityBo> toBo = new ModelObjectUtils.Transformer<PositionResponsibility, PositionResponsibilityBo>() {

        public PositionResponsibilityBo transform(PositionResponsibility input) {
            return PositionResponsibilityBo.from(input);
        }

        ;
    };

    @PortableSequenceGenerator(name = "PSTN_RPS_OPT_S")
    @GeneratedValue(generator = "PSTN_RPS_OPT_S")
    @Id
    @Column(name = "PM_PSTN_RPS_ID", length = 60)
    private String positionResponsibilityId;

    @Column(name = "PM_RPS_OPTION", nullable = false, length = 20)
    private String positionResponsibilityOption;

    @Column(name = "PRCT_TM", length = 10)
    private BigDecimal percentTime;

    @Transient
    private Campus campusObj;

    public String getPositionResponsibilityId() {
        return positionResponsibilityId;
    }

    public void setPositionResponsibilityId(String positionResponsibilityId) {
        this.positionResponsibilityId = positionResponsibilityId;
    }

    public String getPositionResponsibilityOption() {
        return positionResponsibilityOption;
    }

    public void setPositionResponsibilityOption(String positionResponsibilityOption) {
        this.positionResponsibilityOption = positionResponsibilityOption;
    }

    public BigDecimal getPercentTime() {
        return percentTime;
    }

    public void setPercentTime(BigDecimal percentTime) {
        this.percentTime = percentTime;
    }

    public Campus getCampusObj() {
        return campusObj;
    }

    public void setCampusObj(Campus campusObj) {
        this.campusObj = campusObj;
    }

    public String getId() {
        return this.getPositionResponsibilityId();
    }

    public void setId(String id) {
        this.setPositionResponsibilityId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        PositionResponsibilityBo rhs = (PositionResponsibilityBo) obj;
        return new EqualsBuilder().append(positionResponsibilityId, rhs.getPositionResponsibilityId()).append(positionResponsibilityOption, rhs.getPositionResponsibilityOption()).append(percentTime, rhs.getPercentTime()).append(hrPositionId, rhs.getHrPositionId()).isEquals();
    }

    public static PositionResponsibilityBo from(PositionResponsibility im) {
        if (im == null) {
            return null;
        }
        PositionResponsibilityBo pr = new PositionResponsibilityBo();
        pr.setCampusObj(im.getCampusObj());
        pr.setPositionResponsibilityOption(im.getPositionResponsibilityOption());
        pr.setPositionResponsibilityId(im.getPositionResponsibilityId());
        pr.setPercentTime(im.getPercentTime());
        pr.setVersionNumber(im.getVersionNumber());
        pr.setObjectId(im.getObjectId());
        pr.setHrPositionId(im.getHrPositionId());
        return pr;
    }

    public static PositionResponsibility to(PositionResponsibilityBo bo) {
        if (bo == null) {
            return null;
        }
        return PositionResponsibility.Builder.create(bo).build();
    }
}
