package edu.iu.uis.hr.tk.job.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.service.Service;
import edu.iu.uis.hr.tk.job.entity.Job;

public interface JobService extends Service {

    public Job getJob(String universityId, BigDecimal employeeRecord, Date transactionRecordEffectiveDate);

    public TypedPersistentMaintainedEntityList getJobs(String universityId, Date transactionRecordEffectiveDate);

    public TypedPersistentMaintainedEntityList getJobsWithAssignments(String universityId, Date transactionRecordEffectiveDate);

    @SuppressWarnings("unchecked")
	public List getJobs(Date date, BigDecimal workArea);
    
    @SuppressWarnings("unchecked")
	public List getJobs(Date date, BigDecimal workArea, BigDecimal taskId);
    
    public TypedPersistentMaintainedEntityList getJobs(String universityId, Date transactionRecordEffectiveDate, boolean checkPayPeriodOnly);    
    
}