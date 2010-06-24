package edu.iu.uis.hr.tk.extract.entity.logic;

import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;

public class PayrollExtractScheduleExtractDateAfterPayEndDateLogic extends AbstractLogic implements OperationalLogic {

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof PayrollExtractSchedule)) {
            throw new IllegalArgumentException("PayrollExtractScheduleExtractDateAfterPayEndDateLogic's execute(Entity entity) method requires a non-null Entity of type PayrollExtractSchedule");
        }

        TimedDate proposedExtractDate = new TimedDate(((PayrollExtractSchedule)entity).getScheduledBeginTime());

        if (proposedExtractDate.before(new TimedDate(((PayrollExtractSchedule)entity).getPayEndDate()))) {
            entity.getEntityErrors().add(new String[] { FieldNames.PAY_END_DATE }, getMessage(MessageKeyConstants.ERROR_EXTRACT_SCHEDULE_DATE_BEFORE_PAY_END_DT));
        }
    }
}