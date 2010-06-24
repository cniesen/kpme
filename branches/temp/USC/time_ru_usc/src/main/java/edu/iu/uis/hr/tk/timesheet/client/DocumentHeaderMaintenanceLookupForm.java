package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class DocumentHeaderMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {

	protected SearchCriteria getNewSearchCriteria() {
		return new DocumentHeaderSearchCriteria();
	}

	public Class getMaintenanceFormClass() {
		return DocumentHeaderMaintenanceForm.class;
	}

	public PersistentDatabaseEntity getResultTemplate() {
		return new DocumentHeader();
	}

	public void search() {
		setResults((((TimesheetService)getService(TimesheetService.class)).lookupDocumentHeaders(((DocumentHeaderSearchCriteria) getSearchCriteria()))));
	}

	public void prepare() {
	}

}
