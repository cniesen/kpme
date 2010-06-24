package edu.iu.uis.hr.tk.rule.dataaccess;

import java.math.BigDecimal;
import java.util.Date;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRule;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRuleSearchCriteria;

public interface TimeCollectionRuleDataAccess extends DataAccess {
    public TypedPersistentMaintainedEntityList lookupMaintainableTimeCollectionRules(TimeCollectionRuleSearchCriteria timeCollectionRuleSearchCriteria);

    public TimeCollectionRule getTimeCollectionRule(String department, BigDecimal workArea, Date transactionRecordEffectiveDate);

    public void addTimeCollectionRuleManually(TimeCollectionRule timeCollectionRule);
}