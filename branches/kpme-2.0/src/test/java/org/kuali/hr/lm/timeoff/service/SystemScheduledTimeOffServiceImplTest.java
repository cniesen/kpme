package org.kuali.hr.lm.timeoff.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.lm.timeoff.SystemScheduledTimeOff;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.hr.time.service.base.TkServiceLocator;

public class SystemScheduledTimeOffServiceImplTest extends KPMETestCase {
	
	@Test
	public void testSearchSystemScheduledTimeOffs() throws Exception {
		List<SystemScheduledTimeOff> allResults = TkServiceLocator.getSysSchTimeOffService().getSystemScheduledTimeOffs("admin", null, null, null, null, null, null, null, "Y", "N");
		Assert.assertEquals("Search returned the wrong number of results.", 2, allResults.size());
		
		List<SystemScheduledTimeOff> restrictedResults = TkServiceLocator.getSysSchTimeOffService().getSystemScheduledTimeOffs("testuser6", null, null, null, null, null, null, null, "Y", "N");
		Assert.assertEquals("Search returned the wrong number of results.", 0, restrictedResults.size());
	}

}