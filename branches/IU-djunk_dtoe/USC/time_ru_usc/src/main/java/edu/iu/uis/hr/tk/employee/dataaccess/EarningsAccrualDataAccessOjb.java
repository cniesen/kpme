package edu.iu.uis.hr.tk.employee.dataaccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.employee.entity.EarningsAccrual;

public class EarningsAccrualDataAccessOjb extends AbstractDataAccessOjb implements EarningsAccrualDataAccess {

	public List getEarningsAccrualPlans(String erncd, Date accrualProcessDate) {

		List result = new ArrayList();

		Criteria accrualCriteria = new Criteria();
		accrualCriteria.addEqualTo(FieldNames.EARN_CODE, erncd);
		accrualCriteria.addEqualTo(FieldNames.ADD_HOURS_TAKEN, "Y");

		// add in effective dating
		Criteria accrualDateSubCriteria = new Criteria();
		accrualDateSubCriteria.addEqualToField(FieldNames.EARN_CODE, Criteria.PARENT_QUERY_PREFIX + FieldNames.EARN_CODE);
		ReportQueryByCriteria accrualDateSubQuery = QueryFactory.newReportQuery(EarningsAccrual.class, accrualDateSubCriteria);
		accrualDateSubCriteria.addLessOrEqualThan(FieldNames.EFFECTIVE_DATE, accrualProcessDate);
		accrualDateSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.EFFECTIVE_DATE).append(")").toString() });
		accrualCriteria.addEqualTo(FieldNames.EFFECTIVE_DATE, accrualDateSubQuery);

		List accruals = (List) getCollectionByQuery(QueryFactory.newQuery(EarningsAccrual.class, accrualCriteria));
		for (Iterator iter = accruals.iterator(); iter.hasNext();) {
			EarningsAccrual earningsAccrual = (EarningsAccrual) iter.next();
			result.add(earningsAccrual.getPlanType());
		}

		return result;
	}
}
