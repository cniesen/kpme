/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.hr.time.base.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.kuali.hr.core.document.calendar.CalendarDocumentContract;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.calendar.Calendar;
import org.kuali.hr.time.calendar.CalendarEntries;
import org.kuali.hr.time.roles.UserRoles;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUser;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.util.GlobalVariables;

public class ApprovalAction extends TkAction{

	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (form instanceof TkForm) {
           String methodToCall = ((TkForm)form).getMethodToCall();
           if(StringUtils.isEmpty(methodToCall)) {
        	   super.execute(mapping, form, request, response);
        	   return loadApprovalTab(mapping, form, request, response); 
           }
        }
        return super.execute(mapping, form, request, response);
    }
	
	public ActionForward loadApprovalTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("basic");
	}
	
	protected List<String> getSubListPrincipalIds(HttpServletRequest request, List<String> assignmentPrincipalIds) {
	    String page = request.getParameter((new ParamEncoder(TkConstants.APPROVAL_TABLE_ID).encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
	    // The paging index begins from 1, but the sublist index begins from 0.
	    // So the logic below sets the sublist begin index to 0 if the page number is null or equals 1
	    Integer beginIndex = StringUtils.isBlank(page) || StringUtils.equals(page, "1") ? 0 : (Integer.parseInt(page) - 1)*TkConstants.PAGE_SIZE;
	    Integer endIndex = beginIndex + TkConstants.PAGE_SIZE > assignmentPrincipalIds.size() ? assignmentPrincipalIds.size() : beginIndex + TkConstants.PAGE_SIZE;

	    return assignmentPrincipalIds.subList(beginIndex, endIndex);
	}





	protected Boolean isAscending(HttpServletRequest request, ApprovalForm form) {

	    // returned value 1 = ascending; 2 = descending
	    String ascending = request.getParameter((new ParamEncoder(TkConstants.APPROVAL_TABLE_ID).encodeParameterName(TableTagParameters.PARAMETER_ORDER)));
	    if (StringUtils.isEmpty(ascending)) {
            ascending = form.isAscending() ? "1" : "2";
        } else {
            form.setAscending(StringUtils.equals(ascending, "1"));
        }
        return StringUtils.isEmpty(ascending) || StringUtils.equals(ascending, "1") ? true : false;
	}

	protected String getSortField(HttpServletRequest request, ApprovalForm form) {
	    String sortField = request.getParameter((new ParamEncoder(TkConstants.APPROVAL_TABLE_ID).encodeParameterName(TableTagParameters.PARAMETER_SORT)));
        if (StringUtils.isEmpty(sortField)) {
            sortField = form.getSortField();
        } else {
            form.setSortField(sortField);
        }
        return sortField;
	}

    protected String getPage(HttpServletRequest request, ApprovalForm form) {
        String page = new ParamEncoder(TkConstants.APPROVAL_TABLE_ID).encodeParameterName(TableTagParameters.PARAMETER_PAGE);
        String pageVal = request.getParameter(page);
        if (StringUtils.isNotEmpty(pageVal)) {
            form.setPageId(page + "=" + pageVal);
        } else {
            form.setPageId(StringUtils.EMPTY);
        }
        return pageVal;
    }
	
	protected void checkTKAuthorization(ActionForm form, String methodToCall)
			throws AuthorizationException {
			    if (!TKUser.isTimesheetReviewer() && !TKUser.isAnyApproverActive() && !TKUser.isSystemAdmin()
			    		&& !TKUser.isLocationAdmin() && !TKUser.isGlobalViewOnly() && !TKUser.isDeptViewOnly()
			    		&& !TKUser.isDepartmentAdmin()) {
			        throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "ApprovalAction", "");
			    }
			}
	
	protected void resetMainFields(ActionForm form) {
		ApprovalForm taf = (ApprovalForm) form;
		taf.setSearchField(null);
		taf.setSearchTerm(null);
		taf.setSelectedWorkArea(null);
		taf.setSelectedDept(null);
		taf.setPayBeginDate(null);
		taf.setPayEndDate(null);
		taf.setHrPyCalendarEntriesId(null);
	}
	
	protected void setupDocumentOnFormContext(HttpServletRequest request,
			ApprovalForm taf, CalendarEntries payCalendarEntries, String page, CalendarDocumentContract calDoc) {
		if(payCalendarEntries == null) {
			return;
		}
		taf.setHrPyCalendarId(payCalendarEntries.getHrCalendarId());
		taf.setHrPyCalendarEntriesId(payCalendarEntries.getHrCalendarEntriesId());
		taf.setPayBeginDate(payCalendarEntries.getBeginPeriodDateTime());
		taf.setPayEndDate(DateUtils.addMilliseconds(payCalendarEntries.getEndPeriodDateTime(),-1));
		
		CalendarEntries prevPayCalendarEntries = TkServiceLocator.getCalendarEntriesService().getPreviousCalendarEntriesByCalendarId(taf.getHrPyCalendarId(), payCalendarEntries);
		if (prevPayCalendarEntries != null) {
		    taf.setPrevPayCalendarId(prevPayCalendarEntries.getHrCalendarEntriesId());
		} else {
		    taf.setPrevPayCalendarId(null);
		}
		
		CalendarEntries nextPayCalendarEntries = TkServiceLocator.getCalendarEntriesService().getNextCalendarEntriesByCalendarId(taf.getHrPyCalendarId(), payCalendarEntries);
		if (nextPayCalendarEntries != null) {
		    taf.setNextPayCalendarId(nextPayCalendarEntries.getHrCalendarEntriesId());
		} else {
		    taf.setNextPayCalendarId(null);
		}	
		if (StringUtils.isBlank(page)) {
		    List<String> depts = new ArrayList<String>(TKUser.getReportingApprovalDepartments().keySet());
		    if ( depts.isEmpty() ) {
		    	return;
		    }
		    Collections.sort(depts);
		    taf.setDepartments(depts);

            if (calDoc != null) {
                for (Assignment a : calDoc.getAssignments()) {
                    WorkArea wa = TkServiceLocator.getWorkAreaService().getWorkArea(a.getWorkArea(), a.getEffectiveDate());
                    if (taf.getDepartments().contains(wa.getDept())) {
                        taf.setSelectedDept(wa.getDept());
                        break;
                    }
                }
            }

		    if (taf.getDepartments().size() == 1 || taf.getSelectedDept() != null) {
		    	if (StringUtils.isEmpty(taf.getSelectedDept())) {
		    		taf.setSelectedDept(taf.getDepartments().get(0));
                }
		    	
		    	List<WorkArea> workAreas = TkServiceLocator.getWorkAreaService().getWorkAreas(taf.getSelectedDept(), new java.sql.Date(taf.getPayBeginDate().getTime()));
                UserRoles ur = TKUser.getCurrentTargetRoles();
                for(WorkArea wa : workAreas){
		        	if (ur.getApproverWorkAreas().contains(wa.getWorkArea())
		        			|| ur.getReviewerWorkAreas().contains(wa.getWorkArea())) {
		        		taf.getWorkAreaDescr().put(wa.getWorkArea(),wa.getDescription()+"("+wa.getWorkArea()+")");
		        	}
		        }
		    }
		}

	}
	
    public ActionForward gotoCurrentPayPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	

    	ApprovalForm taf = (ApprovalForm) form;
        String page = getPage(request, taf);
    	Date currentDate = TKUtils.getTimelessDate(null);
        Calendar currentPayCalendar = TkServiceLocator.getCalendarService().getCalendarByGroup(taf.getSelectedPayCalendarGroup());
        CalendarEntries payCalendarEntries = TkServiceLocator.getCalendarEntriesService().getCurrentCalendarEntriesByCalendarId(currentPayCalendar.getHrCalendarId(), currentDate);
        taf.setPayCalendarEntries(payCalendarEntries);
        taf.setSelectedCalendarYear(new SimpleDateFormat("yyyy").format(payCalendarEntries.getBeginPeriodDate()));
        taf.setSelectedPayPeriod(payCalendarEntries.getHrCalendarEntriesId());
        populateCalendarAndPayPeriodLists(request, taf);
        setupDocumentOnFormContext(request, taf, payCalendarEntries, page, null);
        return mapping.findForward("basic");
    }
    
   
    
    // Triggered by changes of calendar year drop down list, reloads the pay period list
    public ActionForward changeCalendarYear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ApprovalForm taf = (ApprovalForm) form;
    	if(!StringUtils.isEmpty(request.getParameter("selectedCY"))) {
    		taf.setSelectedCalendarYear(request.getParameter("selectedCY").toString());
    		populateCalendarAndPayPeriodLists(request, taf);
    	}
    	return mapping.findForward("basic");
    }

    // Triggered by changes of pay period drop down list, reloads the whole page based on the selected pay period
    public ActionForward changePayPeriod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      ApprovalForm taf = (ApprovalForm) form;
      String page = getPage(request, taf);
  	  if(!StringUtils.isEmpty(request.getParameter("selectedPP"))) {
  		  taf.setSelectedPayPeriod(request.getParameter("selectedPP").toString());
  		  CalendarEntries pce = TkServiceLocator.getCalendarEntriesService()
  		  	.getCalendarEntries(request.getParameter("selectedPP").toString());
  		  if(pce != null) {
  			  taf.setPayCalendarEntries(pce);
  			  setupDocumentOnFormContext(request, taf, pce, page, null);
  		  }
  	  }
  	  return mapping.findForward("basic");
	}
    // sets the CalendarYear and Pay Period lists. Should be overridden by subclasses
    protected void populateCalendarAndPayPeriodLists(HttpServletRequest request, ApprovalForm taf) {
    	
    }


}
