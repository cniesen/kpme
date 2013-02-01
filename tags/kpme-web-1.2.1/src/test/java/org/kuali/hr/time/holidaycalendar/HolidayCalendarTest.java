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
package org.kuali.hr.time.holidaycalendar;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.hr.time.test.HtmlUnitUtil;
import org.kuali.hr.time.test.TkTestConstants;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HolidayCalendarTest extends KPMETestCase {

	private static Long holidayGroupId = 1L;//id entered in the bootstrap SQL
	@Test
	public void testHolidayCalendarMaintenancePage() throws Exception{
		HtmlPage earnCodeLookUp = HtmlUnitUtil.gotoPageAndLogin(TkTestConstants.Urls.HOLIDAY_CALENDAR_MAINT_URL);
		earnCodeLookUp = HtmlUnitUtil.clickInputContainingText(earnCodeLookUp, "search");
		Assert.assertTrue("Page contains REG entry", earnCodeLookUp.asText().contains("REG"));
		HtmlPage maintPage = HtmlUnitUtil.clickAnchorContainingText(earnCodeLookUp, "edit",holidayGroupId.toString());
		Assert.assertTrue("Maintenance Page contains REG entry",maintPage.asText().contains("REG"));
	}

	@Test
	public void testHolidayCalendarCreateNew() throws Exception {

    	HtmlPage page = HtmlUnitUtil.gotoPageAndLogin(TkTestConstants.Urls.HOLIDAY_CALENDAR_MAINT_NEW_URL);
    	Assert.assertNotNull(page);
    	HtmlForm form = page.getFormByName("KualiForm");
    	Assert.assertNotNull("Search form was missing from page.", form);
    	HtmlInput  input  = HtmlUnitUtil.getInputContainingText(form, "methodToCall.route");
    	Assert.assertNotNull("Could not locate submit button", input);

    	setFieldValue(page, "document.documentHeader.documentDescription", "HolidayCalendar - test");
    	setFieldValue(page, "document.newMaintainableObject.holidayCalendarGroup", "TT");
        setFieldValue(page, "document.newMaintainableObject.descr", "desc");
        setFieldValue(page, "document.newMaintainableObject.add.dateEntries.holidayDescr", "desc");
        setFieldValue(page, "document.newMaintainableObject.add.dateEntries.holidayDate", "7/4/2011");
        setFieldValue(page, "document.newMaintainableObject.add.dateEntries.holidayHours", "8");
    	HtmlElement element = page.getElementByName("methodToCall.route");
        HtmlPage nextPage = element.click();
        Assert.assertTrue("Page was submitted successfully", nextPage.asText().contains("submitted"));

	}


}