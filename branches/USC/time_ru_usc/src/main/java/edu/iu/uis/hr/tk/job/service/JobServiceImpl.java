package edu.iu.uis.hr.tk.job.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.tk.job.dataaccess.JobDataAccess;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;

public class JobServiceImpl extends AbstractService implements JobService {

    private JobDataAccess jobDataAccess;
    private PayCalendarService payCalendarService;
    
	@CacheResult (
    		secondsRefreshPeriod=900
    )
    public Job getJob(String universityId, BigDecimal employeeRecord, Date transactionRecordEffectiveDate) {
    	return getJobDataAccess().getJob(universityId, employeeRecord, transactionRecordEffectiveDate);
    }

	@SuppressWarnings("unchecked")
	@CacheResult (
    		secondsRefreshPeriod=900
    )
    public TypedPersistentMaintainedEntityList getJobs(String universityId, Date transactionRecordEffectiveDate) {
		TypedPersistentMaintainedEntityList jobs = getJobDataAccess().getJobs(universityId, getPayCalendarService().getPayCalendar(transactionRecordEffectiveDate));
        Map employeeRecordToJobRecordsMap = mapEmployeeRecordsToJobs(jobs);
        if (Utility.hasValue(jobs)) {
            Iterator jobsItr = jobs.iterator();
            while (jobsItr.hasNext()) {
                Job job = (Job)jobsItr.next();
                List assignments = (List) job.getAssignments();
                if (Utility.hasValue(assignments)) {
                    Iterator assignmentsItr = assignments.iterator();
                    while (assignmentsItr.hasNext()) {
                        Assignment assignment = (Assignment)assignmentsItr.next();
                        Object jobsForEmployeeRecord = employeeRecordToJobRecordsMap.get(assignment.getJob().getEmployeeRecord());
                        if (Utility.hasValue(jobsForEmployeeRecord) && ((List)jobsForEmployeeRecord).size() >= 1) {
                            setAssignmentEndDate(assignment, (List)jobsForEmployeeRecord, universityId, transactionRecordEffectiveDate, job.getEmployeeRecord());
                        }
                        assignment.setAssignmentDescription(getAssignmentService().getAssignmentDescription(assignment));
                    }
                }
            }
        }
        return jobs;
    }

    public TypedPersistentMaintainedEntityList getJobs(String universityId, Date transactionRecordEffectiveDate, boolean checkPayPeriodOnly) {
		TypedPersistentMaintainedEntityList jobs = getJobDataAccess().getJobs(universityId, getPayCalendarService().getPayCalendar(transactionRecordEffectiveDate), true);
        return jobs;
    }
	
    @CacheResult
    public TypedPersistentMaintainedEntityList getTermedJobs(String universityId, Date transactionRecordEffectiveDate, BigDecimal employeeRecord) {
    	return getJobDataAccess().getTermedJobs(universityId, getPayCalendarService().getPayCalendar(new TimelessDate(transactionRecordEffectiveDate).getDate()), employeeRecord);
    }

	@SuppressWarnings("unchecked")
	@CacheResult (
    		secondsRefreshPeriod=900
    )
    public List getJobs(Date date, BigDecimal workArea) {
    	return getJobDataAccess().getJobs(date, workArea);
    }
    
	@SuppressWarnings("unchecked")
	@CacheResult (
    		secondsRefreshPeriod=900
    )
    public List getJobs(Date date, BigDecimal workArea, BigDecimal taskId) {
        return getJobDataAccess().getJobs(date, workArea, taskId);
    }
    
