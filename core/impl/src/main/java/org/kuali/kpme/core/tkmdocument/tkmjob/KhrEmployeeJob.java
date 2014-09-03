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

import org.kuali.kpme.core.assignment.AssignmentBo;
import org.kuali.kpme.core.job.JobBo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jwillia on 4/11/14.
 */
public class KhrEmployeeJob extends JobBo {
    private Date endDate;
    private List<AssignmentBo> assignments = new ArrayList<AssignmentBo>();

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<AssignmentBo> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentBo> assignments) {
        this.assignments = assignments;
    }
}

