package edu.iu.uis.hr.tk.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class LogOutAction extends DispatchAction {
	
	
    public final ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	request.getSession().invalidate();
    	//request.getServletPath();
        return new ActionForward("/kiosk/index.jsp", true); //need to do this 'cause Kiosk page must be out of WEB-INF folder
    }
	
    public final ActionForward sessionExpired(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	request.getSession().invalidate();
    	return mapping.findForward("sessionExpired");
    }    

}