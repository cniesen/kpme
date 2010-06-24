package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public class LunchInActionAllowedLogic extends AbstractClockActionAllowedLogic {

    public void execute(Entity entity) {
        setAction(Clock.LUNCH_IN);
        super.execute(entity);
    }
}
