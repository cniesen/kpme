package org.kuali.kpme.tklm.leave.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kpme.core.util.HrConstants;
import org.kuali.kpme.tklm.common.LMConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class LeaveBlockTypeValuesFinder extends KeyValuesBase{
	@Override
	public List<KeyValue> getKeyValues() {
		List<KeyValue> keyValues = new ArrayList<KeyValue>();
		for (Map.Entry entry : LMConstants.LEAVE_BLOCK_TYPE_MAP.entrySet()) {
            keyValues.add(new ConcreteKeyValue((String) entry.getKey(), (String) entry.getValue()));
        }           
		return keyValues;
	}
}
