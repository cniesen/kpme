package edu.iu.uis.hr.tk.rule.client;

import java.math.BigDecimal;
import java.util.List;

import edu.iu.uis.hr.client.AbstractMaintenanceActionForm;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.client.Utility;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;
import edu.iu.uis.hr.tk.rule.entity.Rule;

public abstract class AbstractRuleMaintenanceActionForm extends AbstractMaintenanceActionForm {
    private TypedPersistentMaintainedEntityList rules;

    public AbstractRuleMaintenanceActionForm() {
        super();
        setRules(new TypedPersistentMaintainedEntityList(getRuleClass()));
    }

    protected abstract Class getRuleClass();

    public Class getModeAuthorization() {
        return MaintenanceActionFormModeAuthorization.class;
    }
    
    public void setLookupResults(List lookupResults) {
        throw new UnsupportedOperationException(getClass() + " does not implement setLookupResults(List lookupResults)");
    }

    public final void addRows(BigDecimal rowsToAdd) {
        setMaintenanceNestingPath(edu.iu.uis.hr.Utility.getStandardPropertyName(Rule.class));
        addRows(rowsToAdd.intValue());
    }

    public final String getRemoveUrl() {
        return new StringBuffer(BEGIN_POST_TO_OPERATION).append(getPageBaseUrl()).append(Utility.QUESTION_MARK).append(Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, StrutsDispatchParamConstants.DELETE_DISPATCH_PARAM_VALUE)).toString();
    }
    
    protected final TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
        return getRules();
    }

    public final TypedPersistentMaintainedEntityList getRules() {
        return rules;
    }

    public final void setRules(TypedPersistentMaintainedEntityList rules) {
        if (rules != null) {
            this.rules = rules;
        }
    }

    public final Rule getRule(int index) {
        return (Rule)getRules().get(index);
    }

    public final void setRule(int index, Rule rule) {
        if (rule != null) {
            getRules().add(index, rule);
        }
    }
}