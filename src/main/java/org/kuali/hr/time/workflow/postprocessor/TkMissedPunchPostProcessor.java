/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.hr.time.workflow.postprocessor;

import org.kuali.hr.time.missedpunch.MissedPunchDocument;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kew.framework.postprocessor.ProcessDocReport;
import org.kuali.rice.kew.postprocessor.DefaultPostProcessor;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

public class TkMissedPunchPostProcessor extends DefaultPostProcessor {

	@Override
	public ProcessDocReport doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) throws Exception {
        ProcessDocReport pdr = null;
        String documentId = statusChangeEvent.getDocumentId();
        MissedPunchDocument document = TkServiceLocator.getMissedPunchService().getMissedPunchByRouteHeader(documentId);
        if (document != null) {
            pdr = super.doRouteStatusChange(statusChangeEvent);
            // Only update the status if it's different.
            if (!statusChangeEvent.getOldRouteStatus().equals(statusChangeEvent.getNewRouteStatus())
                    && statusChangeEvent.getNewRouteStatus().equals(DocumentStatus.ENROUTE.getCode())) {
                TkServiceLocator.getMissedPunchService().addClockLogForMissedPunch(document);
                KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
            }
        }

        return pdr;
    }

}
