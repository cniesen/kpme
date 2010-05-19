package edu.iu.uis.hr.tk.batch;

public class TKBatchJobEntry {
	private String tkJobId;
	private String dataId;
	private String ipNet;
	private Long totalTime;
	private String tkBatchJobId;
	private String jobStatus;
	private String jobType;
	private String jobMessage;
	private String exception;

	public String getTkJobId() {
		return tkJobId;
	}

	public void setTkJobId(String tkJobId) {
		this.tkJobId = tkJobId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getIpNet() {
		return ipNet;
	}

	public void setIpNet(String ipNet) {
		this.ipNet = ipNet;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	public String getTkBatchJobId() {
		return tkBatchJobId;
	}

	public void setTkBatchJobId(String tkBatchJobId) {
		this.tkBatchJobId = tkBatchJobId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobMessage() {
		return jobMessage;
	}

	public void setJobMessage(String jobMessage) {
		this.jobMessage = jobMessage;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
