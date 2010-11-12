package org.kuali.hr.time.webservices.xml.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.paytype.PayType;
import org.kuali.hr.time.salgroup.SalGroup;

public class JobRule {
	public boolean validateDept(Job job) {
		boolean valid = false;		
		// TODO: We may need a full DAO that handles bo lookups at some point,
		// but we can use the provided one:
		Criteria crit = new Criteria();
		crit.addEqualTo("dept", job.getDept());		
		Query query = QueryFactory.newQuery(Department.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker().getCount(query);		
		if (count >0 ) {
			valid = true;			
		}
		return valid;
	}
	
	public boolean validatePayType(Job job) {
		boolean valid = false;		
		Criteria crit = new Criteria();
		crit.addEqualTo("payType", job.getHrPayType());		
		Query query = QueryFactory.newQuery(PayType.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker().getCount(query);		
		if (count >0 ) {
			valid = true;			
		}
		return valid;
	}
	
	public boolean validateSalGroup(Job job) {
		boolean valid = false;		
		Criteria crit = new Criteria();
		crit.addEqualTo("tkSalGroup", job.getTkSalGroup());		
		Query query = QueryFactory.newQuery(SalGroup.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker().getCount(query);		
		if (count >0 ) {
			valid = true;			
		}
		return valid;
	}
	public boolean validateJobObject(Job job) throws IllegalArgumentException{
		boolean result  = false;
		if (job != null) {		
			if(! this.validateDept(job)){				
				throw new IllegalArgumentException("Invalid Task :"+job.getDept());
			}
			if(! this.validatePayType(job)){				
				throw new IllegalArgumentException("Invalid PayType :"+job.getPayType());
			}
			if(! this.validateSalGroup(job)){				
				throw new IllegalArgumentException("Invalid SalGroup :"+job.getTkSalGroup());
			}
			result = true;
		}
		return result;
	}
}
