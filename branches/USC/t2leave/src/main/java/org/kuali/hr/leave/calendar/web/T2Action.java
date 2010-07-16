package org.kuali.hr.leave.calendar.web;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.action.KualiAction;

public class T2Action extends KualiAction {

	private static final Logger LOG = Logger.getLogger(T2Action.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		T2Form leaveForm = (T2Form) form;

		try {
			leaveForm.setMappingName(mapping.getName());
			leaveForm.setMappingPath(StringUtils.substring(mapping.getPath(), 1));

//ww			EptoUser user = leaveForm.getUser();

//ww			EptoSessionState eptoSessionState = new EptoSessionState(request.getSession());
//ww			if (eptoSessionState.getTargetEmployee() == null) {
//ww				eptoSessionState.setTargetEmployee(user);
//ww			}
//ww			leaveForm.setEmployee(eptoSessionState.getTargetEmployee());
//ww
//ww			EptoServiceLocator.getEptoAuthorizationService().populateSecurityTrees(leaveForm);
//ww
//ww			this.runAccrualIfNecessary(eptoSessionState.getTargetEmployee(), false);
//ww			leaveForm.setShowNonUserTabs(StringUtils.equals(leaveForm.getUser().getEmplid(), leaveForm.getEmployee().getEmplid()));

 			return super.execute(mapping, form, request, response);
		} catch (Throwable t) {
			LOG.error("Exception caught processing request", t);
			throw new RuntimeException(t);
		} finally {
			leaveForm.setMethodToCall(null);
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("basic");
	}

	public ActionForward removeBackdoor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		T2Form leaveForm = (T2Form)form;
//ww		EptoUser user = EptoServiceLocator.getUserService().getUserByNetworkId(EptoLoginFilter.getRemoteUser(request));
//ww		user.setBackdoorUser(null);
//ww		EptoContext.setBackdoorUser(null);
//ww		leaveForm.setEmployee(user);
//ww		EptoServiceLocator.getEptoAuthorizationService().populateSecurityTrees(leaveForm);
//ww		EptoSessionState eptoSessionState = new EptoSessionState(request.getSession());
//ww		eptoSessionState.setTargetEmployee(user);
//ww		eptoSessionState.setBackdoorUser(null);
//ww		eptoSessionState.removeFormsForUser();
		return mapping.findForward("basic");
	}

//ww	protected void runAccrualIfNecessary(EptoUser user, boolean disregardUserStatus) {
//ww		if (EptoServiceLocator.getEptoAuthorizationService().isEptoUser(user,user) || disregardUserStatus) {
//ww			String emplid = user.getEmplid();
//ww			boolean runAccrual = EptoServiceLocator.getJobService().needToRunAccruals(emplid, EptoUtils.getCurrentYear());
//ww			if (runAccrual) {
//ww				EptoServiceLocator.getAccrualService().runAccrual(emplid);
//ww			}
//ww		}
//ww	}

	public ActionForward changeEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		T2Form leaveForm = (T2Form)form;
		if(StringUtils.isBlank(leaveForm.getChangeEmployeeNetworkId())){
			GlobalVariables.getErrorMap().putError("changeValidation", "sysadm.backdoor.empty");
			leaveForm.setMethodToCall("");
			return mapping.findForward("basic");
		}

//ww 		if(!(new CalendarRule().validateChangeEmployee(leaveForm))) {
//ww			leaveForm.setMethodToCall("");
//ww			return mapping.findForward("basic");
//ww		}

 		// created an extra logic to check the position number
//ww		if (new CalendarRule().validateChangeEmployee(leaveForm) ||
//ww				(StringUtils.isEmpty(leaveForm.getEmployee().getPositionNumber()) && leaveForm instanceof CalendarForm)) {
//ww
//ww			EptoSessionState eptoSessionState = new EptoSessionState(request.getSession());
//ww			eptoSessionState.setTargetEmployee(EptoServiceLocator.getUserService().getUserByNetworkId(leaveForm.getChangeEmployeeNetworkId()));
//ww			leaveForm.setEmployee(eptoSessionState.getTargetEmployee());
//ww			leaveForm.setChangeEmployeeNetworkId("");
//ww			GlobalVariables.getErrorMap().clear();
//ww			EptoServiceLocator.getEptoAuthorizationService().populateSecurityTrees(leaveForm);
//ww			this.runAccrualIfNecessary(eptoSessionState.getTargetEmployee(), true);
//ww		}

//ww		leaveForm.setShowNonUserTabs(StringUtils.equals(leaveForm.getUser().getEmplid(), leaveForm.getEmployee().getEmplid()));
//ww		leaveForm.setMethodToCall(null);
//ww		this.putRefererURL(request);
//ww
//ww		// EPTO-394
//ww		if(leaveForm instanceof SystemAdminForm) {
//ww			ActionRedirect redirect = new ActionRedirect(mapping.findForwardConfig("calendar"));
//ww			redirect.addParameter("methodToCall", "view");
//ww			return redirect;
//ww		}
		return mapping.findForward("basic");
	}

	public ActionForward resetEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		T2Form leaveForm = (T2Form)form;
