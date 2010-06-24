package edu.iu.uis.hr.tk.rule.client;

import java.util.List;

import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class ClockLocationRuleMaintenanceForm extends AbstractDepartmentRuleMaintenanceActionForm {
    protected Class getRuleClass() {
        return new ClockLocationRule().getClass();
    }

    public void setLookupResults(List lookupResults) {
        getRules().addAll(lookupResults);
    }

    public void prepare() {
        setFieldOptions(FieldNames.LOCATION, ((PositionService) getService(PositionService.class)).getDropdownLocations());
    }

    public void save() {
        ((RuleService) getService(RuleService.class)).saveClockLocationRules(getRules());
    }
}