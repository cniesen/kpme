package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class HourDetailDistributionModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!Utility.hasValue(modeApplicable) || !(modeApplicable instanceof HourDetailDistribution)) {
            throw new IllegalArgumentException("HourDetailDistributionModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable HourDetailDistribution object.");
        }
        modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
    }
}
