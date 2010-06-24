package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ExceptionAdapter;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.position.entity.Frequency;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.job.entity.EarningsDistribution;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.Hours;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class IsReadyToApproveLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(IsReadyToApproveLogic.class);
    private static final double PAY_FREQUENCY_ANNUALIZE_FACTOR = 52;
    private static final String WORKFLOW_TK_SUPER_USER = "timekeeping-system";

    private TimesheetService timesheetService;
    private AssignmentService assignmentService;
    private EarningService earningService;
    private PositionService positionService;
    private edu.iu.uis.hr.job.service.JobService hrJobService;
    private WorkflowDocument workflowDocument;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("IsReadyToApproveLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
        }
        TimesheetDocument document = (TimesheetDocument)entity;
        evaluateReadyToApprove(document, true);
    }

    public boolean isReadyToApprove(String documentId) {
        TimesheetDocument timesheetDocument = getTimesheetService().getTimesheetDocument(documentId.toString());
        return evaluateReadyToApprove(timesheetDocument, false);
    }

    public boolean evaluateReadyToApprove(final TimesheetDocument timesheetDocument, final boolean addErrorsToEntity) {
    	
        return (Boolean) Context.getTransactionTemplate().execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				Boolean readyToApprove = true;
		    	String errorMessage = new String();
		    	KEWServiceLocator.getRouteHeaderService().lockRouteHeader(new Long(timesheetDocument.getDocumentId()), true);
		    	DocumentRouteHeaderValue documentRouteHeaderValue = KEWServiceLocator.getRouteHeaderService().getRouteHeader(new Long(timesheetDocument.getDocumentId()));
		        
		        try {
//		            workflowDocument = new WorkflowDocument(new NetworkIdDTO(WORKFLOW_TK_SUPER_USER), new Long(timesheetDocument.getDocumentId()));
		            
		              if (overlappingTimesBlocksFound(timesheetDocument.getHours().getHoursDetails())) {
		                	readyToApprove = false;
		                	errorMessage = "Overlapping Time Blocks were found. Timesheet is not ready to approve.";
		                } else if (invalidAssignmentFound(timesheetDocument)) {
		               	    readyToApprove = false;
		               	    errorMessage = "Timesheet contains invalid assignments. Timesheet is not ready to approve.";
		                } else  if (ovtChargedToHRW(timesheetDocument)) {
			               readyToApprove = false;
			               errorMessage = "Overtime has been charged to an HRW job. Timesheet is not ready to approve."; 
		                } else if (!expectedHoursMet(timesheetDocument)) {
		                	readyToApprove = false;
		                	errorMessage = "Expected hours are not met. Timesheet is not ready to approve."; 
		                } else if (absHoursExceedStdHours(timesheetDocument)) {
		                   	readyToApprove = false;
		                   	errorMessage = "ABS hours exceed standard hours. Timesheet is not ready to approve.";
		                } else  if (documentRouteHeaderValue.getDocRouteLevel().intValue() == 2 && tuhEarnCodeFound(timesheetDocument.getHours().getHoursDetails())) {
		                	readyToApprove = false;
		                   	errorMessage = "TUH hours exist on timesheet. Timesheet is not ready to approve.";                	
		                }
		        } catch (NumberFormatException ne) {
		            throw new ExceptionAdapter(ne, LOG);
		        }
//		        catch (WorkflowException we) {
//		            throw new ExceptionAdapter(we, LOG);
//		        }

		        if (!readyToApprove && addErrorsToEntity) {
		            timesheetDocument.getEntityErrors().add(new String[] { "timesheetDocument" }, errorMessage);
		        } 
		        
//		        this.updateReadyToApproveStatus(readyToApprove.toString(), workflowDocument);

		        documentRouteHeaderValue.setAppDocId(readyToApprove.toString());
		        
		        KEWServiceLocator.getRouteHeaderService().saveRouteHeader(documentRouteHeaderValue);
		        
//		        boolean done = false; //hack to deal with optimistic lock exception
//		        for (int i=1;  i < 5; i++) {
//			        if (!done) {
//			        	try {
//			        		KEWServiceLocator.getRouteHeaderService().saveRouteHeader(documentRouteHeaderValue);
//			        		done = true;
//			        	} catch (Exception e) {
//			        		try {
//			        			Thread.sleep(3000);
//			        		} catch (Exception e1){}
//			             }
//			        }
//		        }
		        
		        return readyToApprove;
			}
        	
        });
    	
    	
    	
    }

    private boolean overlappingTimesBlocksFound(TypedPersistentMaintainedEntityList hourDetails) {
        if (Utility.hasValue(hourDetails)) {
            Iterator hourDetailsItr = hourDetails.iterator();
            while (hourDetailsItr.hasNext()) {
                HoursDetail hoursDetail = (HoursDetail)hourDetailsItr.next();
                List comparisonList = new ArrayList();
                comparisonList.addAll(hoursDetail.getHourDetails());
                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
                    HourDetail hourDetail = (HourDetail)iter.next();
                    //adding condition to bypass shifts that begin and end at the same time (bug tk-103)
                    if (hourDetail.getBeginTime().compareTo(hourDetail.getEndTime(), hourDetail.getHoursDetail().getDate()) != 0 && overlappingTimesBlocks(hourDetail, comparisonList)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean overlappingTimesBlocks(HourDetail hourDetail, List comparisonList) {
        if (Utility.hasValue(comparisonList)) {
            Iterator comparisonListItr = comparisonList.iterator();
            while (comparisonListItr.hasNext()) {
                HourDetail compareHourDetail = (HourDetail)comparisonListItr.next();
                if (!getEarningService().isHoursDetailCollectByHoursEarnCode(compareHourDetail.getEarnCode(), compareHourDetail.getHoursDetail().getDate()) && !(hourDetail.getBeginTime().compareTo(hourDetail.getEndTime(), hourDetail.getHoursDetail().getDate()) == 0)) {
                    if (!hourDetail.equals(compareHourDetail) && isOverlapping(hourDetail, compareHourDetail)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isOverlapping(HourDetail hourDetail, HourDetail compareHourDetail) {
        if (Utility.hasValue(hourDetail.getBeginTime()) && Utility.hasValue(compareHourDetail.getBeginTime())) {
            TimedDate hourDetailBegin = new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getBeginTime());
            TimedDate hourDetailEnd = new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getEndTime());
            TimedDate compareHourDetailBegin = new TimedDate(hourDetail.getHoursDetail().getDate(), compareHourDetail.getBeginTime());
            TimedDate compareHourDetailEnd = new TimedDate(hourDetail.getHoursDetail().getDate(), compareHourDetail.getEndTime());

            //adding condition to bypass shifts that begin and end at the same time (bug tk-103)
            if (hourDetailBegin.compareTo(hourDetailEnd) == 0 || compareHourDetailBegin.compareTo(compareHourDetailEnd) == 0) {
                return false;
            }
            if (hourDetailBegin.after(compareHourDetailBegin) && (hourDetailBegin.before(compareHourDetailEnd) || compareHourDetailEnd.after(hourDetailEnd) || hourDetailEnd.equals(compareHourDetailEnd))) {
                return true;
            } else if (compareHourDetailBegin.after(hourDetailBegin) && (compareHourDetailBegin.before(hourDetailEnd) || hourDetailEnd.after(compareHourDetailEnd) || hourDetailEnd.equals(compareHourDetailEnd))) {
                return true;
            } else if (hourDetailBegin.equals(compareHourDetailBegin)) {
                return true;
            }
        }
        return false;
    }

    private boolean invalidAssignmentFound(TimesheetDocument timesheetDocument) {
        TypedPersistentMaintainedEntityList hourDetails = timesheetDocument.getHours().getHoursDetails();
        if (Utility.hasValue(hourDetails)) {
            Iterator hourDetailsItr = hourDetails.iterator();
            while (hourDetailsItr.hasNext()) {
                HoursDetail hoursDetail = (HoursDetail)hourDetailsItr.next();
                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
                    HourDetail hourDetail = (HourDetail)iter.next();
                    Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
                    Timestamp timeblockDate = new Timestamp();
                    timeblockDate.setDate(hoursDetail.getDate());
                    //using hoursDetail's date instead of the timestamp when the record was created
                    Assignment workingAssignment = timesheetDocument.getAssignment((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), (BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), (BigDecimal)assignmentKeyMap.get(FieldNames.TASK), timeblockDate);
                    if (!Utility.hasValue(workingAssignment) || (Utility.hasValue(workingAssignment.getEndDate()) && workingAssignment.getEndDate().before(hoursDetail.getDate()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean ovtChargedToHRW(TimesheetDocument timesheetDocument) {
        TypedPersistentMaintainedEntityList hourDetails = timesheetDocument.getHours().getHoursDetails();
        if (Utility.hasValue(hourDetails)) {
            Iterator hourDetailsItr = hourDetails.iterator();
            while (hourDetailsItr.hasNext()) {
                HoursDetail hoursDetail = (HoursDetail)hourDetailsItr.next();
                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
                    HourDetail hourDetail = (HourDetail)iter.next();
                    Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
                    if (hourDetail.getOvertimeHours() != null && hourDetail.getOvertimeHours().doubleValue() > 0) {
                        if (timesheetDocument.getJob((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hourDetail.getTimestamp()).getSalaryPlan().equals("WSU")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean tuhEarnCodeFound(TypedPersistentMaintainedEntityList hourDetails) {
        if (Utility.hasValue(hourDetails)) {
            List comparisonList = new ArrayList();
            comparisonList.addAll(hourDetails);
            Iterator hourDetailsItr = hourDetails.iterator();
            while (hourDetailsItr.hasNext()) {
                HoursDetail hoursDetail = (HoursDetail)hourDetailsItr.next();
                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
                    HourDetail hourDetail = (HourDetail)iter.next();
                    // TODO:TUH is hard coded
                    if (hourDetail.getEarnCode().equals("TUH")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean expectedHoursMet(TimesheetDocument timesheetDocument) {
        for (int week = 1; week <= 2; week++) {
            if (!expectedHoursMetPerWeek(week, timesheetDocument)) {
                return false;
            }
        }
        return true;
    }

    private boolean expectedHoursMetPerWeek(int week, TimesheetDocument document) {
        TypedPersistentMaintainedEntityList hourDetails = document.getHours().getHoursDetails();
        Map jobExpectedHoursMap = getJobExpectedHoursMap(week, document.getHours(), document.getDocumentHeader().getPayEndDate());
        boolean returnValue = true;
        for (Iterator iter = document.getJobs().iterator(); iter.hasNext();) {
            Job job = (Job)iter.next();
            
            //checking expected hours ONLY if it's a biweekly job and if the job didn't start within current pay period
            if (!job.isHourly() && !job.getEffectiveDate().after(document.getHours().getPayCalendar().getPayBeginDate()) ){

                //TODO: this section is a bit of a hack. if the job has not yet been saved to PS, the job here is null
                edu.iu.uis.hr.job.entity.Job hrJob = getHrJobService().getJob(job.getUniversityId(), job.getEmployeeRecord(), job.getEffectiveDate());
                if (hrJob == null) {
                    return false;
                }
                //end hack

               Frequency frequency = this.getPositionService().getFrequency(hrJob.getFrequencyId(), hrJob.getEffectiveDate());
                //double freqAnnualizedFactor = ((Frequency)((List)hrJob.getStandardHoursFrequencys()).get(0)).getAnnualFrequencyFactor().doubleValue();
               double freqAnnualizedFactor = frequency.getAnnualFrequencyFactor().doubleValue();

                double regularEarningsDistributionPercent = 1.0; // default value if no distribution defined
                if (Utility.hasValue(job.getEarningsDistributions())) { //avoiding NPE for null values
                    for (Iterator iterator = job.getEarningsDistributions().iterator(); iterator.hasNext();) {
                        regularEarningsDistributionPercent = 0.0; // if distribution defined and no reg earnings get found, assume 100% Leave and a dist pct of 0.
                        EarningsDistribution earningsDistribution = (EarningsDistribution)iterator.next();
                        if (earningsDistribution.getEarnCode().startsWith("R")) { //TODO: can we look for reg earnings with this criteria?
                            regularEarningsDistributionPercent = earningsDistribution.getDistributionPercent().doubleValue();
                            break; // found a value, so stop looking
                        }
                    }
                }
                double expectedHours = ((freqAnnualizedFactor) / (PAY_FREQUENCY_ANNUALIZE_FACTOR)) * job.getStandardHours().doubleValue() * regularEarningsDistributionPercent;
                double earnedHours = 0;
                if (jobExpectedHoursMap.containsKey(job.getEmployeeRecord())) {
                    earnedHours = ((BigDecimal)jobExpectedHoursMap.get(job.getEmployeeRecord())).doubleValue();
                }

                if (earnedHours < expectedHours) {
                        //if (workflowDocument.getRouteHeader().getDocRouteLevel().intValue() > 0) {
                            document.getEntityErrors().add(new String[] { "timesheetDocument" }, "Expected hours have not been met for employee record " + job.getEmployeeRecord() + " on week " + week + ". Earned Hours: " + earnedHours + " Expected Hours: " + expectedHours);
                        //}
                    returnValue = false;
                }
            }
        }
        return returnValue;
    }

    private Map getJobExpectedHoursMap(int week, Hours hours, Date effectiveDate) {
        Map jobExpectedHoursMap = new HashMap();
        
        if (Utility.hasValue(hours)) {
            for (int i = (week - 1) * PayCalendar.SATURDAY; i < week * PayCalendar.SATURDAY; i++) {
                HoursDetail hoursDetail = hours.getHoursDetail(i);
                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
                    HourDetail hourDetail = (HourDetail)iter.next();
                    if (getEarningService().isExpectedHoursEarnCode(hourDetail.getEarnCode(), effectiveDate)) {
                        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
                        BigDecimal hoursPerJob = (BigDecimal)jobExpectedHoursMap.get((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD));
                        if (hoursPerJob == null) {
                            hoursPerJob = hourDetail.getHours();
                      } else {
                          hoursPerJob = hoursPerJob.add(hourDetail.getHours());
                      }
                      jobExpectedHoursMap.put((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hoursPerJob);
                    }
                }
            }
        }
        return jobExpectedHoursMap;
    }
    
// Remove following code: It's checking expected hours in biweekly insted of weekly basis
//    private boolean expectedHoursMet(TimesheetDocument document) {
//        TypedPersistentMaintainedEntityList hourDetails = document.getHours().getHoursDetails();
//        Map jobExpectedHoursMap = getJobExpectedHoursMap(hourDetails, document.getDocumentHeader().getPayEndDate());
//        boolean returnValue = true;
//        for (Iterator iter = document.getJobs().iterator(); iter.hasNext();) {
//            Job job = (Job)iter.next();
//            if (!job.isHourly()) {
//
//                //TODO: this section is a bit of a hack. if the job has not yet been saved to PS, the job here is null
//                edu.iu.uis.hr.job.entity.Job hrJob = getHrJobService().getJob(job.getUniversityId(), job.getEmployeeRecord(), job.getEffectiveDate());
//                if (hrJob == null) {
//                    return false;
//                }
//                //TODO: end hack
//
//                job.setStandardHoursFrequencys(hrJob.getStandardHoursFrequencys());
//
//                double freqAnnualizedFactor = ((Frequency)((List)job.getStandardHoursFrequencys()).get(0)).getAnnualFrequencyFactor().doubleValue();
//
//                double regularEarningsDistributionPercent = 1.0; // default value if no distribution defined
//                if (Utility.hasValue(job.getEarningsDistributions())) { //avoiding NPE for null values
//                    for (Iterator iterator = job.getEarningsDistributions().iterator(); iterator.hasNext();) {
//                        regularEarningsDistributionPercent = 0.0; // if distribution defined and no reg earnings get found, assume 100% Leave and a dist pct of 0.
//                        EarningsDistribution earningsDistribution = (EarningsDistribution)iterator.next();
//                        if (earningsDistribution.getEarnCode().startsWith("R")) { //TODO: can we look for reg earnings with this criteria?
//                            regularEarningsDistributionPercent = earningsDistribution.getDistributionPercent().doubleValue();
//                            break; // found a value, so stop looking
//                        }
//                    }
//                }
//                double expectedHours = ((freqAnnualizedFactor) / (PAY_FREQUENCY_ANNUALIZE_FACTOR)) * job.getStandardHours().doubleValue() * regularEarningsDistributionPercent;
//
//                double earnedHours = 0;
//                if (jobExpectedHoursMap.containsKey(job.getEmployeeRecord())) {
//                    earnedHours = ((Double)jobExpectedHoursMap.get(job.getEmployeeRecord())).doubleValue();
//                }
//
//                if (earnedHours < expectedHours) {
//                    try {
//                        WorkflowDocument workflowDocument = new WorkflowDocument(new NetworkIdDTO(WORKFLOW_TK_SUPER_USER), new Long(document.getDocumentId()));
//                        if (workflowDocument.getRouteHeader().getDocRouteLevel().intValue() > 0) {
//                            document.getEntityWarnings().add(new String[] { "timesheetDocument" }, "Expected hours have not been met for employee record " + job.getEmployeeRecord() + ".");
//                        }
//                    } catch (WorkflowException we) {
//                        throw new ExceptionAdapter(we, LOG);
//                    }
//                    returnValue = false;
//                }
//            }
//        }
//        return returnValue;
//    }
//
//    private Map getJobExpectedHoursMap(TypedPersistentMaintainedEntityList hourDetails, Date effectiveDate) {
//        Map jobExpectedHoursMap = new HashMap();
//        if (Utility.hasValue(hourDetails)) {
//            Iterator hourDetailsItr = hourDetails.iterator();
//            while (hourDetailsItr.hasNext()) {
//                HoursDetail hoursDetail = (HoursDetail)hourDetailsItr.next();
//                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
//                    HourDetail hourDetail = (HourDetail)iter.next();
//                    if (getEarningService().isExpectedHoursEarnCode(hourDetail.getEarnCode(), effectiveDate)) {
//                        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
//                        Double hours = (Double)jobExpectedHoursMap.get((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD));
//                        if (hours == null) {
//                            hours = new Double(hourDetail.getHours().doubleValue());
//                        } else {
//                            hours = new Double(hours.doubleValue() + hourDetail.getHours().doubleValue());
//                        }
//                        jobExpectedHoursMap.put((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hours);
//                    }
//                }
//            }
//        }
//        return jobExpectedHoursMap;
//    }
//

    
    
    private boolean absHoursExceedStdHours(TimesheetDocument timesheetDocument) {
        for (int week = 1; week <= 2; week++) {
            if (absHoursExceedStdHoursPerWeek(week, timesheetDocument)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean absHoursExceedStdHoursPerWeek(int week, TimesheetDocument document) {
        TypedPersistentMaintainedEntityList hourDetails = document.getHours().getHoursDetails();
        //Map jobExpectedHoursMap = getJobExpectedHoursMap(week, document.getHours(), document.getDocumentHeader().getPayEndDate());
        Map jobAbsHoursMap = getJobAbsHoursMap(week, document.getHours(), document.getDocumentHeader().getPayEndDate());
        if (Utility.hasValue(jobAbsHoursMap)) { //Adding condition to prevent NPE
           for (Iterator iter = document.getJobs().iterator(); iter.hasNext();) {
           Job job = (Job)iter.next();
              if (!job.isHourly()) {
                  double ABSHours = 0;
                  if (jobAbsHoursMap.containsKey(job.getEmployeeRecord())) {
                      ABSHours = ((BigDecimal)jobAbsHoursMap.get(job.getEmployeeRecord())).doubleValue();
                  }
                  if (ABSHours > job.getStandardHours().doubleValue()) { // && workflowDocument != null && workflowDocument.getRouteHeader().getDocRouteLevel().intValue() > 0) {
                         document.getEntityErrors().add(new String[] { "timesheetDocument" }, " Total ABS hours  exceeds Standard Hours for employee record " + job.getEmployeeRecord() + " on week " + week + ". ABS Hours: " + ABSHours + ". Expected Hours: " + job.getStandardHours());
                         return true;
                 }
              }
           } 
        }
        return false;
        }
    

    private Map getJobAbsHoursMap(int week, Hours hours, Date effectiveDate) {
        Map jobAbsHoursMap = new HashMap();
        
        if (Utility.hasValue(hours)) {
            for (int i = (week - 1) * PayCalendar.SATURDAY; i < week * PayCalendar.SATURDAY; i++) {
                HoursDetail hoursDetail = hours.getHoursDetail(i);
                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
                    HourDetail hourDetail = (HourDetail)iter.next();
                    if (hourDetail.getEarnCode().equals("ABS")) {
                        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
                        BigDecimal hoursPerJob = (BigDecimal)jobAbsHoursMap.get((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD));
                        if (hoursPerJob == null) {
                        	hoursPerJob = hourDetail.getHours();
                        } else {
                        	hoursPerJob = hoursPerJob.add(hourDetail.getHours());
                        }
                        jobAbsHoursMap.put((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hoursPerJob);
                    }
                }
            }
        }
        return jobAbsHoursMap;
    }
    
    
    /* Remove following code after, it's checking ABS in biweekly instead of weekly basis */
//    private boolean absHoursExceedStdHours(TimesheetDocument document) {
//        TypedPersistentMaintainedEntityList hourDetails = document.getHours().getHoursDetails();
//        Map jobAbsHoursMap = getJobAbsHoursMap(hourDetails);
//
//        if (Utility.hasValue(jobAbsHoursMap)) { //Adding condition to prevent NPE
//            for (Iterator iter = document.getJobs().iterator(); iter.hasNext();) {
//                Job job = (Job)iter.next();
//                if (!job.isHourly()) {
//                    if (((Double)jobAbsHoursMap.get(job.getEmployeeRecord())).doubleValue() > job.getStandardHours().doubleValue()) {
//                        // TODO : not complete yet for this check. There are several scenario need to take care.
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//    private Map getJobAbsHoursMap(TypedPersistentMaintainedEntityList hourDetails) {
//        Map jobAbsHoursMap = new HashMap();
//        if (Utility.hasValue(hourDetails)) {
//            Iterator hourDetailsItr = hourDetails.iterator();
//            while (hourDetailsItr.hasNext()) {
//                HoursDetail hoursDetail = (HoursDetail)hourDetailsItr.next();
//                for (Iterator iter = hoursDetail.getHourDetails().iterator(); iter.hasNext();) {
//                    HourDetail hourDetail = (HourDetail)iter.next();
//                    if (hourDetail.getEarnCode().equals("ABS")) {
//                        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
//                        Double hours = (Double)jobAbsHoursMap.get((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD));
//                        if (hours == null) {
//                            hours = new Double(hourDetail.getHours().doubleValue());
//                        } else {
//                            hours = new Double(hours.doubleValue() + hourDetail.getHours().doubleValue());
//                        }
//                        jobAbsHoursMap.put((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), hours);
//                    }
//                }
//            }
//        }
//        return jobAbsHoursMap;
//    }
    


    public TimesheetService getTimesheetService() {
        return (TimesheetService) Context.getService(TimesheetService.class);
}

    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
}


    private EarningService getEarningService() {
        return (EarningService)Context.getService(EarningService.class);
}

    public edu.iu.uis.hr.job.service.JobService getHrJobService() {
        return (edu.iu.uis.hr.job.service.JobService) Context.getService(edu.iu.uis.hr.job.service.JobService.class);
    }

    
	public PositionService getPositionService() {
		return (PositionService) Context.getService(PositionService.class);
	}

}