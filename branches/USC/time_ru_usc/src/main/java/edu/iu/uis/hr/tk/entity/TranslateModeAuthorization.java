package edu.iu.uis.hr.tk.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class TranslateModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof Translate)) {
            throw new IllegalArgumentException("TranslateModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable Translate object.");
        }
        if (getUser().hasSystemRole()) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
        }
    }
}
