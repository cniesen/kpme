package org.kuali.kpme.edo.workflow.postprocessor;

import org.kuali.kpme.edo.service.EdoServiceLocator;
import org.kuali.kpme.edo.workflow.DossierProcessDocumentHeader;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kew.framework.postprocessor.ProcessDocReport;
import org.kuali.rice.kew.postprocessor.DefaultPostProcessor;

public class SupplementalPostProcessor extends DefaultPostProcessor {
	 @Override
	    public ProcessDocReport doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) throws Exception {
	        ProcessDocReport pdr = null;
	        DossierProcessDocumentHeader document = EdoServiceLocator.getDossierProcessDocumentHeaderService().getDossierProcessDocumentHeader(statusChangeEvent.getDocumentId());
	        if (document != null) {
	        	pdr = super.doRouteStatusChange(statusChangeEvent);
	            DocumentStatus newDocumentStatus = DocumentStatus.fromCode(statusChangeEvent.getNewRouteStatus());
	            DocumentStatus oldDocumentStatus = DocumentStatus.fromCode(statusChangeEvent.getOldRouteStatus());
	            updateDossierProcessDocumentHeaderStatus(document, newDocumentStatus);

	        }

	        return pdr;
	       
	    }

	    private void updateDossierProcessDocumentHeaderStatus(DossierProcessDocumentHeader dossierProcessDocumentHeader, DocumentStatus newDocumentStatus) {
	        dossierProcessDocumentHeader.setDocumentStatus(newDocumentStatus.getCode());
	        EdoServiceLocator.getDossierProcessDocumentHeaderService().saveOrUpdate(dossierProcessDocumentHeader);
	    }
	}