package org.kuali.mobility.kpme.entity;

import java.sql.Timestamp;

public class ClockEntryInfoModel extends ClockEntryInfo {

	private String workStatus;
	
	private String lastClockEntry;
	
	private String clockAction;
	
	public ClockEntryInfoModel() {
		super();
	}
	
	public ClockEntryInfoModel(ClockEntryInfo clockEntryInfo) {
		super();
		this.setCurrentTime(clockEntryInfo.getCurrentTime());
		this.setLastClockLogDescription(clockEntryInfo.getLastClockLogDescription());
		this.setAssignKeyToAssignmentDescriptions(clockEntryInfo.getAssignKeyToAssignmentDescriptions());
		this.setClockActions(clockEntryInfo.getClockActions());
		
		if(getLastClockLogDescription()!= null && getLastClockLogDescription().indexOf("since") > 0){
			this.setWorkStatus(getLastClockLogDescription().split("since : ")[0]);
			this.setLastClockEntry(getLastClockLogDescription().split("since : ")[1]);
		}
		if(getClockActions()!=null && getClockActions().size()>0){
			this.setClockAction(getClockActions().get(0));
		}
		if(getAssignKeyToAssignmentDescriptions()!=null){
    		getAssignKeyToAssignmentDescriptions().put("", "Select Assignment");
    	}
		
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getLastClockEntry() {
		return lastClockEntry;
	}

	public void setLastClockEntry(String lastClockEntry) {
		this.lastClockEntry = lastClockEntry;
	}

	public String getClockAction() {
		return clockAction;
	}

	public void setClockAction(String clockAction) {
		this.clockAction = clockAction;
	}
	 
	
}
