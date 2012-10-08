/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.hr.time.principal;

import java.sql.Date;
import java.sql.Timestamp;

import org.kuali.hr.core.KPMEConstants;
import org.kuali.hr.time.HrBusinessObject;
import org.kuali.hr.time.holidaycalendar.HolidayCalendar;
import org.kuali.hr.time.calendar.Calendar;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;

public class PrincipalHRAttributes extends HrBusinessObject {
    public static final String CACHE_NAME = KPMEConstants.APPLICATION_NAMESPACE_CODE + "/" + "PrincipalHRAttributes";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hrPrincipalAttributeId;
	private String principalId;
	private String payCalendar;
	private Date serviceDate;
	private boolean fmlaEligible;
	private boolean workmansCompEligible;
	private String holidayCalendarGroup;
	private String timezone;
	// KPME-1268 Kagata added recordTime and recordLeave variables
	// KPME-1676 
//	private String recordTime;
//	private String recordLeave;
	
	private Calendar calendar;
	private Person person;

    public HolidayCalendar getHolidayCalendar() {
        return holidayCalendar;
    }

    public void setHolidayCalendar(HolidayCalendar holidayCalendar) {
        this.holidayCalendar = holidayCalendar;
    }

    private HolidayCalendar holidayCalendar;

	public String getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
		person = KimApiServiceLocator.getPersonService().getPerson(this.principalId);
	}

	public String getName() {
		 if (person == null) {
	            person = KimApiServiceLocator.getPersonService().getPerson(this.principalId);
	    }
	    return (person != null) ? person.getName() : "";
	}

	public String getPayCalendar() {
		return payCalendar;
	}

	public void setPayCalendar(String payCalendar) {
		this.payCalendar = payCalendar;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public boolean isFmlaEligible() {
		return fmlaEligible;
	}

	public void setFmlaEligible(boolean fmlaEligible) {
		this.fmlaEligible = fmlaEligible;
	}

	public boolean isWorkmansCompEligible() {
		return workmansCompEligible;
	}

	public void setWorkmansCompEligible(boolean workmansCompEligible) {
		this.workmansCompEligible = workmansCompEligible;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getHolidayCalendarGroup() {
		return holidayCalendarGroup;
	}

	public void setHolidayCalendarGroup(String holidayCalendarGroup) {
		this.holidayCalendarGroup = holidayCalendarGroup;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	protected String getUniqueKey() {
		return principalId + "_" + payCalendar == null ? "" : payCalendar;
	}

	@Override
	public void setId(String id) {
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHrPrincipalAttributeId() {
		return hrPrincipalAttributeId;
	}

	public void setHrPrincipalAttributeId(String hrPrincipalAttributeId) {
		this.hrPrincipalAttributeId = hrPrincipalAttributeId;
	}
}
