package edu.iu.uis.hr.tk;

import edu.iu.uis.hr.AbstractWorkflowDocumentElement;
import edu.iu.uis.hr.WorkflowDocumentElement;

public class SalaryPlanElement extends AbstractWorkflowDocumentElement {

	private static final String ELEMENT_NAME = "SALARYPLAN";
	
	protected SalaryPlanElement() {
        super();
        setElementName(SalaryPlanElement.ELEMENT_NAME);
	}
	
	@SuppressWarnings("unchecked")
	public SalaryPlanElement(String salaryPlan) {
        this();
        getAttributeMap().put(WorkflowDocumentElement.VALUE_ATTRIBUTE, salaryPlan);
	}
	
}
