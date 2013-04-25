package org.kuali.hr.pm.pstncontracttype.service;

import java.util.List;

import org.joda.time.LocalDate;
import org.kuali.hr.pm.positiontype.PositionType;
import org.kuali.hr.pm.pstncontracttype.PstnContractType;

public interface PstnContractTypeService {
	/**
	 * retrieve the PstnContractType with given id
	 * @param pmCntrctTypeId
	 * @return
	 */
	public PstnContractType getPstnContractTypeById(String pmCntrctTypeId);
	
	/**
	 * Get list of PstnContractType with given group, institution, campus and effective date
	 * wild card allowed
	 * @param institution
	 * @param campus
	 * @param asOfDate
	 * @return
	 */
	public List<PstnContractType> getPstnContractTypeList(String institution, String campus, LocalDate asOfDate);
}
