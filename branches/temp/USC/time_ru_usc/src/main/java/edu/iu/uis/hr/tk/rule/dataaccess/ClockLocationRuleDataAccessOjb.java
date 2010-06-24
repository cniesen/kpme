package edu.iu.uis.hr.tk.rule.dataaccess;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRule;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRuleSearchCriteria;

public class ClockLocationRuleDataAccessOjb extends AbstractDataAccessOjb implements ClockLocationRuleDataAccess {
    private static final Logger LOG = Logger.getLogger(ClockLocationRuleDataAccessOjb.class);

    public TypedPersistentMaintainedEntityList lookupMaintainableClockLocationRules(ClockLocationRuleSearchCriteria clockLocationRuleSearchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.DEPARTMENT, clockLocationRuleSearchCriteria.getDepartment());
        getStandardLookupLogic(lookupCriteria, FieldNames.WORK_AREA, clockLocationRuleSearchCriteria.getWorkArea());
        getStandardLookupLogic(lookupCriteria, FieldNames.UNIVERSITY_ID, clockLocationRuleSearchCriteria.getUniversityId());
        getStandardLookupLogic(lookupCriteria, FieldNames.EMPLOYEE_RECORD, clockLocationRuleSearchCriteria.getEmployeeRecord());

        getLookupEffectiveLogic(lookupCriteria, ClockLocationRule.class, clockLocationRuleSearchCriteria);
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(ClockLocationRule.class, lookupCriteria));

    }

	public ClockLocationRule getClockLocationRule(String universityId, BigDecimal employeeRecord, String department, BigDecimal workArea, Date effectiveDate, BigDecimal effectiveSequence,
			boolean actiive) {
		// TODO Auto-generated method stub
		return null;
	}
}