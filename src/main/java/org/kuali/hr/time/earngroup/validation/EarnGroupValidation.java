/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.hr.time.earngroup.validation;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.earngroup.EarnGroup;
import org.kuali.hr.time.earngroup.EarnGroupDefinition;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.ValidationUtils;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocator;

import java.util.*;

public class EarnGroupValidation  extends MaintenanceDocumentRuleBase{

	@Override
	protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
		EarnGroup earnGroup = (EarnGroup)this.getNewBo();
		Set<String> earnCodes = new HashSet<String>();
		int index = 0;
		if(earnGroup.getEarnGroups().size() < 1){
			this.putGlobalError("earncode.required");
			return false;
		}
		for(EarnGroupDefinition earnGroupDef : earnGroup.getEarnGroups()){
			if(earnCodes.contains(earnGroupDef.getEarnCode())){
				this.putFieldError("earnGroups["+index+"].earnCode", "earngroup.duplicate.earncode",earnGroupDef.getEarnCode());

			}
			if(earnGroup.getShowSummary()) {
				validateEarnCode(earnGroupDef.getEarnCode().toUpperCase(), index, earnGroup);
			}
			if (!ValidationUtils.validateEarnCode(earnGroupDef.getEarnCode(), earnGroup.getEffectiveDate())) {
				this.putFieldError("earnGroups["+index+"].earnCode", "error.existence", "Earncode '" + earnGroupDef.getEarnCode()+ "'");
			}
			earnCodes.add(earnGroupDef.getEarnCode());
			index++;
		}
		int count = TkServiceLocator.getEarnGroupService().getNewerEarnGroupCount(earnGroup.getEarnGroup(), earnGroup.getEffectiveDate());
		if(count > 0) {
			this.putFieldError("effectiveDate", "earngroup.effectiveDate.newr.exists");
			return false;
		}
		return true;
	}

    protected void validateEarnCode(String earnCode, int index, EarnGroup editedEarnGroup) {
    	BusinessObjectService businessObjectService = KRADServiceLocator.getBusinessObjectService();
    	Map<String,Object> criteria = new HashMap<String,Object>();
		criteria.put("showSummary", "Y");
		criteria.put("active", "Y");
    	Collection aCol = businessObjectService.findMatching(EarnGroup.class, criteria);
		Iterator<EarnGroup> itr = aCol.iterator();
		while (itr.hasNext()) {
			EarnGroup earnGroup = itr.next();
			if(!earnGroup.getHrEarnGroupId().equals(editedEarnGroup.getHrEarnGroupId())) {
				criteria = new HashMap<String,Object>();
				criteria.put("hrEarnGroupId", earnGroup.getHrEarnGroupId());

				Collection earnGroupDefs = businessObjectService.findMatching(EarnGroupDefinition.class, criteria);
				Iterator<EarnGroupDefinition> iterator = earnGroupDefs.iterator();
				while (iterator.hasNext()) {
					EarnGroupDefinition def = iterator.next();
					if(StringUtils.equals(earnCode, def.getEarnCode().toUpperCase())) {
						String[] parameters = new String[2];
						parameters[0] = earnCode;
						parameters[1] = earnGroup.getDescr();
						this.putFieldError("earnGroups["+index+"].earnCode", "earngroup.earncode.already.used", parameters);
					}
				}
			}
		}
    }

 }
