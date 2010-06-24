package edu.iu.uis.hr.tk.rule.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.position.entity.Location;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class LocationRuleModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

        public void execute(ModeApplicable modeApplicable) {
            if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof LocationRule)) {
                throw new IllegalArgumentException("LocationRuleModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable LocationRule object.");
            }
            if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(((LocationRule)modeApplicable).getLocation()) || getUser().hasJurisdictionOver(new Location(((LocationRule)modeApplicable).getLocation()))) {
                modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
            } else {
                modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));            
            }
        }

}
