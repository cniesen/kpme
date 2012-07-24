package org.kuali.hr.time.base.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUser;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.util.GlobalVariables;

public class TkAction extends KualiAction {

    private static final Logger LOG = Logger.getLogger(TkAction.class);


    protected void checkTKAuthorization(ActionForm form, String methodToCall) throws AuthorizationException {
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String methodToCall = null;
            if (form instanceof TkForm) {
                methodToCall = ((TkForm)form).getMethodToCall();
            }
            checkTKAuthorization(form, methodToCall);
        } catch (AuthorizationException e) {
            LOG.error("User: " + TKContext.getPrincipalId() + " Target: " + TKContext.getTargetPrincipalId(), e);
            return mapping.findForward("unauthorized");
        }

        // Run our logic / security first - For some reason kuali
        // dispatches actions BEFORE checking the security...

        return super.execute(mapping, form, request, response);
    }

    /**
	 * Action to clear the current users back door setting.  Clears both
	 * workflow and TK backdoor settings.
	 */
	public ActionForward clearBackdoor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GlobalVariables.getUserSession().clearBackdoorUser();

		return mapping.findForward("basic");
	}

	public ActionForward clearChangeUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserSession userSession = GlobalVariables.getUserSession();

        String returnAction = (String)userSession.getObjectMap().get(TkConstants.TK_TARGET_USER_RETURN);
        if (returnAction == null) returnAction = "/PersonInfo.do";

        TKContext.getUser().clearTargetUser();

        return new ActionRedirect(returnAction);
	}

	public ActionForward userLogout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TKContext.clear();
		request.getSession().invalidate();
		return new ActionRedirect(mapping.findForward("basic"));
	}

}
