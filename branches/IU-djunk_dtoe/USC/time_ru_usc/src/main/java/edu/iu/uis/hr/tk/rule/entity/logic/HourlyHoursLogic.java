package edu.iu.uis.hr.tk.rule.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.MessageKeyConstants;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.rule.entity.HourlyHoursRule;

public class HourlyHoursLogic extends AbstractLogic implements OperationalLogic {
    
    private static final int DAILY_HOURS = 24;
    private static final int WEEKLY_HOURS = 168;
    
    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof HourlyHoursRule)) {
            throw new IllegalArgumentException("MaxDailyHoursValidationLogic's execute(Entity entity) method requires a non-null Entity of type HourlyHoursRule");
        }

        if ( ((HourlyHoursRule)entity).getDailyMinHours().intValue() > DAILY_HOURS) {
            entity.getEntityErrors().add(new String[] { FieldNames.DAILY_MIN_HOURS }, getMessage(MessageKeyConstants.ERROR_INVALID_RANGE_VALUE, new String[] {getLabel(FieldNames.DAILY_MIN_HOURS),"0", String.valueOf(DAILY_HOURS)}));
        }
        if ( ((HourlyHoursRule)entity).getDailyMaxHours().intValue() > DAILY_HOURS) {
            entity.getEntityErrors().add(new String[] { FieldNames.DAILY_MAX_HOURS }, getMessage(MessageKeyConstants.ERROR_INVALID_RANGE_VALUE, new String[] {getLabel(FieldNames.DAILY_MAX_HOURS),"0", String.valueOf(DAILY_HOURS)}));
        }
        if ( ((HourlyHoursRule)entity).getWeeklyMinHours().intValue() > WEEKLY_HOURS) {
            entity.getEntityErrors().add(new String[] { FieldNames.WEEKLY_MIN_HOURS }, getMessage(MessageKeyConstants.ERROR_INVALID_RANGE_VALUE, new String[] {getLabel(FieldNames.WEEKLY_MIN_HOURS),"0", String.valueOf(WEEKLY_HOURS)}));
        }
        if ( ((HourlyHoursRule)entity).getWeeklyMaxHours().intValue() > WEEKLY_HOURS) {
            entity.getEntityErrors().add(new String[] { FieldNames.WEEKLY_MAX_HOURS }, getMessage(MessageKeyConstants.ERROR_INVALID_RANGE_VALUE, new String[] {getLabel(FieldNames.WEEKLY_MAX_HOURS),"0", String.valueOf(WEEKLY_HOURS)}));
        }
    }

}


