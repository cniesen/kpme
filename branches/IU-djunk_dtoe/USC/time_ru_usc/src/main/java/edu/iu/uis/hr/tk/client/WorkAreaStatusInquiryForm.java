package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.client.AbstractLookupActionForm;
import edu.iu.uis.hr.client.InquiryActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.entity.WorkAreaStatusInquirySearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class WorkAreaStatusInquiryForm extends AbstractLookupActionForm implements InquiryActionForm {

    protected SearchCriteria getNewSearchCriteria() {
        return new WorkAreaStatusInquirySearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new ClockLog();
    }

    public void search() {
        setResults(((TimesheetService)getService(TimesheetService.class)).lookupClockLogs((WorkAreaStatusInquirySearchCriteria)getSearchCriteria()));
    }

    public void prepare() {
    }

}
