/**
 * Copyright 2004-2015 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.core.institution.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.kuali.kpme.core.role.KPMERoleMemberAttribute;
import org.kuali.rice.kns.kim.role.RoleTypeServiceBase;

@SuppressWarnings("deprecation")
public class InstitutionRoleTypeServiceImpl extends RoleTypeServiceBase {
	
	@Override
	public List<String> getQualifiersForExactMatch() {    
		return Collections.singletonList(KPMERoleMemberAttribute.INSTITUION.getRoleMemberAttributeName());
	}
	
	@Override
	public boolean performMatch(Map<String, String> inputAttributes, Map<String, String> storedAttributes) {
		boolean matches = false;
		
		String inputInstitution = MapUtils.getString(inputAttributes, KPMERoleMemberAttribute.INSTITUION.getRoleMemberAttributeName());
		String storeInstitution = MapUtils.getString(storedAttributes, KPMERoleMemberAttribute.INSTITUION.getRoleMemberAttributeName());
		
		if (storeInstitution != null) {
			matches = ObjectUtils.equals(inputInstitution, storeInstitution) || ObjectUtils.equals(inputInstitution, "%");
		}
		
		return matches;
	}

}
