package edu.iu.uis.hr.tk.report.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.tk.report.dataaccess.BatchMessageDataAccess;
import edu.iu.uis.hr.tk.report.entity.BatchMessage;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.client.TimesheetDocumentForm;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class BatchMessageServiceImpl extends AbstractService implements BatchMessageService {

	private BatchMessageDataAccess batchMessageDataAccess;

	public BatchMessage getBatchMessage(String messageId) {
		// TODO Auto-generated method stub
		return null;
	}

	public BatchMessageDataAccess getBatchMessageDataAccess() {
		return batchMessageDataAccess;
	}

	public void setBatchMessageDataAccess(BatchMessageDataAccess batchMessageDataAccess) {
		this.batchMessageDataAccess = batchMessageDataAccess;
	}

	public TypedPersistentMaintainedEntityList lookupBatchMessage(BatchMessageSearchCriteria searchCriteria) {
		return getBatchMessageDataAccess().lookupBatchMessage(searchCriteria);
	}

	public List getDropdownProcessNames() {
		return getSortedOptions(BatchMessageSearchCriteria.getProcessNames());
	}

	public void addBatchMessage(BatchMessage batchMessage) {
		getBatchMessageDataAccess().store(batchMessage);
	}

	public void removeBatchMessage(BatchMessage batchMessage) {
		getBatchMessageDataAccess().delete(batchMessage);
	}

	public String formatDocHandler(String documentId) {
		if (StringUtils.isNotBlank(documentId)) {
			StringBuffer urlBuffer = new StringBuffer();
			urlBuffer.append("<a target=\"_blank\" href=");
			urlBuffer.append(AbstractStrutsActionForm.getPageBaseUrl(TimesheetDocumentForm.class));
			urlBuffer.append(TimesheetService.DOC_HANDLER_PARAMS);
			urlBuffer.append("&docId=");
			urlBuffer.append(documentId);
			urlBuffer.append(">");
			urlBuffer.append(documentId);
			urlBuffer.append("</>");
			return urlBuffer.toString();
		} else {
			return edu.iu.uis.hr.Utility.getDefaultStringValue();
		}
	}

}
