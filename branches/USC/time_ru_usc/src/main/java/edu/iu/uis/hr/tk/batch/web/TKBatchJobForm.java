package edu.iu.uis.hr.tk.batch.web;

import java.util.List;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.tk.batch.TKBatchJobEntry;

public class TKBatchJobForm extends AbstractStrutsActionForm {

	private static final long serialVersionUID = -6270514140704368840L;

	private String method;
	private List<TKBatchJobEntry> paginatedList;
	private String tkJobId;
	private String ipNumber;
	private String dataId;
	private String tkBatchJobId;
	private String jobStatus;
	
	private String newJobStatus;
	private String newIpNet;
	
	public void prepare() {
		// TODO Auto-generated method stub

	}

	public List getAccessAuthorizations() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public List<TKBatchJobEntry> getBatchJobs() {
		return paginatedList;
	}

	public void setBatchJobs(List<TKBatchJobEntry> paginatedList) {
		this.paginatedList = paginatedList;
	}

	public List<TKBatchJobEntry>  getPaginatedList() {
		return paginatedList;
	}

	public void setPaginatedList(List<TKBatchJobEntry> paginatedList) {
		this.paginatedList = paginatedList;
	}

	public String getTkJobId() {
		return tkJobId;
	}

	public void setTkJobId(String tkJobId) {
		this.tkJobId = tkJobId;
	}

	public String getIpNumber() {
		return ipNumber;
	}

	public void setIpNumber(String ipNumber) {
		this.ipNumber = ipNumber;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTkBatchJobId() {
		return tkBatchJobId;
	}

	public void setTkBatchJobId(String tkBatchJobId) {
		this.tkBatchJobId = tkBatchJobId;
	}

	public String getNewJobStatus() {
		return newJobStatus;
	}

	public void setNewJobStatus(String newJobStatus) {
		this.newJobStatus = newJobStatus;
	}

	public String getNewIpNet() {
		return newIpNet;
	}

	public void setNewIpNet(String newIpNet) {
		this.newIpNet = newIpNet;
	}


}
