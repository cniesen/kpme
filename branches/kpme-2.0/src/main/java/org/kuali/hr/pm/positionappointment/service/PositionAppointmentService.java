package org.kuali.hr.pm.positionappointment.service;

import java.util.List;

import org.joda.time.LocalDate;
import org.kuali.hr.pm.positionappointment.PositionAppointment;

public interface PositionAppointmentService {

	/**
	 * retrieve the PositionAppointment with given id
	 * 
	 * @param pmPositionReportGroupId
	 * @return
	 */
	public PositionAppointment getPositionAppointmentById(String pmPositionAppointmentId);

	/**
	 * Get list of PositionAppointment with given group, institution, campus and
	 * effective date wild card allowed
	 * 
	 * @param positionReportGroup
	 * @param institution
	 * @param campus
	 * @param asOfDate
	 * @return
	 */
	public List<PositionAppointment> getPositionAppointmentList(String positionAppointment, String institution, String campus, LocalDate asOfDate);
}
