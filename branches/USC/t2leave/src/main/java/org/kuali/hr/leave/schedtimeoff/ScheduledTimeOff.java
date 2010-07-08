package org.kuali.hr.leave.schedtimeoff;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class ScheduledTimeOff extends PersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long leaveCodePseudoKey;
    private Date scheduledDate;
    private Date accrualDate;
    private Date expirationDate;
    private String description;
    private String documentId;
    private String previousDocumentId;
    private Date effectiveDate;
    private boolean activeInd;
    private Date timestamp; 
    
    @Override
    protected LinkedHashMap toStringMapper() {
	// TODO Auto-generated method stub
	return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeaveCodePseudoKey() {
        return leaveCodePseudoKey;
    }

    public void setLeaveCodePseudoKey(Long leaveCodePseudoKey) {
        this.leaveCodePseudoKey = leaveCodePseudoKey;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Date getAccrualDate() {
        return accrualDate;
    }

    public void setAccrualDate(Date accrualDate) {
        this.accrualDate = accrualDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getPreviousDocumentId() {
        return previousDocumentId;
    }

    public void setPreviousDocumentId(String previousDocumentId) {
        this.previousDocumentId = previousDocumentId;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public boolean isActiveInd() {
        return activeInd;
    }

    public void setActiveInd(boolean activeInd) {
        this.activeInd = activeInd;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
