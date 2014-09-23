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
package org.kuali.kpme.core.tkmdocument.web;


import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwillia on 4/16/14.
 */
public class KhrEmployeeDocumentProcessKeyValueFinder extends UifKeyValuesFinderBase {

    private static final long serialVersionUID = 1L;

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("New Job/Payrate","New Job/Payrate"));
        keyValues.add(new ConcreteKeyValue("Change Job Dates","Change Job Dates"));
        keyValues.add(new ConcreteKeyValue("Update Job Details","Update Job Details"));
        keyValues.add(new ConcreteKeyValue("End Job","End Job"));

        return keyValues;
    }
}