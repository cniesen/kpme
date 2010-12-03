package org.kuali.hr.time.accrual.service;

import java.util.List;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.kuali.hr.time.accrual.TimeOffAccrual;
import org.kuali.hr.time.accrual.validation.TimeOffAccrualServiceRule;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;

import sun.print.resources.serviceui;

/**
 * 
 * @author Jigar
 * 
 */
@WebService(endpointInterface = "org.kuali.hr.time.accrual.service.TimeOffAccrualService")
public class TimeOffAccrualServiceImpl implements TimeOffAccrualService {
	private static final Logger log = Logger
			.getLogger(TimeOffAccrualService.class);

	public boolean addTimeOffAccruals(List<TimeOffAccrual> timeOffAccruals)
			throws ServiceException {

		TimeOffAccrualServiceRule timeOffAccrualServiceRule = new TimeOffAccrualServiceRule();
		ServiceException serviceException = new ServiceException(
				"Error in TimeOffAccrual WebService");
		// save / update TimeOffAccrual
		for (TimeOffAccrual timeOffAccrual : timeOffAccruals) {
			try {
				// validation
				if (!timeOffAccrualServiceRule
						.validateTimeOffAccrualObject(timeOffAccrual)) {
					throw new IllegalArgumentException(
							"Invalid data for timeOffAccrual");
				}
				if (timeOffAccrual.getLaAccrualId() != null) {
					TimeOffAccrual oldTimeOffAccrual = KNSServiceLocator
							.getBusinessObjectService().findBySinglePrimaryKey(
									TimeOffAccrual.class,
									timeOffAccrual.getLaAccrualId());

					KNSServiceLocator.getBusinessObjectService().save(
							oldTimeOffAccrual);
				}

				timeOffAccrual.setLaAccrualId(null);
				KNSServiceLocator.getBusinessObjectService().save(
						timeOffAccrual);

			} catch (Exception ex) {
				log.error("Error with TimeOffAccrual Object:" + timeOffAccrual,
						ex);
				serviceException.add(timeOffAccrual, ex);

			}
		}
		if(serviceException.hasErrors()){
			throw serviceException;
		}
		return true;
	}
}
