package edu.iu.uis.hr.tk.employee.dataaccess;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.employee.entity.Employee;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;

public class EmployeeDataAccessOjb extends AbstractDataAccessOjb implements EmployeeDataAccess {
    private static final Logger LOG = Logger.getLogger(EmployeeDataAccessOjb.class);

    public Employee getEmployee(String universityId) {
        Employee employee = (Employee) getCurrentActiveRecord(new Employee(universityId));
        return employee;
    }

    public List getActiveEmployees(PayCalendar payCalendar) {
        Criteria employeeCriteria = new Criteria();

        Criteria jobCriteria = new Criteria();
        jobCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        //jobCriteria.addNotIn(FieldNames.EMPLOYEE_STATUS, edu.iu.uis.hr.job.entity.Job.INVALID_EMPLOYEE_ACTIVITY_EMPL_STATUSES);
        // TODO : addNotIn() is not working; it seems that ojb does not handle in criteria in subquery properly.  
        //        as a work around we're iterating over the the list of invalid employee statuses.
        for (Iterator iter = edu.iu.uis.hr.job.entity.Job.INVALID_EMPLOYEE_ACTIVITY_EMPL_STATUSES.iterator(); iter.hasNext();) {
            String nonActiveEmplStatus = (String) iter.next();
            jobCriteria.addNotEqualTo(FieldNames.EMPLOYEE_STATUS, nonActiveEmplStatus);
        }

        Criteria effDateSubCriteria = new Criteria();
        effDateSubCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        effDateSubCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        effDateSubCriteria.addLessOrEqualThan(FieldNames.EFFECTIVE_DATE, payCalendar.getPayBeginDate());
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

        Criteria assignmentCriteria = new Criteria();
        assignmentCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
        assignmentCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
        assignmentCriteria.addEqualToField(FieldNames.EFFECTIVE_DATE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_DATE);
        assignmentCriteria.addEqualToField(FieldNames.EFFECTIVE_SEQUENCE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EFFECTIVE_SEQUENCE);
        jobCriteria.addExists(QueryFactory.newQuery(Assignment.class, assignmentCriteria));

        employeeCriteria.addExists(QueryFactory.newQuery(Job.class, jobCriteria));

        return (List)getCollectionByQuery(QueryFactory.newQuery(Employee.class, employeeCriteria));
    }

}
