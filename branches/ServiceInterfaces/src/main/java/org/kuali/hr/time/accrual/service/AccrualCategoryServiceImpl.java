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

/**
 * 
 * @author Jigar
 * 
 */
@WebService(endpointInterface = "org.kuali.hr.time.accrual.service.AccrualCategoryService")
public class AccrualCategoryServiceImpl implements AccrualCategoryService {
	private static final Logger log = Logger
			.getLogger(AccrualCategoryService.class);

	public boolean addAccrualCategories(List<AccrualCategory> accrualCategories)
			throws ServiceException {

		AccrualCategoryServiceRule accrualCategoryServiceRule = new AccrualCategoryServiceRule();
		ServiceException serviceException = new ServiceException(
				"Error in AccrualCategory WebService");

		// save / update
		for (AccrualCategory accrualCategory : accrualCategories) {
			try {
				// validation
				if (!accrualCategoryServiceRule
						.validateAccrualCategoryObject(accrualCategory)) {
					throw new IllegalArgumentException(
							"Invalid data for accrualCategory");
				}

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

			} catch (Exception ex) {
				log.error("Error with AccrualCategory Object:"
						+ accrualCategory, ex);
				serviceException.add(accrualCategory, ex);

			}
		}
		if (serviceException.hasErrors()) {
			throw serviceException;
		}
		return true;
	}
}
