package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class HoursModeAuthorization extends AbstractAuthorization implements ModeAuthorization {


    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof Hours)) {
            throw new IllegalArgumentException("HoursModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable Hours object.");
        }
        modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
    }

}
