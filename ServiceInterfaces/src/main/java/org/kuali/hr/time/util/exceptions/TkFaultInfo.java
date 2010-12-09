package org.kuali.hr.time.util.exceptions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Helper class for webservices
 * @author crivera
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LmFaultInfo")
public class TkFaultInfo {

	private Object obj;

	private String exceptionMessage;

	public TkFaultInfo() {

	}

	public TkFaultInfo(Object obj, Exception e) {
		this.obj = obj;
		this.exceptionMessage = e.getMessage();
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Object getObj() {
		return obj;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}
}
