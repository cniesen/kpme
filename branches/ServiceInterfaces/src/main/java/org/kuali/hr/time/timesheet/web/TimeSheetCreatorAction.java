package org.kuali.hr.time.timesheet.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.base.web.TkAction;
import org.kuali.hr.time.paycalendar.PayCalendarEntries;
import org.kuali.hr.time.paycalendar.service.PayCalendarDatesService;
import org.kuali.hr.time.paycalendar.service.PayCalendarService;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.timesheet.TimesheetDocument;
import org.kuali.hr.time.timesheet.service.TimesheetService;
import org.kuali.hr.time.workflow.TimesheetDocumentHeader;


public class TimeSheetCreatorAction extends TkAction{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		ActionForward forward = super.execute(mapping, form, request, response);
		return forward;
	
	}
	
	public ActionForward createTimeSheetForActiveUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		TimeSheetCreatorActionForm timeSheetCreatorActionForm =(TimeSheetCreatorActionForm) form;
		timeSheetCreatorActionForm.setMessage("Created.");
		
		Criteria crit = new Criteria();
		crit.addEqualTo("active", true);
		Query query = QueryFactory.newQuery(Assignment.class, crit);
		List<Assignment> assignments = (List<Assignment>) PersistenceBrokerFactory.defaultPersistenceBroker().getCollectionByQuery(query);
		System.out.println("~~Size Of Assignment : "+assignments.size());
		
		Set<String> activePrincipals = new HashSet<String>();		
		
		for(Assignment assignment:assignments){
			activePrincipals.add(assignment.getPrincipalId()); 
		}
		TimesheetService service = TkServiceLocator.getTimesheetService();
		for(String principal:activePrincipals){			
			PayCalendarService payCalendarService = TkServiceLocator.getPayCalendarSerivce();			
			PayCalendarEntries payCalendarEntries =payCalendarService.getCurrentPayCalendarDates(principal, new java.sql.Date(new Date().getTime()));			
			service.openTimesheetDocument(principal, payCalendarEntries);			
			
		}
		  
		
		
		return mapping.findForward("success");	
	}

}
