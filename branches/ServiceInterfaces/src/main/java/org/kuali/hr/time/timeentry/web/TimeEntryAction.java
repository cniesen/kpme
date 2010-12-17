package org.kuali.hr.time.timeentry.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.assignment.service.AssignmentService;
import org.kuali.hr.time.base.web.TkAction;
import org.kuali.hr.time.paycalendar.PayCalendarEntries;
import org.kuali.hr.time.principal.calendar.service.PrincipalCalendarService;
import org.kuali.hr.time.timeentry.web.TimeEntryForm.Note;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUtils;

public class TimeEntryAction extends TkAction {

	/**
	 * gets loaded when we first bring up the page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward docHandler(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimeEntryForm timeEntryForm = (TimeEntryForm) form;
		timeEntryForm.setTimeCaptureRows(new ArrayList<TimeCapture>());
		// get the current pay calendar
		PayCalendarEntries payCalendarEntry = SpringContext.getBean(
				PrincipalCalendarService.class)
				.getPayCalendarForCurrentPrincipalAndCurrentDate(
						TKContext.getPrincipalId(),
						new Date(new java.util.Date().getTime()));
		// set it for info
		timeEntryForm.setPayCalendarEntry(payCalendarEntry);
		// create the TimeCapture Objects
		timeEntryForm.getTimeCaptureRows().add(
				new TimeCapture(payCalendarEntry.getBeginPeriodDateTime()));

		// get the assingments
		List<Assignment> assignments = SpringContext.getBean(
				AssignmentService.class).getAssignments(
				TKContext.getPrincipalId(),
				new Date(new java.util.Date().getTime()));
		for (Assignment assignment : assignments)
			assignment.setDescription(TKUtils.formatAssignmentDescription(
					assignment).values().iterator().next());
		// set them in the list
		timeEntryForm.setAssignments(assignments);

		return mapping.findForward("basic");
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addTimeCapture(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimeEntryForm timeEntryForm = (TimeEntryForm) form;
		timeEntryForm.getTimeCaptureRows().add(
				new TimeCapture(timeEntryForm.getPayCalendarEntry().getBeginPeriodDateTime()));

		return mapping.findForward("basic");
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addNote(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimeEntryForm timeEntryForm = (TimeEntryForm) form;
		Note note = timeEntryForm.Note();
		note.setCreateDate(new java.util.Date());
		note.setCreator(timeEntryForm.getUser().getActualPerson());
		note.setText(timeEntryForm.getNewNote());
		
		timeEntryForm.getNotes().add(note);
		
		timeEntryForm.setNewNote("");
		return mapping.findForward("basic");
	}
}
