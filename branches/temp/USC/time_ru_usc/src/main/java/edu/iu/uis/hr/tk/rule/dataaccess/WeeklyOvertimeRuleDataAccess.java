package edu.iu.uis.hr.tk.rule.dataaccess;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.WeeklyOvertimeRule;

public interface WeeklyOvertimeRuleDataAccess extends DataAccess {
    public TypedPersistentMaintainedEntityList lookupMaintainableWeeklyOvertimeRules(boolean includeHistory);
    public TypedPersistentMaintainedEntityList getActiveSortedWeeklyOvertimeRules();
    public void addWeeklyOvertimeRuleManually(WeeklyOvertimeRule weeklyOvertimeRule);
}
