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

import org.kuali.hr.time.HrBusinessObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class EarnGroup extends HrBusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3034933572755800531L;

	private String hrEarnGroupId;

	private String earnGroup;

	private String descr;

	private Boolean history;
	
	private Boolean showSummary;

	private List<EarnGroupDefinition> earnGroups = new ArrayList<EarnGroupDefinition>();
	
	private String warningText;

	public String getEarnGroup() {
		return earnGroup;
	}

	public void setEarnGroup(String earnGroup) {
		this.earnGroup = earnGroup;
	}


	public Boolean getHistory() {
		return history;
	}

	public void setHistory(Boolean history) {
		this.history = history;
	}


	public void setEarnGroups(List<EarnGroupDefinition> earnGroups) {
		this.earnGroups = earnGroups;
	}

	public List<EarnGroupDefinition> getEarnGroups() {
		return earnGroups;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getDescr() {
		return descr;
	}

	public String getHrEarnGroupId() {
		return hrEarnGroupId;
	}

	public void setHrEarnGroupId(String hrEarnGroupId) {
		this.hrEarnGroupId = hrEarnGroupId;
	}
	
	public Boolean getShowSummary() {
		return showSummary;
	}

	public void setShowSummary(Boolean showSummary) {
		this.showSummary = showSummary;
	}

	@Override
	public String getUniqueKey() {
		return earnGroup;
	}

	@Override
	public String getId() {
		return getHrEarnGroupId();
	}

	@Override
	public void setId(String id) {
		setHrEarnGroupId(id);
	}

	public String getWarningText() {
		return warningText;
	}

	public void setWarningText(String warningText) {
		this.warningText = warningText;
	}

}
