package edu.iu.uis.hr.tk.timesheet.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.iu.uis.hr.client.MaintenanceAction;
import edu.iu.uis.hr.client.StrutsActionForm;

public class DocumentHeaderApproveAction extends MaintenanceAction {
    public static Logger LOG = Logger.getLogger(DocumentHeaderApproveAction.class);
    
        
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((DocumentHeaderMaintenanceForm)form).approve();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }

}