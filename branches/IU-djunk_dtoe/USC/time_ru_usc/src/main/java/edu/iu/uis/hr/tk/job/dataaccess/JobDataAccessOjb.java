package edu.iu.uis.hr.tk.job.dataaccess;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;

public class JobDataAccessOjb extends AbstractDataAccessOjb implements JobDataAccess {
    private static final Logger LOG = Logger.getLogger(JobDataAccessOjb.class);


    public Job getJob(String universityId, BigDecimal employeeRecord, Date transactionRecordEffectiveDate) {
        Job job = (Job)getCurrentActiveRecord(new Job(universityId, employeeRecord, transactionRecordEffectiveDate));
        return job;
    }
    
    public TypedPersistentMaintainedEntityList getJobs(String universityId, PayCalendar payCalendar) {
        return getJobs(universityId, payCalendar, false);
    }

    public TypedPersistentMaintainedEntityList getJobs(String universityId, PayCalendar payCalendar, boolean checkPayPeriodOnly) {
        Criteria jobCriteria = new Criteria();
        jobCriteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
        jobCriteria.addNotIn(FieldNames.EMPLOYEE_STATUS, edu.iu.uis.hr.job.entity.Job.INVALID_EMPLOYEE_ACTIVITY_EMPL_STATUSES);

        setJobEffectiveCriteria(jobCriteria, payCalendar, checkPayPeriodOnly);
        LOG.debug(jobCriteria.toString());
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(Job.class, jobCriteria));
    }
    
