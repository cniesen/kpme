package edu.iu.uis.hr.tk.rule.client;

import edu.iu.uis.hr.tk.rule.entity.GracePeriodRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class GracePeriodRuleMaintenanceForm extends AbstractSystemRuleMaintenanceActionForm {
    protected Class getRuleClass() {
        return new GracePeriodRule().getClass();
    }

    public void initialize() {
        setRules(((RuleService)getService(RuleService.class)).lookupMaintainableGracePeriodRules());
    }

    public void prepare() {
    }

    public void save() {
        ((RuleService)getService(RuleService.class)).saveGracePeriodRules(getRules());
    }
}