package edu.iu.uis.hr.tk.rule.entity;

import java.util.Date;

import org.apache.log4j.Logger;

public class SystemLunchRule extends AbstractSystemLunchRule implements SystemRule {
    
    private static final Logger LOG = Logger.getLogger(SystemLunchRule.class);
    public static final String SYSTEM_LUNCH_RULE_NOT_FOUND = "No System Lunch Rule was found for current pay period.  Please report this problem to the system administrator.";
    
    public SystemLunchRule() {
        super();
    }
    
    public SystemLunchRule(Date transactionRecordEffectiveDate) {
        this();
        setEffectiveDate(transactionRecordEffectiveDate);
    }
    
    public Class getModeAuthorization() {
        return SystemRuleModeAuthorization.class;
    }
}