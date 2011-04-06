package org.kuali.hr.time.workarea;

import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.task.Task;
import org.kuali.hr.time.util.TKContext;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.ui.Section;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkAreaMaintainableImpl extends KualiMaintainableImpl {

	private static final long serialVersionUID = 6264585236631982347L;

	@Override
	public void saveBusinessObject() {

		WorkArea workArea = (WorkArea) this.getBusinessObject();
		List<TkRole> roles = workArea.getRoles();

		if (workArea.getInactiveRoles() != null
				&& workArea.getInactiveRoles().size() > 0) {
			for (TkRole role : workArea.getInactiveRoles()) {
				roles.add(role);
			}
		}

		List<Task> tasks = workArea.getTasks();

		for (TkRole role : roles) {
			role.setWorkArea(workArea.getWorkArea());
			role.setUserPrincipalId(TKContext.getPrincipalId());
		}

		workArea.setRoles(roles);
		KNSServiceLocator.getBusinessObjectService().save(workArea);
		TkServiceLocator.getTkRoleService().saveOrUpdate(roles);
		this.resetWorkArea(workArea);
	}

	public void resetWorkArea(WorkArea workArea) {
		if (workArea.getWorkArea() == null) {
			Map<String, Object> criteria = new HashMap<String, Object>();
			criteria.put("tkWorkAreaId", workArea.getTkWorkAreaId());
			Collection aCol = KNSServiceLocator.getBusinessObjectService()
					.findMatching(WorkArea.class, criteria);
			if (!aCol.isEmpty()) {
				WorkArea aWorkArea = (WorkArea) aCol.toArray()[0];
				workArea.setWorkArea(aWorkArea.getWorkArea());
			}
		}
	}

	@Override
	public void processAfterEdit(MaintenanceDocument document,
			Map<String, String[]> parameters) {
		WorkArea waOld = (WorkArea) document.getOldMaintainableObject()
				.getBusinessObject();
		WorkArea waNew = (WorkArea) document.getNewMaintainableObject()
				.getBusinessObject();

		TkServiceLocator.getWorkAreaService().populateWorkAreaRoles(waOld);
		TkServiceLocator.getWorkAreaService().populateWorkAreaRoles(waNew);

		WorkArea workArea = (WorkArea) document.getDocumentBusinessObject();
		List<TkRole> roles = new ArrayList<TkRole>();
		List<TkRole> inactiveRoles = new ArrayList<TkRole>();
		for (TkRole role : workArea.getRoles()) {
			if (role.isActive()) {
				roles.add(role);
			} else {
				inactiveRoles.add(role);
			}
		}
		workArea.setRoles(roles);
		workArea.setInactiveRoles(inactiveRoles);
		document.getOldMaintainableObject().setBusinessObject(workArea);
		
		super.processAfterEdit(document, parameters);
	}

	@Override
	public List getSections(MaintenanceDocument document,
			Maintainable oldMaintainable) {
		List sections = super.getSections(document, oldMaintainable);
		for (Object obj : sections) {
			Section sec = (Section) obj;
			if (document.isOldBusinessObjectInDocument()
					&& sec.getSectionId().equals("inactiveRoles")) {
				sec.setHidden(false);
			}
		}
		return sections;
	}

}
