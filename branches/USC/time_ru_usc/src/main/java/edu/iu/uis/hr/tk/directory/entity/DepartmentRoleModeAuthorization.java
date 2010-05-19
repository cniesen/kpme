package edu.iu.uis.hr.tk.directory.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class DepartmentRoleModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof DepartmentRole)) {
            throw new IllegalArgumentException("DepartmentRoleModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable DepartmentRole object.");
        }
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(((DepartmentRole) modeApplicable).getDepartment())) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            List departmentCodes = new ArrayList();
            departmentCodes.add(((DepartmentRole) modeApplicable).getDepartment());
            if (getUser().hasJurisdictionOverDepartments(getPositionService().getDepartments(departmentCodes, new Date()))) {
                modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));

            } else {
                modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
            }
        }

    }
	public PositionService getPositionService() {
		return (PositionService) Context.getService(PositionService.class);
	}
}
