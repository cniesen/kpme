package edu.iu.uis.hr.tk.rule.dataaccess;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRule;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRuleSearchCriteria;

public interface DepartmentLunchRuleDataAccess extends DataAccess {
    public TypedPersistentMaintainedEntityList lookupMaintainableDepartmentLunchRules(DepartmentLunchRuleSearchCriteria departmentLunchRuleSearchCriteria);

    public void addDepartmentLunchRuleManually(DepartmentLunchRule departmentLunchRule);
}