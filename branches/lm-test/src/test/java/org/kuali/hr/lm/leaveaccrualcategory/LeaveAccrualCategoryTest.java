package org.kuali.hr.lm.leaveaccrualcategory;

import java.sql.Date;

import org.junit.Test;
import org.kuali.hr.lm.leaveaccrualcategory.LeaveAccrualCategory;
import org.kuali.hr.time.test.TkTestCase;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class LeaveAccrualCategoryTest extends TkTestCase {
	

	@Override
	public void setUp() throws Exception {
		super.setUp();
		LeaveAccrualCategory leaveAccrualCategory = new LeaveAccrualCategory();
		leaveAccrualCategory.setLmLeaveAccrualCategoryId(1L);
		leaveAccrualCategory.setAccrualCategory("test");
		leaveAccrualCategory.setDescription("Monthly");
		leaveAccrualCategory.setEffectiveDate(new Date(System.currentTimeMillis()));
		leaveAccrualCategory.setLeavePlan("testLeavePlan1");
		leaveAccrualCategory.setAccrualEarnInterval("M");
		leaveAccrualCategory.setProration("O");
		leaveAccrualCategory.setShowOnGrid(true);
		
		KNSServiceLocator.getBusinessObjectService().save(leaveAccrualCategory);
	}

	@Test
	public void testFetchLeaveAccrualCategory() throws Exception {

		LeaveAccrualCategory leaveAccrualCategory = (LeaveAccrualCategory) KNSServiceLocator
				.getBusinessObjectService().findBySinglePrimaryKey(
						LeaveAccrualCategory.class, 1L);
		
		assertTrue(leaveAccrualCategory.getAccrualCategory().equals("test"));

	}

	@Override
	public void tearDown() throws Exception {

		LeaveAccrualCategory leaveAccrualCategory = (LeaveAccrualCategory) KNSServiceLocator
				.getBusinessObjectService().findBySinglePrimaryKey(
						LeaveAccrualCategory.class, 1L);

		KNSServiceLocator.getBusinessObjectService().delete(leaveAccrualCategory);
		super.tearDown();
	}


}
