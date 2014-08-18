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
package org.kuali.kpme.tklm.time.rules.graceperiod;

import com.google.common.collect.ImmutableMap;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.tklm.api.common.TkConstants;
import org.kuali.kpme.tklm.api.time.rules.graceperiod.GracePeriodRuleContract;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "TK_GRACE_PERIOD_RL_T")
public class GracePeriodRule extends HrBusinessObject implements GracePeriodRuleContract {

    private static final long serialVersionUID = 2756221187837436165L;

    public static final String CACHE_NAME = TkConstants.Namespace.NAMESPACE_PREFIX + "GracePeriodRule";

    @PortableSequenceGenerator(name = "TK_GRACE_PERIOD_RL_S")
    @GeneratedValue(generator = "TK_GRACE_PERIOD_RL_S")
    @Id
    @Column(name = "TK_GRACE_PERIOD_RULE_ID", length = 60)
    private String tkGracePeriodRuleId;

    @Column(name = "HOUR_FACTOR", nullable = false, precision = 2)
    private BigDecimal hourFactor;

    @Transient
    private boolean history;

    // KPME-2543 
    // TODO returning an empty map for the time-being, until the BK is finalized 
    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().build();
    }

    public BigDecimal getHourFactor() {
        return hourFactor;
    }

    public void setHourFactor(BigDecimal hourFactor) {
        this.hourFactor = hourFactor;
    }

    public String getTkGracePeriodRuleId() {
        return tkGracePeriodRuleId;
    }

    public void setTkGracePeriodRuleId(String tkGracePeriodRuleId) {
        this.tkGracePeriodRuleId = tkGracePeriodRuleId;
    }

    @Override
    public String getUniqueKey() {
        return hourFactor + "";
    }

    @Override
    public String getId() {
        return getTkGracePeriodRuleId();
    }

    @Override
    public void setId(String id) {
        setTkGracePeriodRuleId(id);
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }
}
