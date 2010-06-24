package edu.iu.uis.hr.tk.rule.dataaccess;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.HolidayIncentiveRuleSearchCriteria;

public interface HolidayIncentiveRuleDataAccess extends DataAccess {
    
    public TypedPersistentMaintainedEntityList lookupMaintainableHolidayIncentiveRules(HolidayIncentiveRuleSearchCriteria holidayIncentiveRuleSearchCriteria);

//    public List getHolidayIncentiveRules(PayCalendar payCalendar);
//
//    public List getHolidayIncentiveRules(String earnCode, Date transactionRecordEffectiveDate);

}