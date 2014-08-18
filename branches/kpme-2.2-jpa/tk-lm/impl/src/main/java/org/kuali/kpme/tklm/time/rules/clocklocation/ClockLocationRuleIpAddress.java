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
package org.kuali.kpme.tklm.time.rules.clocklocation;

import com.google.common.collect.ImmutableMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.tklm.api.time.rules.clocklocation.ClockLocationRuleIpAddressContract;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "TK_CLOCK_LOC_RL_IP_ADDR_T")
public class ClockLocationRuleIpAddress extends HrBusinessObject implements ClockLocationRuleIpAddressContract {

    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "TK_CLOCK_LOC_RL_IP_ADDR_S")
    @GeneratedValue(generator = "TK_CLOCK_LOC_RL_IP_ADDR_S")
    @Id
    @Column(name = "TK_CLOCK_LOC_RULE_IP_ID", length = 60)
    private String tkClockLocationRuleIpId;

    @Column(name = "TK_CLOCK_LOC_RULE_ID", length = 60)
    private String tkClockLocationRuleId;

    @Column(name = "IP_ADDRESS", nullable = false, length = 15)
    private String ipAddress;

    // TODO returning an empty map for the time-being, until the BK is finalized  
    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().build();
    }

    @Override
    public String getId() {
        return this.getTkClockLocationRuleIpId();
    }

    @Override
    public void setId(String id) {
        this.setTkClockLocationRuleIpId(id);
    }

    @Override
    public String getUniqueKey() {
        String ipAddressKey = getTkClockLocationRuleIpId().toString() + "_" + getTkClockLocationRuleId().toString() + "_" + getIpAddress();
        return ipAddressKey;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getTkClockLocationRuleIpId() {
        return tkClockLocationRuleIpId;
    }

    public void setTkClockLocationRuleIpId(String tkClockLocationRuleIpId) {
        this.tkClockLocationRuleIpId = tkClockLocationRuleIpId;
    }

    public String getTkClockLocationRuleId() {
        return tkClockLocationRuleId;
    }

    public void setTkClockLocationRuleId(String tkClockLocationRuleId) {
        this.tkClockLocationRuleId = tkClockLocationRuleId;
    }
}
