package org.kuali.hr.time.util;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;

public class lmProrationValues extends KeyValuesBase {

	@Override
	public List getKeyValues() {
		// TODO Auto-generated method stub
		List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
		keyValues.add(new KeyLabelPair("o", "On"));
		keyValues.add(new KeyLabelPair("f", "Off"));
		return keyValues;
	}

}
