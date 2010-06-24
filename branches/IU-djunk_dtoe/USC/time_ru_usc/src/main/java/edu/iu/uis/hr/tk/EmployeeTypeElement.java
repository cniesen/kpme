package edu.iu.uis.hr.tk;

import edu.iu.uis.hr.AbstractWorkflowDocumentElement;
import edu.iu.uis.hr.WorkflowDocumentElement;

public class EmployeeTypeElement extends AbstractWorkflowDocumentElement {

	private static final String ELEMENT_NAME = "EMPLOYEETYPE";

	public EmployeeTypeElement() {
		super();
		setElementName(ELEMENT_NAME);
	}
	
	@SuppressWarnings("unchecked")
	public EmployeeTypeElement(String employeeType) {
		this();
		getAttributeMap().put(WorkflowDocumentElement.VALUE_ATTRIBUTE, employeeType);
	}
	
}
