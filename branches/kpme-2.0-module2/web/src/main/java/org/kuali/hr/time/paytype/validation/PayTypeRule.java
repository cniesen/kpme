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
package org.kuali.hr.time.paytype.validation;

import java.util.List;

import org.joda.time.LocalDate;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.paytype.PayType;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.ValidationUtils;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class PayTypeRule extends MaintenanceDocumentRuleBase {

	boolean validateEarnCode(String regEarnCode, LocalDate asOfDate) {
		boolean valid = ValidationUtils.validateEarnCode(regEarnCode, asOfDate);

		if (!valid) {
			this.putFieldError("regEarnCode", "earncode.notfound");
		} else {
			valid = !ValidationUtils.validateEarnCode(regEarnCode, true,
					asOfDate);
			if (!valid) {
				this.putFieldError("regEarnCode", "earncode.ovt.not.required",
						regEarnCode);
			}
		}

		return valid;
	}

	boolean validateActive(String hrPayType, LocalDate asOfDate) {
		boolean valid = true;
		List<Job> jobs = TkServiceLocator.getJobService()
				.getActiveJobsForPayType(hrPayType, asOfDate);
		if (jobs != null && !jobs.isEmpty()) {
			this.putFieldError("active", "paytype.inactivate.locked", hrPayType);
			valid = false;
		}
		return valid;
	}
    
	@Override
	protected boolean processCustomRouteDocumentBusinessRules(
			MaintenanceDocument document) {
		boolean valid = false;

		PersistableBusinessObject pbo = (PersistableBusinessObject) this.getNewBo();
		if (pbo instanceof PayType) {
			PayType pt = (PayType) pbo;

			valid = validateEarnCode(pt.getRegEarnCode(), pt.getEffectiveLocalDate());
			if (document.isOldBusinessObjectInDocument() && !pt.isActive()) {
				valid = validateActive(pt.getPayType(), pt.getEffectiveLocalDate());
			}
		}

		return valid;
	}
}
