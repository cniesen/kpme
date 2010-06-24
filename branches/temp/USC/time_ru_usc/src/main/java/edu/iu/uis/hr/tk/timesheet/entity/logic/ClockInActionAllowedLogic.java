package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public class ClockInActionAllowedLogic extends AbstractClockActionAllowedLogic {

	private static final int CURRENT_TIME_OFFSET = 15;
    
    public void execute(Entity entity) {
        setAction(Clock.CLOCK_IN);

        Calendar currentTime = new GregorianCalendar();
        currentTime.setTime(new Date());
        currentTime.add(Calendar.MINUTE, CURRENT_TIME_OFFSET); // CAN CLOCK IN THIS MANY MINUTES INTO THE FUTURE 

        Calendar suggestedTime = new GregorianCalendar();
        suggestedTime.setTime(((Clock)entity).getClockTime().getDate());
        suggestedTime.set(Calendar.HOUR_OF_DAY, ((Clock)entity).getClockTime().get24Hour());
        suggestedTime.set(Calendar.MINUTE, ((Clock)entity).getClockTime().getMinute().intValue());
        
        if (Clock.CLOCK_IN.equals(getAction())  &&  suggestedTime.after(currentTime))  {
            entity.getEntityErrors().add(new String[] { FieldNames.CLOCK_TIME }, getMessage(MessageKeyConstants.ERROR_INVALID_CLOCK_DATE));            
        }
        
        super.execute(entity);
    }
    
}
