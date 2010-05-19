package edu.iu.uis.hr.tk.timesheet.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.core.config.ConfigContext;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TkLoginFilter;
import edu.iu.uis.hr.client.DocumentAction;
import edu.iu.uis.hr.client.MaintenanceActionForm;
import edu.iu.uis.hr.client.StrutsActionForm;
import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.tk.Event;
import edu.iu.uis.hr.tk.timesheet.entity.ClockMode;
import edu.iu.uis.hr.tk.timesheet.entity.logic.IsReadyToApproveLogic;

public class TimesheetDocumentAction extends DocumentAction {
    public static Logger LOG = Logger.getLogger(TimesheetDocumentAction.class);
    
    public static final String CLOCK_IN_DISPATCH_PARAM_VALUE = "clockIn";
    public static final String CLOCK_OUT_DISPATCH_PARAM_VALUE = "clockOut";
    public static final String LUNCH_IN_DISPATCH_PARAM_VALUE = "lunchIn";
    public static final String LUNCH_OUT_DISPATCH_PARAM_VALUE = "lunchOut";
    public static final String BREAK_IN_DISPATCH_PARAM_VALUE = "breakIn";
    public static final String BREAK_OUT_DISPATCH_PARAM_VALUE = "breakOut";
    public static final String COPY_DISPATCH_PARAM_VALUE = "copy";
    public static final String EDIT_DISPATCH_PARAM_VALUE = "edit";
    public static final String UNLOCK_DOCUMENT_DISPATCH_PARAM_VALUE = "unlockDocument";
    public static final String HOUR_DISTRIBUTION_DISPATCH_PARAM_VALUE = "hourDistribution";
    public static final String HOUR_DISTRIBUTION_CANCEL_DISPATCH_PARAM_VALUE = "hourDistributionCancel";
    
    private static final String HOUR_DISTRIBUTION_FORWARD = "hourDistribution";
    
    private static final Map LOGIC_APPLICABLE_EVENTS = new HashMap();
    static {
        LOGIC_APPLICABLE_EVENTS.put(CLOCK_IN_DISPATCH_PARAM_VALUE, Event.CLOCK_IN_EVENT);
        LOGIC_APPLICABLE_EVENTS.put(CLOCK_OUT_DISPATCH_PARAM_VALUE, Event.CLOCK_OUT_EVENT);
        LOGIC_APPLICABLE_EVENTS.put(LUNCH_IN_DISPATCH_PARAM_VALUE, Event.LUNCH_IN_EVENT);
        LOGIC_APPLICABLE_EVENTS.put(LUNCH_OUT_DISPATCH_PARAM_VALUE, Event.LUNCH_OUT_EVENT);
        LOGIC_APPLICABLE_EVENTS.put(BREAK_IN_DISPATCH_PARAM_VALUE, Event.BREAK_IN_EVENT);
        LOGIC_APPLICABLE_EVENTS.put(BREAK_OUT_DISPATCH_PARAM_VALUE, Event.BREAK_OUT_EVENT);
        LOGIC_APPLICABLE_EVENTS.put(EDIT_DISPATCH_PARAM_VALUE, Event.EDIT_EVENT);
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(form instanceof TimesheetDocumentForm)) {
            throw new IllegalArgumentException("The execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) method of TimesheetDocumentAction expects an ActionForm of type TimesheetDocumentForm");
        }
        
        if (StringUtils.isNotEmpty(((TimesheetDocumentForm)form).getCardId())){
        	request.getSession().setAttribute(TkLoginFilter.KIOSK_CARD_USER, "true");
        }
        
        ((TimesheetDocumentForm)form).executeAccessAuthorization();
        
