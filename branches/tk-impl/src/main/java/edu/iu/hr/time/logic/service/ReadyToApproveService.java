package edu.iu.hr.time.logic.service;

import org.kuali.hr.time.timesheet.TimesheetDocument;

public interface ReadyToApproveService {
	public boolean isReadyToApprove(String documentId);
	public boolean isReadyToApprove(TimesheetDocument timesheetDocument);
}
