package edu.iu.uis.hr.tk.report.client;

import edu.iu.uis.hr.client.AbstractLookupActionForm;
import edu.iu.uis.hr.client.InquiryActionForm;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.report.entity.BatchMessage;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;
import edu.iu.uis.hr.tk.report.service.BatchMessageService;

public class BatchMessageInquiryForm extends AbstractLookupActionForm implements InquiryActionForm {
	
	protected SearchCriteria getNewSearchCriteria() {
		return new BatchMessageSearchCriteria();
	}

	public PersistentDatabaseEntity getResultTemplate() {
		return new BatchMessage();
	}

	public void search() {
		setResults((((BatchMessageService) getService(BatchMessageService.class)).lookupBatchMessage((BatchMessageSearchCriteria) getSearchCriteria())));
	}

	public void prepare() {
		setFieldOptions(FieldNames.PROCESS_NAME, ((BatchMessageService) getService(BatchMessageService.class)).getDropdownProcessNames());
	}

}
