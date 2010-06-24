package edu.iu.uis.hr.tk.batch.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.TKBatchJobPopulator;
import edu.iu.uis.hr.tk.batch.TKBatchRunnable;
import edu.iu.uis.hr.tk.batch.job.runnables.RemindUserWeeklyBatchTKRunnable;
import edu.iu.uis.hr.tk.entity.UserPreference;
import edu.iu.uis.hr.tk.util.TkConstants;

public class RemindUserWeeklyBatchJob extends TKBatchJobPopulator {
	private static final long serialVersionUID = 8083129809808985596L;
	private static final Logger LOG = Logger.getLogger(RemindUserWeeklyBatchJob.class);
	

	public static final String WEEKLY_REMINDER_CODE = "WR";
//	private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
//	private static final String MAIL_SUBJECT = "TIME: Enter/Verify Timesheet Hours";
//	private static final String MAIL_MESSAGE_LINE1 = "\n \n Your Timesheet for the pay period ending ";
//	private static final String MAIL_MESSAGE_LINE2 = " will be finalized for pay by your manager on ";
//	private static final String MAIL_MESSAGE_LINE3 = "You will need to review your timesheet and let your manager know if you see any discrepancies before ";
//	private static final String MAIL_MESSAGE_FOOTER = "\n\n This message is being sent due to a preference you have set in the TIME portal.  You may adjust your settings in the TIME:User Preferences channel in OneStart. ";
	
	@Override
	public String getName() {
		return "RemindUserWeeklyBatchJob";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TKBatchRunnable> getTKBatchRunnables() {
		LOG.debug("Executing edu.iu.uis.hr.tk.batch.job.RemindUserWeeklyBatchJob");
		List<UserPreference> user = TKServiceLocator.getUserPreferenceService().getUsersByPreference(WEEKLY_REMINDER_CODE);
		sendKickOffEmail(user.size());
		
		List<TKBatchRunnable> tkbatchRunnables = new ArrayList<TKBatchRunnable>();
		for (UserPreference userPreference : user) {
			tkbatchRunnables.add(new RemindUserWeeklyBatchTKRunnable(userPreference));
		}
		
		return tkbatchRunnables;
	}
	@Override
	public String getType() {
		return TkConstants.BatchJobTypes.UserPref;
	}

	
	
	
	
//	public RemindUserWeeklyBatchJob() {
//		setName("RemindUserWeeklyBatchJob");
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	protected BatchJobDescription setupJobs() {
//		LOG.debug("Executing edu.iu.uis.hr.tk.batch.job.RemindUserWeeklyBatchJob");
//		List user = TKServiceLocator.getUserPreferenceService().getUsersByPreference(WEEKLY_REMINDER_CODE);
//		sendKickOffEmail(user.size());
//		return new BatchJobDescription(getName(), new java.sql.Timestamp(System.currentTimeMillis()), null, user);
//	}
//
//	public static class RemindUserWeeklyBatchRunnable extends BatchRunnable {
//		private static final long serialVersionUID = 2703568000458614205L;
//
//		private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM dd, yyyy");
//		
//		@Override
//		protected void doWork() {
//
//			UserPreference userPreference = (UserPreference) getObject();
//
//			if (userPreference == null || !StringUtils.equals(userPreference.getPreferenceCode(), WEEKLY_REMINDER_CODE)) {
//				return;
//			}
//
//			Calendar payEndDateLatitude = new GregorianCalendar();
//			payEndDateLatitude.setTime(TKServiceLocator.getPayCalendarService().getPayCalendar(new Date()).getPayEndDate());
//			payEndDateLatitude.add(Calendar.DAY_OF_YEAR, 2);
//
//			String MESSAGE = MAIL_MESSAGE_LINE1 + dateFormat.format(TKServiceLocator.getPayCalendarService().getPayCalendar(new Date()).getPayEndDate()) + MAIL_MESSAGE_LINE2
//					+ dateFormat.format(payEndDateLatitude.getTime()) + ". \n\n" + MAIL_MESSAGE_LINE3 + dateFormat.format(payEndDateLatitude.getTime()) + ". \n" + MAIL_MESSAGE_FOOTER + "\n\n";
//			String networkId = TKServiceLocator.getDirectoryService().getNetworkIdByEmployeeId(userPreference.getUniversityId());
//			if (TKServiceLocator.getWebUserService().isReviewer(networkId) || TKServiceLocator.getWebUserService().isViewOnly(networkId)
//					|| TKServiceLocator.getAssignmentService().hasAssignment(userPreference.getUniversityId())) {
//				String userEmail = TKServiceLocator.getDirectoryService().getUserByEmployeeId(userPreference.getUniversityId()).getEmailAddress();
//				LOG.info("Sending  reminder to: " + userEmail + ":  " + MESSAGE + "\n \n");
//
//				if (Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
//					getEmailService().sendMail(userEmail, MAIL_FROM_ADDRESS, MAIL_SUBJECT, MESSAGE);
//				} else {
//					getEmailService().sendMail(Constants.MAIL_REPORT_TO, MAIL_FROM_ADDRESS, MAIL_SUBJECT, "Message intended for: " + userEmail + MESSAGE);
//				}
//			}
//		}
//		
//
//	    public EmailService getEmailService(){
//	    	return TKServiceLocator.getEmailService();
//	    }
//
//		@Override
//		protected String getDataId() {
//			return ((UserPreference) getObject()).getUniversityId();
//		}
//	}
//
//	@Override
//	protected BatchRunnable getBatchRunnable() {
//		return new RemindUserWeeklyBatchRunnable();
//	}
//
//	@Override
//	protected String getType() {
//		return TkConstants.BatchJobTypes.UserPref;
//	}

}