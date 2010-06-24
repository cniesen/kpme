package edu.iu.uis.hr.tk.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.client.SimpleSubmittableForm;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.directory.service.RolesService;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.entity.SupervisorNotification;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;

public class TimeApproverNotificationForm extends AbstractStrutsActionForm implements SimpleSubmittableForm {

    private SupervisorNotification supervisorNotification;
    private static final String SUPERVISOR_NOTIFICATION = "TIME Approver Notification";

    public TimeApproverNotificationForm() {
        super();
        setSupervisorNotification(new SupervisorNotification());
    }

    public List getAccessAuthorizations() {
        List accessAuthorizations = new ArrayList();
        accessAuthorizations.add(EmployeeAccessAuthorization.class);
        return accessAuthorizations;
    }

    public Class getModeAuthorization() {
        return SupervisorNotificationFormModeAuthorization.class;
    }

    public void initialize() {
        getSupervisorNotification().setSupervisors(((RolesService)getService(RolesService.class)).getSupervisorsAsEmailRecipients((BigDecimal)((AssignmentService)getService(AssignmentService.class)).getAssignmentKeyMapFromCommaDelimitedList(supervisorNotification.getAssignment()).get(FieldNames.WORK_AREA)));
        getSupervisorNotification().setFrom(((WebUserService)getService(WebUserService.class)).getUser(getSupervisorNotification().getNetworkId()).getEmailAddress());

    }

    public void submit() {
        ((EmailService)getService(EmailService.class)).sendMail(supervisorNotification.getSupervisors(), supervisorNotification.getFrom(), supervisorNotification.getSubject(), supervisorNotification.getMessage(), SUPERVISOR_NOTIFICATION);
    }

    public final String getSubmitUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append("?method=submit").toString());
    }

    public void prepare() {
        getLabels().put("supervisors", "TIME Approvers");  
    }

    public SupervisorNotification getSupervisorNotification() {
        return supervisorNotification;
    }

    public void setSupervisorNotification(SupervisorNotification supervisorNotification) {
        if (supervisorNotification != null) {
            this.supervisorNotification = supervisorNotification;
        }
    }
}
