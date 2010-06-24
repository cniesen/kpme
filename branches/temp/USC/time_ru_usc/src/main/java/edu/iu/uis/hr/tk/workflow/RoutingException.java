package edu.iu.uis.hr.tk.workflow;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.ApplicationException;

public class RoutingException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7188605989887739433L;

	public RoutingException(String message, Logger logger) {
		super(message, logger);
	}
	
}
