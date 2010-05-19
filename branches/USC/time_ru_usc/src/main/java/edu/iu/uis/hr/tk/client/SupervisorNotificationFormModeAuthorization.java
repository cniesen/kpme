package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class SupervisorNotificationFormModeAuthorization extends AbstractAuthorization implements ModeAuthorization {
    
    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof TimeApproverNotificationForm)) {
            throw new IllegalArgumentException("SupervisorNotificationFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable SupervisorNotificationForm object.");
        }
        modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
    }
}
