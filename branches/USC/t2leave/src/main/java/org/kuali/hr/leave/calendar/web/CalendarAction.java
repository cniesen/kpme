package org.kuali.hr.leave.calendar.web;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.leave.calendar.Month;
import org.kuali.hr.leave.leavecode.LeaveCode;
import org.kuali.hr.leave.util.T2Constants;
import org.kuali.hr.leave.util.T2ServiceLocator;
import org.kuali.hr.leave.util.TimelessDate;
import org.kuali.rice.kew.exception.WorkflowException;

public class CalendarAction extends T2Action {

// TODO	private static CalendarRule calendarRule = new CalendarRule();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CalendarForm calendarForm = (CalendarForm)form;
 
		ActionForward actForw = super.execute(mapping, form, request, response);
// TODO   ActionForward nonPAEForward = this.findActionMappingForNonPAE(mapping,calendarForm);
// TODO   if (nonPAEForward != null) {
// TODO         return nonPAEForward;
// TODO   }


// TODO		Calendar calendar = EptoServiceLocator.getEptoCalendarWorkflowService().getMostRecentSubmittedDateCalendar(calendarForm.getEmployee().getEmplid());
// TODO
// TODO		// if the month is not assigned, set the month to the one which needs to be approved
// TODO		if(calendarForm.getYear() == 0) {
// TODO			if(calendar == null) {
// TODO				calendarForm.setTargetMonth(0);
// TODO				calendarForm.setYear(new TimelessDate().getYear());
// TODO			}
// TODO			else {
// TODO				calendarForm.setTargetMonth(calendar.get(Calendar.MARCH)+1);
// TODO				calendarForm.setYear(calendar.get(Calendar.YEAR));
// TODO			}
// TODO		}

        	// TODO set the calendar to current month for now.  come back and find out about the statements above...
        	// TODO set calendar to current day.
        	Calendar calendar =  Calendar.getInstance();;
		if(calendarForm.getYear() == 0) {
			if(calendar == null) {
				calendarForm.setTargetMonth(0);
				calendarForm.setYear(new TimelessDate().getYear());
			}
			else {
//orig			calendarForm.setTargetMonth(calendar.get(Calendar.MARCH)+1);
				calendarForm.setTargetMonth(calendar.get(Calendar.MARCH));
				calendarForm.setYear(calendar.get(Calendar.YEAR));
			}
		}

 		//Get month each time the page loads
		Calendar targetDateCalendar = new GregorianCalendar(calendarForm.getYear(), calendarForm.getTargetMonth(), 1);
		targetDateCalendar.set(Calendar.DAY_OF_MONTH, targetDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
 		Month month = T2ServiceLocator.getCalendarService().getMonth(calendarForm.getEmployee(), new Date(targetDateCalendar.getTime().getTime()));
 
		//if method to call is docHandler bypass this as it will forward them somewhere else
		String methodToCall = request.getParameter("methodToCall");
// TODO		if (EptoServiceLocator.getEptoBalanceTransferService().isPastTransfer(calendarForm.getEmployee(), targetDateCalendar) && !StringUtils.equals(methodToCall, "docHandler")){
// TODO			return mapping.findForward("balanceTransfer");
// TODO		}
		
// TODO		month.setYearEndTransferComplete(EptoServiceLocator.getEptoBalanceTransferService().isMonthAlreadyTransfered(calendarForm.getEmployee(), month));
// TODO		
// TODO		EptoServiceLocator.getCalendarService().insertDocumentHeaderOnMonth(month, EptoContext.getUser().getEmplid(), calendarForm.getEmployee().getEmplid());
 		calendarForm.setMonth(month);
// TODO		calendarForm.setPtoBalanceContainer(EptoServiceLocator.getBalanceContainerService().getBalanceContainer(calendarForm.getEmployee(), targetDateCalendar));
// TODO		calendarForm.setBasicLeaveCodes(EptoServiceLocator.getLeaveCodeService().getBasicLeaveCodes(calendarForm.getEmployee().getEmplid(), new java.sql.Date(System.currentTimeMillis()), EptoUtils.getLastDayOfMonth(month)));
// TODO		calendarForm.setAdvancedLeaveCodes(EptoServiceLocator.getLeaveCodeService().getAdvancedLeaveCodes(calendarForm.getEmployee().getEmplid(), new java.sql.Date(System.currentTimeMillis()), EptoUtils.getLastDayOfMonth(month)));
// TODO		calendarForm.setAllLeaveCodes(EptoServiceLocator.getLeaveCodeService().getAllLeaveCodes(calendarForm.getEmployee().getEmplid(), new java.sql.Date(System.currentTimeMillis()), EptoUtils.getLastDayOfMonth(month)));

		//reset flex schedule
// TODO		calendarForm.setFlexSchedule(false);
// TODO		List<EmplOverrideRow> emplOverrideRows = (EptoServiceLocator.getEmplOverridePersistenceService().getEmplOverride(EptoContext.getUser().getEmplid(), EptoUtils.getLastDayOfMonth(month))).getEmplOverrideRows();
// TODO		if (emplOverrideRows.size() >= 1) {
// TODO			calendarForm.setFlexSchedule(emplOverrideRows.get(0).getFlexSchedule());
// TODO		}

// TODO		removeHonoraryIfHasNoBalance(calendarForm);

// TODO		setupApprovalButtons(calendarForm);

// TODO		calendarForm.setDisapprovedDocumentIds(EptoServiceLocator.getEptoCalendarWorkflowService().getDisapprovedDocumentIds(calendarForm.getEmployee().getEmplid(), month));
		return actForw;
	}

