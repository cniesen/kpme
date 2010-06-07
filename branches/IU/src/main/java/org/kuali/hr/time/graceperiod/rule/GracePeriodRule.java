package org.kuali.hr.time.graceperiod.rule;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;



public class GracePeriodRule extends PersistableBusinessObjectBase {
    
	private static final long serialVersionUID = 1L;

	private Long gracePeriodRuleId;
	private Date effDt;
	private String effStatus;
	private BigDecimal graceMins;
	private BigDecimal hourFactor;
	private String userEmplId;
	private Timestamp timestamp; 
	
	protected LinkedHashMap<String,Object> toStringMapper() {
		LinkedHashMap<String, Object> toStringMap = new LinkedHashMap<String,Object>();
		
		toStringMap.put("effDt", effDt);
		toStringMap.put("graceMins", graceMins);
		toStringMap.put("hourFactor", hourFactor);
		
		return toStringMap;
	}

	
	public Long getGracePeriodRuleId() {
	    return gracePeriodRuleId;
	}


	public void setGracePeriodRuleId(Long gracePeriodRuleId) {
	    this.gracePeriodRuleId = gracePeriodRuleId;
	}


	public Date getEffDt() {
	    return effDt;
	}

	public void setEffDt(Date effDt) {
	    this.effDt = effDt;
	}

	public String getEffStatus() {
	    return effStatus;
	}

	public void setEffStatus(String effStatus) {
	    this.effStatus = effStatus;
	}

	public BigDecimal getGraceMins() {
	    return graceMins;
	}

	public void setGraceMins(BigDecimal graceMins) {
	    this.graceMins = graceMins;
	}

	public BigDecimal getHourFactor() {
	    return hourFactor;
	}

	public void setHourFactor(BigDecimal hourFactor) {
	    this.hourFactor = hourFactor;
	}

	public String getUserEmplId() {
	    return userEmplId;
	}

	public void setUserEmplId(String userEmplId) {
	    this.userEmplId = userEmplId;
	}

	public Timestamp getTimestamp() {
	    return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
	    this.timestamp = timestamp;
	}


}
