package edu.iu.uis.hr.tk.rule.client;

import java.util.ArrayList;
import java.util.Date;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRule;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class DailyOvertimeRuleMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new DailyOvertimeRuleSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return DailyOvertimeRuleMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new DailyOvertimeRule();
    }

    public void prepare() {
        setFieldOptions(edu.iu.uis.hr.entity.FieldNames.OVERTIME_PREFERENCE, ((EarningService)getService(EarningService.class)).getDropdownWeeklyOvertimeEarnCodes(new Date()));    	
    }

    public void search() {
        setResults(new ArrayList(((RuleService) getService(RuleService.class)).lookupMaintainableDailyOvertimeRules(((DailyOvertimeRuleSearchCriteria) getSearchCriteria()))));
    }
}