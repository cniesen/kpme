package org.kuali.hr.pm.institution.service;

import org.kuali.hr.pm.service.base.PmServiceLocator;
import org.kuali.hr.time.HrBusinessObject;
import org.kuali.hr.time.util.HrBusinessObjectMaintainableImpl;

public class InstitutionMaintainableImpl extends HrBusinessObjectMaintainableImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public HrBusinessObject getObjectById(String id) {
		// TODO Auto-generated method stub
		return PmServiceLocator.getInstitutionService().getInstitutionById(id);
	}

	
	
}
