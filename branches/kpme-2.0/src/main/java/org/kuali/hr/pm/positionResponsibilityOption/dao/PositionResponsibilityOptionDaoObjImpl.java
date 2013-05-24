package org.kuali.hr.pm.positionResponsibilityOption.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.pm.positionResponsibilityOption.PositionResponsibilityOption;
import org.kuali.hr.pm.pstnqlfrtype.PstnQlfrType;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;


public class PositionResponsibilityOptionDaoObjImpl extends PlatformAwareDaoBaseOjb implements PositionResponsibilityOptionDao {

	@Override
	public PositionResponsibilityOption getPositionResponsibilityOptionById(
			String prOptionId) {
		Criteria crit = new Criteria();
        crit.addEqualTo("prOptionId", prOptionId);

        Query query = QueryFactory.newQuery(PositionResponsibilityOption.class, crit);
        return (PositionResponsibilityOption) this.getPersistenceBrokerTemplate().getObjectByQuery(query);
	}
	
	@Override
	public List<PositionResponsibilityOption> getAllActivePstnRspOptions() {
		List<PositionResponsibilityOption> aList = new ArrayList<PositionResponsibilityOption>();
		Criteria root = new Criteria();
		root.addEqualTo("active", true);
		Query query = QueryFactory.newQuery(PositionResponsibilityOption.class, root);
		
		Collection c = this.getPersistenceBrokerTemplate().getCollectionByQuery(query);
		if(!c.isEmpty())
			aList.addAll(c);
		
		return aList;
	}
	
	
}