    public ActionForward findActionMappingForNonPAE(ActionMapping mapping, CalendarForm calendarForm) {
// TODO        if (!calendarForm.getEmployeeSecurityContext().isEptoUser()){
// TODO       	List<String> roleList = calendarForm.getUserSecurityContext().getRoleList();
// TODO        	if (roleList.contains(EptoConstants.EptoSecurity.SYSTEM_ADMINISTRATOR_ROLENAME )){
// TODO        		return mapping.findForward("systemAdmin");
// TODO        	} else if(roleList.contains(EptoConstants.EptoSecurity.EPTO_ADMINISTRATOR_ROLENAME)) {
// TODO        		return mapping.findForward("eptoAdmin");
// TODO        	} else if(roleList.contains(EptoConstants.EptoSecurity.DELEGATE_ROLENAME)) {
// TODO        		return mapping.findForward("delegate");
// TODO        	} else if(roleList.contains(EptoConstants.EptoSecurity.SUPERVISOR_ROLENAME)) {
// TODO        		return mapping.findForward("staffReport");
// TODO        	} else if(roleList.contains(EptoConstants.EptoSecurity.VIEW_ONLY_ROLENAME)) {
// TODO        		return mapping.findForward("viewOnly");
// TODO        	} 
// TODO        }
    	return null;
  }


	//https://onestart.iu.edu/jira-prd/browse/EPTO-279

	public void removeHonoraryIfHasNoBalance(CalendarForm calendarForm) {
// TODO		Date defaultStartDate = TimelessDate.getSqlDate("1/1/1901");
// TODO		BigDecimal honTotal = EptoServiceLocator.getLedgerService().getTotal(LeaveConstants.HONORARY_CODE, calendarForm.getEmployee().getEmplid(), defaultStartDate, EptoUtils.getLastDayOfYear());
		LeaveCode removedBasicLeaveCode = new LeaveCode();
		LeaveCode removedAdvancedLeaveCode = new LeaveCode();

// TODO		if (honTotal.intValue() == 0) {
// TODO			for (LeaveCode leaveCode: calendarForm.getBasicLeaveCodes()) {
// TODO				if (leaveCode.getLeaveCode().equals(LeaveConstants.HONORARY_CODE)) {
// TODO					removedBasicLeaveCode = leaveCode;
// TODO					break;
// TODO				}
// TODO			}
// TODO			for (LeaveCode leaveCode: calendarForm.getAdvancedLeaveCodes()) {
// TODO				if (leaveCode.getLeaveCode().equals(LeaveConstants.HONORARY_CODE)) {
// TODO					removedAdvancedLeaveCode = leaveCode;
// TODO					break;
// TODO				}
// TODO			}
// TODO
// TODO			//removed
// TODO			calendarForm.getBasicLeaveCodes().remove(removedBasicLeaveCode);
// TODO			calendarForm.getAdvancedLeaveCodes().remove(removedAdvancedLeaveCode);
// TODO		}
	}

