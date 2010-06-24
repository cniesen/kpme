package edu.iu.uis.hr.tk.timesheet.service;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.ApplicationException;

public class DocumentHeaderNotFoundException extends ApplicationException {

    public DocumentHeaderNotFoundException(String message, Logger logger) {
        super(message, logger);
    }

}
