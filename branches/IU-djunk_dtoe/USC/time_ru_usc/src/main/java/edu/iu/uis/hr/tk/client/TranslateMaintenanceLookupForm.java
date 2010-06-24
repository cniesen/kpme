package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.entity.Translate;
import edu.iu.uis.hr.tk.entity.TranslateSearchCriteria;
import edu.iu.uis.hr.tk.service.TranslateService;

public class TranslateMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new TranslateSearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new Translate();
    }

    public Class getMaintenanceFormClass() {
        return TranslateMaintenanceForm.class;
    }

    public void prepare() {
    }

    public void search() {
        setResults(((TranslateService) getService(TranslateService.class)).lookupMaintainableTranslates(((TranslateSearchCriteria) getSearchCriteria())));
    }
}