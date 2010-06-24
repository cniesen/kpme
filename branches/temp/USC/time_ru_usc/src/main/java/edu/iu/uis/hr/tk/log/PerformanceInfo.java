package edu.iu.uis.hr.tk.log;

import java.util.Date;

public class PerformanceInfo {
	private long timeTaken;
	private Date timeDateOccured;
	private String clazz;
	private String method;
	
	public PerformanceInfo(long timeTaken, Date timeDateOccured, String clazzName, String method){
		this.timeTaken = timeTaken;
		this.timeDateOccured = timeDateOccured;
		this.clazz = clazzName;
		this.method = method;
	}
	
	public long getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}
	public Date getTimeDateOccured() {
		return timeDateOccured;
	}
	public void setTimeDateOccured(Date timeDateOccured) {
		this.timeDateOccured = timeDateOccured;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
