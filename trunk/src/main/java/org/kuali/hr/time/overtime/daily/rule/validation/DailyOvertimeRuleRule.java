package org.kuali.hr.time.overtime.daily.rule.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.time.overtime.daily.rule.DailyOvertimeRule;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.util.ValidationUtils;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

public class DailyOvertimeRuleRule extends MaintenanceDocumentRuleBase {

	boolean validateWorkArea(DailyOvertimeRule ruleObj) {
		boolean valid = true;
		if (!ValidationUtils.validateWorkArea(ruleObj.getWorkArea(), ruleObj
				.getEffectiveDate())) {
			this.putFieldError("workArea", "error.existence", "workArea '"
					+ ruleObj.getWorkArea() + "'");
			valid = false;
		} else if(!ruleObj.getWorkArea().equals(TkConstants.WILDCARD_LONG)) {
			Criteria crit = new Criteria();
			crit.addEqualTo("dept", ruleObj.getDept());
			crit.addEqualTo("workArea", ruleObj.getWorkArea());
			Query query = QueryFactory.newQuery(WorkArea.class, crit);
			int count = PersistenceBrokerFactory.defaultPersistenceBroker().getCount(query);
			valid = (count > 0);
			if(!valid){
				this.putFieldError("workArea", "dept.workarea.invalid.sync", ruleObj.getWorkArea()+ "");
			}
		}
		return valid;
	}

	boolean validateDepartment(DailyOvertimeRule ruleObj) {
		if (!ValidationUtils.validateDepartment(ruleObj.getDept(), ruleObj.getEffectiveDate())) {
			this.putFieldError("dept", "error.existence", "department '" + ruleObj.getDept() + "'");
			return false;
		} else {
			return true;
		}
	}

	boolean validateEarnCode(DailyOvertimeRule dailyOvertimeRule) {
		if (!ValidationUtils.validateEarnCode(dailyOvertimeRule.getEarnCode(), dailyOvertimeRule.getEffectiveDate())) {
			this.putFieldError("earnCode", "error.existence", "earnCode '" + dailyOvertimeRule.getEarnCode() + "'");
			return false;
		} else {
            if (!ValidationUtils.validateEarnCode(dailyOvertimeRule.getEarnCode(), true, dailyOvertimeRule.getEffectiveDate())) {
                this.putFieldError("earnCode", "earncode.ovt.required", dailyOvertimeRule.getEarnCode());
                return false;
            }
			return true;
		}
	}

	boolean validateEarnGroup(DailyOvertimeRule dailyOvertimeRule) {
		if (!ValidationUtils.validateEarnGroup(dailyOvertimeRule.getFromEarnGroup(), dailyOvertimeRule.getEffectiveDate())) {
			this.putFieldError("fromEarnGroup", "error.existence", "fromEarnGroup '" + dailyOvertimeRule.getFromEarnGroup() + "'");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * It looks like the method that calls this class doesn't actually care
	 * about the return type.
	 */
	@Override
	protected boolean processCustomSaveDocumentBusinessRules(
			MaintenanceDocument document) {
		boolean valid = false;

		LOG.debug("entering custom validation for DailyOvertimeRule");
		PersistableBusinessObject pbo = this.getNewBo();
		if (pbo instanceof DailyOvertimeRule) {
			DailyOvertimeRule dailyOvertimeRule = (DailyOvertimeRule) pbo;
			dailyOvertimeRule.setUserPrincipalId(GlobalVariables.getUserSession().getPrincipalId());
			if (dailyOvertimeRule != null) {
				valid = true;
				valid &= this.validateDepartment(dailyOvertimeRule);
				valid &= this.validateWorkArea(dailyOvertimeRule);
				valid &= this.validateEarnCode(dailyOvertimeRule);
				valid &= this.validateEarnGroup(dailyOvertimeRule);
			}
		}

		return valid;
	}

	@Override
	protected boolean processCustomApproveDocumentBusinessRules(
			MaintenanceDocument document) {
		return super.processCustomApproveDocumentBusinessRules(document);
	}

	@Override
	protected boolean processCustomRouteDocumentBusinessRules(
			MaintenanceDocument document) {
		return super.processCustomRouteDocumentBusinessRules(document);
	}
}