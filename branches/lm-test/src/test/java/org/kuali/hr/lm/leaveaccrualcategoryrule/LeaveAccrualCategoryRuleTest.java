package org.kuali.hr.lm.leaveaccrualcategoryrule;

import org.junit.Test;
import org.kuali.hr.time.test.TkTestCase;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class LeaveAccrualCategoryRuleTest extends TkTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		LeaveAccrualCategoryRule leaveAccrualCategoryRule = new LeaveAccrualCategoryRule();
		leaveAccrualCategoryRule.setLmLeaveAccrualCategoryRuleId(1L);
		leaveAccrualCategoryRule.setLeaveAccrualCategory("Monthly");
		leaveAccrualCategoryRule.setServiceUnitOfTime("Y");
		leaveAccrualCategoryRule.setRuleStart(new Integer(0));
		leaveAccrualCategoryRule.setRuleEnd(new Integer(5));
		leaveAccrualCategoryRule.setAccrualRate(new Integer(12));
		leaveAccrualCategoryRule.setMaxBalance(new Integer(360));
		leaveAccrualCategoryRule.setActionAtMaxBalance("L");

		KNSServiceLocator.getBusinessObjectService().save(leaveAccrualCategoryRule);
	}

	@Test
	public void testFetchLeaveAccrualCategoryRule() throws Exception {

		LeaveAccrualCategoryRule leaveAccrualCategoryRule = (LeaveAccrualCategoryRule) KNSServiceLocator
				.getBusinessObjectService().findBySinglePrimaryKey(
						LeaveAccrualCategoryRule.class, 1L);
		
		assertTrue(leaveAccrualCategoryRule.getServiceUnitOfTime().equals("Y"));
	}

	@Override
	public void tearDown() throws Exception {

		LeaveAccrualCategoryRule leaveAccrualCategoryRule = (LeaveAccrualCategoryRule) KNSServiceLocator
				.getBusinessObjectService().findBySinglePrimaryKey(
						LeaveAccrualCategoryRule.class, 1L);

		KNSServiceLocator.getBusinessObjectService().delete(leaveAccrualCategoryRule);
		super.tearDown();
	}

}
