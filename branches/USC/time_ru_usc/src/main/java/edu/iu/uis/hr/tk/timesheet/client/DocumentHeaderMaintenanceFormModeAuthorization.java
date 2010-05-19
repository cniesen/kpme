package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;

public class DocumentHeaderMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof DocumentHeaderMaintenanceForm)) {
            throw new IllegalArgumentException("DocumentHeaderMaintenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable DocumentHeaderMaintenanceForm object.");
        }
        if (getUser().hasSystemRole()) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
        }    
    }
}
