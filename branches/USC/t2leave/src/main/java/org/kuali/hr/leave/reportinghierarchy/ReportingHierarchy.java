package org.kuali.hr.leave.reportinghierarchy;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class ReportingHierarchy extends PersistableBusinessObjectBase implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String employeeId;
    private String employeeRcd;
    private String employeeStatus;
    private String campus;
    private String departmentId;
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

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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
