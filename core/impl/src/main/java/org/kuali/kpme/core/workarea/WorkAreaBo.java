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
package org.kuali.kpme.core.workarea;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.core.api.assignment.Assignment;
import org.kuali.kpme.core.api.workarea.WorkArea;
import org.kuali.kpme.core.api.workarea.WorkAreaContract;
import org.kuali.kpme.core.assignment.AssignmentBo;
import org.kuali.kpme.core.bo.HrKeyedBusinessObject;
import org.kuali.kpme.core.department.DepartmentBo;
import org.kuali.kpme.core.earncode.EarnCodeBo;
import org.kuali.kpme.core.groupkey.HrGroupKeyBo;
import org.kuali.kpme.core.role.workarea.WorkAreaPositionRoleMemberBo;
import org.kuali.kpme.core.role.workarea.WorkAreaPrincipalRoleMemberBo;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.task.TaskBo;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

//public class WorkAreaBo extends HrBusinessObject implements WorkAreaContract { 
@Entity
@Table(name = "TK_WORK_AREA_T")
public class WorkAreaBo extends HrKeyedBusinessObject implements WorkAreaContract {

    private static final String WORK_AREA = "workArea";

    private static final long serialVersionUID = 2637145083387914260L;

    public static final String CACHE_NAME = HrConstants.CacheNamespace.NAMESPACE_PREFIX + "WorkArea";

    //KPME-2273/1965 Primary Business Keys List.	 
    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(WORK_AREA).build();

