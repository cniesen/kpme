/**
 * Copyright 2004-2015 The Kuali Foundation
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
package org.kuali.hr.time.systemlunch;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.KPMEWebTestCase;
import org.kuali.kpme.core.FunctionalTest;
import org.kuali.kpme.core.util.TKUtils;
import org.kuali.kpme.tklm.time.rules.lunch.sys.SystemLunchRule;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;

@FunctionalTest
public class SystemLunchRuleTest extends KPMEWebTestCase {
	
	SystemLunchRule systemLunchRule;
	DateTime date = new DateTime(2010, 1, 1, 12, 0, 0, 0, TKUtils.getSystemDateTimeZone());
	
	@Test
	public void testSystemLunchRuleFetch() throws Exception{
		this.systemLunchRule = TkServiceLocator.getSystemLunchRuleService().getSystemLunchRule(date.toLocalDate());
		Assert.assertTrue("System lunch rule is pulled back", this.systemLunchRule!=null);
	}
	
	/**
	 * Test if the lunch in/out button shows and if the time block is created with the correct clock action
	 */
	// KPME-3532 system lunch is always off now, so there is no need for this test
	/*
	@Test
	public void testSystemLunchRule() throws Exception {
		
		systemLunchRule = TkServiceLocator.getSystemLunchRuleService().getSystemLunchRule(date.toLocalDate());
		Assert.assertTrue("System lunch rule is pulled back", systemLunchRule!=null);

        String baseUrl = TkTestConstants.Urls.CLOCK_URL;
    	HtmlPage page = HtmlUnitUtil.gotoPageAndLogin(getWebClient(), baseUrl, false);
        Thread.sleep(3000);
//    	HtmlPage page = HtmlUnitUtil.gotoPageAnBatchJobEntryTestdLogin(TkTestConstants.Urls.CLOCK_URL);
    	Assert.assertNotNull(page);
    	
    	Map<String, Object> criteria = new LinkedHashMap<String, Object>();
    	criteria.put("selectedAssignment", new String[]{HrTestConstants.FormElementTypes.DROPDOWN, "IU-BL_2_1234_2"});
    	HtmlUnitUtil.createTempFile(page);
    	// choose the first assignment from the drop down
    	page = HtmlUnitUtil.fillOutForm(page, criteria);
    	Assert.assertNotNull(page);
        //Thread.sleep(3000);
    	// clock in
    	page = HtmlUnitUtil.clickClockInOrOutButton(page);
        Thread.sleep(3000);
    	HtmlUnitUtil.createTempFile(page);
    	Assert.assertTrue("The take lunch button didn't appear", page.asXml().contains("lunchOut"));
        Thread.sleep(3000);
    	// the lunch in button should display after clocking in
    	page = HtmlUnitUtil.clickLunchInOrOutButton(page, "LO");
        //Thread.sleep(3000);
    	Assert.assertTrue("The return from lunch button didn't appear", page.asXml().contains("lunchIn"));
    	Thread.sleep(3000);
    	Assert.assertEquals(TkConstants.LUNCH_OUT, TkServiceLocator.getClockLogService().getLastClockLog("admin").getClockAction());
    	
    	if(systemLunchRule.getShowLunchButton()) {
    		//should be in its own test case, but do not want to expect the exception for purposes of another operation encountering it.
        	HtmlForm defaultForm = HtmlUnitUtil.getDefaultForm(page);
        	try {
        		@SuppressWarnings("unused")
				HtmlSubmitInput input = defaultForm.getInputByName("clockAction");
        	} catch (Exception e) {
            	Assert.assertTrue("Clock Out button should not be present", e instanceof ElementNotFoundException);
        	}
        	
    	}
        //Thread.sleep(3000);
    	// the lunch out button should display after lunching in
    	page = HtmlUnitUtil.clickLunchInOrOutButton(page, "LI");
    	Thread.sleep(3000);
    	Assert.assertEquals(TkConstants.LUNCH_IN, TkServiceLocator.getClockLogService().getLastClockLog("admin").getClockAction());
    	
	} */
}
