package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.client.MaintenanceActionForm;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class MaintenanceActionFormModeAuthorization extends AbstractAuthorization implements ModeAuthorization {
    
    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof MaintenanceActionForm)) {
            throw new IllegalArgumentException("MaintenanceActionFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable MaintenanceActionForm object.");
        }
        if (getUser().hasDepartmentRole()) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));            
        }
    }
}
