/**
 * Copyright 2004-2013 The Kuali Foundation
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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.kpme.tklm.time.rules.timecollection.TimeCollectionRule;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;

public class TimeCollectionRuleServiceImplTest extends KPMETestCase {
	
	@Test
	public void testSearchTimeCollectionRules() throws Exception {
		List<TimeCollectionRule> allResults = TkServiceLocator.getTimeCollectionRuleService().getTimeCollectionRules("admin", null, null, null, "Y", "N");
		Assert.assertEquals("Search returned the wrong number of results.", 4, allResults.size());
		
		List<TimeCollectionRule> restrictedResults = TkServiceLocator.getTimeCollectionRuleService().getTimeCollectionRules("fran", null, null, null, "Y", "N");
		Assert.assertEquals("Search returned the wrong number of results.", 0, restrictedResults.size());
	}

}