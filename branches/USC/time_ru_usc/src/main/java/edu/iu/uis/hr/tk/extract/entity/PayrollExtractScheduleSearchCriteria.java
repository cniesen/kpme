package edu.iu.uis.hr.tk.extract.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;

public class PayrollExtractScheduleSearchCriteria extends AbstractEffectiveDatedSearchCriteria {
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(PayrollExtractSchedule.class);
    }

    private Date payEndDate;
    private String status;
    private String statuses;

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setPayEndDate(null);
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        if (payEndDate != null) {
            this.payEndDate = payEndDate;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null) {
            this.status = status;
        }
    }

    public String getStatuses() {
        return statuses;
    }

    public void setStatuses(String statuses) {
        if (statuses != null) {
            this.statuses = statuses;
        }
    }


}
