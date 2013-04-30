package org.kuali.hr.pm.positionappointment.dao;

import java.util.List;

import org.joda.time.LocalDate;
import org.kuali.hr.pm.positionappointment.PositionAppointment;

public interface PositionAppointmentDao {

	public PositionAppointment getPositionAppointmentById(String pmPositionAppointmentId);

	public List<PositionAppointment> getPositionAppointmentList(String pmPositionAppointment, String institution, String campus, LocalDate asOfDate);

}
