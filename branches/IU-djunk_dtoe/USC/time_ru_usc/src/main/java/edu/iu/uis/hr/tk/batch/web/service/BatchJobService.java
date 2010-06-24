package edu.iu.uis.hr.tk.batch.web.service;

import java.util.List;

import edu.iu.uis.hr.tk.batch.TKBatchJobEntry;
import edu.iu.uis.hr.tk.batch.web.TKBatchJobForm;

public interface BatchJobService {
	public List<TKBatchJobEntry> getBatchJobs(TKBatchJobForm frm);
	public void updateJobStatus(TKBatchJobForm frm, String newStatus);
	public void updateIpNet(TKBatchJobForm frm, String newStatus);
	public void deleteJobEntries(TKBatchJobForm frm);
}
