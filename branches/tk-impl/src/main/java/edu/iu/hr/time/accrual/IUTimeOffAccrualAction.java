package edu.iu.hr.time.accrual;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.time.accrual.web.TimeOffAccrualAction;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUtils;

import edu.iu.hr.time.service.base.TkServiceLocator;

public class IUTimeOffAccrualAction extends TimeOffAccrualAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IUTimeOffAccrualActionForm tof = (IUTimeOffAccrualActionForm)form;
		//tof.setAccrualRates(TkServiceLocator.getAccrualRateService().getAccrualRate(TKContext.getTargetPrincipalId(), TKUtils.getCurrentDate()));
		return super.execute(mapping, form, request, response);
	}
		
}
