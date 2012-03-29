package edu.iu.hr.time.roles;

import java.util.ArrayList;
import java.util.List;

import org.kuali.hr.time.roles.TkRoleValuesFinder;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

import edu.iu.hr.time.util.IUTkConstants;

public class IUTkRoleValuesFinder extends TkRoleValuesFinder  {

	@Override
	public List<KeyLabelPair> getKeyValues() {
	
		List<KeyLabelPair> filteredLabels = new ArrayList<KeyLabelPair>();
		filteredLabels = super.getKeyValues();
		//Added this here as the doc type name is different for some reason between IU and stock KPME
		if(super.getKualiForm()== null || super.getKualiForm().getDocTypeName().equals("WorkAreaMaintenanceDocument")){
			if(TKContext.getUser().isSystemAdmin() || TKContext.getUser().isLocationAdmin()){
				filteredLabels.add(new KeyLabelPair(TkConstants.ROLE_TK_APPROVER, TkConstants.ALL_ROLES_MAP.get(TkConstants.ROLE_TK_APPROVER)));
				filteredLabels.add(new KeyLabelPair(TkConstants.ROLE_TK_APPROVER_DELEGATE, TkConstants.ALL_ROLES_MAP.get(TkConstants.ROLE_TK_APPROVER_DELEGATE)));
				filteredLabels.add(new KeyLabelPair(TkConstants.ROLE_TK_REVIEWER, TkConstants.ALL_ROLES_MAP.get(TkConstants.ROLE_TK_REVIEWER)));
			}
		}
		
		if(super.getKualiForm() == null || super.getKualiForm() .getDocTypeName().equals("DepartmentMaintenanceDocumentType") || super.getKualiForm() .getDocTypeName().equals("RoleGroupMaintenanceDocumentType")) {
			filteredLabels.add(new KeyLabelPair(IUTkConstants.ROLE_TK_PY_PROCESSOR, "Payroll Processor"));
			filteredLabels.add(new KeyLabelPair(IUTkConstants.ROLE_TK_PY_PROCESSOR_DELEGATE, "Payroll Processor Delegate"));
		}

		return filteredLabels;
	}
}
