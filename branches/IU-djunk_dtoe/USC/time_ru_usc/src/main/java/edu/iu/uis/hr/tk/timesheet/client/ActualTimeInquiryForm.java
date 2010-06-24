package edu.iu.uis.hr.tk.timesheet.client;

import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.hr.client.AbstractLookupActionForm;
import edu.iu.uis.hr.client.InquiryActionForm;
import edu.iu.uis.hr.client.OpenAccessAuthorization;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.ActualTime;
import edu.iu.uis.hr.tk.timesheet.entity.ActualTimeSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class ActualTimeInquiryForm extends AbstractLookupActionForm implements InquiryActionForm {

    @Override
    protected SearchCriteria getNewSearchCriteria() {
        return new ActualTimeSearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
       return new ActualTime();
    }
    public void search() {
        setResults((((TimesheetService) getService(TimesheetService.class)).lookupActualTime((ActualTimeSearchCriteria) getSearchCriteria())));

    }

    public void prepare() {
        // TODO implement this method

    }
    
    public List getAccessAuthorizations() {
        List accessAuthorizations = new ArrayList();
        accessAuthorizations.add(OpenAccessAuthorization.class);
        return accessAuthorizations;
    }
    
    public Class getModeAuthorization() {
        return ActualTimeInquiryFormModeAuthorization.class;
    }


}
