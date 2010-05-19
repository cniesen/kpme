package edu.iu.uis.hr.tk.rule.dataaccess;

import java.util.Date;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.GracePeriodRule;

public interface GracePeriodRuleDataAccess extends DataAccess {
    public TypedPersistentMaintainedEntityList lookupMaintainableGracePeriodRules(boolean includeHistory);

    public GracePeriodRule getGracePeriodRule(Date transactionRecordEffectiveDate);
    public void addGracePeriodRuleManually(GracePeriodRule gracePeriodRule);
}