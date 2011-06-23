package org.kuali.hr.time.util;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;

public class lmAccrualEarnIntervalValues extends KeyValuesBase {

	@Override
	public List getKeyValues() {
		// TODO Auto-generated method stub

		List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
		keyValues.add(new KeyLabelPair("D", "Daily"));
		keyValues.add(new KeyLabelPair("W", "Weekly"));
		keyValues.add(new KeyLabelPair("S", "Semi-Monthly"));
		keyValues.add(new KeyLabelPair("M", "Monthly"));
		keyValues.add(new KeyLabelPair("Y", "Yearly"));
		return keyValues;

	}

}