        if (StringUtils.isNotEmpty(request.getParameter("liteMode"))){
        	request.getSession().setAttribute("liteMode", "true");
        }
        if (StringUtils.isNotEmpty(request.getParameter("kioskUser"))){
        	request.getSession().setAttribute("kioskUser", "true");        	
        }
        if (StringUtils.isNotEmpty(request.getParameter("clearLiteMode"))) {
        	request.getSession().removeAttribute("liteMode");
        }
        ((TimesheetDocumentForm)form).setAllowTimesheetLite(StringUtils.equals((String)request.getSession().getAttribute("liteMode"),"true"));
        ((TimesheetDocumentForm)form).setKioskUser(StringUtils.equals((String)request.getSession().getAttribute("kioskUser"),"true"));
        
        Event event = (Event)LOGIC_APPLICABLE_EVENTS.get(getMethodName(mapping, form, request, response, mapping.getParameter()));
        if (event != null) {
            if (event instanceof EditEvent) {
              ((StrutsActionForm)form).setForwardWithErrors(true); //goes to Edit mode displaying errors
            }
            
            if (((StrutsActionForm)form).isErroneous()) {
                return handleErrors(mapping, form, request, response);
            }
            
            if (((StrutsActionForm)form).isWarned()) {
                return handleWarnings(mapping, form, request, response);
            }
            
        	// Auditing executeLogic for ClockOutEvent Time
            Date startTime = new Date();
            
            ((StrutsActionForm)form).executeLogic(event);

        	// Auditing executeLogic for ClockOutEvent Time
            if ( event != null && event instanceof ClockOutEvent) {
                Date endDate = new Date();
                long totalTime = (endDate.getTime() - startTime.getTime()) / 1000;
                LOG.error("Time audit. DocId: " + ((TimesheetDocumentForm)form).getTimesheetDocument().getDocumentId()  + " ExecuteLogic Processing Time: " + totalTime);
            }
            
            
            if (((StrutsActionForm)form).isErroneous()) {
                return handleErrors(mapping, form, request, response);
            }
            
            if (((StrutsActionForm)form).isWarned()) {
                return handleWarnings(mapping, form, request, response);
            }
        }
        return super.execute(mapping, form, request, response);
    }
    
    public final ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).edit();
        ActionForward forward = finish(mapping, (StrutsActionForm)form, request, response);
        ((TimesheetDocumentForm)form).getTimesheetDocument().getClock().setMode(new ClockMode(false, ((TimesheetDocumentForm)form).getTimesheetDocument().getClock()));
        return forward;
    }

    public final ActionForward clockIn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).clockIn();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }

    public final ActionForward clockOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       ((TimesheetDocumentForm)form).clockOut();
       if (((TimesheetDocumentForm)form).getTimesheetDocument().getClock().isUserDoingHourDistribution()){
           finish(mapping, (StrutsActionForm)form, request, response);
           return mapping.findForward(HOUR_DISTRIBUTION_FORWARD);  
       }
       LOG.error("Time audit. DocId: " + ((TimesheetDocumentForm)form).getTimesheetDocument().getDocumentId() + ". Starting finish method." );
       return finish(mapping, (StrutsActionForm)form, request, response);
    }

    public final ActionForward lunchIn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).lunchIn();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }

    public final ActionForward lunchOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).lunchOut();
        if (((TimesheetDocumentForm)form).getTimesheetDocument().getClock().isUserDoingHourDistribution()){
            finish(mapping, (StrutsActionForm)form, request, response);
            return mapping.findForward(HOUR_DISTRIBUTION_FORWARD);  
        }
        return finish(mapping, (StrutsActionForm)form, request, response);
    }

    public final ActionForward breakIn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).breakIn();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }

    public final ActionForward breakOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).breakOut();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }
    
    public final ActionForward unlockDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).unlockDocument();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }

    public final ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean success = false;
        success = ((TimesheetDocumentForm)form).prepareHourDetailCopy();
        ActionForward forward = finish(mapping, (StrutsActionForm)form, request, response);
        if (success) {
	        ((StrutsActionForm)form).setMode(new HourDetailCopyFormMode(true, (TimesheetDocumentForm)form));
            return mapping.findForward("hourDetailCopy");
        }
        return forward;
    }

    public final ActionForward copyHourDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).copyHourDetails();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }
    
    public final ActionForward hourDistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean success = false;
        success = ((TimesheetDocumentForm)form).hourDistribution();
        ActionForward forward = finish(mapping, (StrutsActionForm)form, request, response);
        if (!success) {
            return mapping.findForward(HOUR_DISTRIBUTION_FORWARD);
        }
        return forward;
    }

    public final ActionForward hourDistributionCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).hourDistributionCancel();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }
    
    
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((MaintenanceActionForm)form).add();
        if (((TimesheetDocumentForm)form).getTimesheetDocument().getClock().isUserDoingHourDistribution()){
           finish(mapping, (StrutsActionForm)form, request, response);
           return mapping.findForward(HOUR_DISTRIBUTION_FORWARD);
        }
        return finish(mapping, (StrutsActionForm)form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((MaintenanceActionForm)form).delete();
        if (((TimesheetDocumentForm)form).getTimesheetDocument().getClock().isUserDoingHourDistribution()){
           finish(mapping, (StrutsActionForm)form, request, response);
           return  mapping.findForward(HOUR_DISTRIBUTION_FORWARD);
        }
        return finish(mapping, (StrutsActionForm)form, request, response);
    }
    

    public final ActionForward returnToTimesheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).returnToTimesheet();
        return finish(mapping, (StrutsActionForm)form, request, response);
    }
    
    
    protected ActionForward finish(ActionMapping mapping, StrutsActionForm strutsActionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
//    	TimesheetDocumentForm timesheetDocumentForm = (TimesheetDocumentForm)strutsActionForm;
//
//    	strutsActionForm.setErroneous(false);
//        strutsActionForm.setWarned(false);
//        strutsActionForm.prepare();
//        strutsActionForm.prepareFilters();
//        strutsActionForm.executeModeAuthorization();
//        Event event = (Event)LOGIC_APPLICABLE_EVENTS.get(getMethodName(mapping, (ActionForm)strutsActionForm, request, response, mapping.getParameter()));
//        if ((event != null) && (PRE_INITIALIZED_APPLICABLE_EVENTS.containsKey(getMethodName(mapping, (ActionForm)strutsActionForm, request, response, mapping.getParameter())))) {
//            if (strutsActionForm.isErroneous()) {
//                return mapping.findForward(DEFAULT_FORWARD_NAME);
//            }
//            (strutsActionForm).executeLogic(event);
//        }
    	
    	ActionForward actionForward = super.finish(mapping, strutsActionForm, request, response);
    	if (strutsActionForm.isErroneous()) {
    		return actionForward;
    	}
    	
    	TimesheetDocumentForm timesheetDocumentForm = (TimesheetDocumentForm)strutsActionForm;
    	if (timesheetDocumentForm.isClockOnlyTimesheet() && ! timesheetDocumentForm.isIgnoreClockOnlyStatus()) {
    		if (Boolean.getBoolean(ConfigContext.getCurrentContextConfig().getProperty(Context.NO_TIMESHEET_LITE_KEY)) || 
    				request.getSession().getAttribute("liteMode")==null) {
    			return mapping.findForward(DEFAULT_FORWARD_NAME);	
    		} 
    		return mapping.findForward("clockOnly");
    	}
        return mapping.findForward(DEFAULT_FORWARD_NAME);
    }
    
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((TimesheetDocumentForm)form).save();
        if (StringUtils.equals(((TimesheetDocumentForm)form).getTimesheetDocument().getDocumentHeader().getDocumentStatus(), DocumentConstants.ROUTED)) {
	        IsReadyToApproveLogic readyToApproveLogic = new IsReadyToApproveLogic();
	        readyToApproveLogic.evaluateReadyToApprove(((TimesheetDocumentForm)form).getTimesheetDocument(), false);
        }
        return finish(mapping, (StrutsActionForm)form, request, response);
    }
    
}