package org.kuali.hr.lm.leaveaccrualcategory.validation;

import org.kuali.hr.lm.leaveaccrualcategory.LeaveAccrualCategory;
import org.kuali.hr.time.util.ValidationUtils;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;

public class LeaveAccrualCategoryValidation extends MaintenanceDocumentRuleBase {
	

	boolean validateLeavePlan(LeaveAccrualCategory leaveAccrualCategory) {
		if (leaveAccrualCategory.getLeavePlan() != null
				&& !ValidationUtils.validateLocation(leaveAccrualCategory.getLeavePlan(), null)) {
			this.putFieldError("leavePlan", "error.existence", "leavePlan '"
					+ leaveAccrualCategory.getLeavePlan() + "'");
			return false;
		} else {
			return true;
		}
	}

	boolean validateEffectiveDate(LeaveAccrualCategory leaveAccrualCategory) {
		if (leaveAccrualCategory.getEffectiveDate() != null
				&& !ValidationUtils.validateEffectiveDate(leaveAccrualCategory.getEffectiveDate())) {
			this.putFieldError("effectiveDate", "error.lm.lp.effectiveDate", "effectiveDate '"
					+ leaveAccrualCategory.getEffectiveDate() + "'");
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	protected boolean processCustomSaveDocumentBusinessRules(
			MaintenanceDocument document) {
		boolean valid = false;
		LOG.debug("entering custom validation for Job");
		PersistableBusinessObject pbo = this.getNewBo();
		if (pbo instanceof LeaveAccrualCategory) {
			LeaveAccrualCategory leaveAccrualCategory = (LeaveAccrualCategory) pbo;
			if (leaveAccrualCategory != null) {
				valid = true;
				
				//valid &= this.validateLeavePlan(leaveAccrualCategory);
				valid &= this.validateEffectiveDate(leaveAccrualCategory);
			}
		}
		return valid;
	}

}
