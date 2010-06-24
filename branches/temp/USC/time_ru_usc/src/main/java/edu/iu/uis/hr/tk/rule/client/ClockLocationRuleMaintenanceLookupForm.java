package edu.iu.uis.hr.tk.rule.client;

import java.util.ArrayList;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRule;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class ClockLocationRuleMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new ClockLocationRuleSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return ClockLocationRuleMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new ClockLocationRule();
    }

    public void prepare() {
    }

    public void search() {
        setResults(new ArrayList(((RuleService) getService(RuleService.class)).lookupMaintainableClockLocationRules(((ClockLocationRuleSearchCriteria) getSearchCriteria()))));
    }

}