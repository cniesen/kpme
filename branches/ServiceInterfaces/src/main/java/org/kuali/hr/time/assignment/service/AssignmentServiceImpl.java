package org.kuali.hr.time.assignment.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.assignment.AssignmentDescriptionKey;
import org.kuali.hr.time.assignment.dao.AssignmentDao;
import org.kuali.hr.time.assignment.validation.AssignmentServiceRule;
import org.kuali.hr.time.timesheet.TimesheetDocument;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;

@WebService(endpointInterface = "org.kuali.hr.time.assignment.service.AssignmentService")
public class AssignmentServiceImpl implements AssignmentService {

	private static final Logger log = Logger
			.getLogger(AssignmentServiceImpl.class);

	@Override
	public List<Assignment> getAssignments(String principalId, Date asOfDate) {
		if (asOfDate == null) {
			asOfDate = TKUtils.getCurrentDate();
		}
		return SpringContext.getBean(AssignmentDao.class).findAssignments(
				principalId, asOfDate);
	}

	@Override
	public AssignmentDescriptionKey getAssignmentDescriptionKey(
			String assignmentKey) {
		return new AssignmentDescriptionKey(assignmentKey);
	}

	@Override
	public Map<String, String> getAssignmentDescriptions(TimesheetDocument td) {
		if (td == null) {
			throw new RuntimeException("timesheet document is null.");
		}
		List<Assignment> assignments = td.getAssignments();
		if (assignments.size() < 1) {
			throw new RuntimeException(
					"No assignment on the timesheet document.");
		}

		Map<String, String> assignmentDescriptions = new LinkedHashMap<String, String>();
		for (Assignment assignment : assignments) {
			assignmentDescriptions.putAll(TKUtils
					.formatAssignmentDescription(assignment));
		}

		return assignmentDescriptions;
	}

	public Map<String, String> getAssignmentDescriptions(Assignment assignment) {
		if (assignment == null) {
			throw new RuntimeException("Assignment is null");
		}

		Map<String, String> assignmentDescriptions = new LinkedHashMap<String, String>();
		assignmentDescriptions.putAll(TKUtils
				.formatAssignmentDescription(assignment));

		return assignmentDescriptions;

	}

	@Override
	public Assignment getAssignment(TimesheetDocument timesheetDocument,
			String assignmentKey) {
		List<Assignment> assignments = timesheetDocument.getAssignments();
		AssignmentDescriptionKey desc = getAssignmentDescriptionKey(assignmentKey);

		for (Assignment assignment : assignments) {
			if (assignment.getJobNumber().compareTo(desc.getJobNumber()) == 0
					&& assignment.getWorkArea().compareTo(desc.getWorkArea()) == 0
					&& assignment.getTask().compareTo(desc.getTask()) == 0) {
				return assignment;
			}
		}

		log.warn("no matched assignment found");
		return new Assignment();
	}

	
	public boolean addAssignments(List<Assignment> assignments)
			throws ServiceException {

		AssignmentServiceRule assignmentServiceRule = new AssignmentServiceRule();
		ServiceException serviceException = new ServiceException("Error in Assignment WebService");
		// save /update
		
		for (Assignment assignment : assignments) {
			try {
				// validation
				if (!assignmentServiceRule.validateAssignmentObject(assignment)) {
					throw new IllegalArgumentException(
							"Invalid data for Assignment");
				}

				if (assignment.getTkAssignmentId() != null) {
					Assignment oldAssignment = KNSServiceLocator
							.getBusinessObjectService().findBySinglePrimaryKey(
									Assignment.class,
									assignment.getTkAssignmentId());
					oldAssignment.setActive(Boolean.FALSE);
					oldAssignment.setTimestamp(new Timestamp(Calendar
							.getInstance().getTimeInMillis()));

					KNSServiceLocator.getBusinessObjectService().save(
							oldAssignment);
				}

				assignment.setTimestamp(new Timestamp(Calendar.getInstance()
						.getTimeInMillis()));
				assignment.setTkAssignmentId(null);
				KNSServiceLocator.getBusinessObjectService().save(assignment);

			} catch (Exception ex) {
				log.error("Error with Assignment Object:"
						+ assignment, ex);
				serviceException.add(assignment, ex);
			}
		}
		if(serviceException.hasErrors()){
			throw serviceException;	
		}
		
		return true;
	}
}
