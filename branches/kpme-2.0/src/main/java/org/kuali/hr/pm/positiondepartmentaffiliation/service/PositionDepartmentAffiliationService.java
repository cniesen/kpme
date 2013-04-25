package org.kuali.hr.pm.positiondepartmentaffiliation.service;

import java.util.List;

import org.joda.time.LocalDate;
import org.kuali.hr.pm.positiondepartmentaffiliation.PositionDepartmentAffiliation;
import org.kuali.hr.pm.positiontype.PositionType;

public interface PositionDepartmentAffiliationService {

	/**
	 * retrieve the PositionDepartmentAffiliation with given id
	 * @param pmPositionDeptAfflId
	 * @return
	 */
	public PositionDepartmentAffiliation getPositionDepartmentAffiliationById(String pmPositionDeptAfflId);
	
	public List<PositionDepartmentAffiliation> getPositionDepartmentAffiliationList(String positionDeptAfflType, LocalDate asOfDate);
}
