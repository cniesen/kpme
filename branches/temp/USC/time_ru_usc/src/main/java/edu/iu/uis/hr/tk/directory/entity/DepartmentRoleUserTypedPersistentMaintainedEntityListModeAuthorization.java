package edu.iu.uis.hr.tk.directory.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;
import edu.iu.uis.hr.tk.department.entity.Department;

public class DepartmentRoleUserTypedPersistentMaintainedEntityListModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!(modeApplicable instanceof TypedPersistentMaintainedEntityList)) {
            throw new IllegalArgumentException("DepartmentRoleUserTypedPersistentMaintainedEntityListModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable TypedPersistentMaintainedEntityList object.");
        }
        List departmentCodes = new ArrayList();
        //boolean isWorkAreaRoleUser = (((TypedPersistentMaintainedEntityList) modeApplicable).getParent() instanceof WorkArea);
        departmentCodes.add(((Department) ((TypedPersistentMaintainedEntityList) modeApplicable).getParent()).getDepartment());
        if (getUser().hasLocationRole()) {
            if (edu.iu.uis.hr.entity.logic.Utility.hasValue(departmentCodes) && getUser().hasJurisdictionOverDepartments(getPositionService().getDepartments(departmentCodes, new Date()))) {
                modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
            } else {
                modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
            }
        } else {
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
        }
    }

    private PositionService getPositionService() {
        return (PositionService) Context.getService(PositionService.class);
    }

}
