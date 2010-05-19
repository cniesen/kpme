package edu.iu.uis.hr.tk.authorization;

import javax.servlet.http.HttpServletRequest;

import edu.iu.uis.hr.service.AuthorizationService;

public interface TimeAuthorizationService extends AuthorizationService {

	public boolean isAdministrator(HttpServletRequest request);

}
