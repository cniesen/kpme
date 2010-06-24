package edu.iu.uis.hr.tk.report.dataaccess;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.report.entity.BatchMessage;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;

public interface BatchMessageDataAccess extends DataAccessOjb {

	public BatchMessage getBatchMessage(String messageId);
	
	public TypedPersistentMaintainedEntityList lookupBatchMessage(BatchMessageSearchCriteria searchCriteria);

}
