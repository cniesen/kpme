package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLogSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;
                         
public class ClockLogMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new ClockLogSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return ClockLogMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new ClockLog();
    }

    public void prepare() {
    }

    public void search() {
        setResults((((TimesheetService) getService(TimesheetService.class)).lookupClockLogsByEmployee((ClockLogSearchCriteria) getSearchCriteria())));
    }

}





    