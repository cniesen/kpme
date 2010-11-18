package org.kuali.hr.time.earncode;

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
public class EarnCode extends PersistableBusinessObjectBase {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	
	private Long tkEarnCodeId;	
	@XmlElement
	private String earnCode;
	@XmlElement
	private String description;
	@XmlElement
	private Boolean recordHours;
	@XmlElement
	private Boolean recordTime;
	@XmlElement
	private Boolean recordAmount;
	
	@XmlJavaTypeAdapter(value = TimestampAdapter.class, type = Timestamp.class)
	@XmlElement
	private Timestamp timestamp;
	
	@XmlJavaTypeAdapter(value = SqlDateAdapter.class, type = Date.class)
	@XmlElement
	private Date effectiveDate;
	@XmlElement
	private boolean active;

	@SuppressWarnings("unchecked")
	@Override
	protected LinkedHashMap toStringMapper() {
		return null;
	}

	public String getEarnCode() {
		return earnCode;
	}

	public void setEarnCode(String earnCode) {
		this.earnCode = earnCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getTkEarnCodeId() {
		return tkEarnCodeId;
	}

	public void setTkEarnCodeId(Long tkEarnCodeId) {
		this.tkEarnCodeId = tkEarnCodeId;
	}

	public Boolean getRecordHours() {
		return recordHours;
	}

	public void setRecordHours(Boolean recordHours) {
		this.recordHours = recordHours;
	}

	public Boolean getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Boolean recordTime) {
		this.recordTime = recordTime;
	}

	public Boolean getRecordAmount() {
		return recordAmount;
	}

	public void setRecordAmount(Boolean recordAmount) {
		this.recordAmount = recordAmount;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
