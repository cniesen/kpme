package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.StrutsActionFormMode;

public class HourDetailCopyFormMode extends StrutsActionFormMode {

    public HourDetailCopyFormMode() {
        super();
    }

    public HourDetailCopyFormMode(boolean editable, ModeApplicable modeApplicable) {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
        return new StringBuffer("copyHourDetailsToForm").append(edu.iu.uis.hr.Utility.COMMA).append("returnToTimesheet").toString();
    }

}
