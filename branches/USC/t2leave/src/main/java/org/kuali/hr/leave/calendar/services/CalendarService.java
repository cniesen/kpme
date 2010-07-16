package org.kuali.hr.leave.calendar.services;

import java.math.BigDecimal;
import java.util.Date;

import org.kuali.hr.leave.calendar.Month;
import org.kuali.hr.leave.util.T2User;

public interface CalendarService {

	public Month getMonth(T2User employee, Date dayInMonth);
	
	// TODO - put it back later...
	
	// TODO public void addRange(Month month, Date fromDate, Date toDate, BigDecimal hours, Boolean applyToYTDUsed, String leaveCodeKey, Boolean isWeekendIncluded, EptoUser employee, String description, boolean accrualGenerated, boolean validateLeaveCode);
	
	// TODO public List<Ledger> createDateRange(Month month, Date fromDate, Date toDate, BigDecimal hours, Boolean applyToYTDUsed, LeaveCode leaveCode, Boolean isWeekendIncluded, EptoUser employee, String description,
	// TODO		boolean accrualGenerated, Long scheduledPerstId);
	
	public void insertDocumentHeaderOnMonth(Month month, String supervisorId, String emplid);

}
