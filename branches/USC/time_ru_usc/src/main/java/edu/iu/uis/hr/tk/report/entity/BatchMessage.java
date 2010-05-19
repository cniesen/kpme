package edu.iu.uis.hr.tk.report.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.tk.TKServiceLocator;

public class BatchMessage extends AbstractBatchMessage implements PersistentMaintainedEntity {

	private static final Logger LOG = Logger.getLogger(BatchMessage.class);

	public BatchMessage() {
		super();
	}
	
	public BatchMessage(BigDecimal messageId, String processName, Date processDate, String documentId, String universityId, String message) {
		setMessageId(messageId);
		setProcessName(processName);
		setProcessDate(processDate);
		setDocumentId(documentId);
		TKServiceLocator.getBatchMessageService().formatDocHandler(super.getDocumentId());
		setUniversityId(universityId);
		if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(message)) {
			message=edu.iu.uis.hr.Utility.getDefaultStringValue();
		}
		if (message.length()>255) {
			message = message.substring(0,255);
		}
		setMessage(message);
	}

	@Override
	public String getLookupFieldsToReturn(String fieldName) {
		// TODO Auto-generated method stub
		return super.getLookupFieldsToReturn(fieldName);
	}
}
