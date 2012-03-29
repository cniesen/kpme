package edu.iu.hr.time.logic.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.Interval;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.assignment.AssignmentDescriptionKey;
import org.kuali.hr.time.timeblock.TimeBlock;
import org.kuali.hr.time.timeblock.TimeHourDetail;
import org.kuali.hr.time.timesheet.TimesheetDocument;
import org.kuali.hr.time.util.TkConstants;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import edu.iu.hr.time.earns.dist.EarningsDistribution;
import edu.iu.hr.time.logic.dao.ReadyToApproveDao;
import edu.iu.hr.time.service.base.TkServiceLocator;

public class ReadyToApproveServiceImpl implements ReadyToApproveService {
	private ReadyToApproveDao readyToApproveDao;
	
	
	@Override
	public boolean isReadyToApprove(String documentId) {
		TimesheetDocument ts = TkServiceLocator.getTimesheetService().getTimesheetDocument(documentId);
		return isReadyToApprove(ts);
	}

	@Override
	public boolean isReadyToApprove(TimesheetDocument timesheetDocument) {
		String errorMessage = "";
		if(hasOverlappingTimeBlocks(timesheetDocument)){
			errorMessage = "Overlapping Time Blocks were found. Timesheet is not ready to approve.";
		} else if(invalidAssignmentFound(timesheetDocument)){
			errorMessage = "Timesheet contains invalid assignments. Timesheet is not ready to approve.";
		} else if(ovtChargedToHRW(timesheetDocument)){
			 errorMessage = "Overtime has been charged to an HRW job. Timesheet is not ready to approve."; 
		} else if(hasMetExpectedHours(timesheetDocument)){
			errorMessage = "Expected hours are not met. Timesheet is not ready to approve."; 
		} else if(absHoursExceedStdHours(timesheetDocument)){
			errorMessage = "ABS hours exceed standard hours. Timesheet is not ready to approve.";
		}
		
		
//        if (overlappingTimesBlocksFound(timesheetDocument.getHours().getHoursDetails())) {
//        	readyToApprove = false;
//        	errorMessage = "Overlapping Time Blocks were found. Timesheet is not ready to approve.";
//        } else if (invalidAssignmentFound(timesheetDocument)) {
//       	    readyToApprove = false;
//       	    errorMessage = "Timesheet contains invalid assignments. Timesheet is not ready to approve.";
//        } else  if (ovtChargedToHRW(timesheetDocument)) {
//           readyToApprove = false;
//           errorMessage = "Overtime has been charged to an HRW job. Timesheet is not ready to approve."; 
//        } else if (!expectedHoursMet(timesheetDocument)) {
//        	readyToApprove = false;
//        	errorMessage = "Expected hours are not met. Timesheet is not ready to approve."; 
//        } else if (absHoursExceedStdHours(timesheetDocument)) {
//           	readyToApprove = false;
//           	errorMessage = "ABS hours exceed standard hours. Timesheet is not ready to approve.";
//        } else  if (documentRouteHeaderValue.getDocRouteLevel().intValue() == 2 && tuhEarnCodeFound(timesheetDocument.getHours().getHoursDetails())) {
//        	readyToApprove = false;
//           	errorMessage = "TUH hours exist on timesheet. Timesheet is not ready to approve.";                	
//        }
		return false;
	}
	
	private boolean absHoursExceedStdHours(TimesheetDocument timesheetDocument){
		BigDecimal weekOneABS = new BigDecimal(0.0);
		BigDecimal weekTwoABS = new BigDecimal(0.0);
		
		Map<Long, BigDecimal> jobNumberToABSAmtWeekOne = new HashMap<Long, BigDecimal>();
		Map<Long, BigDecimal> jobNumberToABSAmtWeekTwo = new HashMap<Long, BigDecimal>();
	
		Multimap<Integer, TimeBlock> timeBlocksByWeek = bucketTimeBlocksByWeek(timesheetDocument);
		//Calculate week one abs values
		for(TimeBlock tb : timeBlocksByWeek.get(0)){
			for(TimeHourDetail thd : tb.getTimeHourDetails()){
				if(StringUtils.equals(thd.getEarnCode(),"ABS")){
					if(jobNumberToABSAmtWeekOne.get(tb.getJobNumber())==null){
						jobNumberToABSAmtWeekOne.put(tb.getJobNumber(), new BigDecimal(0.0));
					}
					weekOneABS = jobNumberToABSAmtWeekOne.get(tb.getJobNumber());
					weekOneABS = weekOneABS.add(thd.getHours(), TkConstants.MATH_CONTEXT);
					jobNumberToABSAmtWeekOne.put(tb.getJobNumber(),weekOneABS);
				}
			}
		}
		
		//Calculate week two abs values
		for(TimeBlock tb : timeBlocksByWeek.get(1)){
			for(TimeHourDetail thd : tb.getTimeHourDetails()){
				if(StringUtils.equals(thd.getEarnCode(),"ABS")){
					if(jobNumberToABSAmtWeekTwo.get(tb.getJobNumber())==null){
						jobNumberToABSAmtWeekTwo.put(tb.getJobNumber(), new BigDecimal(0.0));
					}
					weekTwoABS = jobNumberToABSAmtWeekTwo.get(tb.getJobNumber());
					weekTwoABS = weekTwoABS.add(thd.getHours(), TkConstants.MATH_CONTEXT);
					jobNumberToABSAmtWeekTwo.put(tb.getJobNumber(),weekTwoABS);
				}
			}
		}
		
		for(Long jobNumber : jobNumberToABSAmtWeekOne.keySet()){
			Job job = TkServiceLocator.getJobSerivce().getJob(timesheetDocument.getPrincipalId(), jobNumber, timesheetDocument.getAsOfDate());
			if(job != null){
				BigDecimal stdHours = job.getStandardHours();
				if(stdHours != null){
					BigDecimal absHours = jobNumberToABSAmtWeekOne.get(jobNumber);
					if(absHours.compareTo(stdHours) > 0){
						return true;
					}
				}
			}
		}
		
		for(Long jobNumber : jobNumberToABSAmtWeekTwo.keySet()){
			Job job = TkServiceLocator.getJobSerivce().getJob(timesheetDocument.getPrincipalId(), jobNumber, timesheetDocument.getAsOfDate());
			if(job != null){
				BigDecimal stdHours = job.getStandardHours();
				if(stdHours != null){
					BigDecimal absHours = jobNumberToABSAmtWeekTwo.get(jobNumber);
					if(absHours.compareTo(stdHours) > 0){
						return true;
					}
				}
			}
		}		
		
		return false;
		
	}
	
