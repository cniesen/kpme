package edu.iu.uis.hr.tk.workflow;

import org.kuali.rice.kew.rule.RuleValidationAttribute;
import org.kuali.rice.kew.validation.RuleValidationContext;
import org.kuali.rice.kew.validation.ValidationResults;

public class DelegateValidationRuleAttribute implements RuleValidationAttribute {
	
    private RuleValidationAttribute wrappedRuleValidationAttribute = new DelegateValidationRuleAttributeImpl();     

    public ValidationResults validate(RuleValidationContext validationContext) throws Exception {
        return wrappedRuleValidationAttribute.validate(validationContext);
    }

}
