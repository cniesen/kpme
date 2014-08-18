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
package org.kuali.kpme.tklm.time.rules.overtime.weekly;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.tklm.api.time.rules.overtime.weekly.WeeklyOvertimeRuleGroupContract;
import org.kuali.kpme.tklm.time.rules.overtime.weekly.WeeklyOvertimeRule;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

@Entity
@Table(name = "TK_WEEKLY_OVT_GROUP_T")
public class WeeklyOvertimeRuleGroup extends PersistableBusinessObjectBase implements WeeklyOvertimeRuleGroupContract {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Transient
    @OneToMany(targetEntity = WeeklyOvertimeRule.class, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "TK_WEEKLY_OVERTIME_GROUP_ID", referencedColumnName = "TK_WEEKLY_OVT_GROUP_ID", insertable = false, updatable = false)
    private List<WeeklyOvertimeRule> lstWeeklyOvertimeRules = new ArrayList<WeeklyOvertimeRule>();

    @Transient
    @Id
    @Column(name = "TK_WEEKLY_OVERTIME_GROUP_ID")
    private Long tkWeeklyOvertimeRuleGroupId = 1L;

    public List<WeeklyOvertimeRule> getLstWeeklyOvertimeRules() {
        return lstWeeklyOvertimeRules;
    }

    public void setLstWeeklyOvertimeRules(List<WeeklyOvertimeRule> lstWeeklyOvertimeRules) {
        this.lstWeeklyOvertimeRules = lstWeeklyOvertimeRules;
    }

    public Long getTkWeeklyOvertimeRuleGroupId() {
        return tkWeeklyOvertimeRuleGroupId;
    }

    public void setTkWeeklyOvertimeRuleGroupId(Long tkWeeklyOvertimeRuleGroupId) {
        this.tkWeeklyOvertimeRuleGroupId = tkWeeklyOvertimeRuleGroupId;
    }
}
