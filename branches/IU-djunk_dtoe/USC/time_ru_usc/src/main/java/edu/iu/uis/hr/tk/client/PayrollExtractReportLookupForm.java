package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.client.AbstractLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.entity.PayrollExtractReportSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshot;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class PayrollExtractReportLookupForm extends AbstractLookupActionForm {

	public PayrollExtractReportLookupForm() {
		super();
        setClientReturnUrl(Context.getApplicationBaseUrl() + "Report.do?method=load");
        setClientFieldsToReturn("dateAsMillis");
	}
	
	protected SearchCriteria getNewSearchCriteria() {
        return new PayrollExtractReportSearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new DocumentHeaderSnapshot();
    }

    public void search() {
    	// this is whack. we really just want a list of distinct pay end dates in the snap table,
    	// instead we're coming back with a typedpersistedmaintainablelist, with the objects contrived to
    	// have only dates and other values null.
        setResults(((TimesheetService) getService(TimesheetService.class)).lookupPayrollExtractReports(((PayrollExtractReportSearchCriteria) getSearchCriteria())));
    }

    public void prepare() {
    }

}
