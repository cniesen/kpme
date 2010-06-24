package edu.iu.uis.hr.tk.timesheet.service;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.ApplicationException;

public class ActiveAssignmentNotFoundException extends ApplicationException {

    public ActiveAssignmentNotFoundException(String message, Logger logger) {
        super(message, logger);
    }

}
