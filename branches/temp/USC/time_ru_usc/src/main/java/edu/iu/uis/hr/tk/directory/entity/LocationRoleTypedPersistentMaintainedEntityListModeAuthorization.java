package edu.iu.uis.hr.tk.directory.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class LocationRoleTypedPersistentMaintainedEntityListModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!(modeApplicable instanceof TypedPersistentMaintainedEntityList)) {
            throw new IllegalArgumentException("LocationRoleTypedPersistentMaintainedEntityListModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable TypedPersistentMaintainedEntityList object.");
        }
        if (getUser().hasSystemRole()) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));            
        }
    }


}
