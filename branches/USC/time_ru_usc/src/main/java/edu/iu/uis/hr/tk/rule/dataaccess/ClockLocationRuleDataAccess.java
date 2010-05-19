package edu.iu.uis.hr.tk.rule.dataaccess;

import java.math.BigDecimal;
import java.util.Date;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRule;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRuleSearchCriteria;

public interface ClockLocationRuleDataAccess extends DataAccess {
    public TypedPersistentMaintainedEntityList lookupMaintainableClockLocationRules(ClockLocationRuleSearchCriteria clockLocationRuleSearchCriteria);

    public ClockLocationRule getClockLocationRule(String universityId, BigDecimal employeeRecord, String department, BigDecimal workArea, Date effectiveDate, BigDecimal effectiveSequence, boolean actiive);
}