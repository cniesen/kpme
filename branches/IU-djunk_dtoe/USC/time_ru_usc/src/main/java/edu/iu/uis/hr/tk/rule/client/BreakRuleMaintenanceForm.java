package edu.iu.uis.hr.tk.rule.client;

import java.util.List;

import edu.iu.uis.hr.tk.rule.entity.BreakRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class BreakRuleMaintenanceForm extends AbstractDepartmentRuleMaintenanceActionForm {
    protected Class getRuleClass() {
        return BreakRule.class;
    }

    public void setLookupResults(List lookupResults) {
        getRules().addAll(lookupResults);
    }

    public void prepare() {
    }

    public void save() {
        ((RuleService) getService(RuleService.class)).saveBreakRules(getRules());
    }
}