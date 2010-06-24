package edu.iu.uis.hr.tk.directory.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;

public class WorkAreaRoleModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof WorkAreaRole)) {
            throw new IllegalArgumentException("WorkAreaRoleModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable WorkAreaRole object.");
        }
        boolean hasJurisdicationOverWorkArea = false;
        if (getUser().hasLocationRole()) {
            hasJurisdicationOverWorkArea = true;
        } else if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(((WorkAreaRole) modeApplicable).getWorkArea())) {
            hasJurisdicationOverWorkArea = true;
        } else {
            ArrayList payrollManagerDepartments = (ArrayList) getUser().getPayrollManagerDepartments();
            for (Iterator iter = payrollManagerDepartments.iterator(); iter.hasNext();) {
                String departmentId = (String) iter.next();
                WorkArea workArea = getAssignmentService().getWorkArea(((WorkAreaRole) modeApplicable).getWorkArea(), new Date());
                if (edu.iu.uis.hr.entity.logic.Utility.hasValue(workArea) && workArea.getDepartment().equals(departmentId)) {
                    hasJurisdicationOverWorkArea = true;
                    break;
                }
            }
        }
        if (hasJurisdicationOverWorkArea) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
        }
        if(((Entity)modeApplicable).isErroneous()){
        	modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        }           
    }

	public PositionService getPositionService() {
		return (PositionService) Context.getService(PositionService.class);
	}

    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
}

}
