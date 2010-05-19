package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class TimeBlockHistoryModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof TimeBlockHistory)) {
            throw new IllegalArgumentException("TimeBlockHistoryModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable TimeBlockHistory object.");
        }
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
    }
}
