package edu.iu.uis.hr.tk.timesheet.entity.logic;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public abstract class AbstractClockActionAllowedLogic    extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(AbstractClockActionAllowedLogic.class);

//    private TimesheetService timesheetService;
    private String action;   

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof Clock)) {
            throw new IllegalArgumentException("ClockActionAllowedLogic's execute(Entity entity) method requires a non-null Entity of type Clock");
        }
        if ((Clock.ON_THE_CLOCK_CODES.contains(((Clock)entity).getClockLog().getAction()) && Clock.ON_THE_CLOCK_CODES.contains(getAction())) || (Clock.OFF_THE_CLOCK_CODES.contains(((Clock)entity).getClockLog().getAction()) && Clock.OFF_THE_CLOCK_CODES.contains(getAction()))) {
            //TODO:  Putting an error on a field doesn't make sense in this case
            entity.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.ERROR_INVALID_CLOCK_ACTION));            
        }
        
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        if (action != null) {
            this.action = action;
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