package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.Time;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class ValidPtoForManualShiftExistLogic extends AbstractLogic implements OperationalLogic {

//    private EarningService earningService;
//    private RuleService ruleService;
//    private AssignmentService assignmentService;
//    private JobService jobService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("ValidPtoForManualShiftExistLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
        }
        List hoursDetails = ((TimesheetDocument) entity).getHours().getHoursDetails();

        if (Utility.hasValue(hoursDetails)) {
            Iterator hoursDetailsItr = hoursDetails.iterator();
            
            while (hoursDetailsItr.hasNext()) {
            	HoursDetail hoursDetail = (HoursDetail) hoursDetailsItr.next();
            	if(checkForShiftDifferentialRuleEarnCodes((hoursDetail).getHourDetails(), ((TimesheetDocument) entity).getDocumentHeader().getPayEndDate())){
                	hoursDetail.setTabStatus("true");
                }
                
            }
        }
    }

    //When someone would normally earn shift but they are taking some benefit time (SCK, VAC, etc. - found in Earn Program T13) 
    //then a manual shift block should be valid IF there is an appropriate Shift Differential Rule in place. 
    // AND the total shift hours are less than the total PTO hours for that specific day
    private boolean checkForShiftDifferentialRuleEarnCodes(TypedPersistentMaintainedEntityList hourDetails, Date effectiveDate) {
    	boolean result = false;
    	if (Utility.hasValue(hourDetails)) {
            Iterator hourDetailsItr = hourDetails.iterator();
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail) hourDetailsItr.next();
                if (getEarningService().isShiftDifferentialRuleEarnCode(hourDetail.getEarnCode(), effectiveDate)) {
                    if (hasValidPtoInTimeBlocks(hourDetails, hourDetail, effectiveDate)) {
                        List shiftDifferentialRules = (List) getRuleService().getShiftDifferentialRules(hourDetail.getEarnCode(), hourDetail.getHoursDetail().getDate());
                        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
                        Job job = getJobService().getJob((String) assignmentKeyMap.get(FieldNames.UNIVERSITY_ID), (BigDecimal) assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hourDetail.getHoursDetail().getDate());
                        if (Utility.hasValue(shiftDifferentialRules)) {
                            boolean shiftDifferentialFound = false;
                            for (Iterator iter = shiftDifferentialRules.iterator(); iter.hasNext();) {
                                ShiftDifferentialRule shiftDifferentialRule = (ShiftDifferentialRule) iter.next();
                                if ((shiftDifferentialRule.getLocation().equals(Option.ALL_VALUE) || shiftDifferentialRule.getLocation().equalsIgnoreCase(job.getLocation())) && (shiftDifferentialRule.getSalaryPlan().equals(Option.ALL_VALUE) || shiftDifferentialRule.getSalaryPlan().equalsIgnoreCase(job.getSalaryPlan())) && (shiftDifferentialRule.getGrade().equals(Option.ALL_VALUE) || shiftDifferentialRule.getGrade().equalsIgnoreCase(job.getGrade()))) {
                                    if (isShiftDiffRuleApplicable(shiftDifferentialRule, hourDetail)) {
                                    	shiftDifferentialFound = true;
                                        break;
                                    }
                                }
                            }
                            if (!shiftDifferentialFound) {
                                hourDetail.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.TIME_BLOCK_NOT_QUALIFIED_FOR_SHIFT));
                                result = true;
                            }
                        } else {
                            hourDetail.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.TIME_BLOCK_NOT_QUALIFIED_FOR_SHIFT));
                            result = true;
                        }
                    } else {
                        // set up error message here
                        hourDetail.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.TIME_BLOCK_NOT_QUALIFIED_FOR_SHIFT));
                        result = true;
                    }
                }
            }
        }
    	return result;
    }

    private boolean hasValidPtoInTimeBlocks(TypedPersistentMaintainedEntityList hourDetails, HourDetail shiftHourDetail, Date effectiveDate) {
        // is it possible to have multiple pto blocks for T13?
        BigDecimal totalPtoHours = new BigDecimal(0.0);
        Iterator hourDetailsItr = hourDetails.iterator();
        while (hourDetailsItr.hasNext()) {
            HourDetail hourDetail = (HourDetail) hourDetailsItr.next();
            if (getEarningService().isValidPtoForManualShiftEarnCode(hourDetail.getEarnCode(), effectiveDate)) {
            	totalPtoHours = totalPtoHours.add(hourDetail.getHours());
            }
        }
        if (shiftHourDetail.getHours().compareTo(new BigDecimal(0)) == 0) { //new timeblock being added, hours haven't been calculated yet.
          return totalPtoHours.doubleValue() >= (Time.getTimeDifferenceInHours(shiftHourDetail.getEndTime(), shiftHourDetail.getBeginTime(), new Date())).doubleValue();
        } else {
        return (totalPtoHours.doubleValue() >= shiftHourDetail.getHours().doubleValue());
        }
    }

    private boolean isShiftDiffRuleApplicable(ShiftDifferentialRule shiftDifferentialRule, HourDetail hourDetail) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hourDetail.getHoursDetail().getDate());
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SUNDAY && shiftDifferentialRule.isApplicableToSunday()) {
            return true;
        } else if (weekDay == Calendar.MONDAY && shiftDifferentialRule.isApplicableToMonday()) {
            return true;
        } else if (weekDay == Calendar.TUESDAY && shiftDifferentialRule.isApplicableToTuesday()) {
            return true;
        } else if (weekDay == Calendar.WEDNESDAY && shiftDifferentialRule.isApplicableToWednesday()) {
            return true;
        } else if (weekDay == Calendar.THURSDAY && shiftDifferentialRule.isApplicableToThursday()) {
            return true;
        } else if (weekDay == Calendar.FRIDAY && shiftDifferentialRule.isApplicableToFriday()) {
            return true;
        } else if (weekDay == Calendar.SATURDAY && shiftDifferentialRule.isApplicableToSaturday()) {
            return true;
        }
        return false;
    }

    private EarningService getEarningService() {
        return (EarningService)Context.getService(EarningService.class);
}

    public RuleService getRuleService() {
        return (RuleService) Context.getService(RuleService.class);
    }

    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
}
    
	public JobService getJobService() {
		return (JobService) Context.getService(JobService.class);
	}
}
