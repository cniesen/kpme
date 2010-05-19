package edu.iu.uis.hr.tk.directory.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;
import edu.iu.uis.hr.tk.department.entity.WorkArea;

public class WorkAreaRoleUserTypedPersistentMaintainedEntityListModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!(modeApplicable instanceof TypedPersistentMaintainedEntityList)) {
            throw new IllegalArgumentException("DepartmentRoleUserTypedPersistentMaintainedEntityListModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable TypedPersistentMaintainedEntityList object.");
        }
        List departmentCodes = new ArrayList();
        WorkArea workArea = (WorkArea) ((TypedPersistentMaintainedEntityList) modeApplicable).getParent();
        if (Utility.hasValue(workArea.getDepartment())) {
            departmentCodes.add(workArea.getDepartment());
        }
        if (getUser().hasDepartmentRole()) {
            if (departmentCodes.size() > 0 && getUser().hasJurisdictionOverDepartments(getPositionService().getDepartments(departmentCodes, new Date()))) {
                modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
            } else {
                modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
            }
        }
    }
    private PositionService getPositionService() {
        return (PositionService) Context.getService(PositionService.class);
    }
    

}
