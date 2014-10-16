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
package org.kuali.kpme.core.tkmdocument.tkmjob;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.namespace.KPMENamespace;
import org.kuali.kpme.core.assignment.AssignmentBo;
import org.kuali.kpme.core.job.JobBo;
import org.kuali.kpme.core.role.KPMERole;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jwillia on 4/11/14.
 */
public class KhrEmployeeJob extends JobBo {
	
	private static final long serialVersionUID = -2015621064979417200L;

    private List<AssignmentBo> assignments = new ArrayList<AssignmentBo>();

    private String jobNumberDisplay;

    public List<AssignmentBo> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentBo> assignments) {
        this.assignments = assignments;
    }


    public String getJobNumberDisplay() {
        Long currentJobNumber = this.getJobNumber();
        if ( (currentJobNumber != null) && (currentJobNumber.compareTo(0L) >= 0 ) )
        {
            return currentJobNumber.toString();
        }
        else
        {
            return " ";
        }
    }

    public boolean getCanEditJob() {
        // only department admin can edit the job
        if(StringUtils.isNotBlank(this.getDept()) && StringUtils.isNotBlank(this.getGroupKeyCode()) ) {
            if (HrServiceLocator.getKPMERoleService().principalHasRoleInDepartment(GlobalVariables.getUserSession().getPrincipalId(),
                    KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_DEPARTMENT_ADMINISTRATOR.getRoleName(), this.getDept(), this.getGroupKeyCode(), LocalDate.now().toDateTimeAtStartOfDay())
                    || HrServiceLocator.getKPMERoleService().principalHasRoleInDepartment(GlobalVariables.getUserSession().getPrincipalId(),
                    KPMENamespace.KPME_LM.getNamespaceCode(), KPMERole.LEAVE_DEPARTMENT_ADMINISTRATOR.getRoleName(), this.getDept(), this.getGroupKeyCode(), LocalDate.now().toDateTimeAtStartOfDay())) {
                return true;
            }
        }
        return false;
    }
}

