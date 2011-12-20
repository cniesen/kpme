package org.kuali.hr.lm.leavedonation.dao;


import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.lm.leavedonation.LeaveDonation;
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;

public class LeaveDonationDaoSpringOjbImpl extends PersistenceBrokerDaoSupport implements LeaveDonationDao {

	private static final Logger LOG = Logger.getLogger(LeaveDonationDaoSpringOjbImpl.class);

	@Override
	public LeaveDonation getLeaveDonation(Long lmLeaveDonationId) {
		Criteria crit = new Criteria();
		crit.addEqualTo("lmLeaveDonationId", lmLeaveDonationId);
		Query query = QueryFactory.newQuery(LeaveDonation.class, crit);
		return (LeaveDonation) this.getPersistenceBrokerTemplate().getObjectByQuery(query);
	}
	
}
