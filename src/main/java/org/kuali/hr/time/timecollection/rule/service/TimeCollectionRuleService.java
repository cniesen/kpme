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
package org.kuali.hr.time.timecollection.rule.service;

import org.kuali.hr.time.collection.rule.TimeCollectionRule;
import org.springframework.cache.annotation.Cacheable;

import java.sql.Date;
import java.util.List;

public interface TimeCollectionRuleService {
	/**
	 * Fetch TimeCollectionRule for a given dept and work area as of a particular date
	 * @param dept
	 * @param workArea
	 * @param asOfDate
	 * @return
	 */
    @Cacheable(value= TimeCollectionRule.CACHE_NAME,
            key="'dept=' + #p0" +
                "+ '|' + 'workArea=' + #p1" +
                "+ '|' + 'asOfDate=' + #p2")
	public TimeCollectionRule getTimeCollectionRule(String dept, Long workArea,Date asOfDate);

    @Cacheable(value= TimeCollectionRule.CACHE_NAME,
            key="'dept=' + #p0" +
                "+ '|' + 'workArea=' + #p1" +
                "+ '|' + 'payType=' + #p2" +
                "+ '|' + 'asOfDate=' + #p3")
	public TimeCollectionRule getTimeCollectionRule(String dept, Long workArea, String payType, Date asOfDate);

    @Cacheable(value= TimeCollectionRule.CACHE_NAME, key="'tkTimeCollectionRuleId=' + #p0")
	public TimeCollectionRule getTimeCollectionRule(String tkTimeCollectionRuleId);

    List<TimeCollectionRule> getTimeCollectionRules(String dept, String workArea, String payType, String active, String showHistory);
}
