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
package org.kuali.kpme.tklm.time.rules.lunch.department;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.core.bo.HrKeyedBusinessObject;
import org.kuali.kpme.core.department.DepartmentBo;
import org.kuali.kpme.core.job.JobBo;
import org.kuali.kpme.core.workarea.WorkAreaBo;
import org.kuali.kpme.tklm.api.common.TkConstants;
import org.kuali.kpme.tklm.api.time.rules.lunch.department.DeptLunchRuleContract;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "TK_DEPT_LUNCH_RL_T")
public class DeptLunchRule extends HrKeyedBusinessObject implements DeptLunchRuleContract {

    static class KeyFields {

        private static final String JOB_NUMBER = "jobNumber";

        private static final String PRINCIPAL_ID = "principalId";

        private static final String WORK_AREA = "workArea";

        private static final String DEPT = "dept";

        private static final String GROUP_KEY_CODE = "groupKeyCode";
    }

    public static final String CACHE_NAME = TkConstants.Namespace.NAMESPACE_PREFIX + "DeptLunchRule";

    //KPME-2273/1965 Primary Business Keys List. 
    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(KeyFields.DEPT).add(KeyFields.WORK_AREA).add(KeyFields.PRINCIPAL_ID).add(KeyFields.JOB_NUMBER).add(KeyFields.GROUP_KEY_CODE).build();

    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "TK_DEPT_LUNCH_RL_S")
    @GeneratedValue(generator = "TK_DEPT_LUNCH_RL_S")
    @Id
    @Column(name = "TK_DEPT_LUNCH_RL_ID", length = 60)
    private String tkDeptLunchRuleId;

    @Column(name = "DEPT", nullable = false, length = 21)
    private String dept;

    @Column(name = "WORK_AREA")
    private Long workArea;

    @Column(name = "PRINCIPAL_ID", nullable = false, length = 40)
    private String principalId;

    @Column(name = "JOB_NUMBER")
    private Long jobNumber;

    @Column(name = "DEDUCTION_MINS")
    private BigDecimal deductionMins;

    @Column(name = "SHIFT_HOURS")
    private BigDecimal shiftHours;

    @Transient
    private String tkWorkAreaId;

    @Transient
    private String hrDeptId;

    @Transient
    private String hrJobId;

    @Transient
    private transient WorkAreaBo workAreaObj;

    @Transient
    private transient DepartmentBo departmentObj;

    @Transient
    private transient JobBo job;

    @Transient
    private transient Person principal;

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(KeyFields.DEPT, this.getDept()).put(KeyFields.WORK_AREA, this.getWorkArea()).put(KeyFields.PRINCIPAL_ID, this.getPrincipalId()).put(KeyFields.JOB_NUMBER, this.getJobNumber()).put(KeyFields.GROUP_KEY_CODE, this.getGroupKeyCode()).build();
    }

    //for lookups 
    @Transient
    private boolean history;

    public Person getPrincipal() {
        return principal;
    }

    public void setPrincipal(Person principal) {
        this.principal = principal;
    }

    public JobBo getJob() {
        return job;
    }

    public void setJob(JobBo job) {
        this.job = job;
    }

    public WorkAreaBo getWorkAreaObj() {
        return workAreaObj;
    }

    public void setWorkAreaObj(WorkAreaBo workAreaObj) {
        this.workAreaObj = workAreaObj;
    }

    public DepartmentBo getDepartmentObj() {
        return departmentObj;
    }

    public void setDepartmentObj(DepartmentBo departmentObj) {
        this.departmentObj = departmentObj;
    }

    public Long getWorkArea() {
        return workArea;
    }

    public void setWorkArea(Long workArea) {
        this.workArea = workArea;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public Long getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(Long jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getTkDeptLunchRuleId() {
        return tkDeptLunchRuleId;
    }

    public void setTkDeptLunchRuleId(String tkDeptLunchRuleId) {
        this.tkDeptLunchRuleId = tkDeptLunchRuleId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public BigDecimal getDeductionMins() {
        return deductionMins;
    }

    public void setDeductionMins(BigDecimal deductionMins) {
        this.deductionMins = deductionMins;
    }

    public BigDecimal getShiftHours() {
        return shiftHours;
    }

    public void setShiftHours(BigDecimal shiftHours) {
        this.shiftHours = shiftHours;
    }

    public String getTkWorkAreaId() {
        return tkWorkAreaId;
    }

    public void setTkWorkAreaId(String tkWorkAreaId) {
        this.tkWorkAreaId = tkWorkAreaId;
    }

    public String getHrDeptId() {
        return hrDeptId;
    }

    public void setHrDeptId(String hrDeptId) {
        this.hrDeptId = hrDeptId;
    }

    public String getHrJobId() {
        return hrJobId;
    }

    public void setHrJobId(String hrJobId) {
        this.hrJobId = hrJobId;
    }

    @Override
    public String getUniqueKey() {
        return getDept() + "_" + getWorkArea() != null ? getWorkArea().toString() : "" + "_" + getPrincipalId() + "_" + getJobNumber() != null ? getJobNumber().toString() : "";
    }

    @Override
    public String getId() {
        return getTkDeptLunchRuleId();
    }

    @Override
    public void setId(String id) {
        setTkDeptLunchRuleId(id);
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }
}
