package edu.iu.uis.hr.tk.workflow;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.kuali.rice.core.resourceloader.GlobalResourceLoader;
import org.kuali.rice.kew.rule.RuleBaseValues;
import org.kuali.rice.kew.rule.RuleDelegation;
import org.kuali.rice.kew.rule.RuleResponsibility;
import org.kuali.rice.kew.rule.RuleValidationAttribute;
import org.kuali.rice.kew.validation.RuleValidationContext;
import org.kuali.rice.kew.validation.ValidationResults;
import org.kuali.rice.kim.bo.entity.KimPrincipal;

import edu.iu.uis.hr.entity.FieldNames;

public class DelegateValidationRuleAttributeImpl implements RuleValidationAttribute {
	
    private static final Logger LOG = Logger.getLogger(DelegateValidationRuleAttributeImpl.class);
	

    // TODO : not sure about this message.
    private static final String INVALID_DELEGATOR_DELEGATE = "Invalid delegator/delegate set up ";
    private static final String NOT_AUTHORIZED_TO_ENTER_DELEGATE = "Not authorized to enter delegate rule ";

    @SuppressWarnings("unchecked")
	public ValidationResults validate(RuleValidationContext validationContext) throws Exception {
        RuleBaseValues rule=validationContext.getRule();
        KimPrincipal user= validationContext.getRuleAuthor().getPrincipal();
        ValidationResults validationResults = new ValidationResults();
        String delegatorNetworkId = rule.getRuleExtensionValue(DelegationAttributeImpl.DELEGATOR_NETWORK_ID).getValue();
        String workArea = rule.getRuleExtensionValue(FieldNames.WORK_AREA).getValue();

        RuleDelegation ruleDelegation = validationContext.getRuleDelegation();
        RuleResponsibility parentResponsibility = ruleDelegation.getRuleResponsibility();
        
        LOG.debug("RuleDegation: " + ruleDelegation);
        LOG.debug("RuleResponsibility: " + parentResponsibility);
        LOG.debug("rule id: " + rule.getRuleBaseValuesId());

        List delegateNetworkIds = rule.getResponsibilities();
        for (Iterator iter = delegateNetworkIds.iterator(); iter.hasNext();) {
            RuleResponsibility ruleResponsibility = (RuleResponsibility) iter.next();
            // TODO : if delegate is a workgroup, then ruleResponsibility.getWorkflowUser() will be null
            // do we have delegate as a workgroup ?
            if (!getRolesService().isValidDelegate(delegatorNetworkId, ruleResponsibility.getPrincipal().getPrincipalName(), new BigDecimal(workArea), parentResponsibility.getResolvedRoleName())) {
                // TODO : should getRolesService().isValidDelegate return proper message instead of boolean ?
                validationResults.addValidationResult(INVALID_DELEGATOR_DELEGATE);
            }
        }
        if (!user.getPrincipalName().equalsIgnoreCase(delegatorNetworkId) && !getRolesService().isAuthorizedToEnterDelegate(user.getPrincipalName(),new BigDecimal(workArea))) {
            validationResults.addValidationResult(NOT_AUTHORIZED_TO_ENTER_DELEGATE);
        }
        return validationResults;
    }

    private WorkflowSupportService getRolesService() {
        return (WorkflowSupportService) GlobalResourceLoader.getService(new QName("TK", "workflowSupportService"));
    }


}
