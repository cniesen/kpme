package org.kuali.hr.pm.institution.service;

import java.sql.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.kuali.hr.pm.institution.Institution;

public interface InstitutionService {

	public Institution getInstitutionById(String institutionId);
	
	public List<Institution> getActiveInstitutionsAsOf(DateTime asOfDate);
	
	public List<Institution> getInstitutionsByCode(String code);
	
	/**
	 * retrieve the institution with given code and exists before given date
	 * @param institutionCode
	 * @param asOfDate
	 * @return
	 */
	public Institution getInstitution(String institutionCode, Date asOfDate);
	
}
