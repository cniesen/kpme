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
package org.kuali.hr.core.kfs;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.hr.KPMEWebTestCase;
import org.kuali.hr.util.HtmlUnitUtil;
import org.kuali.kpme.core.kfs.coa.businessobject.SubObjectCode;
import org.kuali.kpme.core.util.HrTestConstants;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SubObjectCodeMaintTest extends KPMEWebTestCase {

	private String newUrl;
	private String lookupUrl;
	private Map<String,String> requiredFields;

	@Override
	public void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		before();
	}

	@Override
	public void tearDown() throws Exception {
		requiredFields.clear();
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	@Test
	public void dummyTest() throws Exception {
		Assert.assertNull(null);
	}
	
	private void before() {
		
		newUrl = HrTestConstants.Urls.SUB_OBJECT_CODE_MAINT_NEW_URL;
		lookupUrl = HrTestConstants.Urls.SUB_OBJECT_CODE_MAINT_URL;
		
		requiredFields = new HashMap<String,String>();
		requiredFields.put("universityFiscalYear", "University Fiscal Year (Year) is a required field.");
		requiredFields.put("chartOfAccountsCode", "Chart Code (Chart) is a required field.");
		requiredFields.put("accountNumber", "Account Number (Account Number) is a required field.");
		requiredFields.put("financialObjectCode", "Object Code (Object) is a required field.");
		requiredFields.put("financialSubObjectCode", "Sub-Object Code (Sub-Object) is a required field.");
		requiredFields.put("financialSubObjectCodeName", "Sub-Object Code Name (SubObjCodeName) is a required field.");
		requiredFields.put("financialSubObjectCdShortNm", "Sub-Object Code Short Name (SubObjCodeShortName) is a required field.");
	}
	
	@Test
	public void testRequiredFields() throws Exception {
		HtmlPage maintPage = HtmlUnitUtil.gotoPageAndLogin(getWebClient(), newUrl);
		assertNotNull("maintenance page is null", maintPage);
		
		HtmlInput docDescription = HtmlUnitUtil.getInputContainingText(maintPage, "* Document Description");
		assertNotNull("maintenance page does not contain document description", docDescription);
		
		docDescription.setValueAttribute("testing submission");
		
		HtmlPage resultPage = HtmlUnitUtil.clickInputContainingText(maintPage, "submit");
		assertNotNull("no result page returned after submit", resultPage);
		
		String resultPageAsText = resultPage.asText();
		for(Entry<String,String> requiredField : requiredFields.entrySet()) {
			assertTrue("page does not contain error message for required field: '" + requiredField.getKey() + "'",
					resultPageAsText.contains(requiredField.getValue()));
		}
	}
	
	@Test
	public void testLookup() throws Exception {
		HtmlPage lookupPage = HtmlUnitUtil.gotoPageAndLogin(getWebClient(), lookupUrl);
		assertNotNull("lookup page is null", lookupPage);
		
		lookupPage = HtmlUnitUtil.clickInputContainingText(lookupPage, "search");
		assertNotNull("lookup result page is null", lookupPage);
		
	}	

}
