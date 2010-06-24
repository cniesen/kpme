package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.rule.service.RuleService;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public class RemoveBreakSecondsLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(RemoveBreakSecondsLogic.class);

    private RuleService ruleService;
    private WebUserService webUserService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof Clock)) {
            throw new IllegalArgumentException("RemoveBreakSecondsLogic's execute(Entity entity) method requires a non-null Entity of type Clock");
        }
        if (!Utility.hasValue(((Clock)entity).getClockTime()) || ((Clock)entity).getClockLog().getUniversityId().equals(getWebUserService().getUser().getUniversityId())) {
            ((Clock)entity).setClockTime(new TimedDate().getTimestamp());
        }
        Timestamp clockTime = ((Clock)entity).getClockTime();
        //Setting seconds to 0 regardless to avoid creation of duplicate timeblocks
        ((Clock)entity).getClockTime().setSecond(new BigDecimal(0).setScale(0));
        
    }

    public RuleService getRuleService() {
        return (RuleService) Context.getService(RuleService.class);
    }

    public WebUserService getWebUserService() {
        return (WebUserService) Context.getService(WebUserService.class);
    }
}
