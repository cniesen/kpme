package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.EntityMode;
import edu.iu.uis.hr.tk.timesheet.client.TimesheetDocumentAction;

public class ClockMode extends EntityMode {

    public ClockMode() {
        super();
    }

    public ClockMode(boolean editable, ModeApplicable modeApplicable) {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
        if (isEditable()) {
            if (((Clock)getModeApplicable()).isOnTheClock()) {
                if (((Clock)getModeApplicable()).isClockRequiredForLunch()) {
                     //removing break out button temporarily 
                    //return new StringBuffer(TimesheetDocumentAction.CLOCK_OUT_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(TimesheetDocumentAction.LUNCH_OUT_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(TimesheetDocumentAction.BREAK_OUT_DISPATCH_PARAM_VALUE).toString();
                    return new StringBuffer(TimesheetDocumentAction.CLOCK_OUT_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(TimesheetDocumentAction.LUNCH_OUT_DISPATCH_PARAM_VALUE).toString();
                } else {
                    //removing break out button temporarily 
                    //return new StringBuffer(TimesheetDocumentAction.CLOCK_OUT_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(TimesheetDocumentAction.BREAK_OUT_DISPATCH_PARAM_VALUE).toString();
                    return new StringBuffer(TimesheetDocumentAction.CLOCK_OUT_DISPATCH_PARAM_VALUE).toString();
                }
            } else if (((Clock)getModeApplicable()).isOutToLunch()) {
                return new StringBuffer(TimesheetDocumentAction.LUNCH_IN_DISPATCH_PARAM_VALUE).toString();
            } else if (((Clock)getModeApplicable()).isOutOnBreak()) {
                return new StringBuffer(TimesheetDocumentAction.BREAK_IN_DISPATCH_PARAM_VALUE).toString();
            }
            return new StringBuffer(TimesheetDocumentAction.CLOCK_IN_DISPATCH_PARAM_VALUE).toString();
        }
        return Utility.getDefaultStringValue();
    }

}
