package org.kuali.hr.time.clock.location;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class DeptLunchRule extends PersistableBusinessObjectBase {
 
    private static final long serialVersionUID = 1L;
    
    private Long deptLunchRuleId;
    private String department;
    private BigDecimal workArea;
    private String emplId;         // like principal id
    private BigDecimal emplRcd;    // like job number
    private Date effectiveDate;
    private BigDecimal effectiveSeq;
    private boolean active;          // eff_status
    private String requiredClockFl;
    private BigDecimal maxMins;
    private String userEmpl;
    
    private Timestamp timestamp;
    

    @Override
    protected LinkedHashMap toStringMapper() {
	// TODO Auto-generated method stub	
	LinkedHashMap<String, Object> toStringMap = new LinkedHashMap<String,Object>();
		toStringMap.put("emplId", emplId);
		return toStringMap;

    }


    public Long getDeptLunchRuleId() {
        return deptLunchRuleId;
    }


    public void setDeptLunchRuleId(Long deptLunchRuleId) {
        this.deptLunchRuleId = deptLunchRuleId;
    }


    public String getDepartment() {
        return department;
    }


    public void setDepartment(String department) {
        this.department = department;
    }


    public BigDecimal getWorkArea() {
        return workArea;
    }


    public void setWorkArea(BigDecimal workArea) {
        this.workArea = workArea;
    }


    public String getEmplId() {
        return emplId;
    }


    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }


    public BigDecimal getEmplRcd() {
        return emplRcd;
    }


    public void setEmplRcd(BigDecimal emplRcd) {
        this.emplRcd = emplRcd;
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


    public String getRequiredClockFl() {
        return requiredClockFl;
    }


    public void setRequiredClockFl(String requiredClockFl) {
        this.requiredClockFl = requiredClockFl;
    }


 


    public BigDecimal getMaxMins() {
        return maxMins;
    }


    public void setMaxMins(BigDecimal maxMins) {
        this.maxMins = maxMins;
    }


    public String getUserEmpl() {
        return userEmpl;
    }


    public void setUserEmpl(String userEmpl) {
        this.userEmpl = userEmpl;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


 
    
    
    

}
