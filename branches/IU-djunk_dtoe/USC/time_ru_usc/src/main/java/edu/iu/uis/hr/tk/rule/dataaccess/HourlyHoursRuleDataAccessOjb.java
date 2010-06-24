package edu.iu.uis.hr.tk.rule.dataaccess;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.HourlyHoursRule;
import edu.iu.uis.hr.tk.rule.entity.HourlyHoursRuleSearchCriteria;

public class HourlyHoursRuleDataAccessOjb extends AbstractDataAccessOjb implements HourlyHoursRuleDataAccess {
    private static final Logger LOG = Logger.getLogger(HourlyHoursRuleDataAccessOjb.class);

    public TypedPersistentMaintainedEntityList lookupMaintainableHourlyHoursRules(HourlyHoursRuleSearchCriteria hourlyHoursRuleSearchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.DEPARTMENT, hourlyHoursRuleSearchCriteria.getDepartment());
        getStandardLookupLogic(lookupCriteria, FieldNames.WORK_AREA, hourlyHoursRuleSearchCriteria.getWorkArea());
        getStandardLookupLogic(lookupCriteria, FieldNames.UNIVERSITY_ID, hourlyHoursRuleSearchCriteria.getUniversityId());
        getStandardLookupLogic(lookupCriteria, FieldNames.EMPLOYEE_RECORD, hourlyHoursRuleSearchCriteria.getEmployeeRecord());
        getLookupEffectiveLogic(lookupCriteria, HourlyHoursRule.class, hourlyHoursRuleSearchCriteria);
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(HourlyHoursRule.class, lookupCriteria));
    }
}