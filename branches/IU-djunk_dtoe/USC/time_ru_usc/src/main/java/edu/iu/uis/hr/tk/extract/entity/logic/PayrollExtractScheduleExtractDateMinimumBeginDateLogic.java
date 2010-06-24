package edu.iu.uis.hr.tk.extract.entity.logic;

import java.util.Date;

import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;

public class PayrollExtractScheduleExtractDateMinimumBeginDateLogic extends AbstractLogic implements OperationalLogic {

    private static final int HOURS_INTO_FUTURE_TO_WAIT = 4;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof PayrollExtractSchedule)) {
            throw new IllegalArgumentException("PayrollExtractScheduleExtractDateBeginDateLogic's execute(Entity entity) method requires a non-null Entity of type PayrollExtractSchedule");
        }

        TimedDate proposedExtractDate = new TimedDate(((PayrollExtractSchedule)entity).getScheduledBeginTime());

        if (proposedExtractDate.before(new TimedDate(new Date()).addHours(HOURS_INTO_FUTURE_TO_WAIT))) {
            entity.getEntityErrors().add(new String[] { FieldNames.SCHEDULED_BEGIN_TIME }, getMessage(MessageKeyConstants.ERROR_EXTRACT_SCHEDULE_DATE_BEFORE_TODAY, new String[] { new TimedDate().addHours(HOURS_INTO_FUTURE_TO_WAIT).toString() }));
        }
    }
}