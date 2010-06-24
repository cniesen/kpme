package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.rule.service.ClockLocationService;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class ClockValidIpAddressLogic extends AbstractLogic implements OperationalLogic {

	public void execute(Entity entity) {
		if (!(entity instanceof HourDetail)) {
			throw new IllegalArgumentException("ClockValidIpAddressLogic's execute(Entity entity) method requires a non-null Entity of type HourDetail");
		}
		// only do this check for synchronous employees
		HourDetail hourDetail = (HourDetail) entity;
		Assignment assignment = getAssignmentService().getAssignment(hourDetail.getAssignment(), hourDetail.getHoursDetail().getDate());
		if (getAssignmentService().isSynchronousAssignment(assignment)) {
			PayCalendar payCalendar = getPayCalendarService().getPayCalendar(hourDetail.getHoursDetail().getDate());
			DocumentHeader documentHeader = getTimesheetService().getDocumentHeader(assignment.getJob().getUniversityId(), payCalendar.getPayEndDate());
			TimeBlock timeBlock = getTimesheetService().getTimeBlock(documentHeader, hourDetail);
			ClockLog beginClockLog = getTimesheetService().getClockLog(documentHeader.getUniversityId(), timeBlock.getBeginTime());
			ClockLog endClockLog = getTimesheetService().getClockLog(documentHeader.getUniversityId(), timeBlock.getEndTime());
			if (Utility.hasValue(timeBlock)) {
				boolean beginUnauthorized = Utility.hasValue(beginClockLog) && !(getClockLocationService().isAuthorizedInetAddress(documentHeader, timeBlock, assignment, beginClockLog)) ? true: false;
				boolean endUnauthorized = Utility.hasValue(endClockLog) && !(getClockLocationService().isAuthorizedInetAddress(documentHeader, timeBlock, assignment, endClockLog)) ? true: false;
				if (beginUnauthorized || endUnauthorized) {
					hourDetail.getEntityWarnings().add(new String[] { HourDetail.HOURS_DETAIL_FIELD_NAME }, getMessage(MessageKeyConstants.WARNING_UNAUTHORIZED_IP));
				}
			}
		}

	}

	public boolean isMatch() {
		return false;
	}

	public AssignmentService getAssignmentService() {
		return TKServiceLocator.getAssignmentService();
	}

	public TimesheetService getTimesheetService() {
		return TKServiceLocator.getTimesheetService();
	}

	public PayCalendarService getPayCalendarService() {
		return TKServiceLocator.getPayCalendarService();
	}

	public ClockLocationService getClockLocationService() {
		return TKServiceLocator.getClockLocationService();
	}
}
