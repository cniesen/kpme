package edu.iu.uis.hr.tk.rule.dataaccess;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.SystemLunchRule;

public class SystemLunchRuleDataAccessOjb extends AbstractDataAccessOjb implements SystemLunchRuleDataAccess {

    private static final Logger LOG = Logger.getLogger(SystemLunchRuleDataAccessOjb.class);
    private static final String ACTIVE_STATUS = "A";

    public TypedPersistentMaintainedEntityList lookupMaintainableSystemLunchRules(boolean includeHistory) {
        Criteria lookupCriteria = null;
        if (includeHistory) {
            lookupCriteria = new Criteria();
        } else {
            lookupCriteria = getCurrentAndFutureRecordsCriteria(SystemLunchRule.class, new Date());
        }
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(SystemLunchRule.class, lookupCriteria));
    }


    public SystemLunchRule getEffectiveSystemLunchRule(Date transactionRecordEffectiveDate) {
        SystemLunchRule systemLunchRule = (SystemLunchRule)getCurrentActiveRecord(new SystemLunchRule(transactionRecordEffectiveDate));
        return systemLunchRule;
    }

	public void addSystemLunchRuleManually(SystemLunchRule systemLunchRule) {
		store(systemLunchRule);
	}



}