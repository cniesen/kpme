package org.kuali.hr.time.paytype;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.hr.time.util.jaxb.TimestampAdapter;
import org.kuali.rice.core.jaxb.SqlDateAdapter;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

@XmlAccessorType(value = XmlAccessType.NONE)
public class PayType extends PersistableBusinessObjectBase {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long hrPayTypeId;
	
	@XmlElement
	private String payType;
	
	@XmlElement
	private String descr;

	@XmlElement
	private String regEarnCode;
	
	@XmlJavaTypeAdapter(value = SqlDateAdapter.class, type = Date.class)
	@XmlElement
	private Date effectiveDate;
	
	@XmlJavaTypeAdapter(value = TimestampAdapter.class, type = Timestamp.class)
	@XmlElement
	private Timestamp timestamp;
	
	@XmlElement
	private Boolean active;

	@SuppressWarnings("unchecked")
	@Override
	protected LinkedHashMap toStringMapper() {
		return null;
	}


	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

//	public String getCalendarGroup() {
//		return calendarGroup;
//	}
//
//	public void setCalendarGroup(String calendarGroup) {
//		this.calendarGroup = calendarGroup;
//	}

	public String getRegEarnCode() {
		return regEarnCode;
	}

	public void setRegEarnCode(String regEarnCode) {
		this.regEarnCode = regEarnCode;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

//	public void setHolidayCalendarGroup(String holidayCalendarGroup) {
//		this.holidayCalendarGroup = holidayCalendarGroup;
//	}
//
//	public String getHolidayCalendarGroup() {
//		return holidayCalendarGroup;
//	}
//
//	public HolidayCalendar getHolidayCalendar() {
//		return holidayCalendar;
//	}
//
//	public void setHolidayCalendar(HolidayCalendar holidayCalendar) {
//		this.holidayCalendar = holidayCalendar;
//	}
//
//	public PayCalendar getPayCalendar() {
//		return payCalendar;
//	}
//
//	public void setPayCalendar(PayCalendar payCalendar) {
//		this.payCalendar = payCalendar;
//	}


	public Long getHrPayTypeId() {
		return hrPayTypeId;
	}


	public void setHrPayTypeId(Long hrPayTypeId) {
		this.hrPayTypeId = hrPayTypeId;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}
}
