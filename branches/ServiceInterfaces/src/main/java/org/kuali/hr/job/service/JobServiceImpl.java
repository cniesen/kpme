package org.kuali.hr.job.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
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
			job.setPayType(payType);
		}

		return jobs;
	}

	@Override
	public Job getJob(String principalId, Long jobNumber, Date asOfDate) {
		Job job = SpringContext.getBean(JobDao.class).getJob(principalId,
				jobNumber, asOfDate);
		if (job == null) {
			throw new RuntimeException("No job for principal : " + principalId);
		}
		String hrPayType = job.getHrPayType();
		if (StringUtils.isBlank(hrPayType)) {
			throw new RuntimeException("No pay type for this job!");
		}
		PayType payType = TkServiceLocator.getPayTypeSerivce().getPayType(
				hrPayType, asOfDate);
		if (payType == null)
			throw new RuntimeException("No paytypes defined for this job!");
		job.setPayType(payType);

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
				if (job.getHrJobId() != null) {
					Job oldJob = KNSServiceLocator
							.getBusinessObjectService()
							.findBySinglePrimaryKey(Job.class, job.getHrJobId());
					oldJob.setActive(Boolean.FALSE);
					oldJob.setTimestamp(new Timestamp(Calendar.getInstance()
							.getTimeInMillis()));
					KNSServiceLocator.getBusinessObjectService().save(oldJob);
				}
				job.setHrJobId(null);
				job.setTimestamp(new Timestamp(Calendar.getInstance()
						.getTimeInMillis()));
				KNSServiceLocator.getBusinessObjectService().save(job);

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

}
