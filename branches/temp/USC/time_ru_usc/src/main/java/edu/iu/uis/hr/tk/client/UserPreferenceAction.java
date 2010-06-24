package edu.iu.uis.hr.tk.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.iu.uis.hr.client.AbstractStrutsAction;
import edu.iu.uis.hr.client.StrutsAction;
import edu.iu.uis.hr.client.StrutsActionForm;

public class UserPreferenceAction extends AbstractStrutsAction implements StrutsAction {

    public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((UserPreferenceForm)form).save();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }

}
