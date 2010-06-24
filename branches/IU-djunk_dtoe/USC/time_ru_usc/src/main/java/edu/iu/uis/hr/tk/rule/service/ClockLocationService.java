package edu.iu.uis.hr.tk.rule.service;

import java.math.BigDecimal;
import java.util.List;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRule;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;

public interface ClockLocationService {

	public List<ClockLocationRule> getClockLocation(String emplid, BigDecimal emplRcd, String dept, BigDecimal workArea);
	
	public boolean isAuthorizedInetAddress(DocumentHeader documentHeader, TimeBlock timeBlock, Assignment assignment, ClockLog clockLog);
	
	public TypedPersistentMaintainedEntityList getClockLogs(String universityId);
}
