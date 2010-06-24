package edu.iu.uis.hr.tk.rule.dataaccess;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.BreakRuleSearchCriteria;

public interface BreakRuleDataAccess extends DataAccess {
    public TypedPersistentMaintainedEntityList lookupMaintainableBreakRules(BreakRuleSearchCriteria breakRuleSearchCriteria);
}