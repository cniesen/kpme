package org.kuali.hr.pm.positionresponsibility.service;

import org.kuali.hr.pm.service.base.PmServiceLocator;
import org.kuali.hr.time.HrBusinessObject;
import org.kuali.hr.time.util.HrBusinessObjectMaintainableImpl;

public class PositionResponsibilityMaintainableImpl extends HrBusinessObjectMaintainableImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8359836961276276615L;

	@Override
	public HrBusinessObject getObjectById(String id) {
		return PmServiceLocator.getPositionResponsibilityService().getPositionResponsibilityById(id);
	}


}
