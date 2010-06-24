package edu.iu.uis.hr.tk.rule.dataaccess;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRule;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRuleSearchCriteria;

public class TimeCollectionRuleDataAccessOjb extends AbstractDataAccessOjb implements TimeCollectionRuleDataAccess {
    private static final Logger LOG = Logger.getLogger(TimeCollectionRuleDataAccessOjb.class);

    public TimeCollectionRule getTimeCollectionRule(String department, BigDecimal workArea, Date transactionRecordEffectiveDate) {
        TimeCollectionRule timeCollectionRule = (TimeCollectionRule)getCurrentActiveRecord(new TimeCollectionRule(department, workArea, transactionRecordEffectiveDate));
        //LOG.debug(timeCollectionRule);
        return timeCollectionRule;
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableTimeCollectionRules(TimeCollectionRuleSearchCriteria timeCollectionRuleSearchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.DEPARTMENT, timeCollectionRuleSearchCriteria.getDepartment());
        getStandardLookupLogic(lookupCriteria, FieldNames.WORK_AREA, timeCollectionRuleSearchCriteria.getWorkArea());

        getLookupEffectiveLogic(lookupCriteria, TimeCollectionRule.class, timeCollectionRuleSearchCriteria);
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(TimeCollectionRule.class, lookupCriteria));
    }

	public void addTimeCollectionRuleManually(TimeCollectionRule timeCollectionRule) {
		store(timeCollectionRule);
	}
}