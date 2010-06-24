package edu.iu.uis.hr.tk.batch.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.TKBatchJobPopulator;
import edu.iu.uis.hr.tk.batch.TKBatchRunnable;
import edu.iu.uis.hr.tk.batch.job.runnables.RemindUserDailyBatchTKRunnable;
import edu.iu.uis.hr.tk.timesheet.entity.DailyNotification;
import edu.iu.uis.hr.tk.util.TkConstants;

public class RemindUserDailyBatchJob extends TKBatchJobPopulator {

	private static final long serialVersionUID = 7494693117574945228L;
	
//	@SuppressWarnings("unused")
//	private static final Logger LOG = Logger.getLogger(RemindUserDailyBatchJob.class);
//    
//    private static final String DAILY_REMINDER_CODE = "IN";
//    private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
//    private static final String MAIL_SUBJECT = "TIME: Timesheet Change";
//    private static final String MAIL_MESSAGE_LINE1 = "Your timesheet for the pay period ending on ";
//    private static final String MAIL_MESSAGE_LINE2 = " was adjusted by ";
//    private static String MAIL_MESSAGE_FOOTER = "\n\n This message is being sent due to a preference in the TIME portal.  You may adjust your settings in the TIME:User Preferences channel in OneStart. ";
//    @SuppressWarnings("unchecked")
//	public  List omitNotification = new ArrayList();
    
