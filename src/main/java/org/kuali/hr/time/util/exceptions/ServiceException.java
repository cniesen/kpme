package org.kuali.hr.time.util.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebFault;

import org.apache.log4j.Logger;

/**
 * 
 * @author crivera
 * 
 */
@WebFault(name="ServiceException")
@XmlAccessorType( XmlAccessType.FIELD )
public class ServiceException extends Exception {

	private static Logger log = Logger.getLogger(ServiceException.class);

	private List<TkFaultInfo> errors = new ArrayList<TkFaultInfo>(); 
	
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		if (message == null || message.length() == 0)
			return super.getMessage();
		return message;
	}

	/**
	 * adds an error object so we know which objects failed and why
	 * @param error
	 * @param e
	 */
	public void add(Object error, Exception e) {
		getErrors().add(new TkFaultInfo(error, e));
	}

	/**
	 * checks if there have been any error objects added
	 * @return
	 */
	public boolean hasErrors() {
		return getErrors().size() > 0;
	}

	public List<TkFaultInfo> getErrors() {
		return errors;
	}

}

