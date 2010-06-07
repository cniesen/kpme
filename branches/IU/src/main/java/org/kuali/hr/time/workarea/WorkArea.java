package org.kuali.hr.time.workarea;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class WorkArea extends PersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 1L;
    
    private Long workAreaId;
    private Date effectiveDate;
    private BigDecimal effectiveSeq;
    private boolean active;          // eff_status
    private String descr;
    private String department;
    private String emplType;
    private String overtimePreference;
    private String adminDescr;
    private String userEmplId;
    
    private Timestamp timestamp;
    

    @Override
    protected LinkedHashMap toStringMapper() {
	LinkedHashMap<String, Object> toStringMap = new LinkedHashMap<String,Object>();
	toStringMap.put("workAreaId", workAreaId);
	return toStringMap;
    }


    public Long getWorkAreaId() {
        return workAreaId;
    }


    public void setWorkAreaId(Long workAreaId) {
        this.workAreaId = workAreaId;
    }


    public Date getEffectiveDate() {
        return effectiveDate;
    }


    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    public BigDecimal getEffectiveSeq() {
        return effectiveSeq;
    }


    public void setEffectiveSeq(BigDecimal effectiveSeq) {
        this.effectiveSeq = effectiveSeq;
    }


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }



    public String getDescr() {
        return descr;
    }


    public void setDescr(String descr) {
        this.descr = descr;
    }


    public String getDepartment() {
        return department;
    }


    public void setDepartment(String department) {
        this.department = department;
    }


    public String getEmplType() {
        return emplType;
    }


    public void setEmplType(String emplType) {
        this.emplType = emplType;
    }


    public String getOvertimePreference() {
        return overtimePreference;
    }


    public void setOvertimePreference(String overtimePreference) {
        this.overtimePreference = overtimePreference;
    }


    public String getAdminDescr() {
        return adminDescr;
    }


    public void setAdminDescr(String adminDescr) {
        this.adminDescr = adminDescr;
    }


    public String getUserEmplId() {
        return userEmplId;
    }


    public void setUserEmplId(String userEmplId) {
        this.userEmplId = userEmplId;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    
    

}
