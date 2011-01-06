package org.kuali.hr.job.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.hr.job.Job;
import org.kuali.hr.job.dao.JobDao;
import org.kuali.hr.job.validation.JobServiceRule;
import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.paytype.PayType;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;

@WebService(endpointInterface = "org.kuali.hr.job.service.JobService")
public class JobServiceImpl implements JobService {
	private static final Logger log = Logger.getLogger(JobService.class);

	@Override
	public void saveOrUpdate(Job job) {
		SpringContext.getBean(JobDao.class).saveOrUpdate(job);
	}

	@Override
	public void saveOrUpdate(List<Job> jobList) {
		SpringContext.getBean(JobDao.class).saveOrUpdate(jobList);
	}

	@Override
	public List<Job> getJobs(String principalId, Date asOfDate) {
		List<Job> jobs = SpringContext.getBean(JobDao.class).getJobs(
				principalId, asOfDate);

		for (Job job : jobs) {
			PayType payType = TkServiceLocator.getPayTypeSerivce().getPayType(
					job.getHrPayType(), asOfDate);
			job.setPayTypeObj(payType);
		}

		return jobs;
	}

	@Override
	public Job getJob(String principalId, Long jobNumber, Date asOfDate) {
		Job job = SpringContext.getBean(JobDao.class).getJob(principalId,
				jobNumber, asOfDate);
		if (job == null) {
			throw new RuntimeException("No job for principal : " + principalId + " Job Number: " + jobNumber);
		}
		String hrPayType = job.getHrPayType();
		if (StringUtils.isBlank(hrPayType)) {
			throw new RuntimeException("No pay type for this job!");
		}
		PayType payType = TkServiceLocator.getPayTypeSerivce().getPayType(
				hrPayType, asOfDate);
		if (payType == null)
			throw new RuntimeException("No paytypes defined for this job!");
		job.setPayTypeObj(payType);

		return job;
	}


	public boolean addJobs(List<Job> jobs) throws ServiceException {
		JobServiceRule jobServiceRule = new JobServiceRule();
		ServiceException serviceException  = new ServiceException("Error in Job WebService");
		// save / update jobs
		for (Job job : jobs) {
			try {
				// validation
				if (!jobServiceRule.validateJobObject(job)) {
					throw new IllegalArgumentException("invalid data for job");
				}
				Map<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("principalId", job.getPrincipalId());
				queryMap.put("jobNumber", job.getJobNumber());
				Job oldJob = (Job) KNSServiceLocator.getBusinessObjectService()
						.findByPrimaryKey(Job.class, queryMap);
				if (oldJob != null) {
					// oldJob.setActive(Boolean.FALSE);
					oldJob.setPrincipalId(job.getPrincipalId());
					oldJob.setJobNumber(job.getJobNumber());
					oldJob.setEffectiveDate(job.getEffectiveDate());
					oldJob.setDept(job.getDept());
					oldJob.setTkSalGroup(job.getTkSalGroup());
					oldJob.setFte(job.getFte());
					oldJob.setHrPayType(job.getHrPayType());
					oldJob.setPayGrade(job.getPayGrade());
					oldJob.setCompRate(job.getCompRate());
					oldJob.setLocation(job.getLocation());
					oldJob.setActive(job.getActive());
					oldJob.setTimestamp(new Timestamp(Calendar.getInstance()
							.getTimeInMillis()));
					KNSServiceLocator.getBusinessObjectService().save(oldJob);
				} else {
					job.setHrJobId(null);
					job.setTimestamp(new Timestamp(Calendar.getInstance()
							.getTimeInMillis()));
					KNSServiceLocator.getBusinessObjectService().save(job);
				}

			} catch (Exception ex) {
				log.error("Error with job Object:"+ job, ex);
				serviceException.add(job, ex);
			}
		}
		if(serviceException.hasErrors()){
			throw serviceException;
		}
		return true;
	}
	
	public Job getPrimaryJob(String principalId, Date payPeriodEndDate){
		return SpringContext.getBean(JobDao.class).getPrimaryJob(principalId, payPeriodEndDate);
	}

}
