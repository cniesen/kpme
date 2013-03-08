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
package org.kuali.hr.time.principal.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.hr.core.util.OjbSubQueryUtil;
import org.kuali.hr.time.principal.PrincipalHRAttributes;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

public class PrincipalHRAttributesDaoImpl extends PlatformAwareDaoBaseOjb implements PrincipalHRAttributesDao {

	@Override
	public PrincipalHRAttributes getPrincipalCalendar(String principalId,
			java.util.Date asOfDate) {
		PrincipalHRAttributes pc = null;

		Criteria root = new Criteria();

        ImmutableList<String> fields = new ImmutableList.Builder<String>()
                .add("principalId")
                .build();

		root.addEqualTo("principalId", principalId);
        java.sql.Date effDate = asOfDate == null ? null : new java.sql.Date(asOfDate.getTime());
        root.addEqualTo("effectiveDate", OjbSubQueryUtil.getEffectiveDateSubQuery(PrincipalHRAttributes.class, effDate, fields, false));
        root.addEqualTo("timestamp", OjbSubQueryUtil.getTimestampSubQuery(PrincipalHRAttributes.class, fields, false));

		Criteria activeFilter = new Criteria(); // Inner Join For Activity
		activeFilter.addEqualTo("active", true);
		root.addAndCriteria(activeFilter);
		
		Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, root);
		Object obj = this.getPersistenceBrokerTemplate().getObjectByQuery(query);

		if (obj != null) {
			pc = (PrincipalHRAttributes) obj;
		}

		return pc;
	}

	@Override
	public void saveOrUpdate(PrincipalHRAttributes principalCalendar) {
		this.getPersistenceBrokerTemplate().store(principalCalendar);
		
	}

	@Override
	public void saveOrUpdate(List<PrincipalHRAttributes> lstPrincipalCalendar) {
		if(lstPrincipalCalendar != null){
			for(PrincipalHRAttributes principalCal : lstPrincipalCalendar){
				this.getPersistenceBrokerTemplate().store(principalCal);
			}
		}
		
	}

