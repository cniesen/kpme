package org.kuali.hr.time.roles.validation;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class RoleRule extends MaintenanceDocumentRuleBase {

	protected boolean validateWorkArea(TkRole role) {
		boolean valid = false;
		Criteria crit = new Criteria();
		crit.addEqualTo("workArea", role.getWorkArea());
		Query query = QueryFactory.newQuery(WorkArea.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
		}
		return valid;

	}

	protected boolean validateAssignment(TkRole role) {
		boolean valid = false;
		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(Assignment.class,
						role.getTkAssignmentId()) != null) {
			valid = true;
		}
		return valid;
	}

	/**
	 * It looks like the method that calls this class doesn't actually care
	 * about the return type.
	 */
	@Override
	protected boolean processCustomSaveDocumentBusinessRules(
			MaintenanceDocument document) {
		boolean valid = false;

		LOG.debug("entering custom validation for DeptLunchRule");
		PersistableBusinessObject pbo = this.getNewBo();
		if (pbo instanceof TkRole) {
			TkRole role = (TkRole) pbo;
			if (role != null) {
				valid = true;
				valid &= this.validateWorkArea(role);
				valid &= this.validateAssignment(role);

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