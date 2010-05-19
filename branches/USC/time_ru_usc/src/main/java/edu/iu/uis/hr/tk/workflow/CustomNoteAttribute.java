package edu.iu.uis.hr.tk.workflow;

import org.kuali.rice.kew.dto.RouteHeaderDTO;
import org.kuali.rice.kew.notes.Note;
import org.kuali.rice.kew.web.session.UserSession;


/**
 * A custom Note attribute implemented for Timekeeping.  A user is allowed to add notes if they are in the
 * route log (past, present, or future).  A user is allowed to edit a note if they were the original author.
 * 
 * @author Eric Westfall
 */
public class CustomNoteAttribute implements	org.kuali.rice.kew.notes.CustomNoteAttribute {
	
	private RouteHeaderDTO RouteHeaderDTO;
	private UserSession userSession;
	
	public boolean isAuthorizedToAddNotes() throws Exception {
//TODO: dbb- turning this validation off to speed performance - if you can open the doc you can add a note
//		WorkflowReports workflowReports = new WorkflowReports();
//		return (workflowReports.isUserAuthenticatedByRouteLog(getRouteHeaderDTO().getRouteHeaderId(), new WorkflowIdVO(getUserSession().getWorkflowUser().getWorkflowId()), true));
		return true;
	}
	
	public boolean isAuthorizedToEditNote(Note note) throws Exception {
		 return note.getNoteAuthorWorkflowId().equalsIgnoreCase(getUserSession().getPrincipalId());
	}

	public RouteHeaderDTO getRouteHeaderDTO() {
		return RouteHeaderDTO;
	}

	public void setRouteHeaderDTO(RouteHeaderDTO RouteHeaderDTO) {
		this.RouteHeaderDTO = RouteHeaderDTO;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public RouteHeaderDTO getRouteHeaderVO() {
		return RouteHeaderDTO;
	}

	public void setRouteHeaderVO(RouteHeaderDTO arg0) {
		this.RouteHeaderDTO = RouteHeaderDTO;
		
	}

}