//ww		EptoSessionState eptoSessionState = new EptoSessionState(request.getSession());
//ww		eptoSessionState.setTargetEmployee(leaveForm.getUser());
//ww		leaveForm.setEmployee(eptoSessionState.getTargetEmployee());
		leaveForm.setChangeEmployeeNetworkId("");
//ww		leaveForm.setShowNonUserTabs(StringUtils.equals(leaveForm.getUser().getEmplid(), leaveForm.getEmployee().getEmplid()));
//ww		EptoServiceLocator.getEptoAuthorizationService().populateSecurityTrees(leaveForm);
		leaveForm.setMethodToCall(null);
		String referer = this.getRefererURL(request);
		if (StringUtils.isNotBlank(referer)) {
			return new ActionForward(referer, true);
		}

		return mapping.findForward("basic");
	}


	private static final String REFERER_KEY = "_REFERER";
	protected void putRefererURL(HttpServletRequest request) {
		request.getSession().setAttribute(REFERER_KEY, request.getHeader("referer"));
	}

	protected String getRefererURL(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(REFERER_KEY);
	}

	public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//if only a epto admin take them to the epto admin tab
		//if only a delegate goto delegate tab
		//if actual supervisor take to supervisor tab
		String docId = request.getParameter("docId");
		if(StringUtils.isNotBlank(docId)){
//ww			String emplid = EptoServiceLocator.getEptoCalendarWorkflowService().getEmplidFromDocument(docId);
//ww			String positionNumber = EptoServiceLocator.getPositionHierarchyService().translateEmplidToPosition(emplid);
//ww			EptoUser targetUser = EptoServiceLocator.getUserService().getUserByEmplid(emplid);
//ww			Date monthDate = EptoServiceLocator.getEptoCalendarWorkflowService().getDateFromDocument(docId);
			int month = 0;
			int year = 0;
//ww			if(monthDate != null){
//ww				Calendar cal = GregorianCalendar.getInstance();
//ww				cal.setTime(monthDate);
//ww				month = cal.get(Calendar.MONTH);
//ww				year = cal.get(Calendar.YEAR);
//ww			}
			//get the immediate supervisor
//ww			String posSuper = EptoServiceLocator.getPositionHierarchyService().getParentPosition(positionNumber);
//ww			String emplidSuper = EptoServiceLocator.getPositionHierarchyService().translatePositionToEmplid(posSuper);
//ww			String posUser = EptoServiceLocator.getPositionHierarchyService().translateEmplidToPosition(EptoContext.getUser().getEmplid());
			//check for supervisor first
			//forward them to supervisor tab of the appropriate month
//ww			if(EptoServiceLocator.getEptoAuthorizationService().isUserSupervisorForEmployee(EptoContext.getUser(), targetUser)){
//ww				if(monthDate!=null){
//ww					return new ActionForward("/StaffReportApproval.do?targetMonth="+month+"&year="+year,true);
//ww				}
//ww				else{
//ww					return new ActionForward("/StaffReportApproval.do",true);
//ww				}
//ww			}
//ww			//TODO make this point to the immediate supervisor not necessarily the user...
//ww			else if(EptoServiceLocator.getEptoAuthorizationService().isEptoAdmin(EptoContext.getUser(), targetUser)){
//ww				if(monthDate!=null && posSuper != null && StringUtils.isNotEmpty(emplidSuper)){
//ww					return new ActionForward("/EptoAdmin.do?methodToCall=changeEmployeeNode&employeeNodeToChange="+emplidSuper+"&targetMonth="+month+"&year="+year,true);
//ww				}
//ww 				return new ActionForward("/EptoAdmin.do",true);
//ww			}
//ww			else if(EptoServiceLocator.getEptoAuthorizationService().hasRole(posUser, posSuper, EptoConstants.EptoSecurity.DELEGATE_ROLENAME)){
//ww				if(monthDate!=null && posSuper != null){
//ww					return new ActionForward("/Delegate.do?methodToCall=changeEmployeeNode&employeeNodeToChange="+emplidSuper+"&targetMonth="+month+"&year="+year,true);
//ww				}
//ww				return new ActionForward("/Delegate.do",true);
//ww			}
//ww			else if(EptoServiceLocator.getEptoAuthorizationService().isSystemAdmin()){
//ww				if(monthDate != null){
//ww					LeaveForm frm = (LeaveForm)form;
//ww					frm.setChangeEmployeeNetworkId(targetUser.getNetworkId());
//ww					changeEmployee(mapping, form, request, response);
//ww				}
//ww				return new ActionForward("/ViewCalendar.do",true);
//ww			}
		}

		return mapping.findForward("basic");
	}

}
