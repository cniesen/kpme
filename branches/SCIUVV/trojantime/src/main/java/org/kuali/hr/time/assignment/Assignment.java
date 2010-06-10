package org.kuali.hr.time.assignment;

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
    private Long workArea;
    private Integer taskId;
    private boolean active;
    
    public Assignment() {
	
    }
    
    public Assignment(String principalId, Integer jobNumber, Date effectiveDate, String earnCode, Long workArea, Integer taskId) {
	this.principalId = principalId;
	this.jobNumber = jobNumber;
	this.effectiveDate = effectiveDate;
	this.earnCode = earnCode;
	this.workArea = workArea;
	this.taskId = taskId;
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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getWorkArea() {
        return workArea;
    }

    public void setWorkArea(Long workArea) {
        this.workArea = workArea;
    }
}
