package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class HourDetailToDistributeModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!Utility.hasValue(modeApplicable) || !(modeApplicable instanceof HoursDetailToDistribute)) {
            throw new IllegalArgumentException("HourDetailToDistributeModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable HourDetailToDistribute object.");
        }
        modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
    }

}
