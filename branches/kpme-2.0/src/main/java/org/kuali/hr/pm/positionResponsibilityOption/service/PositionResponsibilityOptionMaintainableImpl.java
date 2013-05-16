package org.kuali.hr.pm.positionResponsibilityOption.service;

import org.kuali.hr.pm.service.base.PmServiceLocator;
import org.kuali.hr.time.HrBusinessObject;
import org.kuali.hr.time.util.HrBusinessObjectMaintainableImpl;

public class PositionResponsibilityOptionMaintainableImpl extends HrBusinessObjectMaintainableImpl {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8301277353231917991L;

	@Override
	public HrBusinessObject getObjectById(String id) {
		return PmServiceLocator.getPositionResponsibilityOptionService().getPositionResponsibilityOptionById(id);
	}

}

