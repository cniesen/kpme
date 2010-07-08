package org.kuali.hr.leave.emploverriderow;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class EmplOverrideRow extends PersistableBusinessObjectBase implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private BigDecimal maxCarryover;
    private BigDecimal maxTaken;
    private boolean flexScheduleInd;
    private String documentId;
    private boolean fmlaOverrideInd;
    private boolean injOverrideInd;
    private boolean milOverrideInd;
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

    public BigDecimal getMaxCarryover() {
        return maxCarryover;
    }

    public void setMaxCarryover(BigDecimal maxCarryover) {
        this.maxCarryover = maxCarryover;
    }

    public BigDecimal getMaxTaken() {
        return maxTaken;
    }

    public void setMaxTaken(BigDecimal maxTaken) {
        this.maxTaken = maxTaken;
    }

    public boolean isFlexScheduleInd() {
        return flexScheduleInd;
    }

    public void setFlexScheduleInd(boolean flexScheduleInd) {
        this.flexScheduleInd = flexScheduleInd;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public boolean isFmlaOverrideInd() {
        return fmlaOverrideInd;
    }

    public void setFmlaOverrideInd(boolean fmlaOverrideInd) {
        this.fmlaOverrideInd = fmlaOverrideInd;
    }

    public boolean isInjOverrideInd() {
        return injOverrideInd;
    }

    public void setInjOverrideInd(boolean injOverrideInd) {
        this.injOverrideInd = injOverrideInd;
    }

    public boolean isMilOverrideInd() {
        return milOverrideInd;
    }

    public void setMilOverrideInd(boolean milOverrideInd) {
        this.milOverrideInd = milOverrideInd;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
