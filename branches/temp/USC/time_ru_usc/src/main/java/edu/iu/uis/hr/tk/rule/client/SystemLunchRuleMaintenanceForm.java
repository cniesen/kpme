package edu.iu.uis.hr.tk.rule.client;

import edu.iu.uis.hr.tk.rule.entity.SystemLunchRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class SystemLunchRuleMaintenanceForm extends AbstractSystemRuleMaintenanceActionForm {
    protected Class getRuleClass() {
        return new SystemLunchRule().getClass();
    }

    public void initialize() {
        setRules(((RuleService) getService(RuleService.class)).lookupMaintainableSystemLunchRules());
    }

    public void prepare() {
    }

    public void save() {
        ((RuleService) getService(RuleService.class)).saveSystemLunchRules(getRules());
    }
}