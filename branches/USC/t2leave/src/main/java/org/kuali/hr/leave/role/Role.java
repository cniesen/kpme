package org.kuali.hr.leave.role;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class Role extends PersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String supervisorPos;
    private String roleTargetPos;
    private String role;
    private String targetEmplId;
    private Date expirationDate;
    private String documentNumber;
    private String previousDocumentId;
    private Date effectiveDate;
    private boolean activeInd;

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

    public String getSupervisorPos() {
	return supervisorPos;
    }

    public void setSupervisorPos(String supervisorPos) {
	this.supervisorPos = supervisorPos;
    }

    public String getRoleTargetPos() {
	return roleTargetPos;
    }

    public void setRoleTargetPos(String roleTargetPos) {
	this.roleTargetPos = roleTargetPos;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getTargetEmplId() {
	return targetEmplId;
    }

    public void setTargetEmplId(String targetEmplId) {
	this.targetEmplId = targetEmplId;
    }

    public Date getExpirationDate() {
	return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
	this.expirationDate = expirationDate;
    }

    public String getDocumentNumber() {
	return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
	this.documentNumber = documentNumber;
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

}
