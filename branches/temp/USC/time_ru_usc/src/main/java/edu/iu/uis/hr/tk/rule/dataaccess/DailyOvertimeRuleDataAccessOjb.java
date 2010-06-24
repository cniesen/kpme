package edu.iu.uis.hr.tk.rule.dataaccess;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRule;
import edu.iu.uis.hr.tk.rule.entity.DailyOvertimeRuleSearchCriteria;

public class DailyOvertimeRuleDataAccessOjb extends AbstractDataAccessOjb implements DailyOvertimeRuleDataAccess {
    private static final Logger LOG = Logger.getLogger(DailyOvertimeRuleDataAccessOjb.class);

    public TypedPersistentMaintainedEntityList lookupMaintainableDailyOvertimeRules(DailyOvertimeRuleSearchCriteria dailyOvertimeRuleSearchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.DEPARTMENT, dailyOvertimeRuleSearchCriteria.getDepartment());
        getStandardLookupLogic(lookupCriteria, FieldNames.WORK_AREA, dailyOvertimeRuleSearchCriteria.getWorkArea());
        getStandardLookupLogic(lookupCriteria, FieldNames.TASK, dailyOvertimeRuleSearchCriteria.getTask());
        getLookupEffectiveLogic(lookupCriteria, DailyOvertimeRule.class, dailyOvertimeRuleSearchCriteria);
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(DailyOvertimeRule.class, lookupCriteria));
    }
    
    public DailyOvertimeRule getDailyOvertimeRule(String department, BigDecimal workArea, BigDecimal task, Date effectiveDate){
    	Criteria criteria = new Criteria();
    	criteria.addEqualTo(FieldNames.DEPARTMENT, department);
    	criteria.addEqualTo(FieldNames.WORK_AREA, workArea);
    	QueryByCriteria query = QueryFactory.newQuery(DailyOvertimeRule.class, criteria);
    	return (DailyOvertimeRule) new TypedPersistentMaintainedEntityList(query.getSearchClass(), getCollectionByQuery(query)).get(0);
    }
}