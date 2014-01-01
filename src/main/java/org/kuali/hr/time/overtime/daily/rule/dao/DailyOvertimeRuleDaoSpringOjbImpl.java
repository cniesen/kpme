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
package org.kuali.hr.time.overtime.daily.rule.dao;

import java.util.Date;
import java.util.List;

import com.google.common.collect.ImmutableList;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.hr.core.util.OjbSubQueryUtil;
import org.kuali.hr.time.overtime.daily.rule.DailyOvertimeRule;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

public class DailyOvertimeRuleDaoSpringOjbImpl extends PlatformAwareDaoBaseOjb implements DailyOvertimeRuleDao {
    private static final ImmutableList<String> EQUAL_TO_FIELDS = new ImmutableList.Builder<String>()
            .add("location")
            .add("payType")
            .add("dept")
            .add("workArea")
            .build();

	@Override
	public DailyOvertimeRule findDailyOvertimeRule(String location, String paytype, String dept, Long workArea, Date asOfDate) {
		DailyOvertimeRule dailyOvertimeRule;

		Criteria root = new Criteria();
        java.sql.Date effDate = null;
        if (asOfDate != null) {
            effDate = new java.sql.Date(asOfDate.getTime());
        }
		root.addEqualTo("dept", dept);
		root.addEqualTo("workArea", workArea);
		root.addEqualTo("location", location);
		root.addEqualTo("paytype", paytype);
		root.addEqualTo("effectiveDate", OjbSubQueryUtil.getEffectiveDateSubQuery(DailyOvertimeRule.class, effDate, EQUAL_TO_FIELDS, false));
		root.addEqualTo("timestamp", OjbSubQueryUtil.getTimestampSubQuery(DailyOvertimeRule.class, EQUAL_TO_FIELDS, false));

		Criteria activeFilter = new Criteria(); // Inner Join For Activity
		activeFilter.addEqualTo("active", true);
		root.addAndCriteria(activeFilter);

		
		Query query = QueryFactory.newQuery(DailyOvertimeRule.class, root);
		dailyOvertimeRule = (DailyOvertimeRule)this.getPersistenceBrokerTemplate().getObjectByQuery(query);
		
		return dailyOvertimeRule;
	}

	@Override
	public void saveOrUpdate(DailyOvertimeRule dailyOvertimeRule) {
		this.getPersistenceBrokerTemplate().store(dailyOvertimeRule);
	}

	@Override
	public void saveOrUpdate(List<DailyOvertimeRule> dailyOvertimeRules) {
		for (DailyOvertimeRule dor : dailyOvertimeRules) {
			saveOrUpdate(dor);
		}
	}

	@Override
	public DailyOvertimeRule getDailyOvertimeRule(String tkDailyOvertimeRuleId) {
		Criteria crit = new Criteria();
		crit.addEqualTo("tkDailyOvertimeRuleId", tkDailyOvertimeRuleId);
		
		Query query = QueryFactory.newQuery(DailyOvertimeRule.class, crit);
		return (DailyOvertimeRule)this.getPersistenceBrokerTemplate().getObjectByQuery(query);
	}

}
