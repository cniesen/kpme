package edu.iu.uis.hr.tk.entity.report;

import edu.iu.uis.hr.Utility;

public class ExtractCount {

	private String status;
	private int count;

	public ExtractCount() {
		setStatus(Utility.getDefaultStringValue());
		setCount(0);
	}
	
	public ExtractCount(String status, int count) {
		setStatus(status);
		setCount(count);
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
