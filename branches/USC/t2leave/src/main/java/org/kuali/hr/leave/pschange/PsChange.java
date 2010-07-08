package org.kuali.hr.leave.pschange;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class PsChange extends PersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String employeeId;
    private String employeeRcd;
    private String employeeStatus;
    private BigDecimal stdHrs;   
    private Date serviceDate;
    private BigDecimal leavePercent;
    private BigDecimal regPercent;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeRcd() {
        return employeeRcd;
    }

    public void setEmployeeRcd(String employeeRcd) {
        this.employeeRcd = employeeRcd;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public BigDecimal getStdHrs() {
        return stdHrs;
    }

    public void setStdHrs(BigDecimal stdHrs) {
        this.stdHrs = stdHrs;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public BigDecimal getLeavePercent() {
        return leavePercent;
    }

    public void setLeavePercent(BigDecimal leavePercent) {
        this.leavePercent = leavePercent;
    }

    public BigDecimal getRegPercent() {
        return regPercent;
    }

    public void setRegPercent(BigDecimal regPercent) {
        this.regPercent = regPercent;
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
