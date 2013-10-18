package org.kuali.kpme.tklm.leave.block;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

import org.kuali.kpme.core.util.HrConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class LeaveCalendarHeaderDocTypeStatusValuesFinder extends KeyValuesBase {
	@Override
	public List<KeyValue> getKeyValues() {
		List<KeyValue> keyValues = new ArrayList<KeyValue>();
		Map<String, String> statusMap = new TreeMap<String, String>();
		statusMap.put("", "");
		for (Map.Entry entry : HrConstants.DOC_ROUTE_STATUS.entrySet()) {
			statusMap.put((String) entry.getKey(), (String) entry.getValue());
		}
		for (Map.Entry entry : statusMap.entrySet()) {
			keyValues.add(new ConcreteKeyValue((String) entry.getKey(),
					(String) entry.getValue()));
		}
		return keyValues;
	}
}
