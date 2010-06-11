package org.kuali.hr.time.clock.web;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.clocklog.ClockLog;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.rice.kns.web.struts.action.KualiAction;

public class ClockAction extends KualiAction {
    
    	private static final Logger LOG = Logger.getLogger(ClockAction.class);
    		
    	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	    String principalId = TKContext.getUser().getPrincipalId();
    	    List<Assignment> assignments = TkServiceLocator.getAssignmentService().getCurrentlyValidActiveAssignments(principalId);
    	    ((ClockActionForm)form).setAssignments(assignments);
    	 
    	    return super.execute(mapping, form, request, response);
    	}
    	
    	public ActionForward clockIn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	    LOG.info("Clock IN");
    	    String principalId = TKContext.getUser().getPrincipalId();    
    	    ClockLog cl = new ClockLog();
    	    cl.setPrincipalId(TKContext.getUser().getPrincipalId());
    	    cl.setClockAction(TkConstants.CLOCK_IN);
    	    //TODO Underlying objects actually store time in GMT, we just need to make sure
    	    // it gets to the database as GMT.
    	    cl.setClockTimestamp(Calendar.getInstance(TkConstants.GMT_TIME_ZONE));
    	    //TODO Make sure the getTimeZone() method pulls the correct values.
    	    // We're likely going to have to use Javascript and set a form value, possibly augmented by dropdown somewhere
    	    // in the application.
    	    cl.setClockTimestampTimezone(TKUtils.getTimeZone());
    	    cl.setIpAddress(request.getRemoteAddr());
    	    //TODO add job to user and put it on the clock log here
    	    cl.setJobNumber(0);
    	    //TODO grab assignment off of finished form and place data correctly
    	    cl.setWorkAreaId(0);
    	    cl.setTaskId(0);
    	    cl.setUserPrincipalId(principalId);
    	    List<Assignment> assignments = TkServiceLocator.getAssignmentService().getCurrentlyValidActiveAssignments(principalId);
    	    
    	    
    	    
    	    TkServiceLocator.getClockLogService().saveClockAction(cl);
    	    return mapping.findForward("basic");
    	}
    
    
}
