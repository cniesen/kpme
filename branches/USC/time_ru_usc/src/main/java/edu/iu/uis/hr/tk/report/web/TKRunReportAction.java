package edu.iu.uis.hr.tk.report.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.report.web.rule.TKRunReportRule;

public class TKRunReportAction extends DispatchAction {
	
	private final static TKRunReportRule TK_RUN_REPORT_RULE = new TKRunReportRule();

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



	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}
	
	public ActionForward runReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TKRunReportForm runReportForm = (TKRunReportForm) form;
		
		String sql = runReportForm.getReports().get(runReportForm.getReportToRun()).getSql();
		Object[] params = runReportForm.getParamsForReport();

		runReportForm.setReportError(TK_RUN_REPORT_RULE.validateInputs(runReportForm));
		if (StringUtils.isEmpty(runReportForm.getReportError())){
			try {
				runReportForm.setResults(TKServiceLocator.getTkJdbcTemplate().queryForList(sql, params));
				runReportForm.getResults();
				runReportForm.setReportError(null);
				runReportForm.setReportRunComplete(true);
			} catch (Exception e){
				runReportForm.setReportError(e.toString());
				runReportForm.setReportRunComplete(false);
			}
		}
		runReportForm.setParamsForReport(null);
		return mapping.findForward("default");
	}
	
//	private List<Object> formatParams(TKRunReportForm runReportForm){
//		List<Object> returnParams = new ArrayList<Object>();
//		Integer i = 0;
//		for (String param : runReportForm.getParamsForReport()){
//			String paramName = runReportForm.getReports().get(runReportForm.getReportToRun()).getParams().get(i);
//			if (StringUtils.equals(paramName, "Comma delimited emplid")){
//				String formattedParam = "";
//				String[] emplidArray = StringUtils.split(param, ",");
//				for (String emplid : emplidArray){
//					emplid = StringUtils.stripStart(StringUtils.stripEnd(emplid, " "), " ");
//					formattedParam += "'"+ emplid + "',";
//				}
//				formattedParam = StringUtils.stripEnd(formattedParam, ",");
//				returnParams.add(formattedParam);
//			} else {
//				returnParams.add(param);
//			}
//			i++;
//		}
//		return returnParams;
//	}
}
