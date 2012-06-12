 package org.kuali.mobility.kpme.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class ClockEntryInfo {
	//Timestamp that represents the current time, used to seed the Current Time javascript clock
	private Timestamp currentTime;
	//Map used to show the assignments for a given user  // (key is sent back to server for Web Service 2)
	private Map<String, String> assignKeyToAssignmentDescriptions;  //this should be a select box when > 1     entry
	//  Last clock description used to populate work status
	private String lastClockLogDescription;
	//List of valid location actions based on last work status
	private List<String> clockActions;
	
	public Timestamp getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Timestamp currentTime) {
		this.currentTime = currentTime;
	}
	public Map<String, String> getAssignKeyToAssignmentDescriptions() {
		return assignKeyToAssignmentDescriptions;
	}
	public void setAssignKeyToAssignmentDescriptions(Map<String, String> assignmentKeyToAssignmentDescriptions) {
		this.assignKeyToAssignmentDescriptions = assignmentKeyToAssignmentDescriptions;
	}
	public String getLastClockLogDescription() {
		return lastClockLogDescription;
	}
	public void setLastClockLogDescription(String lastClockLogDescription) {
		this.lastClockLogDescription = lastClockLogDescription;
	}
	public List<String> getClockActions() {
		return clockActions;
	}
	public void setClockActions(List<String> clockActions) {
		this.clockActions = clockActions;
	}
	@Override
	public String toString() {
		
		return super.toString();
	}
	
	

	
}
