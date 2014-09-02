package org.kuali.kpme.core.tkmdocument.web;

import org.kuali.kpme.core.lookup.KPMELookupableImpl;
import org.kuali.kpme.core.util.TKUtils;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.web.form.LookupForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jwillia on 4/11/14.
 */
public class KhrEmployeeDocumentLookupableImpl extends KPMELookupableImpl {
    @Override
    public List<? extends BusinessObject> getSearchResults(LookupForm form, Map<String, String> searchCriteria, boolean bounded) {
        String universityId = searchCriteria.get("principalId");
        String name = searchCriteria.get("name");
        String fromBeginPeriodDateTime = TKUtils.getFromDateString(searchCriteria.get("startDate"));
        String toBeginPeriodDateTime = TKUtils.getToDateString(searchCriteria.get("endDate"));

        Map<String, String> criteria = new HashMap<String,String>();
        criteria.put("principalId",universityId);

        List<Person> results = KimApiServiceLocator.getPersonService().findPeople(criteria);
        return results;
    }
}

