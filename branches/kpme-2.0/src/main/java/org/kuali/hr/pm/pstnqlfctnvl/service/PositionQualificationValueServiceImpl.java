package org.kuali.hr.pm.pstnqlfctnvl.service;

import org.kuali.hr.pm.pstnqlfctnvl.PositionQualificationValue;
import org.kuali.hr.pm.pstnqlfctnvl.dao.PositionQualificationValueDao;

public class PositionQualificationValueServiceImpl implements PositionQualificationValueService {
	
	private PositionQualificationValueDao pstnQlfctnVlDao; 
	
	@Override
	public PositionQualificationValue getPositionQualificationValueByValue(String value) {
		return pstnQlfctnVlDao.getPositionQualificationValueByValue(value);
	}

	public PositionQualificationValueDao getPstnQlfctnVlDao() {
		return pstnQlfctnVlDao;
	}

	public void setPstnQlfctnVlDao(PositionQualificationValueDao pstnQlfctnVlDao) {
		this.pstnQlfctnVlDao = pstnQlfctnVlDao;
	}

	@Override
	public PositionQualificationValue getPositionQualificationValueById(String id) {
		return pstnQlfctnVlDao.getPositionQualificationValueById(id);
	}

	
}
