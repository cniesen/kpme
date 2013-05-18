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
package org.kuali.hr.time.timeblock.service;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.timeblock.TimeBlockHistory;
import org.kuali.hr.time.timeblock.TimeBlockHistoryDetail;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TimeBlockHistoryLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String DOCUMENT_STATUS = "timesheetDocumentHeader.documentStatus";
    private static final String BEGIN_DATE = "beginDate";
    private static final String BEGIN_TIMESTAMP = "beginTimestamp";

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<TimeBlockHistory> results = new ArrayList<TimeBlockHistory>();

        if (fieldValues.containsKey(BEGIN_DATE)) {
            //beginDate = fieldValues.get(BEGIN_DATE);
            fieldValues.put(BEGIN_TIMESTAMP, fieldValues.get(BEGIN_DATE));
            fieldValues.remove(BEGIN_DATE);
        }

        List<? extends BusinessObject> searchResults = super.getSearchResults(fieldValues);

        for (BusinessObject searchResult : searchResults) {
            TimeBlockHistory timeBlockHistory = (TimeBlockHistory) searchResult;
            results.add(timeBlockHistory);
        }

        results = filterWithSecurity(results);
        results = addDetails(results);

        //Collections.sort(results, new Comparator<TimeBlockHistory>() {
        //    @Override
        //    public int compare(TimeBlockHistory timeBlockHistory1, TimeBlockHistory timeBlockHistory2) {
        //        return timeBlockHistory1.getTkTimeBlockHistoryId().compareTo(timeBlockHistory2.getTkTimeBlockHistoryId());
        //    }
        //});

        return results;
    }

    private List<TimeBlockHistory> filterWithSecurity(List<TimeBlockHistory> objectList) {
        List<TimeBlockHistory> results = new ArrayList<TimeBlockHistory>();
        results.addAll(objectList);
        Iterator<? extends BusinessObject> itr = results.iterator();
        List<TkRole> tkRoles = TkServiceLocator.getTkRoleService().getRoles(TKContext.getPrincipalId(), TKUtils.getCurrentDate());
        while(itr.hasNext()){
            TimeBlockHistory tbhd = (TimeBlockHistory)itr.next();
            Job job = TkServiceLocator.getJobService().getJob(tbhd.getPrincipalId(), tbhd.getJobNumber(), tbhd.getEndTimestamp(), false);
            boolean valid = false;
            for (TkRole tkRole : tkRoles) {
                if (StringUtils.equals(tkRole.getRoleName(),
                        TkConstants.ROLE_TK_SYS_ADMIN)
                        || (StringUtils.equals(tkRole.getRoleName(), TkConstants.ROLE_TK_GLOBAL_VO))
                        || (StringUtils.equals(tkRole.getRoleName(),
                        TkConstants.ROLE_TK_APPROVER) && tbhd.getWorkArea().equals(tkRole.getWorkArea()))
                        || (StringUtils.equals(tkRole.getRoleName(),
                        TkConstants.ROLE_TK_DEPT_ADMIN) && (job != null && (job.getDept().equals(tkRole.getDepartment()))))) {
                    valid = true;
                    break;
                }
                if(StringUtils.equals(tkRole.getRoleName(), TkConstants.ROLE_TK_LOCATION_ADMIN) && job != null && tkRole.getLocationObj()!=null){
                    List<Department> departments = TkServiceLocator.getDepartmentService().getDepartmentByLocation(tkRole.getLocationObj().getLocation());
                    for(Department department : departments){
                        if(StringUtils.equals(job.getDept(), department.getDept())){
                            valid = true;
                            break;
                        }
                    }
                    if(valid){
                        break;
                    }
                }
            }
            if (!valid) {
                itr.remove();
                continue;
            }
        }
        return results;
    }

    private List<TimeBlockHistory> addDetails(List<TimeBlockHistory> timeBlockHistories) {
        List<TimeBlockHistory> results = new ArrayList<TimeBlockHistory>(timeBlockHistories);

        for (TimeBlockHistory timeBlockHistory : timeBlockHistories) {
            List<TimeBlockHistoryDetail> timeBlockHistoryDetails = timeBlockHistory.getTimeBlockHistoryDetails();

            for (TimeBlockHistoryDetail timeBlockHistoryDetail : timeBlockHistoryDetails) {
                if (!timeBlockHistoryDetail.getEarnCode().equalsIgnoreCase(timeBlockHistory.getEarnCode())) {
                    TimeBlockHistory newTimeBlockHistory = timeBlockHistory.copy();
                    newTimeBlockHistory.setEarnCode(timeBlockHistoryDetail.getEarnCode());
                    newTimeBlockHistory.setHours(timeBlockHistoryDetail.getHours());
                    newTimeBlockHistory.setAmount(timeBlockHistoryDetail.getAmount());
                    results.add(newTimeBlockHistory);
                }
            }
        }

        return results;
    }
}
