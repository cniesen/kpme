package edu.iu.uis.hr.tk.rule.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;
import edu.iu.uis.hr.tk.rule.entity.SystemRule;

public class SystemRuleMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof AbstractRuleMaintenanceActionForm && SystemRule.class.isAssignableFrom(((AbstractRuleMaintenanceActionForm)modeApplicable).getRuleClass()))) {
            throw new IllegalArgumentException("SystemRuletenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable AbstractRuleMaintenanceActionForm object and systemrule.");
        }
        if (getUser().hasSystemRole()) {
            modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
        }
    }
}
