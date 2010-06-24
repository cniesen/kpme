package edu.iu.uis.hr.tk;

import edu.iu.uis.hr.AbstractWorkflowDocumentElement;
import edu.iu.uis.hr.WorkflowDocumentElement;

public class PayEndDateElement extends AbstractWorkflowDocumentElement {

	public static final String ELEMENT_NAME = "PAYENDDATEELEMENT";
	
	public PayEndDateElement() {
		super();
		setElementName(ELEMENT_NAME);
	}
	
	@SuppressWarnings("unchecked")
	public PayEndDateElement(String payEndDate) {
		this();
		getAttributeMap().put(WorkflowDocumentElement.VALUE_ATTRIBUTE, payEndDate);
	}
}
