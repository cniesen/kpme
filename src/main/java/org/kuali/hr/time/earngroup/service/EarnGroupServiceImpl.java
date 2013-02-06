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
package org.kuali.hr.time.earngroup.service;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.earngroup.EarnGroup;
import org.kuali.hr.time.earngroup.EarnGroupDefinition;
import org.kuali.hr.time.earngroup.dao.EarnGroupDaoService;
import org.kuali.hr.time.timeblock.TimeBlock;
import org.kuali.hr.time.timesheet.TimesheetDocument;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EarnGroupServiceImpl implements EarnGroupService {
    private EarnGroupDaoService earnGroupDao;

    @Override
    public EarnGroup getEarnGroup(String earnGroup, Date asOfDate) {
        return earnGroupDao.getEarnGroup(earnGroup, asOfDate);
    }

    public EarnGroupDaoService getEarnGroupDao() {
        return earnGroupDao;
    }

    public void setEarnGroupDao(EarnGroupDaoService earnGroupDao) {
        this.earnGroupDao = earnGroupDao;
    }

    @Override
    public EarnGroup getEarnGroupSummaryForEarnCode(String earnCode, Date asOfDate) {
        return earnGroupDao.getEarnGroupSummaryForEarnCode(earnCode, asOfDate);
    }

    @Override
    public EarnGroup getEarnGroupForEarnCode(String earnCode, Date asOfDate) {
        return earnGroupDao.getEarnGroupForEarnCode(earnCode, asOfDate);
    }

    public Set<String> getEarnCodeListForEarnGroup(String earnGroup, Date asOfDate) {
        Set<String> earnCodes = new HashSet<String>();
        EarnGroup earnGroupObj = earnGroupDao.getEarnGroup(earnGroup, asOfDate);
        if ( earnGroupObj != null ) {
            for (EarnGroupDefinition earnGroupDef : earnGroupObj.getEarnGroups()) {
                if (!earnCodes.contains(earnGroupDef.getEarnCode())) {
                    earnCodes.add(earnGroupDef.getEarnCode());
                }
            }
        }
        return earnCodes;
    }

    @Override
    public EarnGroup getEarnGroup(String hrEarnGroupId) {
        return earnGroupDao.getEarnGroup(hrEarnGroupId);
    }
    
    @Override
    public List<String> warningTextFromEarnGroupsOfDocument(TimesheetDocument timesheetDocument) {
    	 List<String> warningMessages = new ArrayList<String>();
	     List<TimeBlock> tbList = timesheetDocument.getTimeBlocks();
	     if (tbList.isEmpty()) {
	         return warningMessages;
	     }
	     
	     Set<String> aSet = new HashSet<String>();
	     for(TimeBlock tb : tbList) {
	    	EarnGroup eg = this.getEarnGroupForEarnCode(tb.getEarnCode(), tb.getBeginDate());
	    	if(eg != null && !StringUtils.isEmpty(eg.getWarningText())) {
	    		aSet.add(eg.getWarningText());
	    	}
	     }
	    warningMessages.addAll(aSet);
		return warningMessages;
    }
    @Override
    public int getEarnGroupCount(String earnGroup) {
    	return earnGroupDao.getEarnGroupCount(earnGroup);
    }
    @Override
    public int getNewerEarnGroupCount(String earnGroup, Date effdt) {
    	return earnGroupDao.getNewerEarnGroupCount(earnGroup, effdt);
    }
}
