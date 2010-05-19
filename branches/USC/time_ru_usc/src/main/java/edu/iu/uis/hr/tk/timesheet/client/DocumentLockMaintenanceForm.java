package edu.iu.uis.hr.tk.timesheet.client;

import java.util.List;

import edu.iu.uis.hr.client.AbstractMaintenanceActionForm;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLock;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class DocumentLockMaintenanceForm extends AbstractMaintenanceActionForm {
    private TypedPersistentMaintainedEntityList documentLocks;

    public DocumentLockMaintenanceForm() {
        super();
        setDocumentLocks(new TypedPersistentMaintainedEntityList(DocumentLock.class));
    }

    public void setLookupResults(List lookupResults) {
        getDocumentLocks().addAll(lookupResults);
    }

    protected final TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
        return getDocumentLocks();
    }

    public Class getModeAuthorization() {
        return DocumentLockMaintenanceFormModeAuthorization.class;
    }

    public void prepare() {
    	setFieldsToReturn(null);
    }

    public void save() {
        ((TimesheetService)getService(TimesheetService.class)).unlockMutipleDocuments(getFieldsToReturn());
        setFieldsToReturn("-1");
    }

    public void delete() {
        //It was necessary to overwrite this method 'cause we're really removing the records from the database
        //setFieldsToReturn method was used for a purpose different than the originally intended, we're just using it to temporarily store the 
        //documentIds selected clicking on the "delete" button. This list is then used by the "save" method 
        if (!Utility.hasValue(getFieldsToReturn())) {
            setFieldsToReturn(getDocumentLock(getIndex()).getDocumentId());
        } else {
            setFieldsToReturn(new StringBuffer(getDocumentLock(getIndex()).getDocumentId()).append(edu.iu.uis.hr.Utility.COMMA).append(getFieldsToReturn()).toString());
        }
        getDocumentLocks().remove(getIndex());
    }

    public TypedPersistentMaintainedEntityList getDocumentLocks() {
        return documentLocks;
    }

    public void setDocumentLocks(TypedPersistentMaintainedEntityList documentLocks) {
        if (documentLocks != null) {
            this.documentLocks = documentLocks;
        }
    }

    public DocumentLock getDocumentLock(int index) {
        return (DocumentLock)getDocumentLocks().get(index);
    }

    public void setDocumentLock(int index, DocumentLock documentLock) {
        getDocumentLocks().set(index, documentLock);
    }

}