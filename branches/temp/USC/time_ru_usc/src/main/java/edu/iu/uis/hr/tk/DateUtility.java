package edu.iu.uis.hr.tk;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


public final class DateUtility {
	
	private static final String SERVER_TIMEZONE_ID = "America/New_York";
	private static final String CENTRAL_TIMEZONE_ID = "America/Chicago";
	
	private static final Integer SERVER_RAW_OFFSET = new Integer(TimeZone.getTimeZone(SERVER_TIMEZONE_ID).getRawOffset());
	private static final Integer CENTRAL_RAW_OFFSET = new Integer(TimeZone.getTimeZone(CENTRAL_TIMEZONE_ID).getRawOffset());
	private static final Map offsetMap = new HashMap();
	static {
		offsetMap.put(SERVER_TIMEZONE_ID, SERVER_RAW_OFFSET);
		offsetMap.put(CENTRAL_TIMEZONE_ID, CENTRAL_RAW_OFFSET);
	}
	

	public static void main(String[] args){

		  DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		  Date newDate;
		  Calendar cal;
		  
		  // October 29, 2006, 12:00:00 AM EDT
		  cal = new GregorianCalendar(2006,9,29);
		  
		  for (int i=0; i<5;i++) {
			  println("If we come from " + CENTRAL_TIMEZONE_ID + " with time " + df.format(cal.getTime()) + " on the server,");
			  newDate = DateUtility.withZoneRetainFields(cal.getTime(), CENTRAL_TIMEZONE_ID);
			  println("We would record " + df.format(newDate) + " in the db.");
			  printSeparator();
			  cal.add(Calendar.HOUR, 1);		  
		  }
		  
		  printSeparator();
		  printSeparator();
		  
		  // March 11, 2007, 12:00:00 AM EST
		  cal = new GregorianCalendar(2007,2,11);
		  
		  for (int i=0; i<5;i++) {
			  println("If we come from " + CENTRAL_TIMEZONE_ID + " with time " + df.format(cal.getTime()) + " on the server,");
			  newDate = DateUtility.withZoneRetainFields(cal.getTime(), CENTRAL_TIMEZONE_ID);
			  println("We would record " + df.format(newDate) + " in the db.");
			  printSeparator();
			  cal.add(Calendar.HOUR, 1);		  
		  }
		  
	}

	
	/* Given a java date and time zone id,
	 * return a java date representing the
	 * same wall time in the server time zone. 
	 * Example: if we pass in a Dec 14, 2006 4:00:00 PM and "America/Central"
	 * we expect to get back Dec 14, 2006 3:00:00 PM.
	 * Example use: when a user clocks in for a central time job,
	 * we'd pass in new Date() and "America/Central". The return
	 * value is what's displayed to the user ( new Date() - 1 hour) and stored in the
	 * database, along with the "America/Central" info. */
	
	
	  
	 // TODO: 
	 // --TEST THIS WHEN CROSSING DST - done
	 // --TRY HOOKING IT UP TO THE CLOCK
	 // --UNRELATED, BUT PUT TOGETHER THE HEIRARCHICAL RULE FUNCTIONALITY FOR LOCATION OVERRIDE
	public static Date withZoneRetainFields(Date inDate, String inTimeZoneID) {
		
		int offset = ((Integer)offsetMap.get(SERVER_TIMEZONE_ID)).intValue() - ((Integer)offsetMap.get(inTimeZoneID)).intValue(); 
		
		return new Date(inDate.getTime() - offset);
		
	}
	
	public static void println(String s) {
		System.out.println(s);
	}
	public static void print(String s) {
		System.out.print(s);
	}
	public static void printSeparator() {
		println("-----------------------------------------------------------------------------------");
	}
}
