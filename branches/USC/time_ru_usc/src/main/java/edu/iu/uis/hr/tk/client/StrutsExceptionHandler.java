package edu.iu.uis.hr.tk.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ExceptionConfig;

import edu.iu.uis.hr.ApplicationException;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ExceptionAdapter;
import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.client.IncidentReportForm;
import edu.iu.uis.hr.client.Utility;
import edu.iu.uis.hr.entity.Incident;
import edu.iu.uis.hr.tk.timesheet.client.TimesheetDocumentForm;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class StrutsExceptionHandler extends edu.iu.uis.hr.client.StrutsExceptionHandler {
    private static Logger LOG = Logger.getLogger(StrutsExceptionHandler.class);

    public ActionForward execute(Exception exception, ExceptionConfig exceptionConfig, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = null;
        String docId = null;
        try {
        	if (request.getRequestURI().lastIndexOf(AbstractStrutsActionForm.getPageBaseUrl(IncidentReportForm.class)) < 0) {
	            errorMessage = exception.getMessage();
				try { //adding DocId to email message sent when there is an exception
					docId = " Doc Id: " + ((TimesheetDocumentForm) form).getTimesheetDocument().getDocumentId();
				} catch (Exception e) {
					docId = "";
				}
	            if (exception instanceof ExceptionAdapter) {
	                LOG.error("Caught ExceptionAdapter", exception);
	                processSystemLevelError(GENERIC_SYSTEM_ERROR_MESSAGE + docId, ((ExceptionAdapter)exception).getOriginalException(), ((ExceptionAdapter)exception).getLogger(), request, response);
	            } else if (exception instanceof ApplicationException) {
	                processSystemLevelError(((ApplicationException)exception).getMessage() + docId, exception, ((ApplicationException)exception).getLogger(), request, response);
	                LOG.error("Caught ApplicationException", exception);
	            } else {
	                processSystemLevelError(GENERIC_SYSTEM_ERROR_MESSAGE + docId, exception, LOG, request, response);
	                LOG.error("Caught unknown exception type", exception);
	            }
		        if (!response.isCommitted()) {
		        	TimesheetDocument timesheetDocument = null;
					try {
						timesheetDocument = ((TimesheetDocumentForm) form).getTimesheetDocument();
					} catch (Exception e) {
					}        	
					String inputPage = edu.iu.uis.hr.Utility.splitCamelCappedString(request.getRequestURI().substring((request.getRequestURI().lastIndexOf(edu.iu.uis.hr.Utility.FORWARD_SLASH) + 1)));
		            if (inputPage.lastIndexOf(edu.iu.uis.hr.Utility.PERIOD) > -1) {
		                inputPage = inputPage.substring(0, inputPage.lastIndexOf(edu.iu.uis.hr.Utility.PERIOD));
		            }
		            StringBuffer path = new StringBuffer(Context.getLookupBaseUrl()).append(AbstractStrutsActionForm.getPageLoadUrl(IncidentReportForm.class));
		            path.append(Utility.getParameterQueryString(Incident.APPLICATION_CODE_PARAMETER, Context.getApplicationCode()));
		            path.append(Utility.getParameterQueryString(Incident.INPUT_PAGE_PARAMETER, inputPage));
		            path.append(Utility.getParameterQueryString(Incident.PROBLEM_TYPE_PARAMETER, ERROR));
		            if (edu.iu.uis.hr.entity.logic.Utility.hasValue(timesheetDocument)) {
		                path.append(Utility.getParameterQueryString(Incident.DOCUMENT_ID_PARAMETER, timesheetDocument.getDocumentId()));
		//                path.append(Utility.getParameterQueryString(Incident.DEPARTMENT_ID_PARAMETER, timesheetDocument.get
		//                path.append(Utility.getParameterQueryString(Incident.WORK_AREA_PARAMETER, timesheetDocument.getDocumentId()));
		            } else {
		            	path.append(Utility.getParameterQueryString(Incident.DOCUMENT_ID_PARAMETER, "NO DOC ID"));
		            }
		            if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(errorMessage)) {
		            	errorMessage = GENERIC_SYSTEM_ERROR_MESSAGE;
		            }
		            path.append(Utility.getParameterQueryString(Incident.ERROR_MESSAGE_PARAMETER, errorMessage));
		            
		            LOG.debug("Redirecting to path: " + path);
		            return new ActionForward(path.toString(), true);
		        } else {
	                LOG.error("Ignoring request that was already committed");
		            return null;
		        }
        	} else {
                LOG.error("Ignoring request coming from IncidentReportForm");
	            return null;
        	}
        } catch (Throwable t) {
            LOG.error("Caught exception during exception handling ", t);
            return null;
        }	        
   }
}