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