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
package org.kuali.hr.time.timesummary;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONValue;
import org.kuali.hr.lm.leaveSummary.LeaveSummaryRow;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class TimeSummary implements Serializable {
	private List<String> summaryHeader = new ArrayList<String>();
	private List<EarnGroupSection> sections = new ArrayList<EarnGroupSection>();
	private List<LeaveSummaryRow> maxedLeaveRows = new ArrayList<LeaveSummaryRow>();
	private List<BigDecimal> workedHours = new ArrayList<BigDecimal>();

	public List<String> getSummaryHeader() {
		return summaryHeader;
	}
	public void setSummaryHeader(List<String> summaryHeader) {
		this.summaryHeader = summaryHeader;
	}
	public List<EarnGroupSection> getSections() {
		return sections;
	}
	public void setSections(List<EarnGroupSection> sections) {
		this.sections = sections;
	}
	public List<BigDecimal> getWorkedHours() {
		return workedHours;
	}
	public void setWorkedHours(List<BigDecimal> workedHours) {
		this.workedHours = workedHours;
	}

    public String toJsonString() {
        List<Map<String, Object>> earnGroupSections = new ArrayList<Map<String, Object>>();

        //Lets add a fake EarnGroupSection for worked hours!!!
        Map<String, Object> workedHours = new HashMap<String, Object>();
        workedHours.put("totals", getWorkedHours());
        workedHours.put("earnGroup", "Worked Hours");
        workedHours.put("earnCodeSections", new HashMap<String, Object>());

        //Map<String, Object> workedHoursECS = new HashMap<String, Object>();
        //workedHoursECS.put("earnCode", "");
        ///workedHoursECS.put("desc", "Worked Hours");
        //workedHoursECS.put("totals", getWorkedHours());
        //workedHoursECS.put("isAmountEarnCode", earnCodeSection.getIsAmountEarnCode());

        for (EarnGroupSection earnGroupSection : this.sections) {
            List<Map<String, Object>> earnCodeSections = new ArrayList<Map<String, Object>>();
            Map<String, Object> egs = new TreeMap<String, Object>();
            egs.put("earnGroup", earnGroupSection.getEarnGroup());
            egs.put("totals", earnGroupSection.getTotals());

            for (EarnCodeSection earnCodeSection : earnGroupSection.getEarnCodeSections()) {
                Map<String, Object> ecs = new TreeMap<String, Object>();

                ecs.put("earnCode", earnCodeSection.getEarnCode());
                ecs.put("desc", earnCodeSection.getDescription());
                ecs.put("totals", earnCodeSection.getTotals());
                ecs.put("isAmountEarnCode", earnCodeSection.getIsAmountEarnCode());

                List<Map<String, Object>> assignmentRows = new ArrayList<Map<String, Object>>();
                for (AssignmentRow assignmentRow : earnCodeSection.getAssignmentsRows()) {
                    Map<String, Object> ar = new TreeMap<String, Object>();

                    ar.put("descr", assignmentRow.getDescr());
                    ar.put("assignmentKey", assignmentRow.getAssignmentKey());
                    ar.put("cssClass", assignmentRow.getCssClass());
                    ar.put("earnCode", earnCodeSection.getEarnCode());
                    ecs.put("earnGroup", earnGroupSection.getEarnGroup());
                    ecs.put("totals", earnGroupSection.getTotals());
                    
                    List<Map<String, Object>> assignmentColumns = new ArrayList<Map<String, Object>>();
                    for (AssignmentColumn assignmentColumn : assignmentRow.getAssignmentColumns()) {
                    	Map<String, Object> ac = new TreeMap<String, Object>();
                    	
                    	ac.put("cssClass", assignmentColumn.getCssClass());
                    	ac.put("amount", assignmentColumn.getAmount());
                    	ac.put("total", assignmentColumn.getTotal());
                    	ac.put("isWeeklyTotal", assignmentColumn.isWeeklyTotal());
                    	
                    	assignmentColumns.add(ac);
                    }
                    ar.put("assignmentColumns", assignmentColumns);

                    assignmentRows.add(ar);
                }
                ecs.put("assignmentRows", assignmentRows);

                earnCodeSections.add(ecs);
            }
            egs.put("earnCodeSections", earnCodeSections);
            earnGroupSections.add(egs);
        }
        earnGroupSections.add(workedHours);

        return JSONValue.toJSONString(earnGroupSections);
    }
	public List<LeaveSummaryRow> getMaxedLeaveRows() {
		return maxedLeaveRows;
	}
	public void setMaxedLeaveRows(List<LeaveSummaryRow> maxedLeaveRows) {
		this.maxedLeaveRows = maxedLeaveRows;
	}

}
