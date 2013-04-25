package org.kuali.hr.pm.pstncontracttype.service;

import java.util.List;

import org.joda.time.LocalDate;
import org.kuali.hr.pm.positiontype.PositionType;
import org.kuali.hr.pm.positiontype.dao.PositionTypeDao;
import org.kuali.hr.pm.pstncontracttype.PstnContractType;
import org.kuali.hr.pm.pstncontracttype.dao.PstnContractTypeDao;

public class PstnContractTypeServiceImpl implements PstnContractTypeService {

	private PstnContractTypeDao pstnContractTypeDao;

	public PstnContractTypeDao getPstnContractTypeDao() {
		return pstnContractTypeDao;
	}

	public void setPstnContractTypeDao(
			PstnContractTypeDao pstnContractTypeDao) {
		this.pstnContractTypeDao = pstnContractTypeDao;
	}


	@Override
	public PstnContractType getPstnContractTypeById(
			String pmPositionTypeId) {
		return pstnContractTypeDao.getPstnContractTypeById(pmPositionTypeId);
	}

	@Override
	public List<PstnContractType> getPstnContractTypeList(String institution, String campus, LocalDate asOfDate) {
		return pstnContractTypeDao.getPstnContractTypeList( institution, campus, asOfDate);
	}

}
