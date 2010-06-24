package edu.iu.uis.hr.tk.rule.dataaccess;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.rule.entity.HolidayIncentiveRule;
import edu.iu.uis.hr.tk.rule.entity.HolidayIncentiveRuleSearchCriteria;

public class HolidayIncentiveRuleDataAccessOjb extends AbstractDataAccessOjb implements HolidayIncentiveRuleDataAccess {
    private static final Logger LOG = Logger.getLogger(HolidayIncentiveRuleDataAccessOjb.class);
    
    public TypedPersistentMaintainedEntityList lookupMaintainableHolidayIncentiveRules(HolidayIncentiveRuleSearchCriteria holidayIncentiveSearchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.LOCATION, holidayIncentiveSearchCriteria.getLocation());
        getStandardLookupLogic(lookupCriteria, FieldNames.SALARY_PLAN, holidayIncentiveSearchCriteria.getSalaryPlan());
        getLookupEffectiveLogic(lookupCriteria, HolidayIncentiveRule.class, holidayIncentiveSearchCriteria);
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(HolidayIncentiveRule.class, lookupCriteria));
    }
//
//    public List getHolidayIncentiveRules(PayCalendar payCalendar) {
//        Criteria shiftDifferentialRuleCriteria=new Criteria();
//        // TODO : "A" - find there is any final string defined for this
//        setHolidayIncentiveRuleEffectiveCriteria(shiftDifferentialRuleCriteria,payCalendar);
//        shiftDifferentialRuleCriteria.addEqualTo(FieldNames.ACTIVE,"A");
//        return (List) getCollectionByQuery(QueryFactory.newQuery(ShiftDifferentialRule.class, shiftDifferentialRuleCriteria));
//    }
//    
//    private void setHolidayIncentiveRuleEffectiveCriteria(Criteria shiftDifferentialRuleCriteria, PayCalendar payCalendar) {
//        Criteria effDateSubCriteria = new Criteria();
//        effDateSubCriteria.addEqualToField(FieldNames.LOCATION, Criteria.PARENT_QUERY_PREFIX + FieldNames.LOCATION);
//        effDateSubCriteria.addEqualToField(FieldNames.SALARY_PLAN, Criteria.PARENT_QUERY_PREFIX + FieldNames.SALARY_PLAN);
//        effDateSubCriteria.addEqualToField(FieldNames.GRADE, Criteria.PARENT_QUERY_PREFIX + FieldNames.GRADE);
//        effDateSubCriteria.addEqualToField(FieldNames.EARN_CODE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EARN_CODE);
//        effDateSubCriteria.addLessOrEqualThan(FieldNames.EFFECTIVE_DATE, payCalendar.getPayBeginDate());
//        ReportQueryByCriteria effectiveDateSubQuery = QueryFactory.newReportQuery(ShiftDifferentialRule.class, effDateSubCriteria);
//        effectiveDateSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_DATE).append(")").toString() });
//        Criteria effDateLessCriteria = new Criteria();
//        effDateLessCriteria.addEqualTo(FieldNames.EFFECTIVE_DATE, effectiveDateSubQuery);
//
//        Criteria effDateSubOrCriteria = new Criteria();
//        effDateSubOrCriteria.addGreaterThan(FieldNames.EFFECTIVE_DATE, payCalendar.getPayBeginDate());
//        effDateSubOrCriteria.addLessOrEqualThan(FieldNames.EFFECTIVE_DATE, payCalendar.getPayEndDate());
//        effDateLessCriteria.addOrCriteria(effDateSubOrCriteria);
//
//        shiftDifferentialRuleCriteria.addAndCriteria(effDateLessCriteria);
//
//        Criteria effSeqSubCriteria = new Criteria();
//        effSeqSubCriteria.addEqualToField(FieldNames.LOCATION, Criteria.PARENT_QUERY_PREFIX + FieldNames.LOCATION);
//        effSeqSubCriteria.addEqualToField(FieldNames.SALARY_PLAN, Criteria.PARENT_QUERY_PREFIX + FieldNames.SALARY_PLAN);
//        effSeqSubCriteria.addEqualToField(FieldNames.GRADE, Criteria.PARENT_QUERY_PREFIX + FieldNames.GRADE);
//        effSeqSubCriteria.addEqualToField(FieldNames.EFFECTIVE_DATE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_DATE);
//        effSeqSubCriteria.addEqualToField(FieldNames.EARN_CODE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EARN_CODE);
//        ReportQueryByCriteria effectiveSeqSubQuery = QueryFactory.newReportQuery(ShiftDifferentialRule.class, effSeqSubCriteria);
//        effectiveSeqSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_SEQUENCE).append(")").toString() });
//        shiftDifferentialRuleCriteria.addEqualTo(FieldNames.EFFECTIVE_SEQUENCE, effectiveSeqSubQuery);
//
//    }
//
//    
//    public List getHolidayIncentiveRules(String earnCode,Date transactionRecordEffectiveDate) {
//        Criteria shiftDifferentialRuleCriteria=new Criteria();
//
//        Criteria effDateSubCriteria = new Criteria();
//        effDateSubCriteria.addEqualToField(FieldNames.LOCATION, Criteria.PARENT_QUERY_PREFIX + FieldNames.LOCATION);
//        effDateSubCriteria.addEqualToField(FieldNames.SALARY_PLAN, Criteria.PARENT_QUERY_PREFIX + FieldNames.SALARY_PLAN);
//        effDateSubCriteria.addEqualToField(FieldNames.GRADE, Criteria.PARENT_QUERY_PREFIX + FieldNames.GRADE);
//        effDateSubCriteria.addLessOrEqualThan(FieldNames.EFFECTIVE_DATE, transactionRecordEffectiveDate);
//        ReportQueryByCriteria effectiveDateSubQuery = QueryFactory.newReportQuery(ShiftDifferentialRule.class, effDateSubCriteria);
//        effectiveDateSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_DATE).append(")").toString() });
//        Criteria effDateLessCriteria = new Criteria();
//        effDateLessCriteria.addEqualTo(FieldNames.EFFECTIVE_DATE, effectiveDateSubQuery);
//
//        shiftDifferentialRuleCriteria.addAndCriteria(effDateLessCriteria);
//
//        Criteria effSeqSubCriteria = new Criteria();
//        effDateSubCriteria.addEqualToField(FieldNames.LOCATION, Criteria.PARENT_QUERY_PREFIX + FieldNames.LOCATION);
//        effDateSubCriteria.addEqualToField(FieldNames.SALARY_PLAN, Criteria.PARENT_QUERY_PREFIX + FieldNames.SALARY_PLAN);
//        effDateSubCriteria.addEqualToField(FieldNames.GRADE, Criteria.PARENT_QUERY_PREFIX + FieldNames.GRADE);
//        effSeqSubCriteria.addEqualToField(FieldNames.EFFECTIVE_DATE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_DATE);
//        ReportQueryByCriteria effectiveSeqSubQuery = QueryFactory.newReportQuery(ShiftDifferentialRule.class, effSeqSubCriteria);
//        effectiveSeqSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_SEQUENCE).append(")").toString() });
//        shiftDifferentialRuleCriteria.addEqualTo(FieldNames.EFFECTIVE_SEQUENCE, effectiveSeqSubQuery);
//
//        
//        shiftDifferentialRuleCriteria.addEqualTo(FieldNames.ACTIVE,"A");
//        shiftDifferentialRuleCriteria.addEqualTo(FieldNames.EARN_CODE,earnCode);
//        return (List) getCollectionByQuery(QueryFactory.newQuery(ShiftDifferentialRule.class, new Criteria()));
//    }

}