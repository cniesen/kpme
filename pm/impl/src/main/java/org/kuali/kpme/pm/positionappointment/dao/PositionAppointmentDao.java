/**
 * Copyright 2004-2015 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.pm.positionappointment.dao;

import java.util.List;

import org.joda.time.LocalDate;
import org.kuali.kpme.pm.positionappointment.PositionAppointmentBo;

public interface PositionAppointmentDao {

	public PositionAppointmentBo getPositionAppointmentById(String pmPositionAppointmentId);

	public List<PositionAppointmentBo> getPositionAppointmentList(String pmPositionAppointment, String description, String groupKeyCode, 
			LocalDate fromEffdt, LocalDate toEffdt, String active, String showHistory);
	
	public List<PositionAppointmentBo> getPositionAppointmentList(String pmPositionAppointment, String groupKeyCode, LocalDate asOfDate);
}
