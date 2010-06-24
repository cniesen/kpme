package edu.iu.uis.hr.tk.rule.dataaccess;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.BreakRule;
import edu.iu.uis.hr.tk.rule.entity.BreakRuleSearchCriteria;

public class BreakRuleDataAccessOjb extends AbstractDataAccessOjb implements BreakRuleDataAccess {
    
    private static final Logger LOG = Logger.getLogger(BreakRuleDataAccessOjb.class);
    private static String TRUE = "A";
    
    public TypedPersistentMaintainedEntityList lookupMaintainableBreakRules(BreakRuleSearchCriteria breakRuleSearchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.DEPARTMENT, breakRuleSearchCriteria.getDepartment());
        getStandardLookupLogic(lookupCriteria, FieldNames.WORK_AREA, breakRuleSearchCriteria.getWorkArea());
        getStandardLookupLogic(lookupCriteria, FieldNames.UNIVERSITY_ID, breakRuleSearchCriteria.getUniversityId());
        getStandardLookupLogic(lookupCriteria, FieldNames.EMPLOYEE_RECORD, breakRuleSearchCriteria.getEmployeeRecord());
        if (breakRuleSearchCriteria.isActive()){
          getStandardLookupLogic(lookupCriteria, FieldNames.ACTIVE, TRUE);
        }
        getLookupEffectiveLogic(lookupCriteria, BreakRule.class, breakRuleSearchCriteria);
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(BreakRule.class, lookupCriteria));
    }
}