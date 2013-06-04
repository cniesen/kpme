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
package org.kuali.hr.core.service;

import java.util.ArrayList;
import java.util.List;

import org.kuali.hr.lm.accrual.AccrualCategory;
import org.kuali.hr.lm.leaveplan.LeavePlan;
import org.kuali.hr.paygrade.PayGrade;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.earncode.EarnCode;
import org.kuali.hr.time.position.Position;
import org.kuali.hr.time.principal.PrincipalHRAttributes;
import org.kuali.hr.time.salgroup.SalGroup;
import org.kuali.hr.time.task.Task;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.krad.service.impl.ModuleServiceBase;


public class KpmeModuleService extends ModuleServiceBase {

    @Override
    public List<List<String>> listAlternatePrimaryKeyFieldNames(Class businessObjectInterfaceClass) {
        if (LeavePlan.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("leavePlan");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        } else if (EarnCode.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("earnCode");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        } else if (Task.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("task");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        } else if (WorkArea.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("workArea");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        } else if (PayGrade.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("payGrade");
            keyList.add("salGroup");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        } else if (SalGroup.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("hrSalGroup");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        } else if (Department.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("dept");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        } else if (Position.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("positionNumber");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        } else if (AccrualCategory.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("accrualCategory");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        } else if (PrincipalHRAttributes.class.isAssignableFrom(businessObjectInterfaceClass)) {
            List<List<String>> retList = new ArrayList<List<String>>();
            List<String> keyList = new ArrayList<String>();
            keyList.add("principalId");
            keyList.add("effectiveDate");
            retList.add(keyList);
            return retList;
        }
        return super.listAlternatePrimaryKeyFieldNames(businessObjectInterfaceClass);
    }
}
