package edu.iu.uis.hr.tk.rule.entity.logic;

import java.util.Date;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.job.entity.Job;
import edu.iu.uis.hr.job.service.JobService;
import edu.iu.uis.hr.tk.rule.entity.JobRule;

public class EmployeeHasEmployeeRecordLogic extends AbstractLogic implements OperationalLogic {

//    private JobService jobService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof JobRule)) {
            throw new IllegalArgumentException("EmployeeHasEmployeeRecordLogic's execute(Entity entity) method requires a non-null Entity of type JobRule");
        }
        if ( !edu.iu.uis.hr.entity.logic.Utility.isAllValue(((JobRule) entity).getEmployeeRecord()) ) {
            Job  job =  getJobService().getJob(((JobRule) entity).getUniversityId(), ((JobRule) entity).getEmployeeRecord(), new Date());
            if ( !edu.iu.uis.hr.entity.logic.Utility.hasValue(job) || !edu.iu.uis.hr.entity.logic.Utility.hasValue(job.getEmployeeRecord()) || !job.getEmployeeRecord().equals(((JobRule) entity).getEmployeeRecord())) {
               entity.getEntityErrors().add(new String[] { FieldNames.EMPLOYEE_RECORD }, getMessage(MessageKeyConstants.ERROR_EMPLOYEE_HAS_NO_EMPLOYEE_RECORD));
            }
        }
    }

	public JobService getJobService() {
		return (JobService) Context.getService(JobService.class);
	}
}
