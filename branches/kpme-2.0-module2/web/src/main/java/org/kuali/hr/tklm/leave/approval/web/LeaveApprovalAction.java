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
package org.kuali.hr.tklm.leave.approval.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hsqldb.lib.StringUtil;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.kuali.hr.core.ApprovalAction;
import org.kuali.hr.core.ApprovalForm;
import org.kuali.hr.core.assignment.Assignment;
import org.kuali.hr.core.calendar.Calendar;
import org.kuali.hr.core.calendar.CalendarEntry;
import org.kuali.hr.core.role.KPMERole;
import org.kuali.hr.core.service.HrServiceLocator;
import org.kuali.hr.core.workarea.WorkArea;
import org.kuali.hr.tklm.leave.calendar.LeaveCalendarDocument;
import org.kuali.hr.tklm.leave.workflow.LeaveCalendarDocumentHeader;
import org.kuali.hr.tklm.time.detail.web.ActionFormUtils;
import org.kuali.hr.tklm.time.person.TKPerson;
import org.kuali.hr.tklm.time.service.base.TkServiceLocator;
import org.kuali.hr.tklm.time.util.TKContext;
import org.kuali.hr.tklm.time.util.TkConstants;
import org.kuali.rice.krad.util.GlobalVariables;

public class LeaveApprovalAction extends ApprovalAction{
	
	public ActionForward searchResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LeaveApprovalActionForm laaf = (LeaveApprovalActionForm)form;
		
        if (StringUtils.equals("documentId", laaf.getSearchField())) {
        	LeaveCalendarDocumentHeader lcd = TkServiceLocator.getLeaveCalendarDocumentHeaderService().getDocumentHeader(laaf.getSearchTerm());
        	laaf.setSearchTerm(lcd != null ? lcd.getPrincipalId() : StringUtils.EMPTY);
        }
        
    	laaf.setSearchField("principalId");
        List<String> principalIds = new ArrayList<String>();
        principalIds.add(laaf.getSearchTerm());
        List<TKPerson> persons = HrServiceLocator.getPersonService().getPersonCollection(principalIds);
        CalendarEntry payCalendarEntry = HrServiceLocator.getCalendarEntryService().getCalendarEntry(laaf.getHrPyCalendarEntryId());
        if (persons.isEmpty()) {
        	laaf.setLeaveApprovalRows(new ArrayList<ApprovalLeaveSummaryRow>());
        	laaf.setResultSize(0);
        } else {
        	this.setApprovalTables(laaf, principalIds, request, payCalendarEntry);
        	
   	        laaf.setPayCalendarEntry(payCalendarEntry);
   	        laaf.setLeaveCalendarDates(TkServiceLocator.getLeaveSummaryService().getLeaveSummaryDates(payCalendarEntry));
        	
	        List<Assignment> assignments = HrServiceLocator.getAssignmentService().getAssignments(laaf.getSearchTerm(), payCalendarEntry.getEndPeriodFullDateTime().toLocalDate());
	        if(!assignments.isEmpty()){
	        	 for(Long wa : laaf.getWorkAreaDescr().keySet()){
	        		for (Assignment assign : assignments) {
		             	if (assign.getWorkArea().toString().equals(wa.toString())) {
		             		laaf.setSelectedWorkArea(wa.toString());
		             		break;
		             	}
	        		}
	             }
	        }
        }
 
