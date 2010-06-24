package edu.iu.uis.hr.tk.authorization;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.rice.kew.web.session.UserSession;

import edu.iu.hrms.hredoc.infrastructure.HredocServiceLocator;
import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.client.StrutsActionForm;
import edu.iu.uis.hr.directory.UserNotFoundException;

public class TimeAuthorizationServiceImpl implements TimeAuthorizationService {
	
	private String workgroupName;

	public boolean isAdministrator(HttpServletRequest request) {
		UserSession userSession = UserSession.getAuthenticatedUser();
		if (userSession == null) {
            throw new RuntimeException("Could not determine authenticated user.  UserSession was null.");
		}
		if (userSession.getPrincipal() == null) {
            throw new RuntimeException("Could not determine workflow user.  UserSession.getWorkflowUser was null.");
		}
		
		List<String> lstSuperUsers = HredocServiceLocator.getHreUserGroupService().getGroupMembersById(getWorkgroupName());
		//Workgroup superUsers = KEWServiceLocator.getWorkgroupService().getWorkgroup(new GroupNameId(getWorkgroupName()), true); 
		try {
			//WorkflowUser user = KEWServiceLocator.getUserService().getWorkflowUser(new AuthenticationUserId(userSession.getNetworkId()));
			if (lstSuperUsers.contains(userSession.getPrincipalId())) {
				return true;
			}
		} catch (UserNotFoundException eunfe) {
			return false;
		}
		return false;
	}

	public String getWorkgroupName() {
		return workgroupName;
	}

	public void setWorkgroupName(String workgroupName) {
		this.workgroupName = workgroupName;
	}

	public void executeAccessAuthorization(StrutsActionForm strutsActionForm) {
		// TODO Auto-generated method stub
		
	}

	public void executeModeAuthorization(StrutsActionForm strutsActionForm) {
		// TODO Auto-generated method stub
		
	}

	public List prepareFilters(AbstractStrutsActionForm strutsActionForm) {
		// TODO Auto-generated method stub
		return null;
	}

	public List prepareFilters(AbstractStrutsActionForm strutsActionForm,
			Iterator filterClasses) {
		// TODO Auto-generated method stub
		return null;
	}

}
