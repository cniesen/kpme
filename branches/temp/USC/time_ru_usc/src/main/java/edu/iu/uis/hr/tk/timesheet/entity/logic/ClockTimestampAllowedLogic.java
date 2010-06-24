package edu.iu.uis.hr.tk.timesheet.entity.logic;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class ClockTimestampAllowedLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(ClockTimestampAllowedLogic.class);

//    private TimesheetService timesheetService;
    
    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof Clock)) {
            throw new IllegalArgumentException("ClockActionAllowedLogic's execute(Entity entity) method requires a non-null Entity of type Clock");
        }
        if (Utility.hasValue(((Clock)entity).getClockLog().getClockTime()) && new TimedDate(((Clock)entity).getClockLog().getClockTime()).after(new TimedDate(((Clock)entity).getClockTime()))) {
            entity.getEntityErrors().add(new String[] { FieldNames.CLOCK_TIME }, getMessage(MessageKeyConstants.ERROR_INVALID_CLOCK_TIME, new Object[] { new TimedDate(((Clock)entity).getClockLog().getClockTime()).toString() }));
        } 
    }

    public TimesheetService getTimesheetService() {
        return (TimesheetService) Context.getService(TimesheetService.class);
    }

//    public void setTimesheetService(TimesheetService timesheetService) {
//        if (timesheetService != null) {
//            this.timesheetService = timesheetService;
//        }
//    }

}