    public static final ModelObjectUtils.Transformer<WorkAreaBo, WorkArea> toWorkArea = new ModelObjectUtils.Transformer<WorkAreaBo, WorkArea>() {

        public WorkArea transform(WorkAreaBo input) {
            return WorkAreaBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<WorkArea, WorkAreaBo> toWorkAreaBo = new ModelObjectUtils.Transformer<WorkArea, WorkAreaBo>() {

        public WorkAreaBo transform(WorkArea input) {
            return WorkAreaBo.from(input);
        }

        ;
    };

    @PortableSequenceGenerator(name = "TK_WORK_AREA_S")
    @GeneratedValue(generator = "TK_WORK_AREA_S")
    @Id
    @Column(name = "TK_WORK_AREA_ID", length = 60)
    private String tkWorkAreaId;

    @PortableSequenceGenerator(name = "TK_WORK_AREA_KEY_S")
    @GeneratedValue(generator = "TK_WORK_AREA_KEY_S")
    @Column(name = "WORK_AREA")
    private Long workArea;

    @Column(name = "DESCR", nullable = false, length = 30)
    private String description;

    @Column(name = "OVERTIME_EDIT_ROLE", length = 20)
    private String overtimeEditRole;

    @Column(name = "DEFAULT_OVERTIME_EARNCODE", length = 15)
    private String defaultOvertimeEarnCode;

    @Transient
    private Boolean ovtEarnCode;

    @Column(name = "DEPT", nullable = false, length = 21)
    private String dept;

    @Column(name = "ADMIN_DESCR", nullable = false, length = 30)
    private String adminDescr;

    @Transient
    private boolean history;

    @Column(name = "HRS_DISTRIBUTION_FL", length = 1)
    @Convert(converter = BooleanYNConverter.class)
    private boolean hrsDistributionF;

    @Transient
    private EarnCodeBo defaultOvertimeEarnCodeObj;

    @Transient
    private DepartmentBo department;

    @Transient
    private List<TaskBo> tasks = new ArrayList<TaskBo>();

    @Transient
    private List<WorkAreaPrincipalRoleMemberBo> principalRoleMembers = new ArrayList<WorkAreaPrincipalRoleMemberBo>();

    @Transient
    private List<WorkAreaPrincipalRoleMemberBo> inactivePrincipalRoleMembers = new ArrayList<WorkAreaPrincipalRoleMemberBo>();

    @Transient
    private List<WorkAreaPositionRoleMemberBo> positionRoleMembers = new ArrayList<WorkAreaPositionRoleMemberBo>();

    @Transient
    private List<WorkAreaPositionRoleMemberBo> inactivePositionRoleMembers = new ArrayList<WorkAreaPositionRoleMemberBo>();

    @Transient
    private List<AssignmentBo> workAreaMembers = new ArrayList<AssignmentBo>();

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(WORK_AREA, this.getWorkArea()).build();
    }

    @Override
    public String getUniqueKey() {
        return workArea != null ? workArea.toString() : "" + "_" + dept;
    }

    @Override
    public String getId() {
        return getTkWorkAreaId();
    }

    @Override
    public void setId(String id) {
        setTkWorkAreaId(id);
    }

    public String getTkWorkAreaId() {
        return tkWorkAreaId;
    }

    public void setTkWorkAreaId(String tkWorkAreaId) {
        this.tkWorkAreaId = tkWorkAreaId;
    }

    public Long getWorkArea() {
        return workArea;
    }

    public void setWorkArea(Long workArea) {
        this.workArea = workArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOvertimeEditRole() {
        return overtimeEditRole;
    }

    public void setOvertimeEditRole(String overtimeEditRole) {
        this.overtimeEditRole = overtimeEditRole;
    }

    public String getDefaultOvertimeEarnCode() {
        return defaultOvertimeEarnCode;
    }

    public void setDefaultOvertimeEarnCode(String defaultOvertimeEarnCode) {
        this.defaultOvertimeEarnCode = defaultOvertimeEarnCode;
    }

    public Boolean getOvtEarnCode() {
        return isOvtEarnCode();
    }

    public Boolean isOvtEarnCode() {
        return ovtEarnCode;
    }

    public void setOvtEarnCode(Boolean ovtEarnCode) {
        this.ovtEarnCode = ovtEarnCode;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAdminDescr() {
        return adminDescr;
    }

    public void setAdminDescr(String adminDescr) {
        this.adminDescr = adminDescr;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    public EarnCodeBo getDefaultOvertimeEarnCodeObj() {
        return defaultOvertimeEarnCodeObj;
    }

    public void setDefaultOvertimeEarnCodeObj(EarnCodeBo defaultOvertimeEarnCodeObj) {
        this.defaultOvertimeEarnCodeObj = defaultOvertimeEarnCodeObj;
    }

    public DepartmentBo getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentBo department) {
        this.department = department;
    }

    public List<TaskBo> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskBo> tasks) {
        this.tasks = tasks;
    }

    public List<WorkAreaPrincipalRoleMemberBo> getPrincipalRoleMembers() {
        return principalRoleMembers;
    }

    public void addPrincipalRoleMember(WorkAreaPrincipalRoleMemberBo principalRoleMember) {
        principalRoleMembers.add(principalRoleMember);
    }

    public void removePrincipalRoleMember(WorkAreaPrincipalRoleMemberBo principalRoleMember) {
        principalRoleMembers.remove(principalRoleMember);
    }

    public void setPrincipalRoleMembers(List<WorkAreaPrincipalRoleMemberBo> principalRoleMembers) {
        this.principalRoleMembers = principalRoleMembers;
    }

    public List<WorkAreaPrincipalRoleMemberBo> getInactivePrincipalRoleMembers() {
        return inactivePrincipalRoleMembers;
    }

    public void addInactivePrincipalRoleMember(WorkAreaPrincipalRoleMemberBo inactivePrincipalRoleMember) {
        inactivePrincipalRoleMembers.add(inactivePrincipalRoleMember);
    }

    public void removeInactivePrincipalRoleMember(WorkAreaPrincipalRoleMemberBo inactivePrincipalRoleMember) {
        inactivePrincipalRoleMembers.remove(inactivePrincipalRoleMember);
    }

    public void setInactivePrincipalRoleMembers(List<WorkAreaPrincipalRoleMemberBo> inactivePrincipalRoleMembers) {
        this.inactivePrincipalRoleMembers = inactivePrincipalRoleMembers;
    }

    public List<WorkAreaPositionRoleMemberBo> getPositionRoleMembers() {
        return positionRoleMembers;
    }

    public void addPositionRoleMember(WorkAreaPositionRoleMemberBo positionRoleMember) {
        positionRoleMembers.add(positionRoleMember);
    }

    public void removePositionRoleMember(WorkAreaPositionRoleMemberBo positionRoleMember) {
        positionRoleMembers.remove(positionRoleMember);
    }

    public void setPositionRoleMembers(List<WorkAreaPositionRoleMemberBo> positionRoleMembers) {
        this.positionRoleMembers = positionRoleMembers;
    }

    public List<WorkAreaPositionRoleMemberBo> getInactivePositionRoleMembers() {
        return inactivePositionRoleMembers;
    }

    public void addInactivePositionRoleMember(WorkAreaPositionRoleMemberBo inactivePositionRoleMember) {
        inactivePositionRoleMembers.add(inactivePositionRoleMember);
    }

    public void removeInactivePositionRoleMember(WorkAreaPositionRoleMemberBo inactivePositionRoleMember) {
        inactivePositionRoleMembers.remove(inactivePositionRoleMember);
    }

    public void setInactivePositionRoleMembers(List<WorkAreaPositionRoleMemberBo> inactivePositionRoleMembers) {
        this.inactivePositionRoleMembers = inactivePositionRoleMembers;
    }

    public boolean isHrsDistributionF() {
        return hrsDistributionF;
    }

    public void setHrsDistributionF(boolean hrsDistributionF) {
        this.hrsDistributionF = hrsDistributionF;
    }

    public List<AssignmentBo> getWorkAreaMembers() {
        workAreaMembers = new ArrayList<AssignmentBo>();
        if (workArea != null && getEffectiveLocalDate() != null) {
            List<Assignment> workAreaAssignments = HrServiceLocator.getAssignmentService().getActiveAssignmentsForWorkArea(this.workArea, this.getEffectiveLocalDate());
            for (Assignment assignment : workAreaAssignments) {
                workAreaMembers.add(AssignmentBo.from(assignment));
            }
        }
        return workAreaMembers;
    }

    public void setWorkAreaMembers(List<AssignmentBo> workAreaMemebers) {
        this.workAreaMembers = workAreaMemebers;
    }

    public static WorkAreaBo from(WorkArea im) {
        if (im == null) {
            return null;
        }
        WorkAreaBo wa = new WorkAreaBo();
        wa.setTkWorkAreaId(im.getTkWorkAreaId());
        wa.setWorkArea(im.getWorkArea());
        wa.setDescription(im.getDescription());
        wa.setOvertimeEditRole(im.getOvertimeEditRole());
        wa.setDefaultOvertimeEarnCode(im.getDefaultOvertimeEarnCode());
        wa.setOvtEarnCode(im.isOvtEarnCode());
        wa.setDept(im.getDept());
        wa.setAdminDescr(im.getAdminDescr());
        wa.setHrsDistributionF(im.isHrsDistributionF());
        wa.setDefaultOvertimeEarnCodeObj(im.getDefaultOvertimeEarnCodeObj() == null ? null : EarnCodeBo.from(im.getDefaultOvertimeEarnCodeObj()));
        wa.setDepartment(im.getDepartment() == null ? null : DepartmentBo.from(im.getDepartment()));
        wa.setEffectiveDate(im.getEffectiveLocalDate() == null ? null : im.getEffectiveLocalDate().toDate());
        wa.setActive(im.isActive());
        if (im.getCreateTime() != null) {
            wa.setTimestamp(new Timestamp(im.getCreateTime().getMillis()));
        }
        wa.setUserPrincipalId(im.getUserPrincipalId());
        wa.setVersionNumber(im.getVersionNumber());
        wa.setObjectId(im.getObjectId());
        wa.setGroupKey(im.getGroupKey() == null ? null : HrGroupKeyBo.from(im.getGroupKey()));
        wa.setGroupKeyCode(im.getGroupKeyCode());
        copyCommonFields(wa, im);
        return wa;
    }

    public static WorkArea to(WorkAreaBo bo) {
        if (bo == null) {
            return null;
        }
        return WorkArea.Builder.create(bo).build();
    }
}
