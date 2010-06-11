package org.kuali.hr.time.clock.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.time.assignment.Assignment;

public class ClockActionForm extends ActionForm {

    /**
     * 
     */
    private static final long serialVersionUID = -3843074202863670372L;
    
    private List<Assignment> assignments;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
	super.reset(mapping, request);
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	return super.validate(mapping, request);
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    
}
