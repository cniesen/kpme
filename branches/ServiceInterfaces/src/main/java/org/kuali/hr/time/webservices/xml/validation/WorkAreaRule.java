package org.kuali.hr.time.webservices.xml.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.workarea.WorkArea;

public class WorkAreaRule {
	public boolean validateDept(WorkArea workArea) {
		boolean valid = false;		
		// TODO: We may need a full DAO that handles bo lookups at some point,
		// but we can use the provided one:
		Criteria crit = new Criteria();
		crit.addEqualTo("dept", workArea.getDept());		
		Query query = QueryFactory.newQuery(Department.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker().getCount(query);		
		if (count >0 ) {
			valid = true;			
		}
		return valid;
	}

	public boolean validateWorkAreaObject(WorkArea workArea)
			throws IllegalArgumentException {
		boolean result = false;
		if (workArea != null) {
			if (!this.validateDept(workArea)) {
				throw new IllegalArgumentException("Invalid dept:"
						+ workArea.getDept());
			}
			result = true;
		}
		return result;
	}
}
