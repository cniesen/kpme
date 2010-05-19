package edu.iu.uis.hr.tk.rule.dataaccess;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRuleSearchCriteria;

public interface ShiftDifferentialRuleDataAccess extends DataAccessOjb {
    public TypedPersistentMaintainedEntityList lookupMaintainableShiftDifferentialRules(ShiftDifferentialRuleSearchCriteria shiftDifferentialSearchCriteria);

    public List getShiftDifferentialRules(PayCalendar payCalendar);

    public List getShiftDifferentialRules(String earnCode, Date transactionRecordEffectiveDate);

}