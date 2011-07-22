package org.kuali.hr.time.holidaycalendar;

import org.junit.Test;
import org.kuali.hr.time.test.HtmlUnitUtil;
import org.kuali.hr.time.test.TkTestCase;
import org.kuali.hr.time.test.TkTestConstants;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HolidayCalendarTest extends TkTestCase {
	
	private static Long holidayGroupId = 1L;//id entered in the bootstrap SQL
	final String ERROR_LOCATION_NULL = "The specified location '%' does not exist.";
	@Test
	public void testHolidayCalendarMaintenancePage() throws Exception{	
		HtmlPage earnCodeLookUp = HtmlUnitUtil.gotoPageAndLogin(TkTestConstants.Urls.HOLIDAY_CALENDAR_MAINT_URL);
		earnCodeLookUp = HtmlUnitUtil.clickInputContainingText(earnCodeLookUp, "search");
		assertTrue("Page contains REG entry", earnCodeLookUp.asText().contains("REG"));		
		HtmlPage maintPage = HtmlUnitUtil.clickAnchorContainingText(earnCodeLookUp, "edit",holidayGroupId.toString());		
		assertTrue("Maintenance Page contains REG entry",maintPage.asText().contains("REG"));
	}
	
	@Test
	public void testHolidayCalendarCreateNew() throws Exception {
		
    	HtmlPage page = HtmlUnitUtil.gotoPageAndLogin(TkTestConstants.Urls.HOLIDAY_CALENDAR_MAINT_NEW_URL);
    	assertNotNull(page);
    	HtmlForm form = page.getFormByName("KualiForm");
    	assertNotNull("Search form was missing from page.", form);
    	HtmlInput  input  = HtmlUnitUtil.getInputContainingText(form, "methodToCall.route");
    	assertNotNull("Could not locate submit button", input);
    	
    	setFieldValue(page, "document.documentHeader.documentDescription", "HolidayCalendar - test");
    	setFieldValue(page, "document.newMaintainableObject.holidayCalendarGroup", "TT");
    	setFieldValue(page, "document.newMaintainableObject.location", "%");
    	HtmlElement element = page.getElementByName("methodToCall.route");
        HtmlPage nextPage = element.click();
        assertFalse("pagedoes not contain: " + ERROR_LOCATION_NULL, nextPage.asText().contains(ERROR_LOCATION_NULL));
        
	}

	
}