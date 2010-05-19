package edu.iu.uis.hr.tk.directory.client;

import edu.iu.uis.hr.client.AbstractLookupActionForm;
import edu.iu.uis.hr.client.InquiryActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.directory.entity.RoleAudit;
import edu.iu.uis.hr.tk.directory.entity.RoleAuditSearchCriteria;
import edu.iu.uis.hr.tk.directory.service.RolesService;

public class RoleAuditInquiryForm extends AbstractLookupActionForm implements InquiryActionForm {
    
    private static final String ROLE_AUDIT_ID_SHORT_NAME = "Id";
    private static final String ROLE_EMPLID_SHORT_NAME = "User";
    private static final String MODIFIED_BY_EMPLID_SHORT_NAME = "Changed By";
    private static final String DEPTID_SHORT_NAME = "Department";
    private static final String TIMESTAMP_SHORT_NAME = "Time Stamp";
    private static final String ROLE_SHORT_NAME = "Role";
    private static final String OPERATION_SHORT_NAME = "Operation";
    private static final String LOCATION_SHORT_NAME = "Location";

    protected SearchCriteria getNewSearchCriteria() {
        return new RoleAuditSearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new RoleAudit();
    }

    public void search() {
        setResults(((RolesService) getService(RolesService.class)).lookupRoleChanges((RoleAuditSearchCriteria)getSearchCriteria()));

    }

    public void prepare() {
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.ROLE_AUDIT_ID, ROLE_AUDIT_ID_SHORT_NAME);
        //getLabels().put(edu.iu.uis.hr.entity.FieldNames.ro, ROLE_EMPLID_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.USER_UNIVERSITY_ID, MODIFIED_BY_EMPLID_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.DEPARTMENT, DEPTID_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.TIMESTAMP, TIMESTAMP_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.ROLE, ROLE_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.OPERATION, OPERATION_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.LOCATION, LOCATION_SHORT_NAME);
    }

}
