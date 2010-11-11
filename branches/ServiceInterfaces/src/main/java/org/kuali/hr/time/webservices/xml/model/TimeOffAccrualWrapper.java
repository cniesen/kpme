package org.kuali.hr.time.webservices.xml.model;

import java.util.List;

import org.joda.time.TimeOfDay;
import org.kuali.hr.time.accrual.TimeOffAccrual;

public class TimeOffAccrualWrapper {
	List<TimeOffAccrual> timeOffAccruals;

	public TimeOffAccrualWrapper() {
		
	}

	public TimeOffAccrualWrapper(List<TimeOffAccrual> timeOffAccruals) {
		
		this.timeOffAccruals = timeOffAccruals;
	}

	public List<TimeOffAccrual> getTimeOffAccruals() {
		return timeOffAccruals;
	}

	public void setTimeOffAccruals(List<TimeOffAccrual> timeOffAccruals) {
		this.timeOffAccruals = timeOffAccruals;
	}

}
