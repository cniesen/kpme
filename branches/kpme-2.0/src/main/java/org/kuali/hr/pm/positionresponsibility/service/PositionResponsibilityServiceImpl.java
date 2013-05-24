package org.kuali.hr.pm.positionresponsibility.service;

import org.kuali.hr.pm.positionResponsibilityOption.dao.PositionResponsibilityOptionDao;
import org.kuali.hr.pm.positionresponsibility.PositionResponsibility;
import org.kuali.hr.pm.positionresponsibility.dao.PositionResponsibilityDao;


public class PositionResponsibilityServiceImpl implements PositionResponsibilityService {
	private PositionResponsibilityDao positionResponsibilityDao;

	@Override
	public PositionResponsibility getPositionResponsibilityById(
			String positionResponsibilityId) {
		return positionResponsibilityDao.getPositionResponsibilityById(positionResponsibilityId);

	}

	public PositionResponsibilityDao getPositionResponsibilityDao() {
		return positionResponsibilityDao;
	}

	public void setPositionResponsibilityDao(
			PositionResponsibilityDao positionResponsibilityDao) {
		this.positionResponsibilityDao = positionResponsibilityDao;
	}

	

}
