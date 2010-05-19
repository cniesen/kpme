package edu.iu.uis.hr.tk.batch.rule;

import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.tk.batch.web.TKBatchAdminForm;
import edu.iu.uis.hr.tk.util.TkConstants;


public class TKBatchAdminRule {
	
	public boolean validateAddJob (){
			return StringUtils.isNotEmpty(getBatchAdminForm().getJobClass()) 
			&&  StringUtils.isNotEmpty(getBatchAdminForm().getJobName()) 
			&&  StringUtils.isNotEmpty(getBatchAdminForm().getTriggerCronValue());
	}
	
	public boolean validateJobAction (){
		return StringUtils.isNotEmpty(getBatchAdminForm().getExistingJobGroup()) 
		&&  StringUtils.isNotEmpty(getBatchAdminForm().getExistingJobName());
	}	
	
	public boolean doesJobExist() throws Exception{
		return (getScheduler().getJobDetail(getBatchAdminForm().getJobName(), TkConstants.BatchWebConstants.SCHEDULED_JOB_GROUP) != null);
	}
	
	private TKBatchAdminForm getBatchAdminForm() {
		return (TKBatchAdminForm) Context.getForm();
	}
	
	private Scheduler getScheduler() {
		return (Scheduler) Context.getApplicationContext().getBean("ksbScheduler");
	}	
}
