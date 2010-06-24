package edu.iu.uis.hr.tk;

import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.directory.service.WebUserService;

public abstract class AbstractAuthorization extends edu.iu.uis.hr.AbstractAuthorization {

    protected User getUser() {
        return (User)((WebUserService)getWebUserService()).getUser();
    }

    protected ModeFactory getModeAuthorizationFactory() {
        return (ModeFactory) super.getModeFactory();
    }
}
