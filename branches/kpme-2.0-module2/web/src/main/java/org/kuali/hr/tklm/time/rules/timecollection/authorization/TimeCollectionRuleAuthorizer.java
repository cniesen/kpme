package org.kuali.hr.tklm.time.rules.timecollection.authorization;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.core.authorization.KPMEMaintenanceDocumentAuthorizerBase;
import org.kuali.hr.core.bo.department.Department;
import org.kuali.hr.core.role.KPMERoleMemberAttribute;
import org.kuali.hr.core.service.HrServiceLocator;
import org.kuali.hr.tklm.time.rules.timecollection.TimeCollectionRule;

@SuppressWarnings("deprecation")
public class TimeCollectionRuleAuthorizer extends KPMEMaintenanceDocumentAuthorizerBase {

	private static final long serialVersionUID = 4244084290191633140L;

	protected void addRoleQualification(Object dataObject, Map<String, String> attributes) {
		super.addRoleQualification(dataObject, attributes);

		String department = StringUtils.EMPTY;
		String location = StringUtils.EMPTY;
		
		if (dataObject instanceof TimeCollectionRule) {
			TimeCollectionRule timeCollectionRuleObj = (TimeCollectionRule) dataObject;
			
			if (timeCollectionRuleObj != null) {
				department = timeCollectionRuleObj.getDept();
				
				Department departmentObj = HrServiceLocator.getDepartmentService().getDepartment(department, timeCollectionRuleObj.getEffectiveLocalDate());
			
				if (departmentObj != null) {
					location = departmentObj.getLocation();
				}
			}
		}
		
		attributes.put(KPMERoleMemberAttribute.DEPARTMENT.getRoleMemberAttributeName(), department);
		attributes.put(KPMERoleMemberAttribute.LOCATION.getRoleMemberAttributeName(), location);
	}
	
}