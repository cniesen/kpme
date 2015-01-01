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
import org.kuali.kpme.core.krms.KPMERulesEngineExecuter;
import org.kuali.kpme.core.krms.KpmeKrmsFactBuilderService;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.SelectionCriteria;

import java.util.HashMap;
import java.util.Map;

public class KhrEmployeeMaintDocRulesEngineExecutor extends KPMERulesEngineExecuter {
	
    private static final String NAME = "name";
	private static final String NAMESPACE_CODE = "namespaceCode";
	private static final String KHR_EMPLOYEE_SETUP_FACT_BUILDER_SERVICE = "khrEmployeeSetupFactBuilderService";

	@Override
    protected EngineResults performExecute(RouteContext routeContext, Engine engine) {
        Map<String, String> contextQualifiers = new HashMap<String, String>();
        contextQualifiers.put(NAMESPACE_CODE, KPMENamespace.KPME_HR.getNamespaceCode());
        contextQualifiers.put(NAME, HrKrmsConstants.Context.KHR_EMP_SETUP_CONTEXT_NAME);

        SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers, new HashMap<String, String>());

        //Get khr emp setup maintenance doc to create facts
        String documentId = routeContext.getDocument().getDocumentId();
        Object document = null;
        try {
            document = (MaintenanceDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(documentId);
        } 
        catch (WorkflowException e) {
            e.printStackTrace();
        }

        KpmeKrmsFactBuilderService fbService = HrServiceLocator.getService(KHR_EMPLOYEE_SETUP_FACT_BUILDER_SERVICE);
        Facts.Builder factsBuilder = Facts.Builder.create();
        fbService.addFacts(factsBuilder, document, HrKrmsConstants.Context.KHR_EMP_SETUP_CONTEXT_ID, KPMENamespace.KPME_HR.getNamespaceCode());
        EngineResults results = engine.execute(selectionCriteria, factsBuilder.build(), null);
        return results;
    }
}
