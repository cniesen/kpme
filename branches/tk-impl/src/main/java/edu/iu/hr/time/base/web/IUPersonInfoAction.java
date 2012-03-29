package edu.iu.hr.time.base.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.assignment.Assignment;
import edu.iu.hr.time.base.web.IUPersonInfoActionForm;
import org.kuali.hr.time.base.web.PersonInfoAction;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.roles.UserRoles;
import org.kuali.hr.time.service.base.TkServiceLocator;
import edu.iu.hr.time.util.IUTkConstants;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.KIMServiceLocator;

public class IUPersonInfoAction extends PersonInfoAction {



	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		IUPersonInfoActionForm personForm = (IUPersonInfoActionForm)form;
		
		UserRoles roles = TKContext.getUser().getCurrentRoles();
		for(Long waApprover : roles.getPayrollProcessorWorkAreas()){
			personForm.getPayrollProcessorWorkAreas().add(waApprover);
		}
		
		List<Assignment> lstAssign = TkServiceLocator.getAssignmentService().getAssignments(TKContext.getTargetPrincipalId(), TKUtils.getCurrentDate());
		Map<Long,List<Assignment>> jobNumberToListAssignments = new HashMap<Long,List<Assignment>>();
		
		Map<Long,List<TkRole>> workAreaToPayrollProcessor = new HashMap<Long,List<TkRole>>();
		Map<Long,List<Person>> workAreaToPayrollProcessorPerson = new HashMap<Long, List<Person>>();
		
		for(Assignment assign : lstAssign){
			List<Assignment> lstCurrJobAssign = jobNumberToListAssignments.get(assign.getJobNumber());
			if(lstCurrJobAssign == null){
				lstCurrJobAssign = new ArrayList<Assignment>();
			}
			lstCurrJobAssign.add(assign);
			jobNumberToListAssignments.put(assign.getJobNumber(), lstCurrJobAssign);

			this.assignWorkAreaToPayrollProcessor(assign.getWorkArea(), workAreaToPayrollProcessor, workAreaToPayrollProcessorPerson);

		}

		personForm.setWorkAreaToPayrollProcessorPerson(workAreaToPayrollProcessorPerson);
		return super.execute(mapping, personForm, request, response);
	}
	
	private void assignWorkAreaToPayrollProcessor(Long workArea, Map<Long,List<TkRole>> workAreaToPayrollProcessor, Map<Long,List<Person>> workAreaToPayrollProcessorPerson ){
		List<TkRole> lstPayrollProcessorRoles = TkServiceLocator.getTkRoleService().getWorkAreaRoles(workArea, IUTkConstants.ROLE_TK_PY_PROCESSOR,
				TKUtils.getCurrentDate());
		workAreaToPayrollProcessor.put(workArea, lstPayrollProcessorRoles);
		for(TkRole role : lstPayrollProcessorRoles){
			if(role.getPositionNumber() != null){
				List<Job> lstJobs = TkServiceLocator.getJobSerivce().getActiveJobsForPosition(role.getPositionNumber(), TKUtils.getCurrentDate());
				for(Job j : lstJobs){
					Person payrollProcessor = KIMServiceLocator.getPersonService().getPerson(j.getPrincipalId());
					if(payrollProcessor!=null){
						addPayrollProcessorPersonForWorkArea(workArea, payrollProcessor, workAreaToPayrollProcessorPerson);
					}
				}
			} else{
				Person payrollProcessor = KIMServiceLocator.getPersonService().getPerson(role.getPrincipalId());
				if(payrollProcessor!=null){
					addPayrollProcessorPersonForWorkArea(workArea, payrollProcessor, workAreaToPayrollProcessorPerson);
				}
			}
		}
	}
	
	private void addPayrollProcessorPersonForWorkArea(Long workArea, Person person, Map<Long,List<Person>> workAreaToPayrollProcessorPerson){
		List<Person> payrollProcessors = workAreaToPayrollProcessorPerson.get(workArea);
		if(payrollProcessors == null){
			payrollProcessors = new ArrayList<Person>();
		}
		payrollProcessors.add(person);
		workAreaToPayrollProcessorPerson.put(workArea, payrollProcessors);
	}

}
