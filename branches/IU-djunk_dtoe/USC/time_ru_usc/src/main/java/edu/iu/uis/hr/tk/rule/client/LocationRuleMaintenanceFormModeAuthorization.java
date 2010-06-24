package edu.iu.uis.hr.tk.rule.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;
import edu.iu.uis.hr.tk.rule.entity.LocationRule;

public class LocationRuleMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof AbstractRuleMaintenanceActionForm && LocationRule.class.isAssignableFrom(((AbstractRuleMaintenanceActionForm) modeApplicable).getRuleClass()))) {
            throw new IllegalArgumentException("LocationRuletenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable AbstractRuleMaintenanceActionForm and LocationRule object.");
        }
        if (getUser().hasLocationRole()) {
            modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
        }
    }

}
