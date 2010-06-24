package edu.iu.uis.hr.tk.batch.job.runnables;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import edu.iu.uis.hr.Constants;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.entity.UserPreference;

public class RemindUserWeeklyBatchTKRunnable extends AbstractTKBatchRunnable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2511363346588943834L;
	private static final Logger LOG = Logger.getLogger(RemindUserWeeklyBatchTKRunnable.class);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM dd, yyyy");
	
	public static final String WEEKLY_REMINDER_CODE = "WR";
	private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
	private static final String MAIL_SUBJECT = "TIME: Enter/Verify Timesheet Hours";
	private static final String MAIL_MESSAGE_LINE1 = "\n \n Your Timesheet for the pay period ending ";
	private static final String MAIL_MESSAGE_LINE2 = " will be finalized for pay by your manager on ";
	private static final String MAIL_MESSAGE_LINE3 = "You will need to review your timesheet and let your manager know if you see any discrepancies before ";
	private static final String MAIL_MESSAGE_FOOTER = "\n\n This message is being sent due to a preference you have set in the TIME portal.  You may adjust your settings in the TIME:User Preferences channel in OneStart. ";

	private UserPreference userPreference;
	
	public RemindUserWeeklyBatchTKRunnable(UserPreference userPreference) {
		this.userPreference = userPreference;
	}

	public String getDataId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void run() {

		if (userPreference == null || !StringUtils.equals(userPreference.getPreferenceCode(), WEEKLY_REMINDER_CODE)) {
			return;
		}

		Calendar payEndDateLatitude = new GregorianCalendar();
		payEndDateLatitude.setTime(TKServiceLocator.getPayCalendarService().getPayCalendar(new Date()).getPayEndDate());
		payEndDateLatitude.add(Calendar.DAY_OF_YEAR, 2);

		String MESSAGE = MAIL_MESSAGE_LINE1 + dateFormat.format(TKServiceLocator.getPayCalendarService().getPayCalendar(new Date()).getPayEndDate()) + MAIL_MESSAGE_LINE2
				+ dateFormat.format(payEndDateLatitude.getTime()) + ". \n\n" + MAIL_MESSAGE_LINE3 + dateFormat.format(payEndDateLatitude.getTime()) + ". \n" + MAIL_MESSAGE_FOOTER + "\n\n";
		String networkId = TKServiceLocator.getDirectoryService().getNetworkIdByEmployeeId(userPreference.getUniversityId());
		if (TKServiceLocator.getWebUserService().isReviewer(networkId) || TKServiceLocator.getWebUserService().isViewOnly(networkId)
				|| TKServiceLocator.getAssignmentService().hasAssignment(userPreference.getUniversityId())) {
			String userEmail = TKServiceLocator.getDirectoryService().getUserByEmployeeId(userPreference.getUniversityId()).getEmailAddress();
			LOG.info("Sending  reminder to: " + userEmail + ":  " + MESSAGE + "\n \n");

			if (Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
				getEmailService().sendMail(userEmail, MAIL_FROM_ADDRESS, MAIL_SUBJECT, MESSAGE);
			} else {
				getEmailService().sendMail(Constants.MAIL_REPORT_TO, MAIL_FROM_ADDRESS, MAIL_SUBJECT, "Message intended for: " + userEmail + MESSAGE);
			}
		}
	}

    public EmailService getEmailService(){
    	return TKServiceLocator.getEmailService();
    }
}
