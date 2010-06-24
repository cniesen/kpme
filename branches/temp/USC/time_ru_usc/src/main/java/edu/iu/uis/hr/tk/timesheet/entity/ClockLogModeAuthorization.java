package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class ClockLogModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof ClockLog)) {
            throw new IllegalArgumentException("ClockLogModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable ClockLog object.");
        }

        if (getUser().isInterfaceManager()) { //allowing IM to add new clock log entry            
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
        }
        
    }
}
