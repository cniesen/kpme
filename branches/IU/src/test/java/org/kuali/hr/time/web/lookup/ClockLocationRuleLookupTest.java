package org.kuali.hr.time.web.lookup;

import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.hr.time.domain.rule.ClockLocationRule;
import org.kuali.hr.time.test.HtmlUnitUtil;
import org.kuali.hr.time.test.TestHarnessWebBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ClockLocationRuleLookupTest extends TestHarnessWebBase {

    private static BusinessObjectService boService = null;
    
    /**
     * Load some data to find for all of the tests in this test collection.
     */
    @BeforeClass
    public static void lookupSetup() {
	boService = KNSServiceLocator.getBusinessObjectService();
	clearBusinessObjects(ClockLocationRule.class);
	
	ClockLocationRule clr = new ClockLocationRule();
	clr.setActive(true);
	clr.setDepartment("12345");
	clr.setIpAddress("ipaddress");
	
	boService.save(clr);
    }
    
    @Before
    public void setUp() throws Exception {
	super.setUp();
    }
    
    
    @Test
    public void testBasicFindRecordByWebLookup() throws Exception {
	assertEquals("Database data verification failed.", 1, boService.findAll(ClockLocationRule.class).size());
	String baseUrl = HtmlUnitUtil.getBaseURL() + "/kr/lookup.do?methodToCall=start&amp;businessObjectClassName=org.kuali.hr.time.domain.rule.ClockLocationRule&amp;returnLocation=http://localhost:8080/tk-dev/portal.do&amp;hideReturnLink=true&amp;docFormKey=88888888";	
	HtmlPage page = HtmlUnitUtil.gotoPageAndLogin(baseUrl);
	assertNotNull(page);
	HtmlUnitUtil.createTempFile(page);
    }
    
    
    @SuppressWarnings("unchecked")
    public static void clearBusinessObjects(Class clazz) {
	boService.deleteMatching(clazz, new HashMap());
    }
}
