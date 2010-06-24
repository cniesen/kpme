package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class TimeBlockMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {

	protected SearchCriteria getNewSearchCriteria() {
		return new TimeBlockSearchCriteria();
	}

	public Class getMaintenanceFormClass() {
		return TimeBlockMaintenanceForm.class;
	}

	public PersistentDatabaseEntity getResultTemplate() {
		return new TimeBlock();
	}

	public void search() {
		setResults((((TimesheetService)getService(TimesheetService.class)).lookupTimeBlocks(((TimeBlockSearchCriteria) getSearchCriteria()))));
	}

	public void prepare() {
	}

}
