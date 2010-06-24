package edu.iu.uis.hr.tk.rule.client;

import java.util.ArrayList;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.BreakRule;
import edu.iu.uis.hr.tk.rule.entity.BreakRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class BreakRuleMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new BreakRuleSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return BreakRuleMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new BreakRule();
    }

    public void prepare() {
    }

    public void search() {
        setResults(new ArrayList(((RuleService) getService(RuleService.class)).lookupMaintainableBreakRules(((BreakRuleSearchCriteria) getSearchCriteria()))));
    }
}