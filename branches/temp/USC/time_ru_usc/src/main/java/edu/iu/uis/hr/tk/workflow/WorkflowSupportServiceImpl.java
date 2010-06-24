package edu.iu.uis.hr.tk.workflow;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.tk.directory.service.RolesService;
import edu.iu.uis.hr.tk.timesheet.entity.logic.IsReadyToApproveLogic;

public class WorkflowSupportServiceImpl implements WorkflowSupportService {

	private static final Logger LOG = Logger.getLogger(WorkflowSupportServiceImpl.class);
	
	public boolean isDocumentReadyToBeApproved(Long documentId) {
		long millis = System.currentTimeMillis();
		LOG.debug("Entering TK ActionList Server isReadyToApprove()");
		try {
			IsReadyToApproveLogic logic = (IsReadyToApproveLogic) Context.getLogic(IsReadyToApproveLogic.class);
			return logic.isReadyToApprove(documentId.toString());
		} finally {
			LOG.debug("Leaving TK Action List Component.  Time: " + (System.currentTimeMillis() - millis));
		}
	}

	public boolean isValidDelegate(String delegatorNetworkId, String delegateNetworkId, BigDecimal workAreaId, String roleName) {
		return ((RolesService)Context.getService(RolesService.class)).isValidDelegate(delegatorNetworkId, delegateNetworkId, workAreaId, roleName);
	}

	public boolean isAuthorizedToEnterDelegate(String userNetworkId, BigDecimal workAreaId) {
		return ((RolesService)Context.getService(RolesService.class)).isAuthorizedToEnterDelegate(userNetworkId, workAreaId);
	}

	
	
}
