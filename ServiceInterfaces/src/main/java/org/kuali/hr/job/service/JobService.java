package org.kuali.hr.job.service;

import java.sql.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.hr.job.Job;
import org.kuali.hr.time.assignment.AssignmentAccount;
import org.kuali.hr.time.util.exceptions.ServiceException;


@WebService(name = "jobServiceSOAP", targetNamespace = "/tk")
@javax.jws.soap.SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface JobService {
	@WebMethod(exclude = true)
	public void saveOrUpdate(Job job);

	@WebMethod(exclude = true)
	public void saveOrUpdate(List<Job> jobList);

	/**
	 * Provides a list of current jobs that are valid relative to the provided
	 * effective date. Timestamp of row creation is taken into account when two
	 * rows with the same job number have the same effective date.
	 * 
	 * Assignments are NOT populated on this object. We may want to consider
	 * removing getAssignments().
	 * 
	 * @param principalId
	 * @param asOfDate
	 * @return
	 */
	@WebMethod(exclude = true)
	public List<Job> getJobs(String principalId, Date asOfDate);

	@WebMethod(exclude = true)
	public Job getJob(String principalId, Long jobNumber, Date asOfDate);

	@WebMethod
	public boolean addJobs(@WebParam(name = "jobs") List<Job> jobs)
			throws ServiceException;

}
