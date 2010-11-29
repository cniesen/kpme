package org.kuali.hr.time.accrual;

import java.sql.Date;
import java.util.LinkedHashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.kuali.hr.time.util.jaxb.DateAdapter;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

@XmlAccessorType(value = XmlAccessType.NONE)
public class TimeOffAccrual extends PersistableBusinessObjectBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private Long laAccrualId;
	
	@XmlElement(required=true, nillable=false)
	private String principalId;
	
	@XmlElement(required=true, nillable=false)
	private String accrualCategory;
	
	@XmlElement(required=true, nillable=false)
    @XmlJavaTypeAdapter(DateAdapter.class)
	private Date effectiveDate;
	
	@XmlElement(required=true, nillable=false)
	private Long hoursAccrued;
	
	@XmlElement(required=true, nillable=false)
	private Long hoursTaken;
	
	@XmlElement(required=true, nillable=false)
	private Long hoursAdjust;

	private AccrualCategory accrualCategoryObj;

	private Person principal;

	@SuppressWarnings("unchecked")
	@Override
	protected LinkedHashMap toStringMapper() {
		return null;
	}


	public String getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	public String getAccrualCategory() {		
		return accrualCategory;
	}

	public void setAccrualCategory(String accrualCategory) {
		this.accrualCategory = accrualCategory;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	
	public Long getHoursAccrued() {
		return hoursAccrued;
	}


	public void setHoursAccrued(Long hoursAccrued) {
		this.hoursAccrued = hoursAccrued;
	}


	public Long getHoursTaken() {
		return hoursTaken;
	}


	public void setHoursTaken(Long hoursTaken) {
		this.hoursTaken = hoursTaken;
	}


	public Long getHoursAdjust() {
		return hoursAdjust;
	}


	public void setHoursAdjust(Long hoursAdjust) {
		this.hoursAdjust = hoursAdjust;
	}


	public void setAccrualCategoryObj(AccrualCategory accrualCategoryObj) {
		this.accrualCategoryObj = accrualCategoryObj;
	}

	public AccrualCategory getAccrualCategoryObj() {
		return accrualCategoryObj;
	}

	public void setPrincipal(Person principal) {
		this.principal = principal;
	}

	public Person getPrincipal() {
		return principal;
	}


	public Long getLaAccrualId() {
		return laAccrualId;
	}


	public void setLaAccrualId(Long laAccrualId) {
		this.laAccrualId = laAccrualId;
	}

}
