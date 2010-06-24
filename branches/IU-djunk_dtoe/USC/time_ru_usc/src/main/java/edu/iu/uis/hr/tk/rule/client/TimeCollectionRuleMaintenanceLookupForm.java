package edu.iu.uis.hr.tk.rule.client;

import java.util.ArrayList;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRule;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class TimeCollectionRuleMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new TimeCollectionRuleSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return TimeCollectionRuleMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new TimeCollectionRule();
    }

    public void prepare() {
    }

    public void search() {
        setResults(new ArrayList(((RuleService) getService(RuleService.class)).lookupMaintainableTimeCollectionRules(((TimeCollectionRuleSearchCriteria) getSearchCriteria()))));
    }
}