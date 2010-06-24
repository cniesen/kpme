package edu.iu.uis.hr.tk.timesheet.service;

import org.apache.log4j.Logger;
import org.kuali.rice.ksb.messaging.PersistedMessage;
import org.kuali.rice.ksb.messaging.exceptionhandling.DefaultMessageExceptionHandler;

public class AsynTimesheetMessageExceptionHandler
		extends
			DefaultMessageExceptionHandler {

	
	private static final Logger LOG = Logger.getLogger(AsynTimesheetMessageExceptionHandler.class);

	@Override
	public void handleException(Throwable throwable, PersistedMessage message, Object service) throws Exception {
		LOG.error("Exception thrown running asynchronous clock action", throwable);
		placeInException(throwable, message);
	}
}