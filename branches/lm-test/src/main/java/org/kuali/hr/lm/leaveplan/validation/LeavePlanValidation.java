package org.kuali.hr.lm.leaveplan.validation;

import org.kuali.hr.lm.leaveplan.LeavePlan;
import org.kuali.hr.time.util.ValidationUtils;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;

public class LeavePlanValidation extends MaintenanceDocumentRuleBase {
	boolean validateSalGroup(LeavePlan leavePlan) {
		if (!ValidationUtils.validateSalGroup(leavePlan.getSalaryGroup(), leavePlan
				.getEffectiveDate())) {
			this.putFieldError("salGroup", "error.existence", "Salgroup '"
					+ leavePlan.getSalaryGroup() + "'");
			return false;
		} else {
			return true;
		}
	}

	boolean validateLocation(LeavePlan leavePlan) {
		if (leavePlan.getLocation() != null
				&& !ValidationUtils.validateLocation(leavePlan.getLocation(), null)) {
			this.putFieldError("location", "error.existence", "location '"
					+ leavePlan.getLocation() + "'");
			return false;
		} else {
			return true;
		}
	}

	boolean validateEffectiveDate(LeavePlan leavePlan) {
		if (leavePlan.getEffectiveDate() != null
				&& !ValidationUtils.validateEffectiveDate(leavePlan.getEffectiveDate())) {
			this.putFieldError("effectiveDate", "error.lm.lp.effectiveDate", "effectiveDate '"
					+ leavePlan.getEffectiveDate() + "'");
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
		if (pbo instanceof LeavePlan) {
			LeavePlan leavePlan = (LeavePlan) pbo;
			if (leavePlan != null) {
				valid = true;
				
				valid &= this.validateSalGroup(leavePlan);
				valid &= this.validateLocation(leavePlan);
				valid &= this.validateEffectiveDate(leavePlan);
			}
		}
		return valid;
	}

}
