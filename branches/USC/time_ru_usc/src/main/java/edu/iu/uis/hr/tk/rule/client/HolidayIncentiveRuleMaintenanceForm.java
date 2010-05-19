package edu.iu.uis.hr.tk.rule.client;

import java.util.List;

import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.rule.entity.HolidayIncentiveRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class HolidayIncentiveRuleMaintenanceForm extends AbstractLocationRuleMaintenanceActionForm {
    
    protected Class getRuleClass() {
        return new HolidayIncentiveRule().getClass();
    }

    public void setLookupResults(List lookupResults) {
        getRules().addAll(lookupResults);
    }

    public void prepare() {
        setFieldOptions(FieldNames.LOCATION, ((PositionService) getService(PositionService.class)).getDropdownLocations());
    }

    public void save() {
        ((RuleService) getService(RuleService.class)).saveHolidayIncentiveRules(getRules());
    }
}