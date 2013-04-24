
package org.kuali.hr.pm.paystep;

import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.kuali.hr.lm.LMConstants;
import org.kuali.hr.paygrade.PayGrade;
import org.kuali.hr.pm.institution.Institution;
import org.kuali.hr.time.HrBusinessObject;
import org.kuali.hr.time.salgroup.SalGroup;
import org.kuali.rice.location.impl.campus.CampusBo;
import com.google.common.collect.ImmutableList;

public class PayStep extends HrBusinessObject implements Comparable {

	//KPME-2273/1965 Primary Business Keys List.	
	public static final ImmutableList<String> EQUAL_TO_FIELDS = new ImmutableList.Builder<String>()
		    .add("payStep")
		    .build();

	private static final long serialVersionUID = 1L;
	
	private String pmPayStepId;
	private String payStep;
	private String institution;
	private String campus;
	private String salaryGroup;
	private String payGrade;
	private int stepNumber;
	private BigDecimal compRate;
	private int serviceAmount;
	private String serviceUnit;
	
	private Institution institutionObj;
	private CampusBo campusObj;
	private SalGroup salaryGroupObj;
	private PayGrade PayGradeObj;

	@Override
	public boolean isActive() {
		return super.isActive();
	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);
	}

	@Override
	public String getObjectId() {
		return super.getObjectId();
	}

	@Override
	public Long getVersionNumber() {
		return super.getVersionNumber();
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof PayStep) {
			PayStep s = (PayStep) o;
			if(StringUtils.equals(s.salaryGroup,salaryGroup)
					&& StringUtils.equals(s.payGrade,payGrade)) {

				Integer otherServiceTime = 0;
				if(StringUtils.equals(s.serviceUnit,LMConstants.SERVICE_TIME_YEAR))
					otherServiceTime = s.getServiceAmount() * 12;
				else
					otherServiceTime = s.getServiceAmount();
				
				Integer thisServiceTime = 0;
				if(StringUtils.equals(serviceUnit, LMConstants.SERVICE_TIME_YEAR))
					thisServiceTime = serviceAmount * 12;
				else
					thisServiceTime = serviceAmount;
				
				return otherServiceTime.compareTo(thisServiceTime);
			}
			else
				throw new IllegalArgumentException("pay step must be within the same salary group and pay grade");
		}
			
		return 0;
	}

	public String getPayStep() {
		return payStep;
	}

	public void setPayStep(String payStep) {
		this.payStep = payStep;
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

	public String getSalaryGroup() {
		return salaryGroup;
	}

	public void setSalaryGroup(String salaryGroup) {
		this.salaryGroup = salaryGroup;
	}

	public String getPayGrade() {
		return payGrade;
	}

	public void setPayGrade(String payGrade) {
		this.payGrade = payGrade;
	}

	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	public BigDecimal getCompRate() {
		return compRate;
	}

	public void setCompRate(BigDecimal compRate) {
		this.compRate = compRate;
	}

	public int getServiceAmount() {
		return serviceAmount;
	}

	public void setServiceAmount(int serviceInterval) {
		this.serviceAmount = serviceInterval;
	}

	public String getServiceUnit() {
		return serviceUnit;
	}

	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}

	@Override
	public String getId() {
		return pmPayStepId;
	}

	@Override
	public void setId(String id) {
		pmPayStepId = id;
	}

	@Override
	protected String getUniqueKey() {
		return getPmPayStepId();
	}

	public String getPmPayStepId() {
		return pmPayStepId;
	}

	public void setPmPayStepId(String pmPayStepId) {
		this.pmPayStepId = pmPayStepId;
	}

	public Institution getInstitutionObj() {
		return institutionObj;
	}

	public void setInstitutionObj(Institution institutionObj) {
		this.institutionObj = institutionObj;
	}

	public CampusBo getCampusObj() {
		return campusObj;
	}

	public void setCampusObj(CampusBo campusObj) {
		this.campusObj = campusObj;
	}

	public SalGroup getSalaryGroupObj() {
		return salaryGroupObj;
	}

	public void setSalaryGroupObj(SalGroup salaryGroupObj) {
		this.salaryGroupObj = salaryGroupObj;
	}

	public PayGrade getPayGradeObj() {
		return PayGradeObj;
	}

	public void setPayGradeObj(PayGrade payGradeObj) {
		PayGradeObj = payGradeObj;
	}

}
