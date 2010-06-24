package edu.iu.uis.hr.tk.rule.dataaccess;

import java.math.BigDecimal;
import java.util.Date;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRule;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRuleSearchCriteria;

public interface DailyOvertimeRuleDataAccess extends DataAccess {
    public TypedPersistentMaintainedEntityList lookupMaintainableDailyOvertimeRules(DailyOvertimeRuleSearchCriteria dailyOvertimeRuleSearchCriteria);
    
    public DailyOvertimeRule getDailyOvertimeRule(String department, BigDecimal workArea, BigDecimal task, Date effectiveDate);
}