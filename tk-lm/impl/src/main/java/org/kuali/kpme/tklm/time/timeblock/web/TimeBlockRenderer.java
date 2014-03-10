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
package org.kuali.kpme.tklm.time.timeblock.web;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.task.TaskContract;
import org.kuali.kpme.core.api.workarea.WorkAreaContract;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.kpme.core.util.HrContext;
import org.kuali.kpme.tklm.api.common.TkConstants;
import org.kuali.kpme.tklm.api.time.timeblock.TimeBlock;
import org.kuali.kpme.tklm.api.time.timeblock.web.TimeBlockRendererContract;
import org.kuali.kpme.tklm.api.time.timehourdetail.TimeHourDetail;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;
import org.kuali.kpme.tklm.time.timehourdetail.TimeHourDetailRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Render helper to handle timeblock and time hour details display
 */
public class TimeBlockRenderer implements TimeBlockRendererContract {

    private TimeBlock timeBlock;
    private List<TimeHourDetailRenderer> detailRenderers = new ArrayList<TimeHourDetailRenderer>();
    private String assignmentClass;
    private String lunchLabel;
    private String lunchLabelId;

    public TimeBlockRenderer(TimeBlock b) {
        this.timeBlock = b;
        for (TimeHourDetail detail : timeBlock.getTimeHourDetails()) {
            detailRenderers.add(new TimeHourDetailRenderer(detail));
        }
    }

    public List<TimeHourDetailRenderer> getDetailRenderers() {
        return detailRenderers;
    }

    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    public String getTimeRange() {
        StringBuilder b = new StringBuilder();
        if(StringUtils.equals(timeBlock.getEarnCodeType(), HrConstants.EARN_CODE_TIME)) {
            b.append(timeBlock.getBeginTimeDisplay().toString(TkConstants.DT_BASIC_TIME_FORMAT));
            b.append(" - ");
            b.append(timeBlock.getEndTimeDisplay().toString(TkConstants.DT_BASIC_TIME_FORMAT));
        }

        return b.toString();
    }

    public String getTitle() {
        StringBuilder b = new StringBuilder();

        WorkAreaContract wa = HrServiceLocator.getWorkAreaService().getWorkAreaWithoutRoles(timeBlock.getWorkArea(), LocalDate.now());
        if(wa != null) {
        	b.append(wa.getDescription());
        }
        TaskContract task = HrServiceLocator.getTaskService().getTask(timeBlock.getTask(), timeBlock.getBeginDateTime().toLocalDate());
        if(task != null) {
        	// do not display task description if the task is the default one
        	// default task is created in getTask() of TaskService
        	if(!task.getDescription().equals(HrConstants.TASK_DEFAULT_DESP)) {
        		b.append("-" + task.getDescription());
        	}
        }
        return b.toString();
    }

    public String getAmount() {
    	if(StringUtils.equals(timeBlock.getEarnCodeType(), HrConstants.EARN_CODE_AMOUNT)) {
    		if(timeBlock.getAmount() != null) {
    			return timeBlock.getEarnCode() + ": $" + timeBlock.getAmount().toString();
    		} else {
    			return timeBlock.getEarnCode() + ": $0.00";
    		}
	    }
    	return "";
    }

    public String getEarnCodeType() {
    	return timeBlock.getEarnCodeType();
    }

	public String getAssignmentClass() {
		return assignmentClass;
	}

	public void setAssignmentClass(String assignmentClass) {
		this.assignmentClass = assignmentClass;
	}

	public String getLunchLabel() {
		return lunchLabel;
	}

	public void setLunchLabel(String lunchLabel) {
		this.lunchLabel = lunchLabel;
	}

    public String getLunchLabelId() {
        return lunchLabelId;
    }

    public void setLunchLabelId(String lunchLabelId) {
        this.lunchLabelId = lunchLabelId;
    }

    public boolean isTimeBlockEditable() {
        return TkServiceLocator.getTKPermissionService().canEditTimeBlock(HrContext.getPrincipalId(), timeBlock);
    }

    public boolean isDeletable() {
        return TkServiceLocator.getTKPermissionService().canDeleteTimeBlock(HrContext.getPrincipalId(), timeBlock);
    }

    public boolean isOvertimeEditable() {
        return TkServiceLocator.getTKPermissionService().canEditOvertimeEarnCode(HrContext.getPrincipalId(), timeBlock);
    }
}
