package org.kuali.hr.time.util.exceptions;

import org.apache.log4j.Logger;

import javax.xml.ws.WebFault;

@WebFault(name="ServiceException")
public class ServiceException extends Exception {

	private static Logger log = Logger.getLogger(ServiceException.class);

	private String message;

	/**
	 * 
	 */
	private static final long serialVersionUID = -801051020052173192L;

	public ServiceException() {
		log.error("An unknown Service error has occured");
	}

	public ServiceException(String message) {
		this.setMessage(message);
		log.error(message);
	}

	public ServiceException(Exception e) {
		super(e.getMessage(), e);
		this.setMessage(e.getMessage());
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		if (message == null || message.length() == 0)
			return super.getMessage();
		return message;
	}

}
