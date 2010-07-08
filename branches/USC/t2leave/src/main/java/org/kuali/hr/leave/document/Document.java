package org.kuali.hr.leave.document;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class Document extends PersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private String doumentStatus;
    private String documentClass;
    private String employeeId;
    private Date month;

    @Override
    protected LinkedHashMap toStringMapper() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getDocumentNumber() {
	return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
	this.documentNumber = documentNumber;
    }

    public String getDoumentStatus() {
	return doumentStatus;
    }

    public void setDoumentStatus(String doumentStatus) {
	this.doumentStatus = doumentStatus;
    }

    public String getDocumentClass() {
	return documentClass;
    }

    public void setDocumentClass(String documentClass) {
	this.documentClass = documentClass;
    }

    public String getEmployeeId() {
	return employeeId;
    }

    public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
    }

    public Date getMonth() {
	return month;
    }

    public void setMonth(Date month) {
	this.month = month;
    }

}
