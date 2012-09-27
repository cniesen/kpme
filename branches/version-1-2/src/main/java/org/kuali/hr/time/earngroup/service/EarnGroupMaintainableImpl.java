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
package org.kuali.hr.time.earngroup.service;

import org.kuali.hr.time.HrBusinessObject;
import org.kuali.hr.time.earngroup.EarnGroup;
import org.kuali.hr.time.earngroup.EarnGroupDefinition;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.HrBusinessObjectMaintainableImpl;
import org.kuali.hr.time.util.ValidationUtils;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EarnGroupMaintainableImpl extends HrBusinessObjectMaintainableImpl{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
    public void addNewLineToCollection(String collectionName) {
        if (collectionName.equals("earnGroups")) {
        	EarnGroupDefinition definition = (EarnGroupDefinition)newCollectionLines.get(collectionName );
            if ( definition != null ) {
            	EarnGroup earnGroup = (EarnGroup)this.getBusinessObject();
            	Set<String> earnCodes = new HashSet<String>();
            	for(EarnGroupDefinition earnGroupDef : earnGroup.getEarnGroups()){
            		earnCodes.add(earnGroupDef.getEarnCode());
            	}
            	if(earnCodes.contains(definition.getEarnCode())){
            		GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE +"earnGroups",
            				"earngroup.duplicate.earncode",definition.getEarnCode());
            		return;
    			} 
            	if (!ValidationUtils.validateEarnCode(definition.getEarnCode().toUpperCase(), earnGroup.getEffectiveDate())) {
    				GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE +"earnGroups",
    							"error.existence", "Earncode '" + definition.getEarnCode()+ "'");
    				return;
    			} 
            }
        }
       super.addNewLineToCollection(collectionName);
    }
	
	@Override
    public void processAfterEdit( MaintenanceDocument document, Map<String,String[]> parameters ) {
		EarnGroup earnGroup = (EarnGroup)this.getBusinessObject();
		int count = TkServiceLocator.getEarnGroupService().getNewerEarnGroupCount(earnGroup.getEarnGroup(), earnGroup.getEffectiveDate());
		if(count > 0) {
			GlobalVariables.getMessageMap().putWarningWithoutFullErrorPath(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE + "effectiveDate",
					"earngroup.effectiveDate.newr.exists");
		}
	}

	@Override
	public HrBusinessObject getObjectById(String id) {
		return TkServiceLocator.getEarnGroupService().getEarnGroup(id);
	} 
	
}
