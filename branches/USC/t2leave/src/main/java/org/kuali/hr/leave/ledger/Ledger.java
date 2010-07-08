package org.kuali.hr.leave.ledger;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class Ledger extends PersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long ledgerId;
    private Date ptoDate;
    private String employeeId;
    private String leaveCodePseudoKey;
    private String leaveCode;
    private Long shedTimeOffId;
    private Long accrualCategoryId;
    private boolean statusInd;
    private BigDecimal hours;
    private boolean applyToYtdUsedInd;
    private String documentNumber;
    private String employeeIdActivated;
    private String employeeIdInactivated;
    private Date timestampActivated;
    private Date timestampinActivated;
    private boolean accrualGeneratedInd;
    private Long lockingNumber;
    private String description;
    private Long blockId;
    private String altSchedTimeOff;
    private boolean doNotAllowRemovalInd;
    private boolean messageInd;
    private Date timestamp;
   
    @Override
    protected LinkedHashMap toStringMapper() {
	// TODO Auto-generated method stub
	return null;
    }

    public Long getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Long ledgerId) {
        this.ledgerId = ledgerId;
    }

    public Date getPtoDate() {
        return ptoDate;
    }

    public void setPtoDate(Date ptoDate) {
        this.ptoDate = ptoDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLeaveCodePseudoKey() {
        return leaveCodePseudoKey;
    }

    public void setLeaveCodePseudoKey(String leaveCodePseudoKey) {
        this.leaveCodePseudoKey = leaveCodePseudoKey;
    }

    public String getLeaveCode() {
        return leaveCode;
    }

    public void setLeaveCode(String leaveCode) {
        this.leaveCode = leaveCode;
    }

    public Long getShedTimeOffId() {
        return shedTimeOffId;
    }

    public void setShedTimeOffId(Long shedTimeOffId) {
        this.shedTimeOffId = shedTimeOffId;
    }

    public Long getAccrualCategoryId() {
        return accrualCategoryId;
    }

    public void setAccrualCategoryId(Long accrualCategoryId) {
        this.accrualCategoryId = accrualCategoryId;
    }

    public boolean isStatusInd() {
        return statusInd;
    }

    public void setStatusInd(boolean statusInd) {
        this.statusInd = statusInd;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public boolean isApplyToYtdUsedInd() {
        return applyToYtdUsedInd;
    }

    public void setApplyToYtdUsedInd(boolean applyToYtdUsedInd) {
        this.applyToYtdUsedInd = applyToYtdUsedInd;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmployeeIdActivated() {
        return employeeIdActivated;
    }

    public void setEmployeeIdActivated(String employeeIdActivated) {
        this.employeeIdActivated = employeeIdActivated;
    }

    public String getEmployeeIdInactivated() {
        return employeeIdInactivated;
    }

    public void setEmployeeIdInactivated(String employeeIdInactivated) {
        this.employeeIdInactivated = employeeIdInactivated;
    }

    public Date getTimestampActivated() {
        return timestampActivated;
    }

    public void setTimestampActivated(Date timestampActivated) {
        this.timestampActivated = timestampActivated;
    }

    public Date getTimestampinActivated() {
        return timestampinActivated;
    }

    public void setTimestampinActivated(Date timestampinActivated) {
        this.timestampinActivated = timestampinActivated;
    }

    public boolean isAccrualGeneratedInd() {
        return accrualGeneratedInd;
    }

    public void setAccrualGeneratedInd(boolean accrualGeneratedInd) {
        this.accrualGeneratedInd = accrualGeneratedInd;
    }

    public Long getLockingNumber() {
        return lockingNumber;
    }

    public void setLockingNumber(Long lockingNumber) {
        this.lockingNumber = lockingNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public String getAltSchedTimeOff() {
        return altSchedTimeOff;
    }

    public void setAltSchedTimeOff(String altSchedTimeOff) {
        this.altSchedTimeOff = altSchedTimeOff;
    }

    public boolean isDoNotAllowRemovalInd() {
        return doNotAllowRemovalInd;
    }

    public void setDoNotAllowRemovalInd(boolean doNotAllowRemovalInd) {
        this.doNotAllowRemovalInd = doNotAllowRemovalInd;
    }

    public boolean isMessageInd() {
        return messageInd;
    }

    public void setMessageInd(boolean messageInd) {
        this.messageInd = messageInd;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
