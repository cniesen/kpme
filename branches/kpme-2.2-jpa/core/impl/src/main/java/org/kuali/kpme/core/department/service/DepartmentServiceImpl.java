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
import org.kuali.kpme.core.api.department.Department;
import org.kuali.kpme.core.api.department.DepartmentService;
import org.kuali.kpme.core.api.groupkey.HrGroupKey;
import org.kuali.kpme.core.api.groupkey.HrGroupKeyService;
import org.kuali.kpme.core.department.DepartmentBo;
import org.kuali.kpme.core.department.dao.DepartmentDao;
import org.kuali.rice.core.api.criteria.CountFlag;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.*;

public class DepartmentServiceImpl implements DepartmentService {

    private EffectiveObjectDao effectiveObjectDao;
    private DataObjectService dataObjectService;
    private HrGroupKeyService hrGroupKeyService;
    private static final ModelObjectUtils.Transformer<DepartmentBo, Department> toDepartment =
            new ModelObjectUtils.Transformer<DepartmentBo, Department>() {
                public Department transform(DepartmentBo input) {
                    return DepartmentBo.to(input);
                };
            };
	
	@Override
    public Department getDepartment(String hrDeptId) {
        return DepartmentBo.to(getDepartmentBo(hrDeptId));
    }

	protected DepartmentBo getDepartmentBo(String hrDeptId) {
        return dataObjectService.find(DepartmentBo.class, hrDeptId);
	}
    
	@Override
	public int getDepartmentCount(String department, String groupKeyCode) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("dept", department);
        criteria.put("groupKeyCode", groupKeyCode);
        QueryByCriteria.Builder builder = QueryByCriteria.Builder.andAttributes(criteria);
        builder.setCountFlag(CountFlag.ONLY);
        QueryResults<DepartmentBo> results = dataObjectService.findMatching(DepartmentBo.class, builder.build());
		return results.getTotalRowCount();
	}

    @Override
    public Department getDepartment(String department, String groupKeyCode, LocalDate asOfDate) {
        return DepartmentBo.to(getDepartmentBo(department, groupKeyCode, asOfDate));
    }

    protected DepartmentBo getDepartmentBo(String department,  String groupKeyCode, LocalDate asOfDate) {
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("dept", department);
        criteria.put("groupKeyCode", groupKeyCode);
        return effectiveObjectDao.getSingleEffectiveObject(DepartmentBo.class, DepartmentBo.BUSINESS_KEYS, criteria, asOfDate);
    }

    @Override
    public List<String> getDepartmentValuesWithLocation(String location, LocalDate asOfDate) {
        return getDepartmentValuesWithLocations(Collections.singletonList(location), asOfDate);
    }

    @Override
    public List<String> getDepartmentValuesWithLocations(List<String> locations, LocalDate asOfDate) {
        if (CollectionUtils.isEmpty(locations)) {
            return Collections.emptyList();
        }
        
        List<HrGroupKey> groupKeys = hrGroupKeyService.getHrGroupKeysForLocations(locations, asOfDate);
        List<String> groupKeyCodes = getGroupKeyCodesFromList(groupKeys);

        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("groupKeyCode", groupKeyCodes);

        List<DepartmentBo> departmentBos = effectiveObjectDao.getEffectiveObjectList(DepartmentBo.class, DepartmentBo.BUSINESS_KEYS, criteria, asOfDate);
        List<String> depts = new ArrayList<String>();
        for (DepartmentBo departmentObj : departmentBos) {
            depts.add(departmentObj.getBusinessKeyId());
        }

        return depts;
    }

    @Override
    public List<Department> getDepartmentsWithLocation(String location, LocalDate asOfDate) {
      	List<HrGroupKey> groupKeys = hrGroupKeyService.getHrGroupKeysWithLocation(location, asOfDate);
        List<String> groupKeyCodes = getGroupKeyCodesFromList(groupKeys);

        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("groupKeyCode", groupKeyCodes);

        List<DepartmentBo> departmentBos = effectiveObjectDao.getEffectiveObjectList(DepartmentBo.class, DepartmentBo.BUSINESS_KEYS, criteria, asOfDate);

        return ModelObjectUtils.transform(departmentBos, toDepartment);
    }

    @Override
    public List<Department> getDepartmentsWithGroupKey(String groupKeyCode, LocalDate asOfDate) {
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("groupKeyCode", groupKeyCode);

        List<DepartmentBo> departmentBos = effectiveObjectDao.getEffectiveObjectList(DepartmentBo.class, DepartmentBo.BUSINESS_KEYS, criteria, asOfDate);

        return ModelObjectUtils.transform(departmentBos, toDepartment);
    }


    @Override
    public List<Department> getDepartments(String department, String location, LocalDate asOfDate) {
        List<HrGroupKey> groupKeys = hrGroupKeyService.getHrGroupKeysWithLocation(location, asOfDate);
        List<String> groupKeyCodes = getGroupKeyCodesFromList(groupKeys);

        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("groupKeyCode", groupKeyCodes);
        criteria.put("dept", department);

        List<DepartmentBo> departmentBos = effectiveObjectDao.getEffectiveObjectList(DepartmentBo.class, DepartmentBo.BUSINESS_KEYS, criteria, asOfDate);

        return ModelObjectUtils.transform(departmentBos, toDepartment);
    }


    @Override
    public List<Department> getDepartments(LocalDate asOfDate) {
        List<DepartmentBo> departmentBos = effectiveObjectDao.getEffectiveObjectList(DepartmentBo.class, DepartmentBo.BUSINESS_KEYS, Collections.<String, Object>emptyMap(), asOfDate);

        return ModelObjectUtils.transform(departmentBos, toDepartment);
    }


    public void setHrGroupKeyService(HrGroupKeyService hrGroupKeyService) {
        this.hrGroupKeyService = hrGroupKeyService;
    }

    protected List<String> getGroupKeyCodesFromList(List<HrGroupKey> groupKeys) {
        List<String> groupKeyCodes = new ArrayList<String>();
        for (HrGroupKey key : groupKeys) {
            groupKeyCodes.add(key.getGroupKeyCode());
        }
        return groupKeyCodes;
    }

    public void setEffectiveObjectDao(EffectiveObjectDao effectiveObjectDao) {
        this.effectiveObjectDao = effectiveObjectDao;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}