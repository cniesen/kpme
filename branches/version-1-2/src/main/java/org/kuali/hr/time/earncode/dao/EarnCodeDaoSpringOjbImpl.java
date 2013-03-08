/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.hr.time.earncode.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.hr.core.util.OjbSubQueryUtil;
import org.kuali.hr.time.earncode.EarnCode;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

public class EarnCodeDaoSpringOjbImpl extends PlatformAwareDaoBaseOjb implements EarnCodeDao {
    private static final ImmutableList<String> EQUAL_TO_FIELDS = new ImmutableList.Builder<String>()
            .add("earnCode")
            //.add("leavePlan")
            .build();

	private static final Logger LOG = Logger.getLogger(EarnCodeDaoSpringOjbImpl.class);

	public void saveOrUpdate(EarnCode earnCode) {
		this.getPersistenceBrokerTemplate().store(earnCode);
	}

	public void saveOrUpdate(List<EarnCode> earnCodeList) {
		if (earnCodeList != null) {
			for (EarnCode earnCode : earnCodeList) {
				this.getPersistenceBrokerTemplate().store(earnCode);
			}
		}
	}

	public EarnCode getEarnCodeById(String earnCodeId) {
		Criteria crit = new Criteria();
		crit.addEqualTo("hrEarnCodeId", earnCodeId);
		return (EarnCode) this.getPersistenceBrokerTemplate().getObjectByQuery(QueryFactory.newQuery(EarnCode.class, crit));
	}

	@Override
	public EarnCode getEarnCode(String earnCode, Date asOfDate) {
		EarnCode ec = null;

		Criteria root = new Criteria();

		root.addEqualTo("earnCode", earnCode);
        root.addEqualTo("effectiveDate", OjbSubQueryUtil.getEffectiveDateSubQuery(EarnCode.class, asOfDate, EQUAL_TO_FIELDS, false));
        root.addEqualTo("timestamp", OjbSubQueryUtil.getTimestampSubQuery(EarnCode.class, EQUAL_TO_FIELDS, false));

		Criteria activeFilter = new Criteria(); // Inner Join For Activity
		activeFilter.addEqualTo("active", true);
		root.addAndCriteria(activeFilter);
		
		
		Query query = QueryFactory.newQuery(EarnCode.class, root);
		Object obj = this.getPersistenceBrokerTemplate().getObjectByQuery(query);

		if (obj != null) {
			ec = (EarnCode) obj;
		}

		return ec;
	}

	@Override
	public List<EarnCode> getOvertimeEarnCodes(Date asOfDate) {
		Criteria root = new Criteria();

		root.addEqualTo("ovtEarnCode", "Y");
        root.addEqualTo("effectiveDate", OjbSubQueryUtil.getEffectiveDateSubQuery(EarnCode.class, asOfDate, EQUAL_TO_FIELDS, false));
        root.addEqualTo("timestamp", OjbSubQueryUtil.getTimestampSubQuery(EarnCode.class, EQUAL_TO_FIELDS, false));
//		root.addEqualTo("active", true);
		
		Criteria activeFilter = new Criteria(); // Inner Join For Activity
		activeFilter.addEqualTo("active", true);
		root.addAndCriteria(activeFilter);
		
		
		Query query = QueryFactory.newQuery(EarnCode.class, root);
		List<EarnCode> ovtEarnCodes = (List<EarnCode>)this.getPersistenceBrokerTemplate().getCollectionByQuery(query);
		return ovtEarnCodes;
	}
	
	@Override
	public int getEarnCodeCount(String earnCode) {
		Criteria crit = new Criteria();
		crit.addEqualTo("earnCode", earnCode);
		Query query = QueryFactory.newQuery(EarnCode.class, crit);
		return this.getPersistenceBrokerTemplate().getCount(query);
	}
	
	@Override
	public int getNewerEarnCodeCount(String earnCode, Date effdt) {
		Criteria crit = new Criteria();
		crit.addEqualTo("earnCode", earnCode);
		crit.addEqualTo("active", "Y");
		crit.addGreaterThan("effectiveDate", effdt);
		Query query = QueryFactory.newQuery(EarnCode.class, crit);
       	return this.getPersistenceBrokerTemplate().getCount(query);
	}

	@Override
    @SuppressWarnings("unchecked")
    public List<EarnCode> getEarnCodes(String earnCode, String ovtEarnCode, String descr, Date fromEffdt, Date toEffdt, String active, String showHistory) {
        List<EarnCode> results = new ArrayList<EarnCode>();
        
        Criteria root = new Criteria();
        
        if (StringUtils.isNotBlank(earnCode)) {
            root.addLike("earnCode", earnCode);
        }
        
        if (StringUtils.isNotBlank(ovtEarnCode)) {
            root.addEqualTo("ovtEarnCode", ovtEarnCode);
        }
        
        if (StringUtils.isNotBlank(descr)) {
            root.addLike("description", descr);
        }

        Criteria effectiveDateFilter = new Criteria();
        if (fromEffdt != null) {
            effectiveDateFilter.addGreaterOrEqualThan("effectiveDate", fromEffdt);
        }
        if (toEffdt != null) {
            effectiveDateFilter.addLessOrEqualThan("effectiveDate", toEffdt);
        }
        if (fromEffdt == null && toEffdt == null) {
            effectiveDateFilter.addLessOrEqualThan("effectiveDate", TKUtils.getCurrentDate());
        }
        root.addAndCriteria(effectiveDateFilter);
        
        if (StringUtils.isNotBlank(active)) {
        	Criteria activeFilter = new Criteria();
            if (StringUtils.equals(active, "Y")) {
                activeFilter.addEqualTo("active", true);
            } else if (StringUtils.equals(active, "N")) {
                activeFilter.addEqualTo("active", false);
            }
            root.addAndCriteria(activeFilter);
        }

        if (StringUtils.equals(showHistory, "N")) {
            root.addEqualTo("effectiveDate", OjbSubQueryUtil.getEffectiveDateSubQueryWithFilter(EarnCode.class, effectiveDateFilter, EQUAL_TO_FIELDS, false));
            root.addEqualTo("timestamp", OjbSubQueryUtil.getTimestampSubQuery(EarnCode.class, EQUAL_TO_FIELDS, false));
        }
        
        Query query = QueryFactory.newQuery(EarnCode.class, root);
        results.addAll(getPersistenceBrokerTemplate().getCollectionByQuery(query));
        
        return results;
    }
	
}