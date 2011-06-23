package org.kuali.hr.lm.leaveaccrualcategory;

import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class LeaveAccrualCategoryMaintainableServiceImpl extends KualiMaintainableImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void saveBusinessObject() {
		LeaveAccrualCategory leaveAccrualCategory = (LeaveAccrualCategory)this.getBusinessObject();	
		leaveAccrualCategory.setLmLeaveAccrualCategoryId(null);
		leaveAccrualCategory.setTimeStamp(null);
		KNSServiceLocator.getBusinessObjectService().save(leaveAccrualCategory);
	}
}
