package edu.iu.uis.hr.tk.client;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Constants;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.client.BatchProgram;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.entity.UserPreference;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.service.UserPreferenceService;

public class RemindUserWeeklyBatchProgram implements BatchProgram {

    private static final Logger LOG = Logger.getLogger(RemindUserWeeklyBatchProgram.class);
    private DirectoryService directoryService;
    private AssignmentService assignmentService;
    private PayCalendarService payCalendarService;
    private EmailService emailService;
    private UserPreferenceService userPreferenceService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM dd, yyyy");

    private static final String WEEKLY_REMINDER_CODE = "WR";
    private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
    private static final String MAIL_SUBJECT = "TIME: Enter/Verify Timesheet Hours";
    private static final String MAIL_MESSAGE_LINE1 = "\n \n Your Timesheet for the pay period ending ";
    private static final String MAIL_MESSAGE_LINE2 = " will be finalized for pay by your manager on ";
    private static final String MAIL_MESSAGE_LINE3 = "You will need to review your timesheet and let your manager know if you see any discrepancies before ";
    private static final String MAIL_MESSAGE_FOOTER = "\n\n This message is being sent due to a preference you have set in the TIME portal.  You may adjust your settings in the TIME:User Preferences channel in OneStart. ";
    
    public void execute() {
        LOG.debug("Executing edu.iu.uis.hr.tk.client.RemindUserWeeklyBatchProgram");
        Calendar payEndDateLatitude = new GregorianCalendar();
        payEndDateLatitude.setTime(getPayCalendarService().getPayCalendar(new Date()).getPayEndDate());
        payEndDateLatitude.add(Calendar.DAY_OF_YEAR, 2);

        String MESSAGE = MAIL_MESSAGE_LINE1 + dateFormat.format(getPayCalendarService().getPayCalendar(new Date()).getPayEndDate()) + MAIL_MESSAGE_LINE2 + dateFormat.format(payEndDateLatitude.getTime()) + ". \n\n" + MAIL_MESSAGE_LINE3 + dateFormat.format(payEndDateLatitude.getTime()) + ". \n" + MAIL_MESSAGE_FOOTER + "\n\n";

        for (Iterator iter = getUserPreferenceService().getUsersByPreference(WEEKLY_REMINDER_CODE).iterator(); iter.hasNext();) {
            UserPreference userPreference = (UserPreference)iter.next();

            try {
            	String userEmail = getDirectoryService().getUserByEmployeeId(userPreference.getUniversityId()).getEmailAddress();
                if (getAssignmentService().hasAssignment(userPreference.getUniversityId())) {
                    LOG.info("Sending  reminder to: " + userEmail + ":  " + MESSAGE + "\n \n");
                    if (Context.PRODUCTION_ENVIRONMENT.equals(Context.getEnvironment())) {
                    	getEmailService().sendMail(userEmail, MAIL_FROM_ADDRESS , MAIL_SUBJECT, MESSAGE);
                    } else {
                    	getEmailService().sendMail(Constants.MAIL_REPORT_TO, MAIL_FROM_ADDRESS, MAIL_SUBJECT, "Message intended for: " + userEmail +  MESSAGE);
                    }
                }
            } catch (Exception e) {
                LOG.info("Error using getEdsDataAccess() for user: " + userPreference.getUniversityId());
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
