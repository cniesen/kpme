package edu.iu.uis.hr.tk.rule.client;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.tk.rule.entity.WeeklyOvertimeRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class WeeklyOvertimeRuleMaintenanceForm extends AbstractSystemRuleMaintenanceActionForm {
    private static final Logger LOG = Logger.getLogger(WeeklyOvertimeRuleMaintenanceForm.class);

    public WeeklyOvertimeRuleMaintenanceForm() {
        super();
    }

    protected Class getRuleClass() {
        return new WeeklyOvertimeRule().getClass();
    }

    public void initialize() {
        LOG.info("Started initialize() Method");
        setRules(((RuleService) getService(RuleService.class)).lookupMaintainableWeeklyOvertimeRules());
        LOG.info("Finished initialize() Method");
    }

    public void prepare() {
    }

    public void save() {
        ((RuleService) getService(RuleService.class)).saveWeeklyOvertimeRules(getRules());

    }
}
