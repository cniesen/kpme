package org.kuali.hr.time.util;

import java.util.ArrayList;
import java.util.List;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;

import edu.iu.hr.time.util.IUTkConstants;

public class IUTkRoleWorkAreaOtEditValuesFinder extends KeyValuesBase {
	
	static final List<KeyLabelPair> labels = new ArrayList<KeyLabelPair>(TkConstants.ROLE_ASSIGNMENT_FOR_WORK_AREA_OT_EDIT.size());
	static {
		for (String roleKey : TkConstants.ROLE_ASSIGNMENT_FOR_WORK_AREA_OT_EDIT) {
			labels.add(new KeyLabelPair(roleKey, TkConstants.ALL_ROLES_MAP.get(roleKey)));
		}
		labels.add(new KeyLabelPair(IUTkConstants.ROLE_TK_PY_PROCESSOR, "Payroll Processor"));
	}

	@Override
	public List<KeyLabelPair> getKeyValues() {
		return labels;
	}
}
