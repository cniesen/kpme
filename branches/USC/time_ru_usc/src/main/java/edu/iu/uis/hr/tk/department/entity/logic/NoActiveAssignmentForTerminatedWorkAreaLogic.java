package edu.iu.uis.hr.tk.department.entity.logic;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.department.entity.WorkArea;
import edu.iu.uis.hr.tk.job.service.JobService;

public class NoActiveAssignmentForTerminatedWorkAreaLogic extends AbstractLogic implements OperationalLogic {
//    private JobService jobService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof WorkArea)) {
            throw new IllegalArgumentException("NoActiveAssignmentForTerminatedWorkAreaLogic's execute(Entity entity) method requires a non-null Entity of type WorkArea");
        }
        if (!((WorkArea) entity).isActive()) {
            if (edu.iu.uis.hr.entity.logic.Utility.hasValue(getJobService().getJobs(((WorkArea) entity).getEffectiveDate(), ((WorkArea) entity).getWorkArea()))) {
                entity.getEntityErrors().add(new String[] { FieldNames.WORK_AREA }, getMessage(MessageKeyConstants.ERROR_WORK_AREA_HAS_ACTIVE_ASSIGNMENT));
            }
        }
    }

    public JobService getJobService() {
        return (JobService) Context.getService(JobService.class);
    }

//    public void setJobService(JobService jobService) {
//        if (jobService != null) {
//            this.jobService = jobService;
//        }
//    }

 }
