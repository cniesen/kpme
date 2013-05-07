package org.kuali.kpme.pm.positionreportgroup.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.bo.utils.OjbSubQueryUtil;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.kpme.pm.positionreportgroup.PositionReportGroup;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

import com.google.common.collect.ImmutableList;

public class PositionReportGroupDaoObjImpl extends PlatformAwareDaoBaseOjb implements PositionReportGroupDao {

	@Override
	public PositionReportGroup getPositionReportGroupById(
			String pmPositionReportGroupId) {
		Criteria crit = new Criteria();
        crit.addEqualTo("pmPositionReportGroupId", pmPositionReportGroupId);

        Query query = QueryFactory.newQuery(PositionReportGroup.class, crit);
        return (PositionReportGroup) this.getPersistenceBrokerTemplate().getObjectByQuery(query);
	}

	@Override
	public List<PositionReportGroup> getPositionReportGroupList(String positionReportGroup, String institution, String campus, LocalDate asOfDate) {
		List<PositionReportGroup> prgList = new ArrayList<PositionReportGroup>();
		Criteria root = new Criteria();

		if(StringUtils.isNotEmpty(positionReportGroup) 
				&& !StringUtils.equals(positionReportGroup, HrConstants.WILDCARD_CHARACTER)) {
			root.addEqualTo("positionReportGroup", positionReportGroup);  
		}
		if(StringUtils.isNotEmpty(institution) 
				&& !StringUtils.equals(institution, HrConstants.WILDCARD_CHARACTER)) {
			root.addEqualTo("institution", institution); 
		}
		if(StringUtils.isNotEmpty(campus) 
				&& !StringUtils.equals(campus, HrConstants.WILDCARD_CHARACTER)) {
			root.addEqualTo("campus", campus); 
		}
        
        root.addEqualTo("effectiveDate", OjbSubQueryUtil.getEffectiveDateSubQuery(PositionReportGroup.class, asOfDate, PositionReportGroup.EQUAL_TO_FIELDS, false));
        root.addEqualTo("timestamp", OjbSubQueryUtil.getTimestampSubQuery(PositionReportGroup.class, PositionReportGroup.EQUAL_TO_FIELDS, false));
        
        Criteria activeFilter = new Criteria();
        activeFilter.addEqualTo("active", true);
        root.addAndCriteria(activeFilter);

        Query query = QueryFactory.newQuery(PositionReportGroup.class, root);
        
        Collection c = this.getPersistenceBrokerTemplate().getCollectionByQuery(query);
		if(!c.isEmpty())
			prgList.addAll(c);
		
		return prgList;
	}

}
