package org.kuali.hr.location.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.location.Location;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.hr.time.service.base.TkServiceLocator;

public class LocationServiceImplTest extends KPMETestCase {

	@Test
	public void testSearchLocations() throws Exception {
		List<Location> allResults = TkServiceLocator.getLocationService().searchLocations("admin", null, null, "Y", "N");
		Assert.assertEquals("Search returned the wrong number of results.", 2, allResults.size());
		
		List<Location> restrictedResults = TkServiceLocator.getLocationService().searchLocations("testuser6", null, null, "Y", "N");
		Assert.assertEquals("Search returned the wrong number of results.", 0, restrictedResults.size());
	}
	
}