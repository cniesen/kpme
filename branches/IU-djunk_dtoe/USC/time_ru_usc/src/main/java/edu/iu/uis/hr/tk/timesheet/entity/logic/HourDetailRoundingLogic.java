package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.Time;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.rule.entity.GracePeriodRule;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;

public class HourDetailRoundingLogic extends AbstractLogic implements OperationalLogic {

	private static int END_OF_DAY_ADJUSTED_MINUTE = 59;
	private static int END_OF_DAY_NONAJUSTED_MINUTE = 60;
	private static int END_OF_DAY_HOUR = 23;
//	private RuleService ruleService;
//	private AssignmentService assignmentService;
	
	public void execute(Entity entity) {
        if (!(entity instanceof HourDetail)) {
            throw new IllegalArgumentException("RoundClockTimeLogic's execute(Entity entity) method requires a non-null Entity of type HourDetail");
        }
        
        Assignment assignment = getAssignmentService().getAssignment(((HourDetail) entity).getAssignment(), ((HourDetail) entity).getHoursDetail().getDate());
        GracePeriodRule gracePeriodRule = getRuleService().getGracePeriodRule(((HourDetail) entity).getHoursDetail().getDate());
        if (Utility.hasValue(gracePeriodRule) && Utility.hasValue(assignment)) {

	    	WorkArea workArea = getAssignmentService().getWorkArea(assignment.getWorkArea(), ((HourDetail) entity).getHoursDetail().getDate());
	        TimeCollectionRule timeCollectionRule = getRuleService().getTimeCollectionRule(workArea.getDepartment(), workArea.getWorkArea(), ((HourDetail) entity).getHoursDetail().getDate());
	        if (Utility.hasValue(timeCollectionRule)) {
	        	if (timeCollectionRule.isClockUseRequired()) {
		        
		            Time beginTime = ((HourDetail) entity).getBeginTime();
		            Time endTime = ((HourDetail) entity).getEndTime();
		
		            BigDecimal minuteIncrement = gracePeriodRule.getHourFactor().multiply(new BigDecimal(60));
		            int graceMinutes = gracePeriodRule.getGraceMinutes().intValue();
		            
		            boolean isBeginTimeAdjusted = calculateRoundedTime(beginTime, minuteIncrement, graceMinutes);

		            boolean isEndTimeAdjusted = false;
		            // don't mess with the end of day time
	            	if (endTime.get24Hour() == END_OF_DAY_HOUR) {
	            		if (endTime.getMinute().intValue() != END_OF_DAY_ADJUSTED_MINUTE) {
	    		            isEndTimeAdjusted = calculateRoundedTime(endTime, minuteIncrement, graceMinutes);
	            		}
	            	} else {
    		            isEndTimeAdjusted = calculateRoundedTime(endTime, minuteIncrement, graceMinutes);
	            	}
		            
	            	// if we rounded the time, we need to set the end of day time properly
		            if (isEndTimeAdjusted) {
		            	if (endTime.get24Hour() == END_OF_DAY_HOUR) {
		            		if (endTime.getMinute().intValue() == END_OF_DAY_NONAJUSTED_MINUTE) {
		            			endTime.setMinute(new BigDecimal(END_OF_DAY_ADJUSTED_MINUTE));
		            		}
		            	}
		            }
		            if (isBeginTimeAdjusted || isEndTimeAdjusted) {
		            	((HourDetail) entity).getEntityWarnings().add(new String[] { HourDetail.HOURS_DETAIL_FIELD_NAME }, getMessage(MessageKeyConstants.WARNING_ROUNDING_RULE_APPLIED));
		            }
	        	}
	        }
        }
	}

	public boolean isMatch() {
		return true;
	}

	public RuleService getRuleService() {
        return (RuleService) Context.getService(RuleService.class);
    }
	
	private boolean calculateRoundedTime(Time time, BigDecimal minuteIncrement, int graceMinutes) {
		Time original = new Time(time.toString());
		try {
	        int bottomIntervalFactor = time.getMinute().divide(minuteIncrement, 0, BigDecimal.ROUND_FLOOR).intValue();
	        BigDecimal bottomInterval = new BigDecimal(bottomIntervalFactor).multiply(minuteIncrement);
	        BigDecimal topInterval = new BigDecimal(bottomIntervalFactor + 1).multiply(minuteIncrement);
	        
	        if (bottomInterval.subtract(time.getMinute()).abs().intValue() < topInterval.subtract(time.getMinute()).abs().intValue()) {
	            if (bottomInterval.subtract(time.getMinute()).abs().intValue() <= graceMinutes) {
	            	time.setMinute(bottomInterval.setScale(0,BigDecimal.ROUND_HALF_UP));
	            }
	        } else {
	            if (topInterval.subtract(time.getMinute()).abs().intValue() <= graceMinutes) {
	                if (topInterval.equals(new BigDecimal(60))) {
	                	time.setHour(time.getHour().add(new BigDecimal(1).setScale(0)));
	                	time.setMinute(new BigDecimal(0).setScale(0));
	                } else {
	                	time.setMinute(topInterval.setScale(0,BigDecimal.ROUND_HALF_UP));
	                }
	            }
	        }
		} catch (ArithmeticException ae) {
			return false;
		} catch (IllegalArgumentException iae) {
			return false;
		}
        return !(original.compareTo(time) == 0);
	}

    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }

}
