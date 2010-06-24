package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class OtherTimesheetsSearchCriteriaModeAuthorization extends AbstractAuthorization implements ModeAuthorization {
    
    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof OtherTimesheetsLookupForm)) {
            throw new IllegalArgumentException("OtherTimesheetsSearchCriteriaModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable OtherTimesheetsSearchCriteria object.");
        }
        modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
    }
}
