package edu.iu.uis.hr.tk.batch.web;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.client.StrutsActionForm;
import edu.iu.uis.hr.tk.batch.old.TKJob;
import edu.iu.uis.hr.tk.batch.rule.TKBatchAdminRule;
import edu.iu.uis.hr.tk.util.TkConstants;

public class TKBatchAdminAction extends DispatchAction {

	private static TKBatchAdminRule tkBatchAdminRule = new TKBatchAdminRule();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		((StrutsActionForm)form).executeAccessAuthorization();
		Context.setForm(form);
		super.execute(mapping, form, request, response);
		updateBatchState((TKBatchAdminForm) form);
		return mapping.findForward("default");
	}

	
	
	@Override
	protected String getMethodName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String parameter) throws Exception {
		String methodName = super.getMethodName(mapping, form, request, response, parameter);
		return (StringUtils.isEmpty(methodName) ? "defaultAction" : methodName);
	}



	public ActionForward defaultAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//nothing
		return mapping.findForward("default");
	}
	
	public ActionForward scheduleJob(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TKBatchAdminForm batchAdminForm = (TKBatchAdminForm) form;
		
		if (tkBatchAdminRule.validateAddJob()){
			if (tkBatchAdminRule.doesJobExist()){
				getScheduler().deleteJob(batchAdminForm.getJobName(), TkConstants.BatchWebConstants.SCHEDULED_JOB_GROUP);
			}
			CronTrigger trigger = new CronTrigger();
			JobDetail job = new JobDetail(batchAdminForm.getJobName(),TkConstants.BatchWebConstants.SCHEDULED_JOB_GROUP,Class.forName(batchAdminForm.getJobClass()));				
			trigger.setCronExpression(batchAdminForm.getTriggerCronValue());
			trigger.setName(batchAdminForm.getJobName()+ TkConstants.BatchWebConstants.SCHEDULED_TRIGGER);
			trigger.setGroup(TkConstants.BatchWebConstants.SCHEDULED_TRIGGER_GROUP);
			getScheduler().scheduleJob(job, trigger);			
		}
		return mapping.findForward("default");
	}
	
	public ActionForward removeJob(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TKBatchAdminForm batchAdminForm = (TKBatchAdminForm) form;
		if (tkBatchAdminRule.validateJobAction()){	
			getScheduler().deleteJob(batchAdminForm.getExistingJobName(), batchAdminForm.getExistingJobGroup());			
		}
		return mapping.findForward("default");
	}

	public ActionForward runJob(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TKBatchAdminForm batchAdminForm = (TKBatchAdminForm) form;
		if (tkBatchAdminRule.validateJobAction()){		
			JobDetail jobToSchedule = getScheduler().getJobDetail(batchAdminForm.getExistingJobName(), batchAdminForm.getExistingJobGroup());
			jobToSchedule.setGroup(TkConstants.BatchWebConstants.RUN_NOW__JOB_GROUP);
			SimpleTrigger trigger = new SimpleTrigger(batchAdminForm.getExistingJobName()+TkConstants.BatchWebConstants.RUN_NOW_TRIGGER, TkConstants.BatchWebConstants.RUN_NOW_TRIGGER_GROUP);
			getScheduler().scheduleJob(jobToSchedule, trigger);
		}
		return mapping.findForward("default");
	}
	
	private void updateBatchState(TKBatchAdminForm batchAdminForm) throws Exception {		
		batchAdminForm.setJobs(new ArrayList());
		for (String jobGroup : Arrays.asList(getScheduler().getJobGroupNames())){
			for (String job : Arrays.asList(getScheduler().getJobNames(jobGroup))){
				batchAdminForm.getJobs().add(new TKJob(getScheduler().getJobDetail(job, jobGroup), Arrays.asList(getScheduler().getTriggersOfJob(job, jobGroup))));
			}
		}
		batchAdminForm.setSummary(StringUtils.replace(getScheduler().getMetaData().getSummary(), "\n", "\n<br>"));
	}
	
	private Scheduler getScheduler(){
		return (Scheduler) Context.getApplicationContext().getBean("ksbScheduler");
	}
	
}
