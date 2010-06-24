package edu.iu.uis.hr.tk.batch.old;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.hr.tk.batch.TKBatchRunnable;

public class BatchJobDescription implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6334870300209212291L;
	
	private String name;
	private Timestamp startTime;
	private Timestamp endTime;
	private int totalJobs;
	private List<TKBatchRunnable> jobList = new ArrayList();
	private int batchJobIndex = 0;
	
	@SuppressWarnings("unchecked")
	public BatchJobDescription(String name, Timestamp startTime, Timestamp endTime, List<TKBatchRunnable> jobList){
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalJobs = jobList.size();
		this.jobList = jobList;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getTotalJobs() {
		return totalJobs;
	}
	public void setTotalJobs(int totalJobs) {
		this.totalJobs = totalJobs;
	}

	public List<TKBatchRunnable> getJobList() {
		return jobList;
	}

	public void setJobList(List<TKBatchRunnable> jobList) {
		this.jobList = jobList;
	}

	public int getBatchJobIndex() {
		return batchJobIndex;
	}

	public void setBatchJobIndex(int batchJobIndex) {
		this.batchJobIndex = batchJobIndex;
	}
}
