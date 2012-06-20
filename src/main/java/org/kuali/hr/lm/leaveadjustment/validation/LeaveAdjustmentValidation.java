package org.kuali.hr.lm.leaveadjustment.validation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.lm.earncodesec.EarnCodeSecurity;
import org.kuali.hr.lm.leaveadjustment.LeaveAdjustment;
import org.kuali.hr.lm.leavedonation.validation.LeaveDonationValidation;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.timeblock.TimeBlock;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.util.ValidationUtils;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;

public class LeaveAdjustmentValidation extends MaintenanceDocumentRuleBase{
	
	boolean validateLeavePlan(String leavePlan, Date asOfDate) {
		boolean valid = true;
		if (!ValidationUtils.validateLeavePlan(leavePlan, asOfDate)) {
			this.putFieldError("leavePlan", "error.existence", "leavePlan '"
					+ leavePlan + "'");
			valid = false;
		}
		return valid;
	}
	
	boolean validateEarnCode(String earnCode, String accrualCategory, String principalId, Date asOfDate) {
		boolean valid = true;
		if (!ValidationUtils.validateEarnCodeOfAccrualCategory(earnCode, accrualCategory, principalId, asOfDate)) {
			this.putFieldError("earnCode", "error.existence", "earnCode '"
					+ earnCode + "'");
			valid = false;
		}
		return valid;
	}

	boolean validatePrincipal(String principalId) {
		boolean valid = true;
		if (!ValidationUtils.validatePrincipalId(principalId)) {
			this.putFieldError("principalId", "error.existence",
					"principalId '" + principalId + "'");
			valid = false;
		}
		return valid;
	}

	boolean validateAccrualCategory(String accrualCategory, Date asOfDate, String principalId) {
		boolean valid = true;
		if (!ValidationUtils.validateAccCategory(accrualCategory, principalId, asOfDate)) {
			this.putFieldError("accrualCategory", "error.existence", "accrualCategory '"
					+ accrualCategory + "'");
			valid = false;
		}
		return valid;
	}
		
	@Override
	protected boolean processCustomRouteDocumentBusinessRules(
			MaintenanceDocument document) {
		boolean valid = false;

		LOG.debug("entering custom validation for LeaveAdjustment");
		PersistableBusinessObject pbo = this.getNewBo();
		if (pbo instanceof LeaveAdjustment) {
			LeaveAdjustment leaveAdjustment = (LeaveAdjustment) pbo;

			if (leaveAdjustment != null) {
				valid = true;
				if(leaveAdjustment.getPrincipalId() != null) {
					valid &= this.validatePrincipal(leaveAdjustment.getPrincipalId());
				}
				if(leaveAdjustment.getAccrualCategory() != null) {
					valid &= this.validateAccrualCategory(leaveAdjustment.getAccrualCategory(),leaveAdjustment.getEffectiveDate(), leaveAdjustment.getPrincipalId());
				}
				if(leaveAdjustment.getLeavePlan() != null) {
					valid &= this.validateLeavePlan(leaveAdjustment.getLeavePlan(), leaveAdjustment.getEffectiveDate());
				}
				if(leaveAdjustment.getEarnCode() != null) {
					valid &= this.validateEarnCode(leaveAdjustment.getEarnCode(), leaveAdjustment.getAccrualCategory(), leaveAdjustment.getPrincipalId(), leaveAdjustment.getEffectiveDate());
				}
			}
		}

		return valid;
	}

}
