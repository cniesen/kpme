package edu.iu.uis.hr.tk.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class UserPreferenceModeAuthorization extends AbstractAuthorization implements ModeAuthorization {
    
    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof UserPreferences)) {
            throw new IllegalArgumentException("UserPreferenceModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable UserPreferences object.");
        }
        modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
    }
}