	private Multimap<Integer,TimeBlock> bucketTimeBlocksByWeek(TimesheetDocument timesheetDocument){
		Multimap<Integer, TimeBlock> weekToTimeBlocks= HashMultimap.create();
		
		Date beginPeriodDate = timesheetDocument.getPayCalendarEntry().getBeginPeriodDate();
		Date endPeriodDate = timesheetDocument.getPayCalendarEntry().getEndPeriodDate();
		
		Date currenDate = new Date(beginPeriodDate.getTime());
		int count = 1;
		Interval weekOne = null;
		Interval weekTwo = null;
		while(currenDate.before(endPeriodDate)){
			if(count == 7){
				weekOne = new Interval(beginPeriodDate.getTime(),currenDate.getTime());
				beginPeriodDate = currenDate;
			} else if(count == 14){
				weekTwo = new Interval(currenDate.getTime(), endPeriodDate.getTime());
			}
		}
		
		for(TimeBlock tb : timesheetDocument.getTimeBlocks()){
			if(weekOne.contains(tb.getBeginDate().getTime())){
				weekToTimeBlocks.put(0, tb);
			} else {
				weekToTimeBlocks.put(1, tb);
			}
		}
		
		return weekToTimeBlocks;
	}
	
	
	//TODO implement this
	private boolean hasMetExpectedHours(TimesheetDocument timesheetDocument){
		Map<Integer, BigDecimal> jobToRegHours = new HashMap<Integer, BigDecimal>();
		Multimap<Integer, TimeBlock> timeBlocksByWeek = bucketTimeBlocksByWeek(timesheetDocument);
		for(TimeBlock tb : timeBlocksByWeek.get(0)){
			
		}
		
		for(TimeBlock tb : timeBlocksByWeek.get(1)){
			
		}
		
		//EarningsDistribution earnDist = readyToApproveDao.getEarningsDistribution(timesheetDocument.getPrincipalId(), emplRecord, effectiveDate)
		
		return true;
	}
	
	private boolean ovtChargedToHRW(TimesheetDocument timesheetDocument){
		List<TimeBlock> timeBlocks = timesheetDocument.getTimeBlocks();
		String principalId = timesheetDocument.getPrincipalId();
		for(TimeBlock tb : timeBlocks){
			for(TimeHourDetail thd : tb.getTimeHourDetails()){
				if(StringUtils.equals(thd.getEarnCode(),"OVT")){
					Job job = TkServiceLocator.getJobSerivce().getJob(principalId, tb.getJobNumber(), tb.getBeginDate());
					if(job!=null && StringUtils.equals(job.getHrSalGroup(), "WSU")){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean hasOverlappingTimeBlocks(TimesheetDocument timesheetDocument){
		List<TimeBlock> timeBlocks = timesheetDocument.getTimeBlocks();
		List<Interval> tbIntervals = new ArrayList<Interval>();
		
		for(TimeBlock tb : timeBlocks){
			Interval interval = new Interval(tb.getBeginTimestamp().getTime(),tb.getEndTimestamp().getTime());
			tbIntervals.add(interval);
		}
		
		for(Interval tbInterval : tbIntervals){
			for(Interval tb : tbIntervals){
				if(tb.equals(tbInterval)){
					continue;
				}
				if(tb.overlaps(tbInterval)){
					return true;
				}
			}
		}
		
		return true;
	}
	
	private boolean invalidAssignmentFound(TimesheetDocument timesheetDocument){
		for(TimeBlock tb : timesheetDocument.getTimeBlocks()){
			AssignmentDescriptionKey assignKey = TkServiceLocator.getAssignmentService().getAssignmentDescriptionKey(tb.getAssignmentKey());
			Assignment assign = TkServiceLocator.getAssignmentService().getAssignment(assignKey, tb.getBeginDate());
			if(assign==null || !assign.isActive()){
				return true;
			}
		}
		return false;
	}

	public ReadyToApproveDao getReadyToApproveDao() {
		return readyToApproveDao;
	}

	public void setReadyToApproveDao(ReadyToApproveDao readyToApproveDao) {
		this.readyToApproveDao = readyToApproveDao;
	}

}
