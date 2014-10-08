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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kpme.core.lookup.KPMELookupableImpl;
import org.kuali.kpme.core.util.TKUtils;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.web.form.LookupForm;

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

    @Override
    public boolean allowsMaintenanceNewOrCopyAction() {
        return false;
    }
       
    @Override
    public void getMaintenanceActionLink(Action actionLink, Object model, String maintenanceMethodToCall) {
    	// change label for Edit action to Open
    	if(actionLink.getActionLabel().equals("edit")) {
    		actionLink.setActionLabel("Open");
    	}
    	super.getMaintenanceActionLink(actionLink, model, maintenanceMethodToCall);
    }
    
}

