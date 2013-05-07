/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.hr.time.position.validation;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.hr.time.position.Position;
import org.kuali.hr.time.util.ValidationUtils;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.rules.MaintenanceDocumentRuleBase;

public class PositionValidation extends MaintenanceDocumentRuleBase {

	private boolean validateInstitution(String institution, LocalDate asOfDate) {
		boolean valid = true;
		
		if (!StringUtils.isBlank(institution)) {
			valid = ValidationUtils.validateInstitution(institution, asOfDate);

			if (!valid) {
				this.putFieldError("institution", "paytype.institution.invalid", institution);
			} 			
		}
		
		return valid;
	}
	
	private boolean validateCampus(String campus) {
		boolean valid = true;
		
		if (!StringUtils.isBlank(campus)) {
			valid = ValidationUtils.validateCampus(campus);

			if (!valid) {
				this.putFieldError("campus", "paytype.campus.invalid", campus);
			} 			
		}

		return valid;
	}
	
	
	@Override
	protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
		boolean valid = false;
		LOG.debug("entering custom validation for Position");
		
		Position position = (Position)this.getNewDataObject();

		if (position != null) {
			valid &= validateInstitution(position.getInstitution(), position.getEffectiveLocalDate());
			valid &= validateCampus(position.getCampus());
		}
		
		return valid;
	}
	
	
}