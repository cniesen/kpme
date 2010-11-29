package org.kuali.hr.time.accrual.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.kuali.hr.time.accrual.AccrualCategory;
import org.kuali.hr.time.accrual.validation.AccrualCategoryServiceRule;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 
 * @author Jigar
 *
 */
@WebService(endpointInterface = "org.kuali.hr.time.accrual.service.AccrualCategoryService")
public class AccrualCategoryServiceImpl implements AccrualCategoryService {
	private static final Logger LOG = Logger
			.getLogger(AccrualCategoryService.class);

	@Transactional
	public boolean addAccrualCategories(List<AccrualCategory> accrualCategories)
			throws ServiceException {
		try {
			AccrualCategoryServiceRule accrualCategoryServiceRule = new AccrualCategoryServiceRule();
			// validating xml data
			for (AccrualCategory accrualCategory : accrualCategories) {
				if (!accrualCategoryServiceRule
						.validateAccrualCategoryObject(accrualCategory)) {
					throw new IllegalArgumentException(
							"Invalid data for accrualCategory");
				}
			}
			//save / update
			for (AccrualCategory accrualCategory : accrualCategories) {
				if (accrualCategory.getLaAccrualCategoryId() != null) {
					AccrualCategory oldAccrualCategory = KNSServiceLocator
							.getBusinessObjectService().findBySinglePrimaryKey(
									AccrualCategory.class,
									accrualCategory.getLaAccrualCategoryId());

					oldAccrualCategory.setTimestamp(new Timestamp(Calendar
							.getInstance().getTimeInMillis()));
					oldAccrualCategory.setActive(Boolean.FALSE);
					KNSServiceLocator.getBusinessObjectService().save(
							oldAccrualCategory);
				}
				accrualCategory.setLaAccrualCategoryId(null);
				accrualCategory.setTimestamp(new Timestamp(Calendar
						.getInstance().getTimeInMillis()));
				KNSServiceLocator.getBusinessObjectService().save(
						accrualCategory);
			}

		} catch (Exception ex) {
			LOG.error(ex);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new ServiceException(ex);
		}
		return true;
	}

}
