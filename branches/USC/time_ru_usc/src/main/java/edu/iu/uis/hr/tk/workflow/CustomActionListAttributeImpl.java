package edu.iu.uis.hr.tk.workflow;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.kuali.rice.kew.actionitem.ActionItem;
import org.kuali.rice.kew.actionlist.DisplayParameters;
import org.kuali.rice.kew.actions.ActionSet;
import org.kuali.rice.kew.dto.RouteNodeInstanceDTO;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kew.web.session.UserSession;

import edu.iu.uis.eden.web.IUUserSessionUtils;

public class CustomActionListAttributeImpl implements org.kuali.rice.kew.actionlist.CustomActionListAttribute {

	private static final Logger LOG = Logger.getLogger(CustomActionListAttribute.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 5929948932740590810L;

	public static final DisplayParameters DISPLAY_PARAMETERS = new DisplayParameters(new Integer(400));
	private static final String SUPERVISOR_NODE_NAME = "Supervisor";
	private static final String PAYROLL_PROCESSOR_NODE_NAME = "PayrollProcessor";
	
	private static final String WORKFLOW_SUPER_USER = "timekeeping-system";

	public ActionSet getLegalActions(UserSession userSession, ActionItem actionItem) throws Exception {
		LOG.debug("Entering tk CustomActionListAttributeImpl.getLegalActions");
		// load the active node instances of the document
		RouteNodeInstanceDTO[] nodeInstances = new WorkflowInfo().getActiveNodeInstances(actionItem.getRouteHeaderId());
		// supervisor && readytoapprove OR payp && hassafewordauth &&
		// readytoapprove
		ActionSet actionSet = new ActionSet();
		actionSet.addFyi();
		actionSet.addAcknowledge();
		if (isAtSupervisorNode(nodeInstances) && isReadyToApprove(actionItem.getRouteHeaderId())) {
			actionSet.addApprove();
			return actionSet;
		} else if (isAtPayrollProcessorNode(nodeInstances) && isSafewordAuthenticated(userSession) && isReadyToApprove(actionItem.getRouteHeaderId())) {
			actionSet.addApprove();
		}
		return actionSet;
	}

	/**
	 * Returns the display parameters for the inline framed doc handler on the
	 * Action List. If this method returns null, then a default value will be
	 * used.
	 */
	public DisplayParameters getDocHandlerDisplayParameters(UserSession userSession, ActionItem actionItem) throws Exception {
		return DISPLAY_PARAMETERS;

	}

	private boolean isAtSupervisorNode(RouteNodeInstanceDTO[] nodeInstances) {
		return isAtNode(nodeInstances, SUPERVISOR_NODE_NAME);
	}

	private boolean isAtPayrollProcessorNode(RouteNodeInstanceDTO[] nodeInstances) {
		return isAtNode(nodeInstances, PAYROLL_PROCESSOR_NODE_NAME);
	}

	private boolean isAtNode(RouteNodeInstanceDTO[] nodeInstances, String nodeName) {
		for (int index = 0; index < nodeInstances.length; index++) {
			RouteNodeInstanceDTO nodeInstance = nodeInstances[index];
			if (nodeInstance.getName().equals(nodeName)) {
				return true;
			}
		}
		return false;
	}

	private static Set<Long> READY_TO_APPROVE_SET = Collections.synchronizedSet(new HashSet<Long>());

	private boolean isReadyToApprove(Long documentId) throws Exception {
		String appDocId = new WorkflowInfo().getAppDocId(documentId);
		return new Boolean(appDocId);
	}

	private boolean isSafewordAuthenticated(UserSession userSession) {
		return IUUserSessionUtils.isSafewordAuthenticated(userSession);
	}

}
