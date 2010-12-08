package org.kuali.hr.time.assignment.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.assignment.AssignmentDescriptionKey;
import org.kuali.hr.time.timesheet.TimesheetDocument;
import org.kuali.hr.time.util.exceptions.ServiceException;


@WebService(name = "assignmentServiceSOAP", targetNamespace = "/tk")
@javax.jws.soap.SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AssignmentService {
	@WebMethod(exclude = true)
	public List<Assignment> getAssignments(String principalId, Date asOfDate);

	@WebMethod(exclude = true)
	public Assignment getAssignment(TimesheetDocument timesheetDocument,
			String assignmentKey);

	@WebMethod(exclude = true)
	public AssignmentDescriptionKey getAssignmentDescriptionKey(
			String assignmentDesc);

	@WebMethod(exclude = true)
	public Map<String, String> getAssignmentDescriptions(TimesheetDocument td);

	@WebMethod(exclude = true)
	public Map<String, String> getAssignmentDescriptions(Assignment assignment);

	@WebMethod
	public boolean addAssignments(
			@WebParam(name = "assignments") List<Assignment> assignments)
			throws ServiceException;

}
