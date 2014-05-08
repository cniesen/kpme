package org.kuali.kpme.tklm.time.clocklog.web;

import java.util.Map;

import org.kuali.kpme.tklm.time.clocklog.ClockLog;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;
import org.kuali.rice.krad.inquiry.InquirableImpl;

public class ClockLogKradInquirableImpl extends InquirableImpl {


	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
    public ClockLog retrieveDataObject(Map fieldValues) {
		ClockLog aClockLog = (ClockLog) super.retrieveDataObject(fieldValues);
		if(aClockLog != null) {
			aClockLog.setClockedByMissedPunch(TkServiceLocator.getClockLogService().isClockLogCreatedByMissedPunch(aClockLog.getTkClockLogId()));
		}
        return aClockLog;
    }

}
