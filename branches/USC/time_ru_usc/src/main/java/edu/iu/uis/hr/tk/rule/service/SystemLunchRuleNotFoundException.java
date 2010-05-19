package edu.iu.uis.hr.tk.rule.service;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.ApplicationException;

public class SystemLunchRuleNotFoundException extends ApplicationException {

    public SystemLunchRuleNotFoundException(String message, Logger logger) {
        super(message, logger);
    }

}
