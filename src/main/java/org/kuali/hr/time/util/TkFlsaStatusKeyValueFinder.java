package org.kuali.hr.time.util;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

import java.util.ArrayList;
import java.util.List;

public class TkFlsaStatusKeyValueFinder extends KeyValuesBase {

	@Override
	public List getKeyValues() {
		List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue(TkConstants.FLSA_STATUS_NON_EXEMPT, "Non-Exempt"));
        keyValues.add(new ConcreteKeyValue(TkConstants.FLSA_STATUS_EXEMPT, "Exempt"));
        return keyValues;
	}

}
