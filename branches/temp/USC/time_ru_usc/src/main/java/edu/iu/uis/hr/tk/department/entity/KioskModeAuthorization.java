package edu.iu.uis.hr.tk.department.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class KioskModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof Kiosk)) {
            throw new IllegalArgumentException("KioskModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable WorkArea object.");
        }
      //  modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
        modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
    }

}
