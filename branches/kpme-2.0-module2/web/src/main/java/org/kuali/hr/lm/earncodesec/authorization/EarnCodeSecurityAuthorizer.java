package org.kuali.hr.lm.earncodesec.authorization;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.core.authorization.KPMEMaintenanceDocumentAuthorizerBase;
import org.kuali.hr.core.role.KPMERoleMemberAttribute;
import org.kuali.hr.lm.earncodesec.EarnCodeSecurity;

@SuppressWarnings("deprecation")
public class EarnCodeSecurityAuthorizer extends KPMEMaintenanceDocumentAuthorizerBase {

	private static final long serialVersionUID = 7352157020861633853L;

	protected void addRoleQualification(Object dataObject, Map<String, String> attributes) {
		super.addRoleQualification(dataObject, attributes);

		String department = StringUtils.EMPTY;
		String location = StringUtils.EMPTY;
		
		if (dataObject instanceof EarnCodeSecurity) {
			EarnCodeSecurity earnCodeSecurityObj = (EarnCodeSecurity) dataObject;
			
			if (earnCodeSecurityObj != null) {
				department = earnCodeSecurityObj.getDept();
				location = earnCodeSecurityObj.getLocation();
			}
		}
		
		attributes.put(KPMERoleMemberAttribute.DEPARTMENT.getRoleMemberAttributeName(), department);
		attributes.put(KPMERoleMemberAttribute.LOCATION.getRoleMemberAttributeName(), location);
	}
	
}