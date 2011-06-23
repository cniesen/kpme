package org.kuali.hr.lm.leaveplan;

import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class LeavePlanMaintainableServiceImpl extends KualiMaintainableImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void saveBusinessObject() {
		LeavePlan leavePlan = (LeavePlan)this.getBusinessObject();	
		leavePlan.setLmLeavePlanId(null);
		leavePlan.setTimeStamp(null);
		KNSServiceLocator.getBusinessObjectService().save(leavePlan);
	}
}
