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
package org.kuali.hr.time.timesheet.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.hr.lm.accrual.AccrualCategory;
import org.kuali.hr.time.base.web.TkAction;
import org.kuali.hr.time.calendar.Calendar;
import org.kuali.hr.time.calendar.CalendarEntries;
import org.kuali.hr.time.detail.web.ActionFormUtils;
import org.kuali.hr.time.principal.PrincipalHRAttributes;
import org.kuali.hr.time.roles.TkUserRoles;
import org.kuali.hr.time.roles.UserRoles;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.timesheet.TimesheetDocument;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUser;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.workflow.TimesheetDocumentHeader;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.util.GlobalVariables;

public class TimesheetAction extends TkAction {

	private static final Logger LOG = Logger.getLogger(TimesheetAction.class);

    @Override
    protected void checkTKAuthorization(ActionForm form, String methodToCall) throws AuthorizationException {
        UserRoles roles = TkUserRoles.getUserRoles(GlobalVariables.getUserSession().getPrincipalId());
        TimesheetDocument doc = TKContext.getCurrentTimesheetDocument();

        if (!roles.isDocumentReadable(doc)) {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "TimesheetAction: docid: " + (doc == null ? "" : doc.getDocumentId()), "");
        }
    }

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TimesheetActionForm taForm = (TimesheetActionForm) form;
		String documentId = taForm.getDocumentId();
		
        if (StringUtils.equals(request.getParameter("command"), "displayDocSearchView")
        		|| StringUtils.equals(request.getParameter("command"), "displayActionListView") ) {
        	documentId = (String) request.getParameter("docId");
        }

        LOG.debug("DOCID: " + documentId);

        // Here - viewPrincipal will be the principal of the user we intend to
        // view, be it target user, backdoor or otherwise.
        String viewPrincipal = TKUser.getCurrentTargetPerson().getPrincipalId();
        Date currentDate = TKUtils.getTimelessDate(null);
		CalendarEntries payCalendarEntry = TkServiceLocator.getCalendarService().getCurrentCalendarDates(viewPrincipal,  currentDate);

        // By handling the prev/next in the execute method, we are saving one
        // fetch/construction of a TimesheetDocument. If it were broken out into
        // methods, we would first fetch the current document, and then fetch
        // the next one instead of doing it in the single action.
		TimesheetDocument td;
        if (StringUtils.isNotBlank(documentId)) {
            td = TkServiceLocator.getTimesheetService().getTimesheetDocument(documentId);
        } else {
            // Default to whatever is active for "today".
            if (payCalendarEntry == null) {
                throw new RuntimeException("No pay calendar entry for " + viewPrincipal);
            }
            td = TkServiceLocator.getTimesheetService().openTimesheetDocument(viewPrincipal, payCalendarEntry);
        }

        // Set the TKContext for the current timesheet document id.
        if (td != null) {
           setupDocumentOnFormContext(taForm, td);
        } else {
            LOG.error("Null timesheet document in TimesheetAction.");
        }
        
        List<String> warnings = new ArrayList<String>();
        
        // add warning messages based on max carry over balances for each accrual category for non-exempt leave users
        if (TkServiceLocator.getLeaveApprovalService().isActiveAssignmentFoundOnJobFlsaStatus(viewPrincipal, TkConstants.FLSA_STATUS_NON_EXEMPT, true)) {
        	PrincipalHRAttributes principalCalendar = TkServiceLocator.getPrincipalHRAttributeService().getPrincipalCalendar(viewPrincipal, payCalendarEntry.getEndPeriodDate());
        	Map<String,ArrayList<String>> eligibilities = TkServiceLocator.getBalanceTransferService().getEligibleTransfers(td.getCalendarEntry(),td.getPrincipalId());
        	boolean maxBalance = false;
        	for(Entry<String,ArrayList<String>> entry : eligibilities.entrySet()) {
        		if(!entry.getValue().isEmpty()) {
        			maxBalance = true;
        			break;
        		}
        	}
        	if(maxBalance) {
            	warnings.add("You have exceeded the balance limit for one or more accrual categories within your leave plan.");
            	warnings.add("Depending upon the rules of your institution, you may lose any leave over this limit.");
        	}
        	if (principalCalendar != null) {
	        	Calendar calendar = TkServiceLocator.getCalendarService().getCalendarByPrincipalIdAndDate(viewPrincipal, taForm.getEndPeriodDateTime(), true);
					
				if (calendar != null) {
					List<CalendarEntries> leaveCalendarEntries = TkServiceLocator.getCalendarEntriesService().getCalendarEntriesEndingBetweenBeginAndEndDate(calendar.getHrCalendarId(), taForm.getBeginPeriodDateTime(), taForm.getEndPeriodDateTime());
					
					List<AccrualCategory> accrualCategories = TkServiceLocator.getAccrualCategoryService().getActiveLeaveAccrualCategoriesForLeavePlan(principalCalendar.getLeavePlan(), new java.sql.Date(taForm.getEndPeriodDateTime().getTime()));
					for (AccrualCategory accrualCategory : accrualCategories) {
						if (TkServiceLocator.getAccrualCategoryMaxCarryOverService().exceedsAccrualCategoryMaxCarryOver(accrualCategory.getAccrualCategory(), viewPrincipal, leaveCalendarEntries, taForm.getEndPeriodDateTime())) {
							String message = "Your pending leave balance is greater than the annual max carry over for accrual category '" + accrualCategory.getAccrualCategory() + "' and upon approval, the excess balance will be lost.";
							if (!warnings.contains(message)) {
								warnings.add(message);
							}
						}
					}
				}
			}
        }
		
		taForm.setWarnings(warnings);

        // Do this at the end, so we load the document first,
        // then check security permissions via the superclass execution chain.
		return super.execute(mapping, form, request, response);
	}

    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward("basic");
    	String command = request.getParameter("command");
    	
    	if (StringUtils.equals(command, "displayDocSearchView") || StringUtils.equals(command, "displayActionListView")) {
        	String docId = (String) request.getParameter("docId");
        	TimesheetDocument timesheetDocument = TkServiceLocator.getTimesheetService().getTimesheetDocument(docId);
        	String timesheetPrincipalName = KimApiServiceLocator.getPersonService().getPerson(timesheetDocument.getPrincipalId()).getPrincipalName();
        	
        	String principalId = TKUser.getCurrentTargetPerson().getPrincipalId();
        	String principalName = KimApiServiceLocator.getPersonService().getPerson(principalId).getPrincipalName();
        	
        	StringBuilder builder = new StringBuilder();
        	if (!StringUtils.equals(principalName, timesheetPrincipalName)) {
            	if (StringUtils.equals(command, "displayDocSearchView")) {
            		builder.append("changeTargetPerson.do?methodToCall=changeTargetPerson");
            		builder.append("&documentId=");
            		builder.append(docId);
            		builder.append("&principalName=");
            		builder.append(timesheetPrincipalName);
            		builder.append("&targetUrl=TimeDetail.do");
            		builder.append("?docmentId=" + docId);
            		builder.append("&returnUrl=TimeApproval.do");
            	} else {
            		builder.append("TimeApproval.do");
            	}
        	} else {
        		builder.append("TimeDetail.do");
        		builder.append("?docmentId=" + docId);
        	}

        	forward = new ActionRedirect(builder.toString());
        }
    	
    	return forward;
    }

    protected void setupDocumentOnFormContext(TimesheetActionForm taForm, TimesheetDocument td){
    	String viewPrincipal = TKUser.getCurrentTargetPerson().getPrincipalId();
    	TKContext.setCurrentTimesheetDocumentId(td.getDocumentId());
        TKContext.setCurrentTimesheetDocument(td);
	    taForm.setTimesheetDocument(td);
	    taForm.setDocumentId(td.getDocumentId());
        TimesheetDocumentHeader prevTdh = TkServiceLocator.getTimesheetDocumentHeaderService().getPrevOrNextDocumentHeader(TkConstants.PREV_TIMESHEET, viewPrincipal);
        TimesheetDocumentHeader nextTdh = TkServiceLocator.getTimesheetDocumentHeaderService().getPrevOrNextDocumentHeader(TkConstants.NEXT_TIMESHEET, viewPrincipal);
       
        taForm.setPrevDocumentId(prevTdh != null ? prevTdh.getDocumentId() : null);
        taForm.setNextDocumentId(nextTdh != null ? nextTdh.getDocumentId() : null);
      
        taForm.setPayCalendarDates(td.getCalendarEntry());
        taForm.setOnCurrentPeriod(ActionFormUtils.getOnCurrentPeriodFlag(taForm.getPayCalendarDates()));
    }

}
