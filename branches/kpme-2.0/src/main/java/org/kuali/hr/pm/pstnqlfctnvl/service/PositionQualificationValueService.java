package org.kuali.hr.pm.pstnqlfctnvl.service;

import org.kuali.hr.pm.pstnqlfctnvl.PositionQualificationValue;

public interface PositionQualificationValueService {
	/**
	 * retrieve the PositionQualificationValue with given value
	 * @param value
	 * @return
	 */
	public PositionQualificationValue getPositionQualificationValueByValue(String value);
	/**
	 * retrieve the PositionQualificationValue with given id
	 * @param id
	 * @return
	 */
	public PositionQualificationValue getPositionQualificationValueById(String id);

}