    @SuppressWarnings("unchecked")
	@CacheResult (
    		secondsRefreshPeriod=900
    )
    public TypedPersistentMaintainedEntityList getJobsWithAssignments(String universityId, Date transactionRecordEffectiveDate) {
        TypedPersistentMaintainedEntityList jobs = getJobDataAccess().getJobs(universityId, getPayCalendarService().getPayCalendar(transactionRecordEffectiveDate));
        Map employeeRecordToJobRecordsMap = mapEmployeeRecordsToJobs(jobs);
        if (Utility.hasValue(jobs)) {
            Iterator jobsItr = jobs.iterator();
            while (jobsItr.hasNext()) {
                Job job = (Job)jobsItr.next();
                List assignments = (List) job.getAssignments();
                if (Utility.hasValue(assignments)) {
                    Iterator assignmentsItr = assignments.iterator();
                    while (assignmentsItr.hasNext()) {
                        Assignment assignment = (Assignment)assignmentsItr.next();
                       Object jobsForEmployeeRecord = employeeRecordToJobRecordsMap.get(assignment.getJob().getEmployeeRecord());
                        if (Utility.hasValue(jobsForEmployeeRecord) && ((List)jobsForEmployeeRecord).size() > 1) {
                            setAssignmentEndDate(assignment, (List)jobsForEmployeeRecord, universityId, transactionRecordEffectiveDate, job.getEmployeeRecord());
                        }
                        assignment.setAssignmentDescription(getAssignmentService().getAssignmentDescription(assignment));
                        
                    }
                } else { //removes job from list if it doesn't have assignments
                    jobsItr.remove();
                }
            }
        }
        return jobs;
    }
    
    @SuppressWarnings("unchecked")
	private void setAssignmentEndDate(Assignment assignment, List jobsForEmployeeRecord, String universityId, Date transactionRecordEffectiveDate, BigDecimal employeeRecord){
    	TypedPersistentMaintainedEntityList termedJobs = getTermedJobs(universityId, transactionRecordEffectiveDate,  employeeRecord);
        if(Utility.hasValue(termedJobs)){
         	jobsForEmployeeRecord.addAll(termedJobs);
        }
        
        TimelessDate endDate = null;
        TimelessDate assignmentDate = new TimelessDate(assignment.getJob().getEffectiveDate());
        Iterator jobsForEmployeeRecordItr = jobsForEmployeeRecord.iterator();
        while (jobsForEmployeeRecordItr.hasNext()) {
            TimelessDate compareJobDate = new TimelessDate(((Job)jobsForEmployeeRecordItr.next()).getEffectiveDate());
            if (assignmentDate.before(compareJobDate)) {
                if (!Utility.hasValue(endDate) || (Utility.hasValue(endDate) && compareJobDate.before(endDate))) {
                    endDate = compareJobDate;
                }
            }
        }
        if (Utility.hasValue(endDate)) {
            assignment.setEndDate(endDate.addDays(-1).getDate());
        }
    }

    @SuppressWarnings("unchecked")
	private Map mapEmployeeRecordsToJobs(TypedPersistentMaintainedEntityList jobs) {
        Map employeeRecordsToJobsMap = new HashMap();
        if (Utility.hasValue(jobs)) {
            Iterator jobsItr = jobs.iterator();
            while (jobsItr.hasNext()) {
                Job job = (Job)jobsItr.next();
                if (Utility.hasValue(employeeRecordsToJobsMap.get(job.getEmployeeRecord()))) {
                    ((List)employeeRecordsToJobsMap.get(job.getEmployeeRecord())).add(job);
                } else {
                    List jobsForEmployeeRecord = new ArrayList();
                    jobsForEmployeeRecord.add(job);
                    employeeRecordsToJobsMap.put(job.getEmployeeRecord(), jobsForEmployeeRecord);
                }

            }
        }
        return employeeRecordsToJobsMap;
    }

    private JobDataAccess getJobDataAccess() {
        return jobDataAccess;
    }

    public void setJobDataAccess(JobDataAccess jobDataAccess) {
        if (jobDataAccess != null) {
            this.jobDataAccess = jobDataAccess;
        }
    }

    private PayCalendarService getPayCalendarService() {
        return payCalendarService;
    }

    public void setPayCalendarService(PayCalendarService payCalendarService) {
        if (payCalendarService != null) {
            this.payCalendarService = payCalendarService;
        }
    }

    private AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }

}