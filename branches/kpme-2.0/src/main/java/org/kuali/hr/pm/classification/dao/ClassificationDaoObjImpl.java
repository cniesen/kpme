package org.kuali.hr.pm.classification.dao;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.pm.classification.Classification;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

public class ClassificationDaoObjImpl extends PlatformAwareDaoBaseOjb implements ClassificationDao {

	@Override
	public Classification getClassificationById(String pmClassificationId) {
		Criteria crit = new Criteria();
        crit.addEqualTo("pmPositionClassId", pmClassificationId);

        Query query = QueryFactory.newQuery(Classification.class, crit);
        return (Classification) this.getPersistenceBrokerTemplate().getObjectByQuery(query);
	}

	
}
