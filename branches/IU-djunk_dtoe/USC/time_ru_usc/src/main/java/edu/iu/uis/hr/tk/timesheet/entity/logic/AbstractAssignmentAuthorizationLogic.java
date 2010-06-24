package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.job.service.JobService;

public abstract class AbstractAssignmentAuthorizationLogic extends AbstractLogic implements OperationalLogic{

//    private WebUserService webUserService;
//    private AssignmentService assignmentService;
//    private JobService jobService;

    public boolean isMatch() {
        return false;
    }

    public void execute(Entity entity) {
    }

    public WebUserService getWebUserService() {
        return (WebUserService) Context.getService(WebUserService.class);
    }

    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }

    public JobService getJobService() {
		return (JobService) Context.getService(JobService.class);
	}
}
