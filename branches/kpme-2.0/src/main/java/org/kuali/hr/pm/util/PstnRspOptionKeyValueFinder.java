package org.kuali.hr.pm.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.hr.pm.positionResponsibilityOption.PositionResponsibilityOption;
import org.kuali.hr.pm.service.base.PmServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class PstnRspOptionKeyValueFinder extends KeyValuesBase{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6768131853656090086L;

	@Override
	public List<KeyValue> getKeyValues() {
		List<KeyValue> keyValues = new ArrayList<KeyValue>();
		List<PositionResponsibilityOption> typeList = PmServiceLocator.getPositionResponsibilityOptionService().getAllActivePstnRspOptions();
		keyValues.add(new ConcreteKeyValue("", ""));
		if(CollectionUtils.isNotEmpty(typeList)) {
			for(PositionResponsibilityOption aType : typeList) {
				keyValues.add(new ConcreteKeyValue((String) aType.getPrOptionId(), (String) aType.getPrOptionName()));
			}
		}         
		return keyValues;
	}

}
