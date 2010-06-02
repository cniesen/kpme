package org.kuali.hr.time.domain.base;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class Assignment extends PersistableBusinessObjectBase {

    /**
     * 
     */
    private static final long serialVersionUID = -3408305833805778653L;
    
    private Long assignmentId;
    private String principalId;
    private Integer jobNumber;
    private Date effectiveDate;
    private String earnCode;
    private Integer effectiveSequence;
    private Integer workAreaId;
    private Integer taskId;
    private BigDecimal percent;
    
    public Assignment() {
	
    }
    
    public Assignment(String principalId, Integer jobNumber, Date effectiveDate, String earnCode, Integer effectiveSequence, Integer workAreaId, Integer taskId, BigDecimal percent) {
	this.principalId = principalId;
	this.jobNumber = jobNumber;
	this.effectiveDate = effectiveDate;
	this.earnCode = earnCode;
	this.effectiveSequence = effectiveSequence;
	this.workAreaId = workAreaId;
	this.taskId = taskId;
	this.percent = percent;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
	// TODO Auto-generated method stub
	return null;
    }



    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public Integer getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getEarnCode() {
        return earnCode;
    }

    public void setEarnCode(String earnCode) {
        this.earnCode = earnCode;
    }

    public Integer getEffectiveSequence() {
        return effectiveSequence;
    }

    public void setEffectiveSequence(Integer effectiveSequence) {
        this.effectiveSequence = effectiveSequence;
    }

    public Integer getWorkAreaId() {
        return workAreaId;
    }

    public void setWorkAreaId(Integer workAreaId) {
        this.workAreaId = workAreaId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }
}
