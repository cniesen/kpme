package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;

public class HasValidAssignmentLogic extends AbstractLogic implements OperationalLogic {

	private static final String HAS_NO_VALID_ASSIGNMENT= "The employee has no valid assignments for this pay period. A Timesheet cannot be created.";
//	private JobService jobService;
	
	public void execute(Entity entity) {
        if (!(entity instanceof DocumentHeader)) {
            throw new IllegalArgumentException("HasValidAssignmentLogic's execute(Entity entity) method requires a non-null Entity of type DocumentHeader");
        }
		DocumentHeader documentHeader = (DocumentHeader)entity;	
		if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(getJobService().getJobsWithAssignments(documentHeader.getUniversityId(), documentHeader.getPayEndDate()))) {
			entity.getEntityErrors().add(new String[] {"universityId"},HAS_NO_VALID_ASSIGNMENT);
		}
	}
	
	public boolean isMatch() {
		return true;
	}

	public JobService getJobService() {
		return (JobService) Context.getService(JobService.class);
	}
}
