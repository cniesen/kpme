package org.kuali.hr.pm.positionResponsibilityOption.service;

import java.util.List;

import org.kuali.hr.pm.positionResponsibilityOption.PositionResponsibilityOption;

public interface PositionResponsibilityOptionService {
	
	public PositionResponsibilityOption getPositionResponsibilityOptionById(String prOptionId);
	public List<PositionResponsibilityOption> getAllActivePstnRspOptions();
	
	

}
