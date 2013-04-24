package org.kuali.hr.time.timecollection.rule.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.hr.time.collection.rule.TimeCollectionRule;
import org.kuali.hr.time.service.base.TkServiceLocator;

public class TimeCollectionRuleServiceImplTest extends KPMETestCase {
	
	@Test
	public void testSearchTimeCollectionRules() throws Exception {
		List<TimeCollectionRule> allResults = TkServiceLocator.getTimeCollectionRuleService().getTimeCollectionRules("admin", null, null, null, "Y");
		Assert.assertEquals("Search returned the wrong number of results.", 4, allResults.size());
		
		List<TimeCollectionRule> restrictedResults = TkServiceLocator.getTimeCollectionRuleService().getTimeCollectionRules("fran", null, null, null, "Y");
		Assert.assertEquals("Search returned the wrong number of results.", 0, restrictedResults.size());
	}

}