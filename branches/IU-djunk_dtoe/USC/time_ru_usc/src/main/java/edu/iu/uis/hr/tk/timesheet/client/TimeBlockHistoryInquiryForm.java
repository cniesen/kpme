package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.client.AbstractLookupActionForm;
import edu.iu.uis.hr.client.InquiryActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockHistory;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockHistorySearchCriteria;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class TimeBlockHistoryInquiryForm extends AbstractLookupActionForm implements InquiryActionForm {

    private final String DOCUMENTID_SHORT_NAME = "DOC ID";
    private final String EMPLOYEE_RECORD_SHORT_NAME = "ER";
    private final String CREATED_BY_CLOCK_SHORT_NAME = "Clock";
    private final String OVERTIME_HOURS_SHORT_NAME = "OT Hrs";
    private final String OVERTIME_EARN_CODE_SHORT_NAME = "OT EC";
    private final String SHIFT_EARN_CODE_SHORT_NAME = "Shift EC";
    private final String SHIFT_HOURS_SHORT_NAME = "Shift Hrs";    
    private final String PREMIUM_HOURS_SHORT_NAME = "Prem. Hrs";
    private final String PREMIUM_EARN_CODE_SHORT_NAME = "Prem. EC";
    

    protected SearchCriteria getNewSearchCriteria() {
        return new TimeBlockHistorySearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new TimeBlockHistory();
    }

    public void search() {
        setResults((((TimesheetService) getService(TimesheetService.class)).lookupTimeBlockHistory((TimeBlockHistorySearchCriteria) getSearchCriteria())));        
    }

    public void prepare() {
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.DOCUMENT_ID, DOCUMENTID_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.EMPLOYEE_RECORD, EMPLOYEE_RECORD_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.OVERTIME_HOURS, OVERTIME_HOURS_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.OVERTIME_EARN_CODE, OVERTIME_EARN_CODE_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.SHIFT_HOURS, SHIFT_HOURS_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.SHIFT_EARN_CODE, SHIFT_EARN_CODE_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.PREMIUM_HOURS, PREMIUM_HOURS_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.PREMIUM_EARN_CODE, PREMIUM_EARN_CODE_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.CREATED_BY_CLOCK, CREATED_BY_CLOCK_SHORT_NAME);
    }
    

}