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
package org.kuali.kpme.core.tkmdocument;

import org.kuali.kpme.core.tkmdocument.tkmjob.KhrEmployeeJob;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jwillia on 4/11/14.
 */
public class KhrEmployeeDocument extends PersistableBusinessObjectBase {

	private static final long serialVersionUID = 5006332932081928714L;
	
	private String principalId;
    private String name;
    private Date startDate;
    private Date endDate;

    private String process;

    private List<String> editableDepartmentList = new ArrayList<String>();
    private List<KhrEmployeeJob> jobList = new ArrayList<KhrEmployeeJob>();

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public List<KhrEmployeeJob> getJobList() {
        return jobList;
    }

    public void setJobList(List<KhrEmployeeJob> jobList) {
        this.jobList = jobList;
    }

    public List<String> getEditableDepartmentList() {
        return editableDepartmentList;
    }

    public void setEditableDepartmentList(List<String> editableDepartmentList) {
        this.editableDepartmentList = editableDepartmentList;
    }

    @Override
    public void refreshNonUpdateableReferences() {
        //do nothing
    }


}

