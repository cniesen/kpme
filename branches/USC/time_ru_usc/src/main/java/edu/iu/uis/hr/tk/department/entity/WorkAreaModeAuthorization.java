package edu.iu.uis.hr.tk.department.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.Task;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;
import edu.iu.uis.hr.tk.job.service.JobService;

public class WorkAreaModeAuthorization extends AbstractAuthorization implements ModeAuthorization {
	
    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof WorkArea)) {
            throw new IllegalArgumentException("WorkAreaModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable WorkArea object.");
        }
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(((WorkArea) modeApplicable).getDepartment())) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            List departmentCodes = new ArrayList();
            departmentCodes.add(((WorkArea) modeApplicable).getDepartment());
            if (getUser().hasJurisdictionOverDepartments(getPositionService().getDepartments(departmentCodes, new Date()))) {
                modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
            } else {
                modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
            }
            if(((WorkArea) modeApplicable).isErroneous()){
            	modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
            }	
        }

        List jobList = new ArrayList();
        if (((WorkArea) modeApplicable).isActive()) {
            jobList = getJobService().getJobs(((WorkArea) modeApplicable).getEffectiveDate(), ((WorkArea) modeApplicable).getWorkArea());
        }
        
        //checking if tasks have active assignments
        Iterator tasksIt = ((WorkArea) modeApplicable).getTasks().iterator();
        while (tasksIt.hasNext()) {
            Task task = (Task)tasksIt.next();
            
            if ( Utility.hasValue(getJobService().getJobs(((WorkArea) modeApplicable).getEffectiveDate(), ((WorkArea) modeApplicable).getWorkArea(), task.getTask()))) {
                task.getMode().setEditable(false);
            }  else {
                task.getMode().setEditable(true); 
            }
        }
        
    }
    private PositionService getPositionService() {
        return (PositionService) Context.getService(PositionService.class);
    }
	public JobService getJobService() {
		return (JobService) Context.getService(JobService.class);
	}
    

}
