package org.kuali.hr.time.assignment.service;

import java.util.List;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.kuali.hr.time.assignment.AssignmentAccount;
import org.kuali.hr.time.assignment.validation.AssignmentAccountServiceRule;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * 
 * @author Jigar
 * 
 */
@WebService(endpointInterface = "org.kuali.hr.time.assignment.service.AssignmentAccountService")
public class AssignmentAccountServiceImpl implements AssignmentAccountService {
	private static final Logger log = Logger
			.getLogger(AssignmentAccountService.class);

	public boolean addAssignmentAccounts(
			List<AssignmentAccount> assignmentAccounts) throws ServiceException {
		ServiceException serviceException = new ServiceException(
				"Error in AssignmentAccount WebService");

		AssignmentAccountServiceRule assignmentAccountServiceRule = new AssignmentAccountServiceRule();

		// saving all data
		for (AssignmentAccount assignmentAccount : assignmentAccounts) {
			try {
				// validating data
				if (!assignmentAccountServiceRule
						.validateAssignmentAccountObject(assignmentAccount)) {
					throw new IllegalArgumentException(
							"Invalid data for assignmentAccount");
				}

				if (assignmentAccount.getTkAssignAcctId() != null) {
					AssignmentAccount oldAssignmentAccount = KNSServiceLocator
							.getBusinessObjectService().findBySinglePrimaryKey(
									AssignmentAccount.class,
									assignmentAccount.getTkAssignAcctId());
					oldAssignmentAccount.setActive(Boolean.FALSE);
					KNSServiceLocator.getBusinessObjectService().save(
							oldAssignmentAccount);
				}
				assignmentAccount.setTkAssignAcctId(null);
				KNSServiceLocator.getBusinessObjectService().save(
						assignmentAccount);

			} catch (Exception ex) {
				log.error("Error with AssignmentAccount Object:"
						+ assignmentAccount, ex);
				serviceException.add(assignmentAccount, ex);
			}

		}
		if (serviceException.hasErrors()) {
			throw serviceException;
		}
		return true;
	}
}
