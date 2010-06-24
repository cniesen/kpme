package edu.iu.uis.hr.tk.directory.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class UserRolesModeAuthorization extends AbstractAuthorization implements ModeAuthorization {
//	Controls interface manager setting editability on role maintenance page
    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof UserRoles)) {
            throw new IllegalArgumentException("UserRolesModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable UserRoles object.");
        }
        if (getUser().hasSystemRole()) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        } else {
            modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));            
        }
     }
    
}
