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
package org.kuali.kpme.tklm.time.rules.lunch.sys;

import com.google.common.collect.ImmutableMap;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.tklm.api.common.TkConstants;
import org.kuali.kpme.tklm.api.time.rules.lunch.sys.SystemLunchRuleContract;
import org.kuali.kpme.tklm.time.rules.TkRule;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "TK_SYSTEM_LUNCH_RL_T")
public class SystemLunchRule extends TkRule implements SystemLunchRuleContract {

    public static final String CACHE_NAME = TkConstants.Namespace.NAMESPACE_PREFIX + "SystemLunchRule";

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "TK_SYSTEM_LUNCH_RL_S")
    @GeneratedValue(generator = "TK_SYSTEM_LUNCH_RL_S")
    @Id
    @Column(name = "TK_SYSTEM_LUNCH_RL_ID", length = 60)
    private String tkSystemLunchRuleId;

    @Column(name = "SHOW_LUNCH_BUTTON", nullable = false, length = 1)
    @Convert(converter = BooleanYNConverter.class)
    private Boolean showLunchButton = false;

    @Transient
    private boolean history;

    // TODO returning an empty map for the time-being, until the BK is finalized  
    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().build();
    }

    public String getTkSystemLunchRuleId() {
        return tkSystemLunchRuleId;
    }

    public void setTkSystemLunchRuleId(String tkSystemLunchRuleId) {
        this.tkSystemLunchRuleId = tkSystemLunchRuleId;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    public Boolean getShowLunchButton() {
        return showLunchButton;
    }

    public void setShowLunchButton(Boolean showLunchButton) {
        this.showLunchButton = showLunchButton;
    }

    @Override
    public String getUniqueKey() {
        return "";
    }

    @Override
    public String getId() {
        return getTkSystemLunchRuleId();
    }

    @Override
    public void setId(String id) {
        setTkSystemLunchRuleId(id);
    }
}
