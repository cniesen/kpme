package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public class BreakOutActionAllowedLogic extends AbstractClockActionAllowedLogic {

    public void execute(Entity entity) {
        setAction(Clock.BREAK_OUT);
        super.execute(entity);
    }
}
