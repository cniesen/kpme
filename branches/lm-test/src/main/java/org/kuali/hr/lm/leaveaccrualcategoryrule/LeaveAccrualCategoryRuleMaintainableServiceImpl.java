package org.kuali.hr.lm.leaveaccrualcategoryrule;

import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class LeaveAccrualCategoryRuleMaintainableServiceImpl extends KualiMaintainableImpl {

	
	private static final long serialVersionUID = 1L;

	@Override
	public void saveBusinessObject() {
		LeaveAccrualCategoryRule leaveAccrualCategoryRule = (LeaveAccrualCategoryRule)this.getBusinessObject();	
		leaveAccrualCategoryRule.setLmLeaveAccrualCategoryRuleId(null);
								 
		leaveAccrualCategoryRule.setTimeStamp(null);
		KNSServiceLocator.getBusinessObjectService().save(leaveAccrualCategoryRule);
	}
}
