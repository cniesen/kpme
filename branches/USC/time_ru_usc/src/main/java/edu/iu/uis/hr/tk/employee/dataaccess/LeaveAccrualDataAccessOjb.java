package edu.iu.uis.hr.tk.employee.dataaccess;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.employee.entity.LeaveAccrual;

public class LeaveAccrualDataAccessOjb extends AbstractDataAccessOjb implements LeaveAccrualDataAccess {

	private static final Logger LOG = Logger.getLogger(LeaveAccrualDataAccessOjb.class);
	private static final String EMPLOYEE_RECORD_ZERO = "0";
	private static final String COMPANY = "IU";

	public LeaveAccrual getLeaveAccrual(String universityId, String planType, Date accrualProcessDate) {
		LeaveAccrual result = new LeaveAccrual();

		Criteria leaveCriteria = new Criteria();
		leaveCriteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
		leaveCriteria.addEqualTo(FieldNames.EMPLOYEE_RECORD, EMPLOYEE_RECORD_ZERO );
		leaveCriteria.addEqualTo(FieldNames.COMPANY, COMPANY);
		leaveCriteria.addEqualTo(FieldNames.PLAN_TYPE, planType);

		//add in effective dating/max date/null date to query
		Criteria accrualDateSubCriteria = new Criteria();
		accrualDateSubCriteria.addEqualToField(FieldNames.UNIVERSITY_ID, Criteria.PARENT_QUERY_PREFIX + FieldNames.UNIVERSITY_ID);
		accrualDateSubCriteria.addEqualToField(FieldNames.EMPLOYEE_RECORD, Criteria.PARENT_QUERY_PREFIX + FieldNames.EMPLOYEE_RECORD);
		accrualDateSubCriteria.addEqualToField(FieldNames.COMPANY, Criteria.PARENT_QUERY_PREFIX + FieldNames.COMPANY);
		accrualDateSubCriteria.addEqualToField(FieldNames.PLAN_TYPE, Criteria.PARENT_QUERY_PREFIX + FieldNames.PLAN_TYPE);
		accrualDateSubCriteria.addLessOrEqualThan(FieldNames.ACCRUAL_PROCESS_DATE, accrualProcessDate);
		ReportQueryByCriteria accrualDateSubQuery = QueryFactory.newReportQuery(LeaveAccrual.class, accrualDateSubCriteria);
		accrualDateSubQuery.setAttributes(new String[] {new StringBuffer("max(").append(FieldNames.ACCRUAL_PROCESS_DATE).append(")").toString() });

		leaveCriteria.addEqualTo(FieldNames.ACCRUAL_PROCESS_DATE, accrualDateSubQuery);

		Criteria accrualDateNullCriteria = new Criteria();
		accrualDateNullCriteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
		accrualDateNullCriteria.addEqualTo(FieldNames.EMPLOYEE_RECORD, EMPLOYEE_RECORD_ZERO);
		accrualDateNullCriteria.addEqualTo(FieldNames.COMPANY, COMPANY);
		accrualDateNullCriteria.addEqualTo(FieldNames.PLAN_TYPE, planType);
		accrualDateNullCriteria.addIsNull(FieldNames.ACCRUAL_PROCESS_DATE);

		leaveCriteria.addOrCriteria(accrualDateNullCriteria);
		
		
		
		//get back a list, as the above query should return 2 rows in the case of both null and non-null dates
		//unless we can be tricky in the sql and use the oracle nvl function...
		List accruals = (List)getCollectionByQuery(QueryFactory.newQuery(LeaveAccrual.class, leaveCriteria));

		//pick the nonnull in the case of 2 coming back
		//or can we sort in the query and always take the first?
		//yes, we can, but we'll need to determine where null falls (first/last)
		//to know sort direction...)
		if (accruals.size()>0) {
			result = (LeaveAccrual)accruals.get(0);
			if ((result.getAccrualProcessDate() == null) && (accruals.size()>1)) {
				result = (LeaveAccrual)accruals.get(1);
			}
		} 
		
		return result;
	}
}
