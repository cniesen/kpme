package edu.iu.uis.hr.tk.directory.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.WorkArea;

public class RoleUserModeAuthorization extends AbstractAuthorization implements ModeAuthorization {
	
    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof RoleUser)) {
            throw new IllegalArgumentException("RoleUserModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable RoleUser object.");
        }
        RoleUser roleUser = (RoleUser) modeApplicable;
        if ((roleUser.getParent() instanceof Department && !edu.iu.uis.hr.entity.logic.Utility.hasValue(((Department) (roleUser.getParent())).getDepartment())) || (roleUser.getParent() instanceof WorkArea && !edu.iu.uis.hr.entity.logic.Utility.hasValue(((WorkArea) (roleUser.getParent())).getDepartment()))) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            List departmentCodes = new ArrayList();
            if (roleUser.getParent() instanceof Department) {
                departmentCodes.add(((Department) roleUser.getParent()).getDepartment());
            } else {
                departmentCodes.add(((WorkArea) roleUser.getParent()).getDepartment());
            }
            if (getUser().hasJurisdictionOverDepartments(getPositionService().getDepartments(departmentCodes, new Date()))) {
                if (getUser().hasLocationRole()) {
                    modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
                } else if (getUser().hasDepartmentRole()) {
                    if (roleUser.getParent() instanceof Department) {
                        if (roleUser instanceof DepartmentRoleUser) {
                            roleUser.getMode().setEditable(false);
                        } else {
                            roleUser.getMode().setEditable(true);
                        }
                    }
                }
            } else {
                modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
            }
        }

    }

    private PositionService getPositionService() {
        return (PositionService) Context.getService(PositionService.class);
    }

}
