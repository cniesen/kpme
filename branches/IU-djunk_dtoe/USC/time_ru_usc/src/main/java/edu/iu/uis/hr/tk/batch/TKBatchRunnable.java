package edu.iu.uis.hr.tk.batch;

import java.io.Serializable;
import java.util.Date;

public interface TKBatchRunnable extends Runnable, Serializable {
	
	public String getDataId();
	
	public Long getJobId();
	public void setJobId(Long jobId);
	
	public String getJobMessage();
	
	public Long getBatchId();
	public void setBatchId(Long batchId);
	
	public void handleException(Throwable t, String processName, Date processDate, String documentId, String universityId);
}