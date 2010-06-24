package edu.iu.uis.hr.tk;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;

public final class TimeZoneUtility {

	/**
	 * 
	 * @param location
	 * @param time
	 * @returns the three letter code for the location and millisecond time value - will return
	 * xDT/xST depending if the time is in daylight time 
	 */
	public static String getTimeZoneThreeLetterCode(String location, long time){
	    TimeZone clientTimeZone = TimeZone.getTimeZone(Context.getMessage(location));    
	    java.util.Calendar cCal = GregorianCalendar.getInstance(clientTimeZone);
	    cCal.setTimeInMillis(time);
	    Date gDate = cCal.getTime();
	    SimpleDateFormat sdt = new SimpleDateFormat("z");
	    sdt.setTimeZone(clientTimeZone);
	    String gCalZone = sdt.format(gDate);
	    return  gCalZone;
	}
	
	public static Clock setZone(Clock clock, String location){
	    String timeZone = getTimeZoneThreeLetterCode(location,clock.getClockTime().getDate().getTime()); 
	    clock.setTimeZone(timeZone);
		return clock;
	}
	
	public static Clock adjustClock (Clock clock,long now, String location){
	    TimeZone clientTimeZone = TimeZone.getTimeZone(Context.getMessage(location));	 
	    java.util.Calendar cCal = GregorianCalendar.getInstance(clientTimeZone);
	    cCal.setTimeInMillis(now);
	    String sAmPm = null;	    	
		if(cCal.get(java.util.Calendar.AM_PM) == java.util.Calendar.PM){
			sAmPm = "PM";
		}else{
			sAmPm = "AM";
		}		
		int hour = cCal.get(java.util.Calendar.HOUR);
		if(cCal.get(java.util.Calendar.HOUR) == 0){
			hour = 12;
		}
	    clock.getClockTime().setHour(new BigDecimal(hour));
	    clock.getClockTime().setAmPm(sAmPm);	    
	    String timeZone = getTimeZoneThreeLetterCode(location,now); 
	    clock.setTimeZone(timeZone);
	    String dateString = getDateString(cCal.get(java.util.Calendar.DATE),cCal.get(java.util.Calendar.MONTH)+1,cCal.get(java.util.Calendar.YEAR));
	    TimelessDate tDate = new TimelessDate(dateString);	    
	    clock.getClockTime().setDate(tDate.getDate());
	    return clock;
}
	

	
	 public static String getDateString(int day, int month, int year) {
        StringBuffer toString = new StringBuffer(10);
        if (month < 10) {
            toString.append("0");
        }
        toString.append(month).append(Utility.FORWARD_SLASH);
        if (day < 10) {
            toString.append("0");
        }
        toString.append(day).append(Utility.FORWARD_SLASH).append(year);
        return toString.toString();
    }
	 
}

