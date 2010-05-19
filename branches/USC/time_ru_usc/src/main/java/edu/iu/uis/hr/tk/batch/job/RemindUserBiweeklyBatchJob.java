package edu.iu.uis.hr.tk.batch.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.TKBatchJobPopulator;
import edu.iu.uis.hr.tk.batch.TKBatchRunnable;
import edu.iu.uis.hr.tk.batch.job.runnables.RemindUserBiweeklyTKRunnable;
import edu.iu.uis.hr.tk.entity.UserPreference;
import edu.iu.uis.hr.tk.util.TkConstants;

public class RemindUserBiweeklyBatchJob extends TKBatchJobPopulator {

	private static final long serialVersionUID = 7880658620886806256L;

	private static final Logger LOG = Logger.getLogger(RemindUserBiweeklyBatchJob.class);

    public static final String BIWEEKLY_REMINDER_CODE = "BR";
//    private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
//    private static final String MAIL_SUBJECT = "TIME: Pay Period End Reminder - Timesheets Routed for Approval";
//    private static String MAIL_MESSAGE_START = "\n\n Timesheets have been routed for approval for the pay period ending on ";
//    private static String MAIL_MESSAGE_FOOTER = "\n\n This message is being sent due to a preference you have set in the TIME portal.  You may adjust your settings in the TIME:User Preferences channel in OneStart. ";
    
	@Override
	public String getName() {
		return "RemindUserBiweeklyBatchJob";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TKBatchRunnable> getTKBatchRunnables() {
		LOG.debug("Executing RemindUserBiweeklyBatchJob");
		List<UserPreference> users = TKServiceLocator.getUserPreferenceService().getUsersByPreference(BIWEEKLY_REMINDER_CODE);
		try {
			sendKickOffEmail(users.size());
		}catch (Exception e){
			LOG.debug("Error sending email from " + getName() + ": " + e);
		}
		
		List<TKBatchRunnable> tkbatchRunnables = new ArrayList<TKBatchRunnable>();
		for (UserPreference userPreference : users) {
			tkbatchRunnables.add(new RemindUserBiweeklyTKRunnable(userPreference));
		}
		
		return tkbatchRunnables;
	}
	
	
	@Override
	public String getType() {
		return TkConstants.BatchJobTypes.UserPref;
	}
    
    
    
    
//    public RemindUserBiweeklyBatchJob(){
//    	setName("RemindUserBiweeklyBatchJob");
//    }
//    
//	@SuppressWarnings("unchecked")
//	@Override
//	protected BatchJobDescription setupJobs() {
//		LOG.debug("Executing RemindUserBiweeklyBatchJob");
//		List users = TKServiceLocator.getUserPreferenceService().getUsersByPreference(BIWEEKLY_REMINDER_CODE);
//		sendKickOffEmail(users.size());
//		return new BatchJobDescription(getName(), new Timestamp(System.currentTimeMillis()), null, users);
//	}
//
//   public static class RemindUserBiweeklyBatchRunnable extends BatchRunnable {
//		private static final long serialVersionUID = 4404513720688467651L;
//		
//		@Override
//		protected void doWork() {
//					
//					PayCalendar payCalendar = TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new TimelessDate(new Date()).getDate());
//					 String mailMessage = MAIL_MESSAGE_START + payCalendar.getValue() + MAIL_MESSAGE_FOOTER;  //getValue grabs the pay period end date
//					 UserPreference userPreference = (UserPreference)getObject();
//					 String networkId = TKServiceLocator.getDirectoryService().getNetworkIdByEmployeeId(userPreference.getUniversityId());
//						if (TKServiceLocator.getWebUserService().isReviewer(networkId) ||
//			            		TKServiceLocator.getWebUserService().isViewOnly(networkId) ||
//			            		TKServiceLocator.getAssignmentService().hasAssignment(userPreference.getUniversityId())) {
//							String userEmail = TKServiceLocator.getDirectoryService()
//									.getUserByEmployeeId(
//											userPreference.getUniversityId())
//									.getEmailAddress();
//							if (Context.PRODUCTION_ENVIRONMENT.equals(Context
//									.getEnvironment())) {
//								LOG.info("Sending reminder to: " + userEmail + ": "
//										+ mailMessage + "\n\n");
//								getSendEmailService().sendMail(userEmail,
//										MAIL_FROM_ADDRESS, MAIL_SUBJECT, mailMessage);
//							} else {
//								getSendEmailService().sendMail(
//										Constants.MAIL_REPORT_TO,
//										MAIL_FROM_ADDRESS,
//										MAIL_SUBJECT,
//										"Message intended for " + userEmail + ":  "
//												+ mailMessage);
//							}
//					
//				}
//		}
//
//		@Override
//		protected String getDataId() {
//			return ((UserPreference)getObject()).getUniversityId();
//		}
//		
//	    public EmailService getSendEmailService(){
//	    	return TKServiceLocator.getEmailService();
//	    }
//    }
//   
//
//    
////	protected BatchRunnerService getBatchRunnerService(UserPreference userPreference) {
////		return (BatchRunnerService) KSBServiceLocator
////		.getMessageHelper().getServiceAsynchronously(Context.EXPORTED_BATCH_RUNNER_SERVICE_QNAME, null,
////				getName(), userPreference.getUniversityId(),1);		
////	}
//
//	@Override
//	protected BatchRunnable getBatchRunnable() {
//		return new RemindUserBiweeklyBatchRunnable();
//	}
//
//	@Override
//	protected String getType() {
//		return TkConstants.BatchJobTypes.UserPref;
//	}





}