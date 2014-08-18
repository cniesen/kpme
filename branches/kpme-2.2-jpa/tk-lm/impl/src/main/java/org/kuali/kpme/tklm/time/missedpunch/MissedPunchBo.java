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
package org.kuali.kpme.tklm.time.missedpunch;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.kuali.kpme.core.api.assignment.Assignment;
import org.kuali.kpme.core.api.assignment.AssignmentDescriptionKey;
import org.kuali.kpme.core.api.groupkey.HrGroupKey;
import org.kuali.kpme.core.api.util.KpmeUtils;
import org.kuali.kpme.core.department.DepartmentBo;
import org.kuali.kpme.core.groupkey.HrGroupKeyBo;
import org.kuali.kpme.core.job.JobBo;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.task.TaskBo;
import org.kuali.kpme.core.workarea.WorkAreaBo;
import org.kuali.kpme.tklm.api.time.missedpunch.MissedPunch;
import org.kuali.kpme.tklm.api.time.missedpunch.MissedPunchContract;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.principal.EntityNamePrincipalName;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "TK_MISSED_PUNCH_T")
public class MissedPunchBo extends PersistableBusinessObjectBase implements MissedPunchContract {

    private static final long serialVersionUID = 4494739150619504989L;

    public static final ModelObjectUtils.Transformer<MissedPunchBo, MissedPunch> toMissedPunch = new ModelObjectUtils.Transformer<MissedPunchBo, MissedPunch>() {

        public MissedPunch transform(MissedPunchBo input) {
            return MissedPunchBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<MissedPunch, MissedPunchBo> toMissedPunchBo = new ModelObjectUtils.Transformer<MissedPunch, MissedPunchBo>() {

        public MissedPunchBo transform(MissedPunch input) {
            return MissedPunchBo.from(input);
        }

        ;
    };

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("hh:mm aa");

    @PortableSequenceGenerator(name = "TK_MISSED_PUNCH_S")
    @GeneratedValue(generator = "TK_MISSED_PUNCH_S")
    @Id
    @Column(name = "TK_MISSED_PUNCH_ID", nullable = false, length = 60)
    private String tkMissedPunchId;

    @Column(name = "PRINCIPAL_ID", nullable = false, length = 40)
    private String principalId;

    @Column(name = "TIMESHEET_DOCUMENT_ID", nullable = false, length = 14)
    private String timesheetDocumentId;

    @Column(name = "JOB_NUMBER")
    private Long jobNumber;

    @Column(name = "WORK_AREA")
    private Long workArea;

    @Column(name = "TASK")
    private Long task;

    @Column(name = "ACTION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionDateTime;

    @Column(name = "CLOCK_ACTION", nullable = false, length = 20)
    private String clockAction;

    @Column(name = "TK_CLOCK_LOG_ID", length = 60)
    private String tkClockLogId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP", nullable = false)
    private Timestamp timestamp;

    @Column(name = "GRP_KEY_CD", nullable = false, length = 30)
    private String groupKeyCode;

    @Transient
    private transient HrGroupKeyBo groupKey;

    @Transient
    private transient String principalName;

    @Transient
    private transient String personName;

    @Transient
    private transient JobBo jobObj;

    @Transient
    private transient WorkAreaBo workAreaObj;

    @Transient
    private transient DepartmentBo departmentObj;

    @Transient
    private transient TaskBo taskObj;

    @Transient
    private transient LocalDate localDate;

    @Transient
    private transient LocalTime localTime;

    @Transient
    private transient Person person;

    @Transient
    private transient boolean isAssignmentReadOnly;

    @Transient
    private transient String note;

    @Transient
    private transient String missedPunchDocId;

    @Transient
    private transient String missedPunchDocStatus;

    public String getTkMissedPunchId() {
        return tkMissedPunchId;
    }

    public void setTkMissedPunchId(String tkMissedPunchId) {
        this.tkMissedPunchId = tkMissedPunchId;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getTimesheetDocumentId() {
        return timesheetDocumentId;
    }

    public void setTimesheetDocumentId(String timesheetDocumentId) {
        this.timesheetDocumentId = timesheetDocumentId;
    }

    public String getAssignmentKey() {
        return KpmeUtils.formatAssignmentKey(getGroupKeyCode(), getJobNumber(), getWorkArea(), getTask());
    }

    public void setAssignmentKey(String assignmentKey) {
        AssignmentDescriptionKey assignmentDescriptionKey = AssignmentDescriptionKey.get(assignmentKey);
        setGroupKeyCode(assignmentDescriptionKey.getGroupKeyCode());
        setJobNumber(assignmentDescriptionKey.getJobNumber());
        setWorkArea(assignmentDescriptionKey.getWorkArea());
        setTask(assignmentDescriptionKey.getTask());
    }

    public String getAssignmentValue() {
        return HrServiceLocator.getAssignmentService().getAssignmentDescription(getGroupKeyCode(), getPrincipalId(), getJobNumber(), getWorkArea(), getTask(), getActionFullDateTime().toLocalDate());
    }

    public Date getRelativeEffectiveDate() {
        if (getActionDate() != null)
            return getActionDate();
        else
            return new Date();
    }

    public void setRelativeEffectiveDate(Date relativeEffectiveDate) {
    }

    public Long getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(Long jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Long getWorkArea() {
        return workArea;
    }

    public void setWorkArea(Long workArea) {
        this.workArea = workArea;
    }

    public Long getTask() {
        return task;
    }

    public void setTask(Long task) {
        this.task = task;
    }

    @Override
    public String getGroupKeyCode() {
        return groupKeyCode;
    }

    public void setGroupKeyCode(String groupKeyCode) {
        this.groupKeyCode = groupKeyCode;
    }

    @Override
    public HrGroupKeyBo getGroupKey() {
        if (groupKey == null) {
            groupKey = HrGroupKeyBo.from(HrServiceLocator.getHrGroupKeyService().getHrGroupKey(getGroupKeyCode(), getActionFullDateTime().toLocalDate()));
        }
        return groupKey;
    }

    public void setGroupKey(HrGroupKeyBo groupKey) {
        this.groupKey = groupKey;
    }

    public DateTime getActionFullDateTime() {
        return actionDateTime != null ? new DateTime(actionDateTime) : null;
    }

    public LocalDate getActionLocalDate() {
        return actionDateTime != null ? new LocalDate(actionDateTime) : null;
    }

    public void setActionFullDateTime(DateTime actionFullDateTime) {
        this.actionDateTime = actionFullDateTime != null ? actionFullDateTime.toDate() : null;
    }

    public Date getActionDateTime() {
        return new Date(actionDateTime.getTime());
    }

    public Timestamp getActionDateTimestamp() {
        return new Timestamp(actionDateTime.getTime());
    }

    public void setActionDateTime(Date actionDateTime) {
        this.actionDateTime = new Date(actionDateTime.getTime());
    }

    public Date getActionDate() {
        return actionDateTime != null ? LocalDate.fromDateFields(actionDateTime).toDate() : (getLocalDate() != null ? getLocalDate().toDate() : null);
    }

    public void setActionDate(Date actionDate) {
        setLocalDate(actionDate != null ? LocalDate.fromDateFields(actionDate) : null);
        //LocalTime localTime = actionDateTime != null ? LocalTime.fromDateFields(actionDateTime) : LocalTime.MIDNIGHT; 
        if (localDate != null && localTime != null) {
            actionDateTime = localDate.toDateTime(localTime).toDate();
        }
    }

    public String getActionTime() {
        return actionDateTime != null ? FORMATTER.print(LocalTime.fromDateFields(actionDateTime)) : getLocalTimeString();
    }

    public void setActionTime(String actionTime) {
        if (StringUtils.isNotBlank(actionTime)) {
            setLocalTime(actionTime != null ? FORMATTER.parseLocalTime(actionTime) : null);
            if (localDate != null && localTime != null) {
                actionDateTime = localTime.toDateTime(localDate.toDateTimeAtStartOfDay()).toDate();
            }
        }
    }

    public String getClockAction() {
        return clockAction;
    }

    public void setClockAction(String clockAction) {
        this.clockAction = clockAction;
    }

    public String getTkClockLogId() {
        return tkClockLogId;
    }

    public void setTkClockLogId(String tkClockLogId) {
        this.tkClockLogId = tkClockLogId;
    }

    public Timestamp getTimestamp() {
        return timestamp == null ? null : new Timestamp(timestamp.getTime());
    }

    public DateTime getCreateTime() {
        return timestamp == null ? null : new DateTime(timestamp.getTime());
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrincipalName() {
        if (StringUtils.isBlank(principalName) && StringUtils.isNotBlank(principalId)) {
            Principal principal = KimApiServiceLocator.getIdentityService().getPrincipal(principalId);
            principalName = principal != null ? principal.getPrincipalName() : null;
        }
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPersonName() {
        if (StringUtils.isBlank(personName) && StringUtils.isNotBlank(principalId)) {
            EntityNamePrincipalName entityNamePrincipalName = KimApiServiceLocator.getIdentityService().getDefaultNamesForPrincipalId(principalId);
            if (entityNamePrincipalName != null && entityNamePrincipalName.getDefaultName() != null) {
                personName = entityNamePrincipalName.getDefaultName().getCompositeName();
            } else {
                return "";
            }
        }
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public JobBo getJobObj() {
        return jobObj;
    }

    public void setJobObj(JobBo jobObj) {
        this.jobObj = jobObj;
    }

    public WorkAreaBo getWorkAreaObj() {
        return workAreaObj;
    }

    public void setWorkAreaObj(WorkAreaBo workAreaObj) {
        this.workAreaObj = workAreaObj;
    }

    public TaskBo getTaskObj() {
        return taskObj;
    }

    public void setTaskObj(TaskBo taskObj) {
        this.taskObj = taskObj;
    }

    public boolean isAssignmentReadOnly() {
        return isAssignmentReadOnly;
    }

    public void setAssignmentReadOnly(boolean isAssignmentReadOnly) {
        this.isAssignmentReadOnly = isAssignmentReadOnly;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    protected String getLocalTimeString() {
        if (getLocalTime() != null) {
            return FORMATTER.print(getLocalTime());
        } else {
            return StringUtils.EMPTY;
        }
    }

    public String getDepartment() {
        if (getAssignmentKey() != null) {
            Assignment a = HrServiceLocator.getAssignmentService().getAssignment(getPrincipalId(), AssignmentDescriptionKey.get(getAssignmentKey()), getActionFullDateTime().toLocalDate());
            return a != null ? (a.getJob() != null ? a.getJob().getDept() : null) : null;
        }
        return null;
    }

    public DepartmentBo getDepartmentObj() {
        return departmentObj;
    }

    public void setDepartmentOjb(DepartmentBo departmentObj) {
        this.departmentObj = departmentObj;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMissedPunchDocId() {
        if (StringUtils.isBlank(missedPunchDocId)) {
            MissedPunchDocument aDoc = TkServiceLocator.getMissedPunchDocumentService().getMissedPunchDocumentByMissedPunchId(this.getTkMissedPunchId());
            if (aDoc != null) {
                this.setMissedPunchDocId(aDoc.getDocumentNumber());
            }
        }
        return missedPunchDocId;
    }

    public void setMissedPunchDocId(String missedPunchDocId) {
        this.missedPunchDocId = missedPunchDocId;
    }

    public String getMissedPunchDocStatus() {
        if (StringUtils.isBlank(missedPunchDocStatus)) {
            String docId = getMissedPunchDocId();
            if (StringUtils.isNotEmpty(docId)) {
                DocumentStatus aStatus = KewApiServiceLocator.getWorkflowDocumentService().getDocumentStatus(docId);
                if (aStatus != null) {
                    this.setMissedPunchDocStatus(aStatus.getLabel());
                }
            }
        }
        return missedPunchDocStatus;
    }

    public void setMissedPunchDocStatus(String missedPunchDocStatus) {
        this.missedPunchDocStatus = missedPunchDocStatus;
    }

    public static MissedPunchBo from(MissedPunch im) {
        if (im == null) {
            return null;
        }
        MissedPunchBo mp = new MissedPunchBo();
        mp.setTkMissedPunchId(im.getTkMissedPunchId());
        mp.setPrincipalId(im.getPrincipalId());
        mp.setTimesheetDocumentId(im.getTimesheetDocumentId());
        mp.setJobNumber(im.getJobNumber());
        mp.setWorkArea(im.getWorkArea());
        mp.setTask(im.getTask());
        mp.setActionDateTime(im.getActionFullDateTime() == null ? null : im.getActionFullDateTime().toDate());
        mp.setClockAction(im.getClockAction());
        mp.setTkClockLogId(im.getTkClockLogId());
        mp.setPrincipalName(im.getPrincipalName());
        mp.setPersonName(im.getPersonName());
        mp.setGroupKeyCode(im.getGroupKeyCode());
        mp.setGroupKey(HrGroupKeyBo.from(im.getGroupKey()));
        mp.setAssignmentReadOnly(im.isAssignmentReadOnly());
        mp.setMissedPunchDocId(im.getMissedPunchDocId());
        mp.setMissedPunchDocStatus(im.getMissedPunchDocStatus());
        mp.setTimestamp(im.getCreateTime() == null ? null : new Timestamp(im.getCreateTime().getMillis()));
        mp.setVersionNumber(im.getVersionNumber());
        mp.setObjectId(im.getObjectId());
        return mp;
    }

    public static MissedPunch to(MissedPunchBo bo) {
        if (bo == null) {
            return null;
        }
        return MissedPunch.Builder.create(bo).build();
    }
}
