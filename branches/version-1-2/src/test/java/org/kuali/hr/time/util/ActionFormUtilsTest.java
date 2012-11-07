/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.hr.time.util;

import java.sql.Date;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.hr.time.detail.web.ActionFormUtils;
import org.kuali.hr.time.test.TkTestUtils;
import org.kuali.hr.time.timesheet.TimesheetDocument;

public class ActionFormUtilsTest extends KPMETestCase {
	
	@Test
	public void testBuildAssignmentStyleClassMap() {
		Date aDate = new Date((new DateTime(2011, 7, 7, 0, 0, 0, 0, TKUtils.getSystemDateTimeZone())).getMillis());
		TimesheetDocument doc = TkTestUtils.populateTimesheetDocument(aDate);
		Map<String, String> aMap = ActionFormUtils.buildAssignmentStyleClassMap(doc.getTimeBlocks());
		Assert.assertEquals("Wrong number of classes in style class map", 1, aMap.size());
		Assert.assertEquals("Wrong key for class assignment0", "assignment0", aMap.get("1_1234_1"));
	}

}