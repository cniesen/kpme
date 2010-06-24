package edu.iu.uis.hr.tk.rule.client;

import java.util.List;

import edu.iu.uis.hr.tk.rule.entity.HourlyHoursRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class HourlyHoursRuleMaintenanceForm extends AbstractDepartmentRuleMaintenanceActionForm {
    protected Class getRuleClass() {
        return new HourlyHoursRule().getClass();
    }

    public void setLookupResults(List lookupResults) {
        getRules().addAll(lookupResults);
    }
    
    public void prepare() {
    }

    public void save() {
        ((RuleService) getService(RuleService.class)).saveHourlyHoursRules(getRules());
    }
}