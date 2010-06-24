package edu.iu.uis.hr.tk.department.entity;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class DepartmentModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof Department)) {
            throw new IllegalArgumentException("DepartmentModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable Department object.");
        }
        modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
    }

    private PositionService getPositionService() {
        return (PositionService) Context.getService(PositionService.class);
    }
}
