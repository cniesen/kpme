/**
 * Copyright 2004-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.hr.job.service;

import org.kuali.hr.job.Job;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface JobService {

	/**
	 * Updates or saves a job
	 * @param job
	 */
    @CacheEvict(value={Job.CACHE_NAME}, allEntries = true)
	public void saveOrUpdate(Job job);
	
	/**
	 * Updates or saves a list of jobs
	 * @param jobList
	 */
    @CacheEvict(value={Job.CACHE_NAME}, allEntries = true)
	public void saveOrUpdate(List<Job> jobList);
	
	/**
	 * Provides a list of current jobs that are valid relative to the provided effective date.  
	 * Timestamp of row creation is taken into account when two rows with the same job number
	 * have the same effective date.
	 * 
	 * Assignments are NOT populated on this object.  We may want to consider removing 
	 * getAssignments().
	 * 
	 * @param principalId
	 * @param asOfDate
	 * @return
	 */
    @Cacheable(value= Job.CACHE_NAME, key="'principalId=' + #p0 + '|' + 'asOfDate=' + #p1")
	public List<Job> getJobs(String principalId, Date asOfDate);
	
	/**
	 * Provides a job by specific job number, principal ID and as of Date combination. 
	 */
    @Cacheable(value= Job.CACHE_NAME, key="'principalId=' + #p0 + '|' + 'jobNumber=' + #p1 + '|' + 'asOfDate=' + #p2")
	public Job getJob(String principalId, Long jobNumber, Date asOfDate);
	
	/**
	 * Provides a job by specific job number, principal ID and as of Date combination, and check details will throw error if required. 
	 */
    @Cacheable(value= Job.CACHE_NAME, key="'principalId=' + #p0 + '|' + 'jobNumber=' + #p1 + '|' + 'asOfDate=' + #p2 + '|' + 'chkDetails=' + #p3")
	public Job getJob(String principalId, Long jobNumber, Date asOfDate, boolean chkDetails);
	
	/**
	 * For a given principal ID, the job that is marked "primary" is returned 
	 * here.
	 * 
	 * @param principalId The principal under investigation
	 * @param asOfDate Run the request as of this date. 
	 * @return
	 */
    @Cacheable(value= Job.CACHE_NAME, key="'{getPrimaryJob}' + 'principalId=' + #p0 + '|' + 'asOfDate=' + #p1")
	public Job getPrimaryJob(String principalId, Date asOfDate);
	
	/**
	 * 
	 * @param positionNbr
	 * @param asOfDate
	 * @return
	 */
    @Cacheable(value= Job.CACHE_NAME, key="'positionNbr=' + #p0 + '|' + 'asOfDate=' + #p1")
	public List<Job> getActiveJobsForPosition(String positionNbr, Date asOfDate);
	
	/**
	 * 
	 * @param hrPayType
	 * @param asOfDate
	 * @return
	 */
    @Cacheable(value= Job.CACHE_NAME, key="'hrPayType=' + #p0 + '|' + 'asOfDate=' + #p1")
	public List<Job> getActiveJobsForPayType(String hrPayType, Date asOfDate);
	
	/**
	 * Get job by the unique id
	 * @param hrJobId
	 * @return
	 */
    @Cacheable(value= Job.CACHE_NAME, key="'hrJobId=' + #p0")
	public Job getJob(String hrJobId);
	
	/**
	 * Get the max jobnumber job for this principal
	 * @param principalId
	 * @return
	 */
    @Cacheable(value= Job.CACHE_NAME, key="'principalId=' + #p0")
	public Job getMaxJob(String principalId);

    List<Job> getJobs(String principalId, String firstName, String lastName, String jobNumber,
                      String dept, String positionNbr, String payType,
                      java.sql.Date fromEffdt, java.sql.Date toEffdt, String active, String showHistory);
    
    public int getJobCount(String principalId, Long jobNumber, String dept);


    /**
     * Get sum of standard hours of given jobs
     * @param jobs
     * @return
     */
    public BigDecimal getStandardHoursSumForJobs(List<Job> jobs);


}