	@SuppressWarnings("unchecked")
	public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CalendarForm calForm = (CalendarForm)form;
// TODO		if(StringUtils.equals(calForm.getEmployee().getEmplid(), calForm.getUser().getEmplid())){
// TODO			throw new RuntimeException("You cannot approve your own calendar");
// TODO		}
// TODO		String docId = calForm.getMonth().getDocumentId();
// TODO		if(StringUtils.isNotBlank(docId)){
// TODO			WorkflowDocument wd = new WorkflowDocument(new EmplIdDTO(calForm.getUser().getEmplid()), Long.parseLong(docId));
// TODO			if(!EptoServiceLocator.getEptoAuthorizationService().isSystemAdmin()){
// TODO				wd.approve("PTO was approved by "+calForm.getUser().getFullName()+". "+(calForm.getDisapprovedMessaage() == null ? "" : calForm.getDisapprovedMessaage()));
// TODO			} else {
// TODO				wd.superUserApprove("PTO was approved by "+calForm.getUser().getFullName()+". "+(calForm.getDisapprovedMessaage() == null ? "" : calForm.getDisapprovedMessaage()));
// TODO			}
// TODO
// TODO			EptoServiceLocator.getEptoNotificationService().sendPTOApprovedMessage(calForm.getEmployee().getEmplid(), calForm.getUser().getEmplid(), calForm.getMonth().getMonth());
// TODO		}
		return mapping.findForward("basic");
	}

	@SuppressWarnings("unchecked")
	public ActionForward disapprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
// TODO		CalendarForm calForm = (CalendarForm)form;
// TODO		if(StringUtils.isBlank(calForm.getDisapprovedMessaage())){
// TODO			GlobalVariables.getErrorMap().putError("cal", "workflow.document.disapproved.requires.message");
// TODO			return mapping.findForward("basic");
// TODO		}
// TODO		String docId = calForm.getMonth().getDocumentId();
// TODO		if(StringUtils.isNotBlank(docId)){
// TODO			WorkflowDocument wd = new WorkflowDocument(new EmplIdDTO(calForm.getUser().getEmplid()), Long.parseLong(docId));
// TODO			if(!EptoServiceLocator.getEptoAuthorizationService().isSystemAdmin()){
// TODO				wd.disapprove("PTO was disapproved by "+calForm.getUser().getFullName()+". "+(calForm.getDisapprovedMessaage() == null ? "" : calForm.getDisapprovedMessaage()));
// TODO			} else {
// TODO				wd.superUserDisapprove("PTO was disapproved by "+calForm.getUser().getFullName()+". "+(calForm.getDisapprovedMessaage() == null ? "" : calForm.getDisapprovedMessaage()));
// TODO			}
// TODO			EptoServiceLocator.getEptoCalendarWorkflowService().disapproveDocument(docId);
// TODO		}
		return mapping.findForward("basic");
	}

	private void setupApprovalButtons(CalendarForm calendarForm) throws NumberFormatException, WorkflowException{
// TODO		EptoUser eptoUser = calendarForm.getUser();
// TODO		String docId = calendarForm.getMonth().getDocumentId();
// TODO		boolean canSubmitThisMonth = EptoServiceLocator.getEptoCalendarWorkflowService().isValidSubmissionMonth(calendarForm.getEmployee().getEmplid(), calendarForm.getUser().getEmplid(), calendarForm.getMonth());
// TODO		if(StringUtils.isBlank(docId) && canSubmitThisMonth){
// TODO			//if no doc id show the submit button
// TODO			calendarForm.setShowApprove(false);
// TODO			calendarForm.setShowSubmit(true);
// TODO		}
// TODO		else{
// TODO			if(StringUtils.isNotBlank(docId) && !StringUtils.equals(docId, "-1")){
// TODO				WorkflowDocument wd = new WorkflowDocument(new NetworkIdDTO(eptoUser.getNetworkId()), Long.parseLong(docId));
// TODO				if((wd.isApprovalRequested() && !wd.isCompletionRequested()) || (EptoServiceLocator.getEptoAuthorizationService().isSystemAdmin() && wd.stateIsEnroute())){
// TODO					//show the approve/disapprove since this must be a supervisor or delegate
// TODO					calendarForm.setShowApprove(true);
// TODO					calendarForm.setShowSubmit(false);
// TODO				}
// TODO				else{
// TODO					//show no approval button because no action is allowed/required from this user
// TODO					calendarForm.setShowApprove(false);
// TODO					calendarForm.setShowSubmit(false);
// TODO				}
// TODO			}
// TODO			else{
// TODO				//show no approval button because no action is allowed/required from this user
// TODO				calendarForm.setShowApprove(false);
// TODO				calendarForm.setShowSubmit(false);
// TODO			}
// TODO		}
	}

	@SuppressWarnings("unchecked")
	public ActionForward submitCalendar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CalendarForm calendarForm = (CalendarForm)form;
