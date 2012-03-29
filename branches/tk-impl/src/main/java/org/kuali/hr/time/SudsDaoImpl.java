package org.kuali.hr.time;

import org.springmodules.orm.ojb.PersistenceBrokerTemplate;
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;

public class SudsDaoImpl extends PersistenceBrokerDaoSupport{
	
	public PersistenceBrokerTemplate getPersistenceTemplate(){
		return this.getPersistenceBrokerTemplate();
	}

}
