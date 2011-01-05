package org.kuali.hr.time.assignment.validation;



import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.task.Task;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class AssignmentRule extends MaintenanceDocumentRuleBase {

	protected boolean validateWorkArea(Assignment assignment ) {
		boolean valid = false;
		LOG.debug("Validating workarea: " + assignment.getWorkArea());
		Criteria crit = new Criteria();
		crit.addEqualTo("workArea", assignment.getWorkArea());
		Query query = QueryFactory.newQuery(WorkArea.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
			LOG.debug("found workarea.");
		}else{
			this.putFieldError("workArea", "error.existence", "workarea '"
					+ assignment.getWorkArea()+ "'");
		}
		return valid;
		
	} 
	
	@SuppressWarnings("rawtypes")
	protected boolean validateTask(Assignment assignment ) {
		boolean valid = false;
		LOG.debug("Validating task: " + assignment.getTask());
		Criteria crit = new Criteria();
		crit.addEqualTo("task", assignment.getTask());
		Query query = QueryFactory.newQuery(Task.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			valid = true;
			LOG.debug("found task.");
		}else{
			this.putFieldError("task", "error.existence", "task '"
					+ assignment.getWorkArea()+ "'");
		}
		return valid;		 
	}
	
	protected boolean validateJob(Assignment assignment ) {
		LOG.debug("Validating job: " + assignment.getJobNumber());
		boolean valid = false;
		Criteria crit = new Criteria();
		crit.addEqualTo("jobNumber", assignment.getJobNumber());
		Query query = QueryFactory.newQuery(Job.class, crit);
		int count = PersistenceBrokerFactory.defaultPersistenceBroker()
				.getCount(query);
		if (count > 0) {
			
			valid = true;
			
		}else{
			this.putFieldError("jobNumber", "error.existence", "jobNumber '"
					+ assignment.getJobNumber()+ "'");
		}
		return valid;		
		
	} 
	
	//TODO fix this class
	protected boolean validateEarnCode(Assignment assignment ) {
		boolean valid = false;
//		LOG.debug("Validating EarnCode: " + assignment.getEarnCode()());
//		EarnCode earnCode = KNSServiceLocator.getBusinessObjectService()
//				.findBySinglePrimaryKey(EarnCode.class, assignment.getEarnCodeId());
//		if (earnCode != null) {
//			valid = true;			
//			LOG.debug("found earnCode.");
//		} else {
//			this.putFieldError("earnCodeId", "error.existence", "earnCodeId '"
//					+ assignment.getEarnCodeId()+ "'");
//		}
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

		LOG.debug("entering custom validation for Assignment");
		PersistableBusinessObject pbo = this.getNewBo();
		if (pbo instanceof Assignment) {
			Assignment assignment = (Assignment) pbo;			
			if (assignment != null) {
				valid = true;				
				valid &= this.validateWorkArea(assignment);		
				valid &= this.validateJob(assignment);
				valid &= this.validateTask(assignment);				
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
