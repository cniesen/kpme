package org.kuali.hr.pm.positionresponsibility;

import java.math.BigDecimal;

import org.kuali.hr.pm.institution.Institution;
import org.kuali.hr.time.HrBusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.location.impl.campus.CampusBo;

public class PositionResponsibility extends HrBusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1631206606795253956L;
	
	private String positionResponsibilityId;
	private String institution;
	private String campus; //location
	private String positionResponsibilityOption;
	private BigDecimal percentTime;
	private String hrPositionId;
	private CampusBo campusObj;

	public String getPositionResponsibilityId() {
		return positionResponsibilityId;
	}

	public void setPositionResponsibilityId(String positionResponsibilityId) {
		this.positionResponsibilityId = positionResponsibilityId;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getPositionResponsibilityOption() {
		return positionResponsibilityOption;
	}

	public void setPositionResponsibilityOption(String positionResponsibilityOption) {
		this.positionResponsibilityOption = positionResponsibilityOption;
	}

	
	public BigDecimal getPercentTime() {
		return percentTime;
	}

	public void setPercentTime(BigDecimal percentTime) {
		this.percentTime = percentTime;
	}

	@Override
	protected String getUniqueKey() {
		return this.getInstitution() + "_" + this.getCampus();
	}
	
	public CampusBo getCampusObj() {
		return campusObj;
	}

	public void setCampusObj(CampusBo campusObj) {
		this.campusObj = campusObj;
	}

	public String getHrPositionId() {
		return hrPositionId;
	}

	public void setHrPositionId(String hrPositionId) {
		this.hrPositionId = hrPositionId;
	}

	@Override
	public String getId() {
		return this.getPositionResponsibilityId();
	}

	@Override
	public void setId(String id) {
		this.setPositionResponsibilityId(id);
		
	}

	
	
	
}
