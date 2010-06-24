package edu.iu.uis.hr.tk.web;

import javax.servlet.http.HttpServletRequest;

import org.kuali.rice.kew.user.AuthenticationUserId;
import org.kuali.rice.kew.user.UserId;
import org.kuali.rice.kew.web.session.UserSession;
import org.kuali.rice.kim.service.AuthenticationService;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.directory.service.WebUserService;

public class TKWebAuthenticationService implements AuthenticationService {

	//@Override
	public String getNetworkId(HttpServletRequest request) {
		return ((WebUserService)Context.getService(WebUserService.class)).getNetworkId(request);
	}

	public boolean isValidatePassword() {
		return true;
	}

	public UserSession establishInitialUserSession(UserSession userSession, HttpServletRequest request) {
		return userSession;
	}

	public UserId getUserId(HttpServletRequest request) {
		return new AuthenticationUserId(getNetworkId(request));
	}

	public UserSession updateUserSession(UserSession userSession, HttpServletRequest request) {
		return userSession;
	}

	public String getPrincipalName(HttpServletRequest request) {
		return ((WebUserService)Context.getService(WebUserService.class)).getNetworkId(request);
	}
}