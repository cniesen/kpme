package edu.iu.uis.hr.tk.rule.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class DepartmentRuleModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof DepartmentRule)) {
            throw new IllegalArgumentException("DepartmentRuleModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable DepartmentRule object.");
        }
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(((DepartmentRule)modeApplicable).getDepartment()) && getUser().hasRole(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_MANAGER_ROLE)) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            List departmentCodes = new ArrayList();
            departmentCodes.add(((DepartmentRule)modeApplicable).getDepartment());
            if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(getPositionService().getDepartments(departmentCodes, new Date())) || getUser().hasJurisdictionOverDepartments(getPositionService().getDepartments(departmentCodes, new Date()))) {
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
