package org.kuali.hr.time.accrual.service;

import java.util.List;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.kuali.hr.time.accrual.TimeOffAccrual;
import org.kuali.hr.time.accrual.validation.TimeOffAccrualServiceRule;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
/**
 * 
 * @author Jigar
 *
 */
@WebService(endpointInterface="org.kuali.hr.time.accrual.service.TimeOffAccrualService")
public class TimeOffAccrualServiceImpl implements TimeOffAccrualService {
	private static final Logger LOG = Logger
			.getLogger(TimeOffAccrualService.class);

	@Transactional
	public boolean addTimeOffAccruals(List<TimeOffAccrual> timeOffAccruals)
			throws ServiceException {
		try {
			TimeOffAccrualServiceRule timeOffAccrualServiceRule = new TimeOffAccrualServiceRule();
			// validate TimeOffArrcuals
			for (TimeOffAccrual timeOffAccrual : timeOffAccruals) {
				if (!timeOffAccrualServiceRule
						.validateTimeOffAccrualObject(timeOffAccrual)) {
					throw new IllegalArgumentException(
							"Invalid data for timeOffAccrual");
				}
			}
			// save / update TimeOffAccrual
			for (TimeOffAccrual timeOffAccrual : timeOffAccruals) {
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
			}

		} catch (Exception ex) {
			LOG.error(ex);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new ServiceException(ex);
		}
		return true;
	}

}
