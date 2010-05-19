package edu.iu.uis.hr.tk.rule.client;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.rule.entity.HolidayIncentiveRule;
import edu.iu.uis.hr.tk.rule.entity.HolidayIncentiveRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class HolidayIncentiveRuleMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {

    protected SearchCriteria getNewSearchCriteria() {
        return new HolidayIncentiveRuleSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return HolidayIncentiveRuleMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new HolidayIncentiveRule();
    }

    public void prepare() {
        setFieldOptions(FieldNames.LOCATION, ((PositionService) getService(PositionService.class)).getDropdownLocations());
    }

    public void search() {
        setResults(((RuleService) getService(RuleService.class)).lookupMaintainableHolidayIncentiveRules(((HolidayIncentiveRuleSearchCriteria) getSearchCriteria())));
    }
}