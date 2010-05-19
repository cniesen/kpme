package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.client.AccessAuthorization;
import edu.iu.uis.hr.client.AccessAuthorizationApplicable;
import edu.iu.uis.hr.tk.AbstractAuthorization;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;

public class EmployeeAccessAuthorization extends AbstractAuthorization implements AccessAuthorization {

    public boolean execute(AccessAuthorizationApplicable accessAuthorizationApplicable) {
        if (getAssignmentService().hasAssignment(getUser().getUniversityId())) {
            return true;            
        }
        return false;
    }
    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }
}
