package edu.iu.uis.hr.tk.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.iu.uis.hr.client.AbstractStrutsAction;
import edu.iu.uis.hr.client.StrutsAction;

public class SupervisorNotificationAction extends AbstractStrutsAction implements StrutsAction {

	private static final String notifyStatus = "/WEB-INF/jsp/NotifySupervisorStatus.jsp";
	public final ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimeApproverNotificationForm)form).submit();
		return new ActionForward(notifyStatus, false);
    }
}
