package edu.iu.uis.hr.tk.rule.dataaccess;

import java.util.Date;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.SystemLunchRule;

public interface SystemLunchRuleDataAccess extends DataAccess {
    public TypedPersistentMaintainedEntityList lookupMaintainableSystemLunchRules(boolean includeHistory);
    public SystemLunchRule getEffectiveSystemLunchRule(Date date);
    public void addSystemLunchRuleManually(SystemLunchRule systemLunchRule);
}