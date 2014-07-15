/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.kpme.core.paystep;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.kpme.core.api.paystep.PayStep;
import org.kuali.kpme.core.api.paystep.PayStepContract;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.rice.core.api.mo.ModelObjectUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class PayStepBo extends HrBusinessObject implements Comparable, PayStepContract {

	static class KeyFields {
		private static final String PAY_STEP = "payStep";
	}

	/*
	 * convert bo to immutable
	 *
	 * Can be used with ModelObjectUtils:
	 *
	 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfPayStepBo, PayStepBo.toImmutable);
	 */
	public static final ModelObjectUtils.Transformer<PayStepBo, PayStep> toImmutable =
			new ModelObjectUtils.Transformer<PayStepBo, PayStep>() {
		public PayStep transform(PayStepBo input) {
			return PayStepBo.to(input);
		};
	};

	/*
	 * convert immutable to bo
	 * 
	 * Can be used with ModelObjectUtils:
	 * 
	 * org.kuali.rice.core.api.mo.ModelObjectUtils.transform(listOfPayStep, PayStepBo.toBo);
	 */
	public static final ModelObjectUtils.Transformer<PayStep, PayStepBo> toBo =
			new ModelObjectUtils.Transformer<PayStep, PayStepBo>() {
		public PayStepBo transform(PayStep input) {
			return PayStepBo.from(input);
		};
	};

	private static final Logger LOG = Logger.getLogger(PayStepBo.class);
	//KPME-2273/1965 Primary Business Keys List.	
	public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>()
			.add(KeyFields.PAY_STEP)
			.build();

	private static final long serialVersionUID = 1L;

	private String pmPayStepId;
	private String payStep;
	private String salaryGroup;
	private String payGrade;
	private int stepNumber;
	private BigDecimal compRate;
	private int serviceAmount;
	private String serviceUnit;

	@Override
	public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
		return  new ImmutableMap.Builder<String, Object>()
				.put(KeyFields.PAY_STEP, this.getPayStep())
				.build();
	}

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
		if(o instanceof PayStepBo) {
			PayStepBo s = (PayStepBo) o;
			if(StringUtils.equals(s.salaryGroup,salaryGroup)
					&& StringUtils.equals(s.payGrade,payGrade)) {

				Integer otherServiceTime = 0;
				if(StringUtils.equals(s.serviceUnit,HrConstants.SERVICE_TIME_YEAR))
					otherServiceTime = s.getServiceAmount() * 12;
				else
					otherServiceTime = s.getServiceAmount();

				Integer thisServiceTime = 0;
				if(StringUtils.equals(serviceUnit, HrConstants.SERVICE_TIME_YEAR))
					thisServiceTime = serviceAmount * 12;
				else
					thisServiceTime = serviceAmount;

				return otherServiceTime.compareTo(thisServiceTime);
			}
			else 
				//	throw new IllegalArgumentException("pay step must be within the same salary group and pay grade");
				LOG.error("pay step must be within the same salary group and pay grade");
		}

		return 0;
	}

	public String getPayStep() {
		return payStep;
	}

	public void setPayStep(String payStep) {
		this.payStep = payStep;
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

	public static PayStepBo from(PayStep im) {
		if (im == null) {
			return null;
		}
		PayStepBo ps = new PayStepBo();
		ps.setPmPayStepId(im.getPmPayStepId());
		ps.setPayStep(im.getPayStep());
		ps.setSalaryGroup(im.getSalaryGroup());
		ps.setPayGrade(im.getPayGrade());
		ps.setStepNumber(im.getStepNumber());
		ps.setCompRate(im.getCompRate());
		ps.setServiceAmount(im.getServiceAmount());
		ps.setServiceUnit(im.getServiceUnit());

		// finally copy over the common fields into ps from im
		copyCommonFields(ps, im);

		return ps;
	}

	public static PayStep to(PayStepBo bo) {
		if (bo == null) {
			return null;
		}
		return PayStep.Builder.create(bo).build();
	}
}