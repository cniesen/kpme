package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.client.AccessAuthorization;
import edu.iu.uis.hr.client.AccessAuthorizationApplicable;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class SupervisoryRoleAccessAuthorization extends AbstractAuthorization implements AccessAuthorization {

    public boolean execute(AccessAuthorizationApplicable accessAuthorizationApplicable) {
        if (getUser().hasWorkAreaRole()) {
            return true;
        }
        return false;
    }

}
