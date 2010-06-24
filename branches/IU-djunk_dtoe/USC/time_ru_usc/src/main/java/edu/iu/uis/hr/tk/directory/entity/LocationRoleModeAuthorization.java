package edu.iu.uis.hr.tk.directory.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.position.entity.Location;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class LocationRoleModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof LocationRole)) {
            throw new IllegalArgumentException("LocationRoleModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable LocationRole object.");
        }
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(((LocationRole) modeApplicable).getLocation()) || getUser().hasJurisdictionOver(new Location(((LocationRole) modeApplicable).getLocation()))) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
        }
    }

}
