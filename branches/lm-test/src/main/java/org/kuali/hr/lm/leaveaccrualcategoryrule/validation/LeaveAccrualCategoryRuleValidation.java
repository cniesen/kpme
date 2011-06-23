package org.kuali.hr.lm.leaveaccrualcategoryrule.validation;

import org.kuali.hr.lm.leaveaccrualcategoryrule.LeaveAccrualCategoryRule;
import org.kuali.hr.lm.leaveplan.LeavePlan;
import org.kuali.hr.time.util.ValidationUtils;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;

public class LeaveAccrualCategoryRuleValidation extends MaintenanceDocumentRuleBase {
	
	boolean validateLeaveAccrualCategory(LeaveAccrualCategoryRule leaveAccrualCategoryRule) {
		
		// do not have validateLeaveAccrualCategory yet
		if (leaveAccrualCategoryRule.getLeaveAccrualCategory() != null
				
				&& !ValidationUtils.validateLocation(leaveAccrualCategoryRule.getLeaveAccrualCategory(), null)) {
			this.putFieldError("leaveAccrualCategoryRule", "error.existence", "leaveAccrualCategoryRule '"
					+ leaveAccrualCategoryRule.getLeaveAccrualCategory() + "'");
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
		if (pbo instanceof LeaveAccrualCategoryRule) {
			LeaveAccrualCategoryRule leaveAccrualCategoryRule = (LeaveAccrualCategoryRule) pbo;
			if (leaveAccrualCategoryRule != null) {
				valid = true;
				
				//valid &= this.validateLeaveAccrualCategory(leaveAccrualCategoryRule);
				//valid &= this.validateLocation(leavePlan);
				//valid &= this.validateEffectiveDate(leavePlan);
			}
		}
		return valid;
	}

}
