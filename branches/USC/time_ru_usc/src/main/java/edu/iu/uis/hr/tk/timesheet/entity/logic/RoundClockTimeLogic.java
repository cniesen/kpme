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
import edu.iu.uis.hr.tk.rule.entity.GracePeriodRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public class RoundClockTimeLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(RoundClockTimeLogic.class);

//    private RuleService ruleService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof Clock)) {
            throw new IllegalArgumentException("RoundClockTimeLogic's execute(Entity entity) method requires a non-null Entity of type Clock");
        }

        Timestamp clockTime = ((Clock)entity).getClockTime();
        //Setting seconds to 0 regardless to avoid creation of duplicate timeblocks
        ((Clock)entity).getClockTime().setSecond(new BigDecimal(0).setScale(0));
        
        GracePeriodRule gracePeriodRule = getRuleService().getGracePeriodRule(new TimedDate(clockTime).getDate());
        if (Utility.hasValue(gracePeriodRule)) {
            BigDecimal minuteIncrement = gracePeriodRule.getHourFactor().multiply(new BigDecimal(60));
            int bottomIntervalFactor = clockTime.getMinute().divide(minuteIncrement, 0, BigDecimal.ROUND_FLOOR).intValue();
            BigDecimal bottomInterval = new BigDecimal(bottomIntervalFactor).multiply(minuteIncrement);
            BigDecimal topInterval = new BigDecimal(bottomIntervalFactor + 1).multiply(minuteIncrement);
            if (bottomInterval.subtract(clockTime.getMinute()).abs().intValue() < topInterval.subtract(clockTime.getMinute()).abs().intValue()) {
                if (bottomInterval.subtract(clockTime.getMinute()).abs().intValue() <= gracePeriodRule.getGraceMinutes().intValue()) {
                    ((Clock)entity).getClockTime().setMinute(bottomInterval.setScale(0,BigDecimal.ROUND_HALF_UP));
                }
            } else {
                if (topInterval.subtract(clockTime.getMinute()).abs().intValue() <= gracePeriodRule.getGraceMinutes().intValue()) {
                    if (topInterval.equals(new BigDecimal(60))) {
                        ((Clock)entity).getClockTime().setHour(((Clock)entity).getClockTime().getHour().add(new BigDecimal(1).setScale(0)));
                        ((Clock)entity).getClockTime().setMinute(new BigDecimal(0).setScale(0));
                    } else {
                        ((Clock)entity).getClockTime().setMinute(topInterval.setScale(0,BigDecimal.ROUND_HALF_UP));
                    }
                }
            }
        }
    }

    public RuleService getRuleService() {
        return (RuleService) Context.getService(RuleService.class);
    }

//    public void setRuleService(RuleService ruleService) {
//        if (ruleService != null) {
//            this.ruleService = ruleService;
//        }
//    }

}
