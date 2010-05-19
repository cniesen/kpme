package edu.iu.uis.hr.tk.timesheet.service;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.ApplicationException;

public class ConflictingLockException extends ApplicationException {

    public ConflictingLockException(String message, Logger logger) {
        super(message, logger);
    }

}
