package org.kuali.hr.time.util;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;

public class lmActionAtMaxBalanceValues extends KeyValuesBase {

	@Override
	public List getKeyValues() {
		// TODO Auto-generated method stub
		
		List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("T", "Transfer"));     		
        keyValues.add(new KeyLabelPair("P", "Payout"));
        keyValues.add(new KeyLabelPair("L", "Lose"));
        keyValues.add(new KeyLabelPair("N", "Not Applicable"));
        return keyValues;
		
	}

}
