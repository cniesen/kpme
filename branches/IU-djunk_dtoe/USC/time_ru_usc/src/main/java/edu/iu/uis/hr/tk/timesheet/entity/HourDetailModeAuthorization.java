package edu.iu.uis.hr.tk.timesheet.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.position.entity.Department;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;
import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;

public class HourDetailModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!Utility.hasValue(modeApplicable) || !(modeApplicable instanceof HourDetail)) {
            throw new IllegalArgumentException("HourDetailModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable HourDetail object.");
        }
        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(((HourDetail)modeApplicable).getAssignment());
        WorkArea workArea = null;
        if (Utility.hasValue(assignmentKeyMap.get(FieldNames.WORK_AREA)) && Utility.hasValue(((HourDetail)modeApplicable).getHoursDetail().getDate())) {
            workArea = getAssignmentService().getWorkArea((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), ((HourDetail)modeApplicable).getHoursDetail().getDate());            
        }

        if (Utility.hasValue(workArea)) {
            if (((User)getWebUserService().getUser()).hasSupervisoryRole()) {
                Iterator departmentsItr = getPositionService().getDepartments(Arrays.asList(new String[] { workArea.getDepartment() }), new Date()).iterator();
                boolean hasJurisdiction = false;
                while (departmentsItr.hasNext()) {
                    if (((User)getWebUserService().getUser()).hasJurisdictionOver(workArea, (Department)departmentsItr.next())) {
                        hasJurisdiction = true;
                    }
                }
                if (!hasJurisdiction) {
                    modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
                }
            } else {
                Job job = getJobService().getJob((String)assignmentKeyMap.get(FieldNames.UNIVERSITY_ID), (BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), ((HourDetail)modeApplicable).getHoursDetail().getDate());
                TimeCollectionRule timeCollectionRule = getRuleService().getTimeCollectionRule(workArea.getDepartment(), workArea.getWorkArea(), ((HourDetail)modeApplicable).getHoursDetail().getDate());
                if (!Utility.hasValue(timeCollectionRule) || timeCollectionRule.isClockUseRequired()) {
                    if (job.isHourly()) {
                        modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
                    } else if (!getEarningService().isPtoEarnCode(((HourDetail)modeApplicable).getEarnCode(), ((HourDetail)modeApplicable).getHoursDetail().getDate())) {
                        modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
                    }
                }
            }
        }
        modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
    }
    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }

    private PositionService getPositionService() {
        return (PositionService) Context.getService(PositionService.class);
    }

    public RuleService getRuleService() {
        return (RuleService) Context.getService(RuleService.class);
    }

    private JobService getJobService() {
        return (JobService) Context.getService(JobService.class);
    }
    private EarningService getEarningService() {
        return (EarningService)Context.getService(EarningService.class);
    }
}
