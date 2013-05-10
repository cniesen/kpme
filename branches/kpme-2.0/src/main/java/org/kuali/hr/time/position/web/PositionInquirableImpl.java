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
package org.kuali.hr.time.position.web;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.hr.time.base.web.TkInquirableImpl;
import org.kuali.hr.time.position.Position;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.rice.krad.bo.BusinessObject;

public class PositionInquirableImpl extends TkInquirableImpl {

	private static final long serialVersionUID = 6232629850941402875L;

	@Override
	@SuppressWarnings("rawtypes")
	public BusinessObject getBusinessObject(Map fieldValues) {
		Position position = null;
		
		if (StringUtils.isNotBlank((String)fieldValues.get("hrPositionId"))) {
			position = TkServiceLocator.getPositionService().getPosition((String) fieldValues.get("hrPositionId"));

		} else if(StringUtils.isNotBlank((String)fieldValues.get("positionNumber"))
					&& StringUtils.isNotBlank((String)fieldValues.get("effectiveDate"))) {
			String positionNumber = (String) fieldValues.get("positionNumber");
			LocalDate effectiveDate = TKUtils.formatDateString((String) fieldValues.get("effectiveDate"));
			
		    position = TkServiceLocator.getPositionService().getPosition(positionNumber, effectiveDate);
		} else {
			position = (Position) super.getBusinessObject(fieldValues);
		}

		return position;
	}
}
