package edu.iu.uis.hr.tk.rule.client;

import java.util.ArrayList;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.HourlyHoursRule;
import edu.iu.uis.hr.tk.rule.entity.HourlyHoursRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class HourlyHoursRuleMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new HourlyHoursRuleSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return HourlyHoursRuleMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new HourlyHoursRule();
    }

    public void prepare() {
    }

    public void search() {
        setResults(new ArrayList(((RuleService) getService(RuleService.class)).lookupMaintainableHourlyHoursRules(((HourlyHoursRuleSearchCriteria) getSearchCriteria()))));
    }
}