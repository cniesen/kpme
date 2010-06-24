package edu.iu.uis.hr.tk.workflow;

import java.math.BigDecimal;

public interface WorkflowSupportService {
	public boolean isDocumentReadyToBeApproved(Long documentId);
	public boolean isValidDelegate(String delegatorNetworkId, String delegateNetworkId, BigDecimal workAreaId, String roleName);
	public boolean isAuthorizedToEnterDelegate(String userNetworkId, BigDecimal workAreaId);
}