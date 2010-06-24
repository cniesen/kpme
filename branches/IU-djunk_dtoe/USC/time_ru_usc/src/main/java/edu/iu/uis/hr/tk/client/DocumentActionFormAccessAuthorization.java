package edu.iu.uis.hr.tk.client;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.client.AccessAuthorizationApplicable;
import edu.iu.uis.hr.client.DocumentActionForm;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;

public class DocumentActionFormAccessAuthorization extends edu.iu.uis.hr.client.DocumentActionFormAccessAuthorization {

    private static final Logger LOG = Logger.getLogger(DocumentActionFormAccessAuthorization.class);

    private AssignmentService assignmentService;
    
    public boolean execute(AccessAuthorizationApplicable accessAuthorizationApplicable) {
        if (!Utility.hasValue(accessAuthorizationApplicable) || !(accessAuthorizationApplicable instanceof DocumentActionForm)) {
            throw new IllegalArgumentException("DocumentActionFormAccessAuthorization's execute(AccessAuthorizationApplicable accessAuthorizationApplicable) method requires a non-null instance of DocumentActionForm");
        }
        if (StrutsDispatchParamConstants.INITIATE_DISPATCH_PARAM_VALUE.equals(((DocumentActionForm)accessAuthorizationApplicable).getCommand()) && ((User)((WebUserService)getWebUserService()).getUser()).hasAdministrativeRole()) {
            return true;
        }
        if ((!Utility.hasValue(((DocumentActionForm)accessAuthorizationApplicable).getCommand()) || !Utility.hasValue(((DocumentActionForm)accessAuthorizationApplicable).getDocId())) && getAssignmentService().hasAssignment(getWebUserService().getUser().getUniversityId())) {
            return true;
        }
        return super.execute(accessAuthorizationApplicable);
    }

    public AssignmentService getAssignmentService() {
        return assignmentService;
    }

    public void setAssignmentService(AssignmentService assignmentService) {
        if (assignmentService != null) {
            this.assignmentService = assignmentService;
        }
    }
}
