package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class UserPreferenceFormModeAuthorization extends AbstractAuthorization implements ModeAuthorization {
    
    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof UserPreferenceForm)) {
            throw new IllegalArgumentException("UserPreferenceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable UserPreferenceForm object.");
        }
        modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
    }
}
