package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.entity.EntityMode;

public class CheckedPayCalendarDateMode extends EntityMode {

    public CheckedPayCalendarDateMode() {
        super();
    }

    public CheckedPayCalendarDateMode(boolean editable, ModeApplicable modeApplicable) {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
        return edu.iu.uis.hr.Utility.getDefaultStringValue();
    }

}
