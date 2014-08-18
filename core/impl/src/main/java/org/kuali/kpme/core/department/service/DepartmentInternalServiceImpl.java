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
package org.kuali.kpme.core.department.service;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.bo.dao.EffectiveObjectDao;
import org.kuali.kpme.core.api.namespace.KPMENamespace;
import org.kuali.kpme.core.department.DepartmentBo;
import org.kuali.kpme.core.department.dao.DepartmentDao;
import org.kuali.kpme.core.role.KPMERole;
import org.kuali.kpme.core.role.department.DepartmentPrincipalRoleMemberBo;
import org.kuali.kpme.core.service.role.KPMERoleService;
import org.kuali.rice.kim.api.role.RoleMember;
import org.kuali.rice.kim.impl.role.RoleMemberBo;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.*;


public class DepartmentInternalServiceImpl implements DepartmentInternalService {

    private DataObjectService dataObjectService;
    private EffectiveObjectDao effectiveObjectDao;
    private KPMERoleService kpmeRoleService;

    @Override
    public DepartmentBo getDepartmentWithRoleData(String hrDeptId) {
        DepartmentBo departmentObj = dataObjectService.find(DepartmentBo.class, hrDeptId);

        if (departmentObj != null) {
            populateDepartmentRoleMembers(departmentObj, departmentObj.getEffectiveLocalDate());
        }

        return departmentObj;
    }

    private void populateDepartmentRoleMembers(DepartmentBo department, LocalDate asOfDate) {
        if (department != null && asOfDate != null
                && CollectionUtils.isEmpty(department.getRoleMembers()) && CollectionUtils.isEmpty(department.getInactiveRoleMembers())) {
            Set<RoleMember> roleMembers = new HashSet<RoleMember>();

            roleMembers.addAll(kpmeRoleService.getRoleMembersInDepartment(KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_DEPARTMENT_VIEW_ONLY.getRoleName(), department.getDept(), department.getGroupKeyCode(), asOfDate.toDateTimeAtStartOfDay(), false));
            roleMembers.addAll(kpmeRoleService.getRoleMembersInDepartment(KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_DEPARTMENT_ADMINISTRATOR.getRoleName(), department.getDept(), department.getGroupKeyCode(), asOfDate.toDateTimeAtStartOfDay(), false));
            roleMembers.addAll(kpmeRoleService.getRoleMembersInDepartment(KPMENamespace.KPME_LM.getNamespaceCode(), KPMERole.LEAVE_DEPARTMENT_VIEW_ONLY.getRoleName(), department.getDept(), department.getGroupKeyCode(), asOfDate.toDateTimeAtStartOfDay(), false));
            roleMembers.addAll(kpmeRoleService.getRoleMembersInDepartment(KPMENamespace.KPME_LM.getNamespaceCode(), KPMERole.LEAVE_DEPARTMENT_ADMINISTRATOR.getRoleName(), department.getDept(), department.getGroupKeyCode(), asOfDate.toDateTimeAtStartOfDay(), false));

            roleMembers.addAll(kpmeRoleService.getRoleMembersInDepartment(KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.PAYROLL_PROCESSOR.getRoleName(), department.getDept(), department.getGroupKeyCode(), asOfDate.toDateTimeAtStartOfDay(), false));
            roleMembers.addAll(kpmeRoleService.getRoleMembersInDepartment(KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.PAYROLL_PROCESSOR_DELEGATE.getRoleName(), department.getDept(), department.getGroupKeyCode(), asOfDate.toDateTimeAtStartOfDay(), false));

            for (RoleMember roleMember : roleMembers) {
                RoleMemberBo roleMemberBo = RoleMemberBo.from(roleMember);

                if (roleMemberBo.isActive()) {
                    department.addRoleMember(DepartmentPrincipalRoleMemberBo.from(roleMemberBo, roleMember.getAttributes()));
                } else {
                    department.addInactiveRoleMember(DepartmentPrincipalRoleMemberBo.from(roleMemberBo, roleMember.getAttributes()));
                }
            }
        }
    }

    @Override
    public DepartmentBo getDepartmentWithRoleData(String department, String groupKeyCode, LocalDate asOfDate) {
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("dept", department);
        criteria.put("groupKeyCode", groupKeyCode);
        DepartmentBo departmentBo =  effectiveObjectDao.getSingleEffectiveObject(DepartmentBo.class, DepartmentBo.BUSINESS_KEYS, criteria, asOfDate);

        if (departmentBo != null) {
            populateDepartmentRoleMembers(departmentBo, asOfDate);
        }

        return departmentBo;
    }

    public void setKpmeRoleService(KPMERoleService kpmeRoleService) {
        this.kpmeRoleService = kpmeRoleService;
    }


    public void setEffectiveObjectDao(EffectiveObjectDao effectiveObjectDao) {
        this.effectiveObjectDao = effectiveObjectDao;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
