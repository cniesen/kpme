/**
 * Copyright 2004-2015 The Kuali Foundation
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


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.job.Job;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.tkmdocument.KhrEmployeeDocument;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.web.form.MaintenanceDocumentForm;

/**
 * Created by jwillia on 4/16/14.
 */
public class KhrEmployeeDocumentProcessKeyValueFinder extends UifKeyValuesFinderBase {

    private static final long serialVersionUID = 1L;

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue(HrConstants.KhrEmployeeDocProcess.NEW_JOB,HrConstants.KhrEmployeeDocProcess.NEW_JOB));
        keyValues.add(new ConcreteKeyValue(HrConstants.KhrEmployeeDocProcess.MAINTAIN_JOB,HrConstants.KhrEmployeeDocProcess.MAINTAIN_JOB));

        return keyValues;
    }
    
	@Override
	public List<KeyValue> getKeyValues(ViewModel model) {
		List<KeyValue> keyValues = new ArrayList<KeyValue>();
        if (model instanceof MaintenanceDocumentForm) {
		    MaintenanceDocumentForm docForm = (MaintenanceDocumentForm) model;
		    KhrEmployeeDocument anEmployeeDoc = (KhrEmployeeDocument) docForm.getDocument().getNewMaintainableObject().getDataObject();
		    String pId = anEmployeeDoc.getPrincipalId();
		    if(StringUtils.isNotBlank(pId)) {
		    	List<Job> jobList = HrServiceLocator.getJobService().getJobs(pId, LocalDate.now());
		    	if(CollectionUtils.isEmpty(jobList)) {
		    		 keyValues.add(new ConcreteKeyValue(HrConstants.KhrEmployeeDocProcess.NEW_JOB,HrConstants.KhrEmployeeDocProcess.NEW_JOB));
		    		 return keyValues;
		    	} else {
		    		return this.getKeyValues();
		    	}
		    }
        }
        return keyValues;
	}
}