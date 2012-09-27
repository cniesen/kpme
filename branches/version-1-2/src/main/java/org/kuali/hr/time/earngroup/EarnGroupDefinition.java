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
package org.kuali.hr.time.earngroup;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.earncode.EarnCode;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import java.util.LinkedHashMap;

public class EarnGroupDefinition extends PersistableBusinessObjectBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8463674251885306591L;

	private String hrEarnGroupDefId;

	private String earnCode;

	private String hrEarnGroupId;

    private EarnCode earnCodeObj;

    // this is for the maintenance screen
    private String earnCodeDesc;

	public String getEarnCode() {
		return earnCode;
	}

	public void setEarnCode(String earnCode) {
		this.earnCode = earnCode;
	}

	public String getHrEarnGroupDefId() {
		return hrEarnGroupDefId;
	}

	public void setHrEarnGroupDefId(String hrEarnGroupDefId) {
		this.hrEarnGroupDefId = hrEarnGroupDefId;
	}

	public String getHrEarnGroupId() {
		return hrEarnGroupId;
	}

	public void setHrEarnGroupId(String hrEarnGroupId) {
		this.hrEarnGroupId = hrEarnGroupId;
	}

	public EarnCode getEarnCodeObj() {
		return earnCodeObj;
	}

	public void setEarnCodeObj(EarnCode earnCodeObj) {
		this.earnCodeObj = earnCodeObj;
	}
	
	// this is for the maintenance screen
	public String getEarnCodeDesc() {
		EarnCode earnCode = TkServiceLocator.getEarnCodeService().getEarnCode(this.earnCode, new java.sql.Date(System.currentTimeMillis()));
		
		if(earnCode != null && StringUtils.isNotBlank(earnCode.getDescription())) {
			return earnCode.getDescription();
		}
		return "";
	}
}