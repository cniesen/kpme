package edu.iu.uis.hr.tk.batch.web;

import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.tk.client.AdministrativeAccessAuthorization;

public class TKBatchAdminForm extends AbstractStrutsActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4858804150178570099L;
	
	private String method;
	
	private String existingJobName;
	private String existingJobGroup;
	
	private String jobName;
	private String jobClass;
	private String triggerCronValue;
	private String summary;
	
	private List jobs;
	
	public void prepare() {
		
	}

	@SuppressWarnings("unchecked")
	public List getAccessAuthorizations() {
        List accessAuthorizations = new ArrayList();
        accessAuthorizations.add(AdministrativeAccessAuthorization.class);
        return accessAuthorizations;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getTriggerCronValue() {
		return triggerCronValue;
	}

	public void setTriggerCronValue(String triggerCronValue) {
		this.triggerCronValue = triggerCronValue;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List getJobs() {
		return jobs;
	}

	public void setJobs(List jobs) {
		this.jobs = jobs;
	}

	public String getExistingJobName() {
		return existingJobName;
	}

	public void setExistingJobName(String existingJobName) {
		this.existingJobName = existingJobName;
	}

	public String getExistingJobGroup() {
		return existingJobGroup;
	}

	public void setExistingJobGroup(String existingJobGroup) {
		this.existingJobGroup = existingJobGroup;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}