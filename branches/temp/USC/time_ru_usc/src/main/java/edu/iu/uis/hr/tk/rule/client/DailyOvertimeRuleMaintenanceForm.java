package edu.iu.uis.hr.tk.rule.client;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class DailyOvertimeRuleMaintenanceForm extends AbstractDepartmentRuleMaintenanceActionForm {
    protected Class getRuleClass() {
        return new DailyOvertimeRule().getClass();
    }

    public void setLookupResults(List lookupResults) {
        getRules().addAll(lookupResults);
    }

    public void prepare() {
        setFieldOptions(edu.iu.uis.hr.entity.FieldNames.OVERTIME_PREFERENCE, ((EarningService)getService(EarningService.class)).getDropdownWeeklyOvertimeEarnCodes(new Date()));    	
    }

    public void save() {
        ((RuleService) getService(RuleService.class)).saveDailyOvertimeRules(getRules());
    }
}