//    public List getJobs(PayCalendar payCalendar) {
//        LOG.info("Started getActiveJobs(Date payEndDate) Method");
//        Criteria jobCriteria = new Criteria();
//        jobCriteria.addNotIn(FieldNames.EMPLOYEE_STATUS, edu.iu.uis.hr.job.entity.Job.INVALID_EMPLOYEE_ACTIVITY_EMPL_STATUSES);
//
//        setJobEffectiveCriteria(jobCriteria, payCalendar);
//
//        Criteria assignmentCriteria = new Criteria();
//        assignmentCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
//        assignmentCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
//        assignmentCriteria.addEqualToField(FieldNames.EFFECTIVE_DATE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_DATE);
//        assignmentCriteria.addEqualToField(FieldNames.EFFECTIVE_SEQUENCE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_SEQUENCE);
//        jobCriteria.addExists(QueryFactory.newQuery(Assignment.class, assignmentCriteria));
//
//        LOG.info("Finished getActiveJobs(Date payEndDate) Method");
//        return (List) getCollectionByQuery(QueryFactory.newQuery(Job.class, jobCriteria));
//    }
    
    public TypedPersistentMaintainedEntityList getTermedJobs(String universityId, PayCalendar payCalendar, BigDecimal employeeRecord) {
        Criteria jobCriteria = new Criteria();
        jobCriteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
        jobCriteria.addIn(FieldNames.EMPLOYEE_STATUS, edu.iu.uis.hr.job.entity.Job.INVALID_EMPLOYEE_ACTIVITY_EMPL_STATUSES);
        jobCriteria.addEqualTo(FieldNames.EMPLOYEE_RECORD, employeeRecord);
        setJobEffectiveCriteria(jobCriteria, payCalendar);

        LOG.debug(jobCriteria.toString());
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(Job.class, jobCriteria));
    }


    private void setJobEffectiveCriteria(Criteria jobCriteria, PayCalendar payCalendar) {
    	setJobEffectiveCriteria(jobCriteria, payCalendar, false);
    }

    private void setJobEffectiveCriteria(Criteria jobCriteria, PayCalendar payCalendar, boolean checkPayPeriodOnly) {
        Criteria effDateSubCriteria = new Criteria();
        effDateSubCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        effDateSubCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        if (!checkPayPeriodOnly) {
            effDateSubCriteria.addLessOrEqualThan(FieldNames.EFFECTIVE_DATE, payCalendar.getPayBeginDate());
        }
        ReportQueryByCriteria effectiveDateSubQuery = QueryFactory.newReportQuery(Job.class, effDateSubCriteria);
        effectiveDateSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_DATE).append(")").toString() });
        Criteria effDateLessCriteria = new Criteria();
        effDateLessCriteria.addEqualTo(FieldNames.EFFECTIVE_DATE, effectiveDateSubQuery);

        Criteria effDateSubOrCriteria = new Criteria();
        effDateSubOrCriteria.addGreaterThan(FieldNames.EFFECTIVE_DATE, payCalendar.getPayBeginDate());
        effDateSubOrCriteria.addLessOrEqualThan(FieldNames.EFFECTIVE_DATE, payCalendar.getPayEndDate());
        effDateLessCriteria.addOrCriteria(effDateSubOrCriteria);

        jobCriteria.addAndCriteria(effDateLessCriteria);

        Criteria effSeqSubCriteria = new Criteria();
        effSeqSubCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        effSeqSubCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        effSeqSubCriteria.addEqualToField(FieldNames.EFFECTIVE_DATE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_DATE);
        ReportQueryByCriteria effectiveSeqSubQuery = QueryFactory.newReportQuery(Job.class, effSeqSubCriteria);
        effectiveSeqSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_SEQUENCE).append(")").toString() });
        jobCriteria.addEqualTo(FieldNames.EFFECTIVE_SEQUENCE, effectiveSeqSubQuery);

    }
    
    @SuppressWarnings("unchecked")
	public List getJobs(Date date, BigDecimal workArea) {
        // used to check whether it's ok to terminate a work area
        Criteria jobCriteria = new Criteria();
        jobCriteria.addNotIn(FieldNames.EMPLOYEE_STATUS, edu.iu.uis.hr.job.entity.Job.INVALID_EMPLOYEE_ACTIVITY_EMPL_STATUSES);

        Criteria effDateSubCriteria = new Criteria();
        effDateSubCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        effDateSubCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        effDateSubCriteria.addLessOrEqualThan(FieldNames.EFFECTIVE_DATE, date);
        ReportQueryByCriteria effectiveDateSubQuery = QueryFactory.newReportQuery(Job.class, effDateSubCriteria);
        effectiveDateSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_DATE).append(")").toString() });
        Criteria effDateLessCriteria = new Criteria();
        effDateLessCriteria.addEqualTo(FieldNames.EFFECTIVE_DATE, effectiveDateSubQuery);

        Criteria effDateSubOrCriteria = new Criteria();
        effDateSubOrCriteria.addGreaterThan(FieldNames.EFFECTIVE_DATE, date);
        effDateLessCriteria.addOrCriteria(effDateSubOrCriteria);

        jobCriteria.addAndCriteria(effDateLessCriteria);

        Criteria effSeqSubCriteria = new Criteria();
        effSeqSubCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        effSeqSubCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        effSeqSubCriteria.addEqualToField(FieldNames.EFFECTIVE_DATE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_DATE);
        ReportQueryByCriteria effectiveSeqSubQuery = QueryFactory.newReportQuery(Job.class, effSeqSubCriteria);
        effectiveSeqSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_SEQUENCE).append(")").toString() });
        jobCriteria.addEqualTo(FieldNames.EFFECTIVE_SEQUENCE, effectiveSeqSubQuery);
        
        Criteria assignmentCriteria = new Criteria();
        assignmentCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        assignmentCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        assignmentCriteria.addEqualToField(FieldNames.EFFECTIVE_DATE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_DATE);
        assignmentCriteria.addEqualToField(FieldNames.EFFECTIVE_SEQUENCE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_SEQUENCE);
        assignmentCriteria.addEqualTo(FieldNames.WORK_AREA, workArea);
        jobCriteria.addExists(QueryFactory.newQuery(Assignment.class, assignmentCriteria));

        LOG.debug(jobCriteria.toString());
        return (List) getCollectionByQuery(QueryFactory.newQuery(Job.class, jobCriteria));
    }

    @SuppressWarnings("unchecked")
	public List getJobs(Date date, BigDecimal workArea, BigDecimal task) {
        // used to check whether it's ok to delete a task
        Criteria jobCriteria = new Criteria();
        jobCriteria.addNotIn(FieldNames.EMPLOYEE_STATUS, edu.iu.uis.hr.job.entity.Job.INVALID_EMPLOYEE_ACTIVITY_EMPL_STATUSES);

        Criteria effDateSubCriteria = new Criteria();
        effDateSubCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        effDateSubCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        effDateSubCriteria.addLessOrEqualThan(FieldNames.EFFECTIVE_DATE, date);
        ReportQueryByCriteria effectiveDateSubQuery = QueryFactory.newReportQuery(Job.class, effDateSubCriteria);
        effectiveDateSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_DATE).append(")").toString() });
        Criteria effDateLessCriteria = new Criteria();
        effDateLessCriteria.addEqualTo(FieldNames.EFFECTIVE_DATE, effectiveDateSubQuery);

        Criteria effDateSubOrCriteria = new Criteria();
        effDateSubOrCriteria.addGreaterThan(FieldNames.EFFECTIVE_DATE, date);
        effDateLessCriteria.addOrCriteria(effDateSubOrCriteria);

        jobCriteria.addAndCriteria(effDateLessCriteria);

        Criteria effSeqSubCriteria = new Criteria();
        effSeqSubCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        effSeqSubCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        effSeqSubCriteria.addEqualToField(FieldNames.EFFECTIVE_DATE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_DATE);
        ReportQueryByCriteria effectiveSeqSubQuery = QueryFactory.newReportQuery(Job.class, effSeqSubCriteria);
        effectiveSeqSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_SEQUENCE).append(")").toString() });
        jobCriteria.addEqualTo(FieldNames.EFFECTIVE_SEQUENCE, effectiveSeqSubQuery);
        
        Criteria assignmentCriteria = new Criteria();
        assignmentCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        assignmentCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        assignmentCriteria.addEqualToField(FieldNames.EFFECTIVE_DATE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_DATE);
        assignmentCriteria.addEqualToField(FieldNames.EFFECTIVE_SEQUENCE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_SEQUENCE);
        assignmentCriteria.addEqualTo(FieldNames.WORK_AREA, workArea);
        assignmentCriteria.addEqualTo(FieldNames.TASK, task);        
        jobCriteria.addExists(QueryFactory.newQuery(Assignment.class, assignmentCriteria));

        LOG.debug(jobCriteria.toString());
        return (List) getCollectionByQuery(QueryFactory.newQuery(Job.class, jobCriteria));
    }
    
    
}
