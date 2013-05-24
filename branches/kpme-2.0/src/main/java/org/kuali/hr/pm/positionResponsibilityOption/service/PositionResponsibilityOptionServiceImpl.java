package org.kuali.hr.pm.positionResponsibilityOption.service;

import java.util.List;

import org.kuali.hr.pm.positionResponsibilityOption.PositionResponsibilityOption;
import org.kuali.hr.pm.positionResponsibilityOption.dao.PositionResponsibilityOptionDao;

public class PositionResponsibilityOptionServiceImpl implements PositionResponsibilityOptionService {
	
	private PositionResponsibilityOptionDao positionResponsibilityOptionDao;

	public PositionResponsibilityOptionDao getPositionResponsibilityOptionDao() {
		return positionResponsibilityOptionDao;
	}
	
	public void setPositionResponsibilityOptionDao(
			PositionResponsibilityOptionDao positionResponsibilityOptionDao) {
		this.positionResponsibilityOptionDao = positionResponsibilityOptionDao;
	}
	
	public PositionResponsibilityOption getPositionResponsibilityOptionById(
			String prOptionId) {
		return positionResponsibilityOptionDao.getPositionResponsibilityOptionById(prOptionId);
	}
	public List<PositionResponsibilityOption> getAllActivePstnRspOptions() {
		
		return positionResponsibilityOptionDao.getAllActivePstnRspOptions();
		
	}
	
	

}
