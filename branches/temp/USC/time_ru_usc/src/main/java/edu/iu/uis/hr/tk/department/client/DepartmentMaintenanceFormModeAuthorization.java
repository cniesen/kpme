package edu.iu.uis.hr.tk.department.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;

public class DepartmentMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof DepartmentMaintenanceForm)) {
            throw new IllegalArgumentException("DepartmentMaintenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable DepartmentMaintenanceActionForm object.");
        }
        List departmentCodes = new ArrayList();
        departmentCodes.add(((DepartmentMaintenanceForm) modeApplicable).getDepartment().getDepartment());
        if (getUser().hasLocationRole()) {
            if (getUser().hasJurisdictionOverDepartments(getPositionService().getDepartments(departmentCodes, new Date()))) {
                modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
            } else {
                modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
            }
        } else if (getUser().hasDepartmentRole()) {
            if (getUser().hasJurisdictionOverDepartments(getPositionService().getDepartments(departmentCodes, new Date()))) {
                modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
            } else {
                modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
            }
        } else {
            modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
        }
    }
    private PositionService getPositionService() {
        return (PositionService) Context.getService(PositionService.class);
    }

}
