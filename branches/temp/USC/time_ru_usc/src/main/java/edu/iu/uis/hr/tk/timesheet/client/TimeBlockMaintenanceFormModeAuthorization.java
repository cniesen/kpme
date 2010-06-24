package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;

public class TimeBlockMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof TimeBlockMaintenanceForm)) {
            throw new IllegalArgumentException("TimeBlockMaintenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable TimeBlockMaintenanceForm object.");
        }
        if (getUser().isInterfaceManager()) {
            modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
        } else {
            throw new IllegalArgumentException("You're not authorized to access this functionality.");
        }
    }

}
