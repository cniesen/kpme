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
package org.kuali.kpme.pm.pstnqlfctnvl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.kpme.pm.api.pstnqlfctnvl.PositionQualificationValueContract;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "PM_PSTN_QLFCTN_VL_T")
public class PositionQualificationValue extends PersistableBusinessObjectBase implements PositionQualificationValueContract {

    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "PM_PSTN_QLFCTN_VL_S")
    @GeneratedValue(generator = "PM_PSTN_QLFCTN_VL_S")
    @Id
    @Column(name = "PM_PSTN_QLFCTN_VL_ID", length = 60)
    private String pmPstnQlfctnVlId;

    @Column(name = "VL_NM", nullable = false, length = 20)
    private String vlName;

    public String getVlName() {
        return vlName;
    }

    public void setVlName(String vlName) {
        this.vlName = vlName;
    }

    public String getPmPstnQlfctnVlId() {
        return pmPstnQlfctnVlId;
    }

    public void setPmPstnQlfctnVlId(String pmPstnQlfctnVlId) {
        this.pmPstnQlfctnVlId = pmPstnQlfctnVlId;
    }
}
