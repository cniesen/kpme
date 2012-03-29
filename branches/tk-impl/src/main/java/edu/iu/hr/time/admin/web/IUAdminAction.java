package edu.iu.hr.time.admin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.hr.time.admin.web.AdminAction;
import org.kuali.hr.time.admin.web.AdminActionForm;
import org.kuali.hr.time.user.service.UserServiceImpl;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUser;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.rice.kew.web.UserLoginFilter;
import org.kuali.rice.kew.web.session.UserSession;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.kns.exception.AuthorizationException;

public class IUAdminAction extends AdminAction {

	private static final Logger LOG = Logger.getLogger(AdminAction.class);
	
    @Override
    protected void checkTKAuthorization(ActionForm form, String methodToCall) throws AuthorizationException {
        TKUser user = TKContext.getUser();
        AdminActionForm adminForm = (AdminActionForm) form;

        if (StringUtils.equals(methodToCall, "targetEmployee") || StringUtils.equals(methodToCall, "changeEmployee") || StringUtils.equals(methodToCall, "clearBackdoor") || StringUtils.equals(methodToCall, "clearChangeUser")) {
            // Handle security validation in targetEmployee action, we may need
            // to check the document for validity, since the user may not
            // necessarily be a system administrator.
        } else {
          	Person changePerson = KIMServiceLocator.getPersonService().getPersonByPrincipalName(adminForm.getChangeTargetPrincipalName());
            if (user == null ||
            		(!user.isSystemAdmin()
            			&& !user.isLocationAdmin()
            			&& !user.isDepartmentAdmin()
            			&& !user.isGlobalViewOnly()
            			&& !user.isDepartmentViewOnly()
            			&& !user.getCurrentRoles().isApproverForPerson(changePerson.getPrincipalId())
            			&& !user.getCurrentRoles().isDocumentReadable(adminForm.getDocumentId())
            			&& !user.getCurrentRoles().isPayrollProcessorForPerson(changePerson.getPrincipalId())
            		))  {
                throw new AuthorizationException("", "AdminAction", "");
            }
        }
    }

    @Override
    public ActionForward changeEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminActionForm adminForm = (AdminActionForm) form;
        TKUser tkUser = TKContext.getUser();

        if (StringUtils.isNotBlank(adminForm.getChangeTargetPrincipalName())) {
        	Person changePerson = KIMServiceLocator.getPersonService().getPersonByPrincipalName(adminForm.getChangeTargetPrincipalName());
 
	        if (changePerson != null && tkUser != null) {
	            if (tkUser.getCurrentRoles().isSystemAdmin()
	                	|| tkUser.getCurrentRoles().isGlobalViewOnly()
	                	|| tkUser.getCurrentRoles().isDepartmentAdminForPerson(changePerson.getPrincipalId())
	                	|| tkUser.getCurrentRoles().isDeptViewOnlyForPerson(changePerson.getPrincipalId())
	                	|| tkUser.getCurrentRoles().isLocationAdminForPerson(changePerson.getPrincipalId())
	                	|| tkUser.getCurrentRoles().isTimesheetReviewerForPerson(changePerson.getPrincipalId())
	                	|| tkUser.getCurrentRoles().isPayrollProcessorForPerson(changePerson.getPrincipalId())
	                	|| tkUser.getCurrentRoles().isApproverForPerson(changePerson.getPrincipalId())) {
		                	
		            UserSession userSession = UserLoginFilter.getUserSession(request);
		            userSession.getObjectMap().put(TkConstants.TK_TARGET_USER_PERSON, changePerson);
	
		            if (StringUtils.isNotEmpty(adminForm.getReturnUrl())) {
		                userSession.getObjectMap().put(TkConstants.TK_TARGET_USER_RETURN, adminForm.getReturnUrl());
		            }
		
		            tkUser.setTargetPerson(changePerson);
		            UserServiceImpl.loadRoles(tkUser);
		            TKContext.setUser(tkUser);
		
		            LOG.debug("\n\n" + TKContext.getUser().getActualPerson().getPrincipalName() + " change employee as : " + changePerson.getPrincipalName() + "\n\n");
	            }else {
	                LOG.warn("Non-Admin user attempting to backdoor.");
	                return mapping.findForward("unauthorized");
	            }
	        }
        }
        
    	return mapping.findForward("basic");
    }
}