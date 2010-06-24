package edu.iu.uis.hr.tk.rule.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;
import edu.iu.uis.hr.tk.rule.entity.DepartmentRule;

public class DepartmentRuleMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof AbstractRuleMaintenanceActionForm && DepartmentRule.class.isAssignableFrom(((AbstractRuleMaintenanceActionForm) modeApplicable).getRuleClass()))) {
            throw new IllegalArgumentException("DepartmentRuletenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable AbstractRuleMaintenanceActionForm and department rule object.");
        }
        if (getUser().hasDepartmentRole()) {
            modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
        }
    }
}
