package edu.iu.uis.hr.tk.extract.client;

import java.util.ArrayList;
import java.util.Date;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractScheduleSearchCriteria;
import edu.iu.uis.hr.tk.extract.service.PayrollExtractService;
import edu.iu.uis.hr.tk.service.TranslateService;

public class PayrollExtractScheduleMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new PayrollExtractScheduleSearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new PayrollExtractSchedule();
    }

    public void search() {
        setResults(new ArrayList(((PayrollExtractService) getService(PayrollExtractService.class)).lookupMaintainablePayrollExtractSchedules(((PayrollExtractScheduleSearchCriteria) getSearchCriteria()))));

    }

    public Class getMaintenanceFormClass() {
        return PayrollExtractScheduleMaintenanceForm.class;
    }

    public void prepare() {
        setFieldOptions(FieldNames.STATUS, ((TranslateService) getService(TranslateService.class)).getDropdownTranslates(FieldNames.STATUS, edu.iu.uis.hr.Utility.getListFromCommaDelimitedList(((PayrollExtractScheduleSearchCriteria) getSearchCriteria()).getStatuses()), new Date()));
        setFieldOptions(Timestamp.AM_PM_FIELD, Timestamp.getAmPmValues());
    }
}
