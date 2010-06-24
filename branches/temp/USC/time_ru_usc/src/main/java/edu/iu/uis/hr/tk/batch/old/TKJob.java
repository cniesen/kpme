package edu.iu.uis.hr.tk.batch.old;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public class TKJob {
	private JobDetail jobDetail;
	private List<Trigger> triggers;
	
	public TKJob(JobDetail jobDetail, List<Trigger> triggers){
		setJobDetail(jobDetail);
		setTriggers(triggers);
	}
	
	public JobDetail getJobDetail() {
		return jobDetail;
	}
	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public List<Trigger> getTriggers() {
		return triggers;
	}

	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
	}

}
