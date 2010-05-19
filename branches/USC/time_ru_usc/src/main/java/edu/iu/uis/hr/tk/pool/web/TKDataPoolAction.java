package edu.iu.uis.hr.tk.pool.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.kuali.rice.core.database.XAPoolDataSource;

import edu.iu.uis.hr.tk.TKServiceLocator;

public class TKDataPoolAction extends DispatchAction {
	
	public ActionForward start(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TKDataPoolForm tkForm = (TKDataPoolForm)form;
		XAPoolDataSource ds = (XAPoolDataSource)TKServiceLocator.getDataSource("tkDataSource");
		tkForm.setMinConnections(ds.getMinSize());
		tkForm.setMaxConnections(ds.getMaxSize());
		tkForm.setFreeConnections(ds.pool.getUnlockedObjectCount());
		tkForm.setUsedConnections(ds.pool.getLockedObjectCount());
		tkForm.setPoolSize(ds.pool.getCount());
		
		return mapping.findForward("default");
	}

}
