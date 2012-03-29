package edu.iu.hr.time.approval.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.kuali.hr.time.approval.web.ApprovalTimeSummaryRow;
import org.kuali.hr.time.approval.web.TimeApprovalAction;
import org.kuali.hr.time.approval.web.TimeApprovalActionForm;
import org.kuali.hr.time.paycalendar.PayCalendar;
import org.kuali.hr.time.paycalendar.PayCalendarEntries;
import org.kuali.hr.time.person.TKPerson;
import org.kuali.hr.time.roles.UserRoles;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUser;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.exception.AuthorizationException;

public class IUTimeApprovalAction extends TimeApprovalAction {

	@Override
	protected void checkTKAuthorization(ActionForm form, String methodToCall)
			throws AuthorizationException {
		TKUser user = TKContext.getUser();
		UserRoles roles = user.getCurrentRoles();
		TimeApprovalActionForm taaf = (TimeApprovalActionForm) form;
		
		if (taaf.getRoleName() == null) {
			 if (!roles.isTimesheetReviewer() && !roles.isAnyApproverActive() && !roles.isSystemAdmin() && !roles.isLocationAdmin() && !roles.isGlobalViewOnly() && !roles.isDeptViewOnly() && !roles.isDepartmentAdmin()) {
		            throw new AuthorizationException(user.getPrincipalId(), "TimeApprovalAction", "");
		        }
		} else {
			if (!roles.isTimesheetPayrollProcessor()) {
				throw new AuthorizationException(user.getPrincipalId(), "TimeApprovalAction", "");
			}
		}
	}

