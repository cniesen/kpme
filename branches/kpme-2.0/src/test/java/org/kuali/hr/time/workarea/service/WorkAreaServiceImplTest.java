package org.kuali.hr.time.workarea.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.workarea.WorkArea;

public class WorkAreaServiceImplTest extends KPMETestCase {
	
	@Test
	public void testSearchWorkAreas() throws Exception {
		List<WorkArea> allResults = TkServiceLocator.getWorkAreaService().getWorkAreas("admin", null, null, null, null, null, "Y", "N");
		Assert.assertEquals("Search returned the wrong number of results.", 8, allResults.size());
		
		List<WorkArea> restrictedResults = TkServiceLocator.getWorkAreaService().getWorkAreas("testuser6", null, null, null, null, null, "Y", "N");
		Assert.assertEquals("Search returned the wrong number of results.", 2, restrictedResults.size());
	}

}