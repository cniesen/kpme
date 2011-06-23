package org.kuali.hr.lm.leaveplan;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;
import org.kuali.hr.time.test.TkTestCase;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class LeavePlanTest extends TkTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		LeavePlan leavePlan = new LeavePlan();
		leavePlan.setLmLeavePlanId(1L);
		leavePlan.setEffectiveDate(new Date(System.currentTimeMillis()));
		leavePlan.setLocation("D");
		leavePlan.setDescription("salary");
		leavePlan.setLeavePlan("test");
		//leavePlan.setUnitOfTime("D");

		KNSServiceLocator.getBusinessObjectService().save(leavePlan);
	}

	@Test
	public void testFetchLeavePlan() throws Exception {

		LeavePlan leavePlan = (LeavePlan) KNSServiceLocator
				.getBusinessObjectService().findBySinglePrimaryKey(
						LeavePlan.class, 1L);
		
		assertTrue(leavePlan.getLeavePlan().equals("test"));

	}

	@Override
	public void tearDown() throws Exception {

		LeavePlan leavePlan = (LeavePlan) KNSServiceLocator
				.getBusinessObjectService().findBySinglePrimaryKey(
						LeavePlan.class, 1L);

		KNSServiceLocator.getBusinessObjectService().delete(leavePlan);
		super.tearDown();
	}

}
