package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.ModeApplicable;

public class TranslateMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof TranslateMaintenanceForm)) {
            throw new IllegalArgumentException("TranslateMaintenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable TranslateMaintenanceForm object.");
        }
        if (getUser().hasSystemRole()) {
            modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
        }
    }

}