	@Override
	public String getName() {
		return "RemindUserDailyBatchJob";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TKBatchRunnable> getTKBatchRunnables() {
		Calendar cal = new GregorianCalendar();
		TimelessDate yesterday = new TimelessDate(cal.getTime());
		yesterday = yesterday.addDays(-1);
		TimedDate timedDate = new TimedDate(yesterday.toString(), "12", "0",
				"AM");
		Timestamp startDate = timedDate.getTimestamp();
		timedDate = new TimedDate(yesterday.toString(), "11", "59", "PM");
		Timestamp endDate = timedDate.getTimestamp();
		Date payEndDate = TKServiceLocator.getPayCalendarService()
				.getPayCalendar(new TimelessDate(new Date()).getDate())
				.getPayEndDate();

		List<DailyNotification> modifiedDocuments = TKServiceLocator
				.getTimesheetService().getTimeBlockHistoryModifiedDocuments(
						payEndDate, startDate, endDate);

		sendKickOffEmail(modifiedDocuments.size());
		List<TKBatchRunnable> tkbatchRunnables = new ArrayList<TKBatchRunnable>();
		for (DailyNotification documentHeader : modifiedDocuments) {
			tkbatchRunnables.add(new RemindUserDailyBatchTKRunnable(documentHeader));
		}
      
		return tkbatchRunnables;
	}

	@Override
	public String getType() {
		return TkConstants.BatchJobTypes.Document;
	}
    

    
    
    
//    public RemindUserDailyBatchJob(){
//    	setName("RemindUserDailyBatchJob");
//    }
//    
//	@SuppressWarnings("unchecked")
//	@Override
//	protected BatchJobDescription setupJobs() {
//        Calendar cal = new GregorianCalendar();
//        TimelessDate yesterday = new TimelessDate(cal.getTime());
//        yesterday = yesterday.addDays(-1);
//        TimedDate timedDate = new TimedDate(yesterday.toString(),"12","0","AM");
//        Timestamp startDate  = timedDate.getTimestamp();
//        timedDate = new TimedDate(yesterday.toString(),"11","59","PM");
//        Timestamp endDate  = timedDate.getTimestamp();  
//		Date payEndDate = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(
//				new Date()).getDate()).getPayEndDate();
//
//        List modifiedDocuments = TKServiceLocator.getTimesheetService().getTimeBlockHistoryModifiedDocuments(
//				payEndDate, startDate, endDate);
//        sendKickOffEmail(modifiedDocuments.size());
//        
//        return new BatchJobDescription(getName(), new java.sql.Timestamp(System.currentTimeMillis()), null, modifiedDocuments);
//	}
//	
//	public static class RemindUserDailyBatchRunnable extends BatchRunnable{
//		private static final long serialVersionUID = 5661744373713625863L;
//		
//		private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM dd, yyyy");
//		
//		
//		
//		@SuppressWarnings("unchecked")
//		@Override
//		protected void doWork() {
//					
//					DocumentHeader modifiedDocument = (DocumentHeader)getObject();
//					if (TKServiceLocator.getUserPreferenceService().getUserPreference(modifiedDocument.getUniversityId(), DAILY_REMINDER_CODE) != null) {
//						return; //if daily_reminder preference exists, the email notification SHOULD NOT be sent
//					}
//					
//					Calendar cal = new GregorianCalendar();
//					TimelessDate yesterday = new TimelessDate(cal.getTime());
//					yesterday = yesterday.addDays(-1);
//					TimedDate timedDate = new TimedDate(yesterday.toString(),"12","0","AM");
//			     	Timestamp startDate  = timedDate.getTimestamp();
//			     	timedDate = new TimedDate(yesterday.toString(),"11","59","PM");
//			     	Timestamp endDate  = timedDate.getTimestamp();  
//		        
//			     	List timeBlocksModified = TKServiceLocator.getTimesheetService().getTimeBlockHistoryModifiedRecords(
//		    			 modifiedDocument.getPayEndDate(), startDate, endDate, modifiedDocument.getDocumentId());
//		        
//			     	String currentDocumentId = new String();
//			     	String messageBegin = MAIL_MESSAGE_LINE1 + dateFormat.format(modifiedDocument.getPayEndDate()) + MAIL_MESSAGE_LINE2;
//		        	String messageEnd = " on " + dateFormat.format(startDate.getDate())+".";
//		        	String timeBlockModifiers = new String();
//		        	Collection emailList = new ArrayList();
//		        	Collection ccList = new ArrayList();
//		        
//
//		        	   for (Iterator iter = timeBlocksModified.iterator(); iter.hasNext();) {
//				           DailyNotification timeBlockModified = (DailyNotification)iter.next();
//		                   if (!Utility.hasValue(currentDocumentId)){ 
//		 		        	   currentDocumentId = timeBlockModified.getDocumentId();
//		 		        	   emailList.add(TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getUniversityId()).getEmailAddress());
//		 		        	   ccList.add((TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getEmailAddress()));
//		 		        	   timeBlockModifiers = TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getName();
//		 		           } else {
//		 		        		   timeBlockModifiers += ", " + TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getName();
//		 		        		   ccList.add((TKServiceLocator.getDirectoryService().getUserByEmployeeId(timeBlockModified.getModifiedByUniversityId()).getEmailAddress()));
//		 		           }
//				       }//endFor
//		        	   if(!StringUtils.isBlank(timeBlockModifiers)){
//		        		   String message = messageBegin + timeBlockModifiers + messageEnd + MAIL_MESSAGE_FOOTER;
//		        		   
//		        		   if (Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
//		        			   getEmailService().send(emailList, MAIL_FROM_ADDRESS, MAIL_SUBJECT, message, ccList, null);
//		        		   } else {
//		        			   getEmailService().sendMail(Constants.MAIL_REPORT_TO, MAIL_FROM_ADDRESS, MAIL_SUBJECT, "Message intended for: " + emailList.toString() + ", " + ccList.toString() + " \n\n" + message);
//		        		   }
//		        	   }
//		        	   else{
//		        		   LOG.info("No timeblocks were modified for "+modifiedDocument.getDocumentId());
//		        	   }
//					
//				}
//
//	    public EmailService getEmailService(){
//	    	return TKServiceLocator.getEmailService();
//	    }
//
//
//		@Override
//		protected String getDataId() {
//			return ((DocumentHeader)getObject()).getDocumentId();
//		}
//	}
//
//	@Override
//	protected BatchRunnable getBatchRunnable() {
//		return new RemindUserDailyBatchRunnable();
//	}
//
//	@Override
//	protected String getType() {
//		return TkConstants.BatchJobTypes.Document;
//	}
//


}