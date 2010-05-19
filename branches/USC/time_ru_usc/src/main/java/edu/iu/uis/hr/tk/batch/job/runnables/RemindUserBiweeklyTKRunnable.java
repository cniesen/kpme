package edu.iu.uis.hr.tk.batch.job.runnables;

import java.util.Date;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Constants;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.entity.UserPreference;

public class RemindUserBiweeklyTKRunnable extends AbstractTKBatchRunnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -757847337326676501L;

	private static final Logger LOG = Logger.getLogger(RemindUserBiweeklyTKRunnable.class);

    public static final String BIWEEKLY_REMINDER_CODE = "BR";
    private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
    private static final String MAIL_SUBJECT = "TIME: Pay Period End Reminder - Timesheets Routed for Approval";
    private static String MAIL_MESSAGE_START = "\n\n Timesheets have been routed for approval for the pay period ending on ";
    private static String MAIL_MESSAGE_FOOTER = "\n\n This message is being sent due to a preference you have set in the TIME portal.  You may adjust your settings in the TIME:User Preferences channel in OneStart. ";
    
    private UserPreference userPreference;

	public RemindUserBiweeklyTKRunnable(UserPreference userPreference) {
		this.userPreference = userPreference;
	}
	
	public void run() {
		PayCalendar payCalendar = TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new TimelessDate(new Date()).getDate());
		 String mailMessage = MAIL_MESSAGE_START + payCalendar.getValue() + MAIL_MESSAGE_FOOTER;  //getValue grabs the pay period end date
		 String networkId = TKServiceLocator.getDirectoryService().getNetworkIdByEmployeeId(userPreference.getUniversityId());
			if (TKServiceLocator.getWebUserService().isReviewer(networkId) ||
           		TKServiceLocator.getWebUserService().isViewOnly(networkId) ||
           		TKServiceLocator.getAssignmentService().hasAssignment(userPreference.getUniversityId())) {
				String userEmail = TKServiceLocator.getDirectoryService()
						.getUserByEmployeeId(
								userPreference.getUniversityId())
						.getEmailAddress();
				if (Context.PRODUCTION_ENVIRONMENT.equals(Context
						.getEnvironment())) {
					LOG.info("Sending reminder to: " + userEmail + ": "
							+ mailMessage + "\n\n");
					getSendEmailService().sendMail(userEmail,
							MAIL_FROM_ADDRESS, MAIL_SUBJECT, mailMessage);
				} else {
					getSendEmailService().sendMail(
							Constants.MAIL_REPORT_TO,
							MAIL_FROM_ADDRESS,
							MAIL_SUBJECT,
							"Message intended for " + userEmail + ":  "
									+ mailMessage);
				}
		
	}
	}


	public String getDataId() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public EmailService getSendEmailService(){
    	return TKServiceLocator.getEmailService();
    }
}
