package edu.iu.uis.hr.tk.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.CheckboxList;
import edu.iu.uis.hr.SubmitEvent;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.directory.entity.EmailRecipient;
import edu.iu.uis.hr.directory.entity.User;
import edu.iu.uis.hr.entity.AbstractNonDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TransientNonDatabaseEntity;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.tk.entity.logic.SupervisorMustHaveOneSelectedLogic;
import edu.iu.uis.hr.tk.entity.logic.SupervisorsDisplayOnlyFieldsLogic;

public class SupervisorNotification extends AbstractNonDatabaseEntity implements TransientNonDatabaseEntity {
    private static final String SUBJECT = "subject";
    private static final String MESSAGE = "message";
    private static final String FROM = "from";
    private static final String ASSIGNMENT = "assignment";
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        PROPERTY_DESCRIPTORS.put(User.NETWORK_ID_FIELD, new NonDatabaseStringPropertyDescriptor(User.NETWORK_ID_FIELD, true, true, 10));
        NonDatabasePropertyDescriptor workAreaPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.WORK_AREA, WorkArea.class);
        workAreaPropertyDescriptor.setDisplayOnly(true);
        PROPERTY_DESCRIPTORS.put(FieldNames.WORK_AREA, workAreaPropertyDescriptor);
        PROPERTY_DESCRIPTORS.put(SUBJECT, new NonDatabaseStringPropertyDescriptor(SUBJECT, false, true, 200));
        PROPERTY_DESCRIPTORS.put(FROM, new NonDatabaseStringPropertyDescriptor(FROM, true, true, 80));
        PROPERTY_DESCRIPTORS.put(MESSAGE, new NonDatabaseStringPropertyDescriptor(MESSAGE, false, true, 1000));
        PROPERTY_DESCRIPTORS.put(ASSIGNMENT, new NonDatabaseStringPropertyDescriptor(ASSIGNMENT, true, true, 1000));
    }

    private String networkId;
    private BigDecimal workArea;
    private String subject;
    private String message;
    private String from;
    private String assignment;
    private CheckboxList supervisors;

    public SupervisorNotification() {
        super();
        setSupervisors(new CheckboxList(EmailRecipient.class));
    }

    public Class getDisplayOnlyFieldsLogic() {
        return SupervisorsDisplayOnlyFieldsLogic.class;
    }

    protected List getOperationalLogics(SubmitEvent submitEvent) {
        List operationalLogics = edu.iu.uis.hr.Utility.getDefaultListValue();
        operationalLogics.add(SupervisorMustHaveOneSelectedLogic.class);
        return operationalLogics;
    }

    public Class getModeAuthorization() {
        return SupervisorNotificationModeAuthorization.class;
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject != null) {
            this.subject = subject;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String text) {
        if (text != null) {
            this.message = text;
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String employeeEmailAddress) {
        if (employeeEmailAddress != null) {
            this.from = employeeEmailAddress;
        }
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        if (networkId != null) {
            this.networkId = networkId;
        }
    }

    public CheckboxList getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(CheckboxList supervisors) {
        if (supervisors != null) {
            this.supervisors = supervisors;
        }
    }

    public void setSupervisor(int index, EmailRecipient supervisor) {
        getSupervisors().set(index, supervisor);
    }

    public EmailRecipient getSupervisor(int index) {
        return (EmailRecipient) getSupervisors().get(index);
    }

    public BigDecimal getWorkArea() {
        return workArea;
    }

    public void setWorkArea(BigDecimal workArea) {
        if (workArea != null) {
            this.workArea = workArea;
        }
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        if (assignment != null) {
            this.assignment = assignment;
        }
    }

}
