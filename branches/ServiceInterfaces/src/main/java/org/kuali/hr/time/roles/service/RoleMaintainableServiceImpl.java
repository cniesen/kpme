package org.kuali.hr.time.roles.service;


import java.util.Map;

import org.kuali.hr.time.overtime.daily.rule.DailyOvertimeRule;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.util.TKContext;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;

public class RoleMaintainableServiceImpl extends KualiMaintainableImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void saveBusinessObject() {
		TkRole role = (TkRole) this.getBusinessObject();
		TkRole oldRole = (TkRole) KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(TkRole.class, role.getTkRolesId());
		if (oldRole != null) {
			oldRole.setActive(false);
			KNSServiceLocator.getBusinessObjectService().save(oldRole);
		}
		role.setTkRolesId(null);
		role.setTimestamp(null);
		role.setPrincipalId(GlobalVariables.getUserSession().getPrincipalId());
		KNSServiceLocator.getBusinessObjectService().save(role);
	}
	
	@Override
	public void processAfterPost(MaintenanceDocument document,
			Map<String, String[]> parameters) {
		TkRole role = (TkRole) document
				.getDocumentBusinessObject();
		role.setUserPrincipalId(GlobalVariables.getUserSession()
				.getPrincipalId());
		super.processAfterPost(document, parameters);
	}

	@Override
	public void processAfterEdit(MaintenanceDocument document,
			Map<String, String[]> parameters) {
		TkRole role = (TkRole) document
				.getDocumentBusinessObject();
		role.setUserPrincipalId(GlobalVariables.getUserSession()
				.getPrincipalId());
		super.processAfterEdit(document, parameters);
	}


}
