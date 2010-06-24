package edu.iu.uis.hr.tk.rule.client;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRule;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRuleSearchCriteria;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class ShiftDifferentialRuleMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new ShiftDifferentialRuleSearchCriteria();
    }

    public Class getMaintenanceFormClass() {
        return ShiftDifferentialRuleMaintenanceForm.class;
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new ShiftDifferentialRule();
    }

    public void prepare() {
        setFieldOptions(FieldNames.LOCATION, ((PositionService) getService(PositionService.class)).getDropdownLocations());
    }

    public void search() {
        setResults(((RuleService) getService(RuleService.class)).lookupMaintainableShiftDifferentialRules(((ShiftDifferentialRuleSearchCriteria) getSearchCriteria())));
    }
}