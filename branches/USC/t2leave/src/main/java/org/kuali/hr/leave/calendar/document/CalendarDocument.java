package org.kuali.hr.leave.calendar.document;

import java.io.Serializable;

import org.kuali.hr.leave.calendar.Month;
import org.kuali.rice.kns.bo.DocumentHeader;

public class CalendarDocument implements Serializable {
    
	private static final long serialVersionUID = -1L;

	private DocumentHeader documentHeader;
	private Month month;
	private String emplid;

	public DocumentHeader getDocumentHeader() {
		return documentHeader;
	}

	public void setDocumentHeader(DocumentHeader documentHeader) {
		this.documentHeader = documentHeader;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public String getEmplid() {
		return emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

}
