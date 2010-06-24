package edu.iu.uis.hr.tk.report.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;

public class BatchMessageSearchCriteria extends AbstractEffectiveDatedSearchCriteria  {
	
	public static final String DOCUMENT_INITIATION_PROCESS_NAME = "Document Initiation";
	public static final String EXTRACT_TO_PEOPLESOFT_PROCESS_NAME = "Extract to PeopleSoft";
	public static final String EMPLOYEE_APPROVAL_PROCESS_NAME = "Employee Approval";
	public static final String FINALIZE_TIMESHEETS_PROCESS_NAME = "Payroll Extract";
	public static final String SUPERVISOR_APPROVAL_PROCESS_NAME = "Supervisor Approval";
	private static final List processNames = new ArrayList();
	static {
		processNames.add(new Option(DOCUMENT_INITIATION_PROCESS_NAME,DOCUMENT_INITIATION_PROCESS_NAME));
		processNames.add(new Option(EXTRACT_TO_PEOPLESOFT_PROCESS_NAME,EXTRACT_TO_PEOPLESOFT_PROCESS_NAME));
		processNames.add(new Option(EMPLOYEE_APPROVAL_PROCESS_NAME,EMPLOYEE_APPROVAL_PROCESS_NAME));
		processNames.add(new Option(FINALIZE_TIMESHEETS_PROCESS_NAME,FINALIZE_TIMESHEETS_PROCESS_NAME));
		processNames.add(new Option(SUPERVISOR_APPROVAL_PROCESS_NAME,SUPERVISOR_APPROVAL_PROCESS_NAME));
	}
		
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(BatchMessage.class);
    }
    
    private String processName;
    private Date processDate;
    private String documentId;
    private String universityId;
    
	protected Set getPersistentDatabaseEntityClasses() {
		return PERSISTENT_DATABASE_ENTITY_CLASSES;
	}

	public void clear() {
		setProcessName(edu.iu.uis.hr.Utility.getDefaultStringValue());
		setProcessDate(null);
		setDocumentId(edu.iu.uis.hr.Utility.getDefaultStringValue());
		setUniversityId(edu.iu.uis.hr.Utility.getDefaultStringValue());
	}

    public boolean isEffectiveDated(){
        return false;
    }
    
	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String emplid) {
		this.universityId = emplid;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public static List getProcessNames() {
		return processNames;
	}
}
