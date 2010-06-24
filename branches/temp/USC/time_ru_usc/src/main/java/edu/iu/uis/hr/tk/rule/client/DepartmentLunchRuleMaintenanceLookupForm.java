package edu.iu.uis.hr.tk.rule.client;

import java.util.ArrayList;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRule;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class DepartmentLunchRuleMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new DepartmentLunchRuleSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return DepartmentLunchRuleMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new DepartmentLunchRule();
    }

    public void prepare() {
    }

    public void search() {
        setResults(new ArrayList(((RuleService)getService(RuleService.class)).lookupMaintainableDepartmentLunchRules(((DepartmentLunchRuleSearchCriteria)getSearchCriteria()))));
    }
}