package edu.iu.uis.hr.tk.client;

import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.client.AbstractLookupActionForm;
import edu.iu.uis.hr.client.OpenAccessAuthorization;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.tk.entity.OtherTimesheetsSearchCriteria;

public class OtherTimesheetsLookupForm extends AbstractLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new OtherTimesheetsSearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
        // no-op (there is no result template)
        return null;
    }

    public void prepare() {
        // two dates are needed because TimelessDate.addDays() modifies the input
        TimelessDate today1 = new TimelessDate("10/20/2006"); 
        TimelessDate today2 = new TimelessDate();
        setFieldOptions("payPeriod", ((PayCalendarService)getService(PayCalendarService.class)).getDropdownPayCalendars(today1.getDate(), today2.addDays(31).getDate()));
    }

    public void search() {
        // no-op (there is no search)
    }

    public final String getOpenUrl() {
        return "javascript:openNewTimesheetWindow()";
    }
    
    public List getAccessAuthorizations() {
        List accessAuthorizations = new ArrayList();
        accessAuthorizations.add(OpenAccessAuthorization.class);
        return accessAuthorizations;
    }    
}