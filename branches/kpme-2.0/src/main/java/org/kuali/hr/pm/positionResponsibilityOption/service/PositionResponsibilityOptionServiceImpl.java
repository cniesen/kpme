package org.kuali.hr.pm.positionResponsibilityOption.service;

import org.kuali.hr.pm.positionResponsibilityOption.PositionResponsibilityOption;
import org.kuali.hr.pm.positionResponsibilityOption.dao.PositionResponsibilityOptionDao;

public class PositionResponsibilityOptionServiceImpl implements PositionResponsibilityOptionService {
	
	private PositionResponsibilityOptionDao positionResponsibilityOptionDao;

	public PositionResponsibilityOption getPositionResponsibilityOptionById(
			String prOptionId) {
		return positionResponsibilityOptionDao.getPositionResponsibilityOptionById(prOptionId);
	}
	
	

}