// TODO		calendarForm.getCalendarDocument().setEmplid(calendarForm.getEmployee().getEmplid());
// TODO		
// TODO		//this is used to clear out any disapproval notification from workflow in the action list
// TODO		for(String disDocId : calendarForm.getDisapprovedDocumentIds()){
// TODO			EptoServiceLocator.getEptoCalendarWorkflowService().acknowledgeDocument(calendarForm,disDocId);
// TODO		}
// TODO
// TODO		if(EptoServiceLocator.getJobService().isTerminatedEmployee(calendarForm.getEmployee().getEmplid(), calendarForm.getEmployee().getPositionNumber())){
// TODO			//confirm if this is the termination month and if so all balances are zero'd out using the admin functions
// TODO			java.util.Date terminatedDate = EptoServiceLocator.getJobService().getTerminatedDate(calendarForm.getEmployee().getEmplid(), calendarForm.getEmployee().getPositionNumber());
// TODO			Calendar cal = GregorianCalendar.getInstance();
// TODO			cal.setTime(terminatedDate);
// TODO			if(calendarForm.getMonth().getMonth() == cal.get(Calendar.MONTH)){
// TODO				//get all of the accrual categories
// TODO				LeavePlan leavePlan = EptoServiceLocator.getLeavePlanPersistenceService().getLeavePlan(calendarForm.getEmployee().getEmplid());
// TODO				List<AccrualCategory> accrualCategories = EptoServiceLocator.getAccrualCategoryPersistenceService().getAccrualCategories(leavePlan.getPersistableId(), EptoUtils.getLastDayOfMonth(calendarForm.getMonth()));
// TODO				for(AccrualCategory accrCat : accrualCategories){
// TODO					if (StringUtils.equals(accrCat.getAccrualCategory(),"OTHER")) {
// TODO						continue;
// TODO					}
// TODO					BigDecimal total = EptoServiceLocator.getLedgerService().getTotal(accrCat.getPersistableId(),calendarForm.getEmployee().getEmplid(),EptoUtils.getLastDayOfPreviousYear(EptoConstants.LAST_YEAR),EptoUtils.getLastDayOfMonth(calendarForm.getMonth()));
// TODO					if(!total.equals(BigDecimal.ZERO)) {
// TODO						GlobalVariables.getErrorMap().putError("cal", "error.submit.termed.cal.with.balances", new String[]{accrCat.getAccrualCategory(),total.toPlainString()});
// TODO					}
// TODO				}
// TODO				if(!GlobalVariables.getErrorMap().isEmpty()){
// TODO					return mapping.findForward("basic");
// TODO				}
// TODO			}
// TODO		}
// TODO
// TODO		EptoServiceLocator.getEptoCalendarWorkflowService().routeCalendarDocument(calendarForm, calendarForm.getMonth());
// TODO
// TODO		calendarForm.setMethodToCall("");
		return mapping.findForward("basic");
	}

	public ActionForward addDateRangeAdvanced(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return this.addDateRange(mapping, form, request, response);
	}

	public ActionForward addDateRange(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CalendarForm calendarForm = (CalendarForm)form;
// TODO		if(StringUtils.equals(calendarForm.getMonth().getDocumentStatus(), "F") && StringUtils.equals(calendarForm.getEmployee().getEmplid(), calendarForm.getUser().getEmplid())){
// TODO			throw new RuntimeException("You cannot correct your own calendar");
// TODO		}
// TODO		if (calendarRule.validateAddDateRange(calendarForm)) {
// TODO
// TODO			//for advance tab
// TODO			if (calendarForm.getHours() != null) {
// TODO				calendarForm.setHours((calendarForm.getHours().abs()).multiply(new BigDecimal(-1)));
// TODO			}
// TODO			EptoServiceLocator.getCalendarService().addRange(
// TODO					calendarForm.getMonth(),
// TODO					calendarForm.getFromDate(),
// TODO					calendarForm.getToDate(),
// TODO					calendarForm.getHours(),
// TODO					Boolean.TRUE,
// TODO					calendarForm.getLeaveCodeKey(),
// TODO					calendarForm.getIncludeWeekends(),
// TODO					calendarForm.getEmployee(),
// TODO					calendarForm.getDescription(), false, true);
// TODO
// TODO			//dupe code put on actionform
// TODO			if (GlobalVariables.getErrorMap().isEmpty()) {
// TODO				calendarForm.setFromDate(null);
// TODO				calendarForm.setToDate(null);
// TODO				calendarForm.setDescription("");
// TODO				calendarForm.setHours(null);
// TODO				calendarForm.setIncludeWeekends(Boolean.FALSE);
// TODO				calendarForm.setLeaveCodeKey("");
// TODO			}
// TODO		}

		calendarForm.setTargetMonth(calendarForm.getMonth().getMonth());
		calendarForm.setYear(calendarForm.getMonth().getYear());

		// EPTO-316
		/*
		ActionRedirect redirect = new ActionRedirect(mapping.findForwardConfig("basic"));
		redirect.addParameter("methodToCall", "view");
		*/

		return mapping.findForward("basic");
	}

	public ActionForward addAccrual(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CalendarForm calendarForm = (CalendarForm)form;
// TODO		if(StringUtils.equals(calendarForm.getEmployee().getEmplid(), calendarForm.getUser().getEmplid())){
// TODO			throw new RuntimeException("You cannot correct your own calendar");
// TODO		}
// TODO		if (calendarRule.validateAddAccrual(calendarForm)) {
// TODO			EptoServiceLocator.getCalendarService().addRange(
// TODO					calendarForm.getMonth(),
// TODO					calendarForm.getAddAccrualDate(),
// TODO					calendarForm.getAddAccrualDate(),
// TODO					calendarForm.getHours(),
// TODO					calendarForm.getApplyToYTD(),
// TODO					calendarForm.getLeaveCodeKey(),
// TODO					Boolean.TRUE,
// TODO					calendarForm.getEmployee(),calendarForm.getDescription(), false, false);
// TODO
// TODO			//dupe code put on actionform
// TODO			if (GlobalVariables.getErrorMap().isEmpty()) {
// TODO				calendarForm.setAddAccrualDate(null);
// TODO				calendarForm.setDescription("");
// TODO				calendarForm.setIncludeWeekends(Boolean.FALSE);
// TODO				calendarForm.setHours(null);
// TODO				calendarForm.setLeaveCodeKey("");
// TODO			}
// TODO		}

		calendarForm.setTargetMonth(calendarForm.getMonth().getMonth());
		calendarForm.setYear(calendarForm.getMonth().getYear());
		return mapping.findForward("basic");
	}

	public ActionForward inactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CalendarForm calendarForm = (CalendarForm)form;
// TODO		if (calendarRule.validateInactivate()) {
// TODO			Ledger ledgerToInactivate = EptoServiceLocator.getLedgerService().getLedgerRow(calendarForm.getInactiveLedgerId());
// TODO			ledgerToInactivate.setEmplidInactivated(calendarForm.getUser().getEmplid());
// TODO			ledgerToInactivate.setTimestamInactivated(new Date(System.currentTimeMillis()));
// TODO			ledgerToInactivate.setStatus(0);
// TODO			EptoServiceLocator.getLedgerService().inactivateLedgerRow(ledgerToInactivate);
// TODO			if(EptoServiceLocator.getLeaveCodeService().leaveCodeAffectsPay(ledgerToInactivate.getPtoCode())){
// TODO				EptoServiceLocator.getEptoNotificationService().sendInactiveNoticeAffectingPay(ledgerToInactivate.getEmplId(), ledgerToInactivate);
// TODO			}
// TODO		}
		return mapping.findForward("basic");
	}

}