		return mapping.findForward("basic");
	}
	
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LeaveApprovalActionForm laaf = (LeaveApprovalActionForm) form;
       
        List<ApprovalLeaveSummaryRow> lstLeaveRows = laaf.getLeaveApprovalRows();
        for (ApprovalLeaveSummaryRow ar : lstLeaveRows) {
            if (ar.isApprovable() && StringUtils.equals(ar.getSelected(), "on")) {
                String documentNumber = ar.getDocumentId();
                LeaveCalendarDocument lcd = TkServiceLocator.getLeaveCalendarService().getLeaveCalendarDocument(documentNumber);
                TkServiceLocator.getLeaveCalendarService().approveLeaveCalendar(TKContext.getPrincipalId(), lcd);
            }
        }  
        
        return mapping.findForward("basic");
    }
        
	public ActionForward selectNewDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LeaveApprovalActionForm laaf = (LeaveApprovalActionForm)form;
		laaf.setSearchField(null);
		laaf.setSearchTerm(null);
		laaf.getWorkAreaDescr().clear();
		laaf.setSelectedWorkArea("");
		
        CalendarEntry payCalendarEntry = HrServiceLocator.getCalendarEntryService().getCalendarEntry(laaf.getHrPyCalendarEntryId());
        laaf.setPayCalendarEntry(payCalendarEntry);
        laaf.setLeaveCalendarDates(TkServiceLocator.getLeaveSummaryService().getLeaveSummaryDates(payCalendarEntry));

		String principalId = GlobalVariables.getUserSession().getPrincipalId();
    	List<WorkArea> workAreaObjs = HrServiceLocator.getWorkAreaService().getWorkAreas(laaf.getSelectedDept(), LocalDate.fromDateFields(laaf.getPayBeginDate()));
        for (WorkArea workAreaObj : workAreaObjs) {
        	Long workArea = workAreaObj.getWorkArea();
        	String description = workAreaObj.getDescription();
        	
        	if (TkServiceLocator.getHRRoleService().principalHasRoleInWorkArea(principalId, KPMERole.REVIEWER.getRoleName(), workArea, new DateTime())
        			|| TkServiceLocator.getHRRoleService().principalHasRoleInWorkArea(principalId, KPMERole.APPROVER_DELEGATE.getRoleName(), workArea, new DateTime())
        			|| TkServiceLocator.getHRRoleService().principalHasRoleInWorkArea(principalId, KPMERole.APPROVER.getRoleName(), workArea, new DateTime())) {
        		laaf.getWorkAreaDescr().put(workArea, description + "(" + workArea + ")");
        	}
        }
	
        List<String> principalIds = this.getPrincipalIdsToPopulateTable(laaf);
    	this.setApprovalTables(laaf, principalIds, request, payCalendarEntry);
    	
    	this.populateCalendarAndPayPeriodLists(request, laaf);
		return mapping.findForward("basic");
	}
	
	public ActionForward selectNewWorkArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LeaveApprovalActionForm laaf = (LeaveApprovalActionForm)form;
		laaf.setSearchField(null);
		laaf.setSearchTerm(null);

	    CalendarEntry payCalendarEntry = HrServiceLocator.getCalendarEntryService().getCalendarEntry(laaf.getHrPyCalendarEntryId());
        laaf.setLeaveCalendarDates(TkServiceLocator.getLeaveSummaryService().getLeaveSummaryDates(payCalendarEntry));
   
        List<String> idList = this.getPrincipalIdsToPopulateTable(laaf);
        this.setApprovalTables(laaf, idList , request, payCalendarEntry);
        
		return mapping.findForward("basic");
	}	

	private List<String> getPrincipalIdsToPopulateTable(LeaveApprovalActionForm laaf) {
        List<String> workAreaList = new ArrayList<String>();
        if(StringUtil.isEmpty(laaf.getSelectedWorkArea())) {
        	for(Long aKey : laaf.getWorkAreaDescr().keySet()) {
        		workAreaList.add(aKey.toString());
        	}
        } else {
        	workAreaList.add(laaf.getSelectedWorkArea());
        }
        LocalDate endDate = LocalDate.fromDateFields(laaf.getPayEndDate());
        LocalDate beginDate = LocalDate.fromDateFields(laaf.getPayBeginDate());

        List<String> idList = TkServiceLocator.getLeaveApprovalService()
        		.getLeavePrincipalIdsWithSearchCriteria(workAreaList, laaf.getSelectedPayCalendarGroup(), endDate, beginDate, endDate);      
        return idList;
	}	
	
	private void setApprovalTables(LeaveApprovalActionForm laaf, List<String> principalIds, HttpServletRequest request, CalendarEntry payCalendarEntry) {
		laaf.setLeaveCalendarDates(TkServiceLocator.getLeaveSummaryService().getLeaveSummaryDates(payCalendarEntry));
		
		if (principalIds.isEmpty()) {
			laaf.setLeaveApprovalRows(new ArrayList<ApprovalLeaveSummaryRow>());
			laaf.setResultSize(0);
		} else {
			List<TKPerson> persons = HrServiceLocator.getPersonService().getPersonCollection(principalIds);
			List<ApprovalLeaveSummaryRow> approvalRows = getApprovalLeaveRows(laaf, getSubListPrincipalIds(request, persons)); 
		    
			final String sortField = request.getParameter("sortField");		    
		    if (StringUtils.equals(sortField, "Name")) {
			    final boolean sortNameAscending = Boolean.parseBoolean(request.getParameter("sortNameAscending"));
		    	Collections.sort(approvalRows, new Comparator<ApprovalLeaveSummaryRow>() {
					@Override
					public int compare(ApprovalLeaveSummaryRow row1, ApprovalLeaveSummaryRow row2) {
						if (sortNameAscending) {
							return ObjectUtils.compare(StringUtils.lowerCase(row1.getName()), StringUtils.lowerCase(row2.getName()));
						} else {
							return ObjectUtils.compare(StringUtils.lowerCase(row2.getName()), StringUtils.lowerCase(row1.getName()));
						}
					}
		    	});
		    } else if (StringUtils.equals(sortField, "DocumentID")) {
			    final boolean sortDocumentIdAscending = Boolean.parseBoolean(request.getParameter("sortDocumentIDAscending"));
		    	Collections.sort(approvalRows, new Comparator<ApprovalLeaveSummaryRow>() {
					@Override
					public int compare(ApprovalLeaveSummaryRow row1, ApprovalLeaveSummaryRow row2) {
						if (sortDocumentIdAscending) {
							return ObjectUtils.compare(NumberUtils.toInt(row1.getDocumentId()), NumberUtils.toInt(row2.getDocumentId()));
						} else {
							return ObjectUtils.compare(NumberUtils.toInt(row2.getDocumentId()), NumberUtils.toInt(row1.getDocumentId()));
						}
					}
		    	});
		    } else if (StringUtils.equals(sortField, "Status")) {
			    final boolean sortStatusIdAscending = Boolean.parseBoolean(request.getParameter("sortStatusAscending"));
		    	Collections.sort(approvalRows, new Comparator<ApprovalLeaveSummaryRow>() {
					@Override
					public int compare(ApprovalLeaveSummaryRow row1, ApprovalLeaveSummaryRow row2) {
						if (sortStatusIdAscending) {
							return ObjectUtils.compare(StringUtils.lowerCase(row1.getApprovalStatus()), StringUtils.lowerCase(row2.getApprovalStatus()));
						} else {
							return ObjectUtils.compare(StringUtils.lowerCase(row2.getApprovalStatus()), StringUtils.lowerCase(row1.getApprovalStatus()));
						}
					}
		    	});
		    }
		    
			laaf.setLeaveApprovalRows(approvalRows);
		    laaf.setResultSize(persons.size());
		}
	}
	
	@Override
	public ActionForward loadApprovalTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		ActionForward fwd = mapping.findForward("basic");
        LeaveApprovalActionForm laaf = (LeaveApprovalActionForm) form;
        LocalDate currentDate = null;
        CalendarEntry payCalendarEntry = null;
        Calendar currentPayCalendar = null;
        String page = request.getParameter((new ParamEncoder(TkConstants.APPROVAL_TABLE_ID).encodeParameterName(TableTagParameters.PARAMETER_PAGE)));


        //reset state
        if(StringUtils.isBlank(laaf.getSelectedDept())){
        	resetState(form, request);
        }

        // Set current pay calendar entries if present. Decide if the current date should be today or the end period date
        if (laaf.getHrPyCalendarEntryId() != null) {
            if(payCalendarEntry == null){
               payCalendarEntry = HrServiceLocator.getCalendarEntryService().getCalendarEntry(laaf.getHrPyCalendarEntryId());
            }
            currentDate = payCalendarEntry.getEndPeriodFullDateTime().toLocalDate();
        } else {
            currentDate = LocalDate.now();
        }
        List<Long> workAreas = TkServiceLocator.getHRRoleService().getWorkAreasForPrincipalInRole(TKContext.getPrincipalId(), KPMERole.APPROVER.getRoleName(), new DateTime(currentDate), true);
        List<String> principalIds = new ArrayList<String>();
        for (Long workArea : workAreas) {
            List<Assignment> assignments = HrServiceLocator.getAssignmentService().getActiveAssignmentsForWorkArea(workArea, currentDate);
            for (Assignment a : assignments) {
                principalIds.add(a.getPrincipalId());
            }
        }

        // Set calendar groups
        List<String> calGroups =  new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(principalIds)) {
            calGroups = TkServiceLocator.getLeaveApprovalService().getUniqueLeavePayGroupsForPrincipalIds(principalIds);
        }
        laaf.setPayCalendarGroups(calGroups);

        if (StringUtils.isBlank(laaf.getSelectedPayCalendarGroup())
                && CollectionUtils.isNotEmpty(calGroups)) {
            laaf.setSelectedPayCalendarGroup(calGroups.get(0));

        }
        
        // Set current pay calendar entries if present. Decide if the current date should be today or the end period date
        if (laaf.getHrPyCalendarEntryId() != null) {
            payCalendarEntry = HrServiceLocator.getCalendarEntryService().getCalendarEntry(laaf.getHrPyCalendarEntryId());
        } else {
            currentPayCalendar = HrServiceLocator.getCalendarService().getCalendarByGroup(laaf.getSelectedPayCalendarGroup());
            if (currentPayCalendar != null) {
                payCalendarEntry = HrServiceLocator.getCalendarEntryService().getCurrentCalendarEntryByCalendarId(currentPayCalendar.getHrCalendarId(), new DateTime(currentDate));
            }
        }
        laaf.setPayCalendarEntry(payCalendarEntry);
        
        
        if(laaf.getPayCalendarEntry() != null) {
	        populateCalendarAndPayPeriodLists(request, laaf);
        }
        setupDocumentOnFormContext(request,laaf, payCalendarEntry, page);
        return fwd;
	}

	@Override
	protected void setupDocumentOnFormContext(HttpServletRequest request,ApprovalForm form, CalendarEntry payCalendarEntry, String page) {
		super.setupDocumentOnFormContext(request, form, payCalendarEntry, page);
		LeaveApprovalActionForm laaf = (LeaveApprovalActionForm)form;

        if (payCalendarEntry != null) {
		    laaf.setLeaveCalendarDates(TkServiceLocator.getLeaveSummaryService().getLeaveSummaryDates(payCalendarEntry));
		    List<String> principalIds = this.getPrincipalIdsToPopulateTable(laaf); 
            this.setApprovalTables(laaf, principalIds, request, payCalendarEntry);
            laaf.setOnCurrentPeriod(ActionFormUtils.getOnCurrentPeriodFlag(laaf.getPayCalendarEntry()));
        }
	}
	
	public ActionForward selectNewPayCalendar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// resets the common fields for approval pages
		super.resetMainFields(form);
		LeaveApprovalActionForm laaf = (LeaveApprovalActionForm)form;
        // KPME-909
        laaf.setLeaveApprovalRows(new ArrayList<ApprovalLeaveSummaryRow>());
		
		return loadApprovalTab(mapping, form, request, response);
	}
	   
    protected List<ApprovalLeaveSummaryRow> getApprovalLeaveRows(LeaveApprovalActionForm laaf, List<TKPerson> assignmentPrincipalIds) {
        return TkServiceLocator.getLeaveApprovalService().getLeaveApprovalSummaryRows
        	(assignmentPrincipalIds, laaf.getPayCalendarEntry(), laaf.getLeaveCalendarDates());
    }
	
    public void resetState(ActionForm form, HttpServletRequest request) {
    	  LeaveApprovalActionForm laaf = (LeaveApprovalActionForm) form;
 	      String page = request.getParameter((new ParamEncoder(TkConstants.APPROVAL_TABLE_ID).encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
 	      
 	      if (StringUtils.isBlank(page)) {
 			  laaf.getDepartments().clear();
 			  laaf.getWorkAreaDescr().clear();
 			  laaf.setLeaveApprovalRows(new ArrayList<ApprovalLeaveSummaryRow>());
 			  laaf.setSelectedDept(null);
 			  laaf.setSearchField(null);
 			  laaf.setSearchTerm(null);
 	      }
	}
    
    @Override
    protected void populateCalendarAndPayPeriodLists(HttpServletRequest request, ApprovalForm taf) {
    	 LeaveApprovalActionForm laaf = (LeaveApprovalActionForm) taf;
		// set calendar year list
		Set<String> yearSet = new HashSet<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		// if selected calendar year is passed in
		if(!StringUtils.isEmpty(request.getParameter("selectedCY"))) {
			laaf.setSelectedCalendarYear(request.getParameter("selectedCY").toString());
		} else {
			laaf.setSelectedCalendarYear(sdf.format(laaf.getPayCalendarEntry().getBeginPeriodDate()));
		}
		
		List<CalendarEntry> pcListForYear = new ArrayList<CalendarEntry>();
		List<CalendarEntry> pceList =  new ArrayList<CalendarEntry>();
		pceList.addAll(TkServiceLocator.getLeaveApprovalService()
			.getAllLeavePayCalendarEntriesForApprover(TKContext.getPrincipalId(), LocalDate.now()));
		
	    for(CalendarEntry pce : pceList) {
	    	yearSet.add(sdf.format(pce.getBeginPeriodDate()));
	    	if(sdf.format(pce.getBeginPeriodDate()).equals(laaf.getSelectedCalendarYear())) {
	    		pcListForYear.add(pce);
	    	}
	    }
	    List<String> yearList = new ArrayList<String>(yearSet);
	    Collections.sort(yearList);
	    Collections.reverse(yearList);	// newest on top
	    laaf.setCalendarYears(yearList);
		
		// set pay period list contents
		if(!StringUtils.isEmpty(request.getParameter("selectedPP"))) {
			laaf.setSelectedPayPeriod(request.getParameter("selectedPP").toString());
		} else {
			laaf.setSelectedPayPeriod(laaf.getPayCalendarEntry().getHrCalendarEntryId());
			laaf.setPayPeriodsMap(ActionFormUtils.getPayPeriodsMap(pcListForYear, null));
		}
		if(laaf.getPayPeriodsMap().isEmpty()) {
		    laaf.setPayPeriodsMap(ActionFormUtils.getPayPeriodsMap(pcListForYear, null));
		}
	}

}
