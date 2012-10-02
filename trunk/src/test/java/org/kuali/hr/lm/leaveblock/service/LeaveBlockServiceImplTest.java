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
package org.kuali.hr.lm.leaveblock.service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.hr.lm.LMConstants;
import org.kuali.hr.lm.leaveblock.LeaveBlock;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKUtils;

public class LeaveBlockServiceImplTest extends KPMETestCase {
	
	private String TEST_USER = "admin";
	private LeaveBlockService leaveBlockService;
	
	@Before
	public void setUp() throws Exception{
		super.setUp();
		leaveBlockService = TkServiceLocator.getLeaveBlockService();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetLeaveBlock() {
		LeaveBlock leaveBlock = leaveBlockService.getLeaveBlock("1000");
		Assert.assertNotNull("Leave Block not found ", leaveBlock);
	}
	
	@Test
	public void testGetLeaveBlocks(){
		// 03/01/2012 to 03/02/2012
		Calendar cal = Calendar.getInstance();
		cal.setTime(TKUtils.getTimelessDate(null));
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DATE, 1);
		
		Date beginDate = cal.getTime();
		cal.add(Calendar.DATE,1);
		Date endDate = cal.getTime();
		List<LeaveBlock> leaveBlocks = leaveBlockService.getLeaveBlocks(TEST_USER, beginDate, endDate);
		Assert.assertNotNull("Leave blocks not found for user ", leaveBlocks);
		Assert.assertTrue("There should be 2 leave blocks, not " + leaveBlocks.size(), leaveBlocks.size()== 2);
	}
	
	@Test
	public void testGetLeaveBlocksForDocumentId() {
		List<LeaveBlock> leaveBlocks = leaveBlockService.getLeaveBlocksForDocumentId("12546");
		Assert.assertNotNull("Leave Blocks not foudn for document", leaveBlocks);
	}
	
	@Test
	public void testGetLeaveBlocksByLeaveRequestStatus(){
		String requestStatus = LMConstants.REQUEST_STATUS.PLANNED;
		Date currentDate = TKUtils.getTimelessDate(new Date());
		List<LeaveBlock> leaveBlocks = leaveBlockService.getLeaveBlocks(TEST_USER, requestStatus, currentDate);
		Assert.assertNotNull("Leave Blocks not found of Request status", leaveBlocks);
	}
	@Test
	public void testGetLeaveBlocksForLeaveDate(){
		Date leaveDate = TKUtils.getTimelessDate(new Date());
		List<LeaveBlock> leaveBlocks = leaveBlockService.getLeaveBlocksForDate(TEST_USER, leaveDate);
		Assert.assertNotNull("Leave Blocks not found of Request status", leaveBlocks);
	}
	
	@Test
	public void testCalculateAccrualBalance() {
		Calendar leaveDate = Calendar.getInstance();
		leaveDate.setTime(TKUtils.getTimelessDate(null));
		leaveDate.set(Calendar.MONTH, 2);
		leaveDate.set(Calendar.DATE, 2);
		Double sum = leaveBlockService.calculateAccrualBalance(leaveDate.getTime(), "3000", "admin");
	}
	
	@Test
	public void testGetLeaveblocksForTimesheet() {
		// 03/01/2012 to 03/06/2012
		Calendar cal = Calendar.getInstance();
		cal.setTime(TKUtils.getTimelessDate(null));
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DATE, 1);
		
		Date beginDate = cal.getTime();
		cal.add(Calendar.DATE,5);
		Date endDate = cal.getTime();
		List<LeaveBlock> leaveBlocks = leaveBlockService.getLeaveBlocksForTimesheet(TEST_USER, beginDate, endDate);
		Assert.assertNotNull("Leave blocks not found for user ", leaveBlocks);
		Assert.assertTrue("There should be 3 leave blocks, not " + leaveBlocks.size(), leaveBlocks.size()== 3);
	}
	

}
