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

