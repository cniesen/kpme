package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class HoursDetailModeAuthorization extends AbstractAuthorization implements ModeAuthorization {


    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof HoursDetail)) {
            throw new IllegalArgumentException("HoursDetailModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable HoursDetail object.");
        }
        modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
    }

}
