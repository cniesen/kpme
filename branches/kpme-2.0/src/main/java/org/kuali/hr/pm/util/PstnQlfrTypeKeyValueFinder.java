package org.kuali.hr.pm.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.hr.pm.pstnqlfrtype.PstnQlfrType;
import org.kuali.hr.pm.service.base.PmServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class PstnQlfrTypeKeyValueFinder extends KeyValuesBase{

	private static final long serialVersionUID = 1L;

	@Override
	public List<KeyValue> getKeyValues() {
		List<KeyValue> keyValues = new ArrayList<KeyValue>();
		List<PstnQlfrType> typeList = PmServiceLocator.getPstnQlfrTypeService().getAllActivePstnQlfrTypes();
		if(CollectionUtils.isNotEmpty(typeList)) {
			for(PstnQlfrType aType : typeList) {
				keyValues.add(new ConcreteKeyValue((String) aType.getPmPstnQlfrTypeId(), (String) aType.getType()));
			}
		}         
		return keyValues;
	}

}
