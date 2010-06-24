package edu.iu.uis.hr.tk.report.service;

import java.util.List;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.service.Service;
import edu.iu.uis.hr.tk.report.entity.BatchMessage;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;

public interface BatchMessageService extends Service {

	public BatchMessage getBatchMessage(String messageId);
	
	public TypedPersistentMaintainedEntityList lookupBatchMessage(BatchMessageSearchCriteria batchMessageSearchCriteria);
	
	public List getDropdownProcessNames();
	
	public void addBatchMessage(BatchMessage batchMessage);
	
	public void removeBatchMessage(BatchMessage batchMessage);
	
	public String formatDocHandler(String documentId);
	
}
