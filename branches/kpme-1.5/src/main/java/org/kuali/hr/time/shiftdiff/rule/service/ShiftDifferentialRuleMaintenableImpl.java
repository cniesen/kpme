/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.hr.time.shiftdiff.rule.service;

import java.util.Map;

import org.kuali.hr.time.HrBusinessObject;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.shiftdiff.rule.ShiftDifferentialRule;
import org.kuali.hr.time.util.HrBusinessObjectMaintainableImpl;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;

public class ShiftDifferentialRuleMaintenableImpl extends HrBusinessObjectMaintainableImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void processAfterNew(MaintenanceDocument document,
			Map<String, String[]> parameters) {
		super.processAfterNew(document, parameters);
	}

	@Override
	public void processAfterPost(MaintenanceDocument document,
			Map<String, String[]> parameters) {
		ShiftDifferentialRule shiftDifferentialRule = (ShiftDifferentialRule) document
				.getNewMaintainableObject().getBusinessObject();
		shiftDifferentialRule.setUserPrincipalId(GlobalVariables
				.getUserSession().getPrincipalId());
		super.processAfterPost(document, parameters);
	}

	@Override
	public void processAfterEdit(MaintenanceDocument document,
			Map<String, String[]> parameters) {
		ShiftDifferentialRule shiftDifferentialRule = (ShiftDifferentialRule) document
				.getNewMaintainableObject().getBusinessObject();
		shiftDifferentialRule.setUserPrincipalId(GlobalVariables
				.getUserSession().getPrincipalId());
		super.processAfterEdit(document, parameters);
	}


	@Override
	public HrBusinessObject getObjectById(String id) {
		return TkServiceLocator.getShiftDifferentialRuleService().getShiftDifferentialRule(id);
	}

}
