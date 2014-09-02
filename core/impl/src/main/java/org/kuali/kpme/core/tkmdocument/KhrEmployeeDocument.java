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

