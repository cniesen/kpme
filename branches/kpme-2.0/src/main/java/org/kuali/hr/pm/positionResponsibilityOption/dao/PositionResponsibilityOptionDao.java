package org.kuali.hr.pm.positionResponsibilityOption.dao;

import java.util.List;

import org.kuali.hr.pm.positionResponsibilityOption.PositionResponsibilityOption;

public interface PositionResponsibilityOptionDao {
	
	public PositionResponsibilityOption getPositionResponsibilityOptionById(String prOptionId);
	public List<PositionResponsibilityOption> getAllActivePstnRspOptions();

}
