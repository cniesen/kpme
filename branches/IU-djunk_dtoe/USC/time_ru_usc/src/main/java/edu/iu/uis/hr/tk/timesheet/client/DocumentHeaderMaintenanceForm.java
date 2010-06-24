package edu.iu.uis.hr.tk.timesheet.client;

import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.client.AbstractMaintenanceActionForm;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.client.Utility;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.person.service.PersonService;
import edu.iu.uis.hr.tk.timesheet.TimesheetInitiateKey;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class DocumentHeaderMaintenanceForm extends AbstractMaintenanceActionForm {

	private TypedPersistentMaintainedEntityList documentHeaders;

    public DocumentHeaderMaintenanceForm() {
        super();
        setDocumentHeaders(new TypedPersistentMaintainedEntityList(DocumentHeader.class));
    }

	public void setLookupResults(List lookupResults) {
		getDocumentHeaders().addAll(lookupResults);
	}
	
	protected TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
		return getDocumentHeaders();
	}
	
	public Class getModeAuthorization() {
        return DocumentHeaderMaintenanceFormModeAuthorization.class;
    }
    
	public void prepare() {
	}

	public void save() {
		Iterator documentHeaderIterator = getDocumentHeaders().iterator();
		while (documentHeaderIterator.hasNext()) {
			DocumentHeader documentHeader = (DocumentHeader)documentHeaderIterator.next();
			if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(documentHeader.getDocumentId())) {
			    TimesheetInitiateKey initiateKey = new TimesheetInitiateKey();
	            initiateKey.setUniversityId(documentHeader.getUniversityId());
	            initiateKey.setPayEndDate(documentHeader.getPayEndDate());
	            ((TimesheetService) getService(TimesheetService.class)).initiate(initiateKey);
	            documentHeader.setDocumentId(((TimesheetService) getService(TimesheetService.class)).getDocumentHeader(documentHeader.getUniversityId(), documentHeader.getPayEndDate()).getDocumentId());
			} // should have a error catch?
		}
	}
	
    public void approve() {
        DocumentHeader documentHeader = (DocumentHeader) getTypedPersistentMaintainedEntityList().get(getIndex());
        ((TimesheetService) getService(TimesheetService.class)).approve( ((TimesheetService) getService(TimesheetService.class)).getTimesheetDocument(documentHeader.getDocumentId()), ((PersonService) getService(PersonService.class)).getNetworkIdByEmployeeId(documentHeader.getUniversityId()));
    }
    
    public TypedPersistentMaintainedEntityList getDocumentHeaders() {
		return documentHeaders;
	}

	public void setDocumentHeaders(TypedPersistentMaintainedEntityList documentHeaders) {
		this.documentHeaders = documentHeaders;
	}

    public DocumentHeader getDocumentHeader(int index) {
        return (DocumentHeader)getDocumentHeaders().get(index);
    }

    public void setDocumentHeader(int index, DocumentHeader documentHeader) {
        getDocumentHeaders().set(index, documentHeader);
    }
	
    public final String getApproveUrl() {
        return new StringBuffer(BEGIN_POST_TO_OPERATION).append("DocumentHeaderApproveAction.do").append(Utility.QUESTION_MARK).append(StrutsDispatchParamConstants.DISPATCH_PARAMETER).append("=").append(StrutsDispatchParamConstants.APPROVE_DISPATCH_PARAM_VALUE).toString();
        
    }
    
    
}
