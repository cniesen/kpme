package edu.iu.uis.hr.tk.extract.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class PayrollExtractRunFormModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof PayrollExtractRunForm)) {
            throw new IllegalArgumentException("PayrollExtractRunFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable PayrollExtractRunForm object.");
        }
        modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
    }

}
