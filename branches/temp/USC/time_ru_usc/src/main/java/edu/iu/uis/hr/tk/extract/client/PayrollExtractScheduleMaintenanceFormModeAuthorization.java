package edu.iu.uis.hr.tk.extract.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;

public class PayrollExtractScheduleMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof PayrollExtractScheduleMaintenanceForm)) {
            throw new IllegalArgumentException("PayrollExtractScheduleMaintenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable PayrollExtractScheduleMaintenanceForm object.");
        }
        if (getUser().hasSystemRole()) {
            modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
        }
    }

}
