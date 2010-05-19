package edu.iu.uis.hr.tk.entity.report;

import edu.iu.uis.hr.Utility;

public class ExtractDocument implements Comparable {

	private String documentId;
	private String emplid;
	private String employeeRecord;
	private String name;
	private String department;
	private String status;
	private String hasHours;

	public ExtractDocument() {
		setDocumentId(Utility.getDefaultStringValue());
		setEmplid(Utility.getDefaultStringValue());
		setEmployeeRecord(Utility.getDefaultStringValue());
		setName(Utility.getDefaultStringValue());
		setDepartment(Utility.getDefaultStringValue());
		setStatus(Utility.getDefaultStringValue());
		setHasHours(Utility.getDefaultStringValue());
	}
	
	public ExtractDocument(String documentId, String emplid, String employeeRecord, String name, String department, String status, String hasHours) {
		setDocumentId(documentId);
		setEmplid(emplid);
		setEmployeeRecord(employeeRecord);
		setName(name);
		setDepartment(department);
		setStatus(status);
		setHasHours(hasHours);
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getEmplid() {
		return emplid;
	}
	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}
	public String getEmployeeRecord() {
		return employeeRecord;
	}
	public void setEmployeeRecord(String employeeRcd) {
		this.employeeRecord = employeeRcd;
	}
	public String getHasHours() {
		return hasHours;
	}
	public void setHasHours(String hasHours) {
		this.hasHours = hasHours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int compareTo(Object o) {
		ExtractDocument obj = (ExtractDocument)o;
		if (this.getDocumentId().compareTo(obj.getDocumentId())!=0) {
			return this.getDocumentId().compareTo(obj.getDocumentId());
		} else {
			if (this.getEmplid().compareTo(obj.getEmplid())!=0) {
				return this.getEmplid().compareTo(obj.getEmplid());
			} else {
				if (this.getEmployeeRecord().compareTo(obj.getEmployeeRecord())!=0) {
					return this.getEmployeeRecord().compareTo(obj.getEmployeeRecord());
				} else {
					return 0;
				}
			}
		}
	}
	
	public boolean equals(Object o) {
		ExtractDocument obj = (ExtractDocument)o;
		return (this.getDocumentId().equals(obj.getDocumentId()) &&
		        this.getEmplid().equals(obj.getEmplid()) &&
		        this.getEmployeeRecord().equals(obj.getEmployeeRecord()));
		       
	}

	public int hashCode() {
		return (this.getDocumentId() + this.getEmplid() + this.getEmployeeRecord()).hashCode();
	}

	public String toString(){
		return "DocumentID:" + this.getDocumentId() + "\n" + 
		       "UniversityID: " + this.getEmplid() + "\n" +
		       "EmployeeRecord: " + this.getEmployeeRecord() + "\n" +
		       "Name: " + this.getName() + "\n" +
		       "Department: " + this.getDepartment() + "\n" +
		       "EmployeeStatus: " + this.getStatus() + "\n" +
		       "HasHours" + this.hasHours;
	}
	
}