	@Override
	public ActionForward loadApprovalTab(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward fwd = mapping.findForward("basic");
		TKUser user = TKContext.getUser();
        TimeApprovalActionForm taaf = (TimeApprovalActionForm) form;
        Date currentDate = null;
        PayCalendarEntries payCalendarEntries = null;
        PayCalendar currentPayCalendar = null;
        String page = request.getParameter((new ParamEncoder(TkConstants.APPROVAL_TABLE_ID).encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
        resetState(form, request);

		 // Set calendar groups
        List<String> calGroups = TkServiceLocator.getTimeApproveService().getUniquePayGroups();
        taaf.setPayCalendarGroups(calGroups);

        if (StringUtils.isBlank(taaf.getSelectedPayCalendarGroup())) {
            taaf.setSelectedPayCalendarGroup(calGroups.get(0));
        }
        
        // Set current pay calendar entries if present. Decide if the current date should be today or the end period date
        if (taaf.getHrPyCalendarEntriesId() != null) {
        	payCalendarEntries = TkServiceLocator.getPayCalendarEntriesSerivce().getPayCalendarEntries(taaf.getHrPyCalendarEntriesId());
            currentDate = payCalendarEntries.getEndPeriodDate();
        } else {
            currentDate = TKUtils.getTimelessDate(null);
            currentPayCalendar = TkServiceLocator.getPayCalendarSerivce().getPayCalendarByGroup(taaf.getSelectedPayCalendarGroup());
            payCalendarEntries = TkServiceLocator.getPayCalendarEntriesSerivce().getCurrentPayCalendarEntriesByPayCalendarId(currentPayCalendar.getHrPyCalendarId(), currentDate);
        }
        taaf.setPayCalendarEntries(payCalendarEntries);
        
        if(payCalendarEntries != null){
            taaf.setHrPyCalendarId(payCalendarEntries.getHrPyCalendarId());
            taaf.setHrPyCalendarEntriesId(payCalendarEntries.getHrPyCalendarEntriesId());
            taaf.setPayBeginDate(payCalendarEntries.getBeginPeriodDateTime());
            taaf.setPayEndDate(payCalendarEntries.getEndPeriodDateTime());
            
            PayCalendarEntries prevPayCalendarEntries = TkServiceLocator.getPayCalendarEntriesSerivce().getPreviousPayCalendarEntriesByPayCalendarId(taaf.getHrPyCalendarId(), payCalendarEntries);
            if (prevPayCalendarEntries != null) {
                taaf.setPrevPayCalendarId(prevPayCalendarEntries.getHrPyCalendarEntriesId());
            } else {
                taaf.setPrevPayCalendarId(null);
            }
            
            PayCalendarEntries nextPayCalendarEntries = TkServiceLocator.getPayCalendarEntriesSerivce().getNextPayCalendarEntriesByPayCalendarId(taaf.getHrPyCalendarId(), payCalendarEntries);
            if (nextPayCalendarEntries != null) {
                taaf.setNextPayCalendarId(nextPayCalendarEntries.getHrPyCalendarEntriesId());
            } else {
                taaf.setNextPayCalendarId(null);
            }
            taaf.setPayCalendarLabels(TkServiceLocator.getTimeSummaryService().getHeaderForSummary(payCalendarEntries, new ArrayList<Boolean>()));
            
            List<String> depts;
    		if (StringUtils.isBlank(page)) {
    			if (taaf.getRoleName() == null) {
    				depts = new ArrayList<String>(user.getReportingApprovalDepartments().keySet());
    			}else {
    				depts = new ArrayList<String>(user.getReportingPayrollProcessorDepartments().keySet());
    			}
    			
    			if (!depts.isEmpty() ) {
					  Collections.sort(depts);
					  taaf.setDepartments(depts);
  				}
    		}
            
	        if (taaf.getDepartments().size() == 1 || taaf.getSelectedDept() != null) {
            	if (StringUtils.isEmpty(taaf.getSelectedDept()))
            		taaf.setSelectedDept(taaf.getDepartments().get(0));
            	
            	List<WorkArea> workAreas = TkServiceLocator.getWorkAreaService().getWorkAreas(taaf.getSelectedDept(), new java.sql.Date(taaf.getPayBeginDate().getTime()));
                for(WorkArea wa : workAreas){
                	if (TKContext.getUser().getCurrentRoles().getApproverWorkAreas().contains(wa.getWorkArea())
                			|| TKContext.getUser().getCurrentRoles().getReviewerWorkAreas().contains(wa.getWorkArea())) {
                		taaf.getWorkAreaDescr().put(wa.getWorkArea(),wa.getDescription()+"("+wa.getWorkArea()+")");
                	}
                }

                List<String> principalIds = new ArrayList<String>();
            	principalIds = TkServiceLocator.getTimeApproveService().getPrincipalIdsByDeptWorkAreaRolename(taaf.getRoleName(), taaf.getSelectedDept(), taaf.getSelectedWorkArea(), new java.sql.Date(taaf.getPayBeginDate().getTime()), new java.sql.Date(taaf.getPayEndDate().getTime()), taaf.getSelectedPayCalendarGroup());
            	if (principalIds.isEmpty()) {
            		taaf.setApprovalRows(new ArrayList<ApprovalTimeSummaryRow>());
            		taaf.setResultSize(0);
            	}
            	else {
	                List<TKPerson> persons = TkServiceLocator.getPersonService().getPersonCollection(principalIds);
	                Collections.sort(persons);
	                taaf.setApprovalRows(getApprovalRows(taaf, getSubListPrincipalIds(request, persons)));
	                taaf.setResultSize(persons.size());
            	}
            }
        }
           
        return fwd;
	}
	
	public void resetState(ActionForm form, HttpServletRequest request) {
	      TimeApprovalActionForm taaf = (TimeApprovalActionForm) form;
	      String page = request.getParameter((new ParamEncoder(TkConstants.APPROVAL_TABLE_ID).encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
	      
	      if (StringUtils.isBlank(page)) {
			  taaf.getDepartments().clear();
			  taaf.getWorkAreaDescr().clear();
			  taaf.setApprovalRows(new ArrayList<ApprovalTimeSummaryRow>());
			  taaf.setSelectedDept(null);
			  taaf.setSearchField(null);
			  taaf.setSearchTerm(null);
	      }
	}
	@Override
	public ActionForward selectNewDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimeApprovalActionForm taaf = (TimeApprovalActionForm)form;
		taaf.getWorkAreaDescr().clear();
		taaf.setSearchField(null);
		taaf.setSearchTerm(null);
		
		taaf.getWorkAreaDescr().clear();
		if (taaf.getRoleName() == null) {
			List<WorkArea> workAreas = TkServiceLocator.getWorkAreaService().getWorkAreas(taaf.getSelectedDept(), new java.sql.Date(taaf.getPayBeginDate().getTime()));
	        for(WorkArea wa : workAreas){
	        	if (TKContext.getUser().getCurrentRoles().getApproverWorkAreas().contains(wa.getWorkArea())
	        			|| TKContext.getUser().getCurrentRoles().getReviewerWorkAreas().contains(wa.getWorkArea())) {
	        		taaf.getWorkAreaDescr().put(wa.getWorkArea(),wa.getDescription()+"("+wa.getWorkArea()+")");
	        	}
	        }
		}else {
			List<WorkArea> workAreas = TkServiceLocator.getWorkAreaService().getWorkAreas(taaf.getSelectedDept(), new java.sql.Date(taaf.getPayBeginDate().getTime()));
	         for(WorkArea wa : workAreas){
	         		taaf.getWorkAreaDescr().put(wa.getWorkArea(),wa.getDescription()+"("+wa.getWorkArea()+")");
	         }
		}

		PayCalendarEntries payCalendarEntries = TkServiceLocator.getPayCalendarEntriesSerivce().getPayCalendarEntries(taaf.getHrPyCalendarEntriesId());
        taaf.setPayCalendarEntries(payCalendarEntries);
        taaf.setPayCalendarLabels(TkServiceLocator.getTimeSummaryService().getHeaderForSummary(payCalendarEntries, new ArrayList<Boolean>()));

    	List<String> principalIds = TkServiceLocator.getTimeApproveService().getPrincipalIdsByDeptWorkAreaRolename(taaf.getRoleName(), taaf.getSelectedDept(), taaf.getSelectedWorkArea(), new java.sql.Date(taaf.getPayBeginDate().getTime()), new java.sql.Date(taaf.getPayEndDate().getTime()), taaf.getSelectedPayCalendarGroup());
    	if (principalIds.isEmpty()) {
    		taaf.setApprovalRows(new ArrayList<ApprovalTimeSummaryRow>());
    		taaf.setResultSize(0);
    	}
    	else {
	        List<TKPerson> persons = TkServiceLocator.getPersonService().getPersonCollection(principalIds);
	        Collections.sort(persons);
	        taaf.setApprovalRows(getApprovalRows(taaf, getSubListPrincipalIds(request, persons)));
	        taaf.setResultSize(persons.size());
    	}

		return mapping.findForward("basic");
	}
	
}
