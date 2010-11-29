package org.kuali.hr.time.accrual;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.kuali.hr.time.util.jaxb.DateAdapter;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

@XmlAccessorType(value = XmlAccessType.NONE)
public class AccrualCategory extends PersistableBusinessObjectBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private Long laAccrualCategoryId;
	@XmlElement(required = true, nillable = false)
	private String accrualCategory;
	@XmlElement(required = true, nillable = false)
	private String descr;
	@XmlElement(required = true, nillable = false)
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date effectiveDate;
	@XmlElement
	private boolean active;

	private Timestamp timestamp;

	@SuppressWarnings("unchecked")
	@Override
	protected LinkedHashMap toStringMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAccrualCategory() {
		return accrualCategory;
	}

	public void setAccrualCategory(String accrualCategory) {
		this.accrualCategory = accrualCategory;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Long getLaAccrualCategoryId() {
		return laAccrualCategoryId;
	}

	public void setLaAccrualCategoryId(Long laAccrualCategoryId) {
		this.laAccrualCategoryId = laAccrualCategoryId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
