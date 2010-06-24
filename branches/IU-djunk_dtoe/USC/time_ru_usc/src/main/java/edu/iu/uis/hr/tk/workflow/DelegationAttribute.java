package edu.iu.uis.hr.tk.workflow;

import java.util.List;
import java.util.Map;

import org.kuali.rice.kew.routeheader.DocumentContent;
import org.kuali.rice.kew.rule.WorkflowAttribute;

public class DelegationAttribute implements WorkflowAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4155995851762345193L;
	private WorkflowAttribute wrappedAttribute = new DelegationAttributeImpl();
	
	@SuppressWarnings("unchecked")
	public boolean isMatch(DocumentContent documentContent, List ruleExtensionValues) {
		return wrappedAttribute.isMatch(documentContent, ruleExtensionValues);
	}

	@SuppressWarnings("unchecked")
	public List getRuleRows() {
		return wrappedAttribute.getRuleRows();
	}

	@SuppressWarnings("unchecked")
	public List getRoutingDataRows() {
		return wrappedAttribute.getRoutingDataRows();
	}

	public String getDocContent() {
		return wrappedAttribute.getDocContent();
	}

	@SuppressWarnings("unchecked")
	public List getRuleExtensionValues() {
		return wrappedAttribute.getRuleExtensionValues();
	}

	@SuppressWarnings("unchecked")
	public List validateRoutingData(Map paramMap) {
		return wrappedAttribute.validateRoutingData(paramMap);
	}

	@SuppressWarnings("unchecked")
	public List validateRuleData(Map paramMap) {
		return wrappedAttribute.validateRuleData(paramMap);
	}

	public void setRequired(boolean required) {
		wrappedAttribute.setRequired(required);
	}

	public boolean isRequired() {
		return wrappedAttribute.isRequired();
	}

}
