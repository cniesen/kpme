package edu.iu.uis.hr.tk.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Constants;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.client.BatchProgram;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.entity.UserPreference;
import edu.iu.uis.hr.tk.service.UserPreferenceService;
import edu.iu.uis.hr.tk.timesheet.entity.DailyNotification;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class RemindUserDailyBatchProgram implements BatchProgram {

    private static final Logger LOG = Logger.getLogger(RemindUserDailyBatchProgram.class);
    private DirectoryService directoryService;
    private PayCalendarService payCalendarService;
    private EmailService emailService;
    private UserPreferenceService userPreferenceService;
    private TimesheetService timesheetService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM dd, yyyy");

    private static final String DAILY_REMINDER_CODE = "IN";
    private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
    private static final String MAIL_SUBJECT = "TIME: Timesheet Change";
    private static final String MAIL_MESSAGE_LINE1 = "Your timesheet for the pay period ending on ";
    private static final String MAIL_MESSAGE_LINE2 = " was adjusted by ";
    private static String MAIL_MESSAGE_FOOTER = "\n\n This message is being sent due to a preference in the TIME portal.  You may adjust your settings in the TIME:User Preferences channel in OneStart. ";
	private static final String MAIL_REPORT_SUBJECT = "Time Daily Changes Notification Report"; 
    private StringBuffer documentsModified = new StringBuffer();
    private StringBuffer errorsFound = new StringBuffer();
    private int totalDocumentsModified;

    public void execute() {
        LOG.debug("Executing edu.iu.uis.hr.tk.client.RemindUserDailyBatchProgram");
        double startTime = System.currentTimeMillis();
        Calendar cal = new GregorianCalendar();
        TimelessDate yesterday = new TimelessDate(cal.getTime());
        yesterday = yesterday.addDays(-1);
        TimedDate timedDate = new TimedDate(yesterday.toString(),"12","0","AM");
        Timestamp startDate  = timedDate.getTimestamp();
        timedDate = new TimedDate(yesterday.toString(),"11","59","PM");
        Timestamp endDate  = timedDate.getTimestamp();        
        sendReminderPerPayPeriod(getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).getDate()).getPayEndDate(),startDate,endDate);
        sendReminderPerPayPeriod(getPayCalendarService().getPreviousPayCalendar(new TimelessDate(new Date()).getDate()).getPayEndDate(),startDate,endDate);
        double endTime = System.currentTimeMillis();
        double totalTime = Math.floor( ( ((endTime - startTime) / 1000) / 60.0) * 100) / 100;
        sendReport(totalTime);
    }

    private void sendReport(double totalTime){
    	StringBuffer message = new StringBuffer(" \n Time Daily Changes Notification Report. \n\n Total Time:" + totalTime + " minutes. ");
    	message.append("\n\n Total email notifications sent: " + totalDocumentsModified);
    	message.append("\n\n Errors found: \n\n ").append(errorsFound);
    	emailService.sendMail(Constants.MAIL_REPORT_TO, Constants.MAIL_REPORT_FROM, MAIL_REPORT_SUBJECT, message.toString());
    }

    	
    private void sendReminderPerPayPeriod(Date payEndDate, Timestamp startDate, Timestamp endDate) {
    	Map omitNotification = new HashMap();
    	for (Iterator iter = getUserPreferenceService().getUsersByPreference(DAILY_REMINDER_CODE).iterator(); iter.hasNext();) {
            UserPreference userPreference = (UserPreference)iter.next();
            omitNotification.put(userPreference.getUniversityId(), "true");
        }

    	//All timeblocks modified must be retrieved ordered by documentid!
        List timeBlocksModified = getTimesheetService().getTimeBlockHistoryModifiedRecords(payEndDate, startDate, endDate);
        if (timeBlocksModified.size() > 0){ //adds dummy timeblock to be able to process last real record.
        	DailyNotification dummyTimeBlock = new DailyNotification();
        	dummyTimeBlock.setDocumentId("999");
        	timeBlocksModified.add(dummyTimeBlock);
        }
        String currentDocumentId = new String();
        String messageBegin = MAIL_MESSAGE_LINE1 + dateFormat.format(payEndDate) + MAIL_MESSAGE_LINE2;
        String messageEnd = " on " + dateFormat.format(startDate.getDate())+".";
        String timeBlockModifiers = new String();
        Collection emailList = new ArrayList();
        Collection ccList = new ArrayList();
        for (Iterator iter = timeBlocksModified.iterator(); iter.hasNext();) {
           DailyNotification timeBlockModified = (DailyNotification)iter.next();
           try {
	           if (!omitNotification.containsKey(timeBlockModified.getUniversityId())){
		           if (!Utility.hasValue(currentDocumentId)){ //first document
		        	   currentDocumentId = timeBlockModified.getDocumentId();
		        	   emailList.add(getDirectoryService().getUserByEmployeeId(timeBlockModified.getUniversityId()).getEmailAddress());
		        	   ccList.add((getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getEmailAddress()));
		        	   timeBlockModifiers = getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getName();
		           } else {
		        	   if (timeBlockModified.getDocumentId().equals(currentDocumentId)) { //records with same DocId but different ModifiedBy users
		        		   timeBlockModifiers += ", " +getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getName();
		        		   ccList.add((getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getEmailAddress()));
		        	   } else { //record with different docId: sends email for previous docId
		        		   currentDocumentId = timeBlockModified.getDocumentId();
		        		   if (Utility.hasValue(emailList)) {
			        		   totalDocumentsModified++;
			        		   String message = messageBegin + timeBlockModifiers + messageEnd + MAIL_MESSAGE_FOOTER;
			        		   if (Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
			        			   getEmailService().send(emailList, MAIL_FROM_ADDRESS, MAIL_SUBJECT, message, ccList, null);
			        		   } else {
				                    getEmailService().sendMail(Constants.MAIL_REPORT_TO, MAIL_FROM_ADDRESS, MAIL_SUBJECT, "Message intended for: " + emailList.toString() + ", " + ccList.toString() + " \n\n" + message);
			        		   }
		        		   }
		        		   emailList.clear(); 
		        		   ccList.clear();
		        		   if (Utility.hasValue(timeBlockModified.getUniversityId()) && Utility.hasValue(timeBlockModified.getModifiedByUniversityId())){
			                    emailList.add(getDirectoryService().getUserByEmployeeId(timeBlockModified.getUniversityId()).getEmailAddress());
			        		    timeBlockModifiers = getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getName();
			        		    ccList.add((getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getEmailAddress()));
		        		   }
		        	   }
		            }
	             }
	           } catch (Exception e) {
                   LOG.error("Error sending intended for: " + timeBlockModified.getUniversityId());
                   errorsFound = errorsFound.append(timeBlockModified.getDocumentId()).append(": ").append(e).append("\n"); 
              }
        }
    }

    private UserPreferenceService getUserPreferenceService() {
        return userPreferenceService;
    }

    public void setUserPreferenceService(UserPreferenceService userPreferenceService) {
        if (userPreferenceService != null) {
            this.userPreferenceService = userPreferenceService;
        }
    }

    private PayCalendarService getPayCalendarService() {
        return payCalendarService;
    }

    public void setPayCalendarService(PayCalendarService payCalendarService) {
        if (payCalendarService != null) {
            this.payCalendarService = payCalendarService;
        }
    }

    private EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        if (emailService != null) {
            this.emailService = emailService;
        }
    }

    private TimesheetService getTimesheetService() {
        return timesheetService;
    }

    public void setTimesheetService(TimesheetService timesheetService) {
        if (timesheetService != null) {
            this.timesheetService = timesheetService;
        }
    }

    /**
     * @return the directoryService
     */
    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    /**
     * @param the directoryService to set
     */
    public void setDirectoryService(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

}
