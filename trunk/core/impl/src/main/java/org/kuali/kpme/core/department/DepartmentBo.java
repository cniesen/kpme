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
package org.kuali.kpme.core.department;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.kuali.kpme.core.api.department.Department;
import org.kuali.kpme.core.api.department.DepartmentContract;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.groupkey.HrGroupKeyBo;
import org.kuali.kpme.core.kfs.coa.businessobject.Chart;
import org.kuali.kpme.core.kfs.coa.businessobject.Organization;
import org.kuali.kpme.core.location.LocationBo;
import org.kuali.kpme.core.role.department.DepartmentPrincipalRoleMemberBo;
import org.kuali.kpme.core.util.HrConstants;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class DepartmentBo extends HrBusinessObject implements DepartmentContract {

    static class KeyFields {

        final static String DEPT = "dept";
        final static String GROUP_KEY_CODE = "groupKeyCode";

    }

	private static final long serialVersionUID = 5476378484272246487L;

	public static final String CACHE_NAME = HrConstants.CacheNamespace.NAMESPACE_PREFIX + "Department";
	//KPME-2273/1965 Primary Business Keys List.		
	public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>()
            .add(KeyFields.DEPT)
            //.add(KeyFields.GROUP_KEY_CODE)
            .build();

    private String hrDeptId;
    private String groupKeyCode;
    private String dept;
    private String description;
    private String location;
    
	// field added for KPME-3184
	private String institution;
	
    private String chart;
    private String org;
    private boolean payrollApproval;

    private HrGroupKeyBo groupKey;
    private LocationBo locationObj;
    private Chart chartObj;
    private Organization orgObj;
    
    @Transient
    private List<DepartmentPrincipalRoleMemberBo> roleMembers = new ArrayList<DepartmentPrincipalRoleMemberBo>();
    
    @Transient
    private List<DepartmentPrincipalRoleMemberBo> inactiveRoleMembers = new ArrayList<DepartmentPrincipalRoleMemberBo>();
    
    
	@Override
	public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
		return new ImmutableMap.Builder<String, Object>()
				.put(KeyFields.DEPT, this.getDept())
                .put(KeyFields.GROUP_KEY_CODE, this.getGroupKeyCode())
				.build();
	}
    
	@Override
	public String getUniqueKey() {
		return getDept() + "_" + getOrg() + "_" + getChart();
	}
    
	@Override
	public String getId() {
		return getHrDeptId();
	}

	@Override
	public void setId(String id) {
		setHrDeptId(id);
	}
	
	public String getHrDeptId() {
		return hrDeptId;
	}

	public void setHrDeptId(String hrDeptId) {
		this.hrDeptId = hrDeptId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

    public String getGroupKeyCode() {
        return groupKeyCode;
    }

    public void setGroupKeyCode(String groupKeyCode) {
        this.groupKeyCode = groupKeyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

    public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }
    
	public LocationBo getLocationObj() {
		return locationObj;
	}

	public void setLocationObj(LocationBo locationObj) {
		this.locationObj = locationObj;
	}

	public Chart getChartObj() {
		return chartObj;
	}

	public void setChartObj(Chart chartObj) {
		this.chartObj = chartObj;
	}

	public Organization getOrgObj() {
		return orgObj;
	}

	public void setOrgObj(Organization orgObj) {
		this.orgObj = orgObj;
	}

	public List<DepartmentPrincipalRoleMemberBo> getRoleMembers() {
		return roleMembers;
	}
	
	public void addRoleMember(DepartmentPrincipalRoleMemberBo roleMemberBo) {
		roleMembers.add(roleMemberBo);
	}
	
	public void removeRoleMember(DepartmentPrincipalRoleMemberBo roleMemberBo) {
		roleMembers.remove(roleMemberBo);
	}
	
	public void setRoleMembers(List<DepartmentPrincipalRoleMemberBo> roleMembers) {
		this.roleMembers = roleMembers;
	}

	public List<DepartmentPrincipalRoleMemberBo> getInactiveRoleMembers() {
		return inactiveRoleMembers;
	}
	
	public void addInactiveRoleMember(DepartmentPrincipalRoleMemberBo inactiveRoleMemberBo) {
		inactiveRoleMembers.add(inactiveRoleMemberBo);
	}
	
	public void removeInactiveRoleMember(DepartmentPrincipalRoleMemberBo inactiveRoleMemberBo) {
		inactiveRoleMembers.remove(inactiveRoleMemberBo);
	}

	public void setInactiveRoleMembers(List<DepartmentPrincipalRoleMemberBo> inactiveRoleMembers) {
		this.inactiveRoleMembers = inactiveRoleMembers;
	}
	
	public boolean isPayrollApproval() {
		return payrollApproval;
	}

	public void setPayrollApproval(boolean payrollApproval) {
		this.payrollApproval = payrollApproval;
	}

    public HrGroupKeyBo getGroupKey() {
        if (groupKey == null) {
            //get from dg service
        }
        return groupKey;
    }

    public void setGroupKey(HrGroupKeyBo groupKey) {
        this.groupKey = groupKey;
    }

    public static DepartmentBo from(Department im) {
        DepartmentBo dept = new DepartmentBo();

        dept.setHrDeptId(im.getHrDeptId());
        dept.setDept(im.getDept());
        dept.setGroupKeyCode(im.getGroupKeyCode());
        dept.setGroupKey(HrGroupKeyBo.from(im.getGroupKey()));
        dept.setDescription(im.getDescription());
        dept.setLocation(im.getLocation());

        dept.setInstitution(im.getInstitution());

        dept.setChart(im.getChart());
        dept.setOrg(im.getOrg());
        dept.setPayrollApproval(im.isPayrollApproval());

        dept.setEffectiveDate(im.getEffectiveLocalDate() == null ? null : im.getEffectiveLocalDate().toDate());
        dept.setActive(im.isActive());
        if (im.getCreateTime() != null) {
            dept.setTimestamp(new Timestamp(im.getCreateTime().getMillis()));
        }
        dept.setUserPrincipalId(im.getUserPrincipalId());
        dept.setVersionNumber(im.getVersionNumber());
        dept.setObjectId(im.getObjectId());

        return dept;
    }

    public static Department to(DepartmentBo bo) {
        if (bo == null) {
            return null;
        }

        return Department.Builder.create(bo).build();
    }
}