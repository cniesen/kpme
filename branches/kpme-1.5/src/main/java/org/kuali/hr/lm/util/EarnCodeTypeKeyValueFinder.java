/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.hr.lm.util;

import java.util.ArrayList;
import java.util.List;

import org.kuali.hr.lm.earncodesec.EarnCodeType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class EarnCodeTypeKeyValueFinder extends KeyValuesBase {

	@Override
	public List<KeyValue> getKeyValues() {
		List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue(EarnCodeType.TIME.getCode(), "Time"));
        keyValues.add(new ConcreteKeyValue(EarnCodeType.LEAVE.getCode(), "Leave"));
        keyValues.add(new ConcreteKeyValue(EarnCodeType.BOTH.getCode(), "Both"));
        return keyValues;
	}

}
