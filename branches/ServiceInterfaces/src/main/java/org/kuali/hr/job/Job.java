package org.kuali.hr.job;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.kuali.hr.time.paytype.PayType;
import org.kuali.hr.time.util.jaxb.DateAdapter;
import org.kuali.rice.core.jaxb.SqlDateAdapter;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

@XmlAccessorType(value = XmlAccessType.NONE)
public class Job extends PersistableBusinessObjectBase {

	private static final long serialVersionUID = 1L;

	@XmlElement
	private String location;
	@XmlElement
	private String hrPayType;
	@XmlElement
	private Boolean fte;
	@XmlElement
	private String payGrade;
	@XmlElement
	private BigDecimal standardHours;

	private Long hrJobId;
	@XmlElement
	private String principalId;
	@XmlElement
	private Long jobNumber;

	@XmlJavaTypeAdapter(value = SqlDateAdapter.class, type = Date.class)
	@XmlElement(required = true, nillable = false)
	private Date effectiveDate;
	@XmlElement(required = true, nillable = false)
	private String dept;
	@XmlElement
	private String tkSalGroup;
	@XmlElement
	private Boolean active;

	private Timestamp timestamp;

	private PayType payType;
	private BigDecimal compRate = new BigDecimal(0);

	@SuppressWarnings("unchecked")
	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap<String, Object> toStringMap = new LinkedHashMap<String, Object>();
		toStringMap.put("jobId", hrJobId);
		toStringMap.put("principalId", principalId);
		toStringMap.put("tkSalGroup", tkSalGroup);

		return toStringMap;
	}

	public Boolean getFte() {
		return fte;
	}

	public void setFte(Boolean fte) {
		this.fte = fte;
	}

	public String getPayGrade() {
		return payGrade;
	}

	public void setPayGrade(String payGrade) {
		this.payGrade = payGrade;
	}

	public BigDecimal getStandardHours() {
		return standardHours;
	}

	public void setStandardHours(BigDecimal standardHours) {
		this.standardHours = standardHours;
	}

	public String getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	public Long getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(Long jobNumber) {
		this.jobNumber = jobNumber;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public String getHrPayType() {
		return hrPayType;
	}

	public void setHrPayType(String hrPayType) {
		this.hrPayType = hrPayType;
	}

	public Long getHrJobId() {
		return hrJobId;
	}

	public void setHrJobId(Long hrJobId) {
		this.hrJobId = hrJobId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getTkSalGroup() {
		return tkSalGroup;
	}

	public void setTkSalGroup(String tkSalGroup) {
		this.tkSalGroup = tkSalGroup;
	}

	public BigDecimal getCompRate() {
		return compRate;
	}

	public void setCompRate(BigDecimal compRate) {
		this.compRate = compRate;
	}

}
