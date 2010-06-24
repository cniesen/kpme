package edu.iu.uis.hr.tk.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.exception.WorkflowServiceErrorImpl;
import org.kuali.rice.kew.routeheader.DocumentContent;
import org.kuali.rice.kew.rule.RuleExtension;
import org.kuali.rice.kew.rule.RuleExtensionValue;
import org.kuali.rice.kew.rule.WorkflowAttribute;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

import edu.iu.uis.hr.directory.UserNotFoundException;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.directory.Utility;

public class DelegationAttributeImpl implements WorkflowAttribute {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6804214652051224799L;

	private static final Logger LOG = Logger.getLogger(DelegationAttributeImpl.class);

    private String delegatorNetworkId;
    private String workArea;
    private boolean required;

    protected static final String DELEGATOR_NETWORK_ID = "delegatorNetworkId";

    @SuppressWarnings("unchecked")
	public boolean isMatch(DocumentContent documentContent, List ruleExtensions) {
        for (Iterator iterator = ruleExtensions.iterator(); iterator.hasNext();) {
            RuleExtension extension = (RuleExtension) iterator.next();
            for (Iterator iterator2 = extension.getExtensionValues().iterator(); iterator2.hasNext();) {
                RuleExtensionValue extensionValue = (RuleExtensionValue) iterator2.next();
                if (extensionValue.getKey().equals(DELEGATOR_NETWORK_ID)) {
                    delegatorNetworkId = extensionValue.getValue();
                } else if (extensionValue.getKey().equals(FieldNames.WORK_AREA)) {
                    workArea = extensionValue.getValue();
                }
            }
        }
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(delegatorNetworkId)) {
            throw new RoutingException("Could not locate a delegator network id on rule.", LOG);
        }
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(workArea)) {
            throw new RoutingException("Could not locate workArea on rule.", LOG);
        }
        ActionRequestValue parentRequest = documentContent.getRouteContext().getActionRequest();
        return hasDelegator(parentRequest, delegatorNetworkId) && hasWorkareaId(parentRequest, workArea);
    }

    private boolean hasDelegator(ActionRequestValue parentRequest, String delegatorNetworkId) {
        try {
            return parentRequest.isUserRequest() && parentRequest.getPrincipal().getPrincipalName().equals(delegatorNetworkId);
        } catch (UserNotFoundException e) {
            throw new RoutingException("Could not locate user on request.", LOG);
        }
    }

    private boolean hasWorkareaId(ActionRequestValue parentRequest, String workareaId) {
        ActionRequestValue roleRequest = parentRequest.getParentActionRequest();
        String qualifiedRoleName = roleRequest.getQualifiedRoleName();
        String roleWorkareaId = Utility.getNestedRoleData(qualifiedRoleName);
        return workareaId.equals(roleWorkareaId);
    }

    @SuppressWarnings("unchecked")
	public List getRuleRows() {
        List rows = new ArrayList();
        List fields = new ArrayList();
        fields.add(new Field("Delegator Network ID", "", Field.TEXT, true, DELEGATOR_NETWORK_ID, "", false,false,null, null));
        rows.add(new Row(fields));
        fields = new ArrayList();
        fields.add(new Field("Work Area ID", "", Field.TEXT, true, FieldNames.WORK_AREA, "", false,false, null, null));
        rows.add(new Row(fields));
        return rows;
    }

    @SuppressWarnings("unchecked")
	public List getRoutingDataRows() {
        return new ArrayList();
    }

    public String getDocContent() {
        return "";
    }

    @SuppressWarnings("unchecked")
	public List getRuleExtensionValues() {
        List ruleExtensions = new ArrayList();
        ruleExtensions.add(new RuleExtensionValue(DELEGATOR_NETWORK_ID, delegatorNetworkId));
        ruleExtensions.add(new RuleExtensionValue(FieldNames.WORK_AREA, workArea));
        return ruleExtensions;
    }

    @SuppressWarnings("unchecked")
	public List validateRoutingData(Map paramMap) {
        List errors = new ArrayList();
        delegatorNetworkId = (String) paramMap.get(DELEGATOR_NETWORK_ID);
        workArea = (String) paramMap.get(FieldNames.WORK_AREA);

        if (isRequired() && !edu.iu.uis.hr.entity.logic.Utility.hasValue(delegatorNetworkId)) {
            errors.add(new WorkflowServiceErrorImpl("The delegator network id is required.", ""));
        }
        if (isRequired() && !edu.iu.uis.hr.entity.logic.Utility.hasValue(workArea)) {
            errors.add(new WorkflowServiceErrorImpl("Workarea ID is invalid.", ""));
        }

        return errors;

    }

    @SuppressWarnings("unchecked")
	public List validateRuleData(Map paramMap) {
        return validateRoutingData(paramMap);
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

}
