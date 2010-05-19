package edu.iu.uis.hr.tk.job.dataaccess;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.job.entity.Job;

public interface JobDataAccess extends DataAccessOjb {

    public Job getJob(String universityId, BigDecimal employeeRecord, Date transactionRecordEffectiveDate);

    public TypedPersistentMaintainedEntityList getJobs(String universityId, PayCalendar payCalendar);

//    public List getJobs(PayCalendar payCalendar);

    @SuppressWarnings("unchecked")
	public List getJobs(Date date, BigDecimal workArea);
    
    @SuppressWarnings("unchecked")
    public List getJobs(Date date, BigDecimal workArea, BigDecimal taskId);    
    
    public TypedPersistentMaintainedEntityList getTermedJobs(String universityId, PayCalendar payCalendar, BigDecimal employeeRecord);
    
    public TypedPersistentMaintainedEntityList getJobs(String universityId, PayCalendar payCalendar, boolean checkPayPeriodOnly);
    
}
