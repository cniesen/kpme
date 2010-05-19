package edu.iu.uis.hr.tk.rule.dataaccess;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.GracePeriodRule;

public class GracePeriodRuleDataAccessOjb extends AbstractDataAccessOjb implements GracePeriodRuleDataAccess {
    private static final Logger LOG = Logger.getLogger(GracePeriodRuleDataAccessOjb.class);

    @CacheResult
    public GracePeriodRule getGracePeriodRule(Date transactionRecordEffectiveDate) {
        GracePeriodRule gracePeriodRule = (GracePeriodRule)getCurrentActiveRecord(new GracePeriodRule(transactionRecordEffectiveDate));
        return gracePeriodRule;
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableGracePeriodRules(boolean includeHistory) {
        Criteria lookupCriteria = null;
        if (includeHistory) {
            lookupCriteria = new Criteria();
        } else {
            lookupCriteria = getCurrentAndFutureRecordsCriteria(GracePeriodRule.class, new Date());
        }
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(GracePeriodRule.class, lookupCriteria));
    }

	public void addGracePeriodRuleManually(GracePeriodRule gracePeriodRule) {
		store(gracePeriodRule);
	}
}