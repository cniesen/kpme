package org.kuali.hr.time.salgroup;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.hr.time.test.HtmlUnitUtil;
import org.kuali.hr.time.test.TkTestConstants;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SalGroupTest extends KPMETestCase {

	@Test
	public void testSalGroupMaintenancePage() throws Exception{
    	HtmlPage page = HtmlUnitUtil.gotoPageAndLogin(TkTestConstants.Urls.SAL_GROUP_MAINT_URL);
    	page = HtmlUnitUtil.clickInputContainingText(page, "search");
    	HtmlUnitUtil.createTempFile(page);
    	page = HtmlUnitUtil.clickAnchorContainingText(page, "edit","hrSalGroupId=10");
    	Assert.assertTrue("Test that maintenance screen rendered", page.asText().contains("A10"));
	}

}