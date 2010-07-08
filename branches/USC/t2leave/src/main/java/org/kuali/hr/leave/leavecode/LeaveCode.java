package org.kuali.hr.leave.leavecode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class LeaveCode extends PersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;
 
    private Long id;
    private Long accrualCategoryPseudoKey;
    private String leaveCode;
    private String displayName;
    private boolean allowScheduledDaysInd;
    private boolean accrualEarnedInd;
    private BigDecimal defaultHours;
    private boolean reportInHoursInd;
    private boolean reportInFullHoursInd;
    private boolean reportInHalfDaysInd;
    private boolean reportInFullDaysInd;
    private boolean applyToYearlyMaxInd;
    private boolean affectsHolidayAccrualInd;
    private String documentId;
    private String previousDocumentId;
    private boolean basicInd;
    private boolean advanceInd;
    private String validationClass;
    private boolean activeInd;
    private Date effectiveDate;
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

    public Long getAccrualCategoryPseudoKey() {
        return accrualCategoryPseudoKey;
    }

    public void setAccrualCategoryPseudoKey(Long accrualCategoryPseudoKey) {
        this.accrualCategoryPseudoKey = accrualCategoryPseudoKey;
    }

    public String getLeaveCode() {
        return leaveCode;
    }

    public void setLeaveCode(String leaveCode) {
        this.leaveCode = leaveCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isAllowScheduledDaysInd() {
        return allowScheduledDaysInd;
    }

    public void setAllowScheduledDaysInd(boolean allowScheduledDaysInd) {
        this.allowScheduledDaysInd = allowScheduledDaysInd;
    }

    public boolean isAccrualEarnedInd() {
        return accrualEarnedInd;
    }

    public void setAccrualEarnedInd(boolean accrualEarnedInd) {
        this.accrualEarnedInd = accrualEarnedInd;
    }

    public BigDecimal getDefaultHours() {
        return defaultHours;
    }

    public void setDefaultHours(BigDecimal defaultHours) {
        this.defaultHours = defaultHours;
    }

    public boolean isReportInHoursInd() {
        return reportInHoursInd;
    }

    public void setReportInHoursInd(boolean reportInHoursInd) {
        this.reportInHoursInd = reportInHoursInd;
    }

    public boolean isReportInFullHoursInd() {
        return reportInFullHoursInd;
    }

    public void setReportInFullHoursInd(boolean reportInFullHoursInd) {
        this.reportInFullHoursInd = reportInFullHoursInd;
    }

    public boolean isReportInHalfDaysInd() {
        return reportInHalfDaysInd;
    }

    public void setReportInHalfDaysInd(boolean reportInHalfDaysInd) {
        this.reportInHalfDaysInd = reportInHalfDaysInd;
    }

    public boolean isReportInFullDaysInd() {
        return reportInFullDaysInd;
    }

    public void setReportInFullDaysInd(boolean reportInFullDaysInd) {
        this.reportInFullDaysInd = reportInFullDaysInd;
    }

    public boolean isApplyToYearlyMaxInd() {
        return applyToYearlyMaxInd;
    }

    public void setApplyToYearlyMaxInd(boolean applyToYearlyMaxInd) {
        this.applyToYearlyMaxInd = applyToYearlyMaxInd;
    }

    public boolean isAffectsHolidayAccrualInd() {
        return affectsHolidayAccrualInd;
    }

    public void setAffectsHolidayAccrualInd(boolean affectsHolidayAccrualInd) {
        this.affectsHolidayAccrualInd = affectsHolidayAccrualInd;
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

    public boolean isBasicInd() {
        return basicInd;
    }

    public void setBasicInd(boolean basicInd) {
        this.basicInd = basicInd;
    }

    public boolean isAdvanceInd() {
        return advanceInd;
    }

    public void setAdvanceInd(boolean advanceInd) {
        this.advanceInd = advanceInd;
    }

    public String getValidationClass() {
        return validationClass;
    }

    public void setValidationClass(String validationClass) {
        this.validationClass = validationClass;
    }

    public boolean isActiveInd() {
        return activeInd;
    }

    public void setActiveInd(boolean activeInd) {
        this.activeInd = activeInd;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
