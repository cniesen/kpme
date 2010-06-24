package edu.iu.uis.hr.tk.rule.dataaccess;

import java.util.Collections;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.WeeklyOvertimeRule;

public class WeeklyOvertimeRuleDataAccessOjb extends AbstractDataAccessOjb implements WeeklyOvertimeRuleDataAccess {


    private static final String ACTIVE_STATUS = "A";
    private static final Logger LOG = Logger.getLogger(WeeklyOvertimeRuleDataAccessOjb.class);


    public TypedPersistentMaintainedEntityList lookupMaintainableWeeklyOvertimeRules(boolean includeHistory) {
        Criteria lookupCriteria = null;
        if (includeHistory) {
            lookupCriteria = new Criteria();
        } else {
            lookupCriteria = getCurrentAndFutureRecordsCriteria(WeeklyOvertimeRule.class, new Date());
        }
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(WeeklyOvertimeRule.class, lookupCriteria));
    }

    // Returns the active overtime rules ordered by the Steps in which they will be executed to calculate weekly overtime
    public TypedPersistentMaintainedEntityList getActiveSortedWeeklyOvertimeRules() {
        Criteria criteria = new Criteria();
        criteria = getCurrentAndFutureRecordsCriteria(WeeklyOvertimeRule.class, new Date());
        criteria.addEqualTo(FieldNames.ACTIVE, ACTIVE_STATUS);
        TypedPersistentMaintainedEntityList weeklyOvertimeRuleList = getMaintainableValuesListByQuery(QueryFactory.newQuery(WeeklyOvertimeRule.class, criteria));
        Collections.sort(weeklyOvertimeRuleList);
        return weeklyOvertimeRuleList;
    }

	public void addWeeklyOvertimeRuleManually(WeeklyOvertimeRule weeklyOvertimeRule) {
		store(weeklyOvertimeRule);
	}

}
