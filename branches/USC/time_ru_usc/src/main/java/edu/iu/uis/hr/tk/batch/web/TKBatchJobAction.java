package edu.iu.uis.hr.tk.batch.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.tk.TKServiceLocator;

public class TKBatchJobAction extends DispatchAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TKBatchJobForm frm = (TKBatchJobForm)form;
		Context.setForm(form);

		super.execute(mapping, form, request, response);
		if(request.getParameter("page")!=null && frm.getBatchJobs()!=null){
			//reset with the new page
			frm.setBatchJobs(frm.getBatchJobs());
		}
	
		return mapping.findForward("default");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TKBatchJobForm frm = (TKBatchJobForm)form;
		frm.setBatchJobs(TKServiceLocator.getBatchJobService().getBatchJobs(frm));
		return mapping.findForward("default");
	}
	
	public ActionForward updateStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TKBatchJobForm frm = (TKBatchJobForm)form;
		if(StringUtils.isNotBlank(frm.getNewJobStatus())){
			TKServiceLocator.getBatchJobService().updateJobStatus(frm, frm.getNewJobStatus());
		}
		return search(mapping, form, request, response);
	}

	public ActionForward updateIpNet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TKBatchJobForm frm = (TKBatchJobForm)form;
		if(StringUtils.isNotBlank(frm.getNewIpNet())){
			TKServiceLocator.getBatchJobService().updateIpNet(frm, frm.getNewIpNet());
		}
		return search(mapping, form, request, response);
	}
	
	public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TKBatchJobForm frm = (TKBatchJobForm)form;
		TKServiceLocator.getBatchJobService().deleteJobEntries(frm);
		return mapping.findForward("default");
	}
	
	@Override
	protected String getMethodName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String parameter) throws Exception {
		String methodName = super.getMethodName(mapping, form, request, response, parameter);
		return (StringUtils.isEmpty(methodName) ? "defaultAction" : methodName);
	}



	public ActionForward defaultAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//nothing
		return mapping.findForward("default");
	}
}
