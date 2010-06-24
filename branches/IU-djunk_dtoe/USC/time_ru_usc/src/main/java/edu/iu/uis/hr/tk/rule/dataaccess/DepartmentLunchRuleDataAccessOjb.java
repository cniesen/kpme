package edu.iu.uis.hr.tk.rule.dataaccess;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRule;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRuleSearchCriteria;

public class DepartmentLunchRuleDataAccessOjb extends AbstractDataAccessOjb implements DepartmentLunchRuleDataAccess {
    private static final Logger LOG = Logger.getLogger(DepartmentLunchRuleDataAccessOjb.class);

    public TypedPersistentMaintainedEntityList lookupMaintainableDepartmentLunchRules(DepartmentLunchRuleSearchCriteria departmentLunchRuleSearchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.DEPARTMENT, departmentLunchRuleSearchCriteria.getDepartment());
        getStandardLookupLogic(lookupCriteria, FieldNames.WORK_AREA, departmentLunchRuleSearchCriteria.getWorkArea());
        getStandardLookupLogic(lookupCriteria, FieldNames.UNIVERSITY_ID, departmentLunchRuleSearchCriteria.getUniversityId());
        getStandardLookupLogic(lookupCriteria, FieldNames.EMPLOYEE_RECORD, departmentLunchRuleSearchCriteria.getEmployeeRecord());

        getLookupEffectiveLogic(lookupCriteria, DepartmentLunchRule.class, departmentLunchRuleSearchCriteria);
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(DepartmentLunchRule.class, lookupCriteria));
    }

	public void addDepartmentLunchRuleManually(DepartmentLunchRule departmentLunchRule) {
		store(departmentLunchRule);
	}
}