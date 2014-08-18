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
package org.kuali.kpme.tklm.leave.block;

import java.sql.Timestamp;
import javax.persistence.*;

import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.task.TaskContract;
import org.kuali.kpme.core.api.workarea.WorkArea;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.kpme.tklm.api.leave.block.LeaveBlock;
import org.kuali.kpme.tklm.api.leave.block.LeaveBlockHistoryContract;
import org.kuali.kpme.tklm.leave.service.LmServiceLocator;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "LM_LEAVE_BLOCK_HIST_T")
public class LeaveBlockHistory extends LeaveBlockBo implements LeaveBlockHistoryContract {

    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "LM_LEAVE_BLOCK_S")
    @GeneratedValue(generator = "LM_LEAVE_BLOCK_S")
    @Id
    @Column(name = "LM_LEAVE_BLOCK_HIST_ID", length = 60)
    private String lmLeaveBlockHistoryId;

    @Column(name = "ACTION", length = 1)
    private String action;

    @Column(name = "PRINCIPAL_ID_DELETED", length = 40)
    private String principalIdDeleted;

    @Column(name = "TIMESTAMP_DELETED")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestampDeleted;

    public LeaveBlockHistory() {
    }

    public LeaveBlockHistory(LeaveBlockBo lb) {
        this.setLmLeaveBlockId(lb.getLmLeaveBlockId());
        this.setDocumentId(lb.getDocumentId());
        this.setAccrualCategory(lb.getAccrualCategory());
        this.setAccrualGenerated(lb.isAccrualGenerated());
        //		this.setApplyToYtdUsed(lb.getApplyToYtdUsed()); 
        this.setDescription(lb.getDescription());
        this.setLeaveAmount(lb.getLeaveAmount());
        this.setEarnCode(lb.getEarnCode());
        this.setLeaveDate(lb.getLeaveDate());
        this.setPrincipalId(lb.getPrincipalId());
        //		this.setPrincipalIdModified(lb.getPrincipalIdModified()); 
        this.setRequestStatus(lb.getRequestStatus());
        this.setTimestamp(lb.getTimestamp());
        //		this.setTkAssignmentId(lb.getTkAssignmentId()); 
        this.setScheduleTimeOffId(lb.getScheduleTimeOffId());
        this.setLeaveBlockType(lb.getLeaveBlockType());
        this.setBeginTimestamp(lb.getBeginTimestamp());
        this.setEndTimestamp(lb.getEndTimestamp());
        this.setGroupKeyCode(lb.getGroupKeyCode());
        this.setWorkArea(lb.getWorkArea());
        this.setTask(lb.getTask());
        this.setJobNumber(lb.getJobNumber());
        this.setTransactionDocId(lb.getTransactionalDocId());
    }

    public String getLmLeaveBlockHistoryId() {
        return lmLeaveBlockHistoryId;
    }

    public void setLmLeaveBlockHistoryId(String lmLeaveBlockHistoryId) {
        this.lmLeaveBlockHistoryId = lmLeaveBlockHistoryId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPrincipalIdDeleted() {
        return principalIdDeleted;
    }

    public void setPrincipalIdDeleted(String principalIdDeleted) {
        this.principalIdDeleted = principalIdDeleted;
    }

    public Timestamp getTimestampDeleted() {
        return timestampDeleted;
    }

    public void setTimestampDeleted(Timestamp timestampDeleted) {
        this.timestampDeleted = timestampDeleted;
    }

    public String getAssignmentTitle() {
        StringBuilder b = new StringBuilder();
        LeaveBlock lb = LmServiceLocator.getLeaveBlockService().getLeaveBlock(super.getLmLeaveBlockId());
        if (lb != null) {
            if (lb.getWorkArea() != null) {
                WorkArea wa = HrServiceLocator.getWorkAreaService().getWorkArea(lb.getWorkArea(), LocalDate.now());
                if (wa != null) {
                    b.append(wa.getDescription());
                }
                TaskContract task = HrServiceLocator.getTaskService().getTask(this.getTask(), this.getLeaveLocalDate());
                if (task != null) {
                    // do not display task description if the task is the default 
                    // one 
                    // default task is created in getTask() of TaskService 
                    if (!task.getDescription().equals(HrConstants.TASK_DEFAULT_DESP)) {
                        b.append("-" + task.getDescription());
                    }
                }
            }
        }
        return b.toString();
    }
}
