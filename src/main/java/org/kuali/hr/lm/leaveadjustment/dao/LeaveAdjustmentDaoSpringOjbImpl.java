/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.hr.lm.leaveadjustment.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Collections;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.hr.core.util.OjbSubQueryUtil;
import org.kuali.hr.lm.leaveadjustment.LeaveAdjustment;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

public class LeaveAdjustmentDaoSpringOjbImpl extends PlatformAwareDaoBaseOjb implements LeaveAdjustmentDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveAdjustment> getLeaveAdjustments(String principalId, Date asOfDate) {
        List<LeaveAdjustment> leaveAdjustments = new ArrayList<LeaveAdjustment>();
        Criteria root = new Criteria();

        java.sql.Date effDate = asOfDate == null ? null : new java.sql.Date(asOfDate.getTime());
        root.addEqualTo("principalId", principalId);
        root.addEqualTo("effectiveDate", OjbSubQueryUtil.getEffectiveDateSubQuery(LeaveAdjustment.class, effDate, Collections.singletonList("principalId"), false));
        root.addEqualTo("timestamp", OjbSubQueryUtil.getTimestampSubQuery(LeaveAdjustment.class, Collections.singletonList("principalId"), false));

        Criteria activeFilter = new Criteria(); // Inner Join For Activity
        activeFilter.addEqualTo("active", true);
        root.addAndCriteria(activeFilter);

        Query query = QueryFactory.newQuery(LeaveAdjustment.class, root);
        Collection c = this.getPersistenceBrokerTemplate().getCollectionByQuery(query);

        if (c != null) {
        	leaveAdjustments.addAll(c);
        }
        return leaveAdjustments;
    }

	@Override
	public LeaveAdjustment getLeaveAdjustment(String lmLeaveAdjustmentId) {
		Criteria crit = new Criteria();
        crit.addEqualTo("lmLeaveAdjustmentId", lmLeaveAdjustmentId);
        Query query = QueryFactory.newQuery(LeaveAdjustment.class, crit);
        return (LeaveAdjustment) this.getPersistenceBrokerTemplate().getObjectByQuery(query);
	}

}
