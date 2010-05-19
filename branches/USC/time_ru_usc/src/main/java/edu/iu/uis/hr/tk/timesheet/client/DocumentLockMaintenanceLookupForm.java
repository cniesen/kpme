package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLock;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLockSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;
                         
public class DocumentLockMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new DocumentLockSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return DocumentLockMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new DocumentLock();
    }

    public void prepare() {
    }

    public void search() {
        setResults((((TimesheetService) getService(TimesheetService.class)).lookupLockedDocuments((DocumentLockSearchCriteria) getSearchCriteria())));
    }

}





    