package edu.iu.uis.hr.tk.rule.dataaccess;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.HourlyHoursRuleSearchCriteria;

public interface HourlyHoursRuleDataAccess extends DataAccess {
    public TypedPersistentMaintainedEntityList lookupMaintainableHourlyHoursRules(HourlyHoursRuleSearchCriteria hourlyHoursRuleRuleSearchCriteria);
}