//    @Override
//	public PrincipalHRAttributes getPrincipalHRAttributes(String principalId) {
//		Criteria crit = new Criteria();
//		crit.addEqualTo("principalId", principalId);
//		Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, crit);
//		return (PrincipalHRAttributes)this.getPersistenceBrokerTemplate().getObjectByQuery(query);		
//	}
    
    @Override
    public PrincipalHRAttributes getInactivePrincipalHRAttributes(String principalId, java.util.Date asOfDate) {
    	PrincipalHRAttributes pc = null;

		Criteria root = new Criteria();
		Criteria effdt = new Criteria();
		Criteria timestamp = new Criteria();

        effdt.addGreaterOrEqualThan("effectiveDate", asOfDate);
        ImmutableList<String> fields = new ImmutableList.Builder<String>()
                .add("leavePlan")
                .add("principalId")
                .build();
        root.addEqualTo("effectiveDate", OjbSubQueryUtil.getEffectiveDateSubQueryWithFilter(PrincipalHRAttributes.class, effdt, fields, false));
        root.addEqualTo("timestamp", OjbSubQueryUtil.getTimestampSubQuery(PrincipalHRAttributes.class, fields, false));

		root.addEqualTo("principalId", principalId);

		Criteria activeFilter = new Criteria(); // Inner Join For Activity
		activeFilter.addEqualTo("active", false);
		root.addAndCriteria(activeFilter);
		
		Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, root);
		Object obj = this.getPersistenceBrokerTemplate().getObjectByQuery(query);

		if (obj != null) {
			pc = (PrincipalHRAttributes) obj;
		}

		return pc;
    }
    
    @Override
    public PrincipalHRAttributes getPrincipalHRAttributes(String hrPrincipalAttributeId) {
    	Criteria crit = new Criteria();
		crit.addEqualTo("hrPrincipalAttributeId", hrPrincipalAttributeId);
		
		Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, crit);
		return (PrincipalHRAttributes)this.getPersistenceBrokerTemplate().getObjectByQuery(query);
    }
    
    @Override
    public List<PrincipalHRAttributes> getAllActivePrincipalHrAttributesForPrincipalId(String principalId, Date asOfDate) {
    	
    	List<PrincipalHRAttributes> phaList = new ArrayList<PrincipalHRAttributes>();
    	Criteria root = new Criteria();
        root.addEqualTo("principalId", principalId);
        root.addLessOrEqualThan("effectiveDate", asOfDate);
        root.addEqualTo("active", true);
        Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, root);
        Collection c = this.getPersistenceBrokerTemplate().getCollectionByQuery(query);
        if (c != null) {
            phaList.addAll(c);
        }
        return phaList;        
    }
    
    @Override
    public List<PrincipalHRAttributes> getAllInActivePrincipalHrAttributesForPrincipalId(String principalId, Date asOfDate) {
    	List<PrincipalHRAttributes> phaList = new ArrayList<PrincipalHRAttributes>();
    	Criteria root = new Criteria();
        root.addEqualTo("principalId", principalId);
        root.addLessOrEqualThan("effectiveDate", asOfDate);
        root.addEqualTo("active", false);

        Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, root);
        Collection c = this.getPersistenceBrokerTemplate().getCollectionByQuery(query);
        if (c != null) {
            phaList.addAll(c);
        }
        return phaList;  
    }
    @Override
    public PrincipalHRAttributes getMaxTimeStampPrincipalHRAttributes(String principalId) {
    	Criteria root = new Criteria();
        Criteria crit = new Criteria();
        
        crit.addEqualTo("principalId", principalId);
        ReportQueryByCriteria timestampSubQuery = QueryFactory.newReportQuery(PrincipalHRAttributes.class, crit);
        timestampSubQuery.setAttributes(new String[]{"max(timestamp)"});

        root.addEqualTo("principalId", principalId);
        root.addEqualTo("timestamp", timestampSubQuery);

        Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, root);
        return (PrincipalHRAttributes) this.getPersistenceBrokerTemplate().getObjectByQuery(query);
    }
    
    @Override
    public List<PrincipalHRAttributes> getActivePrincipalHrAttributesForRange(String principalId, Date startDate, Date endDate) {
    	List<PrincipalHRAttributes> activeList = new ArrayList<PrincipalHRAttributes>();
    	Criteria root = new Criteria();
        root.addEqualTo("principalId", principalId);
        root.addGreaterOrEqualThan("effectiveDate", startDate);
        root.addLessOrEqualThan("effectiveDate", endDate);
        root.addEqualTo("active", true);
        Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, root);
        Collection c = this.getPersistenceBrokerTemplate().getCollectionByQuery(query);
        if (c != null) {
        	activeList.addAll(c);
        }
        List<PrincipalHRAttributes> aList = new ArrayList<PrincipalHRAttributes>();
        aList.addAll(activeList);
        for(PrincipalHRAttributes aPha : aList) {
        	List<PrincipalHRAttributes> inactivePhas = this.getInactivePrincipalHRAttributesForRange(principalId, aPha.getEffectiveDate(), endDate);
        	if(CollectionUtils.isNotEmpty(inactivePhas)) {
        		for(PrincipalHRAttributes inactive : inactivePhas) {
        			if(inactive.getTimestamp().after(aPha.getTimestamp())) {
        				activeList.remove(aPha);
        			}
        		}
        	}
        }
        
        return activeList;   
    }
    
    @Override
    public List<PrincipalHRAttributes> getInactivePrincipalHRAttributesForRange(String principalId, Date startDate, Date endDate) {
    	List<PrincipalHRAttributes> inactiveList = new ArrayList<PrincipalHRAttributes>();
    	Criteria root = new Criteria();
        root.addEqualTo("principalId", principalId);
        root.addGreaterOrEqualThan("effectiveDate", startDate);
        root.addLessOrEqualThan("effectiveDate", endDate);
        root.addEqualTo("active", false);
        Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, root);
        Collection c = this.getPersistenceBrokerTemplate().getCollectionByQuery(query);
        if (c != null) {
        	inactiveList.addAll(c);
        }
        return inactiveList;
    }

	@Override
    @SuppressWarnings("unchecked")
    public List<PrincipalHRAttributes> getPrincipalHrAtributes(String principalId, java.sql.Date fromEffdt, java.sql.Date toEffdt, String active, String showHistory) {
    	List<PrincipalHRAttributes> results = new ArrayList<PrincipalHRAttributes>();
        
    	Criteria root = new Criteria();
    	
        if (StringUtils.isNotBlank(principalId)) {
            root.addLike("principalId", principalId);
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
            ImmutableList<String> fields = new ImmutableList.Builder<String>()
                    .add("principalId")
                    .build();
            root.addEqualTo("effectiveDate", OjbSubQueryUtil.getEffectiveDateSubQueryWithFilter(PrincipalHRAttributes.class, effectiveDateFilter, fields, false));
            root.addEqualTo("timestamp", OjbSubQueryUtil.getTimestampSubQuery(PrincipalHRAttributes.class, fields, false));
       }
        
       Query query = QueryFactory.newQuery(PrincipalHRAttributes.class, root);
       results.addAll(getPersistenceBrokerTemplate().getCollectionByQuery(query));
       
       return results;
    }

}