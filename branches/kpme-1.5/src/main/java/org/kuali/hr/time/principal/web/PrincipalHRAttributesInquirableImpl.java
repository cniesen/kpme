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
package org.kuali.hr.time.principal.web;


import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.base.web.TkInquirableImpl;
import org.kuali.hr.time.principal.PrincipalHRAttributes;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.rice.krad.bo.BusinessObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class PrincipalHRAttributesInquirableImpl extends TkInquirableImpl {

	@Override
	public BusinessObject getBusinessObject(Map fieldValues) {
		PrincipalHRAttributes principalAttributes = null;
		if(StringUtils.isNotBlank((String)fieldValues.get("hrPrincipalAttributeId"))) {
            principalAttributes = TkServiceLocator.getPrincipalHRAttributeService().getPrincipalHRAttributes((String) fieldValues.get("hrPrincipalAttributeId"));

		} else if(StringUtils.isNotBlank((String)fieldValues.get("principalId"))
					&& StringUtils.isNotBlank((String)fieldValues.get("effectiveDate"))) {
			java.util.Date uDate = null;
			try {
				uDate = new SimpleDateFormat("MM/dd/yyyy").parse(fieldValues.get("effectiveDate").toString());

			    Date effdt = new Date(uDate.getTime());
                principalAttributes = TkServiceLocator.getPrincipalHRAttributeService().getPrincipalCalendar((String) fieldValues.get("principalId"), effdt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
			
		} else {
            principalAttributes = (PrincipalHRAttributes) super.getBusinessObject(fieldValues);
		}

		return principalAttributes;
	}
}
