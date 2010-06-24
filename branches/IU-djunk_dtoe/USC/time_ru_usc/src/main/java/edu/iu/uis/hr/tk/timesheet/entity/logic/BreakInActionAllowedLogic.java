package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public class BreakInActionAllowedLogic extends AbstractClockActionAllowedLogic {

    public void execute(Entity entity) {
        setAction(Clock.BREAK_IN);
        super.execute(entity);
    }
}
