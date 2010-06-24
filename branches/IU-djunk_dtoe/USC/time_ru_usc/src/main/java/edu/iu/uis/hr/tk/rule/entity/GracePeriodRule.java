package edu.iu.uis.hr.tk.rule.entity;

import java.util.Date;

import org.apache.log4j.Logger;

public class GracePeriodRule extends AbstractGracePeriodRule implements SystemRule {
    private static final Logger LOG = Logger.getLogger(GracePeriodRule.class);

    public GracePeriodRule() {
        super();
    }
    
    public GracePeriodRule(Date transactionRecordEffectiveDate) {
        this();
        setEffectiveDate(transactionRecordEffectiveDate);
    }

    public Class getModeAuthorization() {
        return SystemRuleModeAuthorization.class;
    }

}