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
package org.kuali.kpme.core.tkmdocument.workflow.krms;

import org.kuali.kpme.core.api.common.krms.HrKrmsConstants;
import org.kuali.kpme.core.api.namespace.KPMENamespace;
import org.kuali.kpme.core.krms.KpmeKrmsFactBuilderServiceHelper;
import org.kuali.kpme.core.tkmdocument.KhrEmployeeDocument;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krms.api.engine.Facts;

public class KhrEmployeeSetupFactBuilderServiceImpl extends KpmeKrmsFactBuilderServiceHelper {
	
    @Override
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        //remove me?
    }

    @Override
    public void addFacts(Facts.Builder factsBuilder, Object factObject) {
        addFacts(factsBuilder, factObject, HrKrmsConstants.Context.KHR_EMP_SETUP_CONTEXT_ID, KPMENamespace.KPME_HR.getNamespaceCode());
    }

    @Override
    public void addFacts(Facts.Builder factsBuilder, Object factObject, String contextId, String namespace) {
        if (factObject != null && factObject instanceof MaintenanceDocument) {
            MaintenanceDocument document = (MaintenanceDocument) factObject;

            if (document.getNewMaintainableObject().getDataObject() != null && document.getNewMaintainableObject().getDataObject() instanceof KhrEmployeeDocument) {
            	KhrEmployeeDocument employeeSetup = (KhrEmployeeDocument) document.getNewMaintainableObject().getDataObject();
            	addObjectMembersAsFacts(factsBuilder, employeeSetup, contextId, namespace);
            	//factsBuilder.addFact(new Term("employeeSetupProcess"), employeeSetup.getEmployeeSetupProcess());
            }
        }
        
    }
}
