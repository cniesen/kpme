package edu.iu.uis.hr.tk.batch.job.runnables;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import edu.iu.uis.hr.Constants;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.timesheet.entity.DailyNotification;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;

public class RemindUserDailyBatchTKRunnable extends AbstractTKBatchRunnable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3226129406185436240L;
	
	private static final Logger LOG = Logger.getLogger(RemindUserDailyBatchTKRunnable.class);
    
    private static final String DAILY_REMINDER_CODE = "IN";
    private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
    private static final String MAIL_SUBJECT = "TIME: Timesheet Change";
    private static final String MAIL_MESSAGE_LINE1 = "Your timesheet for the pay period ending on ";
    private static final String MAIL_MESSAGE_LINE2 = " was adjusted by ";
    private static String MAIL_MESSAGE_FOOTER = "\n\n This message is being sent due to a preference in the TIME portal.  You may adjust your settings in the TIME:User Preferences channel in OneStart. ";
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM dd, yyyy");
	
	private DailyNotification dailyNotification;
	private DocumentHeader documentHeader;
	
	public RemindUserDailyBatchTKRunnable(DailyNotification dailyNotification) {
		this.dailyNotification = dailyNotification;
	}

	public RemindUserDailyBatchTKRunnable(DocumentHeader documentHeader) {
		this.documentHeader = documentHeader;
	}
	public void run() {
		DailyNotification modifiedDocument = dailyNotification;
		if (TKServiceLocator.getUserPreferenceService().getUserPreference(modifiedDocument.getUniversityId(), DAILY_REMINDER_CODE) != null) {
			return; //if daily_reminder preference exists, the email notification SHOULD NOT be sent
		}
		
		Calendar cal = new GregorianCalendar();
		TimelessDate yesterday = new TimelessDate(cal.getTime());
		yesterday = yesterday.addDays(-1);
		TimedDate timedDate = new TimedDate(yesterday.toString(),"12","0","AM");
     	Timestamp startDate  = timedDate.getTimestamp();
     	timedDate = new TimedDate(yesterday.toString(),"11","59","PM");
     	Timestamp endDate  = timedDate.getTimestamp();  
    
     	List timeBlocksModified = TKServiceLocator.getTimesheetService().getTimeBlockHistoryModifiedRecords(
			 modifiedDocument.getPayEndDate(), startDate, endDate, modifiedDocument.getDocumentId());
    
     	String currentDocumentId = new String();
     	String messageBegin = MAIL_MESSAGE_LINE1 + dateFormat.format(modifiedDocument.getPayEndDate()) + MAIL_MESSAGE_LINE2;
    	String messageEnd = " on " + dateFormat.format(startDate.getDate())+".";
    	String timeBlockModifiers = new String();
    	Collection emailList = new ArrayList();
    	Collection ccList = new ArrayList();
    

    	   for (Iterator iter = timeBlocksModified.iterator(); iter.hasNext();) {
	           DailyNotification timeBlockModified = (DailyNotification)iter.next();
               if (!Utility.hasValue(currentDocumentId)){ 
		        	   currentDocumentId = timeBlockModified.getDocumentId();
		        	   emailList.add(TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getUniversityId()).getEmailAddress());
		        	   ccList.add((TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getEmailAddress()));
		        	   timeBlockModifiers = TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getName();
		           } else {
		        		   timeBlockModifiers += ", " + TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getName();
		        		   ccList.add((TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getEmailAddress()));
		           }
	       }//endFor
    	   if(!StringUtils.isBlank(timeBlockModifiers)){
    		   String message = messageBegin + timeBlockModifiers + messageEnd + MAIL_MESSAGE_FOOTER;
    		   
    		   if (Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
    			   getEmailService().send(emailList, MAIL_FROM_ADDRESS, MAIL_SUBJECT, message, ccList, null);
    		   } else {
    			   getEmailService().sendMail(Constants.MAIL_REPORT_TO, MAIL_FROM_ADDRESS, MAIL_SUBJECT, "Message intended for: " + emailList.toString() + ", " + ccList.toString() + " \n\n" + message);
    		   }
    	   }
    	   else{
    		   LOG.info("No timeblocks were modified for "+modifiedDocument.getDocumentId());
    	   }
	}
	
	public String getDataId() {
		return dailyNotification.getDocumentId();
	}

    public EmailService getEmailService(){
    	return TKServiceLocator.getEmailService();
    }
}
