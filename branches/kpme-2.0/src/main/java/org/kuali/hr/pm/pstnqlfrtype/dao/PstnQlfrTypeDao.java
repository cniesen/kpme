package org.kuali.hr.pm.pstnqlfrtype.dao;

import java.util.List;

import org.kuali.hr.pm.pstnqlfrtype.PstnQlfrType;

public interface PstnQlfrTypeDao {

	public PstnQlfrType getPstnQlfrTypeById(String pmPstnQlfrTypeId);
	
	public List<PstnQlfrType> getAllActivePstnQlfrTypes();
}
