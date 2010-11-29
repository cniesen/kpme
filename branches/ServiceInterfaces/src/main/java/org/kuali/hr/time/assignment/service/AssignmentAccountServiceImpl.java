package org.kuali.hr.time.assignment.service;

import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.kuali.hr.time.assignment.AssignmentAccount;
import org.kuali.hr.time.assignment.validation.AssignmentAccountServiceRule;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 
 * @author Jigar
 *
 */
@WebService(endpointInterface="org.kuali.hr.time.assignment.service.AssignmentAccountService")
public class AssignmentAccountServiceImpl implements AssignmentAccountService {
	private static final Logger LOG = Logger
			.getLogger(AssignmentAccountService.class);

	@Transactional
	public boolean addAssignmentAccounts(
			List<AssignmentAccount> assignmentAccounts) throws ServiceException {
		try {

			AssignmentAccountServiceRule assignmentAccountServiceRule = new AssignmentAccountServiceRule();
			// validating all data
			for (AssignmentAccount assignmentAccount : assignmentAccounts) {
				if (!assignmentAccountServiceRule
						.validateAssignmentAccountObject(assignmentAccount)) {
					throw new IllegalArgumentException(
							"Invalid data for assignmentAccount");
				}
			}
			// saving all data
			for (AssignmentAccount assignmentAccount : assignmentAccounts) {
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
			}

		} catch (Exception ex) {			
			LOG.error(ex);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new ServiceException(ex);
		}
		return true;
	}

}
