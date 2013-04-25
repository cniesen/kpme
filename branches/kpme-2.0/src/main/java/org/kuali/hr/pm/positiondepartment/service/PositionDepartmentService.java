package org.kuali.hr.pm.positiondepartment.service;

import org.kuali.hr.pm.positiondepartment.PositionDepartment;

public interface PositionDepartmentService {

	/**
	 * retrieve the PositionDepartment with given id
	 * @param pmPositionDeptId
	 * @return
	 */
	public PositionDepartment getPositionDepartmentById(String pmPositionDeptId);
	
}
