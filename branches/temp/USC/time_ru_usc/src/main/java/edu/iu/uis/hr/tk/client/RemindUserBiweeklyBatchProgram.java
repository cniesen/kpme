package edu.iu.uis.hr.tk.client;

import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Constants;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.client.BatchProgram;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.directory.service.RolesService;
import edu.iu.uis.hr.tk.entity.UserPreference;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.service.UserPreferenceService;

public class RemindUserBiweeklyBatchProgram implements BatchProgram {

    private static final Logger LOG = Logger.getLogger(RemindUserBiweeklyBatchProgram.class);
    private DirectoryService directoryService;
    private AssignmentService assignmentService;
    private PayCalendarService payCalendarService;
    private EmailService emailService;
    private RolesService rolesService;
    private UserPreferenceService userPreferenceService;
    private static final String BIWEEKLY_REMINDER_CODE = "BR";
    private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
    private static final String MAIL_SUBJECT = "TIME: Pay Period End Reminder - Timesheets Routed for Approval";
    private static String MAIL_MESSAGE_START = "\n\n Timesheets have been routed for approval for the pay period ending on ";
    private static String MAIL_MESSAGE_FOOTER = "\n\n This message is being sent due to a preference you have set in the TIME portal.  You may adjust your settings in the TIME:User Preferences channel in OneStart. ";

    public void execute() {
        LOG.debug("Executing edu.iu.uis.hr.tk.client.RemindUserBatchProgram");
        //this runs AFTER the pay period end so we get previous
        PayCalendar payCalendar = getPayCalendarService().getPreviousPayCalendar(new TimelessDate(new Date()).getDate());
        sendReminder(payCalendar);
    }

    private void sendReminder(PayCalendar payCalendar) {
    	
        String mailMessage = MAIL_MESSAGE_START + payCalendar.getValue() + MAIL_MESSAGE_FOOTER;  //getValue grabs the pay period end date

        //gets a list of all users with biweekly reminder code
        for (Iterator iter = getUserPreferenceService().getUsersByPreference(BIWEEKLY_REMINDER_CODE).iterator(); iter.hasNext();) {
            UserPreference userPreference = (UserPreference)iter.next();
            try {
            	String userEmail = getDirectoryService().getUserByEmployeeId(userPreference.getUniversityId()).getEmailAddress();
                if (Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
                	LOG.info("Sending reminder to: " + userEmail + ": " + mailMessage + "\n\n");
                	getEmailService().sendMail(userEmail, MAIL_FROM_ADDRESS, MAIL_SUBJECT, mailMessage);
                } else  {
                	getEmailService().sendMail(Constants.MAIL_REPORT_TO, MAIL_FROM_ADDRESS, MAIL_SUBJECT, "Message intended for " + userEmail + ":  " + mailMessage);
                }
                
            } catch (Exception e) {
                LOG.info("Error using getDirectoryService() for user: " + userPreference.getUniversityId());
            }
        }
    }

    private RolesService getRolesService() {
        return rolesService;
    }

    public void setRolesService(RolesService rolesService) {
        if (rolesService != null) {
            this.rolesService = rolesService;
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

    private AssignmentService getAssignmentService() {
        return assignmentService;
    }

    public void setAssignmentService(AssignmentService assignmentService) {
        if (assignmentService != null) {
            this.assignmentService = assignmentService;
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
