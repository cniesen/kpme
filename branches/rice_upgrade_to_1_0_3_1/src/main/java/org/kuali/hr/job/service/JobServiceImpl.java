package org.kuali.hr.job.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.job.Job;
import org.kuali.hr.job.dao.JobDao;
import org.kuali.hr.time.paytype.PayType;
import org.kuali.hr.time.service.base.TkServiceLocator;
/**
 * Represents an implementation of {@link JobService}.
 * 
 *
 */
public class JobServiceImpl implements JobService {

	private JobDao jobDao;

	@Override
	public void saveOrUpdate(Job job) {
		jobDao.saveOrUpdate(job);
	}

	@Override
	public void saveOrUpdate(List<Job> jobList) {
		jobDao.saveOrUpdate(jobList);
	}

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	@Override
	public List<Job> getJobs(String principalId, Date asOfDate) {
		List<Job> jobs = jobDao.getJobs(principalId, asOfDate);

		for (Job job : jobs) {
			PayType payType = TkServiceLocator.getPayTypeSerivce().getPayType(
					job.getHrPayType(), asOfDate);
			job.setPayTypeObj(payType);
		}

		return jobs;
	}

	@Override
	public Job getJob(String principalId, Long jobNumber, Date asOfDate) {
		return getJob(principalId, jobNumber, asOfDate, true);
	}

	public Job getPrimaryJob(String principalId, Date payPeriodEndDate) {
		return jobDao.getPrimaryJob(principalId, payPeriodEndDate);
	}
	
	@Override
	public Job getJob(String principalId, Long jobNumber, Date asOfDate,
			boolean chkDetails) {
		Job job = jobDao.getJob(principalId, jobNumber, asOfDate);
		if (job == null && chkDetails) {
			throw new RuntimeException("No job for principal : " + principalId
					+ " Job Number: " + jobNumber);
		}
		if (chkDetails) {
			String hrPayType = job.getHrPayType();
			if (StringUtils.isBlank(hrPayType)) {
				throw new RuntimeException("No pay type for this job!");
			}
			PayType payType = TkServiceLocator.getPayTypeSerivce().getPayType(
					hrPayType, asOfDate);
			if (payType == null)
				throw new RuntimeException("No paytypes defined for this job!");
			job.setPayTypeObj(payType);
		}
		return job;
	}

	@Override
	public List<Job> getActiveJobsForPosition(Long positionNbr, Date asOfDate) {
		return jobDao.getActiveJobsForPosition(positionNbr, asOfDate);
	}

}
