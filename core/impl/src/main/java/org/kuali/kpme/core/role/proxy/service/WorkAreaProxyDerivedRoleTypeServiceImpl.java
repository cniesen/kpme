package org.kuali.kpme.core.role.proxy.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kpme.core.role.KPMERoleMemberAttribute;
import org.kuali.kpme.core.util.HrConstants;

public class WorkAreaProxyDerivedRoleTypeServiceImpl extends KpmeRoleProxyDerivedRoleTypeServiceImpl {

	private String proxiedRoleNamespaceCode = "";
	private String proxiedRoleName = "";
	
	@Override
	protected String getProxiedRoleNamespaceCode() {
		return this.proxiedRoleNamespaceCode;
	}
	
	@Override
	protected String getProxiedRoleName() {
		return this.proxiedRoleName;
	}
	
	@Override
	public List<String> getQualifiersForExactMatch() {    
		return Collections.singletonList(KPMERoleMemberAttribute.WORK_AREA.getRoleMemberAttributeName());
	}
	
	@Override
	public boolean performMatch(Map<String, String> inputAttributes, Map<String, String> storedAttributes) {
		boolean matches = false;

        Long inputWorkArea;
        if (StringUtils.equals(MapUtils.getString(inputAttributes, KPMERoleMemberAttribute.WORK_AREA.getRoleMemberAttributeName()), "%")) {
            inputWorkArea = HrConstants.WILDCARD_LONG;
        } else {
		    inputWorkArea = MapUtils.getLong(inputAttributes, KPMERoleMemberAttribute.WORK_AREA.getRoleMemberAttributeName());
        }
		Long storedWorkArea = MapUtils.getLong(storedAttributes, KPMERoleMemberAttribute.WORK_AREA.getRoleMemberAttributeName());
		
		if (storedWorkArea != null) {
			matches = ObjectUtils.equals(inputWorkArea, storedWorkArea) || ObjectUtils.equals(inputWorkArea, HrConstants.WILDCARD_LONG);
		}
		
		return matches;
	}

	public void setProxiedRoleNamespaceCode(String proxiedRoleNamespaceCode) {
		this.proxiedRoleNamespaceCode = proxiedRoleNamespaceCode;
	}
	
	public void setProxiedRoleName(String proxiedRoleName) {
		this.proxiedRoleName = proxiedRoleName;
	}
	
}
