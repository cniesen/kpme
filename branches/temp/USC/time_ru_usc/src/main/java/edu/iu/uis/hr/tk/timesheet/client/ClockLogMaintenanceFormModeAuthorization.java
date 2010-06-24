package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;

public class ClockLogMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof ClockLogMaintenanceForm)) {
            throw new IllegalArgumentException("ClockLogMaintenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable ClockLogMaintenanceForm object.");
        }
        if (getUser().isInterfaceManager()) {
            modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
        } 
    }